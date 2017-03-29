package guiMain;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Game {
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	// TODO: private ArrayList<Player> players = new ArrayList<Player>();
	private String message = "Error";
	private int numOfPlayers;

	public Game(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}

	public void draw(Graphics graphics) {
		for (Button button : buttons) {
			button.draw(graphics);
		}
		for (int i = 0; i < boards.size(); i++) {

			boards.get(i).draw(graphics);
		}
		if (!this.message.equals("")) {
			this.drawMessageOnScreen(graphics);
		}
	}

	public void initializeGame() {
		this.buttons.clear();
		for (int i = -1; i < this.numOfPlayers - 1; i++) {
			PlayerBoard board = new PlayerBoard(i, numOfPlayers);
			boards.add(board);
		}
		Button exitMessage = new Button(Constants.CloseMessageButtonPosition, Constants.CloseMessageButtonBounds,
				"Close");
		buttons.add(exitMessage);
		String[] values = new String[] { "Stone", "Wood", "Ore", "Clay" };
		for (int i = 0; i < 4; i++) {
			Point buttonPosition = new Point(Constants.TradeLeftBaseButtonPoint.x,
					Constants.TradeLeftBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
			Button LeftTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds, "Left-" + values[i],
					false);
			buttons.add(LeftTradeButton);
		}
		for (int i = 0; i < 4; i++) {
			Point buttonPosition = new Point(Constants.TradeRightBaseButtonPoint.x,
					Constants.TradeRightBaseButtonPoint.y + Constants.TradeButtonYOffet * i);
			Button RightTradeButton = new Button(buttonPosition, Constants.TradeButtonBounds, "Right-" + values[i],
					false);
			buttons.add(RightTradeButton);
		}
	}

	public void onButtonClickInGame(Button clicked) {
		if (clicked.getValue().equals("Close")) {
			this.message = "";
		} else {
			String[] splitValue = clicked.getValue().split("-");
			if (splitValue[0].equals("Right")) {
				// TODO: Trade splitValue[1] with right player
			} else {
				// TODO: Trade splitValue[1] with left player
			}
		}
	}

	public void drawMessageOnScreen(Graphics graphics) {
		graphics.setFont(Constants.ErrorFont);
		graphics.setColor(Constants.TitleColor);
		graphics.drawString(this.message, Constants.ErrorMessagePosition.x, Constants.ErrorMessagePosition.y);
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<Button> getButtons() {
		return this.buttons;
	}
}
