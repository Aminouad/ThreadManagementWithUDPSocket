package model;

public class Message {
private Etudiant Sender;
private Etudiant Reciever;
private String Msg;
public Etudiant getSender() {
	return Sender;
}
public void setSender(Etudiant sender) {
	Sender = sender;
}
public Etudiant getReciever() {
	return Reciever;
}
public void setReciever(Etudiant reciever) {
	Reciever = reciever;
}
public String getMsg() {
	return Msg;
}
public void setMsg(String msg) {
	Msg = msg;
}
public Message(Etudiant sender, Etudiant reciever, String msg) {
	super();
	Sender = sender;
	Reciever = reciever;
	Msg = msg;
}
public Message() {
	super();
}


}
