package backendTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

import backend.handlers.PlayerTurnHandler;
import backend.handlers.SetUpDeckHandler;
import constants.GeneralEnums.CostType;
import constants.GeneralEnums.RawResource;
import constants.GeneralEnums.Resource;
import dataStructures.GameBoard;
import dataStructures.gameMaterials.Card;
import dataStructures.gameMaterials.Deck;
import dataStructures.gameMaterials.Deck.Age;
import dataStructures.gameMaterials.Effect.EffectType;
import dataStructures.gameMaterials.EntityEffect;
import dataStructures.gameMaterials.ValueEffect;
import dataStructures.gameMaterials.ValueEffect.ValueType;
import dataStructures.gameMaterials.Wonder.WonderType;
import dataStructures.playerData.Player;
import dataStructures.playerData.Chip.ChipValue;
import exceptions.InsufficientFundsException;

public class PlayerTurnHandlerTest {

	@Test
	public void testBuildStructureNoCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(0));
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);

		assertEquals(1, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(0)));
		assertTrue(current.getStoragePile().contains(deck.getCard(0)));
	}

	@Test
	public void testBuildStructureCoinCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(4));
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);

		assertEquals(1, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(4)));
		assertTrue(current.getStoragePile().contains(deck.getCard(4)));
		assertEquals(0, (int) current.getCoins().get(ChipValue.THREE));
		assertEquals(2, current.getCoinTotal());
	}

	@Test
	public void testValidBuildStructureResourceCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(1)); // Stone pit
		currentHand.add(deck.getCard(9)); // baths
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);

		assertEquals(2, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(9)));
		assertTrue(current.getStoragePile().contains(deck.getCard(9)));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureResourceCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(0)); // lumber yard
		currentHand.add(deck.getCard(9)); // baths
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		fail();
	}

	@Test
	public void testValidBuildStructureEntityCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(6)); // loom
		currentHand.add(deck.getCard(18)); // apothecary
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);

		assertEquals(2, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(6)));
		assertTrue(current.getStoragePile().contains(deck.getCard(6)));
		assertFalse(current.getCurrentHand().contains(deck.getCard(18)));
		assertTrue(current.getStoragePile().contains(deck.getCard(18)));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureGoodCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(0)); // lumber yard
		currentHand.add(deck.getCard(18)); // baths
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		fail();
	}

	@Test
	public void testValidBuildStructure2ResourceCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck = new Deck(Age.AGE2, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(0)); // sawmill
		currentHand.add(deck.getCard(3)); // quarry
		currentHand.add(deck.getCard(9)); // statue
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);

		assertEquals(3, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(0)));
		assertTrue(current.getStoragePile().contains(deck.getCard(0)));
		assertFalse(current.getCurrentHand().contains(deck.getCard(3)));
		assertTrue(current.getStoragePile().contains(deck.getCard(3)));
		assertFalse(current.getCurrentHand().contains(deck.getCard(9)));
		assertTrue(current.getStoragePile().contains(deck.getCard(9)));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInalidBuildStructure2ResourceCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck = new Deck(Age.AGE2, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(1)); // quarry
		currentHand.add(deck.getCard(7)); // aqueduct
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		fail();
	}

	@Test
	public void testValidBuildStructureMultiEntityCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck = new Deck(Age.AGE2, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(3)); // foundry
		currentHand.add(deck.getCard(5)); // glassworks
		currentHand.add(deck.getCard(16)); // Dispensary
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);

		assertEquals(3, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(3)));
		assertTrue(current.getStoragePile().contains(deck.getCard(3)));
		assertFalse(current.getCurrentHand().contains(deck.getCard(5)));
		assertTrue(current.getStoragePile().contains(deck.getCard(5)));
		assertFalse(current.getCurrentHand().contains(deck.getCard(16)));
		assertTrue(current.getStoragePile().contains(deck.getCard(16)));
	}

	@Test(expected = InsufficientFundsException.class)
	public void testInvalidBuildStructureMultiEntityCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck = new Deck(Age.AGE2, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		currentHand.add(deck.getCard(3)); // foundry
		currentHand.add(deck.getCard(6)); // press
		currentHand.add(deck.getCard(18)); // Dispensary
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);
		fail();
	}

	@Test
	public void testDiscardSelectedCard() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck = new Deck(Age.AGE2, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card discarded = deck.getCard(3);
		currentHand.add(discarded); // foundry
		currentHand.add(deck.getCard(6)); // press
		currentHand.add(deck.getCard(18)); // Dispensary
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.discardSelectedCard(current, discarded, board);

		assertEquals(1, board.getDiscardPile().size());
		assertEquals(6, current.getCoinTotal());
		assertTrue(board.getDiscardPile().contains(discarded));
		assertEquals(2, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(discarded));
	}

	@Test
	public void testBuildStructureAddOneShield() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card lumber = deck.getCard(0);
		Card stockade = deck.getCard(15);
		currentHand.add(lumber); // lumber yard
		currentHand.add(stockade); // stockade

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, lumber, board);
		playerTurnHandler.buildStructure(current, stockade, board);

		assertEquals(1, current.getNumShields());
	}

	@Test
	public void testBuildStructureAddTwoShields() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards1 = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck1 = new Deck(Age.AGE1, cards1);

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck = new Deck(Age.AGE2, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card stone = deck1.getCard(1);
		Card quarry = deck.getCard(1);
		Card walls = deck.getCard(13);

		currentHand.add(quarry);
		currentHand.add(stone);
		currentHand.add(walls);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, quarry, board);
		playerTurnHandler.buildStructure(current, stone, board);
		playerTurnHandler.buildStructure(current, walls, board);

		assertEquals(2, current.getNumShields());
	}
	
	@Test
	public void testBuildStructureAddTwoShieldsSomethingNotEntity() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards1 = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck1 = new Deck(Age.AGE1, cards1);

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE2, 3);
		Deck deck = new Deck(Age.AGE2, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card stone = deck1.getCard(1);
		Card altar = deck1.getCard(10); //alter
		Card quarry = deck.getCard(1);
		Card walls = deck.getCard(13);

		currentHand.add(quarry);
		currentHand.add(stone);
		currentHand.add(walls);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, quarry, board);
		playerTurnHandler.buildStructure(current, altar, board);
		playerTurnHandler.buildStructure(current, stone, board);
		playerTurnHandler.buildStructure(current, walls, board);

		assertEquals(2, current.getNumShields());
	}

	@Test(expected = InsufficientFundsException.class)
	public void testBuildStructureNotEnoughResourcesCanOnlyUseOneOrOther() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));
		players.add(new Player("Hulk", WonderType.TEMPLE));
		players.add(new Player("Iron Man", WonderType.MAUSOLEUM));

		ArrayList<Card> cards1 = new SetUpDeckHandler().createCards(Age.AGE1, 5);
		Deck deck1 = new Deck(Age.AGE1, cards1);

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE2, 5);
		Deck deck = new Deck(Age.AGE2, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card ore = deck1.getCard(7);
		Card forest = deck1.getCard(11);
		Card statue = deck.getCard(16);

		currentHand.add(forest);
		currentHand.add(ore);
		currentHand.add(statue);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, ore, board);
		playerTurnHandler.buildStructure(current, forest, board);
		playerTurnHandler.buildStructure(current, statue, board);
		fail();
	}

	@Test
	public void testBuildStructureAddTwoVictoryPoints() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card stone = deck.getCard(1);
		Card baths = deck.getCard(9);

		currentHand.add(stone);
		currentHand.add(baths);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, stone, board);
		playerTurnHandler.buildStructure(current, baths, board);

		assertEquals(3, current.getNumVictoryPoints());
	}
	
	@Test
	public void testBuildStructureAddCommerce() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card east = deck.getCard(12); //east trading post
		
		currentHand.add(east);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, east, board);

		assertEquals(0, current.getNumVictoryPoints());
		assertEquals(0, current.getNumShields());
		assertTrue(current.storagePileContainsCardByName("East Trading Post"));
	}
	
	@Test
	public void testBuildStructureAddCommerceCoinAdd() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 4);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		ArrayList<Card> currentHand = new ArrayList<Card>();

		Card tavern = deck.getCard(16); //tavern
		
		currentHand.add(tavern);

		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, tavern, board);

		assertEquals(0, current.getNumVictoryPoints());
		assertEquals(0, current.getNumShields());
		assertEquals(8, current.getCoinTotal());
		assertEquals(1, (int) current.getCoins().get(ChipValue.THREE));
		assertTrue(current.storagePileContainsCardByName("Tavern"));
	}

	@Test
	public void testBuildStructureAddTwoVictoryPointsMockedVersion() {
		Player player = EasyMock.mock(Player.class);
		Card cardToBuild = EasyMock.mock(Card.class);
		GameBoard board = EasyMock.mock(GameBoard.class);
		
		HashMap<Enum, Integer> cost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> entities = new HashMap<Enum, Integer>();

		cost.put(Resource.STONE, 1);
		entities.put(Resource.STONE, 1);

		ArrayList<Card> storage = new ArrayList<Card>();
		Card cardInStorage = EasyMock.mock(Card.class);
		storage.add(cardInStorage);
		String previousStructureName = "None";
		EasyMock.expect(cardToBuild.getName()).andReturn("Baths");
		EasyMock.expect(player.storagePileContainsCardByName("Baths")).andReturn(false);
		EasyMock.expect(cardToBuild.getPreviousStructureName()).andReturn(previousStructureName);
		EasyMock.expect(cardToBuild.getCostType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cardToBuild.getCostType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cardToBuild.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(cardToBuild.getCost()).andReturn(cost);
		EasyMock.expect(player.getStoragePile()).andReturn(storage);
		EasyMock.expect(player.getStoragePile()).andReturn(storage);
		EntityEffect entityEffect = EasyMock.mock(EntityEffect.class);

		for (Card sCards : storage) {
			EasyMock.expect(sCards.getName()).andReturn("Brick Yard");
			EasyMock.expect(sCards.getEffectType()).andReturn(EffectType.ENTITY);

			EasyMock.expect(sCards.getEffect()).andReturn(entityEffect);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
		}

		ValueEffect effect = EasyMock.mock(ValueEffect.class);
		EasyMock.expect(cardToBuild.getEffect()).andReturn(effect);

		EasyMock.expect(effect.getValueType()).andReturn(ValueType.VICTORYPOINT);
		EasyMock.expect(effect.getValueAmount()).andReturn(Integer.valueOf(3));
		player.addNumVictoryPoints(3);
		player.addToStoragePile(cardToBuild);
		player.removeFromCurrentHand(cardToBuild);

		EasyMock.replay(player, cardToBuild, cardInStorage, entityEffect, effect);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(player, cardToBuild, board);

		EasyMock.verify(player, cardToBuild, cardInStorage, entityEffect, effect);
	}

	@Test
	public void testInvalidBuildStructureAlreadyHaveStructure() {
		Player player = EasyMock.mock(Player.class);
		Card cardToBuild = EasyMock.mock(Card.class);
		GameBoard board = EasyMock.mock(GameBoard.class);
		HashMap<Enum, Integer> cost = new HashMap<Enum, Integer>();
		HashMap<Enum, Integer> entities = new HashMap<Enum, Integer>();

		cost.put(Resource.STONE, 1);
		entities.put(Resource.STONE, 1);

		ArrayList<Card> storage = new ArrayList<Card>();
		Card cardInStorage = EasyMock.mock(Card.class);
		storage.add(cardInStorage);
		String previousStructureName = "None";
		EasyMock.expect(cardToBuild.getName()).andReturn("Baths");
		EasyMock.expect(cardToBuild.getPreviousStructureName()).andReturn(previousStructureName);
		EasyMock.expect(cardToBuild.getCostType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cardToBuild.getCostType()).andReturn(CostType.RESOURCE);
		EasyMock.expect(cardToBuild.getEffectType()).andReturn(EffectType.VALUE);
		EasyMock.expect(cardToBuild.getCost()).andReturn(cost);
		EasyMock.expect(player.getStoragePile()).andReturn(storage);
		EasyMock.expect(player.getStoragePile()).andReturn(storage);
		EntityEffect entityEffect = EasyMock.mock(EntityEffect.class);

		for (Card sCards : storage) {
			EasyMock.expect(sCards.getName()).andReturn("Brick Yard");
			EasyMock.expect(sCards.getEffectType()).andReturn(EffectType.ENTITY);

			EasyMock.expect(sCards.getEffect()).andReturn(entityEffect);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
			EasyMock.expect(entityEffect.getEntities()).andReturn(entities);
		}

		ValueEffect effect = EasyMock.mock(ValueEffect.class);
		EasyMock.expect(cardToBuild.getEffect()).andReturn(effect);

		EasyMock.expect(effect.getValueType()).andReturn(ValueType.VICTORYPOINT);
		EasyMock.expect(effect.getValueAmount()).andReturn(Integer.valueOf(3));
		EasyMock.expect(player.storagePileContainsCardByName("Baths")).andReturn(false);
		player.addNumVictoryPoints(3);
		player.addToStoragePile(cardToBuild);
		player.removeFromCurrentHand(cardToBuild);
		EasyMock.expect(player.storagePileContainsCardByName("Baths")).andReturn(true);
		EasyMock.expect(cardToBuild.getName()).andReturn("Baths");

		EasyMock.replay(player, cardToBuild, cardInStorage, entityEffect, effect);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(player, cardToBuild, board);

		try {
			playerTurnHandler.buildStructure(player, cardToBuild, board);
			fail();
		} catch (IllegalArgumentException error) {
			assertEquals("Cannot build Structure: Player already has the same Structure", error.getMessage());
		}

		EasyMock.verify(player, cardToBuild, cardInStorage, entityEffect, effect);
	}

	@Test
	public void testBuildPreviousStructureFree() {
		Player player = EasyMock.mock(Player.class);
		GameBoard board = EasyMock.mock(GameBoard.class);
		ArrayList<Card> storage = new ArrayList<Card>();
		Card storaged = EasyMock.mock(Card.class);
		storage.add(storaged);

		Card cardToBuild = EasyMock.mock(Card.class);
		String previousStructureName = "Trading Post";
		EasyMock.expect(cardToBuild.getPreviousStructureName()).andReturn(previousStructureName);
		EasyMock.expect(player.getStoragePile()).andReturn(storage);

		EasyMock.expect(storaged.getName()).andReturn("East Trading Post");
		player.addToStoragePile(cardToBuild);
		player.removeFromCurrentHand(cardToBuild);
		EasyMock.expect(player.storagePileContainsCardByName("Trading Post")).andReturn(false);
		EasyMock.expect(cardToBuild.getName()).andReturn("Trading Post");
		EasyMock.replay(player, storaged, cardToBuild);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(player, cardToBuild, board);

		EasyMock.verify(player, storaged, cardToBuild);
	}
	
	@Test
	public void testValidBuildStructureResourceInTradesCost() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player("Wolverine", WonderType.COLOSSUS));
		players.add(new Player("Captain America", WonderType.LIGHTHOUSE));
		players.add(new Player("Black Widow", WonderType.PYRAMIDS));

		ArrayList<Card> cards = new SetUpDeckHandler().createCards(Age.AGE1, 3);
		Deck deck = new Deck(Age.AGE1, cards);

		GameBoard board = new GameBoard(players, deck);

		Player current = board.getCurrentPlayer();
		current.addTradedValue(RawResource.STONE);
		ArrayList<Card> currentHand = new ArrayList<Card>();
		currentHand.add(deck.getCard(9)); // baths
		current.setCurrentHand(currentHand);

		PlayerTurnHandler playerTurnHandler = new PlayerTurnHandler();
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0), board);

		assertEquals(1, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(9)));
		assertTrue(current.getStoragePile().contains(deck.getCard(9)));
	}
}