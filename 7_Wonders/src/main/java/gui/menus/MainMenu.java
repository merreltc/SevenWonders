package gui.menus;

import java.awt.Graphics;
import java.util.ResourceBundle;

import constants.Constants;
import gui.interactables.Button;
import gui.interactables.Interactable;
import utils.Translate;

public class MainMenu extends Menu {

	ResourceBundle messages = Translate.getNewResourceBundle();

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
		Interactable startGame = new Button(Constants.StartButtonPosition, Constants.StartButtonBounds,
				this.messages.getString("start"));
		this.addInteractable(startGame);
	}

}
