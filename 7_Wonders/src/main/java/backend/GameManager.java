package backend;

import java.util.ArrayList;

import backend.RotateHandler.Direction;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.Deck.Age;

/**
 * Controls the actions of the game and delegates those responsibilities to
 * different classes
 *
 */
public class GameManager {
	private GameBoard board;
	private RotateHandler rotateHandler;
	private TradeHandler tradeHandler;

	public GameManager(ArrayList<String> playerNames) {
		setUpGame(playerNames);
	}

	public void setUpGame(ArrayList<String> playerNames) {
		ArrayList<Player> players = SetUpHandler.setUpHandler.setUpAndReturnPlayers(playerNames);
		ArrayList<Card> cards = SetUpDeckHandler.setUpDeckHandler.createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		this.board = new GameBoard(players, deck);
		this.rotateHandler = new RotateHandler(this.board);
		this.tradeHandler = new TradeHandler(this.board);
	}

	public void trade(Player from, Player to, int valueToTrade) {
		tradeHandler.tradeFromTo(from, to, valueToTrade);
	}

	public void changeRotateDirectionAndResetPositions(Direction direction) {
		this.rotateHandler.changeRotateDirectionAndResetPositions(direction);
	}

	public void rotateClockwise() {
		this.rotateHandler.rotateClockwise();
	}

	public void rotateCounterClockwise() {
		this.rotateHandler.rotateCounterClockwise();
	}

	public int getNumPlayers() {
		return this.board.getNumPlayers();
	}

	public GameBoard getGameBoard() {
		return this.board;
	}

	public ArrayList<Player> getPlayers() {
		return this.board.getPlayers();
	}

	public Player getPlayer(int index) {
		return this.board.getPlayer(index);
	}

	public int getPlayerCoinTotal(int index) {
		return this.board.getPlayerCoinTotal(index);
	}

	public Player getCurrentPlayer() {
		return this.board.getCurrentPlayer();
	}

	public Player getNextPlayer() {
		return this.board.getNextPlayer();
	}

	public Player getPreviousPlayer() {
		return this.board.getPreviousPlayer();
	}
}
