package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import struct.Message;
import struct.Request;


public class ThreadIn extends Thread {

	private ObjectInputStream in;
	private  transient ObjectOutputStream out;
	private Server s;
	private ThreadOut to;

	public ThreadIn(Server s, ObjectInputStream in, ObjectOutputStream out) {
		this.s = s;
		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Message m = (Message) in.readObject();
				switch (m.getType()) {
				case CLIENT:
				    this.to = new ThreadOut(out);
//					this.s.getRequest_list().add(new Request(m.getRequest().getRequestString(), this.to));
					this.s.newRequestArrived(new Request(m.getRequest().getRequestString(), this.to));
					this.to.start();
					break;
				case WORKER:
					this.to = new ThreadOut(out);
					s.getWorker_queue().add(this.to);
					break;
					
				case RESULT:
					s.leadWithResult(m);
					break;
					
				default:
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();	
			}
		}
	}
	
}
