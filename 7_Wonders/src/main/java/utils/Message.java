package utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import constants.Constants;

public class Message {

	public static void showMessage(String message) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		changeJOptionPaneLocale();
		JOptionPane.showMessageDialog(null, message, messages.getString("msg"), JOptionPane.PLAIN_MESSAGE);
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
		changeJOptionPaneLocale();
		String msg = Translate.prepareStringTemplateWithIntArg(i + 1, "playerNameTemplate", messages);
		String name = JOptionPane.showInputDialog(null, msg, messages.getString("inputPlayerName"), JOptionPane.PLAIN_MESSAGE);
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

	
	public String dropDownScienceSelectionMessage() {
		Object[] objects = { "Protractor", "Wheel", "Tablet" };
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, messages.getString("chooseAScience"),
				messages.getString("chooseAScience"), JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
	}

	public static int selectDifficulty() {
		ResourceBundle messages = Translate.getNewResourceBundle();
		Object[] objects = { messages.getString("easy"), messages.getString("normal") };
		return JOptionPane.showOptionDialog(null, messages.getString("selectDifficulty"), messages.getString("difficulty"), JOptionPane.YES_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, objects, null);
	}
	
	public static void changeJOptionPaneLocale() {
		String[] country_lang = Constants.LOCALE.split("_");
		Locale locale = new Locale(country_lang[0], country_lang[1]);
		JOptionPane.setDefaultLocale(locale);
	}
}
