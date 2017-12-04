package server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

import struct.Article;
import struct.Message;
import struct.Request;

public class Server {
	
	private final File dir = new File("news29out");
	private File[] files;
	public static final int PORT = 8080;
	private ServerSocket serverSocket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ThreadIn ti;
	private HashMap<Integer,DealWithRequest> dwr_list;
	private ArrayList<Article> articles;
	private LinkedBlockingQueue<ThreadOut> workers_queue;
	private int sequence_number = 0;
	
	public Server() throws InterruptedException{
		loadArticles();
		init();
	}

	private void loadArticles() throws InterruptedException{
		dwr_list = new HashMap<Integer,DealWithRequest>();
		articles = new ArrayList<>();
		workers_queue = new LinkedBlockingQueue<>();
		files = dir.listFiles();
		for(File f : files){
			articles.add(new Article(f));
		}
	}
	
	
	private void init() {
		try {
			serverSocket = new ServerSocket(PORT);
			while(true){
				System.out.println("Awaiting connections...");
				Socket socket = serverSocket.accept();
				System.out.println("Connected!");
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
				ti= new ThreadIn(this, in, out);
				ti.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try{
				serverSocket.close();
			} catch (IOException e) {
			}
		}
	}

	
	public synchronized LinkedBlockingQueue<ThreadOut> getWorker_queue() {
		return this.workers_queue;
	}

	public synchronized void newRequestArrived(Request req) {
			DealWithRequest dwr = new DealWithRequest(this.articles, req.getRequestString(), req.getTout(), this, this.sequence_number);
			dwr_list.put(sequence_number,dwr);
			this.sequence_number++;
			dwr.start();
	}


	public synchronized void leadWithResult(Message m) {
		DealWithRequest dwr = dwr_list.get(m.getDwr_sequence());
		dwr.reciveResponse(m);
	}
	
	public ArrayList<Article> getArticleList(){
		return this.articles;
	}
	
	public HashMap<Integer, DealWithRequest> getDwr_list() {
		return dwr_list;
	}
}
