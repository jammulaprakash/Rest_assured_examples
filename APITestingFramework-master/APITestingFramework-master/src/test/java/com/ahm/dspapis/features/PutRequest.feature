Feature: PUT Request
  Scenario Outline: Update product using PUT method
    Given I have the JSON file "<fileName>"
    And the product ID is "<Id>"
    And the title is "<title>"
    Then I update requestJson file "<fileName>"
    When I send a PUT request to the endpoint
    Then the product should be updated successfully

    Examples:
      | Id  | title | fileName |
      | 025 | intel | example  |


