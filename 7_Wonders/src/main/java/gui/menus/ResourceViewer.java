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
import utils.ResourceViewerRowRenderer;
import utils.Translate;

public class ResourceViewer extends Menu {

	private Player player;
	private boolean shouldDraw = false;
	private Graphics graphics;
	private ResourceViewerRowRenderer resources = new ResourceViewerRowRenderer();

	@Override
	public void draw(Graphics graphics) {
		if (shouldDraw) {
			Font startingFont = graphics.getFont();
			Color startingColor = graphics.getColor();
			this.graphics = graphics;
			resources.setGraphics(graphics);
			graphics.setFont(Constants.RESOURCE_VIEWER_FONT);
			
			int gridRows = this.player.getStoragePile().size() + 1;
			drawGrid(gridRows);
			graphics.setColor(new Color(50, 50, 50, 150));
			resources.draw();
			graphics.setColor(startingColor);
			graphics.setFont(startingFont);
		}
	}

	private void drawGrid(int rows) {
		graphics.setColor(new Color(254, 254, 254, 150));
		int rowOffset = rows > 9 ? rows : 9;
		graphics.fillRect(Constants.RESOURCE_VIEWER_ROW_X, Constants.RESOURCE_VIEWER_ROW_BASE_Y,
				Constants.RESOURCE_VIEWER_ROW_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT * rowOffset);
		graphics.setColor(new Color(50, 50, 50, 150));
		((Graphics2D) graphics).setStroke(new BasicStroke(10));
		for (int i = 0; i < rows; i++) {
			if (i == 0) {
				resources.drawTitleRow(Constants.RESOURCE_VIEWER_ROW_BASE_Y);
			} else {
				drawRow(i, Constants.RESOURCE_VIEWER_ROW_BASE_Y + i * Constants.RESOURCE_VIEWER_ROW_HEIGHT);
			}
		}
	}

	private void drawRow(int row, int y) {
		graphics.drawRect(Constants.RESOURCE_VIEWER_ROW_X, y, Constants.RESOURCE_VIEWER_FIRST_CELL_WIDTH,
				Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		for (int i = 0; i < Constants.NUM_OF_COLUMNS; i++) {
			graphics.drawRect(
					(Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_FIRST_CELL_WIDTH)
							+ i * Constants.RESOURCE_VIEWER_CELL_WIDTH,
					y, Constants.RESOURCE_VIEWER_CELL_WIDTH, Constants.RESOURCE_VIEWER_ROW_HEIGHT);
		}
		this.populateResourceRow(row, y);
	}

	private void populateResourceRow(int row, int y) {
		ArrayList<Card> cards = player.getStoragePile();
		if (row > cards.size()) {
			return;
		}
		drawValuesForRow(new int[]{row, y}, cards);
	}

	private void drawValuesForRow(int[] rowStats, ArrayList<Card> cards) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		
		rowStats[1] += Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET;
		Card card = cards.get(rowStats[0] - 1);
		int[] values = getRowValues(card);
		graphics.drawString(messages.getString(Translate.prepareNoSpaceString(card.getName())),
				Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3, rowStats[1]);
		for (int i = 0; i < values.length; i++) {
			graphics.drawString(values[i] + "",
					(Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_FIRST_CELL_WIDTH
							+ Constants.RESOURCE_VIEWER_CELL_WIDTH / 2) + i * Constants.RESOURCE_VIEWER_CELL_WIDTH, rowStats[1]);
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
			values = zeroArray(Constants.NUM_OF_COLUMNS);
			break;
		}

		return values;
	}

	private int[] populateEntityValues(Card card) {
		EntityEffect cardEntityEffect = (EntityEffect) card.getEffect();
		EntityType cardType = cardEntityEffect.getEntityType();
		HashMap<Enum, Integer> effectList = cardEntityEffect.getEntities();
		int[] effectArray = populationHalper(cardType, effectList);
		return effectArray;
	}

	private int[] populationHalper(EntityType cardType, HashMap<Enum, Integer> effectList) {
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
		resources.setPlayer(player);
	}

	public void closeMenu() {
		this.shouldDraw = false;
	}

	public boolean isActive() {
		return this.shouldDraw;
	}
}
