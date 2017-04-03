package guiMain;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import backend.GameManager;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class Game {
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	private GameManager gameManager;
	private Message message;
	private TradeHandler tradeHandler;
	private int numOfPlayers;
	private int currentPlayer = 1;

	public Game(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
		this.gameManager = new GameManager(this.numOfPlayers);
		this.message = new Message();
		this.tradeHandler = new TradeHandler(this.gameManager, this.message);
	}

	public void draw(Graphics graphics) {
		for (Button button : buttons) {
			button.draw(graphics);
		}
		for (int i = 0; i < boards.size(); i++) {

			boards.get(i).draw(graphics);
		}
		this.message.drawMessageOnScreen(graphics);
	}

	public void initializeGame() {
		this.buttons.clear();
		createBoardsForEachPlayer();
		setUpMessageButton();
		setUpTradingButtons();
	}

	private void createBoardsForEachPlayer() {
		for (int i = -1; i < this.numOfPlayers - 1; i++) {
			PlayerBoard board = new PlayerBoard(i, numOfPlayers, this.gameManager.getPlayer(i + 1));
			boards.add(board);
		}
	}

	private void setUpMessageButton() {
		Button exitMessage = new Button(Constants.CloseMessageButtonPosition, Constants.CloseMessageButtonBounds,
				"Close");
		buttons.add(exitMessage);
	}

	private void setUpTradingButtons() {
		String[] values = new String[] { "Stone", "Wood", "Ore", "Clay" };
		for (int i = 0; i < 4; i++) {
			Point buttonPosition = new Point(Constants.TradeLeftBaseButtonPoint.x,
					Constants.TradeLeftBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
			Button LeftTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds, "Left-" + values[i],
					false);
			buttons.add(LeftTradeButton);
		}
		for (int i = 0; i < 4; i++) {
			Point buttonPosition = new Point(Constants.TradeRightBaseButtonPoint.x,
					Constants.TradeRightBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
			Button RightTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds, "Right-" + values[i],
					false);
			buttons.add(RightTradeButton);
		}
	}

	public void onButtonClickInGame(Button clicked) {
		if (clicked.getValue().equals("Close")) {
			this.message.clearMessage();
		} else {
			String[] splitValue = clicked.getValue().split("-");
			this.tradeHandler.trade(splitValue, this.currentPlayer);
		}
	}

	public void setMessage(String message) {
		this.message.setMessage(message);
	}

	public ArrayList<Button> getButtons() {
		return this.buttons;
	}
}
