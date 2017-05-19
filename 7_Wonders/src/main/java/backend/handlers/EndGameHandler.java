package backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

import constants.GeneralEnums;
import constants.GeneralEnums.Science;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Chip.ChipValue;
import dataStructures.playerData.Player;
import utils.DropDownMessage;
import utils.Message;

public class EndGameHandler {

	private int pointsForGuild = 0;

	public ArrayList<Integer> calculateScores(ArrayList<Player> players) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		for (int i = 0; i < players.size(); i++) {
			this.pointsForGuild = 0;
			Player player = players.get(i);
			scores.add(this.getTotalScore(player, players));
		}
		return scores;
	}

	private int getTotalScore(Player player, ArrayList<Player> players) {
		int playerLoc = players.indexOf(player);
		runWonderChecks(player, players);
		handleScientistsGuild(player);
		int guildEffects = getPointsFromGuildCards(player,
				players.get((players.size() + playerLoc - 1) % players.size()),
				players.get((playerLoc + 1) % players.size()));
		int scienceScore = getSciencePoints(player);
		return player.getNumVictoryPoints() + player.getCoinTotal() / 3 + player.getConflictTotal() + guildEffects
				+ scienceScore;
	}

	private void runWonderChecks(Player player, ArrayList<Player> players) {
		HashMap<Frequency, HashSet<Effect>> wonderPile = player.getWonderPile();
		for (Frequency freq : wonderPile.keySet()){
			if (wonderPile.get(freq).contains(Effect.EffectType.VALUE)){
				ValueEffect effect = wonderPile.get(freq).toArray(new ValueEffect[1])[0];
				player.addNumVictoryPoints(effect.getAffectingEntities().get(ValueEffect.Value.VICTORYPOINTS));
			}else if (wonderPile.get(freq).contains(Effect.EffectType.ABILITY)){
				int playerLoc = players.indexOf(player);
				copyGuildCard(playerLoc, players);
			}
		}
	}

	private void copyGuildCard(int playerLoc, ArrayList<Player> players) {
		Player left = players.get((players.size() + playerLoc - 1)%players.size());
		Player right = players.get((playerLoc + 1)%players.size());
		ArrayList<Card> cardsLeft = iterateThroughPile(left);
		ArrayList<Card> cardsRight = iterateThroughPile(right);
		ArrayList<String> names = getNames(cardsLeft, cardsRight);
		String selected = showGuildCardMessage(names);
		int index = names.indexOf(selected);
		if (index >= cardsLeft.size()){
			players.get(playerLoc).addCardToStoragePile(cardsLeft.get(index));
		}else {
			players.get(playerLoc).addCardToStoragePile(cardsLeft.get(index - cardsLeft.size()));
		}
	}

	private ArrayList<String> getNames(ArrayList<Card> cardsLeft, ArrayList<Card> cardsRight) {
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < cardsLeft.size(); i++){
			names.add(cardsLeft.get(i).getName());
		}
		for (int i = 0; i < cardsRight.size(); i++){
			names.add(cardsRight.get(i).getName());
		}
		return names;
	}
	
	public String showGuildCardMessage(ArrayList<String> guilds) {
		return new DropDownMessage().dropDownGuildSelectionMessage(guilds.toArray());
	}

	private ArrayList<Card> iterateThroughPile(Player left) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i > -1; i++){
			try{
				Card card = left.getCardFromEndGame(i);
				cards.add(card);
			}catch(Exception e){
				break;
			}
		}
		return cards;
	}

	public int getSciencePoints(Player player) {
		int score = 0;
		int[] scienceOwned = player.getNumberOfEachScience();
		for (int i = 0; i < 3; i++) {
			score += (int) Math.pow((double) scienceOwned[i], 2.0);
		}
		score += 7 * getMin(scienceOwned);
		return score;
	}

	private int getMin(int[] list) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (list[i] < min) {
				min = list[i];
			}
		}
		return min;
	}

	public int getPointsFromGuildCards(Player current, Player left, Player right) {
		try {
			getPoints(current, left, right);
		} catch (Exception e) {

		}
		return pointsForGuild;
	}

	private void getPoints(Player current, Player left, Player right) {
		int counter = 0;
		for (;;) {
			Card card;
			try{
				card = current.getCardFromEndGame(counter++);
			}catch (Exception e){
				return;
			}
			evaluateCardEffect(current, left, right, card);
		}
	}

	private void evaluateCardEffect(Player current, Player left, Player right, Card card) {
		if (card.getName().equals("Strategists Guild")) {
			pointsForGuild += right.getConflictTokens().get(ChipValue.NEG1)
					+ left.getConflictTokens().get(ChipValue.NEG1);
		} else if (card.getName().equals("Scientists Guild") || checkWonders(current.getWonderPile())) {
			throw new UnsupportedOperationException("Show Option Dialog");
		} else if (card.getName().equals("Builder Guild")){
			pointsForGuild += current.getWonder().getNumBuiltLevels();
		}else {
			pointsForGuild += this.checkSelf(card, current) + this.checkLeft(card, left)
					+ this.checkRight(card, right);
		}
	}
	
	private boolean checkWonders(HashMap<Frequency, HashSet<Effect>> wonderPile) {
		for (Frequency freq : wonderPile.keySet()){
			if (wonderPile.get(freq).contains(Effect.EffectType.ENTITY)){
				return true;
			}
		}
		return false;
	}

	private int checkSelf(Card card, Player player) {
		if (card.getEffect().getDirection() == Direction.SELF || card.getEffect().getDirection() == Direction.ALL) {
			return this.useEffect(card, player);
		}
		return 0; 
	}

	private int checkRight(Card card, Player player) {
		if (card.getEffect().getDirection() == Direction.RIGHT || card.getEffect().getDirection() == Direction.ALL
				|| card.getEffect().getDirection() == Direction.NEIGHBORS) {
			return this.useEffect(card, player);
		}
		return 0;
	}

	private int checkLeft(Card card, Player player) {
		if (card.getEffect().getDirection() == Direction.LEFT || card.getEffect().getDirection() == Direction.ALL
				|| card.getEffect().getDirection() == Direction.NEIGHBORS) {
			return this.useEffect(card, player);
		}
		return 0;
	}

	public void handleScientistsGuild(Player player) {
		int count = 0;
		for (;;) {
			try {
				Card card = player.getCardFromEndGame(count++);
				Enum choice = testCard(card.getName());
				if (choice != EffectType.NONE) {
					givePlayerReward(player, choice);
					break;
				}
			} catch (Exception e) {
				break;
			}
		}
	}

	public Enum testCard(String cardName) {
		if (cardName.equals("Scientists Guild")) {
			String str = getChosenString();
			if (str.equals("Protractor")) {
				return Science.PROTRACTOR;
			} else if (str.equals("Wheel")) {
				return Science.WHEEL;
			}
			return Science.TABLET;
		}
		return EffectType.NONE;
	}

	private String getChosenString() {
		String str = "";
		while (str.equals("")) {
			str = showMessage();
		}
		return str;
	}

	public String showMessage() {
		return new DropDownMessage().dropDownScienceSelectionMessage();
	}

	private void givePlayerReward(Player player, Enum choice) {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(choice, 1);
		Effect effect = new EntityEffect(EntityType.SCIENCE, entitiesAndAmounts);
		Card reward = new Card("Scientists Reward", CardType.SCIENTIFICSTRUCTURE, null, effect);
		player.addCardToStoragePile(reward);
	}

	private int useEffect(Card card, Player player) {
		int total = 0;
		ValueEffect effect = (ValueEffect) card.getEffect();
		if (effect.getAffectingEntity() != AffectingEntity.NONE) {
			total += runEffectOnPlayer(effect, effect.getAffectingEntity(), player);
		} else {
			for (Enum currentType : effect.getAffectingEntities().keySet()) {
				total += runEffectOnPlayer(effect, (AffectingEntity) currentType, player);
			}
		}
		return total;
	}

	private int runEffectOnPlayer(ValueEffect effect, AffectingEntity currentType, Player players) {
		int valueToAdd = 0;
		if (!effect.getAffectingEntities().containsKey(currentType)) {
			valueToAdd = effect.getValueAmount();
		} else {
			valueToAdd = effect.getAffectingEntities().get(currentType);
		}
		int total = runEffectOnAllCards(currentType, players, valueToAdd);
		return total;
	}

	private int runEffectOnAllCards(AffectingEntity currentType, Player players, int valueToAdd) {
		int total = 0;
		ArrayList<Card> cards = players.getAllCards();
		for (Card currentCard : cards) {
			if (currentType.toString().contains(currentCard.getCardType().toString())) {
				total += valueToAdd;
			} else if (currentCard.getCardType() == CardType.RAWMATERIAL
					&& currentType == AffectingEntity.RAWRESOURCES) {
				total += valueToAdd;
			}
		}
		return total;
	}

}
