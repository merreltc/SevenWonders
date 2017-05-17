package backend.handlers;

import java.util.ArrayList;

import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.playerData.Player;

public class EndGameHandler {

	public int getPointsFromGuildCards(Player current, Player left, Player right) {
		int points = 0;
		
		int counter = 0;
		for (;;){
			try{
				Card card = current.getCardFromEndGame(counter);
				if (card.getName().equals("Strategists Guild")){
					return right.getNumValueNeg1ConflictTokens() + left.getNumValueNeg1ConflictTokens();
				}else if (card.getName().equals("Scientists Guild")){
					//Figure this out
				}
				
				if (card.getEffect().getDirection() == Direction.SELF || card.getEffect().getDirection() == Direction.ALL){
					points += this.useEffect(card, current);
				}
				if (card.getEffect().getDirection() == Direction.LEFT || card.getEffect().getDirection() == Direction.ALL
						|| card.getEffect().getDirection() == Direction.NEIGHBORS){
					points += this.useEffect(card, left);
				}
				if (card.getEffect().getDirection() == Direction.RIGHT || card.getEffect().getDirection() == Direction.ALL
						|| card.getEffect().getDirection() == Direction.NEIGHBORS){
					points += this.useEffect(card, right);
				}
				
				counter++;
			
			}catch(Exception e){
				break;
			}
		}
		
		return points;
	}

	private int useEffect(Card card, Player player){
		int total = 0;
		ValueEffect effect = (ValueEffect) card.getEffect();
		if (effect.getAffectingEntity() != AffectingEntity.NONE){
			total += runEffectOnPlayer(effect,effect.getAffectingEntity(),player);
		}else {
			for (Enum currentType : effect.getAffectingEntities().keySet()){
				total += runEffectOnPlayer(effect, (AffectingEntity) currentType,player);
			}
		}

		return total;
	}
	
	private int runEffectOnPlayer(ValueEffect effect, AffectingEntity currentType, Player players){
		int total = 0;
		int valueToAdd = 0;
		if (!effect.getAffectingEntities().containsKey(currentType)){
			valueToAdd = effect.getValueAmount();
		}else{
			valueToAdd = effect.getAffectingEntities().get(currentType);;
		}
		
		ArrayList<Card> cards = players.getStoragePile();
		for (Card currentCard : cards){
			if (currentType.toString().contains(currentCard.getCardType().toString())){
				total += valueToAdd;
			}else if (currentCard.getCardType() == CardType.RAWMATERIAL && currentType == AffectingEntity.RAWRESOURCES){
				total += valueToAdd;
			}
		}
		return total;
	}
	
}
