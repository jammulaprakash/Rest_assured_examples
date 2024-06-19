package com.ahm.dspapis.steps;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class requestBuilder {
    public String strURI;
    public String strMethod;
    public Response response;
    public JsonObject responsebody = new JsonObject();
    public String finalrequestwithoutbody;
    public String mypath;

    public Map<String, String> requestheaders = new HashMap<>();
    public Map<String, String> pathparams = new HashMap<>();
    public Map<String, String> queryparams = new HashMap<>();
    public String completefiledetails;
    public String jsonContent = "";

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

    @Given("I set queryparameters details")
    public void i_set_queryparameters_details(DataTable dt) throws InterruptedException{
        List<List<String>> queryparametersDetails = dt.asLists(String.class);
        Integer queryparametersObjects = queryparametersDetails.size();

        for (Integer i=0; i < queryparametersObjects; i++) {
            queryparams.put(queryparametersDetails.get(i).get(0), queryparametersDetails.get(i).get(1));
        }
    }

    @Given("I create jsonbaseline")
    public void iCreateJsonbaseline(DataTable dt) throws InterruptedException {
        List<List<String>> fileDetails = dt.asLists(String.class);
        completefiledetails = "src/test/testdata/"+fileDetails.get(0).get(1)+"/"+fileDetails.get(1).get(1);
    }

    @Given("I set body details")
    public void iSetBodyDetails(DataTable dt) throws InterruptedException, IOException {
        List<List<String>> requestBodyDetails = dt.asLists(String.class);
        Integer countrequestBodyObjects = requestBodyDetails.size();

        File file = new File(completefiledetails);
        jsonContent = new String(Files.readAllBytes(file.toPath()));

        for (Integer i=0; i < countrequestBodyObjects; i++) {
            jsonContent = jsonContent.replace(requestBodyDetails.get(i).get(0),requestBodyDetails.get(i).get(1));
        }
        System.out.println(completefiledetails);
    }

    @Then("I call the endpoint")
    public void iCallTheEndpoint() {

        if (strMethod.equals("get")){

            response = SerenityRest
                    .given()
                    .headers(requestheaders)
                    .queryParams(queryparams)
                    .when()
                    .get(strURI+mypath);

        } else if (strMethod.equals("post")){

        response = SerenityRest
                .given()
                .headers(requestheaders)
                .body(jsonContent)
                .when()
                .post(strURI+mypath);

        } else if (strMethod.equals("put")){
            response = SerenityRest
                    .given()
                    .headers(requestheaders)
                    .body(jsonContent)
                    .when()
                    .post(strURI+mypath);

        } else if (strMethod.equals("delete")){

        } else if (strMethod.equals("patch")){
            response = SerenityRest
                    .given()
                    .headers(requestheaders)
                    .body(jsonContent)
                    .when()
                    .post(strURI+mypath);

        }
    }

}
