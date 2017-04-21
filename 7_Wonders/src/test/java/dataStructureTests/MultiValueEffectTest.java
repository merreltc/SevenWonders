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
	public void test1MultiValueCommerceResourcesEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.SELF, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.COMMERCE, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.RAWRESOURCES, ((MultiValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1MultiValueCommerceManufacturedGoodsEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS, Direction.SELF, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.COMMERCE, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, ((MultiValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1MultiValueCommerceCommercialStructuresEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.COMMERCIALSTRUCTURES, Direction.SELF, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.COMMERCE, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.COMMERCIALSTRUCTURES, ((MultiValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1MultiValueCommerceWonderLevelEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 3);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.WONDERLEVEL, Direction.SELF, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(4, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.COMMERCE, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.WONDERLEVEL, ((MultiValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1MultiValueGuildEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new MultiValueEffect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE, Direction.NEIGHBORS, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(0, ((MultiValueEffect) effect).getMultiValueAmount());
		assertEquals(Value.GUILD, ((MultiValueEffect) effect).getValue());
		assertEquals(AffectingEntity.NONE, ((MultiValueEffect) effect).getAffectingEntity());
	}
}
