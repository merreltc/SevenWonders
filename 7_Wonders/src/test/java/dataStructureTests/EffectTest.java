package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.Effect;
import dataStructures.Effect.AffectingEntity;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.Effect.Value;
import dataStructures.Effect.ValueType;

public class EffectTest {

	@Test
	public void test2VictoryPointsValueEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, effect.getValueAmount());
		assertEquals(Value.VICTORYPOINTS, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
	}
	
	@Test
	public void test8VictoryPointsValueEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 8);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(8, effect.getValueAmount());
		assertEquals(Value.VICTORYPOINTS, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue0Effect(){
		new Effect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 0);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidVictoryPointsValueNeg1Effect(){
		new Effect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, -1);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue9Effect(){
		new Effect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 9);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue10Effect(){
		new Effect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 10);
		fail();
	}
	
	@Test
	public void testInvalidVictoryPointsValue0EffectMessage(){
		try{
			new Effect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 0);
		}catch(IllegalArgumentException error){
			assertEquals("Cannot have valueAmount of 0", error.getMessage());
		}
	}
	
	@Test
	public void testInvalidVictoryPointsValue10EffectMessage(){
		try{
			new Effect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 10);
		}catch(IllegalArgumentException error){
			assertEquals("Cannot have valueAmount of 10", error.getMessage());
		}
	}

	@Test
	public void test1MilitaryValueEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.MILITARY, effect.getValue());
		assertEquals(ValueType.CONFLICTTOKEN, effect.getValueType());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
	}
	
	@Test
	public void test3MilitaryValueEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 3);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(3, effect.getValueAmount());
		assertEquals(Value.MILITARY, effect.getValue());
		assertEquals(ValueType.CONFLICTTOKEN, effect.getValueType());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
	}
	
	@Test
	public void testNeg1MilitaryValueEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, -1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(-1, effect.getValueAmount());
		assertEquals(Value.MILITARY, effect.getValue());
		assertEquals(ValueType.CONFLICTTOKEN, effect.getValueType());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidMilitaryValue0Effect(){
	    new Effect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 0);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidMilitaryValueNeg2Effect(){
	    new Effect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, -2);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidMilitaryValue8Effect(){
	    new Effect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 8);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidMilitaryValue4Effect(){
	    new Effect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 4);
		fail();
	}
	
	@Test
	public void test1CommerceValueRightEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.RIGHT, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.RIGHT, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.COMMERCE, effect.getValue());
		assertEquals(ValueType.COIN, effect.getValueType());
		assertEquals(AffectingEntity.RAWRESOURCES, effect.getAffectingEntity());
	}
	
	@Test
	public void test1CommerceValueLeftEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.LEFT, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.LEFT, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.COMMERCE, effect.getValue());
		assertEquals(ValueType.COIN, effect.getValueType());
		assertEquals(AffectingEntity.RAWRESOURCES, effect.getAffectingEntity());
	}
	
	@Test
	public void test1CommerceValueAllEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS, Direction.ALL, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.ALL, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.COMMERCE, effect.getValue());
		assertEquals(ValueType.COIN, effect.getValueType());
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, effect.getAffectingEntity());
	}
	
	@Test
	public void test1CommerceValueNeighborsEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.COMMERCE, effect.getValue());
		assertEquals(ValueType.COIN, effect.getValueType());
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, effect.getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueCommercialStructuresEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.GUILD, AffectingEntity.COMMERCIALSTRUCTURES, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.GUILD, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.COMMERCIALSTRUCTURES, effect.getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueScientificStructuresEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.GUILD, AffectingEntity.SCIENTIFICSTRUCTURES, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.GUILD, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.SCIENTIFICSTRUCTURES, effect.getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueMilitaryStructuresEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.GUILD, AffectingEntity.MILITARYSTRUCTURES, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.GUILD, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.MILITARYSTRUCTURES, effect.getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueCivilianStructuresEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.GUILD, AffectingEntity.CIVILIANSTRUCTURES, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.GUILD, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.CIVILIANSTRUCTURES, effect.getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueGuildEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.GUILD, AffectingEntity.GUILD, Direction.SELF, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.GUILD, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.GUILD, effect.getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueWonderLevelEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.GUILD, AffectingEntity.WONDERLEVEL, Direction.ALL, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.ALL, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.GUILD, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.WONDERLEVEL, effect.getAffectingEntity());
	}
	
	@Test
	public void test1MultiValueCommerceResourcesEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new Effect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.SELF, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, effect.getMultiValueAmount());
		assertEquals(Value.COMMERCE, effect.getValue());
		assertEquals(AffectingEntity.RAWRESOURCES, effect.getAffectingEntity());
	}
	
	@Test
	public void test1MultiValueCommerceManufacturedGoodsEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new Effect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS, Direction.SELF, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, effect.getMultiValueAmount());
		assertEquals(Value.COMMERCE, effect.getValue());
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, effect.getAffectingEntity());
	}
	
	@Test
	public void test1MultiValueCommerceCommercialStructuresEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new Effect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.COMMERCIALSTRUCTURES, Direction.SELF, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, effect.getMultiValueAmount());
		assertEquals(Value.COMMERCE, effect.getValue());
		assertEquals(AffectingEntity.COMMERCIALSTRUCTURES, effect.getAffectingEntity());
	}
	
	@Test
	public void test1MultiValueCommerceWonderLevelEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.COIN, 3);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new Effect(EffectType.MULTIVALUE, Value.COMMERCE, AffectingEntity.WONDERLEVEL, Direction.SELF, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(4, effect.getMultiValueAmount());
		assertEquals(Value.COMMERCE, effect.getValue());
		assertEquals(AffectingEntity.WONDERLEVEL, effect.getAffectingEntity());
	}
	
	@Test
	public void test1MultiValueGuildEffect() {
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(ValueType.CONFLICTTOKEN, -1);
		entitiesAndAmounts.put(ValueType.VICTORYPOINT, 1);
		Effect effect = new Effect(EffectType.MULTIVALUE, Value.GUILD, AffectingEntity.NONE, Direction.NEIGHBORS, entitiesAndAmounts);
		
		assertEquals(EffectType.MULTIVALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(0, effect.getMultiValueAmount());
		assertEquals(Value.GUILD, effect.getValue());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
	}
}
