package com.ahm.dspapis.steps;


import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import org.junit.Assert;
import java.io.File;

public class insertfile {
    public RequestSpecification httpRequest;
    private Response response;

    @Given("I have the JSON data file")
    public void i_have_the_Json_data_file() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();

    }

    @When("I send a PUT request to the endpoint with the JSON data")
    public void i_send_a_PUT_request_to_the__endpoint_with_th_JSON_data() {
        File jsonFile = new File("C:\\Users\\jammu\\Downloads\\example.json");
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonFile)
                .post(baseURI+"products");
    }

    @Then("the product should be updated")
    public void theProductShouldBeUpdated() {
        Assert.assertEquals(200, response.getStatusCode());


        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }
    }




