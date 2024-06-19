package com.ahm.dspapis.steps;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.put;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBookingSteps {
    public String strURI;
    public String strMethod;
    public Response response;
    public String mypath;

    public Map<String, String> requestheaders = new HashMap<>();
    public Map<String, String> pathparams = new HashMap<>();
    public Map<String, String> queryparams = new HashMap<>();
    public String completefiledetails;
    public String jsonContent = "";


    @Given("I set urlparameters details")
    public void iSetUrlparametersDetails(DataTable dt) throws InterruptedException {


        List<List<String>> urlparametersDetails = dt.asLists(String.class);
        Integer urlparametersObjects = urlparametersDetails.size();
        for (Integer i = 0; i < urlparametersObjects; i++) {
            pathparams.put(urlparametersDetails.get(i).get(0), urlparametersDetails.get(i).get(1));
        }
    }

    @Then("I receive the response code as {string}")
    public void iReceiveTheResponseCodeAs(String arg0) {


            response = RestAssured.given().get(strURI+mypath);
            int localresponseBody = response.getStatusCode();
            System.out.println(localresponseBody);
        int expectedStatusCode = Integer.parseInt(arg0);

            assertEquals(expectedStatusCode, localresponseBody);

        }

    @Then("I verify the total price is {string}")
    public void iVerifyTheTotalPriceIs(String arg1) {

            String actualVenueName = response.jsonPath().getString("totalprice");
            Assert.assertEquals(arg1, actualVenueName);


    }

    @Then("I receive response code as {string}")
    public void iReceiveResponseCodeAs(String arg1) {
        response = RestAssured.given().get(strURI+mypath);
        int localresponseBody = response.getStatusCode();
        System.out.println(localresponseBody);
        int expectedStatusCode = Integer.parseInt(arg1);

        assertEquals(expectedStatusCode, localresponseBody);
    }
}







