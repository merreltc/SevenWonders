package guiMain.Menus;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import guiMain.Interactables.Interactable;

public abstract class Menu {
	
	private ArrayList<Interactable> interactables = new ArrayList<>();
	public Lock lock = new ReentrantLock();

	public abstract void draw(Graphics graphics);
	
	public abstract void initialize();
	
	public ArrayList<Interactable> getInteractables(){
		return interactables;
	}
	
	public void addInteractable(Interactable button){
		lock.lock();
		this.interactables.add(button);
		lock.unlock();
	}
	
	public void removeInteractable(Interactable toRemove){
		lock.lock();
		this.interactables.remove(toRemove);
		lock.unlock();
	}
	
	public void clearInteractables() {
		lock.lock();
		this.interactables.clear();
		lock.unlock();
	}
	
	public void onClick(Interactable clicked) {
	}

}
