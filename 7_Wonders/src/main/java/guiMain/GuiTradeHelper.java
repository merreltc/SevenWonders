package guiMain;

import java.util.ArrayList;

import backend.GameManager;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class GuiTradeHelper {
	private GameManager gameManager;
	private Message message;

	public GuiTradeHelper(GameManager gameManager, Message message) {
		this.gameManager = gameManager;
		this.message = message;
	}

	public void trade(String[] splitValue, int currentPlayer) {
		System.out.println("Trying to trade");
		Player tradeTo;
		Player tradeFrom = this.gameManager.getPlayer(currentPlayer);
		if (splitValue[0].equals("Right")) {
			tradeTo = this.gameManager.getNextPlayer();
		} else {
			tradeTo = this.gameManager.getPreviousPlayer();
		}

		try {
			this.gameManager.trade(tradeFrom, tradeTo, 3);
		} catch (InsufficientFundsException e) {
			this.message.setMessage(e.getMessage());
		}
	}

}
