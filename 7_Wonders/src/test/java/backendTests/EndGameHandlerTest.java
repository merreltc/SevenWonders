package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import backend.handlers.EndGameHandler;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.Good;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Science;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Cost;
import dataStructures.gameMaterials.Effect;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.Card.CardType;
import dataStructures.gameMaterials.Effect.Direction;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect.EntityType;
import dataStructures.gameMaterials.ValueEffect.AffectingEntity;
import dataStructures.gameMaterials.ValueEffect.Value;
import dataStructures.playerData.Player;
import junit.framework.Assert;

public class EndGameHandlerTest {
	
	public void initializePlayers(){
		Player player1 = EasyMock.mock(Player.class);
	    EasyMock.expect(player1.getConflictTotal()).andReturn(3);
	    EasyMock.expect(player1.getCoinTotal()).andReturn(9);
	    EasyMock.expect(player1.getNumVictoryPoints()).andReturn(4);
	    EasyMock.expect(player1.getCardFromEndGame(0)).andReturn(this.createCraftsmenGuild());
	    EasyMock.expect(player1.getNumberOfEachScience()).andReturn(new int[]{1,1,1});
	    
	    Player player2 = EasyMock.mock(Player.class);
	    EasyMock.expect(player2.getConflictTotal()).andReturn(4);
	    EasyMock.expect(player2.getCoinTotal()).andReturn(6);
	    EasyMock.expect(player2.getNumVictoryPoints()).andReturn(2);
	    EasyMock.expect(player2.getCardFromEndGame(0)).andReturn(this.createCraftsmenGuild());
	    EasyMock.expect(player2.getNumberOfEachScience()).andReturn(new int[]{3,1,0});
	    
	    Player player3 = EasyMock.mock(Player.class);
	    EasyMock.expect(player3.getConflictTotal()).andReturn(7);
	    EasyMock.expect(player3.getCoinTotal()).andReturn(2);
	    EasyMock.expect(player3.getNumVictoryPoints()).andReturn(2);
	    EasyMock.expect(player3.getCardFromEndGame(0)).andReturn(this.createWorkersGuild());
	    EasyMock.expect(player3.getNumberOfEachScience()).andReturn(new int[]{1,2,2});
	    
	    Player player4 = EasyMock.mock(Player.class);
	    EasyMock.expect(player4.getConflictTotal()).andReturn(-3);
	    EasyMock.expect(player4.getCoinTotal()).andReturn(1);
	    EasyMock.expect(player4.getNumVictoryPoints()).andReturn(5);
	    EasyMock.expect(player4.getCardFromEndGame(0)).andReturn(this.createCraftsmenGuild());
	    EasyMock.expect(player4.getNumberOfEachScience()).andReturn(new int[]{5,1,2});
	    
	    Player player5 = EasyMock.mock(Player.class);
	    EasyMock.expect(player5.getConflictTotal()).andReturn(5);
	    EasyMock.expect(player5.getCoinTotal()).andReturn(6);
	    EasyMock.expect(player5.getNumVictoryPoints()).andReturn(1);
	    EasyMock.expect(player5.getCardFromEndGame(0)).andReturn(this.createCraftsmenGuild());
	    EasyMock.expect(player5.getNumberOfEachScience()).andReturn(new int[]{1,0,1});
	    
	    Player player6 = EasyMock.mock(Player.class);
	    EasyMock.expect(player6.getConflictTotal()).andReturn(6);
	    EasyMock.expect(player6.getCoinTotal()).andReturn(9);
	    EasyMock.expect(player6.getNumVictoryPoints()).andReturn(0);
	    EasyMock.expect(player6.getCardFromEndGame(0)).andReturn(this.createWorkersGuild());
	    EasyMock.expect(player6.getNumberOfEachScience()).andReturn(new int[]{1,5,2});
	    
	    Player player7 = EasyMock.mock(Player.class);
	    EasyMock.expect(player7.getConflictTotal()).andReturn(4);
	    EasyMock.expect(player7.getCoinTotal()).andReturn(1);
	    EasyMock.expect(player7.getNumVictoryPoints()).andReturn(0);
	    EasyMock.expect(player7.getCardFromEndGame(0)).andReturn(this.createCraftsmenGuild());
	    EasyMock.expect(player7.getNumberOfEachScience()).andReturn(new int[]{1,1,1});
	}

	@Test
	public void testWorkersGuildEffect(){
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(createRawMaterialCard());
		cards.add(createRawMaterialCard());
		
		Player player1 = EasyMock.mock(Player.class);
		Player player2 = EasyMock.mock(Player.class);
		Player player3 = EasyMock.mock(Player.class);
		
		EasyMock.expect(player2.getCardFromEndGame(0)).andReturn(this.createWorkersGuild());
		EasyMock.expect(player2.getCardFromEndGame(1)).andThrow(new IllegalArgumentException(""));
		
		EndGameHandler end = new EndGameHandler();
		EasyMock.expect(player1.getStoragePile()).andReturn(cards);
		EasyMock.expect(player3.getStoragePile()).andReturn(cards);
		
		EasyMock.replay(player1,player2,player3);
		
		Assert.assertEquals(4, end.getPointsFromGuildCards(player2, player1, player3));
	}
	
