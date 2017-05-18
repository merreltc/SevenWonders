Feature: Build Wonder
  In order to win the game,
  As a player,
  I want to build my Wonder.

  #Scenario Outline: valid build Wonder
  #Given A player with a Wonder <wonder> on Side <side>
  #When The builds the wonder <times>
  #Then The player receives the effects
  Scenario Outline: Invalid Build Wonder
    Given A Wonder <wonder> on Side <side>
    When Building the wonder <times> times
    Then The player cannot build that level

    Examples: 
      | wonder     | side | times |
      | Colossus   | A    |     4 |
      | Colossus   | B    |     3 |
      | Colossus   | A    |     5 |
      | Colossus   | B    |     4 |
      | Lighthouse | A    |     4 |
      | Lighthouse | B    |     4 |
      | Lighthouse | A    |     5 |
      | Lighthouse | B    |     5 |
      | Temple     | A    |     4 |
      | Temple     | B    |     4 |
      | Temple     | A    |     5 |
      | Temple     | B    |     5 |
      | Statue     | A    |     4 |
      | Statue     | B    |     4 |
      | Statue     | A    |     5 |
      | Statue     | B    |     5 |
      | Mausoleum  | A    |     4 |
      | Mausoleum  | B    |     4 |
      | Mausoleum  | A    |     5 |
      | Mausoleum  | B    |     5 |
      | Gardens    | A    |     4 |
      | Gardens    | B    |     4 |
      | Gardens    | A    |     5 |
      | Gardens    | B    |     5 |
      | Pyramids   | A    |     4 |
      | Pyramids   | B    |     5 |
      | Pyramids   | A    |     5 |
      | Pyramids   | B    |     6 |
