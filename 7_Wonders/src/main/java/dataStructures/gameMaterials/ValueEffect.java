package dataStructures.gameMaterials;

import java.util.HashMap;

import utils.Translate;

public class ValueEffect extends Effect {
	private Value value;
	private int valueAmount;
	private AffectingEntity affectingEntity = AffectingEntity.NONE;
	private HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();
	private static final int NO_VALUE_AMOUNT = -1;

	public enum Value {
		VICTORYPOINTS, MILITARY, COMMERCE, GUILD
	}

	public enum ValueType {
		VICTORYPOINT, CONFLICTTOKEN, COIN
	}

	public enum AffectingEntity {
		NONE, RAWRESOURCES, MANUFACTUREDGOODS, COMMERCIALSTRUCTURES, SCIENTIFICSTRUCTURES, MILITARYSTRUCTURES, CIVILIANSTRUCTURES, GUILD, WONDERLEVEL
	}

	public ValueEffect(EffectType effectType, Value value, AffectingEntity affectingEntity, int valueAmount) {
		super(effectType);
		this.value = value;
		this.affectingEntity = affectingEntity;

		validateValueAmount(value, valueAmount);
		this.valueAmount = valueAmount;
	}

	public ValueEffect(EffectType effectType, Value value, AffectingEntity affectingEntity, Direction direction) {
		super(effectType);
		this.value = value;
		this.setDirection(direction);
		this.affectingEntity = affectingEntity;
		this.valueAmount = NO_VALUE_AMOUNT;
	}

	public ValueEffect(EffectType effectType, Value value, AffectingEntity affectingEntity, Direction direction,
			int valueAmount) {
		super(effectType);
		this.value = value;
		this.setDirection(direction);
		this.affectingEntity = affectingEntity;
		this.valueAmount = valueAmount;
	}

	public ValueEffect(EffectType effectType, Value value, HashMap<Enum, Integer> affectingEntities) {
		super(effectType);
		this.value = value;
		this.affectingEntities = affectingEntities;
		this.valueAmount = NO_VALUE_AMOUNT;
	}

	private void validateValueAmount(Value value, int valueAmount) {
		switch (value) {
		case VICTORYPOINTS:
			if (valueAmount <= 0 || valueAmount >= 9) {
				String msg = Translate.prepareStringTemplateWithIntArg(valueAmount, "improperValueAmount",
						Translate.getNewResourceBundle());
				throw new IllegalArgumentException(msg);
			}
			break;
		case COMMERCE:
			if (valueAmount <= 0) {
				String msg = Translate.prepareStringTemplateWithIntArg(valueAmount, "improperValueAmount",
						Translate.getNewResourceBundle());
				throw new IllegalArgumentException(msg);
			}
			break;
		default:
			if (valueAmount <= -2 || valueAmount == 0 || valueAmount >= 4) {
				String msg = Translate.prepareStringTemplateWithIntArg(valueAmount, "improperValueAmount",
						Translate.getNewResourceBundle());
				throw new IllegalArgumentException(msg);
			}
			break;
		}
	}

	public int getValueAmount() {
		return this.valueAmount;
	}

	public Value getValue() {
		return this.value;
	}

	public ValueType getValueType() {
		switch (this.value) {
		case VICTORYPOINTS:
			return ValueType.VICTORYPOINT;
		case COMMERCE:
			return ValueType.COIN;
		case GUILD:
			return ValueType.VICTORYPOINT;
		default:
			return ValueType.CONFLICTTOKEN;
		}
	}

	public AffectingEntity getAffectingEntity() {
		return this.affectingEntity;
	}

	public HashMap<Enum, Integer> getAffectingEntities() {
		return this.affectingEntities;
	}

	@Override
	public boolean equals(Object obj) {
		Effect effect = (Effect) obj;
		if (effect.getEffectType() != EffectType.VALUE) {
			return false;
		}

		ValueEffect value = (ValueEffect) effect;

		if (this.valueAmount == value.getValueAmount() && this.getDirection() == value.getDirection()
				&& this.affectingEntity == value.affectingEntity && this.value == value.getValue()
				&& this.affectingEntities.toString().equals(value.affectingEntities.toString())) {
			return true;
		}

		return false;
	}
}
