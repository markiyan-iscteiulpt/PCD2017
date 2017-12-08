package struct;

import java.io.Serializable;

import server.Dispatcher;

public class Request implements Serializable{
	
	private static final long serialVersionUID = -5941465274308594059L;
	private String request;
	private Dispatcher tout;
	
	
	
	public Request(String request, Dispatcher tout) {
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

	public Dispatcher getTout() {
		return tout;
	}

	public void setTout(Dispatcher tout) {
		this.tout = tout;
	}
	

}
