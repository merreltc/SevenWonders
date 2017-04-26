package backendTests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import backend.SetUpDeckHandler;
import dataStructures.Card;
import dataStructures.Cost;
import dataStructures.Cost.CostType;
import dataStructures.Deck.Age;
import dataStructures.Deck;
import dataStructures.Effect;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect;
import dataStructures.MultiValueEffect;
import dataStructures.EntityEffect.EntityType;
import dataStructures.EntityEffect.Good;
import dataStructures.EntityEffect.Resource;
import dataStructures.EntityEffect.Science;
import dataStructures.ValueEffect;
import dataStructures.Card.CardType;
import dataStructures.ValueEffect.AffectingEntity;
import dataStructures.ValueEffect.Value;
import dataStructures.ValueEffect.ValueType;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;

public class SetUpDeckHandlerTest {

	@Test
	public void testCreateAge1Cards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = new ArrayList<Card>();

		String jsonData = readFile("src/assets/age1cards.json");
		createCards(numPlayers, cards, jsonData, "age1");
		
		
		ArrayList<Card> actual = SetUpDeckHandler.setUpDeckHandler.createDeck(Age.AGE1, numPlayers);
		
		for(int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}
	}
	
	@Test
	public void testCreateAge2Cards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = new ArrayList<Card>();

		String jsonData = readFile("src/assets/age2cards.json");
		createCards(numPlayers, cards, jsonData, "age2");
		
		
		ArrayList<Card> actual = SetUpDeckHandler.setUpDeckHandler.createDeck(Age.AGE2, numPlayers);
		
		for(int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}
		
	}
	
	@Test
	public void testCreateAge3Cards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = new ArrayList<Card>();

		String jsonData = readFile("src/assets/age3cards.json");
		createCards(numPlayers, cards, jsonData, "age3");
		
		
		ArrayList<Card> actual = SetUpDeckHandler.setUpDeckHandler.createDeck(Age.AGE3, numPlayers);
		
		for(int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}
		
	}
	
	@Test
	public void testAge2Cards3PlayersTempleHasNextAndPrevious() {
		int numPlayers = 3;
		ArrayList<Card> cards = new ArrayList<Card>();

		String jsonData = readFile("src/assets/age2cards.json");
		createCards(numPlayers, cards, jsonData, "age2");
		
		
		ArrayList<Card> actual = SetUpDeckHandler.setUpDeckHandler.createDeck(Age.AGE2, numPlayers);
		Card temple = actual.get(8);
		assertEquals("Temple", temple.getName());
		assertEquals("Pantheon", temple.getNextStructureName());
		assertEquals("Alter", temple.getPreviousStructureName());
	}
	
	@Test
	public void testVerifyCardInformationRawmaterialCoinCostEntityEffect() {
		int numPlayers = 3;
		ArrayList<Card> cards = new ArrayList<Card>();

		String jsonData = readFile("src/assets/age2cards.json");
		createCards(numPlayers, cards, jsonData, "age2");
		
		
		ArrayList<Card> actual = SetUpDeckHandler.setUpDeckHandler.createDeck(Age.AGE2, numPlayers);
		Card card = actual.get(0);
		EntityEffect effect = (EntityEffect) card.getEffect();
		HashMap<Enum, Integer> entitiesAndAmounts = effect.getEntities();
	
		assertEquals("Sawmill", card.getName());
		assertEquals(CostType.COIN, card.getCostType());
		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(EntityType.RESOURCE, effect.getEntityType());
		assertEquals(2, (int) entitiesAndAmounts.get(Resource.LUMBER));
		assertEquals("None", card.getNextStructureName());
		assertEquals("None", card.getPreviousStructureName());
	}
	
	@Test
	public void testVerifyCardInformationGoodNoCostEntityEffect() {
		int numPlayers = 3;
		ArrayList<Card> cards = new ArrayList<Card>();

		String jsonData = readFile("src/assets/age2cards.json");
		createCards(numPlayers, cards, jsonData, "age2");
		
		
		ArrayList<Card> actual = SetUpDeckHandler.setUpDeckHandler.createDeck(Age.AGE2, numPlayers);
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
	

	private void createCards(int numPlayers, ArrayList<Card> cards, String jsonData, String age) {
		JSONObject jsonObj = new JSONObject(jsonData);
		JSONArray jarr = new JSONArray(jsonObj.getJSONArray(age).toString());

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
	}

	private Effect parseEffect(JSONObject cardData) {
		JSONObject effectObj = cardData.getJSONObject("Effect");
		String effectStr = effectObj.getString("EffectType");
		EffectType effectTypeEnum;
		Effect effect = null;

		if(effectStr.equals("ENTITY")){
			effectTypeEnum = EffectType.ENTITY;
			String entityStr = effectObj.getString("EntityType");
			EntityType entityEnum = EntityType.valueOf(entityStr);
			HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();

			JSONArray entitiesJSON = effectObj.getJSONArray("entitiesAndAmounts");

			for (int entity = 0; entity < entitiesJSON.length(); entity++) {
				JSONObject entityToAdd = entitiesJSON.getJSONObject(entity);
				Set<String> entityKeys = entityToAdd.keySet();

				String[] entityKeysArr = entityKeys.toArray(new String[entityKeys.size()]);
				String entityType = entityKeysArr[1];
				int entityAmount;

				if(entityType.equals("Science")){
					entityAmount = entityToAdd.getInt("entityAmount");
					Science science = Science.valueOf(entityToAdd.getString(entityType));
					entitiesAndAmounts.put(science, entityAmount);
				}else if (entityType.equals("Good")){
					entityAmount = entityToAdd.getInt("entityAmount");
					Good good = Good.valueOf(entityToAdd.getString(entityType));
					entitiesAndAmounts.put(good, entityAmount);
				}else{
					entityAmount = entityToAdd.getInt("entityAmount");
					Resource resource = Resource.valueOf(entityToAdd.getString(entityType));
					entitiesAndAmounts.put(resource, entityAmount);
				}
			}
			
			effect = new EntityEffect(effectTypeEnum, entityEnum, entitiesAndAmounts);
		}else if (effectStr.equals("VALUE")){
			effectTypeEnum = EffectType.VALUE;

			try {
				String affecting = effectObj.getString("AffectingEntities");
				Value value = Value.valueOf(effectObj.getString("Value"));
				AffectingEntity affectingEntities = AffectingEntity.valueOf(affecting);
				int valueAmount = effectObj.getInt("valueAmount");
				
				if (affecting.equals("NONE")){
					effect = new ValueEffect(effectTypeEnum, value, affectingEntities, valueAmount);
				}else{				
					Direction direction = Direction.valueOf(effectObj.getString("Direction"));
					effect = new ValueEffect(effectTypeEnum, value, affectingEntities, direction, valueAmount);
				}
			} catch (JSONException exception) { //the affecting entities was an array
				JSONArray affecting = effectObj.getJSONArray("AffectingEntities");
				Value value = Value.valueOf(effectObj.getString("Value"));
				HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();
				
				for (int ae = 0; ae < affecting.length(); ae++){
					affectingEntities.put(AffectingEntity.valueOf(affecting.getString(ae)), 1);
				}

				effect = new ValueEffect(effectTypeEnum, value, affectingEntities);
			}
		}else{
			effectTypeEnum = EffectType.MULTIVALUE;
			
			String affecting = effectObj.getString("AffectingEntities");
			Value value = Value.valueOf(effectObj.getString("Value"));
			AffectingEntity affectingEntities = AffectingEntity.valueOf(affecting);
			JSONArray entitiesAndAmountsJSON = effectObj.getJSONArray("entitiesAndAmounts");
			HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
			
			for (int ae = 0; ae < entitiesAndAmountsJSON.length(); ae++){
				JSONObject entity = entitiesAndAmountsJSON.getJSONObject(ae);
				valuesAndAmounts.put(ValueType.valueOf(entity.getString("ValueType")), entity.getInt("entityAmount"));
			}
			
			Direction direction = Direction.valueOf(effectObj.getString("Direction"));
			effect = new MultiValueEffect(effectTypeEnum, value, affectingEntities, direction, valuesAndAmounts);
		}
		return effect;
	}

	private Cost parseCost(JSONObject cardData) {
		JSONObject costObj = cardData.getJSONObject("Cost");
		JSONArray costs;
		String costTypeS = costObj.getString("CostType");
		CostType costType;
		Cost cost;

		if(costTypeS.equals("COIN")){
			costType = CostType.COIN;
			int coinCost = costObj.getInt("coinCost");
			cost = new Cost(costType, coinCost);
		}else if (costTypeS.equals("RESOURCE")){
			costType = CostType.RESOURCE;
			cost = createResourceOrGoodCost(costObj, costType, "Resource");
		}else if (costTypeS.equals("GOOD")){
			costType = CostType.GOOD;
			cost = createResourceOrGoodCost(costObj, costType, "Good");
		}else if (costTypeS.equals("MULTITYPE")) {
			costType = CostType.MULTITYPE;
			cost = createMultiTypeCost(costObj, costType);
		}else{
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

}
