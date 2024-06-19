Feature: Retrieve booking details by firstname and comparing them

  Scenario: Get booking details for firstname Mark and comparing them
    When I clean the db
    When I set expectedres
    Then I get actualresu
   Then I compare expected and actual results
