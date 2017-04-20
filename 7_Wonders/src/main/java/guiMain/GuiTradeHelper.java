package guiMain;

import java.util.ArrayList;

import backend.GameManager;
import dataStructures.Player;
import exceptions.InsufficientFundsException;

public class GuiTradeHelper {
	private GameManager gameManager;

	public GuiTradeHelper(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public void trade(String[] splitValue) {
		Player tradeTo;
		Player tradeFrom = this.gameManager.getCurrentPlayer();
		
		if (splitValue[0].equals("Right")) {
			tradeTo = this.gameManager.getNextPlayer();
		} else {
			tradeTo = this.gameManager.getPreviousPlayer();
		}

		try {
			this.gameManager.trade(tradeFrom, tradeTo, 3);
		} catch (InsufficientFundsException e) {
			Message.showMessage(e.getMessage());
		}
	}

}
