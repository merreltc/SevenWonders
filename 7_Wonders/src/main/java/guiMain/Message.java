package guiMain;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import GuiDataStructures.Constants;

public class Message {
	
	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	
	public static String inputPlayerNameMessage(int i) {
		String name = JOptionPane.showInputDialog("Player " + (i + 1) + " name:");
		if (name == null || name.equals("")){
			name = "Player " + (i + 1);
		}
		return name;
	}
}
