package backend;

import java.util.ArrayList;
import java.util.ResourceBundle;

import backend.handlers.DeckHandler;
import backend.handlers.EndGameHandler;
import backend.handlers.PlayerTurnHandler;
import backend.handlers.RotateHandler;
import backend.handlers.RotateHandler.Rotation;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.SetUpPlayerHandler;
import backend.handlers.TradeHandler;
import backend.handlers.TurnHandler;
import constants.GeneralEnums.GameMode;
import dataStructures.GameBoard;
import dataStructures.Handlers;
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
	private Rotation currentDirection = Rotation.CLOCKWISE;
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
		handlers.setTurnHandler(new TurnHandler());
		handlers.setPlayerTurnHandler(new PlayerTurnHandler());
		return handlers;
	}
	
	public void setUpGame(ArrayList<String> names) {
		this.board = createGameBoard(names);
		this.handlers.getPlayerTurnHandler().setGameBoard(this.board);
		this.handlers.setRotateHandler(new RotateHandler(this.board));
		this.handlers.setTradeHandler(new TradeHandler(this.board));
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
		this.handlers.getRotateHandler().rotateClockwise();
	}

	public void rotateCounterClockwise() {
		this.handlers.getRotateHandler().rotateCounterClockwise();
	}

	public boolean makeChangeForValue1Coins(int numCoinsToGet) {
		return this.board.makeChangeForValue1Coins(getCurrentPlayer(), numCoinsToGet);
	}

	public void discardSelectedCard(Card card) {
		this.handlers.getPlayerTurnHandler().discardSelectedCard(getCurrentPlayer(), card);
	}

	public String endCurrentPlayerTurn() {
		String message = "";
		int playersUntilPass = this.handlers.getTurnHandler().getNumPlayersUntilPass();

		if (playersUntilPass == 0) {
			message = checkNeedToSwitchAges();
			playersUntilPass = this.getNumPlayers();
		}

		this.handlers.getTurnHandler().setNumPlayersUntilPass(playersUntilPass - 1);
		this.getCurrentPlayer().removeCurrentTrades();
		rotate(this.currentDirection);
		return message;
	}

	private String checkNeedToSwitchAges() {
		String message;
		int turnsTilEnd = this.handlers.getTurnHandler().getNumTurnsTilEndOfAge();
		if (turnsTilEnd == 0) {
			message = switchAge();
		} else  {
			message = rotateHand(turnsTilEnd);
		}
		return message;
	}

	private String rotateHand(int turnsTilEnd) {
		String message;
		this.handlers.getRotateHandler().rotateCurrentHands(getPlayers(), this.currentDirection);
		this.handlers.getTurnHandler().setNumTurnsTilEndOfAge(turnsTilEnd - 1);
		message = Translate.getNewResourceBundle().getString("endOfCurrentRotation");
		return message;
	}
	
	private String switchAge() {
		Age age = this.board.getAge();
		if (age == Age.AGE3){
			return endGame();
		}
		
		return endAge(age);
	}

	private String endGame() {
		this.handlers.getTurnHandler().endAge(this.getPlayers(), Age.AGE3);
		EndGameHandler end = new EndGameHandler();
		ArrayList<Integer> scores = end.calculateScores(this.getPlayers());
		return formatFinalScores(scores);
	}
	
	public String endAge(Age age) {
		String message;
		Deck newDeck;
		newDeck = switchDeck(age);
		this.board.setDeck(newDeck);
		DeckHandler.shuffleDeck(this.board.getDeck());
		this.handlers.getTurnHandler().dealInitialTurnCards(this.getPlayers(), this.board.getDeck());
		message = Translate.getNewResourceBundle().getString("endOfAge");
		return message;
	}

	public Deck switchDeck(Age currentAge) {
		Deck newDeck;
		Age nextAge = getNextAge(currentAge);
		newDeck = this.handlers.getSetUpDeckHandler().createDeck(nextAge, getNumPlayers());

		this.currentDirection = getNextRotation(currentAge);
		this.handlers.getTurnHandler().endAge(this.getPlayers(), currentAge);
		return newDeck;
	}
	
	public String formatFinalScores(ArrayList<Integer> scores){
		StringBuilder formattedString = new StringBuilder();
		for (int i = 0; i < scores.size(); i++){
			String playerName = this.getPlayer(i).getName();
			formattedString.append(playerName + " : " + scores.get(i) + "\n");
		}
		int indexOfWinner = indexOfMaxScore(scores, this.getPlayers());
		formattedString.append(this.getPlayers().get(indexOfWinner).getName() + " Wins!");
		return formattedString.toString();
	}
	
	private int indexOfMaxScore(ArrayList<Integer> scores, ArrayList<Player> players){
		int max = 0;
		int maxIndex = 0;
		for (int i = 0; i < scores.size(); i++){
			if (scores.get(i) > max){
				max = scores.get(i);
				maxIndex = i;
			}else if (scores.get(i) == max){
				maxIndex = (players.get(i).getCoinTotal() > players.get(maxIndex).getCoinTotal()) ? i : maxIndex; 
			}
		}
		return maxIndex;
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