package ixdcth.chalmers.edu.fourfun.net.packet;
import java.util.ArrayList;
import ixdcth.chalmers.edu.fourfun.net.Client;
import ixdcth.chalmers.edu.fourfun.net.Server;

public class Packet24StartDiscussion extends Packet{
	
	private String question;
	private ArrayList<String> answers = new ArrayList<String>();

	public Packet24StartDiscussion(byte[] data) {
		super(24);
	    String[] dataArray = readData(data).split(":");
		this.question = dataArray[1];
		for(int i = 2; i < dataArray.length; i++){
			answers.add(dataArray[i]);
		}
	}
	
	public Packet24StartDiscussion(String question, ArrayList<String> answers) {
		super(24);
		this.question = question;
		this.answers = answers;
	}

	@Override
	public void writeData(Client client) {
		
	}

	@Override
	public void writeData(Server server) {

	}
	
	@Override
	public byte[] getData() {
		String toSend = "24" + ":" + question;
		for(String s : answers){
			toSend += ":" + s;
		}
		return toSend.getBytes();
	}
	

	public String getQuestion() {
		return question;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}
}
