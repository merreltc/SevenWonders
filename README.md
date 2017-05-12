Description of Game
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
Cards are passed left-right-left over the three ages, so you need to keep an eye on the neighbors in both directions.

Though the box of earlier editions is listed as being for 3–7 players, there is an official 2-player variant included in the instructions."

Instructions to Run:

Configuring the Build Path:
1. right click on 7_Wonders
2. select Build Path / Configure Build Path
3. Select Source in the tabs
4. Select Add Folder
5. Navigate to src/org and select the check box next to org
6. Hit ok, apply and then ok

Running the project:
1. Open the 7_Wonders Package
2. Open src/main/java
3. Open GuiMain
4. Select and run the GuiMainMenu class

Contents
- 1 Wonder board - 1 Courtesan token
- 1 Wonder card - 1 rulebook
- 36 Leader cards - 1 score booklet
- 4 Guild cards - 1 blank Leader card
- 17 value 6 coins

- [x] Test Amount of Players:
    - [x] Can play with 3 players.
    - [x] Can play with 7 players.
    - [x] Can't play with null, 0, invalid input, 8 players.

- [x] Test Card Passing:
    - [x] Have to pass all cards but one to next player.
    - [x] Can only pass in the determined direction.
    - [x] That direction changes (left/right/left).
    - [x] When a player is given 2 cards, they choose one and discard the other.

- [x] Player Board Space:
    - [x] Must be able to see left and right players board/statistic.

- [ ] Trades:
    - [x] Trading with a city with the incorrect materials.
    - [x] trading with a city with just enough materials.
    - [ ] trading with a city with more than enough materials.
    - [x] trading with player next to you.
    - [x] trading with player not next to you.

- [x] Money:
    - [x] Spending money you don't have.
    - [x] Trying to spend less than what you have.
    - [x] Trying to spend all your money.
    - [x] Player can discard a card for 3 coins.

- [ ] Military strength:
    - [ ] You have less strength than your neighbor.
    - [ ] You have equal strength than your neighbor.
    - [ ] you have more strength than your neighbor.

- [ ] Deck Size:
    - [x] Number of cards in a deck is determined by the number of players: 
        - [x] test w/ 3
        - [ ] test w/ 7 players.
    - [x] Deck deals equal number of cards to every player: 
        - [ ] test w/ 3
        - [ ] test w/7 players.

- [ ] Deck types:
    - [x] Each age has a different deck with unique set of cards.
    - [ ] Make sure age 3 has no material cards.
    - [x] Make sure decks swap for 1->2->3.
    - [ ] No Guild cards are in ages 1 or 2.
    - [x] Deck can shuffle at the beginning of the game.  
        - [x] 3 players
        - [ ] 7 players.

- [ ] Wonder Boards:
    - [x] Everyone must have unique wonderboard.
    - [x] No two players can have the same wonder board.
    - [ ] test Wonder Board has 0, 3, 4 Wonder Powers.
    - [ ] Must build Wonder Powers in sequence.
    - [ ] Must get the correct powers from your wonder powers.  test: 0, 3 Wonder Powers.
    - [ ] Player can choose a side. test: player chooses side 1, side 2, any invalid side.

- [ ] Card Activation:
    - [x] test player has not enough materials.
    - [x] test player has just enough materials.
    - [ ] test player has more than enough materials.
    - [x] test player has card that can be built upon.
    - [x] test player can only choose one card per turn.
    - [ ] test all players reveal played card at the same time.

- [ ] Guild Cards:
    - [ ] make sure the number of guild cards = nuber of players + 2  (secret).
    - [ ] test player has no Guild.
    - [ ] test player has all Guild.

- [ ] End Game:
    - [ ] Victory Points = sum of conflict tokens + Guild abilities + Victory points + money/3 + Science Cards.
    - [ ] Player with most Victory Points wins.
    - [ ] if there is a tie, player with the most coins can tie (if another tie, gladiator fight).
    - [ ] display score card at win.

Optional features:
Leaders.

