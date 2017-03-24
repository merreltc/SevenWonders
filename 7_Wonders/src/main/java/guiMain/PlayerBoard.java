package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class PlayerBoard {
	
	private static final int BoardWidth = 300;
	private static final int BoardHeight = 150;
	
	private Point position;
	//private Player player;
	
	/*TODO Pass in a player as well.  This will be the player that controls the board*/
	public PlayerBoard(Point position){
		this.position = position;
	}
	
	public void draw(Graphics graphics){
		graphics.setColor(Color.GREEN);
		graphics.fillRect(position.x, position.y, BoardWidth, BoardHeight);
		graphics.setFont(new Font("Courier New", Font.BOLD, 30));
		graphics.setColor(Color.RED);
		/*First render money*/
		graphics.drawString("1", position.x + BoardWidth - 30, position.y + 30);
		/*rendering resources owned*/
		graphics.drawString("1", position.x + 10, position.y + 25);
		graphics.drawString("1", position.x + 10, position.y + 65);
		graphics.drawString("1", position.x + 10, position.y + 105);
		graphics.drawString("1", position.x + 10, position.y + 145);
		/*Finally, the name of the wonder*/
		graphics.drawString("Wonder", position.x + 100, position.y + 30);
	}

}
