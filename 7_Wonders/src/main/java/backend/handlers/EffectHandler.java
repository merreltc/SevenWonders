package backend.handlers;

import java.util.HashMap;
import java.util.HashSet;

import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.ValueType;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Player;

public class EffectHandler {
	private GameBoard board;
	
	public EffectHandler(GameBoard board){
		this.board = board;
	}
	
	public void enableEffects(Player current, Wonder wonder) {
		HashMap<Frequency, HashSet<Effect>> effects = wonder.buildNextLevel();
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
			if (effect.getValueType() == ValueType.COIN) {
				if (!card.getName().equals("Tavern"))
					board.removeNumCoins(current, effect.getValueAmount());
			}
			
			enableValueEffect(current, effect);
		}
	}

	private void enableValueEffect(Player current, ValueEffect effect) {
		if (effect.getValueType() == ValueType.VICTORYPOINT) {
			current.addNumVictoryPoints(effect.getValueAmount());
		} else if (effect.getValueType() == ValueType.COIN) {
				board.giveNumCoins(current, effect.getValueAmount());
		} else {
			current.addNumShields(effect.getValueAmount());
		}
	}
}
