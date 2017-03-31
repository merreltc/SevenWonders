package guiMain;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MenuMouseListener implements MouseListener {

	private GuiMainMenu menu;

	public MenuMouseListener(GuiMainMenu menu) {
		this.menu = menu;
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		ArrayList<Button> buttons = menu.getActiveButtons();
		for (Button button : buttons) {
			/* fix the offset of getPoint by x - 10 and y - 35 */
			Point offsetClickedPoint = new Point(mouseEvent.getPoint().x - 10, mouseEvent.getPoint().y - 35);
			if (button.checkButtonClicked(offsetClickedPoint)) {
				this.menu.onButtonClick(button);
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
