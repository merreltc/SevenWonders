package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Science;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;

public class EntityEffectTest {

	@Test
	public void test1LumberEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test2LumberEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 2);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test1ClayEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.CLAY, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test2ClayEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.CLAY, 2);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test1OreEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.ORE, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test2OreEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.ORE, 2);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test1StoneEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.STONE, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test2StoneEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.STONE, 2);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testLumberClayEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 1);
		resourcesAndAmount.put(RawResource.CLAY, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testStoneClayEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.STONE, 1);
		resourcesAndAmount.put(RawResource.CLAY, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testClayOreEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.CLAY, 1);
		resourcesAndAmount.put(RawResource.ORE, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testStoneLumberEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.STONE, 1);
		resourcesAndAmount.put(RawResource.LUMBER, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testLumberOreEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 1);
		resourcesAndAmount.put(RawResource.ORE, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testOreStoneEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.ORE, 1);
		resourcesAndAmount.put(RawResource.STONE, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testAllResourcesEntityEffect() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 1);
		resourcesAndAmount.put(RawResource.CLAY, 1);
		resourcesAndAmount.put(RawResource.ORE, 1);
		resourcesAndAmount.put(RawResource.STONE, 1);

		Effect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.RESOURCE, ((EntityEffect) effect).getEntityType());
		assertEquals(resourcesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test1LoomEntityEffect() {
		HashMap<Enum, Integer> goodsAndAmount = new HashMap<Enum, Integer>();
		goodsAndAmount.put(Good.LOOM, 1);

		Effect effect = new EntityEffect(EntityType.MANUFACTUREDGOOD, goodsAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.MANUFACTUREDGOOD, ((EntityEffect) effect).getEntityType());
		assertEquals(goodsAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test1GlassEntityEffect() {
		HashMap<Enum, Integer> goodsAndAmount = new HashMap<Enum, Integer>();
		goodsAndAmount.put(Good.GLASS, 1);

		Effect effect = new EntityEffect(EntityType.MANUFACTUREDGOOD, goodsAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.MANUFACTUREDGOOD, ((EntityEffect) effect).getEntityType());
		assertEquals(goodsAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test1PressEntityEffect() {
		HashMap<Enum, Integer> goodsAndAmount = new HashMap<Enum, Integer>();
		goodsAndAmount.put(Good.PRESS, 1);

		Effect effect = new EntityEffect(EntityType.MANUFACTUREDGOOD, goodsAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.MANUFACTUREDGOOD, ((EntityEffect) effect).getEntityType());
		assertEquals(goodsAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testAllGoodsEntityEffect() {
		HashMap<Enum, Integer> goodsAndAmount = new HashMap<Enum, Integer>();
		goodsAndAmount.put(Good.LOOM, 1);
		goodsAndAmount.put(Good.GLASS, 1);
		goodsAndAmount.put(Good.PRESS, 1);

		Effect effect = new EntityEffect(EntityType.MANUFACTUREDGOOD, goodsAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.MANUFACTUREDGOOD, ((EntityEffect) effect).getEntityType());
		assertEquals(goodsAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test1ProtractorEntityEffect() {
		HashMap<Enum, Integer> sciencesAndAmount = new HashMap<Enum, Integer>();
		sciencesAndAmount.put(Science.PROTRACTOR, 1);

		Effect effect = new EntityEffect(EntityType.SCIENCE, sciencesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.SCIENCE, ((EntityEffect) effect).getEntityType());
		assertEquals(sciencesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test1WheelEntityEffect() {
		HashMap<Enum, Integer> sciencesAndAmount = new HashMap<Enum, Integer>();
		sciencesAndAmount.put(Science.WHEEL, 1);

		Effect effect = new EntityEffect(EntityType.SCIENCE, sciencesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.SCIENCE, ((EntityEffect) effect).getEntityType());
		assertEquals(sciencesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void test1TabletEntityEffect() {
		HashMap<Enum, Integer> sciencesAndAmount = new HashMap<Enum, Integer>();
		sciencesAndAmount.put(Science.TABLET, 1);

		Effect effect = new EntityEffect(EntityType.SCIENCE, sciencesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.SCIENCE, ((EntityEffect) effect).getEntityType());
		assertEquals(sciencesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testAllScienceEntityEffect() {
		HashMap<Enum, Integer> sciencesAndAmount = new HashMap<Enum, Integer>();
		sciencesAndAmount.put(Science.PROTRACTOR, 1);
		sciencesAndAmount.put(Science.WHEEL, 1);
		sciencesAndAmount.put(Science.TABLET, 1);

		Effect effect = new EntityEffect(EntityType.SCIENCE, sciencesAndAmount);

		assertEquals(EffectType.ENTITY, effect.getEffectType());
		assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EntityType.SCIENCE, ((EntityEffect) effect).getEntityType());
		assertEquals(sciencesAndAmount, ((EntityEffect) effect).getEntities());
	}

	@Test
	public void testValidEqualsResource() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 1);

		EntityEffect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);
		HashMap<Enum, Integer> resourcesAndAmount2 = new HashMap<Enum, Integer>();
		resourcesAndAmount2.put(RawResource.LUMBER, 1);

		EntityEffect effect2 = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount2);

		assertTrue(effect.equals(effect2));
	}

	@Test
	public void testInalidEqualsResourceDifferentAmounts() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 3);

		EntityEffect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);
		HashMap<Enum, Integer> resourcesAndAmount2 = new HashMap<Enum, Integer>();
		resourcesAndAmount2.put(RawResource.LUMBER, 1);

		EntityEffect effect2 = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount2);

		assertFalse(effect.equals(effect2));
	}

	@Test
	public void testInalidEqualsResourceDifferentTypes() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.LUMBER, 1);

		EntityEffect effect = new EntityEffect(EntityType.MANUFACTUREDGOOD, resourcesAndAmount);
		HashMap<Enum, Integer> resourcesAndAmount2 = new HashMap<Enum, Integer>();
		resourcesAndAmount2.put(RawResource.LUMBER, 1);

		EntityEffect effect2 = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount2);

		assertFalse(effect.equals(effect2));
	}

	@Test
	public void testInalidEqualsResourceDifferentResources() {
		HashMap<Enum, Integer> resourcesAndAmount = new HashMap<Enum, Integer>();
		resourcesAndAmount.put(RawResource.ORE, 1);

		EntityEffect effect = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount);
		HashMap<Enum, Integer> resourcesAndAmount2 = new HashMap<Enum, Integer>();
		resourcesAndAmount2.put(RawResource.LUMBER, 1);

		EntityEffect effect2 = new EntityEffect(EntityType.RESOURCE, resourcesAndAmount2);

		assertFalse(effect.equals(effect2));
	}
}