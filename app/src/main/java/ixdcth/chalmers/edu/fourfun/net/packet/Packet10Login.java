package ixdcth.chalmers.edu.fourfun.net.packet;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

import ixdcth.chalmers.edu.fourfun.net.packet.Packet;

public class Packet10Login extends Packet {
	
	private String userName;

	public Packet10Login(byte[] data) {
		super(10);
	    String[] dataArray = readData(data).split(":");
		this.userName = dataArray[1];
	}
	
	public Packet10Login(String userName) {
		super(10);
		this.userName = userName;
	}

	@Override
	public void writeData(Client client) {
		
	}

	@Override
	public void writeData(Server server) {
	
	}
	
	@Override
	public byte[] getData(){
		return ("10" + ":" + userName).getBytes();
	}
	

	public String getUserName() {
		return userName;
	}

}
