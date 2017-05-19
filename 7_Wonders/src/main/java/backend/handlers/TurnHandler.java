package backend.handlers;

import java.util.ArrayList;

import backend.handlers.RotateHandler.Rotation;
import dataStructures.GameBoard;
import dataStructures.Handlers;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.playerData.Chip.ChipType;
import utils.Translate;
import dataStructures.playerData.Player;

public class TurnHandler {
	private int numPlayersUntilPass;
	private int numTurnsTilEndOfAge = 5;
	public Handlers handlers;
	private Rotation currentDirection = Rotation.CLOCKWISE;
	private GameBoard board;

	public void dealInitialTurnCards(ArrayList<Player> players, Deck deck) {
		this.numPlayersUntilPass = players.size() - 1;
		this.numTurnsTilEndOfAge = 5;
		
		for (Player player: players) {
			popluatePlayersHand(deck, player);
		}
	}

	private void popluatePlayersHand(Deck deck, Player player) {
		ArrayList<Card> currentHand = new ArrayList<Card>();

		for(int i = 0; i < 7; i++){
			Card toAdd = DeckHandler.drawCard(deck);
			currentHand.add(toAdd);
		}
		
		player.setCurrentHand(currentHand);
	}
	
	public void endAge(ArrayList<Player> players, Age age) {
		for (int i = 0; i < players.size(); i++){
			Player player1 = players.get(i);
			Player player2 = players.get((i+1)%players.size());
			if (player1.getNumShields() > player2.getNumShields()){
				this.addWinningPlayersTokensByAge(player1, age);
				PlayerChipHandler.addValueNeg1(player2, 1, ChipType.CONFLICTTOKEN);
			}else if (player1.getNumShields() < player2.getNumShields()){
				this.addWinningPlayersTokensByAge(player2, age);
				PlayerChipHandler.addValueNeg1(player1, 1, ChipType.CONFLICTTOKEN);
			}
		}
	}
	
	private void addWinningPlayersTokensByAge(Player winner, Age age){
		if (age == Age.AGE1){
			PlayerChipHandler.addValue1(winner, 1, ChipType.CONFLICTTOKEN);
		}else if (age == Age.AGE2){
			PlayerChipHandler.addValue3(winner, 1, ChipType.CONFLICTTOKEN);
		}else {
			PlayerChipHandler.addValue5(winner, 1, ChipType.CONFLICTTOKEN);
		}
	}

	public int getNumPlayersUntilPass() {
		return this.numPlayersUntilPass;
	}

	public void setNumPlayersUntilPass(int num) {
		this.numPlayersUntilPass = num;
	}

	public int getNumTurnsTilEndOfAge() {
		return this.numTurnsTilEndOfAge;
	}

	public void setNumTurnsTilEndOfAge(int num) {
		this.numTurnsTilEndOfAge = num;
	}
	
	public String endCurrentPlayerTurn(Handlers handlers) {
		String message = "";
		this.handlers = handlers;
		int playersUntilPass = this.handlers.getTurnHandler().getNumPlayersUntilPass();

		if (playersUntilPass == 0) {
			message = this.checkNeedToSwitchAges();
			playersUntilPass = this.board.getNumPlayers();
		}

		this.handlers.getTurnHandler().setNumPlayersUntilPass(playersUntilPass - 1);
		this.board.getCurrentPlayer().removeCurrentTrades();
		rotate(this.currentDirection);
		return message;
	}
	
	public void rotate(Rotation rotation) {
		if (rotation == Rotation.CLOCKWISE) {
			this.handlers.getRotateHandler().rotateClockwise();
		} else {
			this.handlers.getRotateHandler().rotateCounterClockwise();
		}
	}
	
	public String checkNeedToSwitchAges() {
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
		this.handlers.getRotateHandler().rotateCurrentHands(this.board.getPlayers(), this.currentDirection);
		this.handlers.getTurnHandler().setNumTurnsTilEndOfAge(turnsTilEnd - 1);
		message = Translate.getNewResourceBundle().getString("endOfCurrentRotation");
		return message;
	}
	
	public String switchAge() {
		Age age = this.board.getAge();
		if (age == Age.AGE3){
			return endGame();
		}
		
		return endAge(age);
	}

	private String endGame() {
		EndGameHandler end = new EndGameHandler();
		ArrayList<Integer> scores = end.calculateScores(this.board.getPlayers());
		return formatFinalScores(scores);
	}
	
	public String endAge(Age age) {
		String message;
		Deck newDeck;
		newDeck = switchDeck(age);
		this.board.setDeck(newDeck);
		DeckHandler.shuffleDeck(this.board.getDeck());
		this.handlers.getTurnHandler().dealInitialTurnCards(this.board.getPlayers(), this.board.getDeck());
		message = Translate.getNewResourceBundle().getString("endOfAge");
		return message;
	}

	public Deck switchDeck(Age currentAge) {
		Deck newDeck;
		Age nextAge = getNextAge(currentAge);

		newDeck = this.handlers.getSetUpDeckHandler().createDeck(nextAge, this.board.getPlayers().size());

		this.currentDirection = getNextRotation(currentAge);
		this.handlers.getTurnHandler().endAge(this.board.getPlayers(), currentAge);
		return newDeck;
	}
	
	public String formatFinalScores(ArrayList<Integer> scores){
		StringBuilder formattedString = new StringBuilder();
		for (int i = 0; i < scores.size(); i++){
			String playerName = this.board.getPlayers().get(i).getName();
			formattedString.append(playerName + " : " + scores.get(i) + "\n");
		}
		int indexOfWinner = indexOfMaxScore(scores, this.board.getPlayers());
		formattedString.append(this.board.getPlayers().get(indexOfWinner).getName() + " Wins!");
		return formattedString.toString();
	}
	
	public int indexOfMaxScore(ArrayList<Integer> scores, ArrayList<Player> players){
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

	public void setGameBoard(GameBoard board){
		this.board = board;
	}
	
	public Rotation getDirection(){
		return this.currentDirection;
	}
	
	public void setDirection(Rotation rotation){
		this.currentDirection = rotation;
	}
}
