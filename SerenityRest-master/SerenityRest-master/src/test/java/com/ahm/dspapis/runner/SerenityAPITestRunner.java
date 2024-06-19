package com.ahm.dspapis.runner;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = { "pretty" }, features = "src/test/resources/features/", glue = {
        "com.ahm.dspapis.steps" })
@ConfigurationParameter(key =FILTER_TAGS_PROPERTY_NAME, value = "@Smoke")
public class SerenityAPITestRunner {


}
