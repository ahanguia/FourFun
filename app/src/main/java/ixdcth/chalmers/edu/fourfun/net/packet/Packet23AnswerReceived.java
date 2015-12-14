package ixdcth.chalmers.edu.fourfun.net.packet;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

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

	}
	
	@Override
	public byte[] getData() {
		return ("23").getBytes();
	}

}
