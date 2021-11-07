package model;

import java.net.InetAddress;

public class Etudiant {
    private   String nom;
    private  String login;
    private  String niveau;
    private  String etatCnx;
    private  InetAddress adresseIp;
    private  int port;
	public Etudiant(String nom, String login, String niveau, String etatCnx, InetAddress adresseIp, int port) {
		
		this.nom = nom;
		this.login = login;
		this.niveau = niveau;
		this.etatCnx = etatCnx;
		this.adresseIp = adresseIp;
		this.port = port;
	}
	public Etudiant() {
		
	}
	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", login=" + login + ", niveau=" + niveau + ", etatCnx=" + etatCnx
				+ ", adresseIp=" + adresseIp + ", port=" + port + "]";
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getEtatCnx() {
		return etatCnx;
	}
	public void setEtatCnx(String etatCnx) {
		this.etatCnx = etatCnx;
	}
	public InetAddress getAdresseIp() {
		return adresseIp;
	}
	public void setAdresseIp(InetAddress adresseIp) {
		this.adresseIp = adresseIp;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	
    
}
