package backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import backend.handlers.RotateHandler.Rotation;
import dataStructures.GameBoard;
import dataStructures.Handlers;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Player;
import utils.Translate;

public class TurnHandler {
	private class TurnInfo{
		protected int numPlayersUntilPass;
		protected int numTurnsTilEndOfAge = 5;
		protected Handlers handlers;
		protected Rotation currentDirection = Rotation.CLOCKWISE;
		protected GameBoard board;
	}
	
	private TurnInfo turnInfo = new TurnInfo();
	
	public TurnHandler(Handlers handlers){
		this.turnInfo.handlers = handlers;
	}

	public void dealInitialTurnCards(ArrayList<Player> players, Deck deck) {
		this.turnInfo.numPlayersUntilPass = players.size() - 1;
		this.turnInfo.numTurnsTilEndOfAge = 5;
		
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
	
	public void performEndAgeBattles(ArrayList<Player> players, Age age) {
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
	
	public String endCurrentPlayerTurn(Handlers handlers) {
		String message = "";
		this.turnInfo.handlers = handlers;
		int playersUntilPass = this.turnInfo.numPlayersUntilPass;
		if(this.turnInfo.numTurnsTilEndOfAge == 0){
			applyEffects();
		}

		if (playersUntilPass == 0) {
			message = this.checkNeedToSwitchAges();
			playersUntilPass = this.turnInfo.board.getNumPlayers();
		}

		this.turnInfo.numPlayersUntilPass = playersUntilPass - 1;
		this.turnInfo.board.getCurrentPlayer().removeCurrentTrades();
		rotate(this.turnInfo.currentDirection);
		return message;
	}
	
	private void applyEffects(){
		HashMap<Frequency, HashSet<Effect>> wonderPile = this.turnInfo.board.getCurrentPlayer().getWonderPile();
		for(Frequency frequency: wonderPile.keySet()){
			
		}	
	}
	
	public void rotate(Rotation rotation) {
		if (rotation == Rotation.CLOCKWISE) {
			this.turnInfo.handlers.getRotateHandler().rotateClockwise();
		} else {
			this.turnInfo.handlers.getRotateHandler().rotateCounterClockwise();
		}
	}
	
	public String checkNeedToSwitchAges() {
		String message;
		int turnsTilEnd = this.turnInfo.numTurnsTilEndOfAge;
		if (turnsTilEnd == 0) {
			message = switchAge();
		} else  {
			message = rotateHand(turnsTilEnd);
		}
		return message;
	}

	private String rotateHand(int turnsTilEnd) {
		String message;
		this.turnInfo.handlers.getRotateHandler().rotateCurrentHands(this.turnInfo.board.getPlayers(), this.turnInfo.currentDirection);
		this.turnInfo.numTurnsTilEndOfAge = turnsTilEnd - 1;
		message = Translate.getNewResourceBundle().getString("endOfCurrentRotation");
		return message;
	}
	
	public String switchAge() {
		Age age = this.turnInfo.board.getAge();
		if (age == Age.AGE3){
			return endGame();
		}
		
		return endAge(age);
	}

	private String endGame() {
		EndGameHandler end = new EndGameHandler();
		ArrayList<Integer> scores = end.calculateScores(this.turnInfo.board.getPlayers());
		return formatFinalScores(scores);
	}
	
	public String endAge(Age age) {
		String message;
		Deck newDeck;
		newDeck = switchDeck(age);
		this.turnInfo.board.setDeck(newDeck);
		DeckHandler.shuffleDeck(this.turnInfo.board.getDeck());
		dealInitialTurnCards(this.turnInfo.board.getPlayers(), this.turnInfo.board.getDeck());
		message = Translate.getNewResourceBundle().getString("endOfAge");
		return message;
	}

	public Deck switchDeck(Age currentAge) {
		Deck newDeck;
		Age nextAge = getNextAge(currentAge);

		newDeck = this.turnInfo.handlers.getSetUpDeckHandler().createDeck(nextAge, this.turnInfo.board.getPlayers().size());

		this.turnInfo.currentDirection = getNextRotation(currentAge);
		performEndAgeBattles(this.turnInfo.board.getPlayers(), currentAge);
		return newDeck;
	}
	
	public String formatFinalScores(ArrayList<Integer> scores){
		StringBuilder formattedString = new StringBuilder();
		for (int i = 0; i < scores.size(); i++){
			String playerName = this.turnInfo.board.getPlayers().get(i).getName();
			formattedString.append(playerName + " : " + scores.get(i) + "\n");
		}
		int indexOfWinner = indexOfMaxScore(scores, this.turnInfo.board.getPlayers());
		formattedString.append(this.turnInfo.board.getPlayers().get(indexOfWinner).getName() + " Wins!");
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
		this.turnInfo.board = board;
	}
	
	public Rotation getDirection(){
		return this.turnInfo.currentDirection;
	}
	
	public void setDirection(Rotation rotation){
		this.turnInfo.currentDirection = rotation;
	}
}
