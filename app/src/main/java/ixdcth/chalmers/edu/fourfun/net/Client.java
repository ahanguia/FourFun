package ixdcth.chalmers.edu.fourfun.net;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import ixdcth.chalmers.edu.fourfun.ResourceState;
import ixdcth.chalmers.edu.fourfun.net.interfaces.CreateJoinInterface;
import ixdcth.chalmers.edu.fourfun.net.interfaces.WaitingInterface;
import ixdcth.chalmers.edu.fourfun.net.packet.*;

public class Client implements Runnable{
	
	private InetAddress ip;
	private DatagramSocket socket;
	private Thread thread;
	private String ipString;
	
	private int port;
	private int packetNumber = 0;
	
	private volatile boolean running = false;
	public boolean disconnected = false;
	
	public boolean connected = false;
	
	private HashMap<Byte, Integer> byteToIntMap = new HashMap<Byte, Integer>();
	
	public LinkedList<Integer> pixels = new LinkedList<Integer>();
	
	private Packet10Login loginPacket;
	private String userName;


	private static CreateJoinInterface createJoin;
	private static WaitingInterface waitingI;

	/** Instance unique non préinitialisée */
	private static Client INSTANCE = null;

	/** Point d'accès pour l'instance unique du singleton */

	public static Client getInstance()
	{
		if (INSTANCE == null)
		{ 	INSTANCE = new Client(ResourceState.playerName, ResourceState.ip, ResourceState.port);
		}
		return INSTANCE;
	}


