package guiMain.Menus;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import backend.GameManager;
import dataStructures.Player;
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
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<Object> wonders = new ArrayList<>(Arrays.asList("Halikarnassos", "Gizah", "Byzantium", "Alexandria",
				"Petra", "Ephesos", "Olympia", "Rhodos", "Babylon"));
		for (int i = 0; i < numOfPlayers; i++) {
			String name = Message.inputPlayerNameMessage(i);
			players.add(name);
			String wonder = Message.dropDownWonderSelectionMessage(wonders.toArray());
			wonders.remove(wonder);
			/*Set wonder to correct values*/
		}
		this.gameManager = new GameManager(players);
	}

	@Override
	public void initialize() {
		this.clearButtons();
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
		this.handManager.rotatePlayers(this.gameManager.getPlayer(0));
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
			RotateBoards();
		} else if (clicked.getValue().equals("Exit")) {
			System.exit(0);
		} else {
			String[] splitValue = clicked.getValue().split("-");
			GuiTradeHelper tradeHandler = new GuiTradeHelper(this.gameManager);
			tradeHandler.trade(splitValue);
		}
	}

	public void RotateBoards() {
		/*
		 * TODO: Talk to them about their rotations. Possibly one rotate method.
		 * Also, fix the train wreck some how
		 */
		this.gameManager.rotateClockwise();

		ArrayList<Player> players = this.gameManager.getPlayers();
		System.out.println(players.toString());
		Player nextPlayer = this.gameManager.getNextPlayer();
		System.out.println(players.indexOf(nextPlayer));
		int totalNumberOfPlayers = this.gameManager.getNumPlayers();
		int nextPlayerIndex = players.indexOf(nextPlayer);

		for (int i = 0; i < players.size(); i++) {
			boards.get(i).changePlayer(players.get((nextPlayerIndex + i) % totalNumberOfPlayers));
		}
	}
}
