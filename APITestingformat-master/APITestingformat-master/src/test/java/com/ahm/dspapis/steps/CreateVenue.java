package com.ahm.dspapis.steps;


import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.codehaus.groovy.transform.SourceURIASTTransformation;

import static org.hamcrest.Matchers.equalTo;

public class CreateVenue {

   public static final String API_BASE_URL = "https://coinmap.org/api/v1/venues/";

    public RequestSpecification request;
    public Response response;

    @Given("a venue to add")
    public void aVenueToAdd() {
        String venueJson = "{ \"name\": \"Sample Venue\", \"address\": \"123 Main St\", \"category\": \"restaurant\" }";
        request = RestAssured.given().body(venueJson);
    }

    @When("the venue is added via the API")
    public void theVenueIsAddedViaTheAPI() {
        response = request.post(API_BASE_URL);
    }

    @Then("verify the response status code is {int}")
    public void verifyTheResponseStatusCodeIs(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Then("verify the added venue details")
    public void verifyTheAddedVenueDetails() {
        System.out.println(response.getBody());
        response.then().assertThat().body("venue.name", equalTo("Sample Venue"));
        response.then().assertThat().body("venue.address", equalTo("123 Main St"));
        response.then().assertThat().body("venue.category", equalTo("restaurant"));
    }
}
