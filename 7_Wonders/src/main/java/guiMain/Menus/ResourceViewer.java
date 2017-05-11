package guiMain.Menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

public class ResourceViewer extends Menu {

	private Player player;
	private boolean shouldDraw = false;

	@Override
	public void draw(Graphics graphics) {
		if (shouldDraw) {
			Font startingFont = graphics.getFont();
			Color startingColor = graphics.getColor();
			
			graphics.setFont(new Font("Calibri", Font.BOLD, 30));

			//int gridRows = player.getStoragePile().size();
			int gridRows = 10;
			drawGrid(gridRows, graphics);

			graphics.setColor(startingColor);
			graphics.setFont(startingFont);
		}
	}

	private void drawGrid(int rows, Graphics graphics) {
		int rowHeight = 40;
	    int rowX = 50;
	    int rowBaseY = 100;
		graphics.setColor(new Color(254,254,254,150));
		graphics.fillRect(rowX, rowBaseY, Constants.FrameWidth - 100, rowHeight * rows);
		graphics.setColor(new Color(50, 50, 50, 150));
		((Graphics2D) graphics).setStroke(new BasicStroke(10));
        for (int i = 0; i < rows; i++){
        	if (i == 0){
        		drawTitleRow(rowBaseY, graphics);
        	}else{
        		drawRow(i, rowBaseY + i * rowHeight, graphics);
        	}
        }
	}
	
	private void drawTitleRow(int y, Graphics graphics){
		int textYOffset = 30;
		int textXOffset = 80;
		int rowWidth = Constants.FrameWidth - 100;
		int firstCell = 250;
		int cellWidth = (rowWidth - firstCell) / 13;
		graphics.drawRect(50, y, firstCell, 40);
		graphics.drawString("Card", 50 + textXOffset, y + textYOffset);
		for (int i = 0; i < 13; i++){
			graphics.drawRect((50 + firstCell) + i*cellWidth, y, cellWidth, 40);
		}
	}
	
	private void drawRow(int row, int y, Graphics graphics){
		int rowWidth = Constants.FrameWidth - 100;
		int firstCell = 250;
		int cellWidth = (rowWidth - firstCell) / 13;
		graphics.drawRect(50, y, firstCell, 40);
		for (int i = 0; i < 13; i++){
			graphics.drawRect((50 + firstCell) + i*cellWidth, y, cellWidth, 40);
		}
		this.populateResourceRow(row, y, graphics);
	}

	private void populateResourceRow(int row, int y, Graphics graphics){
		ArrayList<Card> cards = player.getStoragePile();
		if (row > cards.size()){
			return;
		}
		y += 30;
		Card card = cards.get(row-1);
		int[] values = zeroArray(13);
		//int[] values = getRowValues(card);
		int rowWidth = Constants.FrameWidth - 100;
		int firstCell = 250;
		int cellWidth = (rowWidth - firstCell) / 13;
		graphics.drawString(card.getName(), 50 + cellWidth/3, y);
		for (int i = 0; i < values.length; i++){
			graphics.drawString(values[i] + "", (50 + firstCell + cellWidth/2) + i*cellWidth, y);
		}
		//TODO: get all of the effects of cards
	}
	
	private int[] getRowValues(Card card){
		EffectType cardEffectType = card.getEffectType();
		int[] values = zeroArray(13);
		switch (cardEffectType){
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
	
	private int[] populateMutliValueValues(Card card){
		int[] effectArray = zeroArray(13);
		return effectArray;
	}
	
	private int[] populateValueValues(Card card){
		ValueEffect cardEntityEffect = (ValueEffect) card.getEffect();
		HashMap<Enum, Integer> effectList = cardEntityEffect.getAffectingEntities();
		int[] effectArray = zeroArray(13);
		if (effectList.containsKey(ValueEffect.ValueType.COIN)){
			effectArray[7] = effectList.get(ValueEffect.ValueType.COIN);
		}
		if (effectList.containsKey(ValueEffect.ValueType.CONFLICTTOKEN)){
			effectArray[8] = effectList.get(ValueEffect.ValueType.CONFLICTTOKEN);
		}
		if (effectList.containsKey(ValueEffect.ValueType.VICTORYPOINT)){
			effectArray[9] = effectList.get(ValueEffect.ValueType.VICTORYPOINT);
		}
		return effectArray;
	}
	
	private int[] populateEntityValues(Card card){
		EntityEffect cardEntityEffect = (EntityEffect) card.getEffect();
		HashMap<Enum, Integer> effectList = cardEntityEffect.getEntities();
		int[] effectArray = zeroArray(13);
		if (effectList.containsKey(EntityEffect.EntityType.MANUFACTUREDGOOD)){
			effectArray[10] = effectList.get(EntityEffect.EntityType.MANUFACTUREDGOOD);
		}
		if (effectList.containsKey(EntityEffect.EntityType.RESOURCE)){
			effectArray[11] = effectList.get(EntityEffect.EntityType.RESOURCE);
		}
		if (effectList.containsKey(EntityEffect.EntityType.SCIENCE)){
			effectArray[12] = effectList.get(EntityEffect.EntityType.SCIENCE);
		}
		return effectArray;
	}
	
	private int[] zeroArray(int length){
		int[] array = new int[length];
		for (int i = 0; i < length; i++){
			array[i] = 0;
		}
		return array;
	}
	
	//Card.getCardEffectType() - enum to cast to
	//Card.getEffect() - effects of the card
	
	@Override
	public void initialize() {
	}
	
	public void openMenu(Player player){
		this.player = player;
		this.shouldDraw = true;
	}
	
	public void closeMenu(){
		this.shouldDraw = false;
	}
	
	public boolean isActive(){
		return this.shouldDraw;
	}

}
