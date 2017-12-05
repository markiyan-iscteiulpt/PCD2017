package server;

import java.util.ArrayList;
import java.util.HashMap;

import struct.Article;
import struct.Data;
import struct.Message;
import struct.Type;

public class DealWithRequest extends Thread {
	
	private ArrayList<Article> article_list;
	private String reqString;
	private ThreadOut tout;
	private Server server;
	private int seq_number;
	private int aux = 0;
	private int lim = 0;
	private int response_num = 0;
	private ThreadOut taken_thread;
	private HashMap<Integer, ThreadOut> taken_threads;
	private HashMap<String, Data> res_set;

	public DealWithRequest(ArrayList<Article> art_list, String requestString, ThreadOut tout, Server serv, int seq_numeber) {
		this.seq_number = seq_numeber;
		this.reqString = requestString;
		this.article_list = art_list;
		this.tout = tout;
		this.server = serv;
		this.taken_threads = new HashMap<>();
		this.lim = article_list.size();
		this.res_set = new HashMap<>();
	}
	
	@Override
	public void run() {
		while(aux != lim){
			try {
				this.taken_thread = server.getWorker_queue().take();
				this.taken_thread.sendMessage(new Message(Type.SERVER, this.article_list.get(aux), this.reqString, this.seq_number, aux));
				taken_threads.put(this.aux, this.taken_thread);
				aux++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void reciveResponse(Message m){
		response_num++;
		try {
			ThreadOut t;
			if((t = taken_threads.get(m.getSequenceNumber()))!=null){
				server.getWorker_queue().put(t);
			}else{
				System.out.println("FAILED " + m.getSequenceNumber());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
		
		if(response_num != lim){
			for(String d : m.getHashMap().keySet()){
				res_set.put(d, m.getHashMap().get(d));
			}
		}else{
			Message result_message = new Message(Type.CLIENT, res_set);
			this.tout.sendMessage(result_message);
			this.server.getDwr_list().remove(this.seq_number);
		}
	}

	public ArrayList<Article> getArticle_list() {
		return article_list;
	}

	public void setArticle_list(ArrayList<Article> article_list) {
		this.article_list = article_list;
	}

	public String getReqString() {
		return reqString;
	}

	public void setReqString(String reqString) {
		this.reqString = reqString;
	}

	public ThreadOut getTout() {
		return tout;
	}

	public void setTout(ThreadOut tout) {
		this.tout = tout;
	}

	public int getSeq_number() {
		return seq_number;
	}

	public void setSeq_number(int seq_number) {
		this.seq_number = seq_number;
	}
}
