package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import backend.RotateHandler.Direction;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.Wonder;
import dataStructures.Deck.Age;

/**
 * Controls the actions of the game and delegates those responsibilities to
 * different classes
 *
 */
public class GameManager {
	private GameBoard board;
	private SetUpHandler setUpHandler;
	private SetUpDeckHandler setUpDeckHandler;
	private TurnHandler turnHandler;
	
	private RotateHandler rotateHandler;
	private TradeHandler tradeHandler;

	
	public GameManager(HashMap<String, Wonder.WonderType> playerNamesAndWonders) {
		this(playerNamesAndWonders, new SetUpHandler(), new SetUpDeckHandler(), new TurnHandler());
	}
	
	public GameManager(HashMap<String, Wonder.WonderType> playerNamesAndWonders, SetUpHandler setUpHandler,
			SetUpDeckHandler setUpDeckHandler, TurnHandler turnHandler){
		this.setUpHandler = setUpHandler;
		this.setUpDeckHandler = setUpDeckHandler;
		this.turnHandler = turnHandler;
		setUpGame(playerNamesAndWonders);	
	}

	public void setUpGame(HashMap<String, Wonder.WonderType> playerNamesAndWonders) {
		ArrayList<Player> players = this.setUpHandler.setUpAndReturnPlayers(playerNamesAndWonders);
		Deck deck = this.setUpDeckHandler.createDeck(Age.AGE1, playerNamesAndWonders.size());

		this.board = new GameBoard(players, deck);
		this.rotateHandler = new RotateHandler(this.board);
		this.tradeHandler = new TradeHandler(this.board);
	}
	
	public void dealInitialTurnCards() {
		this.turnHandler.dealInitialTurnCards(this.getPlayers(), this.getNumPlayers(), this.board.getDeck());
	}

	public void trade(Player from, Player to, int valueToTrade) {
		tradeHandler.tradeCoinsFromTo(from, to, valueToTrade);
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
