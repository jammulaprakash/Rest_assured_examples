Feature: Get Booking details
  Background: Prepare Endpoint with required headers and path
    Given I set requestdetails
      | method | get                                          |                  |
      | URL    | https://restful-booker.herokuapp.com/booking |                  |
      | header | Accept                                       | application/json |

#Positive Scenarios
  @Smoke
  Scenario: Verify the get api for Booking details
    Then I call the endpoint
    Then I receive the response code as '200'
    Then I retrieve the venue details
    Then I verify the total price is "437"

  #Negative Scenarios
  @Smoke
  Scenario: Negative scenario - without correct id
  Scenario: Verify the get api for details
    Given I set urlparameters details
      | id | iyt |
    Then I call the endpoint
    Then I receive response code as "404"
