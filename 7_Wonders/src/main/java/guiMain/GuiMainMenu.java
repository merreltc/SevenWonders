package guiMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GuiMainMenu implements ActionListener {

	private JFrame frame;
	
	public void Start(){
		frame = new JFrame();
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		frame.setTitle("Seven Wonders");
	}
	
	public JFrame GetFrame() {
		return frame;
	}

	public Timer GetTimer(){
		return new Timer(60, this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
