package backend.handlers;

import java.io.BufferedReader;
import java.io.FileReader;
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
		if (age == Age.AGE3){
			cards = correctNumberOfGuildCards(cards, numPlayers);
		}
		return new Deck(age, cards);
	}
	
	public ArrayList<Card> correctNumberOfGuildCards(ArrayList<Card> cards, int numOfPlayers){
		
		for (int i = 0; i < NUM_OF_GUILD_CARDS - numOfPlayers - 2; i++){
			int indexToDelete = (int) (Math.random() * (NUM_OF_GUILD_CARDS - i));
			cards.remove(indexToDelete);
		}
		
		return cards;
	}

	public ArrayList<Card> createCards(Age age, int numPlayers) {
		ArrayList<Card> cards = new ArrayList<Card>();

		String jsonData;
		JSONObject jsonObj;
		JSONArray jarr;

		switch (age) {
		case AGE2:
			jsonData = readFile("src/assets/age2cards.json");
			jsonObj = new JSONObject(jsonData);
			jarr = new JSONArray(jsonObj.getJSONArray("age2").toString());
			break;
		case AGE3:
			jsonData = readFile("src/assets/age3cards.json");
			jsonObj = new JSONObject(jsonData);
			jarr = new JSONArray(jsonObj.getJSONArray("age3").toString());
			break;
		default:
			jsonData = readFile("src/assets/age1cards.json");
			jsonObj = new JSONObject(jsonData);
			jarr = new JSONArray(jsonObj.getJSONArray("age1").toString());
			break;
		}

		for (int i = 0; i < jarr.length(); i++) {
			JSONObject cardData = jarr.getJSONObject(i);
			String cardName = cardData.getString("cardName");

			JSONArray frequencyByNumPlayersJSON = cardData.getJSONArray("frequencyByNumPlayers");

			ArrayList<Integer> frequencyByNumPlayers = new ArrayList<Integer>();
			for (int j = 0; j < frequencyByNumPlayersJSON.length(); j++) {
				frequencyByNumPlayers.add(frequencyByNumPlayersJSON.getInt(j));
			}

			CardType cardType = CardType.valueOf(cardData.getString("cardType"));

			Cost cost = parseCost(cardData);
			Effect effect = parseEffect(cardData);
			String previousStructure = cardData.getString("Previous");
			String nextStructure = cardData.getString("Next");

			Card card;

			if (cardType.equals(CardType.GUILD)) {
				card = new Card(cardName, cardType, cost, effect);
			} else {
				card = new Card(cardName, frequencyByNumPlayers, cardType, cost, effect, previousStructure,
						nextStructure);
			}

			for (Integer numOfFrequencyByNumPlayers : frequencyByNumPlayers) {
				if (numPlayers >= numOfFrequencyByNumPlayers) {
					cards.add(card);
				}
			}
		}

		return cards;
	}

	private String readFile(String filename) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	private Cost parseCost(JSONObject cardData) {
		JSONObject costObj = cardData.getJSONObject("Cost");
		String costTypeS = costObj.getString("CostType");
		CostType costType;
		Cost cost;

		if (costTypeS.equals("COIN")) {
			costType = CostType.COIN;
			int coinCost = costObj.getInt("coinCost");
			cost = new Cost(costType, coinCost);
		} else if (costTypeS.equals("RESOURCE")) {
			costType = CostType.RESOURCE;
			cost = createResourceOrGoodCost(costObj, costType, "Resource");
		} else if (costTypeS.equals("GOOD")) {
			costType = CostType.GOOD;
			cost = createResourceOrGoodCost(costObj, costType, "Good");
		} else if (costTypeS.equals("MULTITYPE")) {
			costType = CostType.MULTITYPE;
			cost = createMultiTypeCost(costObj, costType);
		} else {
			costType = CostType.NONE;
			cost = new Cost(costType, 0);
		}
		return cost;
	}

	private Cost createMultiTypeCost(JSONObject costObj, CostType costType) {
		JSONArray costs = costObj.getJSONArray("cost");
		Cost cost;

		HashMap<Enum, Integer> givenCosts = new HashMap<Enum, Integer>();

		for (int c = 0; c < costs.length(); c++) {
			JSONObject costValueObj = costs.getJSONObject(c);
			String type = "";
			try {
				type = costValueObj.getString("Resource");

				int amount = costValueObj.getInt("amount");
				RawResource costResource = RawResource.valueOf(type);

				givenCosts.put(costResource, amount);
			} catch (JSONException exception) {
				type = costValueObj.getString("Good");
				int amount = costValueObj.getInt("amount");
				Good costGood = Good.valueOf(type);

				givenCosts.put(costGood, amount);
			}
		}

		cost = new Cost(costType, givenCosts);
		return cost;
	}

	private Cost createResourceOrGoodCost(JSONObject costObj, CostType costType, String resourceTypeKey) {
		JSONArray costs = costObj.getJSONArray("cost");
		Cost cost;

		HashMap<Enum, Integer> givenCosts = new HashMap<Enum, Integer>();

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

		cost = new Cost(costType, givenCosts);
		return cost;
	}

	private Effect parseEffect(JSONObject cardData) {
		JSONObject effectObj = cardData.getJSONObject("Effect");
		String effectStr = effectObj.getString("EffectType");
		EffectType effectTypeEnum;
		Effect effect = null;

		if (effectStr.equals("ENTITY")) {
			effectTypeEnum = EffectType.ENTITY;
			String entityStr = effectObj.getString("EntityType");
			EntityType entityEnum = EntityType.valueOf(entityStr);
			effect = createEntityEffect(effectObj, effectTypeEnum, entityEnum);
		} else if (effectStr.equals("VALUE")) {
			effectTypeEnum = EffectType.VALUE;
			effect = createValueEffect(effectObj, effectTypeEnum);
		} else {
			effectTypeEnum = EffectType.MULTIVALUE;
			effect = createMultiValueEffect(effectObj, effectTypeEnum);
		}
		return effect;
	}

	private Effect createMultiValueEffect(JSONObject effectObj, EffectType effectTypeEnum) {
		Effect effect;
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
		effect = new MultiValueEffect(effectTypeEnum, value, affectingEntities, direction, valuesAndAmounts);
		return effect;
	}

	private Effect createValueEffect(JSONObject effectObj, EffectType effectTypeEnum) {
		Effect effect;
		try {
			String affecting = effectObj.getString("AffectingEntities");
			Value value = Value.valueOf(effectObj.getString("Value"));
			AffectingEntity affectingEntities = AffectingEntity.valueOf(affecting);
			int valueAmount = effectObj.getInt("valueAmount");

			if (affecting.equals("NONE")) {
				effect = new ValueEffect(effectTypeEnum, value, affectingEntities, valueAmount);
			} else {
				Direction direction = Direction.valueOf(effectObj.getString("Direction"));
				effect = new ValueEffect(effectTypeEnum, value, affectingEntities, direction, valueAmount);
			}
		} catch (JSONException exception) { // the affecting entities was an
											// array
			JSONArray affecting = effectObj.getJSONArray("AffectingEntities");
			Value value = Value.valueOf(effectObj.getString("Value"));
			HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();

			for (int ae = 0; ae < affecting.length(); ae++) {
				affectingEntities.put(AffectingEntity.valueOf(affecting.getString(ae)), 1);
			}

			effect = new ValueEffect(effectTypeEnum, value, affectingEntities);
		}
		return effect;
	}

	private Effect createEntityEffect(JSONObject effectObj, EffectType effectTypeEnum, EntityType entityEnum) {
		Effect effect;
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();

		JSONArray entitiesJSON = effectObj.getJSONArray("entitiesAndAmounts");

		for (int entity = 0; entity < entitiesJSON.length(); entity++) {
			JSONObject entityToAdd = entitiesJSON.getJSONObject(entity);
			Set<String> entityKeys = entityToAdd.keySet();

			String[] entityKeysArr = entityKeys.toArray(new String[entityKeys.size()]);
			String entityType = entityKeysArr[1];
			int entityAmount;

			if (entityType.equals("Science")) {
				entityAmount = entityToAdd.getInt("entityAmount");
				Science science = Science.valueOf(entityToAdd.getString(entityType));
				entitiesAndAmounts.put(science, entityAmount);
			} else if (entityType.equals("Good")) {
				entityAmount = entityToAdd.getInt("entityAmount");
				Good good = Good.valueOf(entityToAdd.getString(entityType));
				entitiesAndAmounts.put(good, entityAmount);
			} else {
				entityAmount = entityToAdd.getInt("entityAmount");
				RawResource resource = RawResource.valueOf(entityToAdd.getString(entityType));
				entitiesAndAmounts.put(resource, entityAmount);
			}
		}

		effect = new EntityEffect(effectTypeEnum, entityEnum, entitiesAndAmounts);
		return effect;
	}
}