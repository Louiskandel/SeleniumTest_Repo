package org.example.stepdefinations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.utils.LocatorSingleton;
import org.example.utils.SingletonBrowserClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class HRM_PageDefination {


    private static SingletonBrowserClass sbc;
    private static Properties prop;

    @BeforeAll
    public static void setup(){
        sbc = SingletonBrowserClass.getInstanceOfSingletonBrowserClass();
        prop = LocatorSingleton.readPropertiesFile("src/test/resources/locators/login-locator.properties");
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
        String username = sbc.getDriver().findElement(By.xpath("//p[text()='zara khan']")).getText();

        if (username.equals("zara khan")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Cart don't have exactly 2 items");
        }
    }

        @Then("find recrument")
        public void find_recrument() {
            sbc.getDriver().findElement(By.xpath("//span[text()='Recruitment']")).click();
        }

    @Then("Select {string} as {string}")
    public void select_job_title( String data, String title)throws InterruptedException {
String DD = prop.getProperty("HRM_recruitment_DD").replace("<<Var1>>",title);
        String DText = prop.getProperty("HRM_recruiment_text").replace("<<Var1>>",title);

String title1 = "";

        Actions actions = new Actions(sbc.getDriver());
        WebElement jobTitle = sbc.getDriver().findElement(By.xpath(DD));
        WebDriverWait wait = new WebDriverWait(sbc.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(jobTitle));
        actions.click(jobTitle).build().perform();
        Thread.sleep(1000);


        for (int i = 0; i < 200; i++) {
            title1 = sbc.getDriver().findElement(By.xpath(DText)).getText();
            System.out.println(title1);
            Thread.sleep(500);
            if (title1.equals(data)) {
                sbc.getDriver().findElement(By.xpath(DText)).click();

                break;
           }
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();

        }


    }


    @Then("Select Vacancy")
    public void select_vacancy() throws InterruptedException {
        String vacancy = "";

        Actions actions = new Actions(sbc.getDriver());
        WebElement jobTitle = sbc.getDriver().findElement(By.xpath("//label[text()='Vacancy']/../following-sibling::div//div[contains(@class,'select-text--active')]"));
        WebDriverWait wait = new WebDriverWait(sbc.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(jobTitle));
        actions.click(jobTitle).build().perform();
        Thread.sleep(1000);


        for (int i = 0; i < 200; i++) {
            vacancy = sbc.getDriver().findElement(By.xpath("//label[text()='Vacancy']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]")).getText();
            System.out.println(vacancy);
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();
            Thread.sleep(500);
            if (vacancy.equals("Senior QA Lead")) {
                break;
            }
            sbc.getDriver().findElement(By.xpath("//label[text()='Vacancy']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]")).click();
        }
Thread.sleep(700);
    }

    @Then("Select Hiring Manager")
    public void select_hiring_manager() throws InterruptedException {
        String title = "";

        Actions actions = new Actions(sbc.getDriver());
        WebElement jobTitle = sbc.getDriver().findElement(By.xpath("//label[text()='Hiring Manager']/../following-sibling::div//div[contains(@class,'select-text--active')]"));
        WebDriverWait wait = new WebDriverWait(sbc.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(jobTitle));
        actions.click(jobTitle).build().perform();
        Thread.sleep(1000);


        for (int i = 0; i < 200; i++) {
            title = sbc.getDriver().findElement(By.xpath("//label[text()='Hiring Manager']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]")).getText();
            System.out.println(title);
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();
            Thread.sleep(500);
            if (title.equals("Linda Anderson")) {
                break;
            }
            sbc.getDriver().findElement(By.xpath("//label[text()='Hiring Manager']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]")).click();
        }

    }
    @Then("Select status")
    public void select_status() throws InterruptedException {

            String title = "";

            Actions actions = new Actions(sbc.getDriver());
            WebElement jobTitle = sbc.getDriver().findElement(By.xpath("//label[text()='Status']/../following-sibling::div//div[contains(@class,'select-text--active')]"));
            WebDriverWait wait = new WebDriverWait(sbc.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(jobTitle));
            actions.click(jobTitle).build().perform();
            Thread.sleep(1000);


            for (int i = 0; i < 200; i++) {
                title = sbc.getDriver().findElement(By.xpath("//label[text()='Status']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]")).getText();
                System.out.println(title);
                actions.sendKeys(Keys.ARROW_DOWN).build().perform();
                Thread.sleep(500);
                if (title.equals("Rejected")) {
                    break;
                }
                sbc.getDriver().findElement(By.xpath("//label[text()='Status']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]")).click();
            }

    }
    @AfterAll
    public static void teardown(){
        sbc.getDriver().quit();
    }




    }












