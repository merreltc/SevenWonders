package guiMain;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Menu {
	
	private ArrayList<Button> buttons = new ArrayList<Button>();

	public void Menu(){
		
	}
	
	public abstract void draw(Graphics graphics);
	
	public abstract void initialize();
	
	public ArrayList<Button> getButtons(){
		return buttons;
	}
	
	public void addButton(Button button){
		this.buttons.add(button);
	}
	
	public void clearButtons(){
		this.buttons.clear();
	}
}
