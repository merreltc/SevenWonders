package utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class DropDownMessage {

	public static String dropDownWonderSelectionMessage(Object[] wonders) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, messages.getString("chooseYourWonder"),
				messages.getString("wonderSelector"), JOptionPane.PLAIN_MESSAGE, null, wonders, wonders[0]);
	}

	public static String dropDownScienceSelectionMessage() {
		Object[] objects = { "Protractor", "Wheel", "Tablet" };
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, messages.getString("chooseAScience"),
				messages.getString("chooseAScience"), JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
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

}
