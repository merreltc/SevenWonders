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

	private static final Font ErrorFont = new Font("Courier New", Font.BOLD, 70);
	private static final Point ErrorMessagePosition = new Point(600, 400);
	
	private static final Point CloseMessageButtonBounds = new Point(200, 80);
	private static final Point CloseMessageButtonPosition = new Point(FrameWidth - CloseMessageButtonBounds.x - 50, FrameHeight - CloseMessageButtonBounds.y - 50);
	
	private static final Point TradeButtonBounds = new Point(20,20);
	
	private static final Point TradeLeftBaseButtonPoint = new Point(30, 300);
	private static final Point TradeRightBaseButtonPoint = new Point(1390,300);
	
	private static final int TradeButtonYOffet = TradeButtonBounds.y + 20;

	private JFrame frame;
	private Timer timer;
	private Menu currentMenu;
	private MenuMouseListener menuMouse;
	private boolean initialized = false;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	// private ArrayList<Player> players = new ArrayList<Player>();
	private String message = "Error";
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
			for (Button button : buttons) {
				button.draw(graphics);
			}
			for (int i = 0; i < boards.size(); i++) {

				boards.get(i).draw(graphics);
			}
			if (!this.message.equals("")) {
				this.drawMessageOnScreen(graphics);
			}
			break;
		}
	}

	private void initializeGame() {
		this.buttons.clear();
		for (int i = -1; i < this.numOfPlayers - 1; i++) {
			PlayerBoard board = new PlayerBoard(i, numOfPlayers);
			boards.add(board);
		}
		Button exitMessage = new Button(CloseMessageButtonPosition, CloseMessageButtonBounds, "Close");
		buttons.add(exitMessage);
		String[] values = new String[]{"Stone","Wood","Ore","Clay"};
		for (int i = 0; i < 4; i++){
			Point buttonPosition = new Point(TradeLeftBaseButtonPoint.x, TradeLeftBaseButtonPoint.y + TradeButtonYOffet*i);
			Button LeftTradeButton = new Button(buttonPosition, TradeButtonBounds, "Left-" + values[i],false);
			buttons.add(LeftTradeButton);
		}
		for (int i = 0; i < 4; i++){
			Point buttonPosition = new Point(TradeRightBaseButtonPoint.x, TradeRightBaseButtonPoint.y + TradeButtonYOffet*i);
			Button RightTradeButton = new Button(buttonPosition, TradeButtonBounds, "Right-" + values[i],false);
			buttons.add(RightTradeButton);
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
		case Game:
			if (clicked.getValue().equals("Close")){
				this.message = "";
			}else {
				String[] splitValue = clicked.getValue().split("-");
				if (splitValue[0].equals("Right")){
					//Trade splitValue[1] with right player
				}else {
					//Trade splitValue[1] with left player
				}
			}
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
	}

	public static void main(String[] args) {
		GuiMainMenu menu = new GuiMainMenu();
		menu.start();
	}
}
