package utils;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ResourceBundle;

import constants.Constants;
import dataStructures.playerData.Chip.ChipValue;
import dataStructures.playerData.Player;

public class ResourceViewerRowRenderer {
	
	private Graphics graphics;
	private RenderImage renderer = new RenderImage();
	Player player;
	private ResourceBundle messages = Translate.getNewResourceBundle();
	
	public void draw(Graphics graphics){
		this.graphics = graphics;
		draw1CoinBox();
		drawShieldBox();
		draw3CoinBox();
		drawConflictTokenBox();
		drawVictoryTokensBox();
	}
	
	private void drawShieldBox() {
		graphics.drawRect(Constants.RESOURCE_VIEWER_SHIELD_X, Constants.RESOURCE_VIEWER_SHIELD_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_SHIELD_X,
				Constants.RESOURCE_VIEWER_SHIELD_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("one shield");
		this.renderer.draw(graphics, new int[] {Constants.RESOURCE_VIEWER_SHIELD_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_SHIELD_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT});
		graphics.drawString(player.getNumShields() + "",
				Constants.RESOURCE_VIEWER_SHIELD_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_SHIELD_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}

	private void draw1CoinBox() {
		graphics.drawRect(Constants.RESOURCE_VIEWER_ONE_COIN_X, Constants.RESOURCE_VIEWER_ONE_COIN_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_ONE_COIN_X,
				Constants.RESOURCE_VIEWER_ONE_COIN_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("coin");
		renderer.draw(graphics,
				new int[] {Constants.RESOURCE_VIEWER_ONE_COIN_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_ONE_COIN_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT});
		graphics.drawString(player.getCoins().get(ChipValue.ONE) + "",
				Constants.RESOURCE_VIEWER_ONE_COIN_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_ONE_COIN_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}

	private void drawConflictTokenBox() {
		graphics.drawRect(Constants.RESOURCE_VIEWER_WAR_TOKEN_X, Constants.RESOURCE_VIEWER_WAR_TOKEN_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_WAR_TOKEN_X,
				Constants.RESOURCE_VIEWER_WAR_TOKEN_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("Conflict Token");
		renderer.draw(graphics,
				new int[] {Constants.RESOURCE_VIEWER_WAR_TOKEN_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_WAR_TOKEN_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT});
		graphics.drawString(player.getConflictTotal() + "",
				Constants.RESOURCE_VIEWER_WAR_TOKEN_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_WAR_TOKEN_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}

	private void drawVictoryTokensBox() {
		graphics.drawRect(Constants.RESOURCE_VIEWER_VICTORY_POINTS_X, Constants.RESOURCE_VIEWER_VICTORY_POINTS_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_VICTORY_POINTS_X,
				Constants.RESOURCE_VIEWER_VICTORY_POINTS_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("Victory Token");
		renderer.draw(graphics,
				new int[] {Constants.RESOURCE_VIEWER_VICTORY_POINTS_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_VICTORY_POINTS_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT});
		graphics.drawString(player.getNumVictoryPoints() + "",
				Constants.RESOURCE_VIEWER_VICTORY_POINTS_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_VICTORY_POINTS_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}

	private void draw3CoinBox() {
		graphics.drawRect(Constants.RESOURCE_VIEWER_THREE_COIN_X, Constants.RESOURCE_VIEWER_THREE_COIN_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_THREE_COIN_X,
				Constants.RESOURCE_VIEWER_THREE_COIN_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("coin3");
		renderer.draw(graphics,
				new int[] {Constants.RESOURCE_VIEWER_THREE_COIN_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_THREE_COIN_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT});
		graphics.drawString(player.getCoins().get(ChipValue.THREE) + "",
				Constants.RESOURCE_VIEWER_THREE_COIN_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_THREE_COIN_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}
	
	public void drawTitleRow(int y) {
		graphics.drawRect(Constants.RESOURCE_VIEWER_ROW_X, y, Constants.RESOURCE_VIEWER_FIRST_CELL_WIDTH,
				Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawString(messages.getString("card"),
				Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET);
		for (int i = 0; i < Constants.NUM_OF_COLUMNS; i++) {
			int x = (Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_FIRST_CELL_WIDTH)
					+ i * Constants.RESOURCE_VIEWER_CELL_WIDTH;
			graphics.drawRect(x, y, Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
			Image image = this.renderer.getImage(Constants.ResourceImages[i]);
			renderer.draw(graphics, new int[] {x + Constants.RESOURCE_VIEWER_CELL_WIDTH / 2 - 10, y + 5,
					Constants.RESOURCE_IMAGE_WIDTH, Constants.RESOURCE_IMAGE_HEIGHT});
		}
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
}
