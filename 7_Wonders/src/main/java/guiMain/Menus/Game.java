package guiMain.Menus;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import backend.GameManager;
import dataStructures.Player;
import dataStructures.Wonder;
import guiDataStructures.Constants;
import guiMain.GuiTradeHelper;
import guiMain.HandManager;
import guiMain.Message;
import guiMain.PlayerBoard;
import guiMain.RenderImage;
import guiMain.Interactables.Button;
import guiMain.Interactables.CardHolder;
import guiMain.Interactables.Interactable;

public class Game extends Menu {
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	private GameManager gameManager;
	private HandManager handManager;
	private RenderImage renderer;

	public Game(int numOfPlayers, RenderImage renderer) {
		setUpPlayers(numOfPlayers);
		this.renderer = renderer;
	}

	private void setUpPlayers(int numOfPlayers) {
		ArrayList<String> playerNames = new ArrayList<String>();
		ArrayList<Wonder.WonderType> wonders = new ArrayList<Wonder.WonderType>();

		HashMap<String, Wonder.WonderType> wonderMap = getWonderMap();

		for (int i = 0; i < numOfPlayers; i++) {
			String name = Message.inputPlayerNameMessage(i);
			String wonder = Message.dropDownWonderSelectionMessage(wonderMap.keySet().toArray());

			playerNames.add(name);
			wonders.add(wonderMap.get(wonder));
			wonderMap.remove(wonder);
			wonderMap.keySet().remove(wonder);
			/* Set wonder to correct values */
		}

		this.gameManager = new GameManager(playerNames, wonders);
		this.gameManager.dealInitialTurnCards();
	}

	private HashMap<String, Wonder.WonderType> getWonderMap() {
		HashMap<String, Wonder.WonderType> wonders = new HashMap<String, Wonder.WonderType>();
		wonders.put(Wonder.getNameByType(Wonder.WonderType.COLOSSUS), Wonder.WonderType.COLOSSUS);
		wonders.put(Wonder.getNameByType(Wonder.WonderType.TEMPLE), Wonder.WonderType.TEMPLE);
		wonders.put(Wonder.getNameByType(Wonder.WonderType.GARDENS), Wonder.WonderType.GARDENS);
		wonders.put(Wonder.getNameByType(Wonder.WonderType.PYRAMIDS), Wonder.WonderType.PYRAMIDS);
		wonders.put(Wonder.getNameByType(Wonder.WonderType.LIGHTHOUSE), Wonder.WonderType.LIGHTHOUSE);
		wonders.put(Wonder.getNameByType(Wonder.WonderType.MAUSOLEUM), Wonder.WonderType.MAUSOLEUM);
		wonders.put(Wonder.getNameByType(Wonder.WonderType.STATUE), Wonder.WonderType.STATUE);
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
					this.gameManager.getPlayer((2 * numOfPlayers - i) % numOfPlayers), renderer);
			boards.add(board);
		}
	}

	private void setUpCardSlots() {
		this.handManager = new HandManager();
		this.handManager.drawCurrentPlayerCards(this.gameManager.getCurrentPlayer(), renderer);
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
			String[] buttons = new String[] { "Build Structure", "Build Wonder", "Discard" };
			int val = JOptionPane.showOptionDialog(null, "Choose Play Type", "Play Card",
					JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[0]);
			try {
				if (val == 0) {
					((CardHolder) clicked).activate(this.gameManager);
				}
				rotateBoards();
				/* update the cards after rotation */
				for (Interactable toRemove : this.handManager.getCurrentPlayerHand()) {
					this.removeInteractable(toRemove);
				}
				this.handManager.drawCurrentPlayerCards(this.gameManager.getCurrentPlayer(), renderer);
				for (int i = 0; i < this.handManager.getPlayerHandSize(); i++) {
					this.addInteractable(this.handManager.getCardHolder(i));
				}
			} catch (Exception e) {
				Message.showMessage(e.getMessage());
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
			boards.get(i)
					.changePlayer(players.get((totalNumberOfPlayers + nextPlayerIndex - i) % totalNumberOfPlayers));
		}
	}
}
