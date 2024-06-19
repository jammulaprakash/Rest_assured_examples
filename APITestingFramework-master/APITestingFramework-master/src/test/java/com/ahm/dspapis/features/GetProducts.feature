
  Feature: Get all products from the API
    Scenario: Verify the get api for the products
      Given I hit URL of get prodcuts api endpoint
      When I pass the url in the request
      Then I recive the response code as 200