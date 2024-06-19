package com.ahm.dspapis.steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;



public class GetStatus {

    public String baseUrl = "http://api.example.com";
    public String endpoint = "/products";
    public Response response;
    public String Status_values;


    @Given("I hit URL of get products api endpoint")
    public void iHitURLOfGetProductsApiEndpoint() {
        RestAssured.baseURI = baseUrl;

    }




    @When("I send a request to filter items by status")
    public void i_send_a_request_to_filter_items_by_status() {
        String requestUrl = baseUrl + endpoint + "?status=" + Status_values;
        response = RestAssured.given()
                .when()
                .get(requestUrl);
        }
    @And("the following status values {}")
    public void theFollowingStatusValues(String arg0) {

        String Status_value = System.getProperty("arg0");
        Status_values =Status_value.replace(" ", ",");
    }
    @Then("I should receive a response with status code {int}")
    public void i_should_receive_a_response_with_status_code(int expectedStatusCode){
            Assert.assertEquals("Verify status code", expectedStatusCode, response.getStatusCode());
        }



}



