package net.packet;
import net.Client;
import net.Server;

public class Packet23AnswerReceived extends Packet{

	public Packet23AnswerReceived(byte[] data) {
		super(23);
	    String[] dataArray = readData(data).split(":");
	}
	
	public Packet23AnswerReceived() {
		super(23);
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
		return ("23").getBytes();
	}

}
