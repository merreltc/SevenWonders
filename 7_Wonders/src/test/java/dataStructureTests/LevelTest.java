package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import constants.GeneralEnums.CostType;
import constants.GeneralEnums.RawResource;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.MultiValueEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.ValueEffect.ValueType;

public class LevelTest {
	private static String MOCK_COST_TO_STRING = "EasyMock for class dataStructures.gameMaterials.Cost";
	private static String MOCK_EFFECTS_TO_STRING_EVERYTURN = "{EVERYTURN=[EasyMock for class dataStructures.gameMaterials.Effect]}";
	private static String MOCK_EFFECTS_TO_STRING_ONCEIMMEDIATE = "{ONCEIMMEDIATE=[EasyMock for class dataStructures.gameMaterials.Effect]}";
	
	@Test
	public void testToStringSingleEffect() {
		int priority = 1;
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		Level level = new Level(priority, cost, effect, Frequency.EVERYTURN);

		assertEquals("Priority: 1, Cost: " + MOCK_COST_TO_STRING + ", Effects: " + MOCK_EFFECTS_TO_STRING_EVERYTURN,
				level.toString());
	}

	@Test
	public void testToStringMultiEffect() {
		int priority = 1;
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Frequency, ArrayList<Effect>> effects = new HashMap<Frequency, ArrayList<Effect>>();
		ArrayList<Effect> effectList = new ArrayList<Effect>();
		effectList.add(effect);
		effects.put(Frequency.ONCEIMMEDIATE, effectList);

		Level level = new Level(priority, cost, effects);
		assertEquals("Priority: 1, Cost: " + MOCK_COST_TO_STRING + ", Effects: " + MOCK_EFFECTS_TO_STRING_ONCEIMMEDIATE,
				level.toString());
	}

	@Test
	public void testEqualsTrueSingleEffect() {
		int priority = 1;
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		Level level1 = new Level(priority, cost, effect, Frequency.EVERYTURN);
		Level level2 = new Level(priority, cost, effect, Frequency.EVERYTURN);
		assertTrue(level1.equals(level2));
	}

	@Test
	public void testEqualsFalseSingleEffect() {
		Cost cost1 = EasyMock.createStrictMock(Cost.class);
		Cost cost2 = EasyMock.createStrictMock(Cost.class);
		Effect effect1 = EasyMock.createStrictMock(Effect.class);
		Effect effect2 = EasyMock.createStrictMock(Effect.class);
		int priority1 = 1;
		int priority2 = 2;

		Level level1 = new Level(priority1, cost1, effect1, Frequency.ONCEIMMEDIATE);
		Level level2 = new Level(priority2, cost2, effect2, Frequency.EVERYTURN);
		assertFalse(level1.equals(level2));
	}

	@Test
	public void testEqualsTrueMultiEffect() {
		int priority = 1;
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Frequency, ArrayList<Effect>> effects = new HashMap<Frequency, ArrayList<Effect>>();
		ArrayList<Effect> effectList = new ArrayList<Effect>();
		effectList.add(effect);
		effects.put(Frequency.ONCEIMMEDIATE, effectList);

		Level level1 = new Level(priority, cost, effects);
		Level level2 = new Level(priority, cost, effects);
		assertTrue(level1.equals(level2));
	}

	@Test
	public void testEqualsFalseMultiEffect() {
		Cost cost1 = EasyMock.createStrictMock(Cost.class);
		Cost cost2 = EasyMock.createStrictMock(Cost.class);
		Effect effect1 = EasyMock.createStrictMock(Effect.class);
		Effect effect2 = EasyMock.createStrictMock(Effect.class);
		int priority1 = 1;
		int priority2 = 2;

		HashMap<Frequency, ArrayList<Effect>> effects1 = new HashMap<Frequency, ArrayList<Effect>>();
		ArrayList<Effect> effect = new ArrayList<Effect>();
		effect.add(effect1);
		effects1.put(Frequency.ONCEIMMEDIATE, effect);

		HashMap<Frequency, ArrayList<Effect>> effects2 = new HashMap<Frequency, ArrayList<Effect>>();
		effect = new ArrayList<Effect>();
		effect.add(effect2);
		effects2.put(Frequency.ONCEIMMEDIATE, effect);

		Level level1 = new Level(priority1, cost1, effects1);
		Level level2 = new Level(priority2, cost2, effects2);
		assertFalse(level1.equals(level2));
	}

	@Test
	public void testGetEffect() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		int priority = 1;

