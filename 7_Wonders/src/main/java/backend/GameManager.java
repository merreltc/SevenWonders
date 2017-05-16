package backend;

import java.util.ArrayList;

import backend.handlers.PlayerTurnHandler;
import backend.handlers.RotateHandler;
import backend.handlers.SetUpDeckHandler;
import backend.handlers.SetUpPlayerHandler;
import backend.handlers.TradeHandler;
import backend.handlers.TurnHandler;
import backend.handlers.RotateHandler.Rotation;
import dataStructures.GameBoard;
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
	private SetUpPlayerHandler setUpPlayerHandler;
	private SetUpDeckHandler setUpDeckHandler;
	private TurnHandler turnHandler;

	private RotateHandler rotateHandler;
	private TradeHandler tradeHandler;
	private PlayerTurnHandler playerTurnHandler;

	private Rotation currentDirection = Rotation.CLOCKWISE;

	public GameManager(ArrayList<String> names) {
		this(names, new SetUpPlayerHandler(), new SetUpDeckHandler(), new TurnHandler(), new PlayerTurnHandler());
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
		ArrayList<Player> players = this.setUpPlayerHandler.setUpAndReturnPlayers(names);
		Deck deck = this.setUpDeckHandler.createDeck(Age.AGE1, names.size());

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
		if (from.storagePileContainsCardByName("East Trading Post")) {
			int fromPosition = this.getPlayers().indexOf(from);
			int toPosition = this.getPlayers().indexOf(to);
			if (++fromPosition == toPosition) {
				this.tradeHandler.tradeFromToForEntity(from, to, entity, true);
				return;
			}
		}

		this.tradeHandler.tradeFromToForEntity(from, to, entity, false);
	}

	public void buildStructure(Card card) {
		this.playerTurnHandler.buildStructure(getCurrentPlayer(), card, this.board);
	}

	public void changeRotateDirectionAndResetPositions(Rotation direction) {
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
					this.currentDirection = Rotation.COUNTERCLOCKWISE;
					this.turnHandler.endAge(this.getPlayers(), Age.AGE1);
				} else {
					newDeck = this.setUpDeckHandler.createDeck(Age.AGE3, getNumPlayers());
					this.turnHandler.endAge(this.getPlayers(), Age.AGE2);
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

		if (this.currentDirection == Rotation.CLOCKWISE) {
			this.rotateClockwise();
		} else {
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

	public Rotation getDirection() {
		return this.currentDirection;
	}
}