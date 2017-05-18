package gui.menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import constants.Constants;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Science;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.playerData.Player;
import dataStructures.playerData.Chip.ChipValue;
import utils.RenderImage;
import utils.Translate;

public class ResourceViewer extends Menu {

	private Player player;
	private RenderImage renderer = new RenderImage();
	private boolean shouldDraw = false;
	private ResourceBundle messages = Translate.getNewResourceBundle();

	@Override
	public void draw(Graphics graphics) {
		if (shouldDraw) {
			Font startingFont = graphics.getFont();
			Color startingColor = graphics.getColor();

			graphics.setFont(Constants.RESOURCE_VIEWER_FONT);

			drawGraphControl(graphics);

			graphics.setColor(startingColor);
			graphics.setFont(startingFont);
		}
	}

	private void drawGraphControl(Graphics graphics) {
		int gridRows = this.player.getStoragePile().size() + 1;
		drawGrid(gridRows, graphics);

		graphics.setColor(new Color(50, 50, 50, 150));
		draw1CoinBox(graphics);
		drawShieldBox(graphics);
		draw3CoinBox(graphics);
		drawConflictTokenBox(graphics);
		drawVictoryTokensBox(graphics);
	}

	private void drawShieldBox(Graphics graphics) {
		graphics.drawRect(Constants.RESOURCE_VIEWER_SHIELD_X, Constants.RESOURCE_VIEWER_SHIELD_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_SHIELD_X,
				Constants.RESOURCE_VIEWER_SHIELD_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("one shield");
		RenderImage.draw(graphics, image, Constants.RESOURCE_VIEWER_SHIELD_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_SHIELD_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT);
		graphics.drawString(player.getNumShields() + "",
				Constants.RESOURCE_VIEWER_SHIELD_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_SHIELD_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}

	private void draw1CoinBox(Graphics graphics) {
		graphics.drawRect(Constants.RESOURCE_VIEWER_ONE_COIN_X, Constants.RESOURCE_VIEWER_ONE_COIN_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_ONE_COIN_X,
				Constants.RESOURCE_VIEWER_ONE_COIN_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("coin");
		RenderImage.draw(graphics, image,
				Constants.RESOURCE_VIEWER_ONE_COIN_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_ONE_COIN_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT);
		graphics.drawString(player.getCoins().get(ChipValue.ONE) + "",
				Constants.RESOURCE_VIEWER_ONE_COIN_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_ONE_COIN_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}

	private void drawConflictTokenBox(Graphics graphics) {
		graphics.drawRect(Constants.RESOURCE_VIEWER_WAR_TOKEN_X, Constants.RESOURCE_VIEWER_WAR_TOKEN_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_WAR_TOKEN_X,
				Constants.RESOURCE_VIEWER_WAR_TOKEN_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("Conflict Token");
		RenderImage.draw(graphics, image,
				Constants.RESOURCE_VIEWER_WAR_TOKEN_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_WAR_TOKEN_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT);
		graphics.drawString(player.getConflictTotal() + "",
				Constants.RESOURCE_VIEWER_WAR_TOKEN_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_WAR_TOKEN_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}

	private void drawVictoryTokensBox(Graphics graphics) {
		graphics.drawRect(Constants.RESOURCE_VIEWER_VICTORY_POINTS_X, Constants.RESOURCE_VIEWER_VICTORY_POINTS_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_VICTORY_POINTS_X,
				Constants.RESOURCE_VIEWER_VICTORY_POINTS_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("Victory Token");
		RenderImage.draw(graphics, image,
				Constants.RESOURCE_VIEWER_VICTORY_POINTS_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_VICTORY_POINTS_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT);
		graphics.drawString(player.getNumVictoryPoints() + "",
				Constants.RESOURCE_VIEWER_VICTORY_POINTS_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_VICTORY_POINTS_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}

	private void draw3CoinBox(Graphics graphics) {
		graphics.drawRect(Constants.RESOURCE_VIEWER_THREE_COIN_X, Constants.RESOURCE_VIEWER_THREE_COIN_Y,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		graphics.drawRect(Constants.RESOURCE_VIEWER_THREE_COIN_X,
				Constants.RESOURCE_VIEWER_THREE_COIN_Y + Constants.RESOURCE_VIEWER_ROW_HEIGHT,
				Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		Image image = this.renderer.getImage("coin3");
		RenderImage.draw(graphics, image,
				Constants.RESOURCE_VIEWER_THREE_COIN_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				Constants.RESOURCE_VIEWER_THREE_COIN_Y + 5, Constants.RESOURCE_IMAGE_WIDTH,
				Constants.RESOURCE_IMAGE_HEIGHT);
		graphics.drawString(player.getCoins().get(ChipValue.THREE) + "",
				Constants.RESOURCE_VIEWER_THREE_COIN_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
				Constants.RESOURCE_VIEWER_THREE_COIN_Y + Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET + Constants.RESOURCE_VIEWER_ROW_HEIGHT);
	}

	private void drawGrid(int rows, Graphics graphics) {
		graphics.setColor(new Color(254, 254, 254, 150));
		int rowOffset = rows > 9 ? rows : 9;
		graphics.fillRect(Constants.RESOURCE_VIEWER_ROW_X, Constants.RESOURCE_VIEWER_ROW_BASE_Y,
				Constants.RESOURCE_VIEWER_ROW_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT * rowOffset);
		graphics.setColor(new Color(50, 50, 50, 150));
		((Graphics2D) graphics).setStroke(new BasicStroke(10));
		for (int i = 0; i < rows; i++) {
			if (i == 0) {
				drawTitleRow(Constants.RESOURCE_VIEWER_ROW_BASE_Y, graphics);
			} else {
				drawRow(i, Constants.RESOURCE_VIEWER_ROW_BASE_Y + i * Constants.RESOURCE_VIEWER_ROW_HEIGHT, graphics);
			}
		}
	}

	private void drawTitleRow(int y, Graphics graphics) {
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
			RenderImage.draw(graphics, image, x + Constants.RESOURCE_VIEWER_CELL_WIDTH / 2 - 10, y + 5,
					Constants.RESOURCE_IMAGE_WIDTH, Constants.RESOURCE_IMAGE_HEIGHT);
		}
	}

	private void drawRow(int row, int y, Graphics graphics) {
		graphics.drawRect(Constants.RESOURCE_VIEWER_ROW_X, y, Constants.RESOURCE_VIEWER_FIRST_CELL_WIDTH,
				Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		for (int i = 0; i < Constants.NUM_OF_COLUMNS; i++) {
			graphics.drawRect(
					(Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_FIRST_CELL_WIDTH)
							+ i * Constants.RESOURCE_VIEWER_CELL_WIDTH,
					y, Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		}
		this.populateResourceRow(row, y, graphics);
	}

	private void populateResourceRow(int row, int y, Graphics graphics) {
		ArrayList<Card> cards = player.getStoragePile();
		if (row > cards.size()) {
			return;
		}
		drawValuesForRow(row, y, graphics, cards);
	}

	private void drawValuesForRow(int row, int y, Graphics graphics, ArrayList<Card> cards) {
		y += Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET;
		Card card = cards.get(row - 1);
		int[] values = getRowValues(card);
		graphics.drawString(messages.getString(Translate.prepareNoSpaceString(card.getName())),
				Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3, y);
		for (int i = 0; i < values.length; i++) {
			graphics.drawString(values[i] + "",
					(Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_FIRST_CELL_WIDTH
							+ Constants.RESOURCE_VIEWER_CELL_WIDTH / 2) + i * Constants.RESOURCE_VIEWER_CELL_WIDTH, y);
		}
	}

	private int[] getRowValues(Card card) {
		EffectType cardEffectType = card.getEffectType();
		int[] values = zeroArray(Constants.NUM_OF_COLUMNS);
		switch (cardEffectType) {
		case ENTITY:
			values = populateEntityValues(card);
			break;

		case MULTIVALUE:
			values = populateMutliValueValues(card);
			break;
		}

		return values;
	}

	private int[] populateMutliValueValues(Card card) {
		int[] effectArray = zeroArray(Constants.NUM_OF_COLUMNS);
		return effectArray;
	}

	private int[] populateEntityValues(Card card) {
		EntityEffect cardEntityEffect = (EntityEffect) card.getEffect();
		EntityType cardType = cardEntityEffect.getEntityType();
		HashMap<Enum, Integer> effectList = cardEntityEffect.getEntities();
		int[] effectArray = zeroArray(Constants.NUM_OF_COLUMNS);
		switch (cardType) {
		case MANUFACTUREDGOOD:
			effectArray = parseManufactured(effectList, effectArray);
			break;
		case RESOURCE:
			effectArray = parseResource(effectList, effectArray);
			break;
		case SCIENCE:
			effectArray = parseScience(effectList, effectArray);
			break;
		}
		return effectArray;
	}

	private int[] parseScience(HashMap<Enum, Integer> effectList, int[] effectArray) {
		for (Enum entity : effectList.keySet()) {
			if (entity == Science.PROTRACTOR) {
				effectArray[9] = effectList.get(entity);
			} else if (entity == Science.TABLET) {
				effectArray[8] = effectList.get(entity);
			} else {
				effectArray[7] = effectList.get(entity);
			}
		}
		return effectArray;
	}

	private int[] parseResource(HashMap<Enum, Integer> effectList, int[] effectArray) {
		for (Enum entity : effectList.keySet()) {
			if (entity == RawResource.CLAY) {
				effectArray[0] = effectList.get(entity);
			} else if (entity == RawResource.LUMBER) {
				effectArray[1] = effectList.get(entity);
			} else if (entity == RawResource.ORE) {
				effectArray[2] = effectList.get(entity);
			} else {
				effectArray[3] = effectList.get(entity);
			}
		}
		return effectArray;
	}

	private int[] parseManufactured(HashMap<Enum, Integer> effectList, int[] effectArray) {
		for (Enum entity : effectList.keySet()) {
			if (entity == Good.GLASS) {
				effectArray[4] = effectList.get(entity);
			} else if (entity == Good.LOOM) {
				effectArray[5] = effectList.get(entity);
			} else {
				effectArray[6] = effectList.get(entity);
			}
		}
		return effectArray;
	}

	private int[] zeroArray(int length) {
		int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = 0;
		}
		return array;
	}

	@Override
	public void initialize() {
	}

	public void openMenu(Player player) {
		this.player = player;
		this.shouldDraw = true;
	}

	public void closeMenu() {
		this.shouldDraw = false;
	}

	public boolean isActive() {
		return this.shouldDraw;
	}
}
