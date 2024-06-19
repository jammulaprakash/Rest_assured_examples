package com.ahm.dspapis.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class ReadVenue {

    private RequestSpecification request;
    private Response response;

    @Given("I have the venue id {string}")
    public void setVenueId(String id) {
        request = RestAssured.given().basePath("/api/v1");
        request.pathParam("id", id);
    }

    @When("I retrieve the venue details")
    public void retrieveVenueDetails() {
        response = request.get("https://coinmap.org/api/v1/venues/{id}");
    }

    @Then("the status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        Assert.assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the venue name should be {string}")
    public void verifyVenueName(String expectedVenueName) {
        String actualVenueName = response.jsonPath().getString("venue.name");
        Assert.assertEquals(expectedVenueName, actualVenueName);
    }


}
