package backend;

public class SetUpHandler {
	private int playerNum;
	

	public void setPlayerNum(int num) {
		if(num == 2 || num == 8)
			throw new IllegalArgumentException();
		playerNum = num;
		
	}
	
	public int getPlayerNum() {
		return this.playerNum;
	}
}
