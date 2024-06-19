Feature: get dynamic price offers from POE getdynamicpricing api

  Background: Prepare Endpoint with required headers and path
    Given I set requestdetails
      | method     | post                                   |                                                |
      | URL        | https://staging.po.services.honda.com/ |                                                |
      | pathparams | PriceEngine/GetDynamicPricing          |                                                |
      | header     | hondaHeaderType.messageId              | 1                                              |
      | header     | Content-Type                           | application/json                               |
      | header     | Accept                                 | application/json                               |
      | header     | Authorization                          | Basic c2VydmljZV90ZWtpb25fcG9fZGV2OkNlbGVyeSQ1 |
      | header     | Accept-Encoding                        | gzip, deflate, br                              |

  @Smoke
  Scenario: validate POE Cash offer for a given Model 3B2H6REW dealer zip code and customer zip code
    Given I create jsonbaseline
      | foldername | getdynamicpricing            |
      | filename   | getdynamicpricingmaster.json |
    Given I set body details
      | input_productDivisionCD         | ACURA     |
      | input_vehicleTypeCD             | N         |
      | input_modelID                   | 3B2H6REW  |
      | input_dealerNo                  | 251003    |
      | input_dealerZipCode             | 60504     |
      | input_customerZipCode           | 60504     |
      | input_eUserIdent                | 300029407 |
      | optional_fico                   | 740       |
      | optional_customerSelectedOffers | AP-POE03  |
    Then I call the endpoint
    Then I validate the poecashOffer from response

  @Smoke
  Scenario: validate POE Cash offer for a given Model dealer 3B2H4REW zip code and customer zip code 90703
    Given I create jsonbaseline
      | foldername | getdynamicpricing            |
      | filename   | getdynamicpricingmaster.json |
    Given I set body details
      | input_productDivisionCD         | ACURA     |
      | input_vehicleTypeCD             | N         |
      | input_modelID                   | 3B2H4REW  |
      | input_dealerNo                  | 251003    |
      | input_dealerZipCode             | 90703     |
      | input_customerZipCode           | 90703     |
      | input_eUserIdent                | 300029407 |
      | optional_fico                   | 740       |
      | optional_customerSelectedOffers | AP-POE03  |
    Then I call the endpoint
    Then I validate the poecashOffer from response





