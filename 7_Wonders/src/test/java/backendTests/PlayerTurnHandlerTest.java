package backendTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import backend.PlayerTurnHandler;
import backend.SetUpDeckHandler;
import backend.TurnHandler;
import dataStructures.Card;
import dataStructures.Deck;
import dataStructures.GameBoard;
import dataStructures.Player;
import dataStructures.Deck.Age;
import dataStructures.Wonder.WonderType;

public class PlayerTurnHandlerTest {

	@Test
	public void testBuildStructureNoCost(){
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
		playerTurnHandler.buildStructure(current, current.getCurrentHand().get(0));
		
		assertEquals(1, current.getStoragePile().size());
		assertEquals(0, current.getCurrentHand().size());
		assertFalse(current.getCurrentHand().contains(deck.getCard(0)));
		assertTrue(current.getStoragePile().contains(deck.getCard(0)));
	}

}
