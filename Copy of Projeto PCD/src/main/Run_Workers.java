package main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.Worker;

public class Run_Workers {
	
	
	public static void main(String[] args) throws UnknownHostException {
	InetAddress address = InetAddress.getByName(null);
	int PORT = 8080;
	ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
            Runnable worker = new Worker(address, PORT);
            executor.execute(worker);
          }
	}
}
