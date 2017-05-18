package backend.handlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
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

public class SetUpDeckHandler {

	public static final int NUM_OF_GUILD_CARDS = 10;

	public Deck createDeck(Age age, int numPlayers) {
		ArrayList<Card> cards = createCards(age, numPlayers);
		if (age == Age.AGE3) {
			cards = correctNumberOfGuildCards(cards, numPlayers);
		}
		return new Deck(age, cards);
	}

	public ArrayList<Card> correctNumberOfGuildCards(ArrayList<Card> cards, int numOfPlayers) {
		for (int i = 0; i < NUM_OF_GUILD_CARDS - numOfPlayers - 2; i++) {
			int indexToDelete = (int) Math.floor((Math.random() * (NUM_OF_GUILD_CARDS - i)));
			cards.remove(indexToDelete);
		}

		return cards;
	}

	public ArrayList<Card> createCards(Age age, int numPlayers) {
		ArrayList<Card> cards = new ArrayList<Card>();
		JSONArray jarr;

		jarr = chooseAgeFile(age);
		parseCardData(numPlayers, cards, jarr);

		return cards;
	}

	private JSONArray chooseAgeFile(Age age) {
		JSONArray jarr;
		if (age == Age.AGE1) {
			jarr = getAge1Info();
		} else if (age == Age.AGE2) {
			jarr = getAge2Info();
		} else {
			jarr = getAge3Info();
		}
		return jarr;
	}

	private JSONArray getAge1Info() {
		String jsonData = readFile("src/assets/age1cards.json");
		JSONObject jsonObj = new JSONObject(jsonData);
		JSONArray jarr = new JSONArray(jsonObj.getJSONArray("age1").toString());
		return jarr;
	}

	private JSONArray getAge2Info() {
		String jsonData = readFile("src/assets/age2cards.json");
		JSONObject jsonObj = new JSONObject(jsonData);
		JSONArray jarr = new JSONArray(jsonObj.getJSONArray("age2").toString());
		return jarr;
	}

	private JSONArray getAge3Info() {
		String jsonData = readFile("src/assets/age3cards.json");
		JSONObject jsonObj = new JSONObject(jsonData);
		JSONArray jarr = new JSONArray(jsonObj.getJSONArray("age3").toString());
		return jarr;
	}

	private void parseCardData(int numPlayers, ArrayList<Card> cards, JSONArray jarr) {
		for (int i = 0; i < jarr.length(); i++) {
			ArrayList<Integer> frequencyByNumPlayers = new ArrayList<Integer>();
			JSONObject cardData = populateFrequencyAndGetCardData(jarr, i, frequencyByNumPlayers);

			Card card = createSingleCard(frequencyByNumPlayers, cardData);
			for (Integer numOfFrequencyByNumPlayers : frequencyByNumPlayers) {
				if (numPlayers >= numOfFrequencyByNumPlayers) {
					cards.add(card);
				}
			}
		}
	}

	private JSONObject populateFrequencyAndGetCardData(JSONArray jarr, int i,
			ArrayList<Integer> frequencyByNumPlayers) {
		JSONObject cardData = jarr.getJSONObject(i);
		JSONArray frequencyByNumPlayersJSON = cardData.getJSONArray("frequencyByNumPlayers");

		for (int j = 0; j < frequencyByNumPlayersJSON.length(); j++) {
			frequencyByNumPlayers.add(frequencyByNumPlayersJSON.getInt(j));
		}
		return cardData;
	}

	private Card createSingleCard(ArrayList<Integer> frequencyByNumPlayers, JSONObject cardData) {
		CardType cardType = CardType.valueOf(cardData.getString("cardType"));
		String cardName = cardData.getString("cardName");
		Cost cost = parseCost(cardData);
		Effect effect = parseEffect(cardData);
		String previousStructure = cardData.getString("Previous");
		String nextStructure = cardData.getString("Next");

		if (cardType.equals(CardType.GUILD)) {
			return new Card(cardName, cardType, cost, effect);
		}
		return new Card(cardName, frequencyByNumPlayers, cardType, cost, effect, previousStructure, nextStructure);
	}

