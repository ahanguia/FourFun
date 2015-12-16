package game;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import net.packet.*;

import net.Server;
import net.ServerInterface;

public class GameManager implements ServerInterface {

	private ArrayList<Group> groups = new ArrayList<Group>();
	private HashMap<InetAddress, Group> groupMap = new HashMap<InetAddress, Group>();
	private HashMap<InetAddress, Player> playerMap = new HashMap<InetAddress, Player>();

	private Server server;
	
	public GameManager(Server server){
		this.server = server;
	}
	
	public void tick(float dt){
		for(Group g : groups)
			g.tick(dt);
	}
	
	@Override
	public void loginRequest(Player player){
		if(playerMap.containsKey(player.ip)){
			server.sendData(new Packet12LoginFailed("Already logged in").getData(), player.ip, player.port);
		}else{
			playerMap.put(player.ip, player);
			server.sendData(new Packet11LoginAccept(player.userName).getData(), player.ip, player.port);
		}
	}

	@Override
	public void createRoom(Player player, String roomName) {
		if(playerMap.containsKey(player.ip)){
			player = playerMap.get(player.ip);
		}else{
			playerMap.put(player.ip, player);
		}
		
		boolean doesExist = false;
		for(Group g : groups){
			if(g.getName().toLowerCase().equals(roomName.toLowerCase())){
				doesExist = true;
			}
		}
		
		if(doesExist == true){
			server.sendData(new Packet15RoomFailed("room already exists").getData(), 
					player.ip, player.port);
		}else{
			Group newGroup = new Group(roomName, server);
			groups.add(newGroup);
			groupMap.put(player.ip, newGroup);
			
			System.out.println("Group " + roomName + " has been created by " + player.userName);
			
			Packet14RoomCreated p = new Packet14RoomCreated(newGroup.popColor(player));
			server.sendData(p.getData(), player.ip, player.port);
		}
	}

	@Override
	public void joinRoom(Player player, String roomName) {
		if(playerMap.containsKey(player.ip)){
			player = playerMap.get(player.ip);
		}else{
			playerMap.put(player.ip, player);
		}

		boolean doesExist = false;
		Group toJoin = null;
		for(Group g : groups){
			if(g.getName().toLowerCase().equals(roomName.toLowerCase())){
				doesExist = true;
				toJoin = g;
				break;
			}
		}
		
		if(doesExist == true){
			if(toJoin.tryAddNewPlayer(player)){
				server.sendData(new Packet17RoomJoined(toJoin.popColor(player)).getData(), 
						player.ip, player.port);
				groupMap.put(player.ip, toJoin);
			}else{
				server.sendData(new Packet18JoinFailed("Room full!").getData(), 
						player.ip, player.port);
			}
		}else{
			server.sendData(new Packet18JoinFailed("Room does not exist!").getData(), 
					player.ip, player.port);
		}
	}

	@Override
	public void questionSent(Player player, String question) {
		groupMap.get(player.ip).questionPosted(player, question);
	}

	@Override
	public void answerSent(Player player, String answer) {
		groupMap.get(player.ip).answerPosted(player, answer);
	}

	@Override
	public void discussionDone(Player player) {
		groupMap.get(player.ip).endDiscussion();
	}

	public Player getPlayer(InetAddress ip, int port){
		if(playerMap.containsKey(ip)){
			return playerMap.get(ip);
		}else{
			Player player = new Player("", ip, port);
			playerMap.put(player.ip, player);
			return player;
		}
	}
	
	public HashMap<InetAddress, Player> getPlayerMap() {
		return playerMap;
	}
	
	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	

}
