package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import GuiDataStructures.Constants;
import backend.SetUpHandler;
import dataStructures.Player;
import guiMain.Interactables.Interactable;
import guiMain.Interactables.MenuKeyListener;
import guiMain.Interactables.MenuMouseListener;
import guiMain.Menus.Game;
import guiMain.Menus.MainMenu;
import guiMain.Menus.Menu;
import guiMain.Menus.PlayerSelect;

public class GuiMainMenu extends JPanel implements ActionListener {
	
	private Menu current;
	private JFrame frame;
	private Timer timer;
	private Integer numOfPlayers;

	public enum MenuType {
		MainMenu, PlayerSelect, Game
	}

	public GuiMainMenu() {
		this.frame = new JFrame();
		this.frame.setSize(Constants.FrameWidth, Constants.FrameHeight);
		this.frame.setVisible(true);
		this.frame.setTitle("Seven Wonders");
		this.frame.setResizable(false);
		this.frame.add(this);
		this.frame.addKeyListener(new MenuKeyListener());
		MenuMouseListener menuMouse = new MenuMouseListener(this);
		this.frame.addMouseListener(menuMouse);
		this.switchMenu(MenuType.MainMenu);
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

		this.current.draw(graphics);
	}

	public void onClick(Interactable clicked) {
		String text = clicked.getValue();
		String classString = current.getClass().getName();
		System.out.println(classString);
		String currentMenu = classString.substring(14);
		switch (currentMenu) {
		case "MainMenu":
			switchMenu(MenuType.PlayerSelect);
			break;
		case "PlayerSelect":
			numOfPlayers = Integer.parseInt(text);
			switchMenu(MenuType.Game);
			break;
		case "Game":
			this.current.onClick(clicked);
			break;
		}
	}

	public void switchMenu(MenuType menu) {
		
		switch (menu) {
		case MainMenu:
			this.current = new MainMenu();
			break;
		case PlayerSelect:
			this.current = new PlayerSelect();
			break;
		case Game:
			this.current = new Game(this.numOfPlayers);
			break;
		}	
		this.current.initialize();
	}

	public ArrayList<Interactable> getActiveButtons() {
		return this.current.getInteractables();
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public Timer getTimer() {
		return timer;
	}

	public static void main(String[] args) {
		GuiMainMenu menu = new GuiMainMenu();
	}

}