package dao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import model.Etudiant;
import model.GroupeDisc;
import model.Message;

public class Server1 {
	private static List<Etudiant> Le;
	private static List<GroupeDisc> Lg;
	private static List<Message> Lm;

	
	public static void main(String[] args) {
		Le=new ArrayList<Etudiant>();
		Lg=new ArrayList<GroupeDisc>();
		Lm=new ArrayList<Message>();


		
		
		
try {
	    
		int port=9000;
		System.out.println("server listen at 9000");
		while(true){
			
			DatagramSocket sc= new DatagramSocket(port);

			byte[] dataRcv=new byte[1024];
			byte[] dataSend=new byte[1024];
			DatagramPacket pkRcv=new DatagramPacket(dataRcv, dataRcv.length);
			try {
				sc.receive(pkRcv);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
	        String commande=new String(pkRcv.getData());
	        InetAddress Ip=pkRcv.getAddress();
	        int portClt=pkRcv.getPort();
	        String msgServeur="";
	        byte[] dataSnd=new byte[1024];
	        if (commande.startsWith("##")){      
	                int r=0;
	                String login=commande.substring(2);                     
	                     for(Etudiant e:Server1.Le){
	        	               if(((e.getLogin()).equals(login))){
	        	                  	 r=1;
	        	                  	 
	        	                  	
	        		
	        	                                       }
	        	             
	        		
	                                           }
	        
	        
	        
         	              if(r==1){
         	                  	msgServeur="login reussit";
	                        	dataSnd=msgServeur.getBytes();
	            	          	DatagramPacket pkSend=new DatagramPacket(dataSnd, dataSnd.length,Ip,portClt);
	            	        	sc.send(pkSend);
	        	
	                                }
         	              if(r==0) {
	        	                 Etudiant newE=new Etudiant(login,login,login,login,Ip,portClt);
	        	                 msgServeur="login failed on vous ajoute " + login + newE.toString();
             	                 dataSnd=msgServeur.getBytes();
       		     
	        	                  Server1.Le.add(newE);
	        	                  DatagramPacket pkSend=new DatagramPacket(dataSnd, dataSnd.length,Ip,portClt);
	       		                  sc.send(pkSend);
	        	
	        	
	                                   }
	        
	     }	
	         
	        else if (commande.equals("#LIST")) {
				String msg="la liste des (noms) des etudiants connectés est: ";
				for (Etudiant etd : Server1.Le) {
					msg=msg+etd.getNom()+" et ";
				}
				DatagramPacket packet2=new DatagramPacket(msg.getBytes(),msg.length(),Ip,portClt);
				sc.send(packet2);
			}
	        
	        
	        else if (commande.equals("#HISTO")) {
				Etudiant e=new Etudiant();
				for (Etudiant etudiant : Server1.Le) {
					if(etudiant.getPort()==pkRcv.getPort()) {
						e=etudiant;
					}
				}
				String msg="les message Envoyé par "+e.getNom() +" sont :\n";
				for (Message msg1 : Server1.Lm) {
					if (msg1.getSender().getPort()==pkRcv.getPort()) {
						msg=msg+msg1.getMsg()+"--> "+msg1.getReciever().getNom()+" \n";
					}
				}
				
				DatagramPacket packet=new DatagramPacket(msg.getBytes(),msg.length(),portClt);
				sc.send(packet);
			}
	        
	        else if (commande.equals("#GRPS")) {
				String msg="la liste des  groupes : \n";
				for (GroupeDisc gp : Server1.Lg) {
					msg=msg+gp.getTitre()+"\n";
				}
				
				DatagramPacket packet=new DatagramPacket(msg.getBytes(),msg.length(),Ip,portClt);
				sc.send(packet);
			}
	        
	        else if (commande.startsWith("#GRP#")) {
	        	String nomG=commande.substring(5);
				List<Etudiant> le=new ArrayList<Etudiant>();
				List<Message> lm=new ArrayList<Message>();
				Server1.Lg.add(new GroupeDisc(nomG,le,lm));
			}
	        else if (commande.startsWith("@#")) {
				Etudiant e=new Etudiant();
				for (Etudiant etudiant : Server1.Le) {
					if(etudiant.getPort()==pkRcv.getPort()) {
						e=etudiant;
					}
				}
				String [] msg1= commande.split("@#");
				for (Etudiant etd : Server1.Le) {
					if (etd.getLogin().equals(msg1[1])) {
						String msg=e.getNom()+":"+msg1[2]+"\n";
						DatagramPacket packet=new DatagramPacket(msg.getBytes(),msg.length(),etd.getAdresseIp(),etd.getPort());
						sc.send(packet);
						Server1.Lm.add(new Message(e,etd,msg));
					}
				}
			}
	        
	        
	        else if(commande.startsWith("#>")) {
				String nomGroupe= commande.substring(2) ;
				for (GroupeDisc gp : Server1.Lg) {
					if(gp.getTitre().equals(nomGroupe)) {
						List<Etudiant> l=gp.getL();
						for (Etudiant etudiant : Server1.Le) {
							if(etudiant.getPort()==pkRcv.getPort()) {
								l.add(etudiant);
							}
						}
						
						gp.setL(l);
					}
				}
			}
	        
	        
	        
	        else if(commande.startsWith("#ETDS#")) {
				String nomGroupe= commande.substring(6) ;
				String msgAEnvoyer ="etudiants dont le nom de groupe "+nomGroupe+" sont :\n\n " ;
				for (GroupeDisc gp : Server1.Lg) {
					if(gp.getTitre().equals(nomGroupe)) {
						for (Etudiant etudiant : gp.getL()) {
							msgAEnvoyer=msgAEnvoyer+etudiant.getNom()+" \n\n";
						}
				
					}
				}
				DatagramPacket packet=new DatagramPacket(msgAEnvoyer.getBytes(),msgAEnvoyer.length(),Ip,portClt);
				sc.send(packet);
				
			}
	        
	        
	        else {
	        
	        	String msg="Erreur commande ";
	        	dataSend=msg.getBytes();
				DatagramPacket pksend=new DatagramPacket(dataSend, dataSend.length,Ip,portClt);
				
					try {
						sc.send(pksend);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						System.out.println("mochkla fel socket");
					}
					
		        }
	        	
	        
				// TODO Auto-generated catch block
	        
	        sc.close();
		}
		
		
   } catch(IOException e1) {
	   e1.printStackTrace();
		System.out.println("mochkla men barra");
   }
   } }
