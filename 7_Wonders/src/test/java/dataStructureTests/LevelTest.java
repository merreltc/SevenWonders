package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import dataStructures.Cost;
import dataStructures.Cost.CostType;
import dataStructures.Effect;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect;
import dataStructures.EntityEffect.EntityType;
import dataStructures.GeneralEnums.RawResource;
import dataStructures.Level;
import dataStructures.Level.Frequency;
import dataStructures.MultiValueEffect;
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
		Level level = new Level(priority, cost, effect, Frequency.ONCEIMMEDIATE);
		assertEquals(1, level.getPriority());
	}
	
	@Test
	public void testPriority2() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		int priority = 2;
		Level level = new Level(priority, cost, effect, Frequency.ONCEIMMEDIATE);
		assertEquals(2, level.getPriority());
	}
	
	@Test
	public void testPriority3() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		int priority = 3;
		Level level = new Level(priority, cost, effect, Frequency.ONCEIMMEDIATE);
		assertEquals(3, level.getPriority());
	}
	
	@Test
	public void testCost() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		HashMap<Enum, Integer> expected = new HashMap<Enum, Integer>();
		expected.put(RawResource.LUMBER, 2);

		EasyMock.expect(cost.getType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cost.getCost()).andReturn(expected);
		EasyMock.replay(cost);
		
		int priority = 1;
		Level level = new Level(priority, cost, effect, Frequency.ONCEIMMEDIATE);
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
		expectedEntities.put(RawResource.LUMBER, 2);

		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(effect.getEntityType()).andReturn(EntityType.RESOURCE);
		EasyMock.expect(effect.getEntities()).andReturn(expectedEntities);
		EasyMock.replay(effect);
		
		int priority = 1;
		Level level = new Level(priority, cost, effect, Frequency.ONCEIMMEDIATE);
		
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

		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(effect.getValue()).andReturn(Value.VICTORYPOINTS);
		EasyMock.expect(effect.getValueType()).andReturn(ValueType.VICTORYPOINT);
		EasyMock.expect(effect.getAffectingEntity()).andReturn(AffectingEntity.RAWRESOURCES);
		EasyMock.replay(effect);
		
		int priority = 1;
		Level level = new Level(priority, cost, effect, Frequency.ONCEIMMEDIATE);
		
		EffectType effectType = level.getEffectType();
		Direction direction = level.getEffectDirection();
		Value value = level.getEffectValue();
		ValueType valueType = level.getEffectValueType();
		AffectingEntity affEntity = level.getAffectingEntity();
		
		EasyMock.verify(effect);
		assertEquals(EffectType.VALUE, effectType);
		assertEquals(Direction.SELF, direction);
		assertEquals(Value.VICTORYPOINTS, value);
		assertEquals(ValueType.VICTORYPOINT, valueType);
		assertEquals(AffectingEntity.RAWRESOURCES, affEntity);
	}
	
	@Test
	public void testMultiValueEffect() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		MultiValueEffect effect = EasyMock.createStrictMock(MultiValueEffect.class);

		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.MULTIVALUE);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(effect.getValue()).andReturn(Value.COMMERCE);
		EasyMock.expect(effect.getAffectingEntity()).andReturn(AffectingEntity.MANUFACTUREDGOODS);
		
		HashMap<Enum, Integer> expectedValues = new HashMap<Enum, Integer>();
		expectedValues.put(ValueType.COIN, 1);
		expectedValues.put(ValueType.VICTORYPOINT, 1);
		
		EasyMock.expect(effect.getValues()).andReturn(expectedValues);
		
		EasyMock.replay(effect);
		
		int priority = 1;
		Level level = new Level(priority, cost, effect, Frequency.ONCEIMMEDIATE);
		
		EffectType effectType = level.getEffectType();
		Direction direction = level.getEffectDirection();
		Value value = level.getEffectValue();
		AffectingEntity affEntity = level.getAffectingEntity();
		HashMap<Enum, Integer> actualValues = level.getValues();
		
		EasyMock.verify(effect);
		assertEquals(EffectType.MULTIVALUE, effectType);
		assertEquals(Direction.SELF, direction);
		assertEquals(Value.COMMERCE, value);
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, affEntity);
		assertEquals(expectedValues, actualValues);
	}
	
	@Test
	public void testMultipleEffects() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		MultiValueEffect effect1 = EasyMock.createStrictMock(MultiValueEffect.class);
		ValueEffect effect2 = EasyMock.createStrictMock(ValueEffect.class);
		EasyMock.replay(effect1, effect2);
		
		int priority = 1;
		Frequency freq = Frequency.ONCEIMMEDIATE;
		HashMap<Effect, Frequency> expectedEffects = new HashMap<Effect, Frequency>();
		expectedEffects.put(effect1, freq);
		expectedEffects.put(effect2, freq);
		Level level = new Level(priority, cost, expectedEffects);
		
		HashMap<Effect, Frequency> actualEffects = level.getEffects();
		
		EasyMock.verify(effect1, effect2);
		assertEquals(expectedEffects, actualEffects);
	}
	
	@Test
	public void testSingleEffectFrequency() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect endOfTurn = EasyMock.createStrictMock(Effect.class);		
		Effect everyTurn = EasyMock.createStrictMock(Effect.class);
		Effect sixthTurn = EasyMock.createStrictMock(Effect.class);
		Effect onceImmediate = EasyMock.createStrictMock(Effect.class);
		Effect onceAge = EasyMock.createStrictMock(Effect.class);
		Effect endOfGame = EasyMock.createStrictMock(Effect.class);
		
		EasyMock.replay();
		
		int priority = 1;
		Level level1 = new Level(priority, cost, endOfTurn, Frequency.ENDOFTURN);
		Level level2 = new Level(priority, cost, everyTurn, Frequency.EVERYTURN);
		Level level3 = new Level(priority, cost, sixthTurn, Frequency.SIXTHTURN);
		Level level4 = new Level(priority, cost, onceImmediate, Frequency.ONCEIMMEDIATE);
		Level level5 = new Level(priority, cost, onceAge, Frequency.ONCEAGE);
		Level level6 = new Level(priority, cost, endOfGame, Frequency.ENDOFGAME);
		
		assertEquals(Frequency.ENDOFTURN, level1.getFrequency());
		assertEquals(Frequency.EVERYTURN, level2.getFrequency());
		assertEquals(Frequency.SIXTHTURN, level3.getFrequency());
		assertEquals(Frequency.ONCEIMMEDIATE, level4.getFrequency());
		assertEquals(Frequency.ONCEAGE, level5.getFrequency());
		assertEquals(Frequency.ENDOFGAME, level6.getFrequency());
		
		EasyMock.verify();
	}
	
	@Test
	public void testMultiEffectFrequency() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect endOfTurn = EasyMock.createStrictMock(Effect.class);		
		Effect everyTurn = EasyMock.createStrictMock(Effect.class);
		HashMap<Effect, Frequency> effects = new HashMap<Effect, Frequency>();
		effects.put(endOfTurn, Frequency.ENDOFTURN);
		effects.put(everyTurn, Frequency.EVERYTURN);
		
		EasyMock.replay();
		
		int priority = 1;
		Level level1 = new Level(priority, cost, effects);
		assertEquals(effects, level1.getEffects());
		
		for(Effect effect : effects.keySet()) {
			Frequency freq = effects.get(effect);
			boolean validFreq = (freq == Frequency.ENDOFTURN || freq == Frequency.EVERYTURN);
			assertTrue(validFreq);
		}
		
		EasyMock.verify();
	}
}
