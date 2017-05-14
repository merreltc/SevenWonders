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
import guiDataStructures.PlayerInformationHolder;
import guiMain.Message;
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
	private PlayerTurnHandler playerTurnHandler;

	private Direction currentDirection = Direction.CLOCKWISE;

	public GameManager(ArrayList<PlayerInformationHolder> holder) {
		this(holder, new SetUpHandler(), new SetUpDeckHandler(), new TurnHandler(), new PlayerTurnHandler());
	}

	public GameManager(ArrayList<PlayerInformationHolder> holder, SetUpHandler setUpHandler,
			SetUpDeckHandler setUpDeckHandler, TurnHandler turnHandler, PlayerTurnHandler playerTurnHandler) {
		this.setUpHandler = setUpHandler;
		this.setUpDeckHandler = setUpDeckHandler;
		this.turnHandler = turnHandler;
		this.playerTurnHandler = playerTurnHandler;
		setUpGame(holder);
	}

	public void setUpGame(ArrayList<PlayerInformationHolder> holder) {
		ArrayList<Player> players = this.setUpHandler.setUpAndReturnPlayers(holder);
		Deck deck = this.setUpDeckHandler.createDeck(Age.AGE1, holder.size());

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
		this.currentDirection = direction;
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
				Deck newDeck;
				if (this.board.getDeck().getAge() == Age.AGE1) {
					newDeck = this.setUpDeckHandler.createDeck(Age.AGE2, getNumPlayers());
					this.currentDirection = Direction.COUNTERCLOCKWISE;
				}else{
					newDeck = this.setUpDeckHandler.createDeck(Age.AGE3, getNumPlayers());
				}
				
				this.board.setDeck(newDeck);
				this.turnHandler.dealInitialTurnCards(this.getPlayers(), this.getNumPlayers(), this.board.getDeck());
				message = "This is the end of the Age.  Finalizing Points";
			} else {
				this.rotateHandler.rotateCurrentHands(getPlayers(), this.currentDirection);
				this.turnHandler.setNumTurnsTilEndOfAge(turnsTilEnd - 1);
				message = "End of current rotation.  Switching Player hands.";
			}

			playersUntilPass = 3;
		}

		this.turnHandler.setNumPlayersUntilPass(playersUntilPass - 1);
		
		if(this.currentDirection == Direction.CLOCKWISE){
			this.rotateClockwise();
		}else{
			this.rotateCounterClockwise();
		}
		return message;
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

	public Direction getDirection() {
		return this.currentDirection;
	}
}
