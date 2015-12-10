package ixdcth.chalmers.edu.fourfun.net.packet;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

public class Packet13CreateRoom extends Packet{
	
	private String roomName;

	public Packet13CreateRoom(byte[] data) {
		super(13);
	    String[] dataArray = readData(data).split(":");
		this.roomName = dataArray[1];
	}
	
	public Packet13CreateRoom(String userName) {
		super(13);
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
		return ("13" + ":" + roomName).getBytes();
	}
	

	public String getRoomName() {
		return roomName;
	}

}
