Feature: Player and Wonder
  In order to win the game,
  As a player,
  I want to get resources from my Wonder.

  Scenario Outline: player recieves resources
    Given A Wonder <wonder> on Side <side>
    And A player with that Wonder
    When Beginning the game
    Then The player begins with the appropriate resource
    And The player does not begin with any other resources

    Examples: 
      | wonder     | side |
      | Colossus   | A    |
      | Colossus   | B    |
      | Lighthouse | A    |
      | Lighthouse | B    |
      | Temple     | A    |
      | Temple     | B    |
      | Statue     | A    |
      | Statue     | B    |
      | Mausoleum  | A    |
      | Mausoleum  | B    |
      | Gardens    | A    |
      | Gardens    | B    |
      | Pyramids   | A    |
      | Pyramids   | B    |