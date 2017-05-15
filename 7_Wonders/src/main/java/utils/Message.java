package utils;

import java.awt.Graphics;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import constants.Constants;
import dataStructures.gameMaterials.Wonder;

public class Message {

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static String inputPlayerNameMessage(int i) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		String name = JOptionPane
				.showInputDialog(Translate.prepareStringTemplateWithIntArg(i + 1, "playerNameTemplate", messages));
		if (name == null || name.equals("")) {
			name = Translate.prepareStringTemplateWithIntArg(i + 1, "defaultPlayerTemplate", messages);
		}
		return name;
	}

	public static String dropDownWonderSelectionMessage(Object[] wonders) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, messages.getString("chooseYourWonder"),
				messages.getString("wonderSelector"), JOptionPane.PLAIN_MESSAGE, null, wonders, wonders[0]);
	}
}
