package com.ahm.dspapis.steps;


import io.cucumber.java.en.Then;
import net.serenitybdd.rest.SerenityRest;


public class getdynamicpricingSteps {

    String localresponseBody = SerenityRest.lastResponse().body().asString();

    @Then("I validate the poecashOffer from response")
    public void iValidateThePoecashOfferFromResponse() {
        String localresponseBody = SerenityRest.lastResponse().body().asString();
        System.out.println(localresponseBody);

    }

}
