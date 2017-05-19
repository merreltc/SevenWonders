package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;

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
	public void testToStringMultiEffect() {
		int priority = 1;
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectList = new HashSet<Effect>();
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

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectSet = new HashSet<Effect>();
		effectSet.add(effect);
		effects.put(Frequency.ENDOFTURN, effectSet);

		Level level1 = new Level(priority, cost, effects);
		Level level2 = new Level(priority, cost, effects);
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

		HashMap<Frequency, HashSet<Effect>> effects1 = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectSet = new HashSet<Effect>();
		effectSet.add(effect1);
		effects1.put(Frequency.ENDOFTURN, effectSet);

		HashMap<Frequency, HashSet<Effect>> effects2 = new HashMap<Frequency, HashSet<Effect>>();
		effectSet = new HashSet<Effect>();
		effectSet.add(effect2);
		effects2.put(Frequency.ENDOFTURN, effectSet);

		Level level1 = new Level(priority1, cost1, effects1);
		Level level2 = new Level(priority2, cost2, effects2);
		assertFalse(level1.equals(level2));
	}

	@Test
	public void testEqualsTrueMultiEffect() {
		int priority = 1;
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectList = new HashSet<Effect>();
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

		HashMap<Frequency, HashSet<Effect>> effects1 = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effect = new HashSet<Effect>();
		effect.add(effect1);
		effects1.put(Frequency.ONCEIMMEDIATE, effect);

		HashMap<Frequency, HashSet<Effect>> effects2 = new HashMap<Frequency, HashSet<Effect>>();
		effect = new HashSet<Effect>();
		effect.add(effect2);
		effects2.put(Frequency.ONCEIMMEDIATE, effect);

		Level level1 = new Level(priority1, cost1, effects1);
		Level level2 = new Level(priority2, cost2, effects2);
		assertFalse(level1.equals(level2));
	}

	@Test
	public void testGetCost() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		int priority = 1;

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectSet = new HashSet<Effect>();
		effectSet.add(effect);
		effects.put(Frequency.ENDOFTURN, effectSet);

		Level level = new Level(priority, cost, effects);
		assertEquals(cost, level.getCost());
	}

	@Test
	public void testPriority1() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectSet = new HashSet<Effect>();
		effectSet.add(effect);
		effects.put(Frequency.ENDOFTURN, effectSet);

		int priority = 1;
		Level level = new Level(priority, cost, effects);
		assertEquals(1, level.getPriority());
	}

	@Test
	public void testPriority2() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectSet = new HashSet<Effect>();
		effectSet.add(effect);
		effects.put(Frequency.ENDOFTURN, effectSet);

		int priority = 2;
		Level level = new Level(priority, cost, effects);
		assertEquals(2, level.getPriority());
	}

	@Test
	public void testPriority3() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectSet = new HashSet<Effect>();
		effectSet.add(effect);
		effects.put(Frequency.ENDOFTURN, effectSet);

		int priority = 3;
		Level level = new Level(priority, cost, effects);
		assertEquals(3, level.getPriority());
	}

	@Test
	public void testMultipleEffects() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		MultiValueEffect effect1 = EasyMock.createStrictMock(MultiValueEffect.class);
		ValueEffect effect2 = EasyMock.createStrictMock(ValueEffect.class);
		EasyMock.replay(effect1, effect2);

		int priority = 1;
		Frequency freq = Frequency.ONCEIMMEDIATE;
		HashMap<Frequency, HashSet<Effect>> expectedEffects = new HashMap<Frequency, HashSet<Effect>>();

		HashSet<Effect> effect = new HashSet<Effect>();
		effect.add(effect1);
		effect.add(effect2);

		expectedEffects.put(freq, effect);

		Level level = new Level(priority, cost, expectedEffects);

		HashMap<Frequency, HashSet<Effect>> actualEffects = level.getEffects();

		EasyMock.verify(effect1, effect2);
		assertEquals(expectedEffects, actualEffects);
	}

	@Test
	public void testMultiEffectFrequency() {
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect endOfTurn = EasyMock.createStrictMock(Effect.class);
		Effect everyTurn = EasyMock.createStrictMock(Effect.class);
		Effect sixthTurn = EasyMock.createStrictMock(Effect.class);
		Effect onceImmediate = EasyMock.createStrictMock(Effect.class);
		Effect onceAge = EasyMock.createStrictMock(Effect.class);
		Effect endOfGame = EasyMock.createStrictMock(Effect.class);
		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();

		HashSet<Effect> effect = new HashSet<Effect>();
		effect.add(endOfTurn);
		effects.put(Frequency.ENDOFTURN, effect);

		effect.add(everyTurn);
		effects.put(Frequency.EVERYTURN, effect);

		effect.add(sixthTurn);
		effects.put(Frequency.SIXTHTURN, effect);

		effect.add(onceImmediate);
		effects.put(Frequency.ONCEIMMEDIATE, effect);

		effect.add(onceAge);
		effects.put(Frequency.ONCEAGE, effect);

		effect.add(endOfGame);
		effects.put(Frequency.ENDOFGAME, effect);

		EasyMock.replay(cost, endOfTurn, everyTurn);

		int priority = 1;
		Level level = new Level(priority, cost, effects);

		assertTrue(level.getEffects().containsKey(Frequency.ENDOFTURN));
		assertTrue(level.getEffects().containsKey(Frequency.EVERYTURN));
		assertTrue(level.getEffects().containsKey(Frequency.SIXTHTURN));
		assertTrue(level.getEffects().containsKey(Frequency.ONCEIMMEDIATE));
		assertTrue(level.getEffects().containsKey(Frequency.ONCEAGE));
		assertTrue(level.getEffects().containsKey(Frequency.ENDOFGAME));
		assertEquals(effects, level.getEffects());

		EasyMock.verify(cost, endOfTurn, everyTurn);
	}
}
