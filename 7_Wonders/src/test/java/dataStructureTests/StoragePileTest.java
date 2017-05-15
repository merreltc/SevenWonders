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

}
