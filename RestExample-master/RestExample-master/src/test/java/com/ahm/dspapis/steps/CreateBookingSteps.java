package com.ahm.dspapis.steps;


import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;


public class CreateBookingSteps extends requestBuilder{
    public Map<String, String> pathparams = new HashMap<>();
    public Response response;




    @Then("I validate the new booking details")
    public void iValidateTheNewBookingDetails() {
        response = RestAssured.get(pathparams.toString());
        String localresponseBody = response.getBody().asString();
        System.out.println(localresponseBody);
    }
}
