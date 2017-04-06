package guiMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public class Constants {
	/* For GuiMainMenu Class */
	public static final int FrameWidth = 1900;
	public static final int FrameHeight = 1000;

	public static final Point StartButtonPosition = new Point(850, 400);
	public static final Point StartButtonBounds = new Point(200, 100);

	public static final Font TitleFont = new Font("Courier New", Font.BOLD, 70);
	public static final Color TitleColor = Color.cyan;

	public static final Point MainMenuTitlePosition = new Point(740, 100);

	public static final Point PlayerSelectButtonBounds = new Point(150, 100);
	public static final Point PlayerSelectTitlePosition = new Point(570, 300);

	public static final Font ErrorFont = new Font("Courier New", Font.BOLD, 35);
	public static final Point ErrorMessagePosition = new Point(550, 400);

	public static final Point CloseMessageButtonBounds = new Point(200, 80);
	public static final Point CloseMessageButtonPosition = new Point(FrameWidth - CloseMessageButtonBounds.x - 50,
			FrameHeight - CloseMessageButtonBounds.y - 50);
	
	/* For PlayerBoard Class */
	public static final int CurrentPlayerBoardWidth = 1200;
	public static final int CurrentPlayerBoardHeight = 521;
	public static final int CurrentPlayerBoardPositionX = 950 - CurrentPlayerBoardWidth / 2;
	public static final int CurrentPlayerBoardPositionY = 950 - CurrentPlayerBoardHeight - 10;

	public static final int LastPlayerBoardWidth = 530;
	public static final int LastPlayerBoardHeight = 230;
	public static final int LastPlayerBoardPositionX = 1900 - LastPlayerBoardWidth - 20;
	public static final int LastPlayerBoardPositionY = 500 - LastPlayerBoardHeight - 10;

	public static final int NextPlayerBoardWidth = 530;
	public static final int NextPlayerBoardHeight = 230;
	public static final int NextPlayerBoardPositionX = 20;
	public static final int NextPlayerBoardPositionY = 500 - NextPlayerBoardHeight - 10;

	public static final int BackPlayerBoardWidth = 345;
	public static final int BackPlayerBoardHeight = 150;
	// public static final int BackPlayerStartingBoardPositionX = 1900 -
	// BackPlayerBoardWidth - 20;
	public static final int BackPlayerBoardXOffset = BackPlayerBoardWidth + 40;
	public static final int BackPlayerBoardPositionY = 10;

	public static final Point TradeButtonBounds = new Point(20, 20);

	public static final Point TradeLeftBaseButtonPoint = new Point(LastPlayerBoardPositionX + 10, LastPlayerBoardPositionY + 10);
	public static final Point TradeRightBaseButtonPoint = new Point(NextPlayerBoardPositionX + 10, NextPlayerBoardPositionY + 10);

	public static final int TradeButtonYOffet = TradeButtonBounds.y + 20;

	public static final int CardHeight = 238;
	public static final int CardWidth = 150;
	
	public static final int CardOffset = CardWidth + 10;
	
	public static final int PlayerHandLeftMostCardPositionX = CurrentPlayerBoardPositionX +10;
	public static final int PlayerHandLeftMostCardPositionY = CurrentPlayerBoardPositionY + CurrentPlayerBoardHeight - CardHeight/2 - 30;
}
