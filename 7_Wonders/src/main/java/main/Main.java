package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import constants.Constants;
import gui.interactables.Interactable;
import gui.interactables.MenuMouseListener;
import gui.menus.GameDisplay;
import gui.menus.MainMenu;
import gui.menus.Menu;
import gui.menus.PlayerSelect;
import utils.RenderImage;

public class Main extends JPanel implements ActionListener {

	private Menu current;
	private JFrame frame;
	private Timer timer;
	private Integer numOfPlayers;
	private Image image;
	private RenderImage renderer = new RenderImage();
	ResourceBundle messages = ResourceBundle.getBundle("message", Locale.getDefault());

	public enum MenuType {
		MainMenu, PlayerSelect, Game
	}

	public Main() {
		this.switchMenu(MenuType.MainMenu);
		this.frame = new JFrame();
		this.frame.setSize(Constants.FrameWidth, Constants.FrameHeight);
		this.frame.setVisible(true);
		this.frame.setTitle(this.messages.getString("sevenWonders"));
		this.frame.setResizable(false);
		this.frame.add(this);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MenuMouseListener menuMouse = new MenuMouseListener(this);
		this.frame.addMouseListener(menuMouse);
		image = renderer.getImage("Background");
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

		RenderImage.draw(graphics, image, 0, 0, Constants.FrameWidth, Constants.FrameHeight);
		this.current.draw(graphics);
	}

	public void onClick(Interactable clicked) {
		String text = clicked.getValue();
		String classString = current.getClass().getName();
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
			this.current = new PlayerSelect(this.renderer);
			break;
		case Game:
			this.current = new GameDisplay(this.numOfPlayers,this.renderer);
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
		new Main();
	}

}
