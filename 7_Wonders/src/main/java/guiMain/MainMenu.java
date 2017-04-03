package guiMain;

import java.awt.Graphics;
import java.util.ArrayList;

public class MainMenu {
	private ArrayList<Button> buttons = new ArrayList<Button>();

	public void draw(Graphics graphics) {
		graphics.setFont(Constants.TitleFont);
		graphics.setColor(Constants.TitleColor);
		graphics.drawString("7 Wonders", Constants.MainMenuTitlePosition.x, Constants.MainMenuTitlePosition.y);

		for (Button button : buttons) {
			button.draw(graphics);
		}
	}

	public void initializeMainMenu() {
		this.buttons.clear();
		Button startGame = new Button(Constants.StartButtonPosition, Constants.StartButtonBounds, "Start");
		buttons.add(startGame);
	}
	
	public ArrayList<Button> getButtons() {
		return this.buttons;
	}

}
