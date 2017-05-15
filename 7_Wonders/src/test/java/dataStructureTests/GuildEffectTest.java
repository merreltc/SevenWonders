package dataStructureTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataStructures.Card.CardType;
import dataStructures.Effect;
import dataStructures.Effect.Direction;
import dataStructures.Effect.EffectType;
import dataStructures.GuildEffect;
import junit.framework.Assert;

public class GuildEffectTest {

	@Test
	public void testSelfCivilianStructureGuildEffect() {
		Effect effect = new GuildEffect(EffectType.GUILD, CardType.CIVILIANSTRUCTURE, Direction.SELF);
		Assert.assertEquals(CardType.CIVILIANSTRUCTURE, ((GuildEffect) effect).getObjectToLookFor());
		Assert.assertEquals(Direction.SELF, effect.getDirection());
		assertEquals(EffectType.GUILD, effect.getEffectType());
	}

	@Test
	public void testNeighborsCommercialStructureGuildEffect() {
		Effect effect = new GuildEffect(EffectType.GUILD, CardType.COMMERCIALSTRUCTURE, Direction.NEIGHBORS);
		Assert.assertEquals(CardType.COMMERCIALSTRUCTURE, ((GuildEffect) effect).getObjectToLookFor());
		Assert.assertEquals(Direction.NEIGHBORS, effect.getDirection());
		assertEquals(EffectType.GUILD, effect.getEffectType());
	}

	@Test
	public void testAllManufacturedGoodGuildEffect() {
		Effect effect = new GuildEffect(EffectType.GUILD, CardType.MANUFACTUREDGOOD, Direction.ALL);
		Assert.assertEquals(CardType.MANUFACTUREDGOOD, ((GuildEffect) effect).getObjectToLookFor());
		Assert.assertEquals(Direction.ALL, effect.getDirection());
		assertEquals(EffectType.GUILD, effect.getEffectType());
	}
}
