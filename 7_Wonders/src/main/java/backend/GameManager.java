package backend;

import java.util.ArrayList;

import backend.handlers.DeckHandler;
import backend.handlers.EffectHandler;
import backend.handlers.PlayerTurnHandler;
import backend.handlers.RotateHandler;
import backend.handlers.RotateHandler.Rotation;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.TradeHandler;
import backend.handlers.TurnHandler;
import constants.GeneralEnums.GameMode;
import dataStructures.GameBoard;
import dataStructures.Handlers;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.playerData.Player;

/**
 * Controls the actions of the game and delegates those responsibilities to
 * different classes
 *
 */
public class GameManager {
	private GameBoard board;
	private Handlers handlers;

	public enum CardinalDirection {
		EAST, WEST
	}

	public GameManager(ArrayList<String> names, GameMode mode) {
		this(names, setUpHandlers(mode));
	}

	public GameManager(ArrayList<String> names, Handlers handlers) {
		this.handlers = handlers;
		setUpGame(names);
	}
	
	private static Handlers setUpHandlers(GameMode mode) {
		Handlers handlers = new Handlers(mode);
		handlers.setSetUpDeckHandler(new SetUpDeckHandler());
		handlers.setTurnHandler(new TurnHandler(handlers));
		handlers.setPlayerTurnHandler(new PlayerTurnHandler());
		return handlers;
	}
	
	public void setUpGame(ArrayList<String> names) {
		this.board = createGameBoard(names);
		this.handlers.getPlayerTurnHandler().setGameBoard(this.board);
		this.handlers.setRotateHandler(new RotateHandler(this.board));
		this.handlers.setTradeHandler(new TradeHandler(this.board));
		this.handlers.getTurnHandler().setGameBoard(this.board);
		this.handlers.setEffectHandler(new EffectHandler(this.board));
	}

	public GameBoard createGameBoard(ArrayList<String> names) {
		ArrayList<Player> players = this.handlers.getSetUpPlayerHandler().setUpAndReturnPlayers(names);
		Deck deck = this.handlers.getSetUpDeckHandler().createDeck(Age.AGE1, names.size());
		return new GameBoard(players, deck);
	}

	public void dealInitialTurnCards() {
		DeckHandler.shuffleDeck(this.board.getDeck());
		this.handlers.getTurnHandler().dealInitialTurnCards(this.getPlayers(), this.board.getDeck());
	}

	public void trade(Player from, Player to, int valueToTrade) {
		this.handlers.getTradeHandler().tradeCoinsFromTo(from, to, valueToTrade);
	}

	public void tradeForEntity(Player from, Player to, Enum entity) {
		this.handlers.getTradeHandler().tradeForEntity(from, to, entity);
	}

	public void buildStructure(Card card) {
		this.handlers.getPlayerTurnHandler().buildStructure(getCurrentPlayer(), card);
	}

	public void changeRotateDirectionAndResetPositions(Rotation direction) {
		this.handlers.getRotateHandler().changeRotateDirectionAndResetPositions(direction);
		this.handlers.getTurnHandler().setDirection(direction);
	}

	public boolean makeChangeForValue1Coins(int numCoinsToGet) {
		return this.board.makeChangeForValue1Coins(getCurrentPlayer(), numCoinsToGet);
	}

	public void discardSelectedCard(Card card) {
		this.handlers.getPlayerTurnHandler().discardSelectedCard(getCurrentPlayer(), card);
	}

	public String endCurrentPlayerTurn() {
		return this.handlers.getTurnHandler().endCurrentPlayerTurn(this.handlers);
	}

	public Deck getDeck() {
		return this.board.getDeck();
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

	public Rotation getDirection() {
		return this.handlers.getTurnHandler().getDirection();
	}
}