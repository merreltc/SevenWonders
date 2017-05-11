package guiMain.Menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import dataStructures.Card;
import dataStructures.Effect;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect;
import dataStructures.MultiValueEffect;
import dataStructures.Player;
import dataStructures.ValueEffect;
import guiDataStructures.Constants;
import guiMain.RenderImage;

public class ResourceViewer extends Menu {

	private Player player;
	private RenderImage renderer = new RenderImage();
	private boolean shouldDraw = false;

	@Override
	public void draw(Graphics graphics) {
		if (shouldDraw) {
			Font startingFont = graphics.getFont();
			Color startingColor = graphics.getColor();

			graphics.setFont(Constants.RESOURCE_VIEWER_FONT);

			int gridRows = this.player.getStoragePile().size() + 1;
			drawGrid(gridRows, graphics);

			graphics.setColor(startingColor);
			graphics.setFont(startingFont);
		}
	}

	private void drawGrid(int rows, Graphics graphics) {
		graphics.setColor(new Color(254, 254, 254, 150));
		graphics.fillRect(Constants.RESOURCE_VIEWER_ROW_X, Constants.RESOURCE_VIEWER_ROW_BASE_Y,
				Constants.FrameWidth - 100, Constants.RESOURCE_VIEWER_ROW_HEIGHT * rows);
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
		graphics.drawString("Card", Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_TEXT_X_OFFSET,
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
		y += Constants.RESOURCE_VIEWER_TEXT_Y_OFFSET;
		Card card = cards.get(row - 1);
		int[] values = zeroArray(Constants.NUM_OF_COLUMNS);
		// int[] values = getRowValues(card);
		graphics.drawString(card.getName(), Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_CELL_WIDTH / 3,
				y);
		for (int i = 0; i < values.length; i++) {
			graphics.drawString(values[i] + "",
					(Constants.RESOURCE_VIEWER_ROW_X + Constants.RESOURCE_VIEWER_FIRST_CELL_WIDTH
							+ Constants.RESOURCE_VIEWER_CELL_WIDTH / 2) + i * Constants.RESOURCE_VIEWER_CELL_WIDTH,
					y);
		}
		// TODO: get all of the effects of cards
	}

	private int[] getRowValues(Card card) {
		EffectType cardEffectType = card.getEffectType();
		int[] values = zeroArray(Constants.NUM_OF_COLUMNS);
		switch (cardEffectType) {
		case ENTITY:
			values = populateEntityValues(card);
			break;

		case VALUE:
			values = populateValueValues(card);
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

	private int[] populateValueValues(Card card) {
		ValueEffect cardEntityEffect = (ValueEffect) card.getEffect();
		HashMap<Enum, Integer> effectList = cardEntityEffect.getAffectingEntities();
		int[] effectArray = zeroArray(Constants.NUM_OF_COLUMNS);
		if (effectList.containsKey(ValueEffect.ValueType.COIN)) {
			effectArray[7] = effectList.get(ValueEffect.ValueType.COIN);
		}
		if (effectList.containsKey(ValueEffect.ValueType.CONFLICTTOKEN)) {
			effectArray[8] = effectList.get(ValueEffect.ValueType.CONFLICTTOKEN);
		}
		if (effectList.containsKey(ValueEffect.ValueType.VICTORYPOINT)) {
			effectArray[9] = effectList.get(ValueEffect.ValueType.VICTORYPOINT);
		}
		return effectArray;
	}

	private int[] populateEntityValues(Card card) {
		EntityEffect cardEntityEffect = (EntityEffect) card.getEffect();
		HashMap<Enum, Integer> effectList = cardEntityEffect.getEntities();
		int[] effectArray = zeroArray(Constants.NUM_OF_COLUMNS);
		if (effectList.containsKey(EntityEffect.EntityType.MANUFACTUREDGOOD)) {
			effectArray[10] = effectList.get(EntityEffect.EntityType.MANUFACTUREDGOOD);
		}
		if (effectList.containsKey(EntityEffect.EntityType.RESOURCE)) {
			effectArray[11] = effectList.get(EntityEffect.EntityType.RESOURCE);
		}
		if (effectList.containsKey(EntityEffect.EntityType.SCIENCE)) {
			effectArray[12] = effectList.get(EntityEffect.EntityType.SCIENCE);
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

	// Card.getCardEffectType() - enum to cast to
	// Card.getEffect() - effects of the card

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
