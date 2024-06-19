Feature: create a new booking in the API

  Background: Prepare Endpoint with required headers and path
    Given I set requestdetails
      | method     | post                       |                  |
      | URL        | http://localhost:3000/api/ |                  |
      | pathparams | bookingdetails             |                  |
      | header     | Content-Type               | application/json |
      | header     | Accept                     | application/json |

  @Smoke
  Scenario: create a new booking with all details with id sai
    Given I create jsonbaseline
      | filename | getdynamicdata.json |
    Given I set body details
      | firstname_value  | Sai        |
      | lastname_value   | Jammula    |
      | totalprice_value | 5367       |
      | paid_value       | true       |
      | checkin_value    | 2019-06-15 |
      | checkout_value   | 2020-08-06 |
      | needs_value      | breakfast  |
    Then I call the endpoint
    Then I validate the new booking details
