Feature: SimSimi Conversation API

  Scenario: Send a GET request to SimSimi API
    Given the SimSimi API endpoint is configured
    When a user sends a GET request with parameters
    Then the response should have a valid result and message
