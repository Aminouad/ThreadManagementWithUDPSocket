package dao;

import java.io.IOException;
import java.net.DatagramSocket;

public class App {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        
        
		DatagramSocket sc = new DatagramSocket(9090);
        ClientRecieve r=new ClientRecieve(sc);
        ClientSend s=new ClientSend(sc);
		s.start();
		r.start();
		
		
		
		
	

}}
