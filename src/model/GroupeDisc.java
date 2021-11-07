package model;

import java.util.List;

public class GroupeDisc {

	private String titre;
	private List<Etudiant> l;
	private List<Message>m;
	public GroupeDisc(String titre, List<Etudiant> l, List<Message> m) {
		super();
		this.titre = titre;
		this.l = l;
		this.m = m;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public List<Etudiant> getL() {
		return l;
	}
	public void setL(List<Etudiant> l) {
		this.l = l;
	}
	public List<Message> getM() {
		return m;
	}
	public void setM(List<Message> m) {
		this.m = m;
	}
	
}
