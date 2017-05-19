package backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import constants.GeneralEnums;
import constants.GeneralEnums.Science;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.playerData.Chip.ChipType;
import dataStructures.playerData.Chip.ChipValue;
import dataStructures.playerData.Player;
import utils.Message;

public class EndGameHandler {

	public ArrayList<Integer> calculateScores(ArrayList<Player> players) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			handleScientistsGuild(player, new Message());
			int guildEffects = getPointsFromGuildCards(player, players.get((players.size() + i - 1) % players.size()),
					players.get((i + 1) % players.size()));

			int scienceScore = getSciencePoints(player);
			scores.add(player.getNumVictoryPoints() + player.getCoinTotal() / 3 + player.getConflictTotal()
					+ guildEffects + scienceScore);
		}
		return scores;
	}

	public int getSciencePoints(Player player) {
		int score = 0;
		int[] scienceOwned = player.getNumberOfEachScience();
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (scienceOwned[i] < min) {
				min = scienceOwned[i];
			}
			score += (int) Math.pow((double) scienceOwned[i], 2.0);
		}
		score += 7 * min;
		return score;
	}

	public int getPointsFromGuildCards(Player current, Player left, Player right) {
		int points = 0;

		int counter = 0;
		for (;;) {
			try {
				Card card = current.getCardFromEndGame(counter);
				if (card.getName().equals("Strategists Guild")) {
					return right.getConflictTokens().get(ChipValue.NEG1) + left.getConflictTokens().get(ChipValue.NEG1);
				} else if (card.getName().equals("Scientists Guild")) {
					throw new UnsupportedOperationException("Show Option Dialog");
				}

				if (card.getEffect().getDirection() == Direction.SELF
						|| card.getEffect().getDirection() == Direction.ALL) {
					points += this.useEffect(card, current);
				}
				if (card.getEffect().getDirection() == Direction.LEFT
						|| card.getEffect().getDirection() == Direction.ALL
						|| card.getEffect().getDirection() == Direction.NEIGHBORS) {
					points += this.useEffect(card, left);
				}
				if (card.getEffect().getDirection() == Direction.RIGHT
						|| card.getEffect().getDirection() == Direction.ALL
						|| card.getEffect().getDirection() == Direction.NEIGHBORS) {
					points += this.useEffect(card, right);
				}

				counter++;

			} catch (Exception e) {
				if (!e.getMessage().equals("Show Option Dialog")) {
					break;
				}
			}
		}

		return points;
	}

	public void handleScientistsGuild(Player player, Message message) {
		int count = 0;

		for (;;) {
			try {

				Card card = player.getCardFromEndGame(count);
				if (card.getName().equals("Scientists Guild")) {
					String str = "";
					while (str.equals("")) {
						str = message.dropDownScienceSelectionMessage();
					}

					Enum choice = Science.TABLET;
					if (str.equals("Protractor")) {
						choice = Science.PROTRACTOR;
					} else if (str.equals("Wheel")) {
						choice = Science.WHEEL;
					}

					HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
					entitiesAndAmounts.put(choice, 1);
					Effect effect = new EntityEffect(EntityType.SCIENCE, entitiesAndAmounts);
					Card reward = new Card("Scientists Reward", CardType.SCIENTIFICSTRUCTURE, null, effect);
					player.addCardToStoragePile(reward);
					break;
				}
				count++;
			} catch (Exception e) {
				break;
			}
		}
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
		int total = 0;
		int valueToAdd = 0;
		if (!effect.getAffectingEntities().containsKey(currentType)) {
			valueToAdd = effect.getValueAmount();
		} else {
			valueToAdd = effect.getAffectingEntities().get(currentType);
			;
		}

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
