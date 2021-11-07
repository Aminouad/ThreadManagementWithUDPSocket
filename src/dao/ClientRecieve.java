package dao;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class ClientRecieve extends Thread{
	DatagramSocket sc;

	public ClientRecieve(DatagramSocket sc) {
		super();
		this.sc = sc;
	}
	public void run(){
	while(true) {
		try {
			
			byte[] dataRcv=new byte[1024];
			DatagramPacket pkRcv=new DatagramPacket(dataRcv, dataRcv.length);
            
			sc.receive(pkRcv);
			String msgServer=new String(pkRcv.getData());
			System.out.println("msg du serveur "+msgServer);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}}

	

}
