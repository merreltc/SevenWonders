package guiMain.Menus;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import guiDataStructures.Constants;
import guiMain.Translate;
import guiMain.Interactables.Button;
import guiMain.Interactables.Interactable;

public class MainMenu extends Menu{
	
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
		Interactable startGame = new Button(Constants.StartButtonPosition, Constants.StartButtonBounds, this.messages.getString("start"));
		this.addInteractable(startGame);
	}

}
