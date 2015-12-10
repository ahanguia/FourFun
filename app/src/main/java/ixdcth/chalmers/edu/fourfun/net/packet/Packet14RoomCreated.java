package ixdcth.chalmers.edu.fourfun.net.packet;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

public class Packet14RoomCreated extends Packet{

    public Packet14RoomCreated(byte[] data) {
        super(14);
        String[] dataArray = readData(data).split(":");
    }

    public Packet14RoomCreated(String username) {
        super(14);
    }

    @Override
    public void writeData(Server server) {
        
    }

    @Override
    public byte[] getData() {
        return ("14").getBytes();
    }

	@Override
	public void writeData(Client client) {
		
	}


}
