package struct;

import java.io.Serializable;
import java.util.HashMap;

import server.ThreadOut;

public class Message implements Serializable{
	
	private static final long serialVersionUID = -5941465274308094059L;
	private Type type;
	private ThreadOut tout;
	private Request request;
	private HashMap<String, Data> response;
	private Article article;
	private String request_string;
	private int dwr_sequence;
	private int sequence_number;

	
	public Message(Type type , Request request) {
		this.type = type;
		this.request = request;
	}
	
	public Message(Type type) {
		this.type = type;
	}
	
	
	public Message(Type type, HashMap<String, Data> response, int sequence_number, int dwr_sequence){
		this.type = type;
		this.response = response;
		this.sequence_number = sequence_number;
		this.dwr_sequence = dwr_sequence;
	}
	
	public Message(Type type, HashMap<String, Data> response){
		this.type = type;
		this.response = response;
	}
	
	public Message(Type type, Article article, String s, int seq_number, int dwr_number){
		this.type = type;
		this.setArticle(article);
		this.request_string = s;
		this.dwr_sequence = dwr_number;
		this.sequence_number = seq_number;
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

	public ThreadOut getTout() {
		return tout;
	}

	public void setTout(ThreadOut tout) {
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
}