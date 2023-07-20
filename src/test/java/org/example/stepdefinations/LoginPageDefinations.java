package org.example.stepdefinations;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.utils.SingletonBrowserClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;


public class LoginPageDefinations {

    private static SingletonBrowserClass sbc;

    @BeforeAll
    public static void setup(){
        sbc = SingletonBrowserClass.getInstanceOfSingletonBrowserClass();
    }


    @Given("User is in Suacedemo homepage {string}")
    public void navigate_to_url(String url) {
        sbc.getDriver().get(url);

    }

    @When("User enters correct username {string}")
    public void user_enters_correct_username(String username) {
        sbc.getDriver().findElement(By.cssSelector("input[id='user-name']")).sendKeys(username);

    }
    @Then("User enters correct password {string}")
    public void user_enters_correct_password(String password) {
        sbc.getDriver().findElement(By.cssSelector("input[id='password']")).sendKeys(password);
    }

    @Then("User should be able to login")
    public void user_should_be_able_to_login() {
        sbc.getDriver().findElement(By.cssSelector("input[id='login-button']")).click();
        WebDriverWait wait = new WebDriverWait(sbc.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='app_logo' and text()='Swag Labs']")));
        String errortext = sbc.getDriver().findElement(By.xpath("//div[@class='app_logo' and text()='Swag Labs']")).getText();
        if (errortext.contains("Swag Labs")){
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Logo didn't appeared after waiting for 10 seconds");
        }
    }

    @When("User enters username as {string} and password as {string}")
    public void user_enters_username_as_and_password_as(String username, String password) {
        sbc.getDriver().findElement(By.cssSelector("input[id='user-name']")).sendKeys(username);
        sbc.getDriver().findElement(By.cssSelector("input[id='password']")).sendKeys(password);
    }
    @Then("User should be able to see error message {string}")
    public void user_should_be_able_to_see_error_message(String errormessage) {
        sbc.getDriver().findElement(By.cssSelector("input[id='login-button']")).click();
        String errortext = sbc.getDriver().findElement(By.xpath("//div[contains(@class,'error-message-container')]/h3[contains(text(),'"+errormessage+"')]")).getText();
        if (errortext.contains(errormessage)){
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Text doesn't contains locked out user");
        }
    }

    @When("User enters correct credentials")
    public void user_enters_correct_credentials(DataTable credentials) {
        List<List<String>> data = credentials.asLists(String.class);
        for(List<String> columns: data){
            sbc.getDriver().findElement(By.cssSelector("input[id='user-name']")).sendKeys(columns.get(0));
            sbc.getDriver().findElement(By.cssSelector("input[id='password']")).sendKeys(columns.get(1));
        }
    }



    @AfterAll
    public static void teardown(){
        sbc.getDriver().quit();
    }
}