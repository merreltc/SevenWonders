package backend.factories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Science;
import constants.GeneralEnums.Side;
import dataStructures.gameMaterials.AbilityEffect;
import dataStructures.gameMaterials.AbilityEffect.Ability;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.Wonder;
import dataStructures.gameMaterials.Wonder.WonderType;
import exceptions.NoSuchLevelException;

public class LevelBuilder {
	private static final String NOT_INITIALIZED = "File not initialized";
	Wonder wonder;
	String jsonData = NOT_INITIALIZED;

	public LevelBuilder(Wonder wonder) {
		this.wonder = wonder;
	}

	public Level buildLevel(int priority) {
		return createLevel(priority);
	}

	public Level createLevel(int priority) {
		JSONObject jsonObj;
		JSONArray jarr;

		if (this.jsonData == NOT_INITIALIZED) {
			this.jsonData = readFile("src/assets/wonders.json");
		}

		jsonObj = new JSONObject(jsonData);
		jarr = new JSONArray(jsonObj.getJSONArray("wonders").toString());

		for (int i = 0; i < jarr.length(); i++) {
			JSONObject wonderData = jarr.getJSONObject(i);
			if (!compareWonder(wonderData)) {
				continue;
			} else {
				return getLevel(wonderData, priority);
			}
		}

		throw new NoSuchLevelException("This level does not exist");
	}

