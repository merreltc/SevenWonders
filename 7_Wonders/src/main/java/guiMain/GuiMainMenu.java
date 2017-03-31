package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import backend.SetUpHandler;
import dataStructures.Player;

public class GuiMainMenu extends JPanel implements ActionListener {
	private Game game;
	private JFrame frame;
	private Timer timer;
	private Menu currentMenu;
	private MenuMouseListener menuMouse;
	private boolean initialized = false;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private Integer numOfPlayers;

	public enum Menu {
		MainMenu, PlayerSelect, Game
	}

	public GuiMainMenu() {
		currentMenu = Menu.MainMenu;
	}

	public void start() {
		frame = new JFrame();
		frame.setSize(Constants.FrameWidth, Constants.FrameHeight);
		frame.setVisible(true);
		frame.setTitle("Seven Wonders");
		frame.setResizable(false);
		frame.add(this);
		frame.addKeyListener(new MenuKeyListener());
		menuMouse = new MenuMouseListener(this);
		frame.addMouseListener(menuMouse);
		timer = new Timer(20, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public void paintComponent(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, Constants.FrameWidth, Constants.FrameHeight);

		switch (this.currentMenu) {
		case MainMenu:
			if (!initialized) {
				intializeMainMenu();
			}
			graphics.setFont(Constants.TitleFont);
			graphics.setColor(Constants.TitleColor);
			graphics.drawString("7 Wonders", Constants.MainMenuTitlePosition.x, Constants.MainMenuTitlePosition.y);

			for (Button button : buttons) {
				button.draw(graphics);
			}
			break;

		case PlayerSelect:
			if (!initialized) {
				intializePlayerSelect();
			}
			for (Button button : buttons) {
				button.draw(graphics);
			}
			graphics.setFont(Constants.TitleFont);
			graphics.setColor(Constants.TitleColor);
			graphics.drawString("Choose number of players", Constants.PlayerSelectTitlePosition.x,
					Constants.PlayerSelectButtonBounds.y);
			break;

		case Game:
			if (!initialized) {
				this.game = new Game(this.numOfPlayers);
				game.initializeGame();
				initialized = true;
			}
			game.draw(graphics);
			break;
		}
	}

	private void intializePlayerSelect() {
		this.buttons.clear();
		for (int i = 3; i <= 7; i++) {
			Button startGame = new Button(new Point(400 + 250 * (i - 3), 400), Constants.PlayerSelectButtonBounds,
					i + "");
			buttons.add(startGame);
		}
		initialized = true;
	}

	private void intializeMainMenu() {
		this.buttons.clear();
		Button startGame = new Button(Constants.StartButtonPosition, Constants.StartButtonBounds, "Start");
		buttons.add(startGame);
		initialized = true;
	}

	public void onButtonClick(Button clicked) {
		String text = clicked.getValue();
		switch (currentMenu) {
		case MainMenu:
			switchMenu(Menu.PlayerSelect);
			break;
		case PlayerSelect:
			numOfPlayers = Integer.parseInt(text);

			switchMenu(Menu.Game);
			break;
		case Game:
			this.game.onButtonClickInGame(clicked);
			break;
		}
	}

	public void switchMenu(Menu menu) {
		this.currentMenu = menu;
		this.initialized = false;
	}

	public ArrayList<Button> getActiveButtons() {
		if (this.currentMenu == Menu.Game) {
			return this.game.getButtons();
		}
		return buttons;
	}

	public JFrame getFrame() {
		return frame;
	}

	public Timer getTimer() {
		return timer;
	}

	public static void main(String[] args) {
		GuiMainMenu menu = new GuiMainMenu();
		menu.start();
	}

}
