package struct;

import java.io.Serializable;
import java.util.HashMap;

import server.Dispatcher;

public class Message implements Serializable{
	
	private static final long serialVersionUID = -5941465274308094059L;
	private Type type;
	private Dispatcher tout;
	private Request request;
	private HashMap<String, Data> response;
	private Article article;
	private String request_string;
	private int dwr_sequence;
	private int sequence_number;
	private int ID;

	
	public Message(Type type , Request request) {
		this.type = type;
		this.request = request;
	}
	
	public Message(Type type, int id) {
		this.type = type;
		this.setID(id);
	}
	
	
	public Message(Type type, HashMap<String, Data> response, int sequence_number, int dwr_sequence){
		this.type = type;
		this.response = response;
		this.ID = sequence_number;
		this.dwr_sequence = dwr_sequence;
	}
	
	public Message(Type type, HashMap<String, Data> response){
		this.type = type;
		this.response = response;
	}
	
	public Message(Type type, Article article, String s, int seq_number, int disp_id){
		this.type = type;
		this.setArticle(article);
		this.request_string = s;
		this.sequence_number = seq_number;
		this.ID = disp_id;
	}
	
	public int getDwr_sequence() {
		return dwr_sequence;
	}

	public void setDwr_sequence(int dwr_sequence) {
		this.dwr_sequence = dwr_sequence;
	}


	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public HashMap<String, Data> getHashMap() {
		return response;
	}

	public void setHaspMap(HashMap<String, Data> response) {
		this.response = response;
	}

	public Dispatcher getTout() {
		return tout;
	}

	public void setTout(Dispatcher tout) {
		this.tout = tout;
	}

	public String getRequest_string() {
		return request_string;
	}

	public void setRequest_string(String request_string) {
		this.request_string = request_string;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getSequenceNumber() {
		return sequence_number;
	}

	public void setSequenceNumber(int sequence_number) {
		this.sequence_number = sequence_number;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}