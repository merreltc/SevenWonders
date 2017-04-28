package guiMain.Menus;

import java.awt.Graphics;
import java.util.ArrayList;

import guiDataStructures.Constants;
import guiMain.Interactables.Button;
import guiMain.Interactables.Interactable;

public class MainMenu extends Menu{

	public void draw(Graphics graphics) {
		graphics.setFont(Constants.TitleFont);
		graphics.setColor(Constants.TitleColor);

		for (Interactable button : this.getInteractables()) {
			button.draw(graphics);
		}
	}

	@Override
	public void initialize() {
		this.clearInteractables();
		Interactable startGame = new Button(Constants.StartButtonPosition, Constants.StartButtonBounds, "Start");
		this.addInteractable(startGame);
	}

}
