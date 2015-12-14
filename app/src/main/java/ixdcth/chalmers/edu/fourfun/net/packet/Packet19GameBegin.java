package ixdcth.chalmers.edu.fourfun.net.packet;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

public class Packet19GameBegin extends Packet{

	private boolean questioneer;

    public Packet19GameBegin(byte[] data) {
        super(19);
        String[] dataArray = readData(data).split(":");
        this.questioneer = Boolean.parseBoolean(dataArray[1]);
    }

    public Packet19GameBegin(boolean questioneer) {
        super(19);
        this.questioneer = questioneer;
    }

    @Override
    public void writeData(Server server) {
        
    }

    @Override
    public byte[] getData() {
        return ("19" + ":" + this.questioneer).getBytes();
    }

    public boolean getIsQuestioneer() {
        return questioneer;
    }

	@Override
	public void writeData(Client client) {
		
	}


}
