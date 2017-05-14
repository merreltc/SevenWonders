# Seven Wonders - Java-based board game

## Description of Game
"You are the leader of one of the 7 great cities of the Ancient World. Gather resources, develop commercial routes, and affirm your military supremacy.
Build your city and erect an architectural wonder which will transcend future times.

7 Wonders lasts three ages. In each age, players receive seven cards from a particular deck, choose one of those cards, then pass the remainder to an adjacent player.
Players reveal their cards simultaneously, paying resources if needed or collecting resources or interacting with other players in various ways.
(Players have individual boards with special powers on which to organize their cards, and the boards are double-sided).
Each player then chooses another card from the deck they were passed, and the process repeats until players have six cards in play from that age.
After three ages, the game ends.

In essence, 7 Wonders is a card development game. Some cards have immediate effects, while others provide bonuses or upgrades later in the game.
Some cards provide discounts on future purchases. Some provide military strength to overpower your neighbors and others give nothing but victory points.
Each card is played immediately after being drafted, so you'll know which cards your neighbor is receiving and how his choices might affect what you've already built up.
Cards are passed left-right-left over the three ages, so you need to keep an eye on the neighbors in both directions."

## Contents of Game
- 7 Wonder board
- 49 Age I Cards
- 49 Age II Cards
- 50 Age III Cards
- 46 Conflict tokens
- 24 Value 3 Coins
- 46 Value 1 Coins

# How to Run in Eclipse:
## Importing and running the project:
1. Import the "7_Wonders" into Eclipse as a Gradle Project
2. Configure Build Path (see "Configuring the Build Path")
2. In the package explorer, double-click src/main/java
3. Double-click "GuiMain"
4. Right-click "GuiMainMenu" and selecte "Run As->Java Application"

## Configuring the Build Path:
1. In the package explorer, right-click on "7_Wonders" and select "Properties"
2. In the left pane, select "Build Path / Configure Build Path"
3. In the right pane, select the "Source" tab
4. Select "Add Folder"
5. Navigate to "src/org" and select the check box next to "org"
6. Hit ok, apply and then ok again

# Definition of "Done"
## Display
### Player Board
- [ ] Overview of player statistics (see "Player Stats")
- [ ] Detailed view of player statistics (see "Player Stats")
- [ ] Details for left/right neighbors
- [ ] Minimal details for non-neighboring players


## Set Up
- [ ] Game mode is chosen (Easy or Normal)
    - Easy Mode: all players receive side A
    - Normal Mode: players can receive side A or B of a Wonder
- [x] Number of players is chosen from valid options
- [x] Players enter their names
- [ ] Players are assigned random, unique wonders and sides
- [x] Age I Deck is assembled based on number of players and shuffled
- [x] Players are dealt equal number of cards
- [ ] Each player begins with 3 value 1 coins

## Decks
- [x] Each age has a unique set of cards
- [x] Deck swaps at the end of round 6 in an age (1->2->3)
- [ ] Age III Deck does not have raw material or manufactured goods cards
- [x] Age I and II decks do not have Guild cards

## Cards
### *Raw Resource*
### *Manufactured Good*
### *Science*
#### Rules
- [ ] End Game Victory Points are equal to number of identical symbols^2 + (7 * number of sets of 3 different symbols)

### *Commercial Structure*
### *Civilian Structure*
### *Military Structure*
### *Guild*
#### Rules
- [ ] Number of guild cards in the game is equal to the number of players + 2 and is kept a secret from players
- [ ] At the end of the game, players scores are affected by the Guild cards

## Commerce and Trading
- [x] In order to trade or build anything, player must have appropriate resources or coins in their stockpile at the beginning of the turn
- [x] Players can only trade with neighboring cities
- [ ] Trading a neighboring city is initially 2 coins per resource and only allows players to use that resource, not take it from their neighbors
- [ ] On a single turn, players may trade for multiple resources from both neighboring cities
- [ ] Traded resources are only available to the player during the turn they are bought
- [ ] Only resources produced through the city/Wonder or a neighbors raw resource or manufactured good cards can be traded
- [ ] Players are unable to purchase resources produced by commercial structures (or some Wonders)
- [ ] Trade cannot be refused by neighboring cities

## War
- [ ] At the end of an age (following sixth rotation), players go to war with BOTH neighboring cities
- [ ] A player with more shields than their neighbor is successful
- [ ] A player with less shields than their neighbor is defeated
- [ ] A player with equal shield as their neighbor is tied
- [ ] A successful conflict results in a number of conflict tokens (Age I: +1, Age II: +3, Age III: +5)
- [ ] A defeat results in a conflict token with the value -1
- [ ] A tie results in no tokens for either city

## Player Stats
- [x] Name
- [ ] Wonder and build level
- [x] Value 1 and value 3 coins
- [ ] Military Shields
- [ ] Conflict Tokens
- [ ] Victory Points
- [x] Entities (Raw Resources, Manufactured Goods, Science)

