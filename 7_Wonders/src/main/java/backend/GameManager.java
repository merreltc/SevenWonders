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
	private SetUpPlayerHandler setUpHandler;
	private SetUpDeckHandler setUpDeckHandler;
	private TurnHandler turnHandler;
	
	private RotateHandler rotateHandler;
	private TradeHandler tradeHandler;
	private PlayerTurnHandler playerTurnHandler;

	
	public GameManager(ArrayList<String> playerNames, ArrayList<Wonder.WonderType> wonders) {
		this(playerNames, wonders, new SetUpPlayerHandler(), new SetUpDeckHandler(), new TurnHandler(), new PlayerTurnHandler());
	}
	
	public GameManager(ArrayList<String> playerNames, ArrayList<Wonder.WonderType> wonders, SetUpPlayerHandler setUpHandler,
			SetUpDeckHandler setUpDeckHandler, TurnHandler turnHandler, PlayerTurnHandler playerTurnHandler){
		this.setUpHandler = setUpHandler;
		this.setUpDeckHandler = setUpDeckHandler;
		this.turnHandler = turnHandler;
		this.playerTurnHandler = playerTurnHandler;
		setUpGame(playerNames, wonders);	
	}

	public void setUpGame(ArrayList<String> playerNames, ArrayList<Wonder.WonderType> wonders) {
		ArrayList<Player> players = this.setUpHandler.setUpAndReturnPlayers(playerNames,wonders);
		Deck deck = this.setUpDeckHandler.createDeck(Age.AGE1, playerNames.size());

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
	
	public void tradeForEntity(Player from, Player to, Enum entity) {
		this.tradeHandler.tradeFromToForEntity(from, to, entity);
	}
	
	public void buildStructure(Card card) {
		this.playerTurnHandler.buildStructure(getCurrentPlayer(), card);
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
