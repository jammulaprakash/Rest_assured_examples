package com.ahm.dspapis.steps;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class get1c1hSteps {

    @Then("I receive the response code as {string}")
    public void i_recive_the_response_code_as(String arg0) {

        String localresponseBody = SerenityRest.lastResponse().body().asString();
        System.out.println(localresponseBody);
        SerenityRest.restAssuredThat(response -> response.statusCode(Integer.parseInt(arg0)));
    }

    @Then("I verify the customer factor is {string}")
    public void i_verify_the_customer_factor_is(String arg0) {
//        String customerfactor = response.body().path("attributes.new_auto_incentive_customer_factor").toString();
//        customerfactor = customerfactor.replace("[", "").replace("]","");
//        Assertions.assertEquals(customerfactor, arg0);

    }
}