## Process of Turn
1. Choose card from temporary hand
2. Action
-  [x] Build the Structure
-  [ ] Build the Next Level of Wonder
-  [x] Discard for 3 Coins
    -  Discarded cards create a Discard Pile
3. Pass temporary hand to next player
- [x] Temporary hand is passed to next player (excluding chosen card)
- [x] Pass direction is determined by Age (I: left, II: right, III: left)
4. On the sixth turn, player has 2 cards: one is chosen, second is discarded for NO coins

## Building the Structure
- [x] If a structure has a resource cost, the player must have sufficient resources in their stockpile or purchase them from neighbors (see "Commerce and Trading")
- [x] If a structure has a coin cost, the player must pay that cost to the bank
- [ ] If player owns a previous structure, they may build their chosen structure for free
- [ ] Identical structures cannot be built by the same player
- [x] At the end of a rotation, all player's built structures are revealed

## Building the Next Level of Wonder
- [ ] Player must have the resources required for the next level of the Wonder (not the structure) in order to build
- [ ] Card used to build Wonder does not provide player its effect in addition to the effect of the Wonder Level and is hidden from other players
- [ ] Levels of Wonder are built once per game and in sequence (left to right)
- [ ] Wonder can be built at any time, in any age
- [ ] The wonder does not need to be built in order for a player to win

### Wonders
- [ ] *The Colossus of Rhodes*
- [ ] *The Lighthouse of Alexandria*
- [ ] *The Hanging Gardens of Babylon*
- [ ] *The Pyramids of Giza*
- [ ] *The Mausoleum of Halicarnassus*
- [ ] *The Statue of Zeus in Olympia*
- [ ] *The Temple of Artemis in Ephesus*

## End Game and Victory
- [ ] After the war of Age III, a score card displaying total Victory Points for each player is displayed
- [ ] Total Victory Points is equal to the sum of total value of Conflict Tokens, the floor of total coin value divided by 3, the total value of Victory Points from Wonders and (Civilian/Commercial) Structures, points acquired from Guild effects (see "Cards->Guild"), and points acquired from Science Cards (see "Cards->Science Structure")
- [ ] Whichever player has the most victory points is the winner
- [ ] In the case of a tie, the player with the most coins wins. Ties at this point are not broken further

## Optional features:
* Leaders

# Code Inspection Standards
## Logistics
**When will inspections be warranted?**  
* At the middle and end of a feature  
* Before a merge  

**Who will moderate meetings?**  
* In general, we will collaborate during inspections  
* In case of emergency, Carrie Utter will moderate inspections  

**How will we share inspection results?**  
* Participate together and communicate over a group meeting  
* Merge the cleaned code onto master (cleaned after feedback)  

## Criteria
**Key Code Smells from "Clean Code":**  
* Names:  
	> Descriptive, albeit long names  
* Functions:  
	> Short (extract functions where necessary)  
	> With as few arguments as possible  
	> Descriptive, verb names  
* Comments:  
	> Explain a feature succinctly  
	> Not overpower the code (only use when necessary)  
	> No commented code  
* Formatting:  
	> Camel case functions and variable names  
	> Ctrl-Shift-F before committing  
	> One blank line between methods  
	> Decreasing order of function abstraction levels  
	> Getters/Setters at the bottom  
* Demeter:  
	> Avoid trainwrecks by extracting methods in calling code  
* Error Handling:  
	> Use try-catch/RuntimeException for user-created exceptions  
	> Descriptive messages  
	> Don't pass/return null  
	> Use Exceptions rather than error codes  
* Unit Testing:  
	> Use BVA to test all boundaries  
	> Single responsibility/concept per test  
* Classes:  
	> Small  
	> Single Responsibility  
	> Object classes should be "Open for extension, Closed for modification"  
	> Organized for change  
* TDD:  
	> Follow the following steps:  
		1. Failing test  
		2. Write minimal code to pass  
		3. Refactor after major responsibility is fully tested

**Will everyone apply all criteria?**  
* Use of pair programming allows for good oversight by partner  
* Whoever is not coding is primarily responsible for ensuring code cleanliness  

## Scope
**What is the scope of inspection (every file in codebase, every file touched on feature branch, or something else)?**  
* Code reviews go through only classes that a feature touches
* During programming, we will review the classes that we are working on. This includes any classes that the programmer did not write, but that they recognize require change that would improve the quality of the code.

**Will each person look at the files in consideration or will files be delegated to team members?**  
* Code reviews will be conducted by the full team  
* Programming reviews at the end of a session will be conducted in pairs (gui and backend)  

## Tools
**Automated tools usage:**  
* At the end of a coding session and before a merge, the following should be run:  
    1. Checkstyle  
    2. Findbugs  
    3. Metrics  
    4. EclEmma (Code Coverage)  
    5. PIT (mutation)  
* Use of these tools will ensure that code meets the majority of the above criteria without the need of human inspection

**When will human intervention be necessary?**  
* Relevancy/Helpfulness of comments  
* Descriptive function/variable names  
* Organization of functions within a class (logical flow of abstraction)  
* Integrity in regards to TDD standards