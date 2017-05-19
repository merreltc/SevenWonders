package dataStructureTests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;

import org.easymock.EasyMock;
import org.junit.Test;

import dataStructures.gameMaterials.AbilityEffect;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.Level;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.MultiValueEffect;
import dataStructures.gameMaterials.ValueEffect;

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
		Effect effect = EasyMock.createStrictMock(AbilityEffect.class);
		
		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.ABILITY);
		EasyMock.replay(effect);

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectSet = new HashSet<Effect>();
		effectSet.add(effect);
		effects.put(Frequency.ENDOFTURN, effectSet);

		Level level1 = new Level(priority, cost, effects);
		Level level2 = new Level(priority, cost, effects);
		assertTrue(level1.equals(level2));
		EasyMock.verify(effect);
	}
	
	@Test
	public void testEqualsEveryCombinationSingleEffect() {
		for (int priorityToUse = 0; priorityToUse <= 1; priorityToUse++){
			for (int costToUse = 0; costToUse <= 1; costToUse++){
				for (int effectToUse = 0; effectToUse <= 1; effectToUse++){
					runEqualsTest(priorityToUse, costToUse, effectToUse);
				}
			}
		}
		
	}
	
	private void runEqualsTest(int priorityToUse, int costToUse, int effectToUse){
		int priority = 1;
		Cost cost = EasyMock.createStrictMock(Cost.class);
		EntityEffect effect = EasyMock.createStrictMock(EntityEffect.class);
		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.ENTITY);
		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		EasyMock.replay(effect, cost);
		HashSet<Effect> effectSet = new HashSet<Effect>();
		effectSet.add(effect);
		effects.put(Frequency.ENDOFTURN, effectSet);
		Level level1 = new Level(priority, cost, effects);

		priority = priorityToUse;
		if (costToUse == 0){
			cost = EasyMock.createStrictMock(Cost.class);
			EasyMock.replay(cost);
		}
		if (effectToUse == 0){
			EntityEffect effect2 = EasyMock.createStrictMock(EntityEffect.class);
			EasyMock.expect(effect2.getEffectType()).andReturn(EffectType.ENTITY);
			effects = new HashMap<Frequency, HashSet<Effect>>();
			effectSet = new HashSet<Effect>();
			effectSet.add(effect2);
			effects.put(Frequency.DEFAULT, effectSet);
			EasyMock.replay(effect2);
		}
		
		
		Level level2 = new Level(priority, cost, effects);
		if (priorityToUse == 1 && costToUse == 1 &&  effectToUse == 1){
			assertTrue(level1.equals(level2));
		}else{
			assertFalse(level1.equals(level2));
		}
	}
	
	@Test
	public void testEqualsDifferentEffects(){
		int priority = 1;
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(AbilityEffect.class);
		
		EasyMock.replay(effect);

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectSet = new HashSet<Effect>();
		effectSet.add(effect);
		effects.put(Frequency.ENDOFTURN, effectSet);
		
		HashMap<Frequency, HashSet<Effect>> effects2 = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectSet2 = new HashSet<Effect>();
		effectSet2.add(effect);
		effects.put(Frequency.ENDOFTURN, effectSet2);

		Level level1 = new Level(priority, cost, effects);
		Level level2 = new Level(priority, cost, effects2);
		assertFalse(level1.equals(level2));
		EasyMock.verify(effect);
	}

	@Test
	public void testEqualsTrueMultiEffect() {
		int priority = 1;
		Cost cost = EasyMock.createStrictMock(Cost.class);
		Effect effect = EasyMock.createStrictMock(AbilityEffect.class);
		
		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.ABILITY);
		EasyMock.replay(effect);

		HashMap<Frequency, HashSet<Effect>> effects = new HashMap<Frequency, HashSet<Effect>>();
		HashSet<Effect> effectList = new HashSet<Effect>();
		effectList.add(effect);
		effects.put(Frequency.ONCEIMMEDIATE, effectList);

		Level level1 = new Level(priority, cost, effects);
		Level level2 = new Level(priority, cost, effects);
		assertTrue(level1.equals(level2));
		EasyMock.verify(effect);
	}

//	@Test
//	public void testEqualsFalseMultiEffect() {
//		Cost cost1 = EasyMock.createStrictMock(Cost.class);
//		Cost cost2 = EasyMock.createStrictMock(Cost.class);
//		Effect effect1 = EasyMock.createStrictMock(Effect.class);
//		Effect effect2 = EasyMock.createStrictMock(Effect.class);
//		int priority1 = 1;
//		int priority2 = 2;
//
//		HashMap<Frequency, HashSet<Effect>> effects1 = new HashMap<Frequency, HashSet<Effect>>();
//		HashSet<Effect> effect = new HashSet<Effect>();
//		effect.add(effect1);
//		effects1.put(Frequency.ONCEIMMEDIATE, effect);
//
//		HashMap<Frequency, HashSet<Effect>> effects2 = new HashMap<Frequency, HashSet<Effect>>();
//		effect = new HashSet<Effect>();
//		effect.add(effect2);
//		effects2.put(Frequency.ONCEIMMEDIATE, effect);
//
//		Level level1 = new Level(priority1, cost1, effects1);
//		Level level2 = new Level(priority2, cost2, effects2);
//		assertFalse(level1.equals(level2));
//	}

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
