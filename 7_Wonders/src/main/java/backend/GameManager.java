package backend;

public class GameManager {
	private int numPlayers = 3;
	
	public GameManager(int numPlayers){
		this.numPlayers = numPlayers;
	}
	
	public int getPlayerNum() {
		return this.numPlayers;
	}
}
