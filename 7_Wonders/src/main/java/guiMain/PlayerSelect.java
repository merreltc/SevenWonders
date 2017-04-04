package guiMain;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class PlayerSelect extends Menu{
	//private ArrayList<Button> buttons = new ArrayList<Button>();

	@Override
	public void draw(Graphics graphics) {
		for (Button button : this.getButtons()) {
			button.draw(graphics);
		}
		graphics.setFont(Constants.TitleFont);
		graphics.setColor(Constants.TitleColor);
		graphics.drawString("Choose number of players", Constants.PlayerSelectTitlePosition.x,
				Constants.PlayerSelectButtonBounds.y);
	}

	public void initializePlayerSelect() {
		
	}

	@Override
	public void initialize() {
		
		this.clearButtons();
		for (int i = 3; i <= 7; i++) {
			Button startGame = new Button(new Point(400 + 250 * (i - 3), 400), Constants.PlayerSelectButtonBounds,
					i + "");
			this.addButton(startGame);
		}

	}
}
