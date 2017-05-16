package guiDataStructures;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

public class Constants {
	public static String LOCALE = "en_US";
	
	/* For GuiMainMenu Class */
	public static final int FrameWidth = 1900;
	public static final int FrameHeight = 1000;
	
	public static final int FrameWidthModifier = 70;

	public static final Point StartButtonPosition = new Point(850, 400);
	public static final Point StartButtonBounds = new Point(200, 100);

	public static final Font TitleFont = new Font("DengXian Regular", Font.BOLD, 60);
	public static final Color TitleColor = Color.yellow;
	
	public static final Font ButtonFont = new Font("DengXian Regular", Font.BOLD, 50);
	public static final Font ResourceFont = new Font("DengXian Regular", Font.BOLD, 30);

	public static final Point MainMenuTitlePosition = new Point(740, 100);

	public static final Point PlayerSelectButtonBounds = new Point(150, 100);
	public static final Point PlayerSelectTitlePosition = new Point(500, 280);

	public static final Font ErrorFont = new Font("DengXian Regular", Font.BOLD, 35);
	public static final Point ErrorMessagePosition = new Point(550, 400);

	public static final Point ExitButtonBounds = new Point(200, 80);
	public static final Point ExitButtonPosition = new Point(FrameWidth - ExitButtonBounds.x - 50,
			FrameHeight - ExitButtonBounds.y - 50);
	
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
	public static final int PlayerNameYOffset = 50;
	
	public static final int BackPlayerBoardXOffset = BackPlayerBoardWidth + 40;
	public static final int BackPlayerBoardPositionY = 10;

	public static final Point TradeButtonBounds = new Point(20, 20);

	public static final Point TradeLeftBaseButtonPoint = new Point(LastPlayerBoardPositionX - 5, LastPlayerBoardPositionY + 5);
	public static final Point TradeRightBaseButtonPoint = new Point(NextPlayerBoardPositionX - 5, NextPlayerBoardPositionY + 5);

	public static final int TradeButtonYOffet = TradeButtonBounds.y + 20;
	
	public static final int ResourcerViewerButtonYOffset = 50;
	
	public static final Point ResourceViewerButtonBounds = new Point(150,50);

	public static final int CardHeight = 258;
	public static final int CardWidth = 150;
	
	public static final int CardOffset = CardWidth + 10;
	
	public static final int PlayerHandLeftMostCardPositionX = CurrentPlayerBoardPositionX +10;
	public static final int PlayerHandLeftMostCardPositionY = CurrentPlayerBoardPositionY + CurrentPlayerBoardHeight - CardHeight;

	/* For Game Class */
	public static final String[] ResourceTypes = new String[] { "Lumber", "Ore", "Stone", "Clay" };
	
	/* For ResourceViewer Class */
	public static final String[] ResourceImages = new String[] { "clay", "lumber", "ore", "stone", "glass", "loom symbol", "papyrus", "manufactured", "resource", "science"};
	public static final int RESOURCE_IMAGE_HEIGHT = 30;
	public static final int RESOURCE_IMAGE_WIDTH = 30;
	public static final Font RESOURCE_VIEWER_FONT = new Font("DengXian Regular", Font.BOLD, 30);
	public static final int NUM_OF_COLUMNS = 10;
	public static final int RESOURCE_VIEWER_ROW_HEIGHT = 40;
	public static final int RESOURCE_VIEWER_ROW_X = 50;
	public static final int RESOURCE_VIEWER_ROW_BASE_Y = 100;
	public static final int RESOURCE_VIEWER_TEXT_X_OFFSET = 50;
	public static final int RESOURCE_VIEWER_TEXT_Y_OFFSET = 30;
	public static final int RESOURCE_VIEWER_ROW_WIDTH = FrameWidth - 100;
	public static final int RESOURCE_VIEWER_FIRST_CELL_WIDTH = 250;
	public static final int RESOURCE_VIEWER_CELL_WIDTH = (RESOURCE_VIEWER_ROW_WIDTH - RESOURCE_VIEWER_FIRST_CELL_WIDTH) / 13;
	
	public static final int RESOURCE_VIEWER_ONE_COIN_X = FrameWidth - 350;
	public static final int RESOURCE_VIEWER_ONE_COIN_Y = 100;
	
	public static final int RESOURCE_VIEWER_SHIELD_X = FrameWidth - 200;
	public static final int RESOURCE_VIEWER_SHIELD_Y = 100;
	
	public static final int RESOURCE_VIEWER_WAR_TOKEN_X = RESOURCE_VIEWER_SHIELD_X;
	public static final int RESOURCE_VIEWER_WAR_TOKEN_Y = RESOURCE_VIEWER_SHIELD_Y + 3 * RESOURCE_VIEWER_ROW_HEIGHT;
	
	public static final int RESOURCE_VIEWER_THREE_COIN_X = RESOURCE_VIEWER_ONE_COIN_X;
	public static final int RESOURCE_VIEWER_THREE_COIN_Y = RESOURCE_VIEWER_ONE_COIN_Y + 3 * RESOURCE_VIEWER_ROW_HEIGHT;
	
	


}
