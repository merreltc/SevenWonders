package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import dataStructures.Cost;
import dataStructures.Cost.CostType;
import dataStructures.Cost.Resource;
import dataStructures.Effect;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect.EntityType;
import dataStructures.EntityEffect;
import dataStructures.Level;
import dataStructures.ValueEffect;
import dataStructures.ValueEffect.AffectingEntity;
import dataStructures.ValueEffect.Value;
import dataStructures.ValueEffect.ValueType;

public class LevelTest {

	@Test
	public void testPriority1() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		int priority = 1;
		Level level = new Level(priority, cost, effect);
		assertEquals(1, level.getPriority());
	}
	
	@Test
	public void testPriority2() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		int priority = 2;
		Level level = new Level(priority, cost, effect);
		assertEquals(2, level.getPriority());
	}
	
	@Test
	public void testPriority3() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		int priority = 3;
		Level level = new Level(priority, cost, effect);
		assertEquals(3, level.getPriority());
	}
	
	@Test
	public void testCost() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(Resource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cost.getCost()).andReturn(expected);
		EasyMock.replay(cost);
		
		int priority = 1;
		Level level = new Level(priority, cost, effect);
		CostType costType = level.getCostType();
		HashMap<Enum, Integer> actual = level.getCost();
		
		EasyMock.verify(cost);
		assertEquals(CostType.RESOURCE, costType);
		assertEquals(expected, actual);
	}

	@Test
	public void testEntityEffect() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		EntityEffect effect = EasyMock.createStrictMock(EntityEffect.class);
		
		HashMap<Enum, Integer> expectedEntities = new HashMap<Enum, Integer>();
		expectedEntities.put(Resource.LUMBER, 2);

		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(effect.getEntityType()).andReturn(EntityType.RESOURCE);
		EasyMock.expect(effect.getEntities()).andReturn(expectedEntities);
		EasyMock.replay(effect);
		
		int priority = 1;
		Level level = new Level(priority, cost, effect);
		
		EffectType effectType = level.getEffectType();
		Direction direction = level.getEffectDirection();
		EntityType entityType = level.getEntityType();
		HashMap<Enum, Integer> actualEntities = level.getEntities();
		
		EasyMock.verify(effect);
		assertEquals(EffectType.ENTITY, effectType);
		assertEquals(Direction.SELF, direction);
		assertEquals(EntityType.RESOURCE, entityType);
		assertEquals(expectedEntities, actualEntities);
	}
	
	@Test
	public void testValueEffect() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		ValueEffect effect = EasyMock.createStrictMock(ValueEffect.class);

		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(effect.getValue()).andReturn(Value.VICTORYPOINTS);
		EasyMock.expect(effect.getValueType()).andReturn(ValueType.VICTORYPOINT);
		EasyMock.expect(effect.getAffectingEntity()).andReturn(AffectingEntity.RAWRESOURCES);
		EasyMock.replay(effect);
		
		int priority = 1;
		Level level = new Level(priority, cost, effect);
		
		EffectType effectType = level.getEffectType();
		Direction direction = level.getEffectDirection();
		Value value = level.getEffectValue();
		ValueType valueType = level.getEffectValueType();
		AffectingEntity affEntity = level.getAffectingEntity();
		
		EasyMock.verify(effect);
		assertEquals(EffectType.ENTITY, effectType);
		assertEquals(Direction.SELF, direction);
		assertEquals(Value.VICTORYPOINTS, value);
		assertEquals(ValueType.VICTORYPOINT, valueType);
		assertEquals(AffectingEntity.RAWRESOURCES, affEntity);
	}
}
