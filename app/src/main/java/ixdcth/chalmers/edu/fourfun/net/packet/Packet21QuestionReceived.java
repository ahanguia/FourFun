package ixdcth.chalmers.edu.fourfun.net.packet;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

public class Packet21QuestionReceived extends Packet{
	
	private String question;

	public Packet21QuestionReceived(byte[] data) {
		super(21);
	    String[] dataArray = readData(data).split(":");
		this.question = dataArray[1];
	}
	
	public Packet21QuestionReceived(String userName) {
		super(21);
		this.question = userName;
	}

	@Override
	public void writeData(Client client) {
		
	}

	@Override
	public void writeData(Server server) {

	}
	
	@Override
	public byte[] getData() {
		return ("21" + ":" + question).getBytes();
	}
	

	public String getQuestion() {
		return question;
	}

}
