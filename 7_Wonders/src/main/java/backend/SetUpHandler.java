package backend;

import java.util.ArrayList;

import dataStructures.GameBoard;
import dataStructures.Player;

public class SetUpHandler {
	private int playerNum;

	public GameBoard setUp(int numPlayers) {
		setPlayerNum(numPlayers);
		return createGameBoard();
	}

	public void setPlayerNum(int num) {
		if (num < 3 || num > 7)
			throw new IllegalArgumentException("Cannot play with " + num + " players");
		playerNum = num;

	}

	public GameBoard createGameBoard() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		players.add(new Player());
		
		return new GameBoard(players);
	}

	public int getPlayerNum() {
		return this.playerNum;
	}
}
