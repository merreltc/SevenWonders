package gui.menus;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import backend.GameManager;
import constants.Constants;
import constants.GeneralEnums.GameMode;
import dataStructures.playerData.Player;
import gui.interactables.Button;
import gui.interactables.CardHolder;
import gui.interactables.Interactable;
import main.HandManager;
import main.PlayerBoard;
import main.TradeHelper;
import utils.Message;
import utils.RenderImage;
import utils.Translate;

public class GameDisplay extends Menu {
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	private GameManager gameManager;
	private HandManager handManager;
	private RenderImage renderer;
	private GameMode mode;
	ResourceBundle messages = Translate.getNewResourceBundle();
	ResourceViewer resource = new ResourceViewer();

	public GameDisplay(int numOfPlayers, RenderImage renderer, GameMode mode) {
		initializeGameManager(numOfPlayers);
		this.renderer = renderer;
		this.mode = mode;
	}

	private void initializeGameManager(int numOfPlayers) {
		ArrayList<String> playerNames = setUpPlayers(numOfPlayers);
		this.gameManager = new GameManager(playerNames, this.mode);
		this.gameManager.dealInitialTurnCards();
	}

	private ArrayList<String> setUpPlayers(int numOfPlayers) {
		ArrayList<String> players = new ArrayList<String>();

		for (int i = 0; i < numOfPlayers; i++) {
			String name = Message.inputPlayerNameMessage(i);
			players.add(name);
		}

		return players;
	}

	@Override
	public void initialize() {
		this.clearInteractables();
		createBoardsForEachPlayer();
		setUpExitButton();
		setUpTradingButtons();
		setUpCardSlots();
	}

	/**
	 * Draws current player's board last, so it's on top. Also draws all
	 * interactable buttons
	 */
	@Override
	public void draw(Graphics graphics) {
		for (int i = 0; i < boards.size(); i++) {
			boards.get((i + 2) % boards.size()).draw(graphics);
		}

		for (Interactable button : this.getInteractables()) {
			button.draw(graphics);
		}

		this.resource.draw(graphics);
	}

	private void createBoardsForEachPlayer() {
		int numOfPlayers = this.gameManager.getNumPlayers();
		for (int i = -1; i < numOfPlayers - 1; i++) {
			PlayerBoard board = new PlayerBoard(i, numOfPlayers,
					this.gameManager.getPlayer((2 * numOfPlayers + i) % numOfPlayers), renderer);
			this.addInteractable(board.generateResourceButton());
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
		Button exitButton = new Button(Constants.ExitButtonPosition, Constants.ExitButtonBounds,
				this.messages.getString("exit"));
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
			this.attemptPlayCard((CardHolder) clicked);
		} else if (clicked.getValue().equals(this.messages.getString("exit"))) {
			exit();
		} else if (clicked.getValue().codePointAt(0) >= 48 && clicked.getValue().codePointAt(0) <= 57) {
			displayResources(clicked);
		} else {
			trade(clicked);
		}
	}

	private void displayResources(Interactable clicked) {
		int playerNum = codePointToInt(clicked.getValue().codePointAt(0));
		int locOfCurrentPlayer = this.gameManager.getPlayers().indexOf(this.gameManager.getCurrentPlayer());
		this.resource.openMenu(
				this.gameManager.getPlayer((locOfCurrentPlayer + playerNum) % this.gameManager.getNumPlayers()));
	}

	private void trade(Interactable clicked) {
		String[] splitValue = clicked.getValue().split("-");
		TradeHelper tradeHandler = new TradeHelper(this.gameManager);
		tradeHandler.trade(splitValue);
	}

	private void exit() {
		if (this.resource.isActive()) {
			this.resource.closeMenu();
		} else {
			System.exit(0);
		}
	}

	private void attemptPlayCard(CardHolder clicked) {
		String[] buttons = new String[] { this.messages.getString("buildStructure"),
				this.messages.getString("buildWonder"), this.messages.getString("discard") };
		int val = JOptionPane.showOptionDialog(null, this.messages.getString("choosePlayType"),
				this.messages.getString("playCard"), JOptionPane.INFORMATION_MESSAGE, 0, null, buttons, buttons[0]);
		try {
			playOrDiscardCard(clicked, val);
			endPlayerTurn();
			updateDisplay();
		} catch (Exception e) {
			Message.showMessage(e.getMessage());
		}
	}

	private void playOrDiscardCard(CardHolder clicked, int val) {
		if (val == 0) {
			clicked.activate(this.gameManager);
		} else if (val == 2) {
			clicked.discard(this.gameManager);
		}
	}

	private void updateDisplay() {
		redrawBoards();
		/* update the cards after rotation */
		for (Interactable toRemove : this.handManager.getCurrentPlayerHand()) {
			this.removeInteractable(toRemove);
		}
		this.handManager.drawCurrentPlayerCards(this.gameManager.getCurrentPlayer(), renderer);
		for (int i = 0; i < this.handManager.getPlayerHandSize(); i++) {
			this.addInteractable(this.handManager.getCardHolder(i));
		}
	}

	private void endPlayerTurn() {
		String message = gameManager.endCurrentPlayerTurn();
		if (!message.equals("")) {
			Message.showMessage(message);
		}
	}

	private int codePointToInt(int val) {
		return val - 48;
	}

	public void redrawBoards() {
		ArrayList<Player> players = this.gameManager.getPlayers();
		Player nextPlayer = this.gameManager.getNextPlayer();
		int totalNumberOfPlayers = this.gameManager.getNumPlayers();
		int nextPlayerIndex = players.indexOf(nextPlayer);
		for (int i = 0; i < players.size(); i++) {
			boards.get(i)
					.changePlayer(players.get((totalNumberOfPlayers + nextPlayerIndex + i - 2) % totalNumberOfPlayers));
			// boards.get(i).changePlayer(
			// players.get((totalNumberOfPlayers + currentPlayerIndex - i + 1) %
			// totalNumberOfPlayers));
		}
	}
}