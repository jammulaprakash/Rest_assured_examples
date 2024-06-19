Feature: Testing API with RestAssured

  Scenario: Retrieve venue information
    Given I have the venue id "123"
    When I retrieve the venue details
    Then the status code should be 200
    And the venue name should be "CeX"

