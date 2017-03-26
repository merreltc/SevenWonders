package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class PlayerBoard {
	
	private static final int CurrentPlayerBoardWidth = 700;
	private static final int CurrentPlayerBoardHeight = 400;
	private static final int CurrentPlayerBoardPositionX = 950 - CurrentPlayerBoardWidth/2;
	private static final int CurrentPlayerBoardPositionY = 950 - CurrentPlayerBoardHeight - 10;
	
	private static final int LastPlayerBoardWidth = 500;
	private static final int LastPlayerBoardHeight = 300;
	private static final int LastPlayerBoardPositionX = 1900 - LastPlayerBoardWidth - 20;
	private static final int LastPlayerBoardPositionY = 800 - LastPlayerBoardHeight - 10;
	
	private static final int NextPlayerBoardWidth = 500;
	private static final int NextPlayerBoardHeight = 300;
	private static final int NextPlayerBoardPositionX = 20;
	private static final int NextPlayerBoardPositionY = 800 - NextPlayerBoardHeight - 10;
	
	private static final int BackPlayerBoardWidth = 300;
	private static final int BackPlayerBoardHeight = 150;
	private static final int BackPlayerStartingBoardPositionX = 1900 - BackPlayerBoardWidth - 20;
	private static final int BackPlayerBoardXOffset = BackPlayerBoardWidth + 40;
	private static final int BackPlayerBoardPositionY = 10;
	
	private Point position;
	private Point sizePoint;
	/*zero index is the current player, next player is last*/
	private int PlayerPosition;
	private int totalNumberOfPlayers;
	
	//private Player player;
	
	/*TODO Pass in a player as well.  This will be the player that controls the board*/
	public PlayerBoard(int startingPosition, int totalNumberOfPlayers){
		this.totalNumberOfPlayers = totalNumberOfPlayers;
		this.PlayerPosition = startingPosition;
	}
	
	public void draw(Graphics graphics){
		switch(PlayerPosition){
		case 0:
			sizePoint = new Point(this.CurrentPlayerBoardWidth, this.CurrentPlayerBoardHeight);
			this.position = new Point(this.CurrentPlayerBoardPositionX, this.CurrentPlayerBoardPositionY);
			break;
		case 1:
			sizePoint = new Point(this.LastPlayerBoardWidth, this.LastPlayerBoardHeight);
			this.position = new Point(this.LastPlayerBoardPositionX, this.LastPlayerBoardPositionY);
			break;
		case -1:
			sizePoint = new Point(this.NextPlayerBoardWidth, this.NextPlayerBoardHeight);
			this.position = new Point(this.NextPlayerBoardPositionX, this.NextPlayerBoardPositionY);
			break;
		default:
			sizePoint = new Point(this.BackPlayerBoardWidth, this.BackPlayerBoardHeight);
			this.position = new Point(this.BackPlayerStartingBoardPositionX - this.BackPlayerBoardXOffset * (this.PlayerPosition - 1)
					, this.BackPlayerBoardPositionY);
			break;
		}
		graphics.setColor(Color.GREEN);
		graphics.fillRect(position.x, position.y, sizePoint.x, sizePoint.y);
		graphics.setFont(new Font("Courier New", Font.BOLD, 30));
		graphics.setColor(Color.RED);
		/*First render money and war tokens*/
		graphics.drawString("1", position.x + sizePoint.x - 30, position.y + 25);
		graphics.drawString("1", position.x + sizePoint.x - 30, position.y + 65);
		/*rendering resources owned*/
		graphics.drawString("1", position.x + 10, position.y + 25);
		graphics.drawString("1", position.x + 10, position.y + 65);
		graphics.drawString("1", position.x + 10, position.y + 105);
		graphics.drawString("1", position.x + 10, position.y + 145);
		/*Finally, the name of the wonder*/
		graphics.drawString("Wonder", position.x + 100, position.y + 30);
	}
	
	public void RotatePlayers(){
		this.PlayerPosition++;
		if (this.PlayerPosition >= this.totalNumberOfPlayers){
			this.PlayerPosition = 0;
		}
		if (this.PlayerPosition == this.totalNumberOfPlayers - 1){
			this.PlayerPosition = -1;
		}
	}

}
