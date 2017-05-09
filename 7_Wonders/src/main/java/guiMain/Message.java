package guiMain;

import java.awt.Graphics;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import dataStructures.Wonder;
import guiDataStructures.Constants;

public class Message {

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static String inputPlayerNameMessage(int i) {
		ResourceBundle messages = ResourceBundle.getBundle("message", Locale.getDefault());
		Object[] messageArgs = {new Integer(i)};
		MessageFormat format = new MessageFormat("");
		format.setLocale(Locale.getDefault());
		format.applyPattern(messages.getString("playerNameTemplate"));
		
		String name = JOptionPane.showInputDialog(format.format(messageArgs));
		if (name == null || name.equals("")) {
			name = "Player " + (i + 1);
		}
		return name;
	}

	public static String dropDownWonderSelectionMessage(Object[] wonders) {
		ResourceBundle messages = ResourceBundle.getBundle("message", Locale.getDefault());
		return (String) JOptionPane.showInputDialog(null,
				messages.getString("chooseYourWonder"),
				messages.getString("wonderSelector"),
				JOptionPane.PLAIN_MESSAGE,
				null,
				wonders,
				wonders[0]);
	}
}
