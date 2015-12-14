package ixdcth.chalmers.edu.fourfun.net.packet;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

public class Packet22SendAnswer extends Packet{
	
	private String answer;

	public Packet22SendAnswer(byte[] data) {
		super(22);
	    String[] dataArray = readData(data).split(":");
		this.answer = dataArray[1];
	}
	
	public Packet22SendAnswer(String answer) {
		super(22);
		this.answer = answer;
	}

	@Override
	public void writeData(Client client) {
		
	}

	@Override
	public void writeData(Server server) {

	}
	
	@Override
	public byte[] getData() {
		return ("22" + ":" + answer).getBytes();
	}
	

	public String getAnswer() {
		return answer;
	}

}
