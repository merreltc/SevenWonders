package dataStructureTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.MultiValueEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.playerData.StoragePile;

public class StoragePileTest {

	@Test
	public void testDefaultStoragePile() {
		StoragePile storagePile = new StoragePile();

		assertTrue(storagePile.getCommercePile().isEmpty());
		assertTrue(storagePile.getSciencePile().isEmpty());
		assertTrue(storagePile.getEndGamePile().isEmpty());
		assertTrue(storagePile.getImmediateEffectPile().isEmpty());
		assertTrue(storagePile.getWonderPile().isEmpty());
		assertTrue(storagePile.getEntireEffectStorage().isEmpty());
	}

	@Test
	public void testAddToCommercePile() {
		Card card = EasyMock.mock(Card.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.replay(card, effect);
		
		StoragePile storagePile = new StoragePile();
		storagePile.addToCommercePile(card);

		assertFalse(storagePile.getCommercePile().isEmpty());
		assertEquals(card, storagePile.getCommercePile().get(0));
		assertEquals(effect, storagePile.getEntireEffectStorage().get(0));
		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToSciencePile() {
		Card card = EasyMock.mock(Card.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.replay(card, effect);
		
		StoragePile storagePile = new StoragePile();
		storagePile.addToSciencePile(card);

		assertTrue(storagePile.getCommercePile().isEmpty());
		assertEquals(card, storagePile.getSciencePile().get(0));
		assertEquals(effect, storagePile.getEntireEffectStorage().get(0));
		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToEndGamePile() {
		Card card = EasyMock.mock(Card.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.replay(card, effect);
		
		StoragePile storagePile = new StoragePile();
		storagePile.addToEndGamePile(card);

		assertTrue(storagePile.getSciencePile().isEmpty());
		assertEquals(card, storagePile.getEndGamePile().get(0));
		assertEquals(effect, storagePile.getEntireEffectStorage().get(0));
		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToImmediateEffectPile() {
		Card card = EasyMock.mock(Card.class);
		Effect effect = EasyMock.createStrictMock(Effect.class);
		
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.replay(card, effect);
		
		StoragePile storagePile = new StoragePile();
		storagePile.addToImmediateEffectPile(card);

		assertTrue(storagePile.getSciencePile().isEmpty());
		assertEquals(card, storagePile.getImmediateEffectPile().get(0));
		assertEquals(effect, storagePile.getEntireEffectStorage().get(0));
		EasyMock.verify(card, effect);
	}
	
	@Test
	public void testAddToWonderPile() {
		StoragePile storagePile = new StoragePile();
		Effect effect = EasyMock.createStrictMock(Effect.class);

		storagePile.addToWonderPile(effect);

		assertEquals(effect, storagePile.getWonderPile().get(0));
		assertEquals(effect, storagePile.getEntireEffectStorage().get(0));
	}

	@Test
	public void testAddToCommercePileManyCards() {
		StoragePile storagePile = new StoragePile();

		for (int i = 0; i < 50; i++) {
			Card card = EasyMock.mock(Card.class);
			storagePile.addToCommercePile(card);
		}

		assertFalse(storagePile.getCommercePile().isEmpty());
		assertEquals(50, storagePile.getCommercePile().size());
	}

	@Test
	public void testAddToSciencePileManyCards() {
		StoragePile storagePile = new StoragePile();

		for (int i = 0; i < 50; i++) {
			Card card = EasyMock.mock(Card.class);
			storagePile.addToSciencePile(card);
		}

		assertFalse(storagePile.getSciencePile().isEmpty());
		assertEquals(50, storagePile.getSciencePile().size());
	}

	@Test
	public void testAddToEndGamePileManyCards() {
		StoragePile storagePile = new StoragePile();

		for (int i = 0; i < 50; i++) {
			Card card = EasyMock.mock(Card.class);
			storagePile.addToEndGamePile(card);
		}

		assertFalse(storagePile.getEndGamePile().isEmpty());
		assertEquals(50, storagePile.getEndGamePile().size());
	}

	@Test
	public void testAddToImmediateEffectPileManyCards() {
		StoragePile storagePile = new StoragePile();

		for (int i = 0; i < 50; i++) {
			Card card = EasyMock.mock(Card.class);
			storagePile.addToImmediateEffectPile(card);
		}

		assertFalse(storagePile.getImmediateEffectPile().isEmpty());
		assertEquals(50, storagePile.getImmediateEffectPile().size());
	}

	@Test
	public void testGetAllCardStoragePile() {
		StoragePile storagePile = new StoragePile();
		Card card = EasyMock.mock(Card.class);
		Card card2 = EasyMock.mock(Card.class);
		Card card3 = EasyMock.mock(Card.class);
		Card card4 = EasyMock.mock(Card.class);

		storagePile.addToCommercePile(card);
		storagePile.addToSciencePile(card2);
		storagePile.addToEndGamePile(card3);
		storagePile.addToImmediateEffectPile(card4);

		assertEquals(1, storagePile.getCommercePile().size());
		assertFalse(storagePile.getEndGamePile().contains(card));
		assertTrue(storagePile.getCommercePile().contains(card));
		assertEquals(1, storagePile.getEndGamePile().size());
		assertEquals(4, storagePile.getAllCardStoragePile().size());
		assertTrue(storagePile.getAllCardStoragePile().contains(card));
		assertTrue(storagePile.getAllCardStoragePile().contains(card2));
		assertTrue(storagePile.getAllCardStoragePile().contains(card3));
		assertTrue(storagePile.getAllCardStoragePile().contains(card4));
	}

	@Test
	public void testGetEntireStoragePileMany() {
		StoragePile storagePile = new StoragePile();
		for (int i = 0; i < 50; i++) {
			Card card = EasyMock.mock(Card.class);
			storagePile.addToCommercePile(card);
			assertTrue(storagePile.getAllCardStoragePile().contains(card));
		}
		for (int i = 0; i < 50; i++) {
			Card card = EasyMock.mock(Card.class);
			storagePile.addToSciencePile(card);
			assertTrue(storagePile.getAllCardStoragePile().contains(card));
		}
		for (int i = 0; i < 50; i++) {
			Card card = EasyMock.mock(Card.class);
			storagePile.addToEndGamePile(card);
			assertTrue(storagePile.getAllCardStoragePile().contains(card));
		}
		for (int i = 0; i < 50; i++) {
			Card card = EasyMock.mock(Card.class);
			storagePile.addToImmediateEffectPile(card);
			assertTrue(storagePile.getAllCardStoragePile().contains(card));
		}

		assertEquals(200, storagePile.getAllCardStoragePile().size());
	}

	@Test
	public void testAddToStoragePileResource() {
		StoragePile storagePile = new StoragePile();

		Card card = EasyMock.mock(Card.class);
		EntityEffect effect = EasyMock.mock(EntityEffect.class);

		EasyMock.expect(card.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getEntityType()).andReturn(EntityType.RESOURCE);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		
		EasyMock.replay(card, effect);

		storagePile.addCard(card);

		assertTrue(storagePile.getCommercePile().contains(card));
	}

	@Test
	public void testAddToStoragePileScience() {
		StoragePile storagePile = new StoragePile();

		Card card = EasyMock.mock(Card.class);
		EntityEffect effect = EasyMock.mock(EntityEffect.class);

		EasyMock.expect(card.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getEntityType()).andReturn(EntityType.SCIENCE);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		
		EasyMock.replay(card, effect);

		storagePile.addCard(card);

		assertTrue(storagePile.getSciencePile().contains(card));

		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToStoragePileGood() {
		StoragePile storagePile = new StoragePile();

		Card card = EasyMock.mock(Card.class);
		EntityEffect effect = EasyMock.mock(EntityEffect.class);

		EasyMock.expect(card.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getEntityType()).andReturn(EntityType.MANUFACTUREDGOOD);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		
		EasyMock.replay(card, effect);

		storagePile.addCard(card);

		assertTrue(storagePile.getCommercePile().contains(card));

		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToStoragePileGuild() {
		StoragePile storagePile = new StoragePile();

		Card card = EasyMock.mock(Card.class);
		ValueEffect effect = EasyMock.mock(ValueEffect.class);

		EasyMock.expect(card.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getValue()).andReturn(Value.GUILD);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		
		EasyMock.replay(card, effect);

		storagePile.addCard(card);

		assertTrue(storagePile.getEndGamePile().contains(card));

		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToStoragePileEndGameCommerce() {
		StoragePile storagePile = new StoragePile();

		Card card = EasyMock.mock(Card.class);
		ValueEffect effect = EasyMock.mock(ValueEffect.class);

		EasyMock.expect(card.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getValue()).andReturn(Value.COMMERCE);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.ALL);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		
		EasyMock.replay(card, effect);

		storagePile.addCard(card);

		assertTrue(storagePile.getEndGamePile().contains(card));

		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToStoragePileCommercePileCommerce() {
		StoragePile storagePile = new StoragePile();

		Card card = EasyMock.mock(Card.class);
		ValueEffect effect = EasyMock.mock(ValueEffect.class);

		EasyMock.expect(card.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getValue()).andReturn(Value.COMMERCE);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.LEFT);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		
		EasyMock.replay(card, effect);

		storagePile.addCard(card);

		assertTrue(storagePile.getCommercePile().contains(card));

		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToStoragePileMilitary() {
		StoragePile storagePile = new StoragePile();

		Card card = EasyMock.mock(Card.class);
		ValueEffect effect = EasyMock.mock(ValueEffect.class);

		EasyMock.expect(card.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getValue()).andReturn(Value.MILITARY);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		
		EasyMock.replay(card, effect);

		storagePile.addCard(card);

		assertTrue(storagePile.getImmediateEffectPile().contains(card));

		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToStoragePileVictoryPoints() {
		StoragePile storagePile = new StoragePile();

		Card card = EasyMock.mock(Card.class);
		ValueEffect effect = EasyMock.mock(ValueEffect.class);

		EasyMock.expect(card.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getValue()).andReturn(Value.VICTORYPOINTS);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		
		EasyMock.replay(card, effect);

		storagePile.addCard(card);

		assertTrue(storagePile.getImmediateEffectPile().contains(card));

		EasyMock.verify(card, effect);
	}

	@Test
	public void testAddToStoragePileMultiValue() {
		StoragePile storagePile = new StoragePile();

		Card card = EasyMock.mock(Card.class);
		MultiValueEffect effect = EasyMock.createStrictMock(MultiValueEffect.class);

		EasyMock.expect(card.getEffectType()).andReturn(EffectType.MULTIVALUE);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.replay(card);

		storagePile.addCard(card);

		assertTrue(storagePile.getEndGamePile().contains(card));

		EasyMock.verify(card);
	}
	
	@Test
	public void testContainsEffect() {
		Effect effect = EasyMock.createStrictMock(EntityEffect.class);
		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(effect.getDirection()).andReturn(Direction.SELF);
		EasyMock.expect(effect.getEffectType()).andReturn(EffectType.ENTITY);
		
		EasyMock.replay(effect);
		
		StoragePile storagePile = new StoragePile();
		storagePile.addToWonderPile(effect);
		
		assertTrue(storagePile.containsEffect(effect));
		EasyMock.verify(effect);
		
	}
}
