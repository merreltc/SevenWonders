package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import dataStructures.GeneralEnums.*;

import dataStructures.Card;
import dataStructures.Effect;
import dataStructures.EntityEffect;
import dataStructures.Player;
import guiDataStructures.Constants;

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
		this.WonderImage = renderer.getImage(player.getWonder().getName());
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
		RenderImage.draw(graphics, WonderImage, this.position.x, this.position.y, this.sizePoint.x, this.sizePoint.y);
		graphics.setFont(Constants.ResourceFont);
		graphics.setColor(Color.RED);
		graphics.drawString(this.player.getName(), position.x + sizePoint.x / 2 - 20,
				position.y + Constants.PlayerNameYOffset);
	}

	public void drawCoinAndWarTokens(Graphics graphics) {
		graphics.drawString(this.player.getCoinTotal() + "", position.x + sizePoint.x - 30, position.y + 25);
		graphics.drawString(this.player.getConflictTotal() + "", position.x + sizePoint.x - 30, position.y + 65);
	}

	public void drawResources(Graphics graphics) {
		
		Object[] lumberArgs = {this.lumber};
		Object[] oreArgs = {this.ore};
		Object[] stoneArgs = {this.stone};
		Object[] clayArgs = {this.clay};
		MessageFormat format = new MessageFormat("");
		format.setLocale(Locale.getDefault());
		
		format.applyPattern(messages.getString("resourceLumberTemplate"));
		graphics.drawString(format.format(lumberArgs), position.x + 10, position.y + 25);
		
		format.applyPattern(messages.getString("resourceOreTemplate"));
		graphics.drawString(format.format(oreArgs), position.x + 10, position.y + 65);
		
		format.applyPattern(messages.getString("resourceStoneTemplate"));
		graphics.drawString(format.format(stoneArgs), position.x + 10, position.y + 105);
		
		format.applyPattern(messages.getString("resourceClayTemplate"));
		graphics.drawString(format.format(clayArgs), position.x + 10, position.y + 145);
	}
	
	public void changePlayer(Player player){
		this.player = player;
		this.WonderImage = renderer.getImage(player.getWonder().getName());
		recalculateResources();
	}
	
	public void recalculateResources(){
		ArrayList<Card> storagePile = player.getStoragePile();
		lumber = 0;
		ore = 0;
		stone = 0;
		clay = 0;
		loom = 0;
		glass = 0;
		press = 0;
		for (int i = 0; i < storagePile.size();i++){
			Card card = storagePile.get(i);
			if (card.getEffectType() == Effect.EffectType.ENTITY) {
				EntityEffect effect = (EntityEffect) card.getEffect();
				sumEntities(effect);
			}
		}
	}

	private void sumEntities(EntityEffect effect) {
		for (Enum entity : effect.getEntities().keySet()){
			if (entity == Resource.LUMBER){
				lumber++;
			}else if (entity == Resource.CLAY){
				clay++;
			}else if (entity == Resource.ORE){
				ore++;
			}else if (entity == Resource.STONE){
				stone++;
			}else if (entity == Good.LOOM){
				loom++;
			}else if (entity == Good.GLASS){
				glass++;
			}else if (entity == Good.PRESS){
				press++;
			}
		}
	}
}
