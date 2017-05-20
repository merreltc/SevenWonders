package backend.handlers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.Wonder;
import dataStructures.playerData.Player;
import exceptions.InsufficientFundsException;
import utils.DropDownMessage;
import utils.Translate;

public class PlayerTurnHandler {
	private GameBoard board;

	public void buildStructure(Player current, Card card) {
		if (current.storagePileContainsCardByName(card.getName())) {
			throw new IllegalArgumentException(
					Translate.getNewResourceBundle().getString("playerHasAlreadyHasStructure"));
		} else if (checkForPreviousStructure(current, card)) {
			return;
		}

		boolean freeBuild = checkEffects(current, card);
		if (freeBuild)
			return;

		validateCardCost(current, card);
		new EffectHandler(this.board).enableCardEffect(current, card);
		current.addCardToStoragePile(card);
		current.removeFromCurrentHand(card);
	}

	private boolean checkEffects(Player current, Card card) {
		HashMap<Frequency, HashSet<Effect>> wonderPile = current.getWonderPile();
		for (Frequency frequency : wonderPile.keySet()) {
			if (frequency == Frequency.ONCEAGE) {
				for (Effect effect : wonderPile.get(frequency)) {
					current.discardTemporaryEffect(effect);
					break;
				}

				String choice = getChosenString();
				return getBuildFree(current, card, choice);
			}
		}

		return false;
	}

	private boolean getBuildFree(Player current, Card card, String choice) {
		if (choice.equals("Free")) {
			new EffectHandler(this.board).enableAbilityFreeBuildEffect(current, card);
			return true;
		} else {
			return false;
		}
	}

	private String getChosenString() {
		String str = "";
		while (str.equals("")) {
			showBuildMessage();
		}
		return str;
	}

	public String showBuildMessage() {
		return new DropDownMessage().dropDownBuildMessage();
	}

	public Level buildWonderLevel(Player current) {
		Wonder wonder = current.getWonder();
		int numBuilt = wonder.getNumBuiltLevels() + 1;
		Level level = wonder.getLevelFactory().getNextLevel(numBuilt);
		updateEffects(current, level);
		return level;
	}

	private void updateEffects(Player current, Level level) {
		current.clearTempWonderEffects();
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>(level.getCosts());
		validateLevelCosts(current, costs);
		new EffectHandler(this.board).enableEffects(current);
	}

	public void validateLevelCosts(Player current, HashMap<Enum, Integer> costs) {
		for (Card sCards : current.getAllCards()) {
			decrementCostsWithStorage(sCards, costs);
		}

		for (Enum key : costs.keySet()) {
			int trades = searchCurrentTradesForCost(current, key);
			costs.put(key, costs.get(key) - trades);
			validateEndCost(costs.get(key));
		}
	}

	private boolean checkForPreviousStructure(Player current, Card card) {
		String previousStructure = card.getPreviousStructureName();
		for (Card storage : current.getAllCards()) {
			if (storage.getName().contains(previousStructure)) {
				current.addCardToStoragePile(card);
				current.removeFromCurrentHand(card);
				return true;
			}
		}
		return false;
	}

	private void validateCardCost(Player current, Card card) {
		if (card.getCostType() == CostType.COIN) {
			int coinCost = card.getCost().get(CostType.COIN);
			PlayerChipHandler.removeTotalCoins(current, coinCost);
		} else if (card.getCostType() != CostType.NONE) {
			validatePlayerHasEntitiesForCard(current, card);
		}
	}

	private void validatePlayerHasEntitiesForCard(Player current, Card card) {
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>(card.getCost());
		for (Card sCards : current.getAllCards()) {
			decrementCostsWithStorage(sCards, costs);
		}

		for (Enum key : costs.keySet()) {
			int trades = searchCurrentTradesForCost(current, key);
			costs.put(key, costs.get(key) - trades);
			validateEndCost(costs.get(key));
		}
	}

	private void decrementCostsWithStorage(Card card, HashMap<Enum, Integer> costs) {
		if (card.getEffectType() != EffectType.ENTITY) {
			return;
		}
		EntityEffect effect = (EntityEffect) card.getEffect();
		Enum singleEffect = (Enum) effect.getEntities().keySet().toArray()[0];

		if (effect.getEntities().size() > 1) {
			chooseEntityForBuild(costs, card);
		} else if (costs.containsKey(singleEffect)) {
			int newCost = costs.get(singleEffect) - effect.getEntities().get(singleEffect);
			costs.put(singleEffect, newCost);
		}
	}

	private void chooseEntityForBuild(HashMap<Enum, Integer> costs, Card sCards) {
		EntityEffect effect = (EntityEffect) sCards.getEffect();
		String choice = null;
		while (choice == null) {
			choice = chooseWhichEntity(effect.getEntities(), sCards.getName());
		}

		Enum entity = translateEntity(choice);
		if (costs.containsKey(entity)) {
			int newCost = costs.get(entity) - effect.getEntities().get(entity);
			costs.put(entity, newCost);
		}
	}

	private Enum translateEntity(String choice) {
		Enum entity;
		try {
			entity = RawResource.valueOf(choice);
		} catch (Exception e) {
			entity = Good.valueOf(choice);
		}
		return entity;
	}

	public String chooseWhichEntity(HashMap<Enum, Integer> entities, String cardName) {
		return new DropDownMessage().dropDownEntitySelectionMessage(entities.keySet(), cardName);
	}

	private int searchCurrentTradesForCost(Player current, Enum key) {
		if (current.getCurrentTrades().containsKey(key)) {
			return current.getCurrentTrades().get(key);
		}
		return 0;
	}

	private void validateEndCost(int numcost) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		if (numcost > 0) {
			throw new InsufficientFundsException(messages.getString("noRequiredItemToBuildStructure"));
		}
	}

	public void discardSelectedCard(Player player, Card card) {
		board.addToDiscardPile(player, card);
		player.removeFromCurrentHand(card);
	}

	public void setGameBoard(GameBoard board) {
		this.board = board;
	}
}