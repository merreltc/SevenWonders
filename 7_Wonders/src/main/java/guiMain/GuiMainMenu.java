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
	private static final int FrameWidth = 1900;
	private static final int FrameHeight = 1000;

	private static final Point StartButtonPosition = new Point(850, 400);
	private static final Point StartButtonBounds = new Point(200, 100);

	private static final Font TitleFont = new Font("Courier New", Font.BOLD, 70);
	private static final Color TitleColor = Color.cyan;

	private static final Point MainMenuTitlePosition = new Point(740, 100);

	private static final Point PlayerSelectButtonBounds = new Point(150, 100);
	private static final Point PlayerSelectTitlePosition = new Point(570, 300);

	private static final Font ErrorFont = new Font("Courier New", Font.BOLD, 100);
	private static final Point ErrorMessagePosition = new Point(500, 400);

	private static final int MessageTimeLength = 60;

	private JFrame frame;
	private Timer timer;
	private Menu currentMenu;
	private MenuMouseListener menuMouse;
	private boolean initialized = false;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	// private ArrayList<Player> players = new ArrayList<Player>();
	private String message = "Error";
	private int messageTimeCounter = 0;
	private Integer numOfPlayers;

	public enum Menu {
		MainMenu, PlayerSelect, Game
	}

	public GuiMainMenu() {
		currentMenu = Menu.MainMenu;
	}

	public void start() {
		frame = new JFrame();
		frame.setSize(FrameWidth, FrameHeight);
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

	public JFrame getFrame() {
		return frame;
	}

	public Timer getTimer() {
		return timer;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public void paintComponent(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, FrameWidth, FrameHeight);
		
		switch (this.currentMenu) {
		case MainMenu:
			if (!initialized) {
				intializeMainMenu();
			}
			graphics.setFont(TitleFont);
			graphics.setColor(TitleColor);
			graphics.drawString("7 Wonders", MainMenuTitlePosition.x, MainMenuTitlePosition.y);

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
			graphics.setFont(TitleFont);
			graphics.setColor(TitleColor);
			graphics.drawString("Choose number of players", PlayerSelectTitlePosition.x, PlayerSelectButtonBounds.y);
			break;
			
		case Game:
			if (!initialized) {
				initializeGame();
			}
			for (int i = 0; i < boards.size(); i++) {

				boards.get(i).draw(graphics);
			}
			if (this.messageTimeCounter > 0) {
				this.drawMessageOnScreen(graphics);
				this.messageTimeCounter--;
			}
			break;
		}
	}

	private void initializeGame() {
		for (int i = -1; i < this.numOfPlayers - 1; i++) {
			PlayerBoard board = new PlayerBoard(i, numOfPlayers);
			boards.add(board);
		}
		initialized = true;
	}

	private void intializePlayerSelect() {
		this.buttons.clear();
		for (int i = 3; i <= 7; i++) {
			Button startGame = new Button(new Point(400 + 250 * (i - 3), 400), PlayerSelectButtonBounds, i + "");
			buttons.add(startGame);
		}
		initialized = true;
	}

	private void intializeMainMenu() {
		this.buttons.clear();
		Button startGame = new Button(StartButtonPosition, StartButtonBounds, "Start");
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
			// players = (Method to get/set players)
			switchMenu(Menu.Game);
			break;
		}
	}

	public void switchMenu(Menu menu) {
		this.currentMenu = menu;
		this.initialized = false;
	}

	public ArrayList<Button> getActiveButtons() {
		return buttons;
	}

	public void drawMessageOnScreen(Graphics graphics) {
		graphics.setFont(ErrorFont);
		graphics.setColor(TitleColor);
		graphics.drawString(this.message, ErrorMessagePosition.x, ErrorMessagePosition.y);
	}

	public void setMessage(String message) {
		this.message = message;
		this.messageTimeCounter = this.MessageTimeLength;
	}

	public static void main(String[] args) {
		GuiMainMenu menu = new GuiMainMenu();
		menu.start();
	}
}
