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

public class GuiMainMenu extends JPanel implements ActionListener {
	private Game game;
	private PlayerSelect playerSelect;
	private MainMenu mainMenu;
	private JFrame frame;
	private Timer timer;
	private Menu currentMenu;
	private MenuMouseListener menuMouse;
	private boolean initialized = false;
	private Integer numOfPlayers;

	public enum Menu {
		MainMenu, PlayerSelect, Game
	}

	public GuiMainMenu() {
		currentMenu = Menu.MainMenu;
	}

	public void start() {
		this.frame = new JFrame();
		this.frame.setSize(Constants.FrameWidth, Constants.FrameHeight);
		this.frame.setVisible(true);
		this.frame.setTitle("Seven Wonders");
		this.frame.setResizable(false);
		this.frame.add(this);
		this.frame.addKeyListener(new MenuKeyListener());
		this.menuMouse = new MenuMouseListener(this);
		this.frame.addMouseListener(menuMouse);
		this.timer = new Timer(20, this);
		this.timer.start();
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
				this.mainMenu = new MainMenu();
				this.mainMenu.initializeMainMenu();
				initialized = true;
			}
			this.mainMenu.draw(graphics);
			break;

		case PlayerSelect:
			if (!initialized) {
				this.playerSelect = new PlayerSelect();
				this.playerSelect.initializePlayerSelect();
				initialized = true;
			}
			this.playerSelect.draw(graphics);
			break;

		case Game:
			if (!initialized) {
				this.game = new Game(this.numOfPlayers);
				this.game.initializeGame();
				initialized = true;
			}
			this.game.draw(graphics);
			break;
		}
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
		switch (currentMenu) {
		case MainMenu:
			return this.mainMenu.getButtons();
		case PlayerSelect:
			return this.playerSelect.getButtons();
		/* if the current Menu is the Game Menu */
		default:
			return this.game.getButtons();
		}
		
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
