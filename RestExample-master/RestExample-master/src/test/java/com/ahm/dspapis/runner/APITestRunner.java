package com.ahm.dspapis.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.runner.RunWith;
import static io.cucumber.core.options.Constants.FILTER_TAGS_PROPERTY_NAME;


public class APITestRunner {





    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = {"src/test/java/com.ahm.dspapis.features"},
            glue = {"com.ahm.dspapis.steps"}
    )
    @ConfigurationParameter(key =FILTER_TAGS_PROPERTY_NAME, value = "@Smoke")
    public class TestRunner {
    }




}
