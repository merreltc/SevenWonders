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
- [ ] **Display**
    - [ ] *Player Board*
        - [ ] Overview of player statistics
        - [ ] Detailed view of player statistics
        - [ ] Details for left/right neighbors
        - [ ] Minimal details for non-neighboring players
- [ ] **Set Up Players**
    - [x] Valid number of players (between 3 and 7) #d832da21
    - [x] Invalid number of players (-1, < 3, > 7) #895549c7
    - [ ] Assign players to wonders randomly (simulate wonder cards)
        - [ ] Easy Mode: all players receive side A
        - [ ] Normal Mode: players can receive side A or B of a Wonder
- [ ] **Set Up Deck**
    - [x] Number of cards in a deck is determined by the number of players
        - [x] 3 players
        - [ ] 7 players
    - [x] Players are dealt equal number of cards
        - [ ] 3 players
        - [ ] 7 players
    - [ ] *Deck Type*
        - [x] Each age has a different deck with unique set of cards
        - [ ] Age III seck does not have raw material or manufactured goods cards
        - [x] Make sure decks swap at the end of an age (1->2->3)
        - [ ] Age I and II decks do not have Guild cards
        - [x] At the beginning of the game, the deck if shuffled
            - [x] 3 players
            - [ ] 7 players
- [ ] **Players**
    - [ ] Name
    - [ ] Wonder (see Wonders)
    - [ ] Coin stockpile
    - [ ] Conflict token stockpile
    - [ ] Card stockpile
    - [ ] Temporary hand
- [ ] **Player Turn*
    -  [ ] *Action*
        -  [ ] Build Structure
        -  [ ] Build Level of Wonder
        -  [X] Discard for 3 Coins
    -  [x] *Pass Cards*
        - [x] Temporary hand is passed to next player (excluding chosen card)
        - [x] Pass direction is determined by Age (1: left, 2: right, 3: left)
        - [x] On the sixth turn, player has 2 cards - one is chosen, second is discarded for NO coins
- [ ] **Building Structures**
    - [x] Fails when player doe not have enough materials
    - [x] Succeeds when player has exactly enough materials
    - [ ] Succeeeds when player has more than enough materials
    - [x] Succeeds when player has card that can be built upon
    - [x] Only one card can be chosen per turn
    - [ ] Players reveal played card at the end of an rotation
- [ ] **Cards**
    - [ ] *Raw Resource*
    - [ ] *Manufactured Good*
    - [ ] *Science*
        - [ ] End Game Victory Points is equal to
            - [ ] number of identical symbols^2 + (7 * number of sets of 3 different symbols)
    - [ ] *Commercial Structure*
    - [ ] *Civilian Structure*
    - [ ] *Military Structure*
    - [ ] *Guild*
        - [ ] Rules:
            - [ ] Number of guild cards = nuber of players + 2 (kept a secret from players)
            - [ ] Effect if player has no Guilds
            - [ ] Effect if player has all Guilds
- [x] **Commerce**
    - [x] Attempting to spend more than amonut in stockpile should fail
    - [x] Attempting to spend less than amount in stockpile should succeed
    - [x] Attempting to spend all money in stockpile should succeed
    - [ ] *Trading*
        - [x] Trading with a city with the incorrect materials.
        - [x] Trading with a city with just enough materials.
        - [ ] Trading with a city with more than enough materials.
        - [x] Trading with player next to you.
        - [x] Trading with player not next to you.
- [ ] **War**
    - [ ] At the end of an age, neighboring cities have war
    - [ ] You have less strength than your neighbor
    - [ ] You have equal strength than your neighbor
    - [ ] You have more strength than your neighbor
- [ ] **Wonder Boards**
    - [x] Every player must have unique wonderboard.
    - [ ] Player can choose side A or B
    - [ ] Wonder board has correct number of levels
    - [ ] Must build Wonder Powers in sequence
    - [ ] Cannot build same level multiple times
    - [ ] Building level for appropriate cost
    - [ ] Building level gives player appropriate effect
    - [ ] *Wonders*
        - [ ] The Colossus of Rhodes
        - [ ] The Lighthouse of Alexandria
        - [ ] The Hanging Gardens of Babylon
        - [ ] The Pyramids of Giza
        - [ ] The Mausoleum of Halicarnassus
        - [ ] The Statue of Zeus in Olympia
        - [ ] The Temple of Artemis in Ephesus
- [ ] **End Game and Victory:**
    - [ ] Score totaled after Age III war
    - [ ] Total Victory Points = Value of Conflict Tokens + Floor(Coin Total / 3)  + # of Victory Points from Wonders and (Civilian/Commercial) Structures + Affect of Guild Abilities + Science Cards (see "Cards->Science Structure") + Victory Points According to Guild Cards (see "Cards->Guild")
    - [ ] Player with most Victory Points wins
    - [ ] If there is a tie, player with the most coins wins (a further tie is not broken)
    - [ ] Display score card at end of game

### Optional features:
* Leaders

# Code Inspection Standards
## **Logistics:**
	1. When will inspections be warranted:  
		* At the middle and end of a feature  
		* Before a merge  
	2. Who will moderate meetings:  
		* In general, we will collaborate during inspections  
		* In case of emergency, Carrie Utter will moderate inspections  
	3. How will we share inspection results:  
		* Participate together and communicate over a group meeting  
		* Merge the cleaned code onto master (cleaned after feedback)  

## **Criteria:**  
	4. Key Code Smells from "Clean Code":  
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
	5. Will everyone apply all criteria:
		* Use of pair programming allows for good oversight by partner  
		* Whoever is not coding is primarily responsible for ensuring code cleanliness  

## **Scope:**  
	6. What is the scope of inspection (every file in codebase, every file touched on feature branch, or something else):  
	    * Code reviews go through only classes that a feature touches  
	    * During programming, we will review the classes that we are working on  
	7. Will each person look at the files in consideration or will files be delegated to team members?  
		* Code reviews will be conducted by the full team  
		* Programming reviews at the end of a session will be conducted in pairs (gui and backend)  

## **Tools:**  
	8. Automated tools:  
		* At the end of a coding session and before a merge, the following should be run:  
		    1. Checkstyle  
		    2. Findbugs  
		    3. Metrics  
		    4. EclEmma (Code Coverage)  
		    5. PIT (mutation)  
		* Use of these tools will ensure that code meets the majority of the above criteria without the need of human inspection  
	9. When will human intervention be necessary:  
		* Relevancy/Helpfulness of comments  
		* Descriptive function/variable names  
		* Organization of functions within a class (logical flow of abstraction)  
		* Integrity in regards to TDD standards  