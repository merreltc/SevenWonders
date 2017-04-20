package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructures.Effect;
import dataStructures.Effect.AffectingEntity;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.Effect.Entity;
import dataStructures.Effect.Good;
import dataStructures.Effect.Resource;
import dataStructures.Effect.Value;
import dataStructures.Effect.ValueType;

public class EffectTest {

	@Test
	public void testDefaultEffect() {
		Effect effect = new Effect();

		assertEquals(EffectType.NONE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
	}

	@Test
	public void testDefaultEntityEffect() {
		Effect effect = new Effect(EffectType.ENTITY);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(AffectingEntity.NONE, effect.getAffectingEntity());
	}
	
	@Test
	public void test1LumberEntityEffect() {
		Effect effect = new Effect(EffectType.ENTITY, Entity.RESOURCE, Resource.LUMBER, 1);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, effect.getEntityAmount());
		assertEquals(Entity.RESOURCE, effect.getEntity());
		assertEquals(Resource.LUMBER, effect.getResource());
	}
	
	@Test
	public void test1ClayEntityEffect() {
		Effect effect = new Effect(EffectType.ENTITY, Entity.RESOURCE, Resource.CLAY, 1);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, effect.getEntityAmount());
		assertEquals(Entity.RESOURCE, effect.getEntity());
		assertEquals(Resource.CLAY, effect.getResource());
	}
	
	@Test
	public void test1OreEntityEffect() {
		Effect effect = new Effect(EffectType.ENTITY, Entity.RESOURCE, Resource.ORE, 1);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, effect.getEntityAmount());
		assertEquals(Entity.RESOURCE, effect.getEntity());
		assertEquals(Resource.ORE, effect.getResource());
	}
	
	@Test
	public void test1StoneEntityEffect() {
		Effect effect = new Effect(EffectType.ENTITY, Entity.RESOURCE, Resource.STONE, 1);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, effect.getEntityAmount());
		assertEquals(Entity.RESOURCE, effect.getEntity());
		assertEquals(Resource.STONE, effect.getResource());
	}
	
	@Test
	public void test1LoomEntityEffect() {
		Effect effect = new Effect(EffectType.ENTITY, Entity.MANUFACTUREDGOOD, Good.LOOM, 1);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, effect.getEntityAmount());
		assertEquals(Entity.MANUFACTUREDGOOD, effect.getEntity());
		assertEquals(Good.LOOM, effect.getGood());
	}
	
	@Test
	public void test1GlassEntityEffect() {
		Effect effect = new Effect(EffectType.ENTITY, Entity.MANUFACTUREDGOOD, Good.GLASS, 1);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, effect.getEntityAmount());
		assertEquals(Entity.MANUFACTUREDGOOD, effect.getEntity());
		assertEquals(Good.GLASS, effect.getGood());
	}
	
	@Test
	public void test1PressEntityEffect() {
		Effect effect = new Effect(EffectType.ENTITY, Entity.MANUFACTUREDGOOD, Good.PRESS, 1);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, effect.getEntityAmount());
		assertEquals(Entity.MANUFACTUREDGOOD, effect.getEntity());
		assertEquals(Good.PRESS, effect.getGood());
	}
	
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
	public void test1GuildValuerCommercialStructuresEffect(){
		Effect effect = new Effect(EffectType.VALUE, Value.GUILD, AffectingEntity.COMMERCIALSTRUCTURES, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, effect.getValueAmount());
		assertEquals(Value.GUILD, effect.getValue());
		assertEquals(ValueType.VICTORYPOINT, effect.getValueType());
		assertEquals(AffectingEntity.COMMERCIALSTRUCTURES, effect.getAffectingEntity());
	}
}
