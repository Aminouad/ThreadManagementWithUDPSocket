package dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientSend extends Thread{
	DatagramSocket sc;

	public ClientSend(DatagramSocket sc) {
		super();
		this.sc = sc;
	}
  public void run() {
	  
	  while(true) {
		String hostServeur="127.0.0.1";int portServer=9000;
		BufferedReader clav=new BufferedReader(new InputStreamReader(System.in));
		InetAddress ipServeur = null;
		try {
			ipServeur = InetAddress.getByName(hostServeur);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] dataSnd=new byte[1024];
		String login = null;
		try {
			System.out.println("donner votre msg");
			login = clav.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataSnd=login.getBytes();
		DatagramPacket pkSend=new DatagramPacket(dataSnd, dataSnd.length,ipServeur,portServer);
		try {
			sc.send(pkSend);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }}
}
