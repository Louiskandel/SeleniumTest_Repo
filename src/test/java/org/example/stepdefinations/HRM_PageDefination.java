package org.example.stepdefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.utils.SingletonBrowserClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;

public class HRM_PageDefination {


    private static SingletonBrowserClass sbc;

    @BeforeAll
    public static void setup(){
        sbc = SingletonBrowserClass.getInstanceOfSingletonBrowserClass();
    }



    @Given("User is in HRM homepage {string}")
    public void user_is_in_hrm_homepage(String url) {
        sbc.getDriver().get(url);        //throw new io.cucumber.java.PendingException();
    }
    @When("user enters username as {string} and password as {string}")
    public void user_enters_username_as_and_password_as(String username3, String password3) throws InterruptedException{
        sbc.getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys(username3);
        Thread.sleep(1000);
        sbc.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(password3);
        Thread.sleep(1000);
    }
    @Then("User can login")
    public void user_can_login() throws InterruptedException{
        sbc.getDriver().findElement(By.xpath("//button[text()=' Login ']")).click();
        Thread.sleep(1000);

    }
    @Then("check username is correct")
    public void check_username_is_correct() {
        String username = sbc.getDriver().findElement(By.xpath("//p[text()='Paul Collings']")).getText();

        if (username.equals("Paul Collings")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Cart don't have exactly 2 items");
        }
    }

        @Then("find recrument")
        public void find_recrument() {
            sbc.getDriver().findElement(By.xpath("//span[text()='Recruitment']")).click();
        }

    @Then("Select Job Title")
    public void select_job_title()throws InterruptedException {


        WebElement selectTitle = sbc.getDriver().findElement(By.xpath("//select[@id='oldSelectMenu']"));

        Select se = new Select(selectTitle);

        se.selectByVisibleText("HR Associate");
        Thread.sleep(1000);
    }








}




