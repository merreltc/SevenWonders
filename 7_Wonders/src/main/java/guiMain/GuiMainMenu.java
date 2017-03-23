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

public class GuiMainMenu  extends JPanel implements ActionListener{

	private JFrame frame;
	private Timer timer;
	private Menu currentMenu;
	private MenuMouseListener menuMouse;
	private boolean initialized = false;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	public enum Menu {
		MainMenu,
		PlayerSelect,
		Game
	}
	
	public GuiMainMenu(){
		currentMenu = Menu.MainMenu;
	}
	
	public void Start(){
		frame = new JFrame();
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		frame.setTitle("Seven Wonders");
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

	public Timer GetTimer(){
		return timer;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics graphics){
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, 1000, 1000);
		switch (this.currentMenu){
			case MainMenu: 
				if (!initialized){
					Button startGame = new Button(new Point(400, 400), new Point(200,100), "ToPlayerSelect");
					buttons.add(startGame);
					initialized = true;
				}
				graphics.setFont(new Font("Courier New", Font.BOLD, 70));
				graphics.setColor(Color.cyan);
				graphics.drawString("7 Wonders",290,100);
				
				for (Button button : buttons){
					button.draw(graphics);
				}
				graphics.setColor(Color.RED);
				graphics.setFont(new Font("Courier New", Font.BOLD, 50));
				graphics.drawString("Start",420,460);
				break;		
			case PlayerSelect:
				
				break;
			case Game:
				
				break;
		}
	}
	
	public void onButtonClick(Button clicked){
		String test = clicked.getValue();
		switch (currentMenu) {
			case MainMenu:
				SwitchMenu(Menu.PlayerSelect);
				break;
		}
	}
	

	public void SwitchMenu(Menu menu){
		this.currentMenu = menu;
	}
	
	public ArrayList<Button> GetActiveButtons(){
		return buttons;
	}
	
	public static void main(String[] args){
		GuiMainMenu menu = new GuiMainMenu();
		menu.Start();
	}
}
