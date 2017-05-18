package utils;

import java.util.ResourceBundle;

import constants.Constants;

public class Translate {

	public static ResourceBundle getNewResourceBundle() {
		return ResourceBundle.getBundle("message_" + Constants.LOCALE);
	}

	public static String prepareNoSpaceString(String str) {
		return str.replaceAll(" ", "");
	}

	public static String prepareStringWithNoArgs(String key, ResourceBundle messages) {
		return messages.getString(key);
	}
}
