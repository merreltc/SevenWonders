package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;

import dataStructures.Effect;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.EntityEffect.Science;
import dataStructures.ValueEffect;
import dataStructures.ValueEffect.AffectingEntity;
import dataStructures.ValueEffect.Value;
import dataStructures.ValueEffect.ValueType;

public class ValueEffectTest {
	@Test
	public void test2VictoryPointsValueEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 2);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(2, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.VICTORYPOINTS, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test8VictoryPointsValueEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 8);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(8, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.VICTORYPOINTS, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue0Effect(){
		new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 0);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidVictoryPointsValueNeg1Effect(){
		new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, -1);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue9Effect(){
		new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 9);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidVictoryPointsValue10Effect(){
		new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 10);
		fail();
	}
	
	@Test
	public void testInvalidVictoryPointsValue0EffectMessage(){
		try{
			new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 0);
		}catch(IllegalArgumentException error){
			assertEquals("Cannot have valueAmount of 0", error.getMessage());
		}
	}
	
	@Test
	public void testInvalidVictoryPointsValue10EffectMessage(){
		try{
			new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 10);
		}catch(IllegalArgumentException error){
			assertEquals("Cannot have valueAmount of 10", error.getMessage());
		}
	}

	@Test
	public void test1MilitaryValueEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.MILITARY, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.CONFLICTTOKEN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test3MilitaryValueEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 3);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(3, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.MILITARY, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.CONFLICTTOKEN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void testNeg1MilitaryValueEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, -1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(-1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.MILITARY, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.CONFLICTTOKEN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidMilitaryValue0Effect(){
	    new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 0);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidMilitaryValueNeg2Effect(){
	    new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, -2);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidMilitaryValue8Effect(){
	    new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 8);
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidMilitaryValue4Effect(){
	    new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 4);
		fail();
	}
	
	@Test
	public void test1CommerceValueRightEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.RIGHT, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.RIGHT, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.RAWRESOURCES, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1CommerceValueLeftEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.RAWRESOURCES, Direction.LEFT, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.LEFT, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.RAWRESOURCES, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1CommerceValueAllEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS, Direction.ALL, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.ALL, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test5CommerceValueNoneEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.NONE, 5);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(5, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.NONE, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidCommerceValueNoneEffect0(){
		new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.NONE, 0);
		fail();
	}
	
	@Test
	public void test1CommerceValueNeighborsEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.MANUFACTUREDGOODS, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.COMMERCE, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.COIN, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.MANUFACTUREDGOODS, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueCommercialStructuresEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.COMMERCIALSTRUCTURES, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.COMMERCIALSTRUCTURES, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueScientificStructuresEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.SCIENTIFICSTRUCTURES, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.SCIENTIFICSTRUCTURES, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueMilitaryStructuresEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.MILITARYSTRUCTURES, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.MILITARYSTRUCTURES, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueCivilianStructuresEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.CIVILIANSTRUCTURES, Direction.NEIGHBORS, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.CIVILIANSTRUCTURES, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueGuildEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.GUILD, Direction.SELF, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.GUILD, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void test1GuildValueWonderLevelEffect(){
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.WONDERLEVEL, Direction.ALL, 1);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.ALL, effect.getDirection());
		assertEquals(1, ((ValueEffect) effect).getValueAmount());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(AffectingEntity.WONDERLEVEL, ((ValueEffect) effect).getAffectingEntity());
	}
	
	@Test
	public void testThreeValueAffectingEntityEffect () {
		HashMap<Enum, Integer> affectingEntities = new HashMap<Enum, Integer>();
		affectingEntities.put(AffectingEntity.RAWRESOURCES, 1);
		affectingEntities.put(AffectingEntity.MANUFACTUREDGOODS, 1);
		affectingEntities.put(AffectingEntity.GUILD, 1);
		
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, affectingEntities);
		
		assertEquals(EffectType.VALUE, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(Value.GUILD, ((ValueEffect) effect).getValue());
		assertEquals(ValueType.VICTORYPOINT, ((ValueEffect) effect).getValueType());
		assertEquals(affectingEntities, ((ValueEffect) effect).getAffectingEntities());
	}
}
