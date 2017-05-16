package gui.interactables;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import main.Main;

public class MenuMouseListener implements MouseListener {

	private Main menu;

	public MenuMouseListener(Main menu) {
		this.menu = menu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		ArrayList<Interactable> buttons = (ArrayList<Interactable>) menu.getActiveButtons().clone();
		for (Interactable button : buttons) {
			/* fix the offset of getPoint by x - 10 and y - 35 */
			Point offsetClickedPoint = new Point(mouseEvent.getPoint().x - 10, mouseEvent.getPoint().y - 35);
			if (button.checkButtonClicked(offsetClickedPoint)) {
				this.menu.onClick(button);
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
