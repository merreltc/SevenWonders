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
import dataStructures.EntityEffect.EntityType;
import dataStructures.EntityEffect.Good;
import dataStructures.EntityEffect.Resource;
import dataStructures.EntityEffect.Science;
import dataStructures.ValueEffect;
import dataStructures.Card.CardType;
import dataStructures.ValueEffect.AffectingEntity;
import dataStructures.ValueEffect.Value;
import json.JSONArray;
import json.JSONException;
import json.JSONObject;

public class SetUpDeckHandlerTest {

	@Test
	public void testCreateCards3Players() {
		int numPlayers = 3;
		ArrayList<Card> cards = new ArrayList<Card>();

		String jsonData = readFile("src/assets/age1cards.json");
		JSONObject jsonObj = new JSONObject(jsonData);
		JSONArray jarr = new JSONArray(jsonObj.getJSONArray("age1").toString());

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
			
			Card card;
			
			if(cardType.equals(CardType.GUILD)){
				card = new Card(cardName, cardType, cost, effect);
			}else{
				card = new Card(cardName, frequencyByNumPlayers, cardType, cost, effect);
			}
			
			cards.add(card);
		}
		
		
		ArrayList<Card> actual = SetUpDeckHandler.setUpDeckHandler.createDeck(Age.AGE1, numPlayers);
		
		for(int i = 0; i < actual.size(); i++) {
			assertEquals(cards.get(i).toString(), actual.get(i).toString());
		}
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
		}
		return effect;
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
		case "NONE":
			costType = CostType.NONE;
			cost = new Cost(costType, 0);
			break;
		case "RESOURCE":
			costType = CostType.RESOURCE;
			JSONArray costs = costObj.getJSONArray("cost");

			HashMap<Enum, Integer> resourceCosts = new HashMap<Enum, Integer>();

			for (int c = 0; c < costs.length(); c++) {
				JSONObject resource = costs.getJSONObject(c);
				String resourceType = resource.getString("Resource");
				int amount = resource.getInt("amount");
				Resource costResource = Resource.valueOf(resourceType);

				resourceCosts.put(costResource, amount);
			}

			cost = new Cost(costType, resourceCosts);
			break;
		default:
			costType = CostType.NONE;
			cost = new Cost(costType, 0);
			break;
		}
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
