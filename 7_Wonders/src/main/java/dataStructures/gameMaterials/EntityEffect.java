package dataStructures.gameMaterials;

import java.util.HashMap;

import dataStructures.gameMaterials.Level.Frequency;

public class EntityEffect extends Effect {
	private EntityType entityType;
	private HashMap<Enum, Integer> entitiesAndAmounts;
	private Frequency frequency;

	public enum EntityType {
		RESOURCE, MANUFACTUREDGOOD, SCIENCE
	}

	public EntityEffect(EffectType effectType, EntityType entityType, HashMap<Enum, Integer> entitiesAndAmounts) {
		this(effectType, entityType, entitiesAndAmounts, Frequency.DEFAULT);
	}

	public EntityEffect(EffectType effectType, EntityType entityType, HashMap<Enum, Integer> entitiesAndAmounts,
			Frequency frequency) {
		super(effectType);
		this.entityType = entityType;
		this.entitiesAndAmounts = entitiesAndAmounts;
		this.frequency = frequency;
	}

	public EntityType getEntityType() {
		return this.entityType;
	}

	public HashMap<Enum, Integer> getEntities() {
		return this.entitiesAndAmounts;
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

	public Frequency getFrequency() {
		return this.frequency;
	}
}
