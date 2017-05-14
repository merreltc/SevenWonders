package dataStructureTests;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
