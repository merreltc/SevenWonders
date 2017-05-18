package utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import constants.Constants;
import constants.GeneralEnums.GameMode;

public class Message {

	public static void showMessage(String message) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		changeJOptionPaneLocale();
		JOptionPane.showMessageDialog(null, message, messages.getString("msg"), JOptionPane.PLAIN_MESSAGE);
	}


	public static String inputPlayerNameMessage(int i) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		changeJOptionPaneLocale();
		String msg = TranslateWithTemplate.prepareStringTemplateWithIntArg(i + 1, "playerNameTemplate", messages);
		String name = JOptionPane.showInputDialog(null, msg, messages.getString("inputPlayerName"),
				JOptionPane.PLAIN_MESSAGE);
		if (name == null || name.equals("")) {
			name = TranslateWithTemplate.prepareStringTemplateWithIntArg(i + 1, "defaultPlayerTemplate", messages);
		}
		return name;
	}

	public static GameMode selectDifficulty() {
		ResourceBundle messages = Translate.getNewResourceBundle();
		Object[] objects = { messages.getString("easy"), messages.getString("normal") };
		int result = JOptionPane.showOptionDialog(null, messages.getString("selectDifficulty"),
				messages.getString("difficulty"), JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, objects,
				null);
		return (result == JOptionPane.YES_OPTION) ? GameMode.EASY : GameMode.NORMAL;
	}

	public static void changeJOptionPaneLocale() {
		String[] country_lang = Constants.LOCALE.split("_");
		Locale locale = new Locale(country_lang[0], country_lang[1]);
		JOptionPane.setDefaultLocale(locale);
	}
}
