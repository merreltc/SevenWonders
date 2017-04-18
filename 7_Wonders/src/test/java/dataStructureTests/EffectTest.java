package dataStructureTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import dataStructures.Effect;
import dataStructures.Effect.AffectingEntity;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.Effect.Entity;
import dataStructures.Effect.Resource;

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
}
