package com.ahm.dspapis.steps;


import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

public class UpdateVenue {

    public static final String API_BASE_URL = "https://coinmap.org/api/v1/venues/";

    public RequestSpecification request;
    public Response response;
    public int venueId;


    @Given("an existing venue with ID \\{int}")
    public void anExistingVenueWithIDInt(int id) {
        venueId = id;
        String updatedVenueJson = "{ \"name\": \"Updated Venue\", \"address\": \"456 Elm St\", \"category\": \"bar\" }";
        request = RestAssured.given().body(updatedVenueJson);
    }

    @When("the venue details are updated via the API")
    public void theVenueDetailsAreUpdatedViaTheAPI() {
        response = request.put(API_BASE_URL + venueId);
    }

    @Then("verify the status code is {int}")
    public void verifyTheStatusCodeIs(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);

    }

    @Then("verify the updated venue details")
    public void verifyTheUpdatedVenueDetails() {
        System.out.println(response.getBody());
        response.then().assertThat().body("venue.name", equalTo("Updated Venue"));
        response.then().assertThat().body("venue.address", equalTo("456 Elm St"));
        response.then().assertThat().body("venue.category", equalTo("bar"));
    }


}
