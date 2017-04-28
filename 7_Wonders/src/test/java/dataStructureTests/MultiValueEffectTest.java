package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.Effect;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.MultiValueEffect;
import dataStructures.ValueEffect.AffectingEntity;
import dataStructures.ValueEffect.Value;
import dataStructures.ValueEffect.ValueType;

public class MultiValueEffectTest {
	
	@Test
	public void testValuesAndAmounts() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE, Direction.NEIGHBORS, valuesAndAmounts);
		
		assertEquals(valuesAndAmounts, ((MultiValueEffect) effect).getValues());
	}
	
	@Test
	public void testCommerceResourcesEffect() {
		HashMap<Enum, Integer> valuesAndAmounts = new HashMap<Enum, Integer>();
		valuesAndAmounts.put(ValueType.COIN, 1);
		valuesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.SELF, valuesAndAmounts);
		
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
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS, Direction.SELF, valuesAndAmounts);
		
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
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.COMMERCIALSTRUCTURES, Direction.SELF, valuesAndAmounts);
		
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
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.WONDERLEVEL, Direction.SELF, valuesAndAmounts);
		
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
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE, Direction.NEIGHBORS, valuesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(0, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.GUILD, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.NONE, ((MultiValueEffect) effect).getAffectingEntity());
	}
}