	@Test
	public void testCraftsmenGuildEffect(){
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(createManufacturedCard());
		cards.add(createManufacturedCard());
		
		Player player1 = EasyMock.mock(Player.class);
		Player player2 = EasyMock.mock(Player.class);
		Player player3 = EasyMock.mock(Player.class);
		
		EasyMock.expect(player2.getCardFromEndGame(0)).andReturn(this.createCraftsmenGuild());
		EasyMock.expect(player2.getCardFromEndGame(1)).andThrow(new IllegalArgumentException(""));
		
		EndGameHandler end = new EndGameHandler();
		EasyMock.expect(player1.getStoragePile()).andReturn(cards);
		EasyMock.expect(player3.getStoragePile()).andReturn(cards);
		
		EasyMock.replay(player1,player2,player3);
		
		Assert.assertEquals(8, end.getPointsFromGuildCards(player2, player1, player3));
	}
	
	@Test
	public void testTradersGuildEffect(){
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(createCommercialCard());
		cards.add(createCommercialCard());
		
		Player player1 = EasyMock.mock(Player.class);
		Player player2 = EasyMock.mock(Player.class);
		Player player3 = EasyMock.mock(Player.class);
		
		EasyMock.expect(player2.getCardFromEndGame(0)).andReturn(this.createTradersGuild());
		EasyMock.expect(player2.getCardFromEndGame(1)).andThrow(new IllegalArgumentException(""));
		
		EndGameHandler end = new EndGameHandler();
		EasyMock.expect(player1.getStoragePile()).andReturn(cards);
		EasyMock.expect(player3.getStoragePile()).andReturn(cards);
		
		EasyMock.replay(player1,player2,player3);
		
		Assert.assertEquals(4, end.getPointsFromGuildCards(player2, player1, player3));
	}
	
	@Test
	public void testPhilosophersGuildEffect(){
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(this.createWorkshop());
		cards.add(this.createWorkshop());
		
		Player player1 = EasyMock.mock(Player.class);
		Player player2 = EasyMock.mock(Player.class);
		Player player3 = EasyMock.mock(Player.class);
		
		EasyMock.expect(player2.getCardFromEndGame(0)).andReturn(this.createPhilosophersGuild());
		EasyMock.expect(player2.getCardFromEndGame(1)).andThrow(new IllegalArgumentException(""));
		
		EndGameHandler end = new EndGameHandler();
		EasyMock.expect(player1.getStoragePile()).andReturn(cards);
		EasyMock.expect(player3.getStoragePile()).andReturn(cards);
		
		EasyMock.replay(player1,player2,player3);
		
		Assert.assertEquals(4, end.getPointsFromGuildCards(player2, player1, player3));
	}
	
	@Test
	public void testSpiesGuildEffect(){
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(createMilitaryCard());
		cards.add(createMilitaryCard());
		
		Player player1 = EasyMock.mock(Player.class);
		Player player2 = EasyMock.mock(Player.class);
		Player player3 = EasyMock.mock(Player.class);
		
		EasyMock.expect(player2.getCardFromEndGame(0)).andReturn(this.createSpiesGuild());
		EasyMock.expect(player2.getCardFromEndGame(1)).andThrow(new IllegalArgumentException(""));
		
		EndGameHandler end = new EndGameHandler();
		EasyMock.expect(player1.getStoragePile()).andReturn(cards);
		EasyMock.expect(player3.getStoragePile()).andReturn(cards);
		
		EasyMock.replay(player1,player2,player3);
		
		Assert.assertEquals(4, end.getPointsFromGuildCards(player2, player1, player3));
	}
	
