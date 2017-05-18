package backend;

import java.util.ArrayList;
import java.util.ResourceBundle;

import backend.handlers.DeckHandler;
import backend.handlers.PlayerTurnHandler;
import backend.handlers.RotateHandler;
import backend.handlers.RotateHandler.Rotation;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.SetUpPlayerHandler;
import backend.handlers.TradeHandler;
import backend.handlers.TurnHandler;
import constants.GeneralEnums.GameMode;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.playerData.Player;
import utils.Translate;

/**
 * Controls the actions of the game and delegates those responsibilities to
 * different classes
 *
 */
public class GameManager {
	private GameBoard board;

	private SetUpPlayerHandler setUpPlayerHandler;
	private SetUpDeckHandler setUpDeckHandler;
	private TurnHandler turnHandler;
	private RotateHandler rotateHandler;
	private TradeHandler tradeHandler;
	private PlayerTurnHandler playerTurnHandler;

	private ResourceBundle messages = Translate.getNewResourceBundle();
	private Rotation currentDirection = Rotation.CLOCKWISE;

	public enum CardinalDirection {
		EAST, WEST
	}

	public GameManager(ArrayList<String> names, GameMode mode) {
		this(names, new SetUpPlayerHandler(mode), new SetUpDeckHandler(), new TurnHandler(), new PlayerTurnHandler());
	}

	public GameManager(ArrayList<String> names, SetUpPlayerHandler setUpPlayerHandler,
			SetUpDeckHandler setUpDeckHandler, TurnHandler turnHandler, PlayerTurnHandler playerTurnHandler) {
		this.setUpPlayerHandler = setUpPlayerHandler;
		this.setUpDeckHandler = setUpDeckHandler;
		this.turnHandler = turnHandler;
		this.playerTurnHandler = playerTurnHandler;
		setUpGame(names);
	}

	public void setUpGame(ArrayList<String> names) {
		this.board = createGameBoard(names);
		this.rotateHandler = new RotateHandler(this.board);
		this.tradeHandler = new TradeHandler(this.board);
	}

	public GameBoard createGameBoard(ArrayList<String> names) {
		ArrayList<Player> players = this.setUpPlayerHandler.setUpAndReturnPlayers(names);
		Deck deck = this.setUpDeckHandler.createDeck(Age.AGE1, names.size());
		return new GameBoard(players, deck);
	}

	public void dealInitialTurnCards() {
		this.turnHandler.dealInitialTurnCards(this.getPlayers(), this.board.getDeck());
	}

	public void trade(Player from, Player to, int valueToTrade) {
		tradeHandler.tradeCoinsFromTo(from, to, valueToTrade);
	}

	public void tradeForEntity(Player from, Player to, Enum entity) {
		boolean discountSuccesful = false;
		if (from.storagePileContainsCardByName("East Trading Post")) {
			discountSuccesful = tryTradeWithDiscount(from, to, entity, CardinalDirection.EAST);
		} else if (from.storagePileContainsCardByName("West Trading Post")) {
			discountSuccesful = tryTradeWithDiscount(from, to, entity, CardinalDirection.WEST);
		} else if (from.storagePileContainsCardByName("Marketplace")) {
			discountSuccesful = true;
		}

		this.tradeHandler.tradeFromToForEntity(from, to, entity, discountSuccesful);
	}

	private boolean tryTradeWithDiscount(Player from, Player to, Enum entity, CardinalDirection direction) {
		int fromPosition = this.getPlayers().indexOf(from);
		int toPosition = this.getPlayers().indexOf(to);
		fromPosition = correctFromIndex(direction, fromPosition);

		return (fromPosition == toPosition) ? true : false;
	}

	private int correctFromIndex(CardinalDirection direction, int fromPosition) {
		int indexCorrection = getIndexCorrection(direction, fromPosition);
		int comparisonIndex = getComparisonIndex(direction);
		
		if (indexCorrection == comparisonIndex) {
			indexCorrection = getNewFromIndex(direction);
		}

		return indexCorrection;
	}

	public int getIndexCorrection(CardinalDirection direction, int index) {
		int correction = (direction == CardinalDirection.EAST) ? 1 : -1;
		return index + correction;
	}

	public int getComparisonIndex(CardinalDirection direction) {
		return (direction == CardinalDirection.EAST) ? this.getNumPlayers() : -1;
	}

	public int getNewFromIndex(CardinalDirection direction) {
		return (direction == CardinalDirection.EAST) ? 0 : this.getNumPlayers() - 1;
	}

	public void buildStructure(Card card) {
		this.playerTurnHandler.buildStructure(getCurrentPlayer(), card, this.board);
	}

	public void changeRotateDirectionAndResetPositions(Rotation direction) {
		this.rotateHandler.changeRotateDirectionAndResetPositions(direction);
		this.currentDirection = direction;
	}

	private void rotate(Rotation rotation) {
		if (rotation == Rotation.CLOCKWISE) {
			this.rotateClockwise();
		} else {
			this.rotateCounterClockwise();
		}
	}

	public void rotateClockwise() {
		this.rotateHandler.rotateClockwise();
	}

	public void rotateCounterClockwise() {
		this.rotateHandler.rotateCounterClockwise();
	}

	public boolean makeChangeForValue1Coins(int numCoinsToGet) {
		return this.board.makeChangeForValue1Coins(getCurrentPlayer(), numCoinsToGet);
	}

	public void discardSelectedCard(Card card) {
		this.playerTurnHandler.discardSelectedCard(getCurrentPlayer(), card, this.board);
	}

	public String endCurrentPlayerTurn() {
		String message = "";
		int playersUntilPass = this.turnHandler.getNumPlayersUntilPass();

		if (playersUntilPass == 0) {
			int turnsTilEnd = this.turnHandler.getNumTurnsTilEndOfAge();
			if (turnsTilEnd == 0) {
				message = switchAge();
			} else {
				message = rotateHand(turnsTilEnd);
			}

			playersUntilPass = this.getNumPlayers();
		}

		this.turnHandler.setNumPlayersUntilPass(playersUntilPass - 1);
		this.getCurrentPlayer().removeCurrentTrades();

		rotate(this.currentDirection);

		return message;
	}

	private String rotateHand(int turnsTilEnd) {
		String message;
		this.rotateHandler.rotateCurrentHands(getPlayers(), this.currentDirection);
		this.turnHandler.setNumTurnsTilEndOfAge(turnsTilEnd - 1);
		message = this.messages.getString("endOfCurrentRotation");
		return message;
	}

	private String switchAge() {
		String message;
		Deck newDeck;
		Age age = this.board.getAge();
		newDeck = switchDeck(age);

		this.board.setDeck(newDeck);
		this.turnHandler.dealInitialTurnCards(this.getPlayers(), this.board.getDeck());
		DeckHandler.shuffleDeck(this.board.getDeck());
		message = this.messages.getString("endOfAge");
		return message;
	}

	private Deck switchDeck(Age currentAge) {
		Deck newDeck;
		Age nextAge = getNextAge(currentAge);
		newDeck = this.setUpDeckHandler.createDeck(nextAge, getNumPlayers());

		this.currentDirection = getNextRotation(currentAge);
		this.turnHandler.endAge(this.getPlayers(), currentAge);
		return newDeck;
	}

	private Age getNextAge(Age currentAge) {
		return (currentAge == Age.AGE1) ? Age.AGE2 : Age.AGE3;
	}

	private Rotation getNextRotation(Age currentAge) {
		return (currentAge == Age.AGE1) ? Rotation.COUNTERCLOCKWISE : Rotation.CLOCKWISE;
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
		return this.currentDirection;
	}
}