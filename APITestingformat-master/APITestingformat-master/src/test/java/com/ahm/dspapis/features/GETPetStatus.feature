Feature: Filter items by status


  Scenario Outline: Filter items by single or multiple status values
    Given  I hit URL of get products api endpoint

    And the following status values <status_values>
    When I send a request to filter items by status
    Then I should receive a response with status code 200


    Examples:
      | status_values     |
      | available         |
      | pending           |
      | sold              |
      | available,pending |
      | pending,sold      |
      | available,sold    |