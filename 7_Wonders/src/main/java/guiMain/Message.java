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

	public static int SelectDifficulty() {
		Object[] objects = { "Easy", "Normal" };
		return JOptionPane.showOptionDialog(null, "Select Your Difficulty", "Difficulty", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, objects, null);
	}
}
