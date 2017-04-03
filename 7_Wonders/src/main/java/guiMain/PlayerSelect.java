package guiMain;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class PlayerSelect {
	private ArrayList<Button> buttons = new ArrayList<Button>();

	public void draw(Graphics graphics) {
		for (Button button : buttons) {
			button.draw(graphics);
		}
		graphics.setFont(Constants.TitleFont);
		graphics.setColor(Constants.TitleColor);
		graphics.drawString("Choose number of players", Constants.PlayerSelectTitlePosition.x,
				Constants.PlayerSelectButtonBounds.y);
	}

	public void initializePlayerSelect() {
		this.buttons.clear();
		for (int i = 3; i <= 7; i++) {
			Button startGame = new Button(new Point(400 + 250 * (i - 3), 400), Constants.PlayerSelectButtonBounds,
					i + "");
			buttons.add(startGame);
		}

	}

	public ArrayList<Button> getButtons() {
		return this.buttons;
	}
}
