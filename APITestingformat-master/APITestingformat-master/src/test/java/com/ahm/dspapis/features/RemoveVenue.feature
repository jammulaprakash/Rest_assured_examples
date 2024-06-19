Feature: Delete a venue from Coinmap

  Scenario: Successfully delete a venue
    Given I have the ID of the venue to be deleted
    When I send a DELETE request to the Coinmap API
    Then I should receive a status code of 200
    And the response should indicate that the status is OK