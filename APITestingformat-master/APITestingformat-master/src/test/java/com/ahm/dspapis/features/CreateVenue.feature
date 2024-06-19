Feature: Adding venues via Coinmap API

  Scenario: Add a new venue successfully
    Given a venue to add
    When the venue is added via the API
    Then verify the response status code is 400
    And verify the added venue details