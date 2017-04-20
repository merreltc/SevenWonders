package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import dataStructures.Player;
import guiDataStructures.Constants;

public class PlayerBoard {
	private Point position;
	private Point sizePoint;
	/* zero index is the current player, next player is last */
	private int playerPosition;
	private int totalNumberOfPlayers;
	private int backPlayerStartingBoardPositionX;
	private boolean rotateClockwise = false;

	private Player player;

	/*
	 * TODO Pass in a player as well. This will be the player that controls the
	 * board
	 */
	public PlayerBoard(int startingPosition, int totalNumberOfPlayers, Player player) {
		backPlayerStartingBoardPositionX = Constants.FrameWidth / 2 - Constants.FrameWidthModifier
				- ((totalNumberOfPlayers - 1) * Constants.BackPlayerBoardWidth) / 2;
		this.totalNumberOfPlayers = totalNumberOfPlayers;
		this.playerPosition = startingPosition;
		this.player = player;
	}

	public void draw(Graphics graphics) {
		switch (playerPosition) {
		case 0:
			this.sizePoint = new Point(Constants.CurrentPlayerBoardWidth, Constants.CurrentPlayerBoardHeight);
			this.position = new Point(Constants.CurrentPlayerBoardPositionX, Constants.CurrentPlayerBoardPositionY);
			break;
		case -1:
			this.sizePoint = new Point(Constants.LastPlayerBoardWidth, Constants.LastPlayerBoardHeight);
			this.position = new Point(Constants.LastPlayerBoardPositionX, Constants.LastPlayerBoardPositionY);
			break;
		case 1:
			this.sizePoint = new Point(Constants.NextPlayerBoardWidth, Constants.NextPlayerBoardHeight);
			this.position = new Point(Constants.NextPlayerBoardPositionX, Constants.NextPlayerBoardPositionY);
			break;
		default:
			this.sizePoint = new Point(Constants.BackPlayerBoardWidth, Constants.BackPlayerBoardHeight);
			this.position = new Point(
					backPlayerStartingBoardPositionX + Constants.BackPlayerBoardXOffset * (this.playerPosition - 1),
					Constants.BackPlayerBoardPositionY);
			break;
		}

		drawBoard(graphics);
		drawCoinAndWarTokens(graphics);
		drawResources(graphics);
	}

	public void drawBoard(Graphics graphics) {
		graphics.setColor(Color.GREEN);
		graphics.fillRect(this.position.x, this.position.y, this.sizePoint.x, this.sizePoint.y);
		graphics.setFont(new Font("Courier New", Font.BOLD, 30));
		graphics.setColor(Color.RED);
		graphics.drawString(this.player.getName(), position.x + sizePoint.x / 2 - 20,
				position.y + Constants.PlayerNameYOffset);
	}

	public void drawCoinAndWarTokens(Graphics graphics) {
		graphics.drawString(this.player.getCoinTotal() + "", position.x + sizePoint.x - 30, position.y + 25);
		graphics.drawString("1", position.x + sizePoint.x - 30, position.y + 65);
	}

	public void drawResources(Graphics graphics) {
		graphics.drawString("1", position.x + 10, position.y + 25);
		graphics.drawString("1", position.x + 10, position.y + 65);
		graphics.drawString("1", position.x + 10, position.y + 105);
		graphics.drawString("1", position.x + 10, position.y + 145);
	}

	public void rotatePlayers() {
		if (this.rotateClockwise) {
			rotateClockwise();
		} else {
			rotateCounterclockwise();
		}
	}

	private void rotateClockwise() {
		this.playerPosition++;
		if (this.playerPosition >= this.totalNumberOfPlayers) {
			this.playerPosition = 0;
		}
		if (this.playerPosition == this.totalNumberOfPlayers - 1) {
			this.playerPosition = -1;
		}
	}

	private void rotateCounterclockwise() {
		this.playerPosition--;
		if (this.playerPosition < -1) {
			this.playerPosition = this.totalNumberOfPlayers - 2;
		}
	}

	public void changeDirection() {
		this.rotateClockwise = !this.rotateClockwise;
	}
}
