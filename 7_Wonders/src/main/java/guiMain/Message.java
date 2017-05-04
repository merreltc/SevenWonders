package guiMain;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import dataStructures.Wonder;
import guiDataStructures.Constants;

public class Message {

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static String inputPlayerNameMessage(int i) {
		String name = JOptionPane.showInputDialog("Player " + (i + 1) + " name:");
		if (name == null || name.equals("")) {
			name = "Player " + (i + 1);
		}
		return name;
	}

	public static String dropDownWonderSelectionMessage(Object[] wonders) {
		return (String) JOptionPane.showInputDialog(null,
				"Choose your Wonder",
				"Wonder Selector",
				JOptionPane.PLAIN_MESSAGE,
				null,
				wonders,
				wonders[0]);
	}
}
