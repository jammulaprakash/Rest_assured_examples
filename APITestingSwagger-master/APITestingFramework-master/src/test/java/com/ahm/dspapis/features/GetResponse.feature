Feature: Retrieve booking details by firstname

  Scenario: Get booking details for firstname Mark and print as JSON anmd send it to database
    Given clean the database
    When I retrieve booking details for firstname Mark
    Then I print the booking details as inside the mysql database


