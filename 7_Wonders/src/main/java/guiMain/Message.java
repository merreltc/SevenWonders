package guiMain;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import GuiDataStructures.Constants;

public class Message {
	private String message = "";
	
	public void setMessage(String message) {
		this.message = message;
		JOptionPane.showMessageDialog(null, this.message);
	}
}
