Feature: Build Wonder
  In order to win the game,
  As a player,
  I want to build my Wonder.

  Scenario Outline: valid number build Wonder
    Given A Wonder <wonder> on Side <side>
    And <numLevels> expected Levels
    When Building the wonder <numLevels> times
    Then The number of levels built equals <numLevels>

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
      | Statue     | A    |         2 |
      | Statue     | A    |         3 |
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

  Scenario Outline: invalid number build wonder
    Given A Wonder <wonder> on Side <side>
    When Building the wonder <numLevels> times
    Then The level cannot be built

    Examples: 
      | wonder     | side | numLevels |
      | Colossus   | A    |         4 |
      | Colossus   | B    |         3 |
      | Colossus   | A    |         5 |
      | Colossus   | B    |         4 |
      | Lighthouse | A    |         4 |
      | Lighthouse | B    |         4 |
      | Lighthouse | A    |         5 |
      | Lighthouse | B    |         5 |
      | Temple     | A    |         4 |
      | Temple     | B    |         4 |
      | Temple     | A    |         5 |
      | Temple     | B    |         5 |
      | Statue     | A    |         4 |
      | Statue     | B    |         4 |
      | Statue     | A    |         5 |
      | Statue     | B    |         5 |
      | Mausoleum  | A    |         4 |
      | Mausoleum  | B    |         4 |
      | Mausoleum  | A    |         5 |
      | Mausoleum  | B    |         5 |
      | Gardens    | A    |         4 |
      | Gardens    | B    |         4 |
      | Gardens    | A    |         5 |
      | Gardens    | B    |         5 |
      | Pyramids   | A    |         4 |
      | Pyramids   | B    |         5 |
      | Pyramids   | A    |         5 |
      | Pyramids   | B    |         6 |

  #Scenario Outline: valid resources
    #Given A Wonder <wonder> on Side <side>
    #And A player with that Wonder
    #When Building the wonder <numLevels> times
    #Then The level is built
    #And the player receives the effect of that level
#
    #Examples: 
      #| wonder     | side | numLevels |
      #| Colossus   | A    |         1 |
      #| Colossus   | A    |         2 |
      #| Colossus   | A    |         3 |
      #| Colossus   | B    |         1 |
      #| Colossus   | B    |         2 |
      #| Lighthouse | A    |         1 |
      #| Lighthouse | A    |         2 |
      #| Lighthouse | A    |         3 |
      #| Lighthouse | B    |         1 |
      #| Lighthouse | B    |         2 |
      #| Lighthouse | B    |         3 |
      #| Temple     | A    |         1 |
      #| Temple     | A    |         2 |
      #| Temple     | A    |         3 |
      #| Temple     | B    |         1 |
      #| Temple     | B    |         2 |
      #| Temple     | B    |         3 |
      #| Statue     | A    |         1 |
      #| Statue     | A    |         3 |
      #| Statue     | A    |         4 |
      #| Statue     | B    |         1 |
      #| Statue     | B    |         2 |
      #| Statue     | B    |         3 |
      #| Mausoleum  | A    |         1 |
      #| Mausoleum  | A    |         2 |
      #| Mausoleum  | A    |         3 |
      #| Mausoleum  | B    |         1 |
      #| Mausoleum  | B    |         2 |
      #| Mausoleum  | B    |         3 |
      #| Gardens    | A    |         1 |
      #| Gardens    | A    |         2 |
      #| Gardens    | A    |         3 |
      #| Gardens    | B    |         1 |
      #| Gardens    | B    |         2 |
      #| Gardens    | B    |         3 |
      #| Pyramids   | A    |         1 |
      #| Pyramids   | A    |         2 |
      #| Pyramids   | A    |         3 |
      #| Pyramids   | B    |         1 |
      #| Pyramids   | B    |         2 |
      #| Pyramids   | B    |         3 |
      #| Pyramids   | B    |         4 |

  #Scenario Outline: invalid resources
    #Given A Wonder <wonder> on Side <side>
    #And A player with that Wonder
    #When Building the wonder <numLevels> times
    #Then The level cannot be built
    #And They do not receive the effect of that level
#
    #Examples: 
      #| wonder     | side | numLevel |
      #| Colossus   | A    |        1 |
      #| Colossus   | A    |        2 |
      #| Colossus   | A    |        3 |
      #| Colossus   | B    |        1 |
      #| Colossus   | B    |        2 |
      #| Lighthouse | A    |        1 |
      #| Lighthouse | A    |        2 |
      #| Lighthouse | A    |        3 |
      #| Lighthouse | B    |        1 |
      #| Lighthouse | B    |        2 |
      #| Lighthouse | B    |        3 |
      #| Temple     | A    |        1 |
      #| Temple     | A    |        2 |
      #| Temple     | A    |        3 |
      #| Temple     | B    |        1 |
      #| Temple     | B    |        2 |
      #| Temple     | B    |        3 |
      #| Statue     | A    |        1 |
      #| Statue     | A    |        3 |
      #| Statue     | A    |        4 |
      #| Statue     | B    |        1 |
      #| Statue     | B    |        2 |
      #| Statue     | B    |        3 |
      #| Mausoleum  | A    |        1 |
      #| Mausoleum  | A    |        2 |
      #| Mausoleum  | A    |        3 |
      #| Mausoleum  | B    |        1 |
      #| Mausoleum  | B    |        2 |
      #| Mausoleum  | B    |        3 |
      #| Gardens    | A    |        1 |
      #| Gardens    | A    |        2 |
      #| Gardens    | A    |        3 |
      #| Gardens    | B    |        1 |
      #| Gardens    | B    |        2 |
      #| Gardens    | B    |        3 |
      #| Pyramids   | A    |        1 |
      #| Pyramids   | A    |        2 |
      #| Pyramids   | A    |        3 |
      #| Pyramids   | B    |        1 |
      #| Pyramids   | B    |        2 |
      #| Pyramids   | B    |        3 |
      #| Pyramids   | B    |        4 |
