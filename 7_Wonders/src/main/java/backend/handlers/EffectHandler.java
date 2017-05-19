package backend.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.ValueType;
import dataStructures.playerData.Player;
import utils.DropDownMessage;

public class EffectHandler {
	private GameBoard board;

	public EffectHandler(GameBoard board) {
		this.board = board;
	}

	public void enableEffects(Player current) {
		HashMap<Frequency, HashSet<Effect>> effects = current.getWonder().buildNextLevel();
		current.addWonderEffectToStoragePile(effects);
		for (Frequency frequency : effects.keySet()) {
			switch (frequency) {
			case ONCEIMMEDIATE:
				enableWonderEffects(current, effects.get(frequency));
				break;
			default:
				break;
			}
		}
	}

	private void enableWonderEffects(Player current, HashSet<Effect> effects) {
		for (Effect effect : effects) {
			if (effect.getEffectType() == EffectType.VALUE) {
				enableValueEffect(current, (ValueEffect) effect);
			}
		}
	}

	public void enableCardEffect(Player current, Card card) {
		if (card.getEffectType() == EffectType.VALUE) {
			ValueEffect effect = (ValueEffect) card.getEffect();
			
			if(effect.getDirection() == Direction.ALL){
				enableCoinCounts(current, effect);
				return;
			}
			if (effect.getValueType() == ValueType.COIN) {
				if (!card.getName().equals("Tavern"))
					board.removeNumCoins(current, effect.getValueAmount());
			}

			enableValueEffect(current, effect);
		}
	}
	
	private void enableCoinCounts(Player current, ValueEffect effect){
		Player next = this.board.getNextPlayer();
		Player previous = this.board.getPreviousPlayer();
		int numCoins = 0;

		numCoins += findTypeCards(next.getAllCards(), effect);
		numCoins += findTypeCards(previous.getAllCards(), effect);
		numCoins += findTypeCards(current.getAllCards(), effect);
		
		this.board.giveNumCoins(current, numCoins);
	}
	
	private int findTypeCards(ArrayList<Card> cards, ValueEffect effect){
		int value = 0;
		int amountIncreased = effect.getValueAmount();
		AffectingEntity affecting = effect.getAffectingEntity();
		String search;
		if(affecting == AffectingEntity.RAWRESOURCES){
			search = "RAWMATERIAL";
		}else{
			search = affecting.toString();
		}
		 
		for(Card card: cards){
			if(search.contains(card.getCardType().toString())){
				value += amountIncreased;
			}
		}
		
		return value;
	}

	public void enableValueEffect(Player current, ValueEffect effect) {
		if (effect.getValueType() == ValueType.VICTORYPOINT) {
			current.addNumVictoryPoints(effect.getValueAmount());
		} else if (effect.getValueType() == ValueType.COIN) {
			board.giveNumCoins(current, effect.getValueAmount());
		} else {
			current.addNumShields(effect.getValueAmount());
		}
	}
	
	public void enableAbilityFreeBuildEffect(Player current, Card card) {
		current.addCardToStoragePile(card);
		current.removeFromCurrentHand(card);
	}

	public void enableAbilityFreeBuildFromDiscard(Player current) {
		Object[] messages = new String[board.getDiscardPile().size()];
		for (int i = 0; i < board.getDiscardPile().size(); i++) {
			messages[i] = board.getDiscardPile().get(i).toString();
		}

		String card = getChosenString(messages);
		for (Card cards : board.getDiscardPile()) {
			if (cards.getName().equals(card)) {
				current.addCardToStoragePile(cards);
				board.getDiscardPile().remove(cards);
				return;
			}
		}
	}

	private String getChosenString(Object[] messages) {
		String str = "";
		while (str.equals("")) {
			str = showMessage(messages);
		}
		return str;
	}

	public String showMessage(Object[] messages) {
		return DropDownMessage.dropDownWonderSelectionMessage(messages);
	}
}
