# Seven Wonders - Java-based board game

## Requirements Left to Cover (Delete as completed)
Key: **\*** - Do not delete. Run/Verify immediately before deadline  
**Current Grade: D**  
**For a C**  
- 100% mutation and basis path coverage of all non-GUI, non-enum code\*
- All tests pass and capture their stated intent (don’t delete failing tests)\*
- Locale: **Select** and Support one non-English locale
    - Full support for non-English locale

**For a B**  
- Code meets quality standards\*
- Definition of done in involves appropriate course techniques\*
- Rules are explicitly linked to done-ness (Issues, Merge Requests, test cases, etc)

**For an A**  
- Code meets Martin’s quality standards\*
- Appropriate automation to verify code meets quality standards\*

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
- [x] Overview of player statistics (see "Player Stats")
- [x] Detailed view of player statistics (see "Player Stats")
- [x] Details for left/right neighbors
- [x] Minimal details for non-neighboring players

## Set Up
- [ ] Language is chosen: English or Chinese
- [ ] Game mode is chosen (Easy or Normal)
    - Easy Mode: all players receive side A
    - Normal Mode: players can receive side A or B of a Wonder
- [x] Number of players is chosen from valid options
- [x] Players enter their names
- [ ] Players are assigned random, unique wonders and sides
- [x] Age I Deck is assembled based on number of players and shuffled
- [x] Players are dealt equal number of cards
- [x] Each player begins with 3 value 1 coins

## Decks
- [x] Each age has a unique set of cards
- [x] Deck swaps at the end of round 6 in an age (1->2->3)
- [ ] Age III Deck does not have raw material or manufactured goods cards
- [x] Age I and II decks do not have Guild cards

## Cards
- [x] Name and type of structure/resource (See "Card Types")
- [x] Cost of building the structure
- [ ] The effect caused by building the structure
- [x] Previous structures that allow this structure to be built for free
- [x] Future structures that can be built free is this card has already been built

### Card Types
- Raw Resource: (Brown) Produce raw materials (lumber, stone, ore, clay)
- Manufacture Good: (Grey) Produce displayed products (loom, glass, press)
- Science: (Green) Award victory points at end game based on collected symbols (wheel, protractor, tablet)
    - Rules:
        - [ ] End game victory points are equal to number of identical symbols^2 + (7 * number of sets of 3 different symbols)

- Civilian Structure: (Blue) Provide victory points
- Commercial Structure: (Yellow) Provide advantages during commercial transactions (See "Commerce and Trading")
- Military Structure: (Red) Builds military strength for wars (See "War")
- Guild: (Purple) Provide end game victory points based on certain criteria
    - Rules:
        - [ ] Number of guild cards in the game is equal to the number of players + 2 and is kept a secret from players
        - [ ] At the end of the game, players scores are affected by the Guild cards

## Commerce and Trading
- [x] In order to trade or build anything, player must have appropriate resources or coins in their stockpile at the beginning of the turn
- [x] Players can only trade with neighboring cities
- [ ] Trading a neighboring city is initially 2 coins per resource and only allows players to use that resource, not take it from their neighbors
    - [ ] Some Commercial Structure (yellow) cards allow players to trade for certain materials for 1 coin after being built
- [x] On a single turn, players may trade for multiple resources from both neighboring cities
- [ ] Traded resources are only available to the player during the turn they are bought
- [ ] Only resources produced through the city/Wonder or a neighbors raw resource or manufactured good cards can be traded
- [ ] Players are unable to purchase resources produced by commercial structures (or some Wonders)
- [x] Trade cannot be refused by neighboring cities

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
- [x] Military Shields
- [x] Conflict Tokens
- [ ] Victory Points
- [x] Entities (Raw Resources, Manufactured Goods, Science)

## Process of Turn
1. Choose card from temporary hand
2. Action
    -  [x] Build the Structure
    -  [ ] Build the Next Level of Wonder
    -  [x] Discard for 3 Coins
        -  Discarded cards create a Discard Pile
        -  A card can always be chosen for discard, even if the player does not have enough resources to build it
3. Pass temporary hand to next player
    - [x] Temporary hand is passed to next player (excluding chosen card)
    - [x] Pass direction is determined by Age (I: left, II: right, III: left)