	private String readFile(String filename) {
		String result = "";
		try {
			StringBuilder sb = readLines(filename);
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private StringBuilder readLines(String filename) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		return sb;
	}

	private Cost parseCost(JSONObject cardData) {
		JSONObject costObj = cardData.getJSONObject("Cost");
		String costTypeS = costObj.getString("CostType");
		Cost cost;
		cost = checkCostTypeAndCreateCost(costObj, costTypeS);
		return cost;
	}

	private Cost checkCostTypeAndCreateCost(JSONObject costObj, String costTypeS) {
		if (costTypeS.equals("COIN")) {
			int coinCost = costObj.getInt("coinCost");
			return new Cost(CostType.COIN, coinCost);
		} else if (costTypeS.equals("RESOURCE")) {
			return createResourceOrGoodCost(costObj, CostType.RESOURCE, "Resource");
		} else if (costTypeS.equals("GOOD")) {
			return createResourceOrGoodCost(costObj, CostType.GOOD, "Good");
		} else if (costTypeS.equals("MULTITYPE")) {
			return createMultiTypeCost(costObj, CostType.MULTITYPE);
		}

		return new Cost(CostType.NONE, 0);
	}

	private Cost createMultiTypeCost(JSONObject costObj, CostType costType) {
		JSONArray costs = costObj.getJSONArray("cost");
		HashMap<Enum, Integer> givenCosts = new HashMap<Enum, Integer>();
		populateGivenMultiTypeCosts(costs, givenCosts);
		return new Cost(costType, givenCosts);
	}

	private void populateGivenMultiTypeCosts(JSONArray costs, HashMap<Enum, Integer> givenCosts) {
		for (int c = 0; c < costs.length(); c++) {
			JSONObject costValueObj = costs.getJSONObject(c);
			String type = "";
			try {
				getCostAsResource(givenCosts, costValueObj);
			} catch (JSONException exception) {
				getCostAsGood(givenCosts, costValueObj);
			}
		}
	}

	private void getCostAsResource(HashMap<Enum, Integer> givenCosts, JSONObject costValueObj) {
		String type;
		type = costValueObj.getString("Resource");
		int amount = costValueObj.getInt("amount");
		RawResource costResource = RawResource.valueOf(type);
		givenCosts.put(costResource, amount);
	}

	private void getCostAsGood(HashMap<Enum, Integer> givenCosts, JSONObject costValueObj) {
		String type;
		type = costValueObj.getString("Good");
		int amount = costValueObj.getInt("amount");
		Good costGood = Good.valueOf(type);
		givenCosts.put(costGood, amount);
	}

	private Cost createResourceOrGoodCost(JSONObject costObj, CostType costType, String resourceTypeKey) {
		JSONArray costs = costObj.getJSONArray("cost");
		HashMap<Enum, Integer> givenCosts = new HashMap<Enum, Integer>();
		populateResourceOrGoodGosts(resourceTypeKey, costs, givenCosts);

		return new Cost(costType, givenCosts);
	}

	private void populateResourceOrGoodGosts(String resourceTypeKey, JSONArray costs,
			HashMap<Enum, Integer> givenCosts) {
		for (int c = 0; c < costs.length(); c++) {
			JSONObject resource = costs.getJSONObject(c);
			String costTypeValue = resource.getString(resourceTypeKey);
			int amount = resource.getInt("amount");

			if (resourceTypeKey.equals("Resource")) {
				RawResource costResource = RawResource.valueOf(costTypeValue);
				givenCosts.put(costResource, amount);
			} else {
				Good costGood = Good.valueOf(costTypeValue);
				givenCosts.put(costGood, amount);
			}
		}
	}

	private Effect parseEffect(JSONObject cardData) {
		JSONObject effectObj = cardData.getJSONObject("Effect");
		String effectStr = effectObj.getString("EffectType");
		return createEffectBasedOnType(effectObj, effectStr);
	}

	private Effect createEffectBasedOnType(JSONObject effectObj, String effectStr) {
		Effect effect;
		if (effectStr.equals("ENTITY")) {
			String entityStr = effectObj.getString("EntityType");
			EntityType entityEnum = EntityType.valueOf(entityStr);
			effect = createEntityEffect(effectObj, EffectType.ENTITY, entityEnum);
		} else if (effectStr.equals("VALUE")) {
			effect = createValueEffect(effectObj, EffectType.VALUE);
		} else {
			effect = createMultiValueEffect(effectObj, EffectType.MULTIVALUE);
		}
		return effect;
	}

	private Effect createMultiValueEffect(JSONObject effectObj, EffectType effectTypeEnum) {
		String affecting = effectObj.getString("AffectingEntities");
		Value value = Value.valueOf(effectObj.getString("Value"));
		AffectingEntity affectingEntities = AffectingEntity.valueOf(affecting);
		JSONArray entitiesAndAmounts = effectObj.getJSONArray("entitiesAndAmounts");
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();

		for (int ae = 0; ae < entitiesAndAmounts.length(); ae++) {
			JSONObject entity = entitiesAndAmounts.getJSONObject(ae);
			valuesAndAmounts.put(ValueType.valueOf(entity.getString("ValueType")), entity.getInt("entityAmount"));
		}
		Direction direction = Direction.valueOf(effectObj.getString("Direction"));
		return new MultiValueEffect(effectTypeEnum, value, affectingEntities, direction, valuesAndAmounts);
	}

	private Effect createValueEffect(JSONObject effectObj, EffectType effectTypeEnum) {
		Effect effect;
		try {
			effect = tryGettingEntitiesAsObject(effectObj, effectTypeEnum);
		} catch (JSONException exception) {
			effect = tryGettingEntitiesAsArray(effectObj, effectTypeEnum);
		}
		return effect;
	}

	private Effect tryGettingEntitiesAsObject(JSONObject effectObj, EffectType effectTypeEnum) {
		String affecting = effectObj.getString("AffectingEntities");
		Value value = Value.valueOf(effectObj.getString("Value"));
		AffectingEntity affectingEntities = AffectingEntity.valueOf(affecting);
		int valueAmount = effectObj.getInt("valueAmount");

		if (affecting.equals("NONE")) {
			return new ValueEffect(effectTypeEnum, value, affectingEntities, valueAmount);
		}

		Direction direction = Direction.valueOf(effectObj.getString("Direction"));
		return new ValueEffect(effectTypeEnum, value, affectingEntities, direction, valueAmount);
	}

	private Effect tryGettingEntitiesAsArray(JSONObject effectObj, EffectType effectTypeEnum) {
		JSONArray affecting = effectObj.getJSONArray("AffectingEntities");
		Value value = Value.valueOf(effectObj.getString("Value"));
		HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();

		for (int ae = 0; ae < affecting.length(); ae++) {
			affectingEntities.put(AffectingEntity.valueOf(affecting.getString(ae)), 1);
		}

		return new ValueEffect(effectTypeEnum, value, affectingEntities);
	}

	private Effect createEntityEffect(JSONObject effectObj, EffectType effectTypeEnum, EntityType entityEnum) {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		JSONArray entitiesJSON = effectObj.getJSONArray("entitiesAndAmounts");

		for (int entity = 0; entity < entitiesJSON.length(); entity++) {
			JSONObject entityToAdd = entitiesJSON.getJSONObject(entity);
			Set<String> entityKeys = entityToAdd.keySet();
			String[] entityKeysArr = entityKeys.toArray(new String[entityKeys.size()]);
			String entityType = entityKeysArr[1];

			addEntity(entitiesAndAmounts, entityToAdd, entityType);
		}

		return new EntityEffect(effectTypeEnum, entityEnum, entitiesAndAmounts);
	}

	private void addEntity(HashMap<Enum, Integer> entitiesAndAmounts, JSONObject entityToAdd, String entityType) {
		int entityAmount = entityToAdd.getInt("entityAmount");
		if (entityType.equals("Science")) {
			Science science = Science.valueOf(entityToAdd.getString(entityType));
			entitiesAndAmounts.put(science, entityAmount);
		} else if (entityType.equals("Good")) {
			Good good = Good.valueOf(entityToAdd.getString(entityType));
			entitiesAndAmounts.put(good, entityAmount);
		} else {
			RawResource resource = RawResource.valueOf(entityToAdd.getString(entityType));
			entitiesAndAmounts.put(resource, entityAmount);
		}
	}
}