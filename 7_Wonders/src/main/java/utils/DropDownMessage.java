package utils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JOptionPane;

public class DropDownMessage {

	public static String dropDownWonderSelectionMessage(Object[] wonders) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, messages.getString("chooseYourWonder"),
				messages.getString("wonderSelector"), JOptionPane.PLAIN_MESSAGE, null, wonders, wonders[0]);
	}

	public String dropDownScienceSelectionMessage() {
		Object[] objects = { "PROTRACTOR", "WHEEL", "TABLET" };
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

	public String dropDownEntitySelectionMessage(Set<Enum> keySet, String cardName) {
		Object[] objects = keySet.toArray(new Object[keySet.size()]);
		Object[] string = new Object[keySet.size()];

		for (int i = 0; i < keySet.size(); i++) {
			string[i] = objects[i].toString();
		}
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, "Choose which card effect to activiate",
				cardName, JOptionPane.PLAIN_MESSAGE, null, string, string[0]);
	}

	public static String dropDownGuildSelectionMessage(Object[] wonders) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, messages.getString("chooseYourWonder"),
				messages.getString("wonderSelector"), JOptionPane.PLAIN_MESSAGE, null, wonders, wonders[0]);
	}

	public String dropDownPlayOrDiscardMessage() {
		Object[] objects = { "Play", "Discard" };
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, "Play Or Discard",
				"7th Card", JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
	}
	
	public String dropDownBuildMessage() {
		Object[] objects = { "Pay", "Free" };
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, "Pay Or Free",
				"Build Cost", JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
	}

	public String dropDownResourceSelectionMessage() {
		Object[] objects = { "LUMBER", "ORE", "CLAY", "STONE" };
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, messages.getString("chooseAScience"),
				messages.getString("chooseAScience"), JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
	}
}
