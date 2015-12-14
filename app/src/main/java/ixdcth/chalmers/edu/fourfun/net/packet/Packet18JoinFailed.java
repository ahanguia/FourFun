package ixdcth.chalmers.edu.fourfun.net.packet;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

public class Packet18JoinFailed extends Packet{

	private String reason;

    public Packet18JoinFailed(byte[] data) {
        super(18);
        String[] dataArray = readData(data).split(":");
        this.reason = dataArray[1];
    }

    public Packet18JoinFailed(String username) {
        super(18);
        this.reason = username;
    }

    @Override
    public void writeData(Server server) {
        
    }

    @Override
    public byte[] getData() {
        return ("18" + ":" + this.reason).getBytes();
    }

    public String getReason() {
        return reason;
    }

	@Override
	public void writeData(Client client) {
		
	}


}
