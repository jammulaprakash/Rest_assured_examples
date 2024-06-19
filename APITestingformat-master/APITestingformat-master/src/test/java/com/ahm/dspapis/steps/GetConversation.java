package com.ahm.dspapis.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class GetConversation  {


    private Response response;

    @Given("the SimSimi API endpoint is configured")
    public void configureSimSimiEndpoint() {
        RestAssured.baseURI = "http://sandbox.api.simsimi.com";

    }

    @When("a user sends a GET request with parameters")
    public void sendGetRequest() {
        String apiKey = "QDytKZkVD7WyUbIZlZl9lgCMaDFJJZ~~es~5nX8j";
        String languageCode = "en";
        double filterThreshold = 1.0;
        String queryText = "hi";

        response = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("lc", languageCode)
                .queryParam("ft", filterThreshold)
                .queryParam("text", queryText)
                .when()
                .get("/request.p");
    }

    @Then("the response should have a valid result and message")
    public void verifyResponse() {
        Assertions.assertEquals(200, response.getStatusCode(), "Expected status code 200");
        Assertions.assertTrue(response.getBody().asString().contains("\"result\": 100"),
                "Expected result code 100 in response");
        System.out.println(response);
    }
}
