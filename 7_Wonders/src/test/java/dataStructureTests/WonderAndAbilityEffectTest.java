package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import dataStructures.gameMaterials.AbilityEffect;
import dataStructures.gameMaterials.AbilityEffect.Ability;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.Level.Frequency;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;

public class WonderAndAbilityEffectTest {
	@Test
	public void testAbilityEffect() {
		AbilityEffect effect = new AbilityEffect(Ability.PLAYSEVENTH);
		assertEquals(EffectType.ABILITY, effect.getEffectType());
		assertEquals(Ability.PLAYSEVENTH, effect.getAbility());

		effect = new AbilityEffect(Ability.FREEBUILD);
		assertEquals(EffectType.ABILITY, effect.getEffectType());
		assertEquals(Ability.FREEBUILD, effect.getAbility());

		effect = new AbilityEffect(Ability.FREEBUILDFROMDISCARD);
		assertEquals(EffectType.ABILITY, effect.getEffectType());
		assertEquals(Ability.FREEBUILDFROMDISCARD, effect.getAbility());

		effect = new AbilityEffect(Ability.COPYONENEIGHBORGUILD);
		assertEquals(EffectType.ABILITY, effect.getEffectType());
		assertEquals(Ability.COPYONENEIGHBORGUILD, effect.getAbility());
	}

	@Test
	public void testAllFrequencies() {
		HashMap<Frequency, ArrayList<Effect>> effects = new HashMap<Frequency, ArrayList<Effect>>();

		ArrayList<Effect> effect1 = new ArrayList<Effect>();
		effect1.add(new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2));
		effects.put(Frequency.ENDOFTURN, effect1);

		ArrayList<Effect> effect2 = new ArrayList<Effect>();
		effect2.add(new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2));
		effects.put(Frequency.EVERYTURN, effect2);

		ArrayList<Effect> effect3 = new ArrayList<Effect>();
		effect3.add(new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2));
		effects.put(Frequency.SIXTHTURN, effect3);

		ArrayList<Effect> effect4 = new ArrayList<Effect>();
		effect4.add(new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2));
		effects.put(Frequency.ONCEIMMEDIATE, effect4);

		ArrayList<Effect> effect5 = new ArrayList<Effect>();
		effect5.add(new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2));
		effects.put(Frequency.ONCEAGE, effect5);

		ArrayList<Effect> effect6 = new ArrayList<Effect>();
		effect6.add(new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2));
		effects.put(Frequency.ENDOFGAME, effect6);

		assertTrue(effects.containsKey(Frequency.ENDOFTURN));
		assertTrue(effects.containsKey(Frequency.EVERYTURN));
		assertTrue(effects.containsKey(Frequency.SIXTHTURN));
		assertTrue(effects.containsKey(Frequency.ONCEIMMEDIATE));
		assertTrue(effects.containsKey(Frequency.ONCEAGE));
		assertTrue(effects.containsKey(Frequency.ENDOFGAME));
	}

}
