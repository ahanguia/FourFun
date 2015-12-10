package ixdcth.chalmers.edu.fourfun.net.packet;
import net.Client;
import net.Server;

public class Packet25EndDiscussion extends Packet{

	public Packet25EndDiscussion(byte[] data) {
		super(25);
	    String[] dataArray = readData(data).split(":");
	}
	
	public Packet25EndDiscussion() {
		super(25);
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
		return ("25").getBytes();
	}

}
