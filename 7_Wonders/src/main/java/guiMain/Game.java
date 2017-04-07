package guiMain;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import GuiDataStructures.Constants;
import backend.GameManager;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class Game extends Menu {
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	private GameManager gameManager;
	private Message message;
	private HandManager handManager;
	private int currentPlayer = 1;

	public Game(int numOfPlayers) {
		ArrayList<String> players = new ArrayList<String>();
		for(int i = 0; i < numOfPlayers; i++) {
			players.add("Player" + i);
		}
		this.gameManager = new GameManager(players);
		this.message = new Message();
	}

	@Override
	public void initialize() {
		this.clearButtons();
		createBoardsForEachPlayer();
		setUpExitButton();
		setUpTradingButtons();
		setUpCardSlots();
	}

	private void setUpCardSlots() {
		this.handManager = new HandManager();
		this.handManager.rotatePlayers(this.gameManager.getPlayer(0));
		for (int i = 0; i < this.handManager.getPlayerHandSize(); i++) {
			this.addInteractable(this.handManager.getCardHolder(i));
		}
	}

	@Override
	public void draw(Graphics graphics) {

		for (int i = 0; i < boards.size(); i++) {
			/* This will always draw the current players board last (on top) */
			boards.get((currentPlayer + i + 1) % this.gameManager.getNumPlayers()).draw(graphics);
		}
		for (Interactable button : this.getInteractables()) {
			button.draw(graphics);
		}
	}

	private void createBoardsForEachPlayer() {
		int numOfPlayers = this.gameManager.getNumPlayers();
		for (int i = -1; i < numOfPlayers-1; i++) {
			PlayerBoard board = new PlayerBoard(i, numOfPlayers, this.gameManager.getPlayer((numOfPlayers + i) % numOfPlayers));
			boards.add(board);
		}
	}

	private void setUpExitButton() {
		Button exitButton = new Button(Constants.ExitButtonPosition, Constants.ExitButtonBounds,
				"Exit");
		this.addInteractable(exitButton);
	}

	private void setUpTradingButtons() {
		String[] values = new String[] { "Stone", "Wood", "Ore", "Clay" };
		for (int i = 0; i < 4; i++) {
			Point buttonPosition = new Point(Constants.TradeLeftBaseButtonPoint.x,
					Constants.TradeLeftBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
			Button LeftTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds, "Left-" + values[i]);
			LeftTradeButton.hide();
			;
			this.addInteractable(LeftTradeButton);
		}
		for (int i = 0; i < 4; i++) {
			Point buttonPosition = new Point(Constants.TradeRightBaseButtonPoint.x,
					Constants.TradeRightBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
			Button RightTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds, "Right-" + values[i]);
			RightTradeButton.hide();
			this.addInteractable(RightTradeButton);
		}
	}

	@Override
	public void onClick(Interactable clicked) {
		if (clicked.getClass().equals(CardHolder.class)) {
			// TODO: (CardHolder) clicked.activate();
		} else if (clicked.getValue().equals("Exit")) {
			System.exit(0);
		} else {
			String[] splitValue = clicked.getValue().split("-");
			GuiTradeHelper tradeHandler = new GuiTradeHelper(this.gameManager, this.message);
			tradeHandler.trade(splitValue);
		}
	}
}
