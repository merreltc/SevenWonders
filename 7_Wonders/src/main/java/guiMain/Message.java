package guiMain;

import java.awt.Graphics;

import GuiDataStructures.Constants;

public class Message {
	private String message = "";
	

	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void drawMessageOnScreen(Graphics graphics) {
		graphics.setFont(Constants.ErrorFont);
		graphics.setColor(Constants.TitleColor);
		graphics.drawString(this.message, Constants.ErrorMessagePosition.x, Constants.ErrorMessagePosition.y);
	}
	
	public void clearMessage() {
		this.message = "";
	}
}
