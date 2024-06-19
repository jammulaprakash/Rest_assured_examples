Feature: Updating venue details via Coinmap API

  Scenario: Update an existing venue
    Given an existing venue with ID {int}
    When the venue details are updated via the API
    Then verify the status code is 200
    And verify the updated venue details
