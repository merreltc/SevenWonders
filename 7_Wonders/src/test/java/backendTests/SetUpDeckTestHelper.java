package backendTests;

import java.util.ArrayList;
import java.util.HashMap;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Resource;
import constants.GeneralEnums.Science;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.MultiValueEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.ValueEffect.ValueType;

public final class SetUpDeckTestHelper {
	public static ArrayList<Card> createAge1Cards(int numPlayers) {
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

	public static ArrayList<Card> createAge2Cards(int numPlayers) {
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

	public static ArrayList<Card> createAge3Cards(int numPlayers) {
		ArrayList<Card> cards = new ArrayList<Card>();
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		Cost cost;
		Effect effect;
		Card card;

		// workers guild
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.CLAY, 1);
		costs.put(RawResource.ORE, 2);
		costs.put(RawResource.STONE, 1);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.RAWRESOURCES, Direction.NEIGHBORS, 1);
		card = new Card("Workers Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// craftsmens guild
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.ORE, 2);
		costs.put(RawResource.STONE, 2);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.MANUFACTUREDGOODS, Direction.NEIGHBORS,
				2);
		card = new Card("Craftsmens Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// traders guild
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(Good.PRESS, 1);
		costs.put(Good.GLASS, 1);
		cost = new Cost(CostType.GOOD, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.COMMERCIALSTRUCTURES,
				Direction.NEIGHBORS, 1);
		card = new Card("Traders Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// philosophers guild
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(Good.PRESS, 1);
		costs.put(RawResource.CLAY, 3);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.SCIENTIFICSTRUCTURES,
				Direction.NEIGHBORS, 1);
		card = new Card("Philosophers Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// Spies guild
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		costs.put(RawResource.LUMBER, 3);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.MILITARYSTRUCTURES, Direction.NEIGHBORS,
				1);
		card = new Card("Spies Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// strategists guild
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.ORE, 2);
		costs.put(RawResource.STONE, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.MILITARYSTRUCTURES, Direction.NEIGHBORS,
				1);
		card = new Card("Strategists Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// shipowners guild
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		costs.put(Good.PRESS, 1);
		costs.put(RawResource.LUMBER, 3);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(AffectingEntity.RAWRESOURCES, 1);
		entitiesAndAmounts.put(AffectingEntity.MANUFACTUREDGOODS, 1);
		entitiesAndAmounts.put(AffectingEntity.GUILD, 1);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, entitiesAndAmounts);
		card = new Card("Shipowners Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// Scientists guild
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.PRESS, 1);
		costs.put(RawResource.LUMBER, 2);
		costs.put(RawResource.ORE, 2);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(AffectingEntity.SCIENTIFICSTRUCTURES, 1);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, entitiesAndAmounts);
		card = new Card("Scientists Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// magistrates guild
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.LUMBER, 3);
		costs.put(RawResource.STONE, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.CIVILIANSTRUCTURES, Direction.NEIGHBORS,
				1);
		card = new Card("Magistrates Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// builders guild
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.CLAY, 2);
		costs.put(RawResource.STONE, 2);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.WONDERLEVEL, Direction.ALL, 1);
		card = new Card("Builders Guild", CardType.GUILD, cost, effect);
		cards.add(card);

		// pantheon
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		costs.put(Good.LOOM, 1);
		costs.put(Good.PRESS, 1);
		costs.put(RawResource.CLAY, 2);
		costs.put(RawResource.ORE, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 7);
		card = new Card("Pantheon", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Temple", "None");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Pantheon", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Temple", "None");
			cards.add(card);
		}

		// gardens
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.CLAY, 2);
		costs.put(RawResource.LUMBER, 1);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 5);
		card = new Card("Gardens", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Statue", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Gardens", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "Statue", "None");
			cards.add(card);
		}

		// Town hall
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		frequency.add(6);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.ORE, 1);
		costs.put(RawResource.STONE, 2);
		costs.put(Good.GLASS, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 6);
		card = new Card("Town Hall", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Town Hall", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);

			if (numPlayers >= 6) {
				card = new Card("Town Hall", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "None");
				cards.add(card);
			}
		}

		// palace
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		costs.put(Good.LOOM, 1);
		costs.put(Good.PRESS, 1);
		costs.put(RawResource.STONE, 1);
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.CLAY, 1);
		costs.put(RawResource.ORE, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 8);
		card = new Card("Palace", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("Palace", frequency, CardType.CIVILIANSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);
		}

		// haven
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.ORE, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES,
				Direction.SELF, entitiesAndAmounts);
		card = new Card("Haven", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Forum", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Haven", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Forum", "None");
			cards.add(card);
		}

		// lighthouse
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.STONE, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.COMMERCIALSTRUCTURES,
				Direction.SELF, entitiesAndAmounts);
		card = new Card("Lighthouse", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Caravansery", "None");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Lighthouse", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Caravansery", "None");
			cards.add(card);
		}

		// chamber of commerce
		if (numPlayers >= 4) {
			frequency = new ArrayList<Integer>();
			frequency.add(4);
			frequency.add(6);
			costs = new HashMap<Enum, Integer>();
			costs.put(Good.PRESS, 1);
			costs.put(RawResource.CLAY, 2);
			cost = new Cost(CostType.MULTITYPE, costs);
			entitiesAndAmounts = new HashMap<Enum, Integer>();
			entitiesAndAmounts.put(ValueType.COIN, 2);
			entitiesAndAmounts.put(ValueType.VICTORYPOINT, 2);
			effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS,
					Direction.SELF, entitiesAndAmounts);
			card = new Card("Chamber Of Commerce", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Caravansery",
					"None");
			cards.add(card);

			if (numPlayers >= 6) {
				card = new Card("Chamber Of Commerce", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect,
						"Caravansery", "None");
				cards.add(card);
			}
		}

		// arena
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.ORE, 1);
		costs.put(RawResource.STONE, 2);
		cost = new Cost(CostType.RESOURCE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 3);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.WONDERLEVEL,
				Direction.SELF, entitiesAndAmounts);
		card = new Card("Arena", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Dispensary", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Arena", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Dispensary", "None");
			cards.add(card);

			if (numPlayers == 7) {
				card = new Card("Arena", frequency, CardType.COMMERCIALSTRUCTURE, cost, effect, "Dispensary", "None");
				cards.add(card);
			}
		}

		// Fortifications
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.STONE, 1);
		costs.put(RawResource.ORE, 3);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 3);
		card = new Card("Fortifications", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Walls", "None");
		cards.add(card);

		if (numPlayers == 7) {
			card = new Card("Fortifications", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Walls", "None");
			cards.add(card);
		}

		// circus
		if (numPlayers >= 4) {
			frequency = new ArrayList<Integer>();
			frequency.add(4);
			frequency.add(5);
			frequency.add(6);
			costs = new HashMap<Enum, Integer>();
			costs.put(RawResource.STONE, 3);
			costs.put(RawResource.ORE, 1);
			cost = new Cost(CostType.RESOURCE, costs);
			effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 3);
			card = new Card("Circus", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Training Ground", "None");
			cards.add(card);

			if (numPlayers >= 5) {
				card = new Card("Circus", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Training Ground",
						"None");
				cards.add(card);

				if (numPlayers >= 6) {
					card = new Card("Circus", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Training Ground",
							"None");
					cards.add(card);
				}
			}
		}

		// arsenal
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 2);
		costs.put(RawResource.ORE, 1);
		costs.put(Good.LOOM, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 3);
		card = new Card("Arsenal", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("Arsenal", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");
			cards.add(card);

			if (numPlayers == 7) {
				card = new Card("Arsenal", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "None", "None");
				cards.add(card);
			}
		}

		// Siege workshop
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.CLAY, 3);
		cost = new Cost(CostType.RESOURCE, costs);
		effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 3);
		card = new Card("Siege Workshop", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Walls", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Siege Workshop", frequency, CardType.MILITARYSTRUCTURE, cost, effect, "Walls", "None");
			cards.add(card);
		}

		// lodge
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(6);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.CLAY, 1);
		costs.put(Good.LOOM, 1);
		costs.put(Good.PRESS, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.PROTRACTOR, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Lodge", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Dispensary", "None");
		cards.add(card);

		if (numPlayers >= 6) {
			card = new Card("Lodge", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Dispensary", "None");
			cards.add(card);
		}

		// observatory
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.ORE, 1);
		costs.put(Good.GLASS, 1);
		costs.put(Good.LOOM, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.WHEEL, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Observatory", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Laboratory", "None");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("Observatory", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Laboratory", "None");
			cards.add(card);
		}

		// university
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(4);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(Good.PRESS, 1);
		costs.put(Good.GLASS, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.TABLET, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("University", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Library", "None");
		cards.add(card);

		if (numPlayers >= 4) {
			card = new Card("University", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "Library", "None");
			cards.add(card);
		}

		// academy
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(7);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.STONE, 3);
		costs.put(Good.GLASS, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.PROTRACTOR, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Academy", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "School", "None");
		cards.add(card);

		if (numPlayers >= 7) {
			card = new Card("Academy", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "School", "None");
			cards.add(card);
		}

		// study
		frequency = new ArrayList<Integer>();
		frequency.add(3);
		frequency.add(5);
		costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(Good.PRESS, 1);
		costs.put(Good.LOOM, 1);
		cost = new Cost(CostType.MULTITYPE, costs);
		entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.WHEEL, 1);
		effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		card = new Card("Study", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "School", "None");
		cards.add(card);

		if (numPlayers >= 5) {
			card = new Card("Study", frequency, CardType.SCIENTIFICSTRUCTURE, cost, effect, "School", "None");
			cards.add(card);
		}

		return cards;
	}
}