	private Card createWorkersGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.CLAY, 1);
		costs.put(RawResource.ORE, 2);
		costs.put(RawResource.STONE, 1);
		Cost cost = new Cost(CostType.RESOURCE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.RAWRESOURCES, Direction.NEIGHBORS, 1);
		Card card = new Card("Workers Guild", CardType.GUILD, cost, effect);
		return card;
	}
	
	private Card createCraftsmenGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.ORE, 2);
		costs.put(RawResource.STONE, 2);
		Cost cost = new Cost(CostType.RESOURCE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.MANUFACTUREDGOODS, Direction.NEIGHBORS,
				2);
		Card card = new Card("Craftsmens Guild", CardType.GUILD, cost, effect);
		return card;
	}
	
	private Card createTradersGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(Good.PRESS, 1);
		costs.put(Good.GLASS, 1);
		Cost cost = new Cost(CostType.GOOD, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.COMMERCIALSTRUCTURES,
				Direction.NEIGHBORS, 1);
		Card card = new Card("Traders Guild", CardType.GUILD, cost, effect);
		return card;
	}
	
	public Card createPhilosophersGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(Good.PRESS, 1);
		costs.put(RawResource.CLAY, 3);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.SCIENTIFICSTRUCTURES,
				Direction.NEIGHBORS, 1);
		Card card = new Card("Philosophers Guild", CardType.GUILD, cost, effect);
		return card;
	}
	
	public Card createSpiesGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		costs.put(RawResource.LUMBER, 3);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.MILITARYSTRUCTURES, Direction.NEIGHBORS,
				1);
		Card card = new Card("Spies Guild", CardType.GUILD, cost, effect);
		return card;
	}
	
	public Card createStrategistsGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.ORE, 2);
		costs.put(RawResource.STONE, 1);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.MILITARYSTRUCTURES, Direction.NEIGHBORS,
				1);
		Card card = new Card("Strategists Guild", CardType.GUILD, cost, effect);
		return card;
	}
	
	public Card createShipOwnersGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		costs.put(Good.PRESS, 1);
		costs.put(RawResource.LUMBER, 3);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(AffectingEntity.RAWRESOURCES, 1);
		entitiesAndAmounts.put(AffectingEntity.MANUFACTUREDGOODS, 1);
		entitiesAndAmounts.put(AffectingEntity.GUILD, 1);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, entitiesAndAmounts);
		Card card = new Card("Shipowners Guild",CardType.GUILD, cost, effect);
		return card;
	}
	
	public Card createScientistsGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.PRESS, 1);
		costs.put(RawResource.LUMBER, 2);
		costs.put(RawResource.ORE, 2);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.PROTRACTOR, 1);
		entitiesAndAmounts.put(Science.WHEEL, 1);
		entitiesAndAmounts.put(Science.TABLET, 1);
		Effect effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		Card card = new Card("Scientists Guild", CardType.GUILD, cost, effect);
		return card;
	}
	
	public Card createMagistratesGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.LUMBER, 3);
		costs.put(RawResource.STONE, 1);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.CIVILIANSTRUCTURES, Direction.NEIGHBORS,
				1);
		Card card = new Card("Magistrates Guild", CardType.GUILD, cost, effect);
		return card;
	}
	
	public Card createBuildersGuild(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.CLAY, 2);
		costs.put(RawResource.STONE, 2);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.GUILD, AffectingEntity.WONDERLEVEL, Direction.ALL, 1);
		Card card = new Card("Builders Guild", CardType.GUILD, cost, effect);
		return card;
	}
	
	private Card createCivilianCard(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(Good.LOOM, 1);
		costs.put(RawResource.CLAY, 2);
		Cost cost = new Cost(CostType.MULTITYPE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.VICTORYPOINTS, AffectingEntity.NONE, 4);
		Card card = new Card("Courthouse", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}
	
	private Card createRawMaterialCard(){
		Cost cost = new Cost(CostType.NONE, 0);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(RawResource.LUMBER, 1);
		Effect effect = new EntityEffect(EffectType.ENTITY, EntityType.RESOURCE, entitiesAndAmounts);

		Card card = new Card("Lumber Yard", CardType.RAWMATERIAL, cost, effect);
		return card;
	}
	
	private Card createManufacturedCard(){
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		Cost cost = new Cost(CostType.NONE, 0);
		entitiesAndAmounts.put(Good.LOOM, 1);
		Effect effect = new EntityEffect(EffectType.ENTITY, EntityType.MANUFACTUREDGOOD, entitiesAndAmounts);

		Card card = new Card("Loom", CardType.MANUFACTUREDGOOD, cost, effect);
		return card;
	}
	
	private Card createCommercialCard(){
		Cost cost = new Cost(CostType.NONE, 0);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.COMMERCE, AffectingEntity.NONE, 5);
		Card card = new Card("Tavern", CardType.COMMERCIALSTRUCTURE, cost, effect);
		return card;
	}
	
	private Card createMilitaryCard(){
		HashMap<Enum, Integer> costs = new HashMap<Enum, Integer>();
		costs.put(RawResource.LUMBER, 1);
		costs.put(RawResource.ORE, 1);
		costs.put(RawResource.CLAY, 1);
		Cost cost = new Cost(CostType.RESOURCE, costs);
		Effect effect = new ValueEffect(EffectType.VALUE, Value.MILITARY, AffectingEntity.NONE, 2);
		Card card = new Card("Stables", CardType.MILITARYSTRUCTURE, cost, effect);
		return card;
	}
	
	private Card createWorkshop(){
		HashMap<Enum, Integer>costs = new HashMap<Enum, Integer>();
		costs.put(Good.GLASS, 1);
		Cost cost = new Cost(CostType.GOOD, costs);
		HashMap<Enum, Integer> entitiesAndAmounts = new HashMap<Enum, Integer>();
		entitiesAndAmounts.put(Science.WHEEL, 1);
		Effect effect = new EntityEffect(EffectType.ENTITY, EntityType.SCIENCE, entitiesAndAmounts);
		Card card = new Card("Workshop", CardType.SCIENTIFICSTRUCTURE, cost, effect);
		return card;
	}
	

}
