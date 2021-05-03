Feature: Shopping Basket Test

  Scenario: shopping basket has 4 Product types
    Given The shopping basket has 1 Jacket, 1 Trousers, 1 Shirt, 1 Tie
    When I calculate the final price
    Then The price should show 111.6

  Scenario: shopping basket has only Jacket
    Given The shopping basket has 1 Jacket
    When I calculate the final price
    Then The price should show 49.9

  Scenario: shopping basket has Jacket and Trousers
    Given The shopping basket has 1 Jacket and 2 Trousers
    When I calculate the final price
    Then The price should show 113.6

  Scenario: shopping basket has Jacket and Trousers and Tie
    Given The shopping basket has 1 Jacket and 2 Trousers and 3 Tie
    When I calculate the final price
    Then The price should show 1.75