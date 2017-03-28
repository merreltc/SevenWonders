package backend;

public class GameManager {
	private int numPlayers = 3;
	
	public GameManager(int numPlayers){
		this.numPlayers = numPlayers;
		setUpGame();
	}
	
	public void setUpGame() {
		(new SetUpHandler()).setUp(3);
	}
	
	public int getPlayerNum() {
		return this.numPlayers;
	}
}
