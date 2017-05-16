package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.MultiValueEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.ValueEffect.ValueType;

public class MultiValueEffectTest {

	@Test
	public void testValuesAndAmounts() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts);

		assertEquals(valuesAndAmounts, ((MultiValueEffect) effect).getValues());
	}

	@Test
	public void testCommerceResourcesEffect() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.COIN, 1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES,
				Direction.SELF, valuesAndAmounts);

		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.COMMERCE, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.RAWRESOURCES, ((MultiValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void testCommerceManufacturedGoodsEffect() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.COIN, 1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS,
				Direction.SELF, valuesAndAmounts);

		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.COMMERCE, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, ((MultiValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void testCommerceCommercialStructuresEffect() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.COIN, 1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE,
				AffectingEntity.COMMERCIALSTRUCTURES, Direction.SELF, valuesAndAmounts);

		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.COMMERCE, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.COMMERCIALSTRUCTURES, ((MultiValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void testCommerceWonderLevelEffect() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.COIN, 3);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.WONDERLEVEL,
				Direction.SELF, valuesAndAmounts);

		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(4, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.COMMERCE, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.WONDERLEVEL, ((MultiValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void testGuildEffect() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts);

		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(0, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.GUILD, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.NONE, ((MultiValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void testValidEqualsGuild() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts);
		HashMap<Enum, Integer> valuesAndAmounts2 = new HashMap<Enum, Integer>();
		valuesAndAmounts2.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts2.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect2 = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts2);

		assertTrue(effect.equals(effect2));
	}

	@Test
	public void testInvalidEqualsCommerce() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts);
		HashMap<Enum, Integer> valuesAndAmounts2 = new HashMap<Enum, Integer>();
		valuesAndAmounts2.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts2.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect2 = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts2);

		assertFalse(effect.equals(effect2));
	}

	@Test
	public void testInvalidEqualsGuildAffecting() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 5);
		MultiValueEffect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts);
		HashMap<Enum, Integer> valuesAndAmounts2 = new HashMap<Enum, Integer>();
		valuesAndAmounts2.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts2.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect2 = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts2);

		assertFalse(effect.equals(effect2));
	}

	@Test
	public void testInvalidEqualsGuildDirection() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.SELF, valuesAndAmounts);
		HashMap<Enum, Integer> valuesAndAmounts2 = new HashMap<Enum, Integer>();
		valuesAndAmounts2.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts2.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect2 = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.NEIGHBORS, valuesAndAmounts2);

		assertFalse(effect.equals(effect2));
	}

	@Test
	public void testInvalidEqualsGuildEntity() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE,
				Direction.SELF, valuesAndAmounts);
		HashMap<Enum, Integer> valuesAndAmounts2 = new HashMap<Enum, Integer>();
		valuesAndAmounts2.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts2.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect2 = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.WONDERLEVEL,
				Direction.SELF, valuesAndAmounts2);

		assertFalse(effect.equals(effect2));
	}

	@Test
	public void testInvalidEqualsGuildDifferentType(){
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		MultiValueEffect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE, Direction.SELF, valuesAndAmounts);
		HashMap<Enum, Integer> valuesAndAmounts2 = new HashMap<Enum, Integer>();
		valuesAndAmounts2.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts2.put(ValueType.VICTORYPOINT, 1);
		ValueEffect effect2 = new ValueEffect(EffectType.VALUE, Value.COMMERCE, valuesAndAmounts2);
		assertFalse(effect.equals(effect2));
	}
}