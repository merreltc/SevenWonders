package backend;

public class SetUpHandler {
	private int playerNum;
	

	public void setPlayerNum(int num) {
		if(num < 3 || num > 7)
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		playerNum = num;
		
	}
	
	public int getPlayerNum() {
		return this.playerNum;
	}
}
