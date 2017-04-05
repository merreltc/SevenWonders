package guiMain;

import java.util.ArrayList;

import backend.GameManager;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class TradeHandler {
	GameManager gameManager;
	Message message;

	public TradeHandler(GameManager gameManager, Message message) {
		this.gameManager = gameManager;
		this.message = message;
	}

	public void trade(String[] splitValue, int currentPlayer) {
		int numOfPlayers = gameManager.getNumPlayers();
		Player tradeTo;
		Player tradeFrom = this.gameManager.getPlayer(currentPlayer);
		if (splitValue[0].equals("Left")) {
			tradeTo = this.gameManager.getPlayer((currentPlayer + 1) % numOfPlayers);

		} else {
			tradeTo = this.gameManager.getPlayer((currentPlayer + (numOfPlayers - 1)) % numOfPlayers);
		}

		try {
			this.gameManager.trade(tradeFrom, tradeTo, 3);
		} catch (InsufficientFundsException e) {
			this.message.setMessage(e.getMessage());
		}
	}

}
