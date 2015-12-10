package net.packet;
import net.Client;
import net.Server;

public class Packet20SendQuestion extends Packet{
	
	private String question;

	public Packet20SendQuestion(byte[] data) {
		super(20);
	    String[] dataArray = readData(data).split(":");
		this.question = dataArray[1];
	}
	
	public Packet20SendQuestion(String userName) {
		super(20);
		this.question = userName;
	}

	@Override
	public void writeData(Client client) {
		
	}

	@Override
	public void writeData(Server server) {
		server.sendDataToAllClients(getData());
	}
	
	@Override
	public byte[] getData() {
		return ("20" + ":" + question).getBytes();
	}
	

	public String getQuestion() {
		return question;
	}

}
