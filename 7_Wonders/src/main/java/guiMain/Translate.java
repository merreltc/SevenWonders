package guiMain;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Translate {
	
	public static ResourceBundle getNewResourceBundle(){
		return ResourceBundle.getBundle("message", Locale.getDefault());
	}
	
	public static String prepareString(String str){
		return str.replaceAll(" ", "");
	}
	
	public static MessageFormat prepareArgFormat(int val, String template, ResourceBundle messages){
		Object[] messageArgs = {new Integer(val)};
		MessageFormat format = new MessageFormat("");
		format.setLocale(Locale.getDefault());
		format.applyPattern(messages.getString(template));
		return format;
	}

}
