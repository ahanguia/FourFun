package net;

public class ClientTest implements ClientInterface{

	public static void main(String[] args){
		ClientTest ct = null;
		Client c = new Client("Simon", "localhost", 12753, ct);
		c.start();
	}

	@Override
	public void loginAccept() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loginFailed(String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void roomCreated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void roomFailed(String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void roomJoined(String colorID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void joinFailed(String reason) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gameBegin(boolean isQuestioneer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void questionReceived(String question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void answerReceived(String nameOfAnswerer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDiscussion(String[] answers) {
		// TODO Auto-generated method stub
		
	}
	
}
