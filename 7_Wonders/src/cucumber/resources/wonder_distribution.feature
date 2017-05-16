Feature: Wonder Distribution
	In order to begin the game,
	As a player,
	I want a unique Wonder.
	
Scenario: Easy Mode, 3 Players
Given A new Game Manager set to Easy mode
And Number of player = 3
When The game is set up
Then Every player should have a unique wonder on side A