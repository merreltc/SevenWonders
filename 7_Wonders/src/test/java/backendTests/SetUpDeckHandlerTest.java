package backendTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.handlers.SetUpDeckHandler;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Resource;
import constants.GeneralEnums.Science;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.MultiValueEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.ValueEffect.ValueType;
import dataStructures.playerData.Player;
import junit.framework.Assert;

public class SetUpDeckHandlerTest {

	@Test
	public void testCreateAge1Cards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = SetUpDeckTestHelper.createAge1Cards(numPlayers);
		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE1, numPlayers);

		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}
	}

	@Test
	public void testCreateAge1Cards7Players() {
		int numPlayers = 7;
		ArrayList<Card> cards = SetUpDeckTestHelper.createAge1Cards(numPlayers);
		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE1, numPlayers);

		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}
	}

	@Test
	public void testCreateAge2Cards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = SetUpDeckTestHelper.createAge2Cards(numPlayers);

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE2, numPlayers);

		assertEquals(cards.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}

	}

	@Test
	public void testCreateAge2Cards7Players() {
		int numPlayers = 7;
		ArrayList<Card> cards = SetUpDeckTestHelper.createAge2Cards(numPlayers);

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE2, numPlayers);

		assertEquals(cards.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}

	}

	@Test
	public void testCreateAge3Cards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = SetUpDeckTestHelper.createAge3Cards(numPlayers);
		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE3, numPlayers);

		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}
	}

	@Test
	public void testAge2Cards3PlayersTempleHasNextAndPrevious() {
		int numPlayers = 3;
		ArrayList<Card> cards = SetUpDeckTestHelper.createAge2Cards(numPlayers);
		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE2, numPlayers);

		Card temple = actual.get(8);
		assertEquals("Temple", temple.getName());
		assertEquals("Pantheon", temple.getNextStructureName());
		assertEquals("Altar", temple.getPreviousStructureName());
	}

	@Test
	public void testVerifyCardInformationRawmaterialCoinCostEntityEffect() {
		int numPlayers = 3;

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE2, numPlayers);
		Card card = actual.get(0);
		EntityEffect effect = (EntityEffect) card.getEffect();
		HashMap<Enum, Integer> entitiesAndAmounts = effect.getEntities();

		assertEquals("Sawmill", card.getName());
		assertEquals(CostType.COIN, card.getCostType());
		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(EntityType.RESOURCE, effect.getEntityType());
		assertEquals(2, (int) entitiesAndAmounts.get(RawResource.LUMBER));
		assertEquals("None", card.getNextStructureName());
		assertEquals("None", card.getPreviousStructureName());
	}

	@Test
	public void testVerifyCardInformationGoodNoCostEntityEffect() {
		int numPlayers = 3;

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE2, numPlayers);
		Card card = actual.get(6);
		EntityEffect effect = (EntityEffect) card.getEffect();
		HashMap<Enum, Integer> entitiesAndAmounts = effect.getEntities();

		assertEquals("Press", card.getName());
		assertEquals(CostType.NONE, card.getCostType());
		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(EntityType.MANUFACTUREDGOOD, effect.getEntityType());
		assertEquals(1, (int) entitiesAndAmounts.get(Good.PRESS));
		assertEquals("None", card.getNextStructureName());
		assertEquals("None", card.getPreviousStructureName());
	}

	@Test
	public void testVerifyCardInformationCivilianResourceCostValueEffect() {
		int numPlayers = 3;

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE2, numPlayers);
		Card card = actual.get(7);
		ValueEffect effect = (ValueEffect) card.getEffect();
		HashMap<Enum, Integer> costs = card.getCost();

		assertEquals("Aqueduct", card.getName());
		assertEquals(CostType.RESOURCE, card.getCostType());
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Value.VICTORYPOINTS, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
		assertEquals(3, (int) costs.get(RawResource.STONE));
		assertEquals(5, effect.getValueAmount());
		assertEquals("None", card.getNextStructureName());
		assertEquals("Baths", card.getPreviousStructureName());
	}

	@Test
	public void testCardInformationCommercialMultiCostMultiEffect() {
		int numPlayers = 3;

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE3, numPlayers);
		Card card = actual.get(14);
		HashMap<Enum, Integer> costs = card.getCost();

		assertEquals("Haven", card.getName());
		assertEquals(CostType.MULTITYPE, card.getCostType());
		assertEquals(1, (int) costs.get(Good.LOOM));
		assertEquals(1, (int) costs.get(RawResource.LUMBER));
		assertEquals(1, (int) costs.get(RawResource.ORE));

		MultiValueEffect effect = (MultiValueEffect) card.getEffect();
		HashMap<Enum, Integer> effects = effect.getValues();
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Value.COMMERCE, effect.getValue());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(AffectingEntity.RAWRESOURCES, effect.getAffectingEntity());
		assertEquals(1, (int) effects.get(ValueType.COIN));
		assertEquals(1, (int) effects.get(ValueType.VICTORYPOINT));

		assertEquals("None", card.getNextStructureName());
		assertEquals("Forum", card.getPreviousStructureName());
	}

	@Test
	public void testVerifyCardAddMultipleOfSameCard7Players() {
		int numPlayers = 7;

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE2, numPlayers);
		Card card = actual.get(14);
		ValueEffect effect = (ValueEffect) card.getEffect();
		HashMap<Enum, Integer> costs = card.getCost();

		assertEquals("Aqueduct", card.getName());
		assertEquals(CostType.RESOURCE, card.getCostType());
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Value.VICTORYPOINTS, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
		assertEquals(3, (int) costs.get(RawResource.STONE));
		assertEquals(5, effect.getValueAmount());
		assertEquals("None", card.getNextStructureName());
		assertEquals("Baths", card.getPreviousStructureName());

		Card card2 = actual.get(15);
		ValueEffect effect2 = (ValueEffect) card.getEffect();
		HashMap<Enum, Integer> costs2 = card.getCost();

		assertEquals("Aqueduct", card2.getName());
		assertEquals(CostType.RESOURCE, card2.getCostType());
		assertEquals(EffectType.VALUE, effect2.getEffectType());
		assertEquals(Value.VICTORYPOINTS, effect2.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect2.getValueType());
		assertEquals(AffectingEntity.NONE, effect2.getAffectingEntity());
		assertEquals(3, (int) costs2.get(RawResource.STONE));
		assertEquals(5, effect2.getValueAmount());
		assertEquals("None", card2.getNextStructureName());
		assertEquals("Baths", card2.getPreviousStructureName());
	}

	@Test
	public void testCreateDeck() {
		assertEquals(Deck.class, new SetUpDeckHandler().createDeck(Age.AGE1, 3).getClass());
	}
	
	@Test
	public void testRemoveGuildCards3Players(){
		ArrayList<Card> deck = new SetUpDeckHandler().createCards(Age.AGE3, 3);
		
		deck = new SetUpDeckHandler().correctNumberOfGuildCards(deck, 3);
		
		for (int i = 0; i < 5; i++){
			Assert.assertEquals(CardType.GUILD, deck.get(i).getCardType());
		}
		Assert.assertFalse(CardType.GUILD == deck.get(5).getCardType());
	}

}
