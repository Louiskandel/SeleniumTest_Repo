package org.example.stepdefinations;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.example.staticfields.StaticInstances;
import org.example.utils.LocatorSingleton;
import org.example.utils.SingletonBrowserClass;

public class commonStepDefination {

    @BeforeAll
    public static void setup(){
        StaticInstances.sbc = SingletonBrowserClass.getInstanceOfSingletonBrowserClass();
        StaticInstances.prop = LocatorSingleton.readPropertiesFile("src/test/resources/locators/login-locator.properties");
    }

    @AfterAll
    public static void teardown(){
        StaticInstances.sbc.getDriver().quit();
    }

}