4. On the sixth turn, player has 2 cards: one is chosen, second is discarded for NO coins

## Building the Structure
- [x] If a structure has a resource cost, the player must have sufficient resources in their stockpile or purchase them from neighbors (see "Commerce and Trading")
- [x] If a structure has a coin cost, the player must pay that cost to the bank
- [x] If player owns a previous structure, they may build their chosen structure for free
- [ ] Identical structures cannot be built by the same player
- [x] At the end of a rotation, all player's built structures are revealed

## Building the Next Level of Wonder
- [ ] Player must have the resources required for the next level of the Wonder (not the structure) in order to build
- [ ] Card used to build Wonder does not provide player its effect in addition to the effect of the Wonder Level and is hidden from other players
- [ ] Levels of Wonder are built once per game and in sequence (left to right)
- [ ] Wonder can be built at any time, in any age
- [ ] The wonder does not need to be built in order for a player to win

### Wonders
- Wonders provide players 1 of the resource specified in the top left corner
- Side A always has 3 levels
    - The first level is worth +3 Victory Points
    - The second level is determined by the Wonder
    - The third level is worth +7 Victory Points
- Number of levels and the effects for each level vary by Wonder on side B
- The cost of building each level varies from side A to B and from Wonder to Wonder
- *The Colossus of Rhodes*
    - Resource: Ore
    - Side A, Level 2: 2 Shields
    - Side B:
        - Level 1: 1 Shield, 3 Victory Points, 3 Coins (from bank)
        - Level 2: 1 Shield, 4 Victory Points, 4 Coins (from bank)
- *The Lighthouse of Alexandria*
    - Resource: Glass
    - Side A, Level 2: Resource of choice from 4 raw materials (lumber, stone, ore, clay) **every turn**
    - Side B:
        - Level 1: (See "Side A, Level 2")
        - Level 2: Resource of choice from 3 manufactured goods (loom, glass, press) **every turn**
        - Level 3: 7 Victory Points
- *The Temple of Artemis in Ephesus*
    - Resource: Press
    - Side A, Level 2: 9 Coins (from bank) **immediately following the building of this level**
    - Side B:
        - Level 1: 2 Victory Points, 4 Coins (from bank)
        - Level 2: 3 Victory Points, 4 Coins (from bank)
        - Level 3: 5 Victory Points, 4 Coins (from bank)
- *The Hanging Gardens of Babylon*
    - Resource: Clay
    - Side A, Level 2: Science symbol of choice from 2 types (wheel, protractor, tablet) at the **end of the game**
    - Side B:
        - Level 1: 3 Victory Points
        - Level 2: Optional ability to play seventh card instead of discarding **on sixth turn**
            - This card can be built (See "Building the Structure"), used to build level 3 (See "Building the Next Level of Wonder"), or discarded for 3 coins from the bank
        - Level 3: (See "Side A, Level 2")
- *The Statue of Zeus in Olympia*
    - Resource: Lumber
    - Side A, Level 2: Build structure for free **once per age**
    - Side B:
        - Level 1: Allows player to purchase raw materials (lumber, stone, ore, clay) from either neighbor for 1 coin
            - Same effect as both Eastern and Western Trading Posts (Commercial Structures)
            - Both trading posts can still be built, but the effect is not cumulative
        - Level 2: 5 Victory Points
        - Level 3: Allows player to "copy" a Guild (purple) card from **one** of their two neighbors at the **end of the game**
            - This ability has no effect on the player who owns the copied Guild
- *The Mausoleum of Halicarnassus*
    - Resource: Loom
    - Side A, Level 2: At the **end of turn when this level is built,** choose one structure from all discarded cards since beginning of game and build for free
    - Side B:
        - Level 1: 2 Victory Points, (See "Side A, Level 2")
        - Level 2: 1 Victory Point, (See "Side A, Level 2")
        - Level 3: (See "Side A, Level 2")
- *The Pyramids of Giza*
    - Resource: Stone
    - Side A, Level 2: 5 Victory Points
    - Side B:
        - Level 1: 3 Victory Points
        - Level 2: 5 Victory Points
        - Level 3: 5 Victory Points
        - Level 4: 7 Victory Points

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