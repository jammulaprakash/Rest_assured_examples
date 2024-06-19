package com.ahm.dspapis.steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class deletefile {
    public RequestSpecification httpRequest;
    public Response response;
    public String jsonContent;
    public String jsonDataFile = "\"C:\\Users\\jammu\\Downloads\\example.json";





//    @Then("the JSON data file should not contain the entry with ID {string}")
//    public void theJSONDataFileShouldNotContainTheEntryWithId(String id) throws IOException {
//        File file = new File("C:\\Users\\jammu\\Downloads\\example.json");
//        jsonContent = new String(Files.readAllBytes(file.toPath()));
//        JSONObject obj = (JSONObject) JSONValue.parse(jsonContent);
//        obj.remove(id);
//
//
//
//        Response response = RestAssured.get(jsonDataFile);
//        assertFalse(response.jsonPath().getList("entries.id").contains(id));
//    }


    @Given("the JSON data file contains an entry with ID")
    public void theJSONDataFileContainsAnEntryWithID() {

        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();

    }

    @When("the entry with ID <{string}> is deleted")
    public void theEntryWithIDIsDeleted(String arg0) {
        System.out.println(jsonContent);
        Response response = given()
                .contentType(ContentType.JSON)
                .delete(baseURI + "products" + arg0);


    }

    @Then("the JSON data file should not contain the entry with ID <{string}>")
    public void theJSONDataFileShouldNotContainTheEntryWithID(String i) throws IOException {

        File file = new File("C:\\Users\\jammu\\Downloads\\example.json");
        jsonContent = new String(Files.readAllBytes(file.toPath()));
        System.out.println(jsonContent);
        JSONObject obj = (JSONObject) JSONValue.parse(jsonContent);
        obj.remove(i);
        String output = JSONValue.toJSONString(obj);
        Files.write(file.toPath(), output.getBytes(StandardCharsets.UTF_8));




    }

}



























