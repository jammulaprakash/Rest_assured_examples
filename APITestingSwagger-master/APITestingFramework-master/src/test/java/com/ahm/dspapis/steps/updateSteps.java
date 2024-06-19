package com.ahm.dspapis.steps;



import io.cucumber.java.en.*;

import io.restassured.response.Response;




public class updateSteps {

    public Response response;

    @Then("I validate the updated booking details")
    public void iValidateTheUpdatedBookingDetails() {
        System.out.println(response);
    }
}


