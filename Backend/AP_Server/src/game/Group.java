package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import net.Server;
import net.packet.Packet19GameBegin;
import net.packet.Packet21QuestionReceived;
import net.packet.Packet23AnswerReceived;
import net.packet.Packet24StartDiscussion;

public class Group {

	private final int roomSize = 4;
	
	private String name;
	
	private ArrayList<String> colors = new ArrayList<String>();
	private LinkedList<Player> players = new LinkedList<Player>();
	
	//private int timer, timeLeft;
	private Timer timer;
	
	private boolean isFull = false;
	
	private boolean gameBegun = false;
	private boolean questionPhase = false;
	private boolean answerPhase = false;
	private boolean discussionPhase = false;
	
	private Server server;
	
	private String question;
	private ArrayList<String> answers = new ArrayList<String>();
	
	Group(String name, Server server){
		this.server = server;
		this.name = name;
		
		colors.add("#ff0000");
		colors.add("#0000ff");
		colors.add("#00ff00");
		colors.add("#ffff00");
	}
	
	public void tick(float dt){
		//System.out.println(name + " ticking...");
		
		if(isFull){	
			if(discussionPhase){
				this.timer = new Timer();
			    TimerTask action = new TimerTask() {
			        public void run() {
			        	startDiscussion(); //as you said in the comments: abc is a static method
			        }
			    };
			    this.timer.schedule(action, 5000);
			    gameBegun = false;
			    discussionPhase = false;
			}else if(answerPhase){
				
			}else if(questionPhase){
				
			}else if(gameBegun){
				this.timer = new Timer();
			    TimerTask action = new TimerTask() {
			        public void run() {
			        	startNewSession(); //as you said in the comments: abc is a static method
			        }
			    };
			    this.timer.schedule(action, 5000);
			    gameBegun = false;
			}
			
			/*if(timer % 10 == 0){
				timeLeft--;
				timer = 0;
			}*/
		}		
	}
	
	public boolean tryAddNewPlayer(Player player){
		if(players.contains(player)){
			System.out.println("Player is member of " + name + ", snding confirmation...");
			return true;
		}else if(players.size() >= roomSize){
			System.out.println("Room " + name + " is full...");
			return false;
		}else{
			players.add(player);
			System.out.println("Adding new player to " + name + "! New Size: " + players.size());
			if(players.size() >= roomSize){
				System.out.println("Room " + name + " is FULL!");
				isFull = true;
				gameBegun = true;
			}
			return true;
		}
	}
	
	public String getName() {
		return name;
	}

	public LinkedList<Player> getPlayers() {
		return players;
	}
	
	public String popColor(Player player){
		if(player.hexColor == null){
			String toReturn = colors.get(colors.size() - 1);
			colors.remove(colors.size() - 1);
			player.hexColor = toReturn;
			return toReturn;
		}else
			return player.hexColor;
	}
	
	private void startNewSession(){
		int count = 0;
		Packet19GameBegin pack;
		for(Player p : players){
			if(count == 0){
				pack = new Packet19GameBegin(true);
			}else{
				pack = new Packet19GameBegin(false);
			}
			count++;
			server.sendData(pack.getData(), p.ip, p.port);
		}
		
		questionPhase = true;
		
		//timer = 0;
		//timeLeft = 60;
	}
	
	public void questionPosted(Player player, String question){
		this.question = question;
		
		int count = 0;
		Packet21QuestionReceived pack;
		for(Player p : players){
			pack = new Packet21QuestionReceived(question);
			server.sendData(pack.getData(), p.ip, p.port);
		}
		
		answerPhase = true;
	}
	
	public void answerPosted(Player player, String answer){
		answers.add(answer);
		
		Packet23AnswerReceived pack = new Packet23AnswerReceived();
		server.sendData(pack.getData(), player.ip, player.port);
		
		System.out.println("Number of answers: " + answers.size());
		
		if(answers.size() >= roomSize - 1){
			discussionPhase = true;
		}
	}
	
	public void startDiscussion(){
		Packet24StartDiscussion pack2;
		for(Player p : players){
			pack2 = new Packet24StartDiscussion(question, answers);
			server.sendData(pack2.getData(), p.ip, p.port);
		}
	}
	
	public void endDiscussion(){
		players.addLast(players.pollFirst());
		answers.clear();
		
		question = "";
		
		questionPhase = false;
		answerPhase = false;
		discussionPhase = false;
		gameBegun = true;
	}
	
	
}
