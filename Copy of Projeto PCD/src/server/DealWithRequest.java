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
	private Dispatcher tout;
	private Server server;
	private int seq_number;
	private int lim = 0;
	private int response_num = 0;
	private Dispatcher taken_thread;
	private int aux = 0;
	private HashMap<String, Data> res_set;

	public DealWithRequest(ArrayList<Article> art_list, String requestString, Dispatcher tout, Server serv, int seq_numeber) {
		this.seq_number = seq_numeber;
		this.reqString = requestString;
		this.article_list = art_list;
		this.tout = tout;
		this.server = serv;
		this.lim = article_list.size();
		this.res_set = new HashMap<>();
		this.aux = 0;
	}
	
	@Override
	public void run() {
		while(aux < lim){
			Dispatcher disp;
				while((disp = server.getWorkerQueue().getFreeDispather())!=null){
					this.taken_thread = disp;
					if(aux < this.article_list.size()){
					this.taken_thread.sendMessage(new Message(Type.SERVER, this.article_list.get(aux), this.reqString, this.seq_number, disp.getID()));
					System.out.println("[THREAD_NR: " + this.taken_thread.getID()+ " WORKING]");
					aux++;
					}
			}
		}
	}
	
	public synchronized void reciveResponse(Message m){
		response_num++;
		server.getWorkerQueue().setDispathcerFree(m.getID());
		if(response_num != lim){
			for(String d : m.getHashMap().keySet()){
				res_set.put(d, m.getHashMap().get(d));
			}
		}else{
			Message result_message = new Message(Type.CLIENT, res_set);
			this.tout.sendMessage(result_message);
			this.server.getDwr_list().remove(this.seq_number);
			this.server.getWorkerQueue().FreeAll();
			System.out.println("RESPONSE SET SIZE: " + res_set.size());
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

	public Dispatcher getTout() {
		return tout;
	}

	public void setTout(Dispatcher tout) {
		this.tout = tout;
	}

	public int getSeq_number() {
		return seq_number;
	}

	public void setSeq_number(int seq_number) {
		this.seq_number = seq_number;
	}
}
