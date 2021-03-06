package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.gameMaterials.ValueEffect.ValueType;

public class ValueEffectTest {
	@Test
	public void test2VictoryPointsValueEffect() {
		Effect effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.VICTORYPOINTS, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test8VictoryPointsValueEffect() {
		Effect effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 8);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(8, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.VICTORYPOINTS, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void testValueEffectThirdConstructor() {
		Effect effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, Direction.LEFT);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.LEFT, effect.getDirection());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue0Effect() {
		new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 0);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue0EffectSecondConstructor() {
		new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 0);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidVictoryPointsValueNeg1Effect() {
		new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, -1);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue9Effect() {
		new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 9);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue10Effect() {
		new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 10);
		fail();
	}

	@Test
	public void testInvalidVictoryPointsValue0EffectMessage() {
		try {
			new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 0);
		} catch (IllegalArgumentException error) {
			assertEquals("Cannot have valueAmount of 0", error.getMessage());
		}
	}

	@Test
	public void testInvalidVictoryPointsValue10EffectMessage() {
		try {
			new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 10);
		} catch (IllegalArgumentException error) {
			assertEquals("Cannot have valueAmount of 10", error.getMessage());
		}
	}

	@Test
	public void test1MilitaryValueEffect() {
		Effect effect = new ValueEffect(Value.MILITARY, AffectingEntity.NONE, 1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.MILITARY, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.CONFLICTTOKEN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test3MilitaryValueEffect() {
		Effect effect = new ValueEffect(Value.MILITARY, AffectingEntity.NONE, 3);
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(3, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.MILITARY, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.CONFLICTTOKEN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void testNeg1MilitaryValueEffect() {
		Effect effect = new ValueEffect(Value.MILITARY, AffectingEntity.NONE, -1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(-1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.MILITARY, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.CONFLICTTOKEN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMilitaryValue0Effect() {
		new ValueEffect(Value.MILITARY, AffectingEntity.NONE, 0);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMilitaryValueNeg2Effect() {
		new ValueEffect(Value.MILITARY, AffectingEntity.NONE, -2);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMilitaryValue8Effect() {
		new ValueEffect(Value.MILITARY, AffectingEntity.NONE, 8);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMilitaryValue4Effect() {
		new ValueEffect(Value.MILITARY, AffectingEntity.NONE, 4);
		fail();
	}

	@Test
	public void test1CommerceValueRightEffect() {
		Effect effect = new ValueEffect(Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.RIGHT, 1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.RIGHT, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.RAWRESOURCES, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test1CommerceValueLeftEffect() {
		Effect effect = new ValueEffect(Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.LEFT,1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.LEFT, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.RAWRESOURCES, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test1CommerceValueAllEffect() {
		Effect effect = new ValueEffect(Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS,
				Direction.ALL, 1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.ALL, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test5CommerceValueNoneEffect() {
		Effect effect = new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 5);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(5, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCommerceValueNoneEffect0() {
		new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, 0);

		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidCommerceValueNoneEffectNeg1() {
		new ValueEffect(Value.COMMERCE, AffectingEntity.NONE, -1);

		fail();
	}

	@Test
	public void test1CommerceValueNeighborsEffect() {
		Effect effect = new ValueEffect(Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS,
				Direction.NEIGHBORS, 1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test1GuildValueCommercialStructuresEffect() {
		Effect effect = new ValueEffect(Value.GUILD, AffectingEntity.COMMERCIALSTRUCTURES,
				Direction.NEIGHBORS, 1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.COMMERCIALSTRUCTURES, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test1GuildValueScientificStructuresEffect() {
		Effect effect = new ValueEffect(Value.GUILD, AffectingEntity.SCIENTIFICSTRUCTURES,
				Direction.NEIGHBORS, 1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.SCIENTIFICSTRUCTURES, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test1GuildValueMilitaryStructuresEffect() {
		Effect effect = new ValueEffect(Value.GUILD, AffectingEntity.MILITARYSTRUCTURES,
				Direction.NEIGHBORS, 1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.MILITARYSTRUCTURES, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test1GuildValueCivilianStructuresEffect() {
		Effect effect = new ValueEffect(Value.GUILD, AffectingEntity.CIVILIANSTRUCTURES,
				Direction.NEIGHBORS, 1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.CIVILIANSTRUCTURES, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test1GuildValueGuildEffect() {
		Effect effect = new ValueEffect(Value.GUILD, AffectingEntity.GUILD, Direction.SELF, 1);
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.GUILD, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void test1GuildValueWonderLevelEffect() {
		Effect effect = new ValueEffect(Value.GUILD, AffectingEntity.WONDERLEVEL, Direction.ALL, 1);

		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.ALL, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.WONDERLEVEL, ((ValueEffect) effect).getAffectingEntity());
	}

	@Test
	public void testThreeValueAffectingEntityEffect() {
		HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();
		affectingEntities.put(AffectingEntity.RAWRESOURCES, 1);
		affectingEntities.put(AffectingEntity.MANUFACTUREDGOODS, 1);
		affectingEntities.put(AffectingEntity.GUILD, 1);

		Effect effect = new ValueEffect(Value.GUILD, affectingEntities);
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(affectingEntities, ((ValueEffect) effect).getAffectingEntities());
	}

	@Test
	public void testValidValueEffectEqualsNoAffectingentities() {
		ValueEffect effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2);
		ValueEffect effect2 = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2);

		assertTrue(effect.equals(effect2));
	}

	@Test
	public void testInvalidValueEffectEqualsNoAffectingentities() {
		ValueEffect effect = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 3);
		ValueEffect effect2 = new ValueEffect(Value.VICTORYPOINTS, AffectingEntity.NONE, 2);

		assertFalse(effect.equals(effect2));
	}

	@Test
	public void testValidValueEffectEqualsCommerce() {
		ValueEffect effect = new ValueEffect(Value.COMMERCE, AffectingEntity.RAWRESOURCES,
				Direction.RIGHT, 1);
		ValueEffect effect2 = new ValueEffect(Value.COMMERCE, AffectingEntity.RAWRESOURCES,
				Direction.RIGHT, 1);

		assertTrue(effect.equals(effect2));
	}

	@Test
	public void testInvalidValueEffectEqualsCommerce() {
		ValueEffect effect = new ValueEffect(Value.COMMERCE, AffectingEntity.RAWRESOURCES,
				Direction.RIGHT, 1);
		ValueEffect effect2 = new ValueEffect(Value.COMMERCE, AffectingEntity.RAWRESOURCES,
				Direction.LEFT, 1);

		assertFalse(effect.equals(effect2));
	}

	@Test
	public void testInvalidValueEffectEqualsCommerceAffecting() {
		ValueEffect effect = new ValueEffect(Value.COMMERCE, AffectingEntity.RAWRESOURCES,
				Direction.RIGHT, 1);
		ValueEffect effect2 = new ValueEffect(Value.COMMERCE, AffectingEntity.COMMERCIALSTRUCTURES,
				Direction.RIGHT, 1);

		assertFalse(effect.equals(effect2));
	}


	@Test
	public void testInvalidValueEffectEqualsGuild() {
		ValueEffect effect = new ValueEffect(Value.GUILD, AffectingEntity.RAWRESOURCES,
				Direction.RIGHT, 1);
		ValueEffect effect2 = new ValueEffect(Value.COMMERCE, AffectingEntity.RAWRESOURCES,
				Direction.RIGHT, 1);

		assertFalse(effect.equals(effect2));
	}

	@Test
	public void testValidValueEffectEqualsGuild() {
		HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();
		affectingEntities.put(AffectingEntity.RAWRESOURCES, 1);
		affectingEntities.put(AffectingEntity.MANUFACTUREDGOODS, 1);
		affectingEntities.put(AffectingEntity.GUILD, 1);

		ValueEffect effect = new ValueEffect(Value.GUILD, affectingEntities);
		HashMap<Enum, Integer> affectingEntities2 = new HashMap<Enum, Integer>();
		affectingEntities2.put(AffectingEntity.RAWRESOURCES, 1);
		affectingEntities2.put(AffectingEntity.MANUFACTUREDGOODS, 1);
		affectingEntities2.put(AffectingEntity.GUILD, 1);

		ValueEffect effect2 = new ValueEffect(Value.GUILD, affectingEntities2);

		assertTrue(effect.equals(effect2));
	}

	@Test
	public void testInvalidValueEffectEqualsGuildAffecting(){
		HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();
		affectingEntities.put(AffectingEntity.RAWRESOURCES, 6);
		affectingEntities.put(AffectingEntity.MANUFACTUREDGOODS, 1);
		affectingEntities.put(AffectingEntity.GUILD, 1);
		
		ValueEffect effect = new ValueEffect(Value.GUILD, affectingEntities);
		HashMap<Enum, Integer> affectingEntities2 = new HashMap<Enum, Integer>();
		affectingEntities2.put(AffectingEntity.RAWRESOURCES, 1);
		affectingEntities2.put(AffectingEntity.MANUFACTUREDGOODS, 1);
		affectingEntities2.put(AffectingEntity.GUILD, 1);
		
		ValueEffect effect2 = new ValueEffect(Value.GUILD, affectingEntities2);
		
		assertFalse(effect.equals(effect2));
	}
}
