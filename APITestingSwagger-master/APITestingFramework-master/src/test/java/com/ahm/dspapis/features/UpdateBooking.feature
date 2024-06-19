Feature: update the details of booking in the API

  Background: Prepare Endpoint with required headers and path
    Given I set requestdetails
      | method     | put                        |                  |
      | URL        | http://localhost:3000/api/ |                  |
      | pathparams | bookingdetails             |                  |
      | header     | Content-Type               | application/json |
      | header     | Accept                     | application/json |

  @Smoke
  Scenario: updating the booking with new details
    Given I create jsonbaseline
      | filename | getdynamicdata.json |
    Given I set body details
      | firstname_value  | prakash      |
      | lastname_value   | Jammula      |
      | totalprice_value | 111          |
      | paid_value       | true         |
      | checkin_value    | 2019-01-01   |
      | checkout_value   | 2020-12-12   |
      | needs_value      | not required |
    Then I call the endpoint
    Then I validate the updated booking details
