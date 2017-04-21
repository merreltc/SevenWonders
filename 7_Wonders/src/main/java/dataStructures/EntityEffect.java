package dataStructures;

import java.util.HashMap;

public class EntityEffect extends Effect {
	private EntityType entityType;
	private HashMap<Enum, Integer> entitiesAndAmounts;
	
	public enum EntityType {
		RESOURCE, MANUFACTUREDGOOD, SCIENCE
	}

	public enum Resource {
		LUMBER, CLAY, ORE, STONE
	}

	public enum Good {
		LOOM, GLASS, PRESS
	}

	public enum Science {
		PROTRACTOR, WHEEL, TABLET		
	}
	
	public EntityEffect(EffectType effectType, EntityType entityType, HashMap<Enum, Integer> entitiesAndAmounts) {
		super(effectType);
		this.entityType = entityType;
		this.entitiesAndAmounts = entitiesAndAmounts;
	}
	
	public EntityType getEntityType() {
		return this.entityType;
	}

	public HashMap<Enum, Integer> getEntities() {
		return this.entitiesAndAmounts;
	}
}
