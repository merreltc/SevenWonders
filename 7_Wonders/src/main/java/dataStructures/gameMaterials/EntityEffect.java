package dataStructures.gameMaterials;

import java.util.HashMap;

import dataStructures.gameMaterials.Level.Frequency;

public class EntityEffect extends Effect {
	private EntityType entityType;
	private HashMap<Enum, Integer> entitiesAndAmounts;

	public enum EntityType {
		RESOURCE, MANUFACTUREDGOOD, SCIENCE
	}

	public EntityEffect(EntityType entityType, HashMap<Enum, Integer> entitiesAndAmounts) {
		super(EffectType.ENTITY);
		this.entityType = entityType;
		this.entitiesAndAmounts = entitiesAndAmounts;
	}

	public EntityType getEntityType() {
		return this.entityType;
	}

	public HashMap<Enum, Integer> getEntities() {
		return this.entitiesAndAmounts;
	}
	
	@Override
	public String toString() {
		return "Entity Type: " + this.entityType + ", Entities and Amounts: " + this.entitiesAndAmounts;
	}

	@Override
	public boolean equals(Object obj) {
		Effect effect = (Effect) obj;
		if (effect.getEffectType() != EffectType.ENTITY) {
			return false;
		}

		EntityEffect entity = (EntityEffect) effect;

		if (this.entityType == entity.entityType
				&& this.entitiesAndAmounts.toString().equals(entity.entitiesAndAmounts.toString())) {
			return true;
		}

		return false;
	}
}
