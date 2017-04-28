package guiMain.Menus;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import guiDataStructures.Constants;
import guiMain.RenderImage;
import guiMain.Interactables.Button;
import guiMain.Interactables.Interactable;

public class PlayerSelect extends Menu{

	@Override
	public void draw(Graphics graphics) {
		for (Interactable button : this.getInteractables()) {
			button.draw(graphics);
		}
		graphics.setFont(Constants.TitleFont);
		graphics.setColor(Constants.TitleColor);
		graphics.drawString("Choose number of players", Constants.PlayerSelectTitlePosition.x,
				Constants.PlayerSelectTitlePosition.y);
	}

	@Override
	public void initialize() {
		this.clearInteractables();
		for (int i = 3; i <= 7; i++) {
			Button startGame = new Button(new Point(400 + 250 * (i - 3), 400), Constants.PlayerSelectButtonBounds,
					i + "");
			Image image = RenderImage.getImage(i + "");
			startGame.addImage(image);
			this.addInteractable(startGame);
		}

	}

}
