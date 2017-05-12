package guiMain.Menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import dataStructures.Card;
import dataStructures.Player;
import guiDataStructures.Constants;

public class ResourceViewer extends Menu {

	private Player player;
	private boolean shouldDraw = false;
	
	//Card.getCardEffectType() - enum to cast to
	//Card.getEffect() - effects of the card

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
        	}
        	drawRow(rowBaseY + i * rowHeight, graphics);
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
	
	private void drawRow(int y, Graphics graphics){
		int rowWidth = Constants.FrameWidth - 100;
		int firstCell = 250;
		int cellWidth = (rowWidth - firstCell) / 13;
		graphics.drawRect(50, y, firstCell, 40);
		for (int i = 0; i < 13; i++){
			graphics.drawRect((50 + firstCell) + i*cellWidth, y, cellWidth, 40);
		}
	}

	private void populateResourceRow(int row){
		ArrayList<Card> cards = player.getStoragePile();
		if (row > cards.size()){
			return;
		}
		Card card = cards.get(row);
		card.getEffectType();
		//TODO: get all of the effects of cards
	}
	
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
