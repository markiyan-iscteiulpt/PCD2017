package struct;

import java.io.Serializable;

import server.ThreadOut;

public class Request implements Serializable{
	
	private static final long serialVersionUID = -5941465274308594059L;
	private String request;
	private ThreadOut tout;
	
	
	
	public Request(String request, ThreadOut tout) {
		this.setRequest(request);
		this.setTout(tout);
	}
	
	
	public Request(String request) {
		this.setRequest(request);
	}
	
	
	
	
	
	public String getRequestString() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public ThreadOut getTout() {
		return tout;
	}

	public void setTout(ThreadOut tout) {
		this.tout = tout;
	}
	

}
