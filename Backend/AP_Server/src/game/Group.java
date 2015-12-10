package game;

import java.util.ArrayList;

public class Group {

	private String name;
	
	private ArrayList<String> colors = new ArrayList<String>();
	private ArrayList<Player> players = new ArrayList<Player>();

	Group(){
		colors.add("#ff0000");
		colors.add("#0000ff");
		colors.add("#00ff00");
		colors.add("#ffff00");
	}
	
	public boolean tryAddNewPlayer(Player player){
		if(players.contains(player)){
			return true;
		}else if(players.size() >= 4){
			return false;
		}else{
			players.add(player);
			return true;
		}
	}
	
	public Group(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	
	
	
}
