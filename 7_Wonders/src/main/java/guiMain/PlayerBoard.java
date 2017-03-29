package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class PlayerBoard {



	private Point position;
	private Point sizePoint;
	/* zero index is the current player, next player is last */
	private int playerPosition;
	private int totalNumberOfPlayers;
	private int backPlayerStartingBoardPositionX;

	// TODO: private Player player;

	/*
	 * TODO Pass in a player as well. This will be the player that controls the
	 * board
	 */
	public PlayerBoard(int startingPosition, int totalNumberOfPlayers) {
		backPlayerStartingBoardPositionX = 950 + ((totalNumberOfPlayers-3) * Constants.BackPlayerBoardWidth)/2;
		this.totalNumberOfPlayers = totalNumberOfPlayers;
		this.playerPosition = startingPosition;
	}

	public void draw(Graphics graphics) {
		switch (playerPosition) {
		case 0:
			sizePoint = new Point(Constants.CurrentPlayerBoardWidth, Constants.CurrentPlayerBoardHeight);
			this.position = new Point(Constants.CurrentPlayerBoardPositionX, Constants.CurrentPlayerBoardPositionY);
			break;
		case 1:
			sizePoint = new Point(Constants.LastPlayerBoardWidth, Constants.LastPlayerBoardHeight);
			this.position = new Point(Constants.LastPlayerBoardPositionX, Constants.LastPlayerBoardPositionY);
			break;
		case -1:
			sizePoint = new Point(Constants.NextPlayerBoardWidth, Constants.NextPlayerBoardHeight);
			this.position = new Point(Constants.NextPlayerBoardPositionX, Constants.NextPlayerBoardPositionY);
			break;
		default:
			sizePoint = new Point(Constants.BackPlayerBoardWidth, Constants.BackPlayerBoardHeight);
			this.position = new Point(
					backPlayerStartingBoardPositionX - Constants.BackPlayerBoardXOffset * (this.playerPosition - 1),
					Constants.BackPlayerBoardPositionY);
			break;
		}
		graphics.setColor(Color.GREEN);
		graphics.fillRect(position.x, position.y, sizePoint.x, sizePoint.y);
		graphics.setFont(new Font("Courier New", Font.BOLD, 30));
		graphics.setColor(Color.RED);
		
		/* First render money and war tokens */
		graphics.drawString("1", position.x + sizePoint.x - 30, position.y + 25);
		graphics.drawString("1", position.x + sizePoint.x - 30, position.y + 65);
		
		/* rendering resources owned */
		graphics.drawString("1", position.x + 10, position.y + 25);
		graphics.drawString("1", position.x + 10, position.y + 65);
		graphics.drawString("1", position.x + 10, position.y + 105);
		graphics.drawString("1", position.x + 10, position.y + 145);
	}

	public void rotatePlayers() {
		this.playerPosition++;
		if (this.playerPosition >= this.totalNumberOfPlayers) {
			this.playerPosition = 0;
		}
		if (this.playerPosition == this.totalNumberOfPlayers - 1) {
			this.playerPosition = -1;
		}
	}

}
