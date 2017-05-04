package guiMain.Interactables;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import guiMain.GuiMainMenu;

public class MenuMouseListener implements MouseListener {

	private GuiMainMenu menu;

	public MenuMouseListener(GuiMainMenu menu) {
		this.menu = menu;
	}

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
