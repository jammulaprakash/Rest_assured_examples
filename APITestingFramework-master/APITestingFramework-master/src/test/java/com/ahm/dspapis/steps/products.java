package com.ahm.dspapis.steps;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class products {
    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int ResponseCode;
    public ResponseBody body;
    public JSONObject requestParams;
    public JsonPath jsnpath;
    public String s;
    static ExtentReports report;
    public static ExtentTest test;
    public static ExtentReports extent = new ExtentReports();


    @Given("^I hit the url of get products api endpoint$")
    public void i_hit_the_url_of_get_products_api_endpoint() throws Throwable {
        RestAssured.baseURI = "https://fakestoreapi.com/";
    }

    @When("I pass the url of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("products");
    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        ResponseCode = response.getStatusCode();
        assertEquals(ResponseCode, 200);
    }



    @Given("I hit the url of post product api endpoint")
    public void i_hit_the_url_of_post_product_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();
        requestParams = new JSONObject();
    }

    @And("I pass the request body of product title {}")
    public void i_pass_the_request_body_of_product_title(String title) {
        requestParams.put("title", title);
        requestParams.put("price",9.95);
        requestParams.put("description","great keychain");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("category","keys");
        httpRequest.body(requestParams.toJSONString());
        Response response =httpRequest.post("products");
        ResponseBody body = response.getBody();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Given("I hit the url of put product api endpoint")
    public void i_hit_the_url_of_put_product_api_endpoint() {
        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = given();
        requestParams = new JSONObject();
    }

    @When("I pass the url of products in the request with {}")
    public void i_pass_the_url_of_products_in_the_request_with(String productnumber) {
        requestParams.put("title", "nvidia");
        requestParams.put("price","255");
        requestParams.put("description","great nvidia rtx studio");
        requestParams.put("image","https://i.pravatar.cc");
        requestParams.put("cateogry","games");
        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.put("products/"+ productnumber);
        ResponseBody body = response.getBody();
        JsonPath jsnpath = response.jsonPath();
        s = jsnpath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
        System.out.println(s);

    }
}