package gui.menus;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ResourceBundle;

import constants.Constants;
import constants.GeneralEnums.GameMode;
import gui.interactables.Button;
import gui.interactables.Interactable;
import utils.Message;
import utils.RenderImage;
import utils.Translate;

public class Logistics extends Menu {

	public RenderImage renderer;
	ResourceBundle messages = Translate.getNewResourceBundle();
	private GameMode mode;

	public Logistics(RenderImage renderer) {
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
		this.mode = Message.selectDifficulty();
		this.clearInteractables();
		for (int i = 3; i <= 7; i++) {
			Button startGame = new Button(new Point(400 + 250 * (i - 3), 400), Constants.PlayerSelectButtonBounds,
					i + "");
			Image image = this.renderer.getImage(i + "");
			startGame.addImage(image);
			this.addInteractable(startGame);
		}

	}
	
	public GameMode getGameMode() {
		return this.mode;
	}

}
