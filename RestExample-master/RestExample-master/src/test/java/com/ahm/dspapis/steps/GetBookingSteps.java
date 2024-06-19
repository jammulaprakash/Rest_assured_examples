package com.ahm.dspapis.steps;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBookingSteps extends requestBuilder {
    public Map<String, String> pathparams = new HashMap<>();
    public Response response;


    @Given("I set requestdetails")
    public void i_set_requestdetails(DataTable dt) throws InterruptedException{
        List<List<String>> requestDetails = dt.asLists(String.class);
        Integer countrequestObjects = requestDetails.size();

        for (Integer i=0; i < countrequestObjects; i++) {

            switch (requestDetails.get(i).get(0)) {
                case "header":
                    requestheaders.put(requestDetails.get(i).get(1), requestDetails.get(i).get(2));
                    break;
                case "pathparams":
                    mypath = requestDetails.get(i).get(1);
                    break;

                case "URL":
                    strURI = requestDetails.get(i).get(1);
                    break;

                case "method":
                    strMethod = requestDetails.get(i).get(1);
                    break;

                default:
                    break;
            }
        }
    }

    @Then("I receive the response code as {string}")
    public void i_recive_the_response_code_as(String arg0) {

         response = RestAssured.get(pathparams.toString());
        String localresponseBody = response.getBody().asString();
        System.out.println(localresponseBody);
        String expectedStatusCode = arg0;
        String actualStatusCode = String.valueOf(response.getStatusCode());
        assertEquals(expectedStatusCode, actualStatusCode);

    }

    @Then("I retrieve the venue details")
    public void iRetrieveTheVenueDetails() {

    }


    @Then("I verify the total price is {string}")
    public void iVerifyTheTotalPriceIs(String arg1) {
        String actualVenueName = response.jsonPath().getString("totalprice");
        Assert.assertEquals(arg1, actualVenueName);

    }


}

