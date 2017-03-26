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
	public void mouseClicked(MouseEvent arg0) {
		ArrayList<Button> buttons = menu.GetActiveButtons();
		System.out.println(arg0.getPoint());
		for (Button button : buttons){
			if (button.checkCollision(new Point(arg0.getPoint().x - 10, arg0.getPoint().y - 35))){
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
