package org.example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "@datatabletest", features = {"src/test/resources/features"}, glue = {"org/example/stepdefinations"}, plugin = {"pretty", "html:target/cucumber-report.html"})
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {


}