package server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import struct.Message;


public class ThreadOut extends Thread {

	private ObjectOutputStream out;
	
	public ThreadOut(ObjectOutputStream out) {
		this.out = out;
	}

	@Override
	public void run() {
		
	}

	public synchronized void sendMessage(Message message) {
		try {
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
