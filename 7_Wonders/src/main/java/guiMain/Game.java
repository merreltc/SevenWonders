package guiMain;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import backend.GameManager;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class Game extends Menu {
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	private GameManager gameManager;
	private Message message;
	private int currentPlayer = 1;

	public Game(int numOfPlayers) {
		this.gameManager = new GameManager(numOfPlayers);
		this.message = new Message();
	}

	@Override
	public void draw(Graphics graphics) {
		for (Interactable button : this.getInteractables()) {
			button.draw(graphics);
		}
		for (int i = 0; i < boards.size(); i++) {

			boards.get(i).draw(graphics);
		}
		this.message.drawMessageOnScreen(graphics);
	}

	private void createBoardsForEachPlayer() {
		int numOfPlayers = this.gameManager.getNumPlayers();
		for (int i = -1; i < numOfPlayers - 1; i++) {
			PlayerBoard board = new PlayerBoard(i, numOfPlayers, this.gameManager.getPlayer(i + 1));
			boards.add(board);
		}
	}

	private void setUpMessageButton() {
		Button exitMessage = new Button(Constants.CloseMessageButtonPosition, Constants.CloseMessageButtonBounds,
				"Close");
		this.addInteractable(exitMessage);
	}

	private void setUpTradingButtons() {
		String[] values = new String[] { "Stone", "Wood", "Ore", "Clay" };
		for (int i = 0; i < 4; i++) {
			Point buttonPosition = new Point(Constants.TradeLeftBaseButtonPoint.x,
					Constants.TradeLeftBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
			Button LeftTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds, "Left-" + values[i]);
			LeftTradeButton.showValue(false);
			this.addInteractable(LeftTradeButton);
		}
		for (int i = 0; i < 4; i++) {
			Point buttonPosition = new Point(Constants.TradeRightBaseButtonPoint.x,
					Constants.TradeRightBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
			Button RightTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds, "Right-" + values[i]);
			RightTradeButton.showValue(false);
			this.addInteractable(RightTradeButton);
		}
	}

	@Override
	public void onClick(Interactable clicked) {
		if (clicked.getValue().equals("Close")) {
			this.message.clearMessage();
		} else {
			String[] splitValue = clicked.getValue().split("-");
			TradeHandler tradeHandler = new TradeHandler(this.gameManager, this.message);
			tradeHandler.trade(splitValue, this.currentPlayer);
		}
	}

	 public void setMessage(String message) {
	 this.message.setMessage(message);
	 }

	@Override
	public void initialize() {
		this.clearButtons();
		createBoardsForEachPlayer();
		setUpMessageButton();
		setUpTradingButtons();
	}
}
