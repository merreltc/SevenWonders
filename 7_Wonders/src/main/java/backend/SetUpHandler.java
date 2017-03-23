package backend;

public class SetUpHandler {
	private int playerNum;
	

	public void setPlayerNum(int num) {
		if(num == 2)
			throw new IllegalArgumentException();
		playerNum = num;
		
	}
	
	public int getPlayerNum() {
		return this.playerNum;
	}
}
