Feature: Player Builds Wonder
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

  Scenario Outline: valid resources
    Given A Wonder <wonder> on Side <side>
    And A player with that Wonder
    And <numLevels> expected Levels
    And The player has the resources for that Wonder
    When The player tries to build the wonder <numLevels> times
    Then The level is built
    And The player receives the effect of that level

    Examples: 
      | wonder     | side | numLevels |
      | Colossus   | A    |         1 |
      | Colossus   | A    |         2 |
      | Colossus   | A    |         3 |
      | Colossus   | B    |         1 |
      | Colossus   | B    |         2 |
      | Lighthouse | A    |         1 |
      | Lighthouse | A    |         2 |
      | Lighthouse | A    |         3 |
      | Lighthouse | B    |         1 |
      | Lighthouse | B    |         2 |
      | Lighthouse | B    |         3 |
      | Temple     | A    |         1 |
      | Temple     | A    |         2 |
      | Temple     | A    |         3 |
      | Temple     | B    |         1 |
      | Temple     | B    |         2 |
      | Temple     | B    |         3 |
      | Statue     | A    |         1 |
      | Statue     | A    |         3 |
      | Statue     | A    |         4 |
      | Statue     | B    |         1 |
      | Statue     | B    |         2 |
      | Statue     | B    |         3 |
      | Mausoleum  | A    |         1 |
      | Mausoleum  | A    |         2 |
      | Mausoleum  | A    |         3 |
      | Mausoleum  | B    |         1 |
      | Mausoleum  | B    |         2 |
      | Mausoleum  | B    |         3 |
      | Gardens    | A    |         1 |
      | Gardens    | A    |         2 |
      | Gardens    | A    |         3 |
      | Gardens    | B    |         1 |
      | Gardens    | B    |         2 |
      | Gardens    | B    |         3 |
      | Pyramids   | A    |         1 |
      | Pyramids   | A    |         2 |
      | Pyramids   | A    |         3 |
      | Pyramids   | B    |         1 |
      | Pyramids   | B    |         2 |
      | Pyramids   | B    |         3 |
      | Pyramids   | B    |         4 |

  Scenario Outline: invalid resources
    Given A Wonder <wonder> on Side <side>
    And A player with that Wonder
    And The player does not have the resources for that Wonder
    When The player tries to build the wonder <numLevels> times
    Then The level cannot be built
    And The player does not receive the effect of that level

    Examples: 
      | wonder     | side | numLevels |
      | Colossus   | A    |         1 |
      | Colossus   | A    |         2 |
      | Colossus   | A    |         3 |
      | Colossus   | B    |         1 |
      | Colossus   | B    |         2 |
      | Lighthouse | A    |         1 |
      | Lighthouse | A    |         2 |
      | Lighthouse | A    |         3 |
      | Lighthouse | B    |         1 |
      | Lighthouse | B    |         2 |
      | Lighthouse | B    |         3 |
      | Temple     | A    |         1 |
      | Temple     | A    |         2 |
      | Temple     | A    |         3 |
      | Temple     | B    |         1 |
      | Temple     | B    |         2 |
      | Temple     | B    |         3 |
      | Statue     | A    |         1 |
      | Statue     | A    |         3 |
      | Statue     | A    |         4 |
      | Statue     | B    |         1 |
      | Statue     | B    |         2 |
      | Statue     | B    |         3 |
      | Mausoleum  | A    |         1 |
      | Mausoleum  | A    |         2 |
      | Mausoleum  | A    |         3 |
      | Mausoleum  | B    |         1 |
      | Mausoleum  | B    |         2 |
      | Mausoleum  | B    |         3 |
      | Gardens    | A    |         1 |
      | Gardens    | A    |         2 |
      | Gardens    | A    |         3 |
      | Gardens    | B    |         1 |
      | Gardens    | B    |         2 |
      | Gardens    | B    |         3 |
      | Pyramids   | A    |         1 |
      | Pyramids   | A    |         2 |
      | Pyramids   | A    |         3 |
      | Pyramids   | B    |         1 |
      | Pyramids   | B    |         2 |
      | Pyramids   | B    |         3 |
      | Pyramids   | B    |         4 |
