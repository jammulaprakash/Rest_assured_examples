Feature: Get customer factor from the 1c1h API
  Background: Prepare Endpoint with required headers and path
    Given I set requestdetails
      | method      | get                                   |                                            |
      | URL         | https://ws-qa4.hondaweb.com/          |                                            |
      | pathparams  | REST/TreasureData/CollectSegments/1.0 |                                            |
      | header      | hondaHeaderType.messageId             | 124566                                     |
      | header      | hondaHeaderType.siteId                | 45666                                      |
      | header      | hondaHeaderType.businessId            | 98766                                      |
      | header      | Accept                                | application/json                           |
      | header      | Authorization                         | Basic c2VydmljZV9jZHBfcG9lX3FhOkxlYWQjNTAw |

#Positive Scenarios
  @Smoke
  Scenario: Verify the get api for the 1C1H to get Customer Factor for Model ID 3B1H4REXW and eUserIdent 300029344
    Given I set queryparameters details
      | version                               | 2                                          |
      | token                                 | aa545d8a-53f2-4949-ac0f-dc6443add848       |
      | key.lookup_key                        | 3B1H4REXW~300029344                         |
    Then I call the endpoint
    Then I receive the response code as '200'
    Then I verify the customer factor is '3.02'

  @Smoke
  Scenario: Verify the get api for the 1C1H to get Customer Factor for Model ID 3B2H4REW and eUserIdent 300029478
    Given I set queryparameters details
      | version                               | 2                                          |
      | token                                 | aa545d8a-53f2-4949-ac0f-dc6443add848       |
      | key.lookup_key                        | 3B2H4REW~300029478                         |
    Then I call the endpoint
    Then I receive the response code as '200'
    Then I verify the customer factor is '3.02'


# Negative Scenarios
  @Smoke
  Scenario: Negative scenario - without modelID
  Scenario: Verify the get api for the 1C1H to get Customer Factor for No Model ID and eUserIdent 300029478
    Given I set queryparameters details
      | version                               | 2                                          |
      | token                                 | aa545d8a-53f2-4949-ac0f-dc6443add848       |
      | key.lookup_key                        | ~300029478                         |
    Then I call the endpoint
    Then I receive the response code as '400'

