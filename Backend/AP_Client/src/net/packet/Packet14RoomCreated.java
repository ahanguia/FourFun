package net.packet;
import net.Client;
import net.Server;

public class Packet14RoomCreated extends Packet{

	String hexColor;
	
    public Packet14RoomCreated(byte[] data) {
        super(14);
        String[] dataArray = readData(data).split(":");
        this.hexColor = dataArray[1];
    }

    public Packet14RoomCreated(String hexColor) {
        super(14);
        this.hexColor = hexColor;
    }

    @Override
    public void writeData(Server server) {
        
    }

    @Override
    public byte[] getData() {
        return ("14" + ":" + hexColor).getBytes();
    }

	@Override
	public void writeData(Client client) {
		
	}


}
