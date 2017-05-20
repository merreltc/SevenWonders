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
		ResourceBundle messages = Translate.getNewResourceBundle();
		Object[] objects = { messages.getString("PROTRACTOR"), messages.getString("WHEEL"),
				messages.getString("TABLET") };
		String str = (String) JOptionPane.showInputDialog(null, messages.getString("chooseAScience"),
				messages.getString("chooseAScience"), JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
		if (str.equals(objects[0])) {
			return "PROTRACTOR";
		} else if (str.equals(objects[1])) {
			return "WHEEL";
		}
		return "TABLET";
	}

	public static String selectLanguageMessage() {
		ResourceBundle messages = ResourceBundle.getBundle("i18n.message", Locale.getDefault());
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

		ResourceBundle messages = Translate.getNewResourceBundle();
		for (int i = 0; i < keySet.size(); i++) {
			string[i] = messages.getString(objects[i].toString());
		}
		String str = (String) JOptionPane.showInputDialog(null, messages.getString("searchBuild"), cardName,
				JOptionPane.PLAIN_MESSAGE, null, string, string[0]);
		for (int k = 0; k < keySet.size(); k++) {
			if (str.equals(string[k])) {
				return objects[k].toString();
			}
		}
		return "";
	}

	public static String dropDownGuildSelectionMessage(Object[] wonders) {
		ResourceBundle messages = Translate.getNewResourceBundle();
		return (String) JOptionPane.showInputDialog(null, messages.getString("chooseYourWonder"),
				messages.getString("wonderSelector"), JOptionPane.PLAIN_MESSAGE, null, wonders, wonders[0]);
	}

	public String dropDownPlayOrDiscardMessage() {
		ResourceBundle messages = Translate.getNewResourceBundle();
		Object[] objects = { messages.getString("Play"), messages.getString("Discard") };
		String str = (String) JOptionPane.showInputDialog(null, messages.getString("playOrDiscard"),
				messages.getString("7thCard"), JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
		if (str.equals(objects[0])) {
			return "Play";
		}
		return "Discard";
	}

	public String dropDownBuildMessage() {
		ResourceBundle messages = Translate.getNewResourceBundle();
		Object[] objects = { messages.getString("pay"), messages.getString("free") };
		String str = (String) JOptionPane.showInputDialog(null, messages.getString("payOrFree"),
				messages.getString("buildCost"), JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
		if (str.equals(objects[0])) {
			return "Pay";
		}
		return "Free";
	}

	public String dropDownResourceSelectionMessage() {
		ResourceBundle messages = Translate.getNewResourceBundle();
		Object[] objects = { messages.getString("LUMBER"), messages.getString("ORE"), messages.getString("CLAY"),
				messages.getString("STONE") };
		String str = (String) JOptionPane.showInputDialog(null, messages.getString("chooseAResource"),
				messages.getString("chooseAResource"), JOptionPane.PLAIN_MESSAGE, null, objects, objects[0]);
		if (str.equals(objects[0])) {
			return "LUMBER";
		} else if (str.equals(objects[1])) {
			return "ORE";
		} else if (str.equals(objects[2])) {
			return "CLAY";
		}
		return "STONE";
	}
}
