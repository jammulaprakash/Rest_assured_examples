Feature: delete Booking details
  Background: Prepare Endpoint with required headers and path
    Given I set requestdetails
      | method     | delete                     |  |
      | URL        | http://localhost:3000/api/ |  |
      | pathparams | bookingdetails             |  |

#Positive Scenarios
  @Smoke
  Scenario: Verify the delete api for Booking details
    Given I set urlparameters details
      | id | 2 |
    Then I call the endpoint
    Then I receive the response code as '200'


# Negative Scenarios
  @Smoke
  Scenario: Negative scenario - without correct id
  Scenario: Verify the delete api for details
    Given I set urlparameters details
      | id | dfg |
    Then I call the endpoint
    Then I receive response code as "404"
