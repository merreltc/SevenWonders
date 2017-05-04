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
		int currentIndex = this.gameManager.getPlayers().indexOf(tradeFrom);
		
		if (splitValue[0].equals("Right")) {
			int offsetRight = (currentIndex - 1 + this.gameManager.getNumPlayers());
			tradeTo = this.gameManager.getPlayer(offsetRight%this.gameManager.getNumPlayers());
		} else {
			int offsetLeft = (currentIndex + 1 + this.gameManager.getNumPlayers());
			tradeTo = this.gameManager.getPlayer(offsetLeft%this.gameManager.getNumPlayers());
		}

		try {
			this.gameManager.trade(tradeFrom, tradeTo, 3);
		} catch (InsufficientFundsException e) {
			Message.showMessage(e.getMessage());
		}
	}

}
