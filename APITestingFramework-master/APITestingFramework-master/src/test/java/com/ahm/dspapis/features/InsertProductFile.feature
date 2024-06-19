Feature: PUT Request
  Scenario: Update product using PUT method
    Given I have the JSON data file
    When I send a PUT request to the endpoint with the JSON data
    Then the product should be updated.