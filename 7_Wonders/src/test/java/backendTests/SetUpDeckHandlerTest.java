package backendTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

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

public class SetUpDeckHandlerTest {

	@Test
	public void testCreateAge1Cards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = createAge1Cards(numPlayers);
		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE1, numPlayers);

		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}
	}

	@Test
	public void testCreateAge1Cards7Players() {
		int numPlayers = 7;
		ArrayList<Card> cards = createAge1Cards(numPlayers);
		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE1, numPlayers);

		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}
	}

	@Test
	public void testCreateAge2Cards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = createAge2Cards(numPlayers);

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE2, numPlayers);

		assertEquals(cards.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}

	}

	@Test
	public void testCreateAge2Cards7Players() {
		int numPlayers = 7;
		ArrayList<Card> cards = createAge2Cards(numPlayers);

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE2, numPlayers);

		assertEquals(cards.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}

	}

	@Test
	public void testCreateAge3Cards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = new ArrayList<Card>();

		ArrayList<Card> actual = new SetUpDeckHandler().createCards(Age.AGE3, numPlayers);

		for (int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}

	}

	@Test
	public void testAge2Cards3PlayersTempleHasNextAndPrevious() {
		int numPlayers = 3;
		ArrayList<Card> cards = createAge2Cards(numPlayers);

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

	public ArrayList<Card> createAge1Cards(int numPlayers) {
		ArrayList<Card> cards = new ArrayList<Card>();
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		Cost cost;
		Effect effect;
		Card card;

		// lumber yard
		frequency.add(3);
		frequency.add(4);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(RawResource.LUMBER, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Lumber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// stone pit
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(5);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(RawResource.STONE, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Stone Pit", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Stone Pit", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// clay pool
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(5);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(RawResource.CLAY, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Clay Pool", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Clay Pool", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// ore vein
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(4);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(RawResource.ORE, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Ore Vein", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Ore Vein", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// tree farm
		if (numPlayers >= 6) {
			frequency = new ArrayList<Integer>();
			entitiesAndAmounts = new HashMap<Enum, Integer>();
			frequency.add(6);
			cost = new Cost(CostType.COIN, 1);
			entitiesAndAmounts.put(RawResource.LUMBER, 1);
			entitiesAndAmounts.put(RawResource.CLAY, 1);
			effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

			card = new Card("Tree Farm", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// excavation
		if (numPlayers >= 4) {
			frequency = new ArrayList<Integer>();
			entitiesAndAmounts = new HashMap<Enum, Integer>();
			frequency.add(4);
			cost = new Cost(CostType.COIN, 1);
			entitiesAndAmounts.put(RawResource.STONE, 1);
			entitiesAndAmounts.put(RawResource.CLAY, 1);
			effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

			card = new Card("Excavation", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// clay pit
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		cost = new Cost(CostType.COIN, 1);
		entitiesAndAmounts.put(RawResource.CLAY, 1);
		entitiesAndAmounts.put(RawResource.ORE, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Clay Pit", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		// timber yard
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		cost = new Cost(CostType.COIN, 1);
		entitiesAndAmounts.put(RawResource.STONE, 1);
		entitiesAndAmounts.put(RawResource.LUMBER, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Timber Yard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		// forest Cave
		if (numPlayers >= 5) {
			frequency = new ArrayList<Integer>();
			entitiesAndAmounts = new HashMap<Enum, Integer>();
			frequency.add(5);
			cost = new Cost(CostType.COIN, 1);
			entitiesAndAmounts.put(RawResource.LUMBER, 1);
			entitiesAndAmounts.put(RawResource.ORE, 1);
			effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

			card = new Card("Forest Cave", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// mine
		if (numPlayers >= 6) {
			frequency = new ArrayList<Integer>();
			entitiesAndAmounts = new HashMap<Enum, Integer>();
			frequency.add(6);
			cost = new Cost(CostType.COIN, 1);
			entitiesAndAmounts.put(RawResource.ORE, 1);
			entitiesAndAmounts.put(RawResource.STONE, 1);
			effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

			card = new Card("Mine", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// loom
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(6);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(Good.LOOM, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);

		card = new Card("Loom", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Loom", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
			cards.add(card);
		}

		// glassworks
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(6);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(Good.GLASS, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);

		card = new Card("Glassworks", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Glassworks", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
			cards.add(card);
		}

		// press
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(6);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(Good.PRESS, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);

		card = new Card("Press", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Press", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
			cards.add(card);
		}

		if (numPlayers >= 4) {
			// pawnshop
			frequency = new ArrayList<Integer>();
			frequency.add(4);
			frequency.add(7);
			cost = new Cost(CostType.NONE, 0);
			effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 3);
			card = new Card("Pawnshop", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);

			if (numPlayers >= 7) {
				card = new Card("Pawnshop", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "None");
				cards.add(card);
			}
		}

		// baths
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Resource.STONE, 1);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 3);
		card = new Card("Baths", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "Aqueduct");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("Baths", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "Aqueduct");
			cards.add(card);
		}

		// altar
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		cost = new Cost(CostType.NONE, 0);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2);
		card = new Card("Altar", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "Temple");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Altar", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "Temple");
			cards.add(card);
		}

		// theater
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		cost = new Cost(CostType.NONE, 0);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2);
		card = new Card("Theater", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "Statue");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Theater", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "Statue");
			cards.add(card);
		}

		// tavern
		if (numPlayers >= 4) {
			frequency = new ArrayList<Integer>();
			frequency.add(4);
			frequency.add(5);
			frequency.add(7);
			cost = new Cost(CostType.NONE, 0);
			effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.NONE, 5);
			card = new Card("Tavern", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);

			if (numPlayers >= 5) {
				card = new Card("Tavern", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");
				cards.add(card);
				if (numPlayers >= 7) {
					card = new Card("Tavern", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");
					cards.add(card);
				}
			}
		}

		// east trading post
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		cost = new Cost(CostType.NONE, 0);
		effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.RIGHT, 1);
		card = new Card("East Trading Post", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "Forum");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("East Trading Post", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None",
					"Forum");
			cards.add(card);
		}

		// west trading post
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		cost = new Cost(CostType.NONE, 0);
		effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.LEFT, 1);
		card = new Card("West Trading Post", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "Forum");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("West Trading Post", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None",
					"Forum");
			cards.add(card);
		}

		// marketplace
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		cost = new Cost(CostType.NONE, 0);
		effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS,
				Direction.NEIGHBORS, 1);
		card = new Card("Marketplace", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "Forum");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Marketplace", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None",
					"Caravansery");
			cards.add(card);
		}

		// stockade
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 1);
		card = new Card("Stockade", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("Stockade", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);
		}

		// barracks
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.ORE, 1);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 1);
		card = new Card("Barracks", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Barracks", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);
		}

		// guard
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.CLAY, 1);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 1);
		card = new Card("Guard Tower", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Guard Tower", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);
		}

		// apothecary
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		cost = new Cost(CostType.GOOD, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.PROTRACTOR, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Apothecary", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "None",
				"Stables/Dispensary");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Apothecary", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "None",
					"Stables/Dispensary");
			cards.add(card);
		}

		// workshop
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		cost = new Cost(CostType.GOOD, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.WHEEL, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Workshop", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "None",
				"Archery Range/Laboratory");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("Workshop", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "None",
					"Archery Range/Laboratory");
			cards.add(card);
		}

		// scriptorium
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.PRESS, 1);
		cost = new Cost(CostType.GOOD, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.TABLET, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Scriptorium", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "None",
				"Courthouse/Library");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Scriptorium", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "None",
					"Courthouse/Library");
			cards.add(card);
		}

		return cards;
	}

	public ArrayList<Card> createAge2Cards(int numPlayers) {
		ArrayList<Card> cards = new ArrayList<Card>();
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		Cost cost;
		Effect effect;
		Card card;

		// Sawmill
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(4);
		cost = new Cost(CostType.COIN, 1);
		entitiesAndAmounts.put(RawResource.LUMBER, 2);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Sawmill", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Sawmill", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// Quarry
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(4);
		cost = new Cost(CostType.COIN, 1);
		entitiesAndAmounts.put(RawResource.STONE, 2);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Quarry", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Quarry", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// Brickyard
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(4);
		cost = new Cost(CostType.COIN, 1);
		entitiesAndAmounts.put(RawResource.CLAY, 2);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Brickyard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Brickyard", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// foundry
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(4);
		cost = new Cost(CostType.COIN, 1);
		entitiesAndAmounts.put(RawResource.ORE, 2);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		card = new Card("Foundry", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Foundry", frequency, CardType.RAWMATERIAL, cost, effect, "None", "None");
			cards.add(card);
		}

		// loom
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(5);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(Good.LOOM, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);

		card = new Card("Loom", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Loom", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
			cards.add(card);
		}

		// glassworks
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(5);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(Good.GLASS, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);

		card = new Card("Glassworks", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Glassworks", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
			cards.add(card);
		}

		// press
		frequency = new ArrayList<Integer>();
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		frequency.add(3);
		frequency.add(5);
		cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(Good.PRESS, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);

		card = new Card("Press", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Press", frequency, CardType.MANUFACTUREDGOOD, cost, effect, "None", "None");
			cards.add(card);
		}

		// aqueduct
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.STONE, 3);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 5);
		card = new Card("Aqueduct", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Baths", "None");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("Aqueduct", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Baths", "None");
			cards.add(card);
		}

		// temple
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.CLAY, 1);
		costs.put(Good.GLASS, 1);

		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 3);
		card = new Card("Temple", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Altar", "Pantheon");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Temple", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Altar", "Pantheon");
			cards.add(card);
		}

		// statue
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.ORE, 2);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 4);
		card = new Card("Statue", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Theater", "Gardens");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("Statue", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Theater", "Gardens");
			cards.add(card);
		}

		// forum
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.CLAY, 2);
		cost = new Cost(CostType.RESOURCE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Good.LOOM, 1);
		entitiesAndAmounts.put(Good.GLASS, 1);
		entitiesAndAmounts.put(Good.PRESS, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);
		card = new Card("Forum", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Trading Post", "Haven");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Forum", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Trading Post", "Haven");
			cards.add(card);

			if (numPlayers == 7) {
				card = new Card("Forum", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Trading Post",
						"Haven");
				cards.add(card);
			}
		}

		// caravansery
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		frequency.add(6);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 2);
		cost = new Cost(CostType.RESOURCE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(RawResource.LUMBER, 1);
		entitiesAndAmounts.put(RawResource.CLAY, 1);
		entitiesAndAmounts.put(RawResource.ORE, 1);
		entitiesAndAmounts.put(RawResource.STONE, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);
		card = new Card("Caravansery", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Marketplace",
				"Lighthouse");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Caravansery", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Marketplace",
					"Lighthouse");
			cards.add(card);

			if (numPlayers >= 6) {
				card = new Card("Caravansery", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Marketplace",
						"Lighthouse");
				cards.add(card);
			}
		}

		// vineyard
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		cost = new Cost(CostType.NONE, 0);
		effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.ALL, 1);
		card = new Card("Vineyard", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Vineyard", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);
		}

		// bazar
		if (numPlayers >= 4) {
			frequency = new ArrayList<Integer>();
			frequency.add(4);
			frequency.add(7);
			cost = new Cost(CostType.NONE, 0);
			effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS, Direction.ALL,
					2);
			card = new Card("Bazar", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);

			if (numPlayers >= 7) {
				card = new Card("Bazar", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "None", "None");
				cards.add(card);
			}
		}

		// walls
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.STONE, 3);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 2);
		card = new Card("Walls", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "Fortifications");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("Walls", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "Fortifications");
			cards.add(card);
		}

		// training ground
		if (numPlayers >= 4) {
			frequency = new ArrayList<Integer>();
			frequency.add(4);
			frequency.add(6);
			frequency.add(7);
			costs = new HashMap<Enum, Integer>();
			costs.put(RawResource.LUMBER, 1);
			costs.put(RawResource.ORE, 2);
			cost = new Cost(CostType.RESOURCE, costs);
			effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 2);
			card = new Card("Training Ground", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "Circus");
			cards.add(card);

			if (numPlayers >= 6) {
				card = new Card("Training Ground", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None",
						"Circus");
				cards.add(card);

				if (numPlayers == 7) {
					card = new Card("Training Ground", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None",
							"Circus");
					cards.add(card);
				}
			}
		}

		// stables
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.ORE, 1);
		costs.put(RawResource.CLAY, 1);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 2);
		card = new Card("Stables", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Apothecary", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Stables", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Apothecary", "None");
			cards.add(card);
		}

		// archery range
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 2);
		costs.put(RawResource.ORE, 1);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 2);
		card = new Card("Archery Range", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Workshop", "None");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Archery Range", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Workshop", "None");
			cards.add(card);
		}

		// dispensary
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.ORE, 2);
		costs.put(Good.GLASS, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.PROTRACTOR, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Dispensary", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Apothecary",
				"Arena/Lodge");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Dispensary", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Apothecary",
					"Arena/Lodge");
			cards.add(card);
		}

		// liboratory
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 2);
		costs.put(Good.PRESS, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.WHEEL, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Laboratory", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Workshop",
				"Siege Workshop/Observatory");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Laboratory", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Workshop",
					"Siege Workshop/Observatory");
			cards.add(card);
		}

		// library
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.STONE, 2);
		costs.put(Good.LOOM, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.TABLET, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Library", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Scriptorium",
				"Senate/University");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Library", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Scriptorium",
					"Senate/University");
			cards.add(card);
		}

		// school
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(Good.PRESS, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.TABLET, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("School", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "None", "Academy/Study");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("School", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "None", "Academy/Study");
			cards.add(card);
		}

		// courthouse
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.CLAY, 2);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 4);
		card = new Card("Courthouse", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Scriptorium", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Courthouse", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Scriptorium", "None");
			cards.add(card);
		}

		return cards;
	}
}
