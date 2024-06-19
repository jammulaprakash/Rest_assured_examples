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

public class CreateBookingSteps {
    public String strURI;
    public String strMethod;
    public Response  response;
    public String mypath;

    public Map<String, String> requestheaders = new HashMap<>();
    public Map<String, String> pathparams = new HashMap<>();
    public Map<String, String> queryparams = new HashMap<>();
    public String completefiledetails;
    public String jsonContent = "";


    

    @Then("I validate the new booking details")
    public void iValidateTheNewBookingDetails() {

        System.out.println(response.getBody().asString());


    }


}


