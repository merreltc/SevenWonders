package guiMain.Menus;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import guiDataStructures.Constants;
import guiMain.Message;
import guiMain.RenderImage;
import guiMain.Translate;
import guiMain.Interactables.Button;
import guiMain.Interactables.Interactable;

public class PlayerSelect extends Menu {

	public RenderImage renderer;
	ResourceBundle messages = Translate.getNewResourceBundle();

	public PlayerSelect(RenderImage renderer) {
		this.renderer = renderer;
	}

	@Override
	public void draw(Graphics graphics) {
		for (Interactable button : this.getInteractables()) {
			button.draw(graphics);
		}
		graphics.setFont(Constants.TitleFont);
		graphics.setColor(Constants.TitleColor);
		graphics.drawString(this.messages.getString("chooseNumberOfPlayers"), Constants.PlayerSelectTitlePosition.x,
				Constants.PlayerSelectTitlePosition.y);
	}

	@Override
	public void initialize() {
		Message.SelectDifficulty();
		this.clearInteractables();
		for (int i = 3; i <= 7; i++) {
			Button startGame = new Button(new Point(400 + 250 * (i - 3), 400), Constants.PlayerSelectButtonBounds,
					i + "");
			Image image = this.renderer.getImage(i + "");
			startGame.addImage(image);
			this.addInteractable(startGame);
		}

	}

}
