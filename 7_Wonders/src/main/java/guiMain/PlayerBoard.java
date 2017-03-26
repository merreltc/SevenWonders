package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class PlayerBoard {

	private static final int CurrentPlayerBoardWidth = 700;
	private static final int CurrentPlayerBoardHeight = 400;
	private static final int CurrentPlayerBoardPositionX = 950 - CurrentPlayerBoardWidth / 2;
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
	//private static final int BackPlayerStartingBoardPositionX = 1900 - BackPlayerBoardWidth - 20;
	private static final int BackPlayerBoardXOffset = BackPlayerBoardWidth + 40;
	private static final int BackPlayerBoardPositionY = 10;

	private Point position;
	private Point sizePoint;
	/* zero index is the current player, next player is last */
	private int playerPosition;
	private int totalNumberOfPlayers;
	private int backPlayerStartingBoardPositionX;

	// private Player player;

	/*
	 * TODO Pass in a player as well. This will be the player that controls the
	 * board
	 */
	public PlayerBoard(int startingPosition, int totalNumberOfPlayers) {
		backPlayerStartingBoardPositionX = 950 + ((totalNumberOfPlayers-3) * BackPlayerBoardWidth)/2;
		this.totalNumberOfPlayers = totalNumberOfPlayers;
		this.playerPosition = startingPosition;
	}

	public void draw(Graphics graphics) {
		switch (playerPosition) {
		case 0:
			sizePoint = new Point(CurrentPlayerBoardWidth, CurrentPlayerBoardHeight);
			this.position = new Point(CurrentPlayerBoardPositionX, CurrentPlayerBoardPositionY);
			break;
		case 1:
			sizePoint = new Point(LastPlayerBoardWidth, LastPlayerBoardHeight);
			this.position = new Point(LastPlayerBoardPositionX, LastPlayerBoardPositionY);
			break;
		case -1:
			sizePoint = new Point(NextPlayerBoardWidth, NextPlayerBoardHeight);
			this.position = new Point(NextPlayerBoardPositionX, NextPlayerBoardPositionY);
			break;
		default:
			sizePoint = new Point(BackPlayerBoardWidth, BackPlayerBoardHeight);
			this.position = new Point(
					backPlayerStartingBoardPositionX - BackPlayerBoardXOffset * (this.playerPosition - 1),
					BackPlayerBoardPositionY);
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
		/* Finally, the name of the wonder */
		graphics.drawString("Wonder", position.x + 100, position.y + 30);
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
