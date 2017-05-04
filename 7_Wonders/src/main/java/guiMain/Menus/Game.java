package guiMain.Menus;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

import backend.GameManager;
import dataStructures.Player;
import dataStructures.Wonder;
import exceptions.InsufficientFundsException;
import guiDataStructures.Constants;
import guiMain.GuiTradeHelper;
import guiMain.HandManager;
import guiMain.Message;
import guiMain.PlayerBoard;
import guiMain.Interactables.Button;
import guiMain.Interactables.CardHolder;
import guiMain.Interactables.Interactable;

public class Game extends Menu {
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	private GameManager gameManager;
	private HandManager handManager;

	public Game(int numOfPlayers) {
		setUpPlayers(numOfPlayers);
	}

	private void setUpPlayers(int numOfPlayers) {
		HashMap<String, Wonder.WonderType> playersAndWonders = new HashMap<String, Wonder.WonderType>();
		
		HashMap<String, Wonder.WonderType> wonders = getWonderMap();
		
		for (int i = 0; i < numOfPlayers; i++) {
			String name = Message.inputPlayerNameMessage(i);
			String wonder = Message.dropDownWonderSelectionMessage(wonders.keySet().toArray());
			
			playersAndWonders.put(name, wonders.get(wonder));
			wonders.remove(wonder);
			wonders.keySet().remove(wonder);
			/*Set wonder to correct values*/
		}
		
		this.gameManager = new GameManager(playersAndWonders);
		this.gameManager.dealInitialTurnCards();
	}

	private HashMap<String, Wonder.WonderType> getWonderMap() {
		HashMap<String, Wonder.WonderType> wonders = new HashMap<String, Wonder.WonderType>();
		wonders.put(Wonder.getWonderNameByType(Wonder.WonderType.COLOSSUS), Wonder.WonderType.COLOSSUS);
		wonders.put(Wonder.getWonderNameByType(Wonder.WonderType.TEMPLE), Wonder.WonderType.TEMPLE);
		wonders.put(Wonder.getWonderNameByType(Wonder.WonderType.GARDENS), Wonder.WonderType.GARDENS);
		wonders.put(Wonder.getWonderNameByType(Wonder.WonderType.PYRAMIDS), Wonder.WonderType.PYRAMIDS);
		wonders.put(Wonder.getWonderNameByType(Wonder.WonderType.LIGHTHOUSE), Wonder.WonderType.LIGHTHOUSE);
		wonders.put(Wonder.getWonderNameByType(Wonder.WonderType.MAUSOLEUM), Wonder.WonderType.MAUSOLEUM);
		wonders.put(Wonder.getWonderNameByType(Wonder.WonderType.STATUE), Wonder.WonderType.STATUE);
		return wonders;
	}

	@Override
	public void initialize() {
		this.clearInteractables();
		createBoardsForEachPlayer();
		setUpExitButton();
		setUpTradingButtons();
		setUpCardSlots();
	}

	@Override
	public void draw(Graphics graphics) {
		for (int i = 0; i < boards.size(); i++) {
			/*
			 * This will draw current Players board last, in order to be on top
			 */
			boards.get((i + 2) % boards.size()).draw(graphics);
		}

		for (Interactable button : this.getInteractables()) {
			button.draw(graphics);
		}
	}

	private void createBoardsForEachPlayer() {
		int numOfPlayers = this.gameManager.getNumPlayers();
		for (int i = -1; i < numOfPlayers - 1; i++) {
			PlayerBoard board = new PlayerBoard(i, numOfPlayers,
					this.gameManager.getPlayer((numOfPlayers + i) % numOfPlayers));
			boards.add(board);
		}
	}

	private void setUpCardSlots() {		
		this.handManager = new HandManager();
		this.handManager.drawCurrentPlayerCards(this.gameManager.getCurrentPlayer());
		for (int i = 0; i < this.handManager.getPlayerHandSize(); i++) {
			this.addInteractable(this.handManager.getCardHolder(i));
		}
	}

	private void setUpExitButton() {
		Button exitButton = new Button(Constants.ExitButtonPosition, Constants.ExitButtonBounds, "Exit");
		this.addInteractable(exitButton);
	}

	private void setUpTradingButtons() {
		for (int i = 0; i < 4; i++) {
			makeLeftButton(i);
			makeRightButton(i);
		}
	}

	private void makeLeftButton(int i) {
		Point buttonPosition = new Point(Constants.TradeLeftBaseButtonPoint.x,
				Constants.TradeLeftBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
		Button leftTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds,
				"Left-" + Constants.ResourceTypes[i]);
		leftTradeButton.hide();
		this.addInteractable(leftTradeButton);
	}

	private void makeRightButton(int i) {
		Point buttonPosition = new Point(Constants.TradeRightBaseButtonPoint.x,
				Constants.TradeRightBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
		Button rightTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds,
				"Right-" + Constants.ResourceTypes[i]);
		rightTradeButton.hide();
		this.addInteractable(rightTradeButton);
	}

	@Override
	public void onClick(Interactable clicked) {
		if (clicked.getClass().equals(CardHolder.class)) {
			((CardHolder) clicked).activate(this.gameManager);
			rotateBoards();
			/* update the cards after rotation */
			for(Interactable toRemove: this.handManager.getCurrentPlayerHand()){
				this.removeInteractable(toRemove);
			}
			this.handManager.drawCurrentPlayerCards(this.gameManager.getCurrentPlayer());
			for (int i = 0; i < this.handManager.getPlayerHandSize(); i++) {
				this.addInteractable(this.handManager.getCardHolder(i));
			}
		} else if (clicked.getValue().equals("Exit")) {
			System.exit(0);
		} else {
			String[] splitValue = clicked.getValue().split("-");
			GuiTradeHelper tradeHandler = new GuiTradeHelper(this.gameManager);
			tradeHandler.trade(splitValue);
		}
	}

	public void rotateBoards() {
		this.gameManager.rotateClockwise();
		ArrayList<Player> players = this.gameManager.getPlayers();
		Player nextPlayer = this.gameManager.getNextPlayer();
		int totalNumberOfPlayers = this.gameManager.getNumPlayers();
		int nextPlayerIndex = players.indexOf(nextPlayer);
		for (int i = 0; i < players.size(); i++) {
			boards.get(i).changePlayer(players.get((totalNumberOfPlayers + nextPlayerIndex - i) % totalNumberOfPlayers));
		}
	}
}
