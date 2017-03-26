package guiMain;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MenuMouseListener implements MouseListener{

	private GuiMainMenu menu;
	
	public MenuMouseListener(GuiMainMenu menu){
		this.menu = menu;
	}
	
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		ArrayList<Button> buttons = menu.GetActiveButtons();
		//Refactor checkcollisions and set offset points to named variables
		for (Button button : buttons){
			if (button.checkCollision(new Point(mouseEvent.getPoint().x - 10, mouseEvent.getPoint().y - 35))){
				this.menu.onButtonClick(button);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
