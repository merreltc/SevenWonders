package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import dataStructures.Card;
import dataStructures.Cost;
import dataStructures.Effect;
import dataStructures.EntityEffect;
import dataStructures.ValueEffect;
import dataStructures.Card.CardType;
import dataStructures.Cost.CostType;
import dataStructures.Deck.Age;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect.EntityType;
import dataStructures.EntityEffect.Good;
import dataStructures.EntityEffect.Resource;
import dataStructures.EntityEffect.Science;
import dataStructures.MultiValueEffect;
import dataStructures.ValueEffect.AffectingEntity;
import dataStructures.ValueEffect.Value;
import dataStructures.ValueEffect.ValueType;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;

public class SetUpDeckHandler {
	public static SetUpDeckHandler setUpDeckHandler = new SetUpDeckHandler();

	public ArrayList<Card> createDeck(Age age, int numPlayers) {
		ArrayList<Card> cards = new ArrayList<Card>();

		String jsonData;
		JSONObject jsonObj;
		JSONArray jarr;
		
		switch (age){
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
			
			if(cardType.equals(CardType.GUILD)){
				card = new Card(cardName, cardType, cost, effect);
			}else{
				card = new Card(cardName, frequencyByNumPlayers, cardType, cost, effect, previousStructure, nextStructure);
			}
			
			for(Integer numOfFrequencyByNumPlayers: frequencyByNumPlayers){
				if(numPlayers >= numOfFrequencyByNumPlayers){
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
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	private Cost parseCost(JSONObject cardData) {
		JSONObject costObj = cardData.getJSONObject("Cost");
		String costTypeS = costObj.getString("CostType");
		CostType costType;
		Cost cost;

		switch (costTypeS) {
		case "COIN":
			costType = CostType.COIN;
			int coinCost = costObj.getInt("coinCost");
			cost = new Cost(costType, coinCost);
			break;
		case "RESOURCE":
			costType = CostType.RESOURCE;
			cost = createResourceOrGoodCost(costObj, costType, "Resource");
			break;
		case "GOOD":
			costType = CostType.GOOD;
			cost = createResourceOrGoodCost(costObj, costType, "Good");
			break;
		case "MULTITYPE":
			costType = CostType.MULTITYPE;
			cost = createMultiTypeCost(costObj, costType);
			break;
		default:
			costType = CostType.NONE;
			cost = new Cost(costType, 0);
			break;
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
			try{
				type = costValueObj.getString("Resource");
				
				int amount = costValueObj.getInt("amount");
				Resource costResource = Resource.valueOf(type);

				givenCosts.put(costResource, amount);
			}catch(JSONException exception){
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
			
			if(resourceTypeKey.equals("Resource")){
				Resource costResource = Resource.valueOf(costTypeValue);
				givenCosts.put(costResource, amount);
			}else{
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

		switch (effectStr) {
		case "ENTITY":
			effectTypeEnum = EffectType.ENTITY;
			String entityStr = effectObj.getString("EntityType");
			EntityType entityEnum = EntityType.valueOf(entityStr);
			effect = createEntityEffect(effectObj, effectTypeEnum, entityEnum);
			break;
		case "VALUE":
			effectTypeEnum = EffectType.VALUE;

			try {
				String affecting = effectObj.getString("AffectingEntities");
				Value value = Value.valueOf(effectObj.getString("Value"));
				AffectingEntity affectingEntities = AffectingEntity.valueOf(affecting);
				int valueAmount = effectObj.getInt("valueAmount");
				
				if (affecting.equals("NONE")){
					effect = new ValueEffect(effectTypeEnum, value, affectingEntities, valueAmount);
					break;
				}
				
				Direction direction = Direction.valueOf(effectObj.getString("Direction"));
				effect = new ValueEffect(effectTypeEnum, value, affectingEntities, direction, valueAmount);
				break;
			} catch (JSONException exception) { //the affecting entities was an array
				JSONArray affecting = effectObj.getJSONArray("AffectingEntities");
				Value value = Value.valueOf(effectObj.getString("Value"));
				HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();
				
				for (int ae = 0; ae < affecting.length(); ae++){
					affectingEntities.put(AffectingEntity.valueOf(affecting.getString(ae)), 1);
				}

				effect = new ValueEffect(effectTypeEnum, value, affectingEntities);
			}

			break;
		case "MULTIVALUE":
			effectTypeEnum = EffectType.MULTIVALUE;
			
			String affecting = effectObj.getString("AffectingEntities");
			Value value = Value.valueOf(effectObj.getString("Value"));
			AffectingEntity affectingEntities = AffectingEntity.valueOf(affecting);
			JSONArray entitiesAndAmounts = effectObj.getJSONArray("entitiesAndAmounts");
			HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
			
			for (int ae = 0; ae < entitiesAndAmounts.length(); ae++){
				JSONObject entity = entitiesAndAmounts.getJSONObject(ae);
				valuesAndAmounts.put(ValueType.valueOf(entity.getString("ValueType")), entity.getInt("entityAmount"));
			}
			Direction direction = Direction.valueOf(effectObj.getString("Direction"));
			effect = new MultiValueEffect(effectTypeEnum, value, affectingEntities, direction, valuesAndAmounts);
			break;
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

			String entityType = entityKeys.iterator().next();
			int entityAmount;

			switch (entityType) {
			case "Science":
				entityAmount = entityToAdd.getInt("entityAmount");
				Science science = Science.valueOf(entityToAdd.getString(entityType));
				entitiesAndAmounts.put(science, entityAmount);
				break;
			case "Good":
				entityAmount = entityToAdd.getInt("entityAmount");
				Good good = Good.valueOf(entityToAdd.getString(entityType));
				entitiesAndAmounts.put(good, entityAmount);
			case "Resource":
				entityAmount = entityToAdd.getInt("entityAmount");
				Resource resource = Resource.valueOf(entityToAdd.getString(entityType));
				entitiesAndAmounts.put(resource, entityAmount);
				break;
			default:
				break;
			}
		}

		effect = new EntityEffect(effectTypeEnum, entityEnum, entitiesAndAmounts);
		return effect;
	}
}
