package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import constants.Constants;
import constants.GeneralEnums.GameMode;
import gui.interactables.Interactable;
import gui.interactables.MenuMouseListener;
import gui.menus.GameDisplay;
import gui.menus.Logistics;
import gui.menus.MainMenu;
import gui.menus.Menu;
import utils.DropDownMessage;
import utils.RenderImage;
import utils.Translate;

public class Main extends JPanel implements ActionListener {

	private Menu current;
	private Integer numOfPlayers;
	private Image image;
	private RenderImage renderer = new RenderImage();

	public enum MenuType {
		MAINMENU, LOGISTICS, GAMEDISPLAY
	}

	public Main() {
		Constants.LOCALE = DropDownMessage.selectLanguageMessage();
		ResourceBundle messages = Translate.getNewResourceBundle();
		this.switchMenu(MenuType.MAINMENU);
		JFrame frame = new JFrame();
		frame.setSize(Constants.FrameWidth, Constants.FrameHeight);
		frame.setVisible(true);
		frame.setTitle(messages.getString("sevenWonders"));
		frame.setResizable(false);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		MenuMouseListener menuMouse = new MenuMouseListener(this);
		frame.addMouseListener(menuMouse);
		image = renderer.getImage("Background");
		Timer timer = new Timer(20,this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
	}

	@Override
	public void paintComponent(Graphics graphics) {
		graphics.setColor(Color.RED);
		renderer.setImage(image);
		renderer.draw(graphics, new int[] {0, 0, Constants.FrameWidth, Constants.FrameHeight});
		this.current.draw(graphics);
	}

	public void onClick(Interactable clicked) {
		String text = clicked.getValue();
		String classString = current.getClass().getName();
		String currentMenu = classString.substring(10);
		switch (currentMenu) {
		case "MainMenu":
			switchMenu(MenuType.LOGISTICS);
			break;
		case "Logistics":
			numOfPlayers = Integer.parseInt(text);
			switchMenu(MenuType.GAMEDISPLAY);
			break;
		case "GameDisplay":
			this.current.onClick(clicked);
			break;
		}
	}

	public void switchMenu(MenuType menu) {
		switch (menu) {
		case MAINMENU:
			this.current = new MainMenu();
			break;
		case LOGISTICS:
			this.current = new Logistics(this.renderer);
			break;
		case GAMEDISPLAY:
			GameMode mode = ((Logistics) this.current).getGameMode();
			this.current = new GameDisplay(this.numOfPlayers, this.renderer, mode);
			break;
		}
		this.current.initialize();
	}

	public ArrayList<Interactable> getActiveButtons() {
		return this.current.getInteractables();
	}

	public static void main(String[] args) {
		new Main();
	}

}
