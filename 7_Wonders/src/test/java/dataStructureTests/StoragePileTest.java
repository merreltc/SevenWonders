package dataStructureTests;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import dataStructures.Card;
import dataStructures.StoragePile;

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
}
