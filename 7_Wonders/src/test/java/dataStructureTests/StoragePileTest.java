package dataStructureTests;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import dataStructures.Card;
import dataStructures.EntityEffect;
import dataStructures.EntityEffect.EntityType;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.StoragePile;
import dataStructures.ValueEffect;
import dataStructures.ValueEffect.Value;
import dataStructures.ValueEffect.ValueType;

public class StoragePileTest {

	@Test
	public void testDefaultStoragePile() {
		StoragePile storagePile = new StoragePile();
		
		assertTrue(storagePile.getCommercePile().isEmpty());
		assertTrue(storagePile.getSciencePile().isEmpty());
		assertTrue(storagePile.getEndGamePile().isEmpty());
		assertTrue(storagePile.getImmediateEffectPile().isEmpty());
	}
	
	@Test
	public void testAddToCommercePile() {
		StoragePile storagePile = new StoragePile();
		Card card = EasyMock.mock(Card.class);
		
		storagePile.addToCommercePile(card);
		
		assertFalse(storagePile.getCommercePile().isEmpty());
		assertEquals(card, storagePile.getCommercePile().get(0));
	}
	
	@Test
	public void testAddToSciencePile() {
		StoragePile storagePile = new StoragePile();
		Card card = EasyMock.mock(Card.class);
		
		storagePile.addToSciencePile(card);
		
		assertTrue(storagePile.getCommercePile().isEmpty());
		assertEquals(card, storagePile.getSciencePile().get(0));
	}
	
	@Test
	public void testAddToEndGamePile(){
		StoragePile storagePile = new StoragePile();
		Card card = EasyMock.mock(Card.class);
		
		storagePile.addToEndGamePile(card);
		
		assertTrue(storagePile.getSciencePile().isEmpty());
		assertEquals(card, storagePile.getEndGamePile().get(0));
	}
	
	@Test
	public void testAddToImmediateEffectPile(){
		StoragePile storagePile = new StoragePile();
		Card card = EasyMock.mock(Card.class);
		
		storagePile.addToImmediateEffectPile(card);
		
		assertTrue(storagePile.getSciencePile().isEmpty());
		assertEquals(card, storagePile.getImmediateEffectPile().get(0));
	}
	
	@Test
	public void testAddToCommercePileManyCards() {
		StoragePile storagePile = new StoragePile();
		
		for(int i = 0; i < 50; i++){
			Card card = EasyMock.mock(Card.class);
			storagePile.addToCommercePile(card);
		}
		
		assertFalse(storagePile.getCommercePile().isEmpty());
		assertEquals(50, storagePile.getCommercePile().size());
	}
	
	@Test
	public void testAddToSciencePileManyCards() {
		StoragePile storagePile = new StoragePile();
		
		for(int i = 0; i < 50; i++){
			Card card = EasyMock.mock(Card.class);
			storagePile.addToSciencePile(card);
		}
		
		assertFalse(storagePile.getSciencePile().isEmpty());
		assertEquals(50, storagePile.getSciencePile().size());
	}
	
	@Test
	public void testAddToEndGamePileManyCards() {
		StoragePile storagePile = new StoragePile();
		
		for(int i = 0; i < 50; i++){
			Card card = EasyMock.mock(Card.class);
			storagePile.addToEndGamePile(card);
		}
		
		assertFalse(storagePile.getEndGamePile().isEmpty());
		assertEquals(50, storagePile.getEndGamePile().size());
	}
	
	@Test
	public void testAddToImmediateEffectPileManyCards() {
		StoragePile storagePile = new StoragePile();
		
		for(int i = 0; i < 50; i++){
			Card card = EasyMock.mock(Card.class);
			storagePile.addToImmediateEffectPile(card);
		}
		
		assertFalse(storagePile.getImmediateEffectPile().isEmpty());
		assertEquals(50, storagePile.getImmediateEffectPile().size());
	}
	
	@Test
	public void testGetEntireStoragePile(){
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
		assertEquals(4, storagePile.getEntireStoragePile().size());
		assertTrue(storagePile.getEntireStoragePile().contains(card));
		assertTrue(storagePile.getEntireStoragePile().contains(card2));
		assertTrue(storagePile.getEntireStoragePile().contains(card3));
		assertTrue(storagePile.getEntireStoragePile().contains(card4));
	}
	
	@Test
	public void testGetEntireStoragePileMany(){
		StoragePile storagePile = new StoragePile();
		for(int i = 0; i < 50; i++){
			Card card = EasyMock.mock(Card.class);
			storagePile.addToCommercePile(card);
			assertTrue(storagePile.getEntireStoragePile().contains(card));
		}
		for(int i = 0; i < 50; i++){
			Card card = EasyMock.mock(Card.class);
			storagePile.addToSciencePile(card);
			assertTrue(storagePile.getEntireStoragePile().contains(card));
		}
		for(int i = 0; i < 50; i++){
			Card card = EasyMock.mock(Card.class);
			storagePile.addToEndGamePile(card);
			assertTrue(storagePile.getEntireStoragePile().contains(card));
		}
		for(int i = 0; i < 50; i++){
			Card card = EasyMock.mock(Card.class);
			storagePile.addToImmediateEffectPile(card);
			assertTrue(storagePile.getEntireStoragePile().contains(card));
		}
		
		assertEquals(200, storagePile.getEntireStoragePile().size());
	}
	
	@Test
	public void testAddToStoragePileResource() {
		StoragePile storagePile = new StoragePile();
		
		Card card = EasyMock.mock(Card.class);
		EntityEffect effect = EasyMock.mock(EntityEffect.class);
		
		EasyMock.expect(card.getEffectType()).andReturn(EffectType.ENTITY);
		EasyMock.expect(card.getEffect()).andReturn(effect);
		EasyMock.expect(effect.getEntityType()).andReturn(EntityType.RESOURCE);
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
		EasyMock.replay(card, effect);
		
		storagePile.addCard(card);
		
		assertTrue(storagePile.getImmediateEffectPile().contains(card));

		EasyMock.verify(card, effect);
	}
	
	@Test
	public void testAddToStoragePileMultiValue() {
		StoragePile storagePile = new StoragePile();
		
		Card card = EasyMock.mock(Card.class);
		
		EasyMock.expect(card.getEffectType()).andReturn(EffectType.MULTIVALUE);
		EasyMock.replay(card);
		
		storagePile.addCard(card);
		
		assertTrue(storagePile.getEndGamePile().contains(card));

		EasyMock.verify(card);
	}
}
