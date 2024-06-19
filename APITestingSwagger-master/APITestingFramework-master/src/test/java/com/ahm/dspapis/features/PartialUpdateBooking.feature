
Feature: update the one or two details of booking in the API

  Background: Prepare Endpoint with required headers and path
    Given I set requestdetails
      | method     | patch                      |                  |
      | URL        | http://localhost:3000/api/ |                  |
      | pathparams | bookingdetails             |                  |
      | header     | Content-Type               | application/json |
      | header     | Accept                     | application/json |

  @Smoke
  Scenario: updating the booking with new details
    Given I create jsonbaseline
      | filename | updatedynamicdata.json |
    Given I set body details
      | paid_value    | false      |
      | checkin_value | 2014-10-25 |
    Then I call the endpoint
    Then I validate the updated booking details
