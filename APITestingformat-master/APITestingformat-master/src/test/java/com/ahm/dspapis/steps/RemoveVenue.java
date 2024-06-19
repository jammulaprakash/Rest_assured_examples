package com.ahm.dspapis.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.delete;
import static org.hamcrest.Matchers.*;

public class RemoveVenue {

    private String baseUrl = "https://coinmap.org/api/v1/venues/";
    private int venueId;
    private Response response;

    @Given("I have the ID of the venue to be deleted")
    public void iHaveTheIdOfTheVenueToBeDeleted() {
        venueId = 123;
    }


    @When("I send a DELETE request to the Coinmap API")
    public void iSendADeleteRequestToTheCoinmapApi() {
        response = delete(baseUrl + venueId);
    }

    @Then("I should receive a status code of {int}")
    public void iShouldReceiveAStatusCodeOf(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should indicate that the status is OK")
    public void theResponseShouldIndicateThatTheStatusIsOk() {
        response.then().body("status", equalTo("OK"));
    }



}