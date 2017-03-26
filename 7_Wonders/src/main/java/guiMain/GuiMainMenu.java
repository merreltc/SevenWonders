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

	private static final int MessageTimeLength = 60;
	
	private JFrame frame;
	private Timer timer;
	private Menu currentMenu;
	private MenuMouseListener menuMouse;
	private boolean initialized = false;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<PlayerBoard> boards = new ArrayList<>();
	//private ArrayList<Player> players = new ArrayList<Player>();
	private String message = "Error";
	private int messageTimeCounter = 0;
	private Integer numOfPlayers;

	public enum Menu {
		MainMenu, PlayerSelect, Game
	}

	public GuiMainMenu() {
		currentMenu = Menu.MainMenu;
	}

	public void Start() {
		frame = new JFrame();
		frame.setSize(1900, 1000);
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

	public JFrame GetFrame() {
		return frame;
	}

	public Timer GetTimer() {
		return timer;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public void paintComponent(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, 1900, 1000);
		switch (this.currentMenu) {
		case MainMenu:
			if (!initialized) {
				this.buttons.clear();
				Button startGame = new Button(new Point(850, 400), new Point(200, 100), "Start");
				buttons.add(startGame);
				initialized = true;
			}
			graphics.setFont(new Font("Courier New", Font.BOLD, 70));
			graphics.setColor(Color.cyan);
			graphics.drawString("7 Wonders", 740, 100);

			for (Button button : buttons) {
				button.draw(graphics);
			}
			break;
		case PlayerSelect:
			if (!initialized) {
				this.buttons.clear();
				for (int i = 3; i <= 7; i++) {
					Button startGame = new Button(new Point(400 + 250 * (i - 3), 400), new Point(150, 100), i + "");
					buttons.add(startGame);
				}
				initialized = true;
			}
			for (Button button : buttons) {
				button.draw(graphics);
			}
			graphics.setFont(new Font("Courier New", Font.BOLD, 50));
			graphics.setColor(Color.cyan);
			graphics.drawString("Choose number of players", 570, 300);
			break;
		case Game:
			
			if (!initialized) {
				for (int i = -1; i < this.numOfPlayers-1; i++){
					PlayerBoard board = new PlayerBoard(i, 5);
					boards.add(board);
				}
				initialized = true;
			}
			for (int i = 0; i < boards.size(); i++){
				
				boards.get(i).draw(graphics);
			}
			if (this.messageTimeCounter > 0){
				this.DrawMessageOnScreen(graphics);
				this.messageTimeCounter--;
			}
			break;
		}
	}

	public void onButtonClick(Button clicked) {
		String text = clicked.getValue();
		switch (currentMenu) {
		case MainMenu:
			SwitchMenu(Menu.PlayerSelect);
			break;
		case PlayerSelect:
			numOfPlayers = Integer.parseInt(text);
			//players = (Method to get/set players)
			SwitchMenu(Menu.Game);
			break;
		}
	}

	public void SwitchMenu(Menu menu) {
		this.currentMenu = menu;
		this.initialized = false;
	}

	public ArrayList<Button> GetActiveButtons() {
		return buttons;
	}
	
	public void DrawMessageOnScreen(Graphics graphics){
		graphics.setFont(new Font("Courier New", Font.BOLD, 100));
		graphics.setColor(Color.cyan);
		graphics.drawString(this.message, 500, 400);
		
	}
	
	public void SetMessage(String message){
		this.message = message;
		this.messageTimeCounter = this.MessageTimeLength;
	}

	public static void main(String[] args) {
		GuiMainMenu menu = new GuiMainMenu();
		menu.Start();
	}
}
