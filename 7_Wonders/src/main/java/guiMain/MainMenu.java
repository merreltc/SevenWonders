package guiMain;

import java.awt.Graphics;
import java.util.ArrayList;

import GuiDataStructures.Constants;

public class MainMenu extends Menu{

	public void draw(Graphics graphics) {
		graphics.setFont(Constants.TitleFont);
		graphics.setColor(Constants.TitleColor);
		graphics.drawString("7 Wonders", Constants.MainMenuTitlePosition.x, Constants.MainMenuTitlePosition.y);

		for (Interactable button : this.getInteractables()) {
			button.draw(graphics);
		}
	}

	@Override
	public void initialize() {
		this.clearButtons();
		Interactable startGame = new Button(Constants.StartButtonPosition, Constants.StartButtonBounds, "Start");
		this.addInteractable(startGame);
	}

}
