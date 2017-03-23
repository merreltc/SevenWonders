package tests.gui;

import org.junit.Test;
import guiMain.Button;

public class ButtonTests {

	@Test
	public void testButtonCreation(){
		Button button = new Button(100, 100);
		button.getBounds();
	}
}
