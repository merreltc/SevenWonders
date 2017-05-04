package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import dataStructures.Player;
import guiDataStructures.Constants;

public class PlayerBoard {
	private Point position;
	private Point sizePoint;

	/* zero index is the current player, next player is last */
	private int playerPosition;
	private int backPlayerStartingBoardPositionX;
	
	Image WonderImage;
	
	private Player player;

	public PlayerBoard(int startingPosition, int totalNumberOfPlayers, Player player) {
		backPlayerStartingBoardPositionX = Constants.FrameWidth / 2 - Constants.FrameWidthModifier
				- ((totalNumberOfPlayers - 1) * Constants.BackPlayerBoardWidth) / 2;
		this.playerPosition = startingPosition;
		this.player = player;
		this.WonderImage = RenderImage.getImage(player.getWonder().getName());
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
		//graphics.setColor(Color.GREEN);
		//graphics.fillRect(this.position.x, this.position.y, this.sizePoint.x, this.sizePoint.y);
		RenderImage.draw(graphics, WonderImage, this.position.x, this.position.y, this.sizePoint.x, this.sizePoint.y);
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
	
	public void changePlayer(Player player){
		this.player = player;
		this.WonderImage = RenderImage.getImage(player.getWonder().getName());
	}
}
