package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import constants.Constants;
import dataStructures.playerData.Player;
import gui.interactables.Button;
import utils.RenderImage;

public class PlayerBoard {
	int lumber = 0;
	int ore = 0;
	int stone = 0;
	int clay = 0;
	int loom = 0;
	int glass = 0;
	int press = 0;

	private Point position;
	private Point sizePoint;

	/* zero index is the current player, next player is last */
	private int playerPosition;
	private int backPlayerStartingBoardPositionX;
	private int totalNumberOfPlayers;

	RenderImage renderer;
	Image WonderImage;

	private Player player;

	ResourceBundle messages = ResourceBundle.getBundle("message", Locale.getDefault());

	public PlayerBoard(int startingPosition, int totalNumberOfPlayers, Player player, RenderImage renderer) {
		this.renderer = renderer;
		backPlayerStartingBoardPositionX = Constants.FrameWidth / 2 - Constants.FrameWidthModifier
				- ((totalNumberOfPlayers - 1) * Constants.BackPlayerBoardWidth) / 2;
		this.playerPosition = startingPosition;
		this.player = player;
		this.totalNumberOfPlayers = totalNumberOfPlayers;
		this.WonderImage = renderer.getImage(player.getWonder().getName());
		this.setBoardPosition();
	}

	public Button generateResourceButton() {
		return makeResourceButton();
	}

	public void draw(Graphics graphics) {
		drawBoard(graphics);
		drawResources(graphics);
	}

	private void setBoardPosition() {
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
	}

	private Button makeResourceButton() {
		Point buttonPositionPoint = new Point(position.x + sizePoint.x / 2 - 20,
				position.y + Constants.PlayerNameYOffset - Constants.ResourcerViewerButtonYOffset);
		Button button = new Button(buttonPositionPoint, Constants.ResourceViewerButtonBounds,
				((playerPosition + this.totalNumberOfPlayers) % this.totalNumberOfPlayers) + "");
		button.hide();
		return button;
	}

	public void drawBoard(Graphics graphics) {
		RenderImage.draw(graphics, WonderImage, this.position.x, this.position.y, this.sizePoint.x, this.sizePoint.y);
		graphics.setFont(Constants.ResourceFont);
		graphics.setColor(Color.RED);
		graphics.drawString(this.player.getName(), position.x + sizePoint.x / 2 - 20,
				position.y + Constants.PlayerNameYOffset);
	}

	public void drawResources(Graphics graphics) {

		Object[] lumberArgs = { this.lumber };
		Object[] oreArgs = { this.ore };
		Object[] stoneArgs = { this.stone };
		Object[] clayArgs = { this.clay };
		MessageFormat format = new MessageFormat("");
		format.setLocale(Locale.getDefault());

		Image image = renderer.getImage("lumber");
		RenderImage.draw(graphics, image, position.x, position.y, Constants.RESOURCE_IMAGE_HEIGHT + 10,
				Constants.RESOURCE_IMAGE_WIDTH + 10);

		image = renderer.getImage("ore");
		RenderImage.draw(graphics, image, position.x, position.y + 45, Constants.RESOURCE_IMAGE_HEIGHT + 10,
				Constants.RESOURCE_IMAGE_WIDTH + 10);

		image = renderer.getImage("stone");
		RenderImage.draw(graphics, image, position.x, position.y + 85, Constants.RESOURCE_IMAGE_HEIGHT + 10,
				Constants.RESOURCE_IMAGE_WIDTH + 10);

		image = renderer.getImage("clay");
		RenderImage.draw(graphics, image, position.x, position.y + 125, Constants.RESOURCE_IMAGE_HEIGHT + 10,
				Constants.RESOURCE_IMAGE_WIDTH + 10);
	}

	public void changePlayer(Player player) {
		this.player = player;
		this.WonderImage = renderer.getImage(player.getWonder().getName());
	}
}