	private Client(String userName, String ip, int port){
		this.userName = userName;
		this.port = port;
		this.ipString=ip;
	}
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this, "client-thread");
		thread.start();

	}
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			this.ip = InetAddress.getByName(ipString);
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		/*while(!connected){
			loginPacket = new Packet10Login(userName);
			sendData(loginPacket.getData());
			
			byte[] data = new byte[1024];
        	DatagramPacket packet = new DatagramPacket(data, data.length);
               
            try{
                socket.setSoTimeout(1000);
                //System.out.println("BEFORE RECEIVE, !conected");
                socket.receive(packet);
                //System.out.println("AFTER RECEIVE, !connected");
            }catch(SocketTimeoutException e){
            	continue;
            } catch (IOException e) {	
            	e.printStackTrace();
            }
            	parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
       }*/
	
        while(running) {
        	byte[] data = new byte[1024];
        	DatagramPacket packet = new DatagramPacket(data, data.length);
               
            try{
                socket.setSoTimeout(1000);
               // System.out.println("BEFORE RECEIVE");
                socket.receive(packet);
                //System.out.println("AFTER RECEIVE");
            }catch(SocketTimeoutException e){
            	continue;
            } catch (IOException e) {	
            	e.printStackTrace();
            }
            	parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
		socket.close();
	}

	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		
		Packet.PacketTypes type = Packet.lookupPacket(Integer.parseInt(message.substring(0, 2)));
		Packet packet = null;
		
		switch(type){
			case LOGINACCEPT:
				connected = true;
				System.out.println("ACCEPT PACKET RECEIVED");
				packet = new Packet11LoginAccept(data);
				handleLoginAccept(packet, address, port);
				break;
			case LOGINFAILED:
				System.out.println("FAILED LOGIN PACKET RECEIVED");
				packet = new Packet12LoginFailed(data);
				handleLoginFailed(packet, address, port);
				break;
			case ROOMCREATED:
				Log.i("CLIENT", "ROOM CREATED PACKET RECEIVED");
				packet = new Packet14RoomCreated(data);
				handleRoomCreated(packet, address, port);
				break;
			case ROOMFAILED:
				System.out.println("ROOM FAILED PACKET RECEIVED");
				packet = new Packet15RoomFailed(data);
				handleRoomFailed(packet, address, port);
				break;
			case ROOMJOINED:
				System.out.println("ROOM JOINED PACKET RECEIVED");
				packet = new Packet17RoomJoined(data);
				handleRoomJoined(packet, address, port);
				break;
			case JOINFAILED:
				System.out.println("JOIN FAILED PACKET RECEIVED");
				packet = new Packet18JoinFailed(data);
				handleJoinFailed(packet, address, port);
				break;
			case GAMEBEGIN:
				System.out.println("GAAAAAAME BEGIIIIIIIN ! Packet Received");
				packet = new Packet19GameBegin(data);
				handleGameBegin(packet, address, port);
				break;
			case QUESTIONRECEIVED:
				System.out.println("Question received ! Packet Received");
				packet = new Packet21QuestionReceived(data);
				handleQuestionReceived(packet, address, port);
				break;
			case ANSWERRECEIVED:
				System.out.println("Answer received ! Packet Received");
				packet = new Packet23AnswerReceived(data);
				handleAnswerReceived(packet, address, port);
				break;
			case STARTDISCUSSION:
				System.out.println("Start discussion ! Packet Received");
				packet = new Packet24StartDiscussion(data);
				handleStartDiscussion(packet, address, port);
				break;
			/*case CONNECT:
				System.out.println("CONNECT PACKET RECEIVED");
				packet = new Packet13Connect(data);
				handleConnect(packet, address, port);
				break;
			case DISCONNECT:
				packet = new Packet12Disconnect(data);
				handleDisconnect(packet, address, port);
				break;*/
			default:
				break;
		}
	}
	
	private void handleConnect(Packet packet, InetAddress address, int port){
		System.out.println("CONNECTED");
	}
	
	private void handleLoginAccept(Packet packet, InetAddress address, int port){
		System.out.println("ACCEPTED BY SERVER");
		connected = true;

		createJoin.loginAccept();
	}
	
	private void handleLoginFailed(Packet packet, InetAddress address, int port){
		System.out.println("FAILED LOGIN BY SERVER");
		
		Packet12LoginFailed p = (Packet12LoginFailed) packet;
		connected = true;
		createJoin.loginFailed(p.getReason());
	}
	
	private void handleRoomCreated(Packet packet, InetAddress address, int port){
		System.out.println("ROOM HAS BEEN CREATED BY SERVER");
		
		Packet14RoomCreated p = (Packet14RoomCreated) packet;

		createJoin.roomCreated(p.getColor());
	}
	
	private void handleRoomFailed(Packet packet, InetAddress address, int port){
		System.out.println("FAILED ROOM CREATION BY SERVER");
		
		Packet15RoomFailed p = (Packet15RoomFailed) packet;

		createJoin.roomFailed(p.getReason());
	}

	private void handleRoomJoined(Packet packet, InetAddress address, int port){
		Log.i("Clients", "ROOM HAS BEEN JOINED");

		Packet17RoomJoined p = (Packet17RoomJoined) packet;

		createJoin.roomJoined(p.getColor());
	}

	private void handleJoinFailed(Packet packet, InetAddress address, int port){
		System.out.println("FAILED ROOM CREATION BY SERVER");

		Packet18JoinFailed p = (Packet18JoinFailed) packet;

		createJoin.joinFailed(p.getReason());
	}
	private void handleGameBegin(Packet packet, InetAddress address, int port){
		System.out.println("GAME BEGIIIIIN");

		Packet19GameBegin p = (Packet19GameBegin) packet;

		waitingI.gameBegin(p.getIsQuestioneer());
	}
	private void handleQuestionReceived(Packet packet, InetAddress address, int port){
		System.out.println("Question Received");

		Packet21QuestionReceived p = (Packet21QuestionReceived) packet;

		waitingI.questionRecieved(p.getQuestion());
	}
	private void handleAnswerReceived(Packet packet, InetAddress address, int port){
		System.out.println("Answer Received");

		Packet23AnswerReceived p = (Packet23AnswerReceived) packet;

		waitingI.answerReceived(null);
	}
	private void handleStartDiscussion(Packet packet, InetAddress address, int port){
		System.out.println("Start Discussion !!! ");

		Packet24StartDiscussion p = (Packet24StartDiscussion) packet;

		waitingI.startDiscussion(p.getQuestion(), p.getAnswers());
	}

	
	public void sendData(byte[] data){
		if(!disconnected){
			DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
			try {
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getUserName() {
		return userName;
	}

	public static void setCreateJoinInterface(CreateJoinInterface cji){
		createJoin = cji;
	}
	public static void setWaitingInterface(WaitingInterface wI){
		waitingI = wI;
	}

}