	private boolean compareWonder(JSONObject wonderData) {
		WonderType wonderType = WonderType.valueOf(wonderData.getString("wonderType"));
		Side side = Side.valueOf(wonderData.getString("side"));
		return (wonderType == this.wonder.getType()) && (side == this.wonder.getSide());
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

	private Level getLevel(JSONObject wonderData, int priority) {
		return getLevelData(wonderData, priority);
	}

	private Level getLevelData(JSONObject wonderData, int priority) {
		Level result = null;
		JSONArray levelArr = new JSONArray(wonderData.getJSONArray("levels").toString());

		for (int i = 0; i < levelArr.length(); i++) {
			JSONObject levelData = levelArr.getJSONObject(i);
			if (!comparePriority(levelData, priority)) {
				continue;
			} else {
				result = parseLevel(levelData, priority);
			}
		}
		return result;
	}

	private boolean comparePriority(JSONObject levelData, int priority) {
		int levelPriority = Integer.parseInt(levelData.getString("priority"));
		return (levelPriority == priority);
	}

	private Level parseLevel(JSONObject levelData, int priority) {
		Cost cost = parseCost(levelData);
		HashMap<Frequency, HashSet<Effect>> effects = parseEffects(levelData);
		return new Level(priority, cost, effects);
	}

	private Cost parseCost(JSONObject levelData) {
		JSONObject costObj = levelData.getJSONObject("cost");
		JSONArray costs = costObj.getJSONArray("costs");
		String costTypeStr = costObj.getString("costType");
		CostType costType = CostType.valueOf(costTypeStr);
		Cost cost;

		switch (costType) {
		case RESOURCE:
		case GOOD:
			cost = createSingleEntityCost(costs, costType);
			break;
		case MULTITYPE:
			cost = createMultiTypeCost(costs);
			break;
		default:
			throw new IllegalArgumentException("Invalid Cost Type");
		}

		return cost;
	}

	private Cost createSingleEntityCost(JSONArray costs, CostType costType) {
		Cost cost;
		HashMap<Enum, Integer> givenCosts = new HashMap<Enum, Integer>();

		for (int c = 0; c < costs.length(); c++) {
			JSONObject entityObj = costs.getJSONObject(c);
			int amount = entityObj.getInt("amount");
			String searchKey = getEntitySearchKey(costType);
			String entity = entityObj.getString(searchKey);
			addEntityCost(costType, givenCosts, amount, entity);
		}

		cost = new Cost(costType, givenCosts);
		return cost;
	}

	private Cost createMultiTypeCost(JSONArray costs) {
		Cost cost;
		HashMap<Enum, Integer> givenCosts = new HashMap<Enum, Integer>();

		for (int i = 0; i < costs.length(); i++) {
			addNextCost(costs, givenCosts, i);
		}

		cost = new Cost(CostType.MULTITYPE, givenCosts);
		return cost;
	}

	private void addNextCost(JSONArray costs, HashMap<Enum, Integer> givenCosts, int c) {
		JSONObject costObj = costs.getJSONObject(c);
		EntityType type = EntityType.valueOf(costObj.getString("entityType"));
		int amount = costObj.getInt("amount");

		String searchKey = getEntitySearchKey(type);
		String entity = costObj.getString(searchKey);
		addEntityCost(type, givenCosts, amount, entity);
	}

	private void addEntityCost(CostType costType, HashMap<Enum, Integer> givenCosts, int amount, String entity) {
		switch (costType) {
		case RESOURCE:
			createRawResource(givenCosts, amount, entity);
			break;
		case GOOD:
			createManufacturedGood(givenCosts, amount, entity);
			break;
		default:
			throw new IllegalArgumentException("Invalid Cost Type");
		}
	}

	private void addEntityCost(EntityType type, HashMap<Enum, Integer> givenCosts, int amount, String entity) {
		switch (type) {
		case RESOURCE:
			createRawResource(givenCosts, amount, entity);
			break;
		case MANUFACTUREDGOOD:
			createManufacturedGood(givenCosts, amount, entity);
			break;
		default:
			throw new IllegalArgumentException("Invalid Cost Type");
		}
	}

	private void createManufacturedGood(HashMap<Enum, Integer> givenCosts, int amount, String entity) {
		Good costGood = Good.valueOf(entity);
		givenCosts.put(costGood, amount);
	}

	private void createRawResource(HashMap<Enum, Integer> givenCosts, int amount, String entity) {
		RawResource costResource = RawResource.valueOf(entity);
		givenCosts.put(costResource, amount);
	}

	private String getEntitySearchKey(CostType costType) {
		String searchKey = costType == CostType.RESOURCE ? "resource" : "good";
		return searchKey;
	}

	private HashMap<Frequency, HashSet<Effect>> parseEffects(JSONObject levelData) {
		JSONArray effectArr = levelData.getJSONArray("effects");
		HashMap<Frequency, HashSet<Effect>> result = new HashMap<Frequency, HashSet<Effect>>();
		addEffects(effectArr, result);
		return result;
	}

	private void addEffects(JSONArray effectArr, HashMap<Frequency, HashSet<Effect>> map) {
		for (int i = 0; i < effectArr.length(); i++) {
			JSONObject effectObj = effectArr.getJSONObject(i);

			String effectStr = effectObj.getString("effectType");
			EffectType effectType = EffectType.valueOf(effectStr);

			Effect effect = createEffect(effectObj, effectType);
			Frequency frequency = getFrequency(effectObj);
			addToMap(effect, frequency, map);
		}
	}

	private Frequency getFrequency(JSONObject effectObj) {
		return Frequency.valueOf(effectObj.getString("frequency"));
	}

	private Effect createEffect(JSONObject effectObj, EffectType effectType) {
		switch (effectType) {
		case ENTITY:
			return createEntityEffect(effectObj);
		case VALUE:
			return createValueEffect(effectObj);
		case ABILITY:
			return createAbilityEffect(effectObj);
		default:
			throw new IllegalArgumentException("Invalid Effect Type");
		}
	}

	private Effect createEntityEffect(JSONObject effectObj) {
		String entityStr = effectObj.getString("entityType");
		EntityType type = EntityType.valueOf(entityStr);

		HashMap<Enum, Integer> entitiesAndAmounts = getEntitiesAndAmounts(effectObj, type);

		return new EntityEffect(type, entitiesAndAmounts);
	}

	private HashMap<Enum, Integer> getEntitiesAndAmounts(JSONObject effectObj, EntityType type) {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		JSONArray entitiesArr = effectObj.getJSONArray("entitiesAndAmounts");

		for (int i = 0; i < entitiesArr.length(); i++) {
			JSONObject entityToAdd = entitiesArr.getJSONObject(i);
			int entityAmount = entityToAdd.getInt("amount");

			String searchKey = getEntitySearchKey(type);
			String entity = entityToAdd.getString(searchKey);
			addEntitiesAndAmounts(entitiesAndAmounts, entityAmount, entityToAdd, type, entity);
		}
		return entitiesAndAmounts;
	}

	private void addEntitiesAndAmounts(HashMap<Enum, Integer> entitiesAndAmounts, int amount, JSONObject entityToAdd,
			EntityType type, String entity) {

		switch (type) {
		case RESOURCE:
			RawResource resource = RawResource.valueOf(entity);
			entitiesAndAmounts.put(resource, amount);
			return;
		case MANUFACTUREDGOOD:
			Good good = Good.valueOf(entity);
			entitiesAndAmounts.put(good, amount);
			return;
		case SCIENCE:
			Science science = Science.valueOf(entity);
			entitiesAndAmounts.put(science, amount);
			return;
		default:
			throw new IllegalArgumentException("Invalid Entity Type");
		}
	}

	private Effect createAbilityEffect(JSONObject effectObj) {
		Ability ability = Ability.valueOf(effectObj.getString("ability"));
		return new AbilityEffect(ability);
	}

	private Effect createValueEffect(JSONObject effectObj) {
		String affectingEntitiesStr = effectObj.getString("affectingEntities");
		AffectingEntity affectingEntities = AffectingEntity.valueOf(affectingEntitiesStr);

		String valueStr = effectObj.getString("value");
		Value value = Value.valueOf(valueStr);

		int amount = effectObj.getInt("amount");

		switch (affectingEntities) {
		case NONE:
			return new ValueEffect(value, affectingEntities, amount);
		default:
			String directionStr = effectObj.getString("direction");
			Direction direction = Direction.valueOf(directionStr);
			return new ValueEffect(value, affectingEntities, direction, amount);
		}
	}

	private void addToMap(Effect effect, Frequency frequency, HashMap<Frequency, HashSet<Effect>> map) {
		HashSet<Effect> toAdd;
		if (map.containsKey(frequency)) {
			toAdd = map.get(frequency);
		} else {
			toAdd = new HashSet<Effect>();
		}
		toAdd.add(effect);
		map.put(frequency, toAdd);
	}

	private String getEntitySearchKey(EntityType entityType) {
		switch (entityType) {
		case RESOURCE:
			return "resource";
		case MANUFACTUREDGOOD:
			return "good";
		case SCIENCE:
			return "science";
		default:
			throw new IllegalArgumentException("Invalid Entity Type");
		}
	}
}
