package com.ahm.dspapis.steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLOutput;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PutRequestfile {
    private Response response;
    private String jsonContent;
    public String Id;
    public String i;
    public String title;
    public String t;
    public RequestSpecification httpRequest;

    @Given("I have the JSON file {string}")
    public void iHaveTheJsonFile(String arg0) throws IOException {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();

//        File file = new File("C:\\Users\\jammu\\Downloads\\"+arg0+".json");
//        jsonContent = new String(Files.readAllBytes(file.toPath()));
    }

    @Given("the product ID is {string}")
    public void theProductIdIs(String args0) {
            i=args0;
    }

    @Given("the title is {string}")
    public void theTitleIs(String args1) {
            t=args1;
    }

    @When("I send a PUT request to the endpoint")
    public void iSendAPutRequestToTheEndpoint() {
        System.out.println(jsonContent);
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonContent)
                .put(baseURI+"products"+i);


           //  productsresposebody.get(response.getBody().asString());
    }

    @Then("the product should be updated successfully")
    public void theProductShouldBeUpdatedSuccessfully() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(title, response.jsonPath().getString("title"));
    }

    @Then("I update requestJson file {string}")
    public void iUpdateRequestJsonFile(String arg0) throws IOException {

        File file = new File("C:\\Users\\jammu\\Downloads\\"+arg0+".json");
        jsonContent = new String(Files.readAllBytes(file.toPath()));
        jsonContent = jsonContent.replace("589", i);
        jsonContent = jsonContent.replace("nvidia",t);
    }
}
