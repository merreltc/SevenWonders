package utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import constants.GeneralEnums.GameMode;

public class Message {

	public static void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public static String selectLanguageMessage() {
		ResourceBundle messages = ResourceBundle.getBundle("message", Locale.getDefault());
		String[] langs = { messages.getString("en_US"), messages.getString("zh_CN") };
		String language = (String) JOptionPane.showInputDialog(null, messages.getString("chooseYourLanguage"),
				messages.getString("languageSelector"), JOptionPane.PLAIN_MESSAGE, null, langs, langs[0]);
		if (language == null) {
			language = " -" + Locale.getDefault().toString();
		}
		return language.split("-")[1];
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

	public static GameMode selectDifficulty() {
		Object[] objects = { "Easy", "Normal" };
		int result = JOptionPane.showOptionDialog(null, "Select Your Difficulty", "Difficulty", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, objects, null);
		System.out.println("result: " + result);
		System.out.println("yes option: " + JOptionPane.YES_OPTION);
		return (result == JOptionPane.YES_OPTION) ? GameMode.EASY : GameMode.NORMAL;
	}
}