		Level level = new Level(priority, cost, effect, Frequency.ONCEIMMEDIATE);
		assertTrue(level.getEffects().containsKey(Frequency.ONCEIMMEDIATE));
		assertEquals(effect, level.getEffects().get(Frequency.ONCEIMMEDIATE).get(0));
	}

	@Test
	public void testGetCost() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		int priority = 1;

		Level level = new Level(priority, cost, effect, Frequency.ONCEIMMEDIATE);
		assertEquals(cost, level.getCost());
	}

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
		HashMap<Enum, Integer> actual = level.getCosts();

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

		Effect actual = level.getEffects().get(Frequency.ONCEIMMEDIATE).get(0);

		EffectType effectType = actual.getEffectType();
		Direction direction = actual.getDirection();
		EntityType entityType = ((EntityEffect) actual).getEntityType();

		HashMap<Enum, Integer> actualEntities = ((EntityEffect) effect).getEntities();

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

		Effect actual = level.getEffects().get(Frequency.ONCEIMMEDIATE).get(0);

		EffectType effectType = actual.getEffectType();
		Direction direction = actual.getDirection();
		Value value = ((ValueEffect) effect).getValue();
		ValueType valueType = ((ValueEffect) effect).getValueType();
		AffectingEntity affEntity = ((ValueEffect) effect).getAffectingEntity();

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

		Effect actual = level.getEffects().get(Frequency.ONCEIMMEDIATE).get(0);

		EffectType effectType = actual.getEffectType();
		Direction direction = actual.getDirection();
		Value value = ((MultiValueEffect) effect).getValue();
		AffectingEntity affEntity = ((MultiValueEffect) effect).getAffectingEntity();
		HashMap<Enum, Integer> actualValues = ((MultiValueEffect) effect).getValues();

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
		HashMap<Frequency, ArrayList<Effect>> expectedEffects = new HashMap<Frequency, ArrayList<Effect>>();

		ArrayList<Effect> effect = new ArrayList<Effect>();
		effect.add(effect1);
		effect.add(effect2);

		expectedEffects.put(freq, effect);

		Level level = new Level(priority, cost, expectedEffects);

		HashMap<Frequency, ArrayList<Effect>> actualEffects = level.getEffects();

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

		EasyMock.replay(cost, endOfTurn, everyTurn, sixthTurn, onceImmediate, onceAge, endOfGame);

		int priority = 1;
		Level level1 = new Level(priority, cost, endOfTurn, Frequency.ENDOFTURN);
		Level level2 = new Level(priority, cost, everyTurn, Frequency.EVERYTURN);
		Level level3 = new Level(priority, cost, sixthTurn, Frequency.SIXTHTURN);
		Level level4 = new Level(priority, cost, onceImmediate, Frequency.ONCEIMMEDIATE);
		Level level5 = new Level(priority, cost, onceAge, Frequency.ONCEAGE);
		Level level6 = new Level(priority, cost, endOfGame, Frequency.ENDOFGAME);

		assertTrue(level1.getEffects().containsKey(Frequency.ENDOFTURN));
		assertTrue(level2.getEffects().containsKey(Frequency.EVERYTURN));
		assertTrue(level3.getEffects().containsKey(Frequency.SIXTHTURN));
		assertTrue(level4.getEffects().containsKey(Frequency.ONCEIMMEDIATE));
		assertTrue(level5.getEffects().containsKey(Frequency.ONCEAGE));
		assertTrue(level6.getEffects().containsKey(Frequency.ENDOFGAME));

		EasyMock.verify(cost, endOfTurn, everyTurn, sixthTurn, onceImmediate, onceAge, endOfGame);
	}

	@Test
	public void testMultiEffectFrequency() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect endOfTurn = EasyMock.createStrictMock(Effect.class);
		Effect everyTurn = EasyMock.createStrictMock(Effect.class);
		HashMap<Frequency, ArrayList<Effect>> effects = new HashMap<Frequency, ArrayList<Effect>>();

		ArrayList<Effect> effect = new ArrayList<Effect>();
		effect.add(endOfTurn);
		effects.put(Frequency.ENDOFTURN, effect);

		effect.add(everyTurn);
		effects.put(Frequency.EVERYTURN, effect);

		EasyMock.replay(cost, endOfTurn, everyTurn);

		int priority = 1;
		Level level1 = new Level(priority, cost, effects);
		assertEquals(effects, level1.getEffects());

		EasyMock.verify(cost, endOfTurn, everyTurn);
	}
}
