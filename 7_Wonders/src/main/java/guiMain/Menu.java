package guiMain;

import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Menu {
	
	private ArrayList<Interactable> interactables = new ArrayList<>();

	public abstract void draw(Graphics graphics);
	
	public abstract void initialize();
	
	public ArrayList<Interactable> getInteractables(){
		return interactables;
	}
	
	public void addInteractable(Interactable button){
		this.interactables.add(button);
	}
	
	public void clearButtons(){
		this.interactables.clear();
	}
	
	public void onClick(Interactable clicked) {
	}

}
