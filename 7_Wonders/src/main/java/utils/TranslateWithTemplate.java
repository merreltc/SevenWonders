package utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import constants.Constants;

public class TranslateWithTemplate {

	public static String prepareStringTemplateWithIntArg(int val, String template, ResourceBundle messages) {
		String[] region_lang = Constants.LOCALE.split("_");
		Locale gameLocale = new Locale(region_lang[0], region_lang[1]);
		Object[] messageArgs = { new Integer(val) };
		MessageFormat format = new MessageFormat("");
		format.setLocale(gameLocale);
		format.applyPattern(messages.getString(template));
		return format.format(messageArgs);
	}

	public static String prepareStringTemplateWithStringArg(String arg, String template, ResourceBundle messages) {
		String[] region_lang = Constants.LOCALE.split("_");
		Locale gameLocale = new Locale(region_lang[0], region_lang[1]);
		Object[] messageArgs = { arg };
		MessageFormat format = new MessageFormat("");
		format.setLocale(gameLocale);
		format.applyPattern(messages.getString(template));
		System.out.println(format.format(messageArgs));
		return format.format(messageArgs);
	}

	public static String prepareStringTemplateWithIntAndStringArg(int arg1, String arg2, String template,
			ResourceBundle messages) {
		String[] region_lang = Constants.LOCALE.split("_");
		Locale gameLocale = new Locale(region_lang[0], region_lang[1]);
		Object[] messageArgs = { new Integer(arg1), arg2 };
		MessageFormat format = new MessageFormat("");
		format.setLocale(gameLocale);
		format.applyPattern(messages.getString(template));
		return format.format(messageArgs);
	}

}
