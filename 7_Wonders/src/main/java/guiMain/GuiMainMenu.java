package guiMain;

import javax.swing.JFrame;

public class GuiMainMenu {

	private JFrame frame;
	
	public void Start(){
		frame = new JFrame();
		frame.setSize(1000, 1000);
	}
	
	public JFrame GetFrame() {
		return frame;
	}
	
}
