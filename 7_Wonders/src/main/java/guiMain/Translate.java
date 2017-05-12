package guiMain;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Translate {
	
	public static ResourceBundle getNewResourceBundle(){
		return ResourceBundle.getBundle("message", Locale.getDefault());
	}
	
	public static String prepareNoSpaceString(String str){
		return str.replaceAll(" ", "");
	}
	
	public static String prepareStringTemplateWithIntArg(int val, String template, ResourceBundle messages){
		Object[] messageArgs = {new Integer(val)};
		MessageFormat format = new MessageFormat("");
		format.setLocale(Locale.getDefault());
		format.applyPattern(messages.getString(template));
		return format.format(messageArgs);
	}
	
	public static String prepareStringTemplateWithStringArg(String arg, String template, ResourceBundle messages){
		Object[] messageArgs = {arg};
		MessageFormat format = new MessageFormat("");
		format.setLocale(Locale.getDefault());
		format.applyPattern(messages.getString(template));
		return format.format(messageArgs);
	}
}
