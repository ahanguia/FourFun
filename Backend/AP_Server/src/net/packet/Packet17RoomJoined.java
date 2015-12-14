package net.packet;
import net.Client;
import net.Server;

public class Packet17RoomJoined extends Packet{

	String hexColor;
	
    public Packet17RoomJoined(byte[] data) {
        super(17);
        String[] dataArray = readData(data).split(":");
        this.hexColor = dataArray[1];
    }

    public Packet17RoomJoined(String hexColor) {
        super(17);
        this.hexColor = hexColor;
    }

    @Override
    public void writeData(Server server) {
        
    }

    @Override
    public byte[] getData() {
        return ("17" + ":" + hexColor).getBytes();
    }

	@Override
	public void writeData(Client client) {
		
	}

	public String getColor(){
		return hexColor;
	}
}
