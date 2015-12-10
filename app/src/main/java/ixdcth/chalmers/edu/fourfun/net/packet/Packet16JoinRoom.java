package ixdcth.chalmers.edu.fourfun.net.packet;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

public class Packet16JoinRoom extends Packet{
	
	private String roomName;

	public Packet16JoinRoom(byte[] data) {
		super(16);
	    String[] dataArray = readData(data).split(":");
		this.roomName = dataArray[1];
	}
	
	public Packet16JoinRoom(String userName) {
		super(16);
		this.roomName = userName;
	}

	@Override
	public void writeData(Client client) {
		
	}

	@Override
	public void writeData(Server server) {
	}
	
	@Override
	public byte[] getData() {
		return ("16" + ":" + roomName).getBytes();
	}
	

	public String getRoomName() {
		return roomName;
	}

}
