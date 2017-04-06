package guiMain;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import GuiDataStructures.Constants;

public class PlayerSelect extends Menu{

	@Override
	public void draw(Graphics graphics) {
		for (Interactable button : this.getInteractables()) {
			button.draw(graphics);
		}
		graphics.setFont(Constants.TitleFont);
		graphics.setColor(Constants.TitleColor);
		graphics.drawString("Choose number of players", Constants.PlayerSelectTitlePosition.x,
				Constants.PlayerSelectButtonBounds.y);
	}

	@Override
	public void initialize() {
		
		this.clearButtons();
		for (int i = 3; i <= 7; i++) {
			Button startGame = new Button(new Point(400 + 250 * (i - 3), 400), Constants.PlayerSelectButtonBounds,
					i + "");
			this.addInteractable(startGame);
		}

	}

}
