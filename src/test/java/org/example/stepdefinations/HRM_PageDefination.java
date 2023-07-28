package org.example.stepdefinations;

import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.staticfields.StaticInstances;
import org.example.utils.SingletonBrowserClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

public class HRM_PageDefination {


   /* private static SingletonBrowserClass sbc;
    private static Properties prop;*/




    @Given("User is in HRM homepage {string}")
    public void user_is_in_hrm_homepage(String url) {
        StaticInstances.sbc.getDriver().get(url);        //throw new io.cucumber.java.PendingException();
    }
    @When("user enters username as {string} and password as {string}")
    public void user_enters_username_as_and_password_as(String username3, String password3) throws InterruptedException{
        StaticInstances.sbc.getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys(username3);
        Thread.sleep(10);
        StaticInstances.sbc.getDriver().findElement(By.xpath("//input[@name='password']")).sendKeys(password3);
        Thread.sleep(10);
    }
    @Then("User can login")
    public void user_can_login() throws InterruptedException{
        StaticInstances.sbc.getDriver().findElement(By.xpath("//button[text()=' Login ']")).click();
        Thread.sleep(100);

    }



    /* @Then("check username is correct")
    public void check_username_is_correct() {
        String username = sbc.getDriver().findElement(By.xpath("//p[text()='zara khan']")).getText();

        if (username.equals("zara khan")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Name is not correct");
        }
    }
*/
        @Then("find recrument")
        public void find_recrument() {
            StaticInstances.sbc.getDriver().findElement(By.xpath("//span[text()='Recruitment']")).click();
        }

    @Then("Select {string} as {string}")
    public void select_job_title( String data, String title)throws InterruptedException {
        String DD = StaticInstances.prop.getProperty("HRM_recruitment_DD").replace("<<Var1>>",title);
        String DText = StaticInstances.prop.getProperty("HRM_recruiment_text").replace("<<Var1>>",title);

        String title1 = "";

        Actions actions = new Actions(StaticInstances.sbc.getDriver());
        WebElement jobTitle = StaticInstances.sbc.getDriver().findElement(By.xpath(DD));
        WebDriverWait wait = new WebDriverWait(StaticInstances.sbc.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(jobTitle));
        actions.click(jobTitle).build().perform();
        Thread.sleep(100);


        for (int i = 0; i < 200; i++) {
            title1 = StaticInstances.sbc.getDriver().findElement(By.xpath(DText)).getText();
            System.out.println(title1);
            Thread.sleep(100);
            if (title1.contains(data)) {
                StaticInstances.sbc.getDriver().findElement(By.xpath(DText)).click();
                break;
            }
            actions.sendKeys(Keys.ARROW_DOWN).build().perform();

        }

    }

    @Then("Select start Date of Application")
    public void select_candidate_name() throws InterruptedException {
         StaticInstances.sbc.getDriver().findElement(By.xpath("//label[text()='Date of Application']/../following-sibling::div//div[contains(@class,'oxd-date-input')]")).click();
       StaticInstances.sbc.getDriver().findElement(By.xpath("//label[text()='Date of Application']/../following-sibling::div//input[contains(@placeholder,'From')]")).sendKeys("1999-08-03");
        Thread.sleep(1000);

    }

    @Then("Select end Date of Application")
    public void select_end_date_of_application() throws InterruptedException {
        StaticInstances.sbc.getDriver().findElement(By.xpath("//label[text()='Date of Application']/../following-sibling::div//div[contains(@class,'oxd-date-input')]")).click();
        StaticInstances.sbc.getDriver().findElement(By.xpath("//label[text()='Date of Application']/../following-sibling::div//input[contains(@placeholder,'To')]")).sendKeys("1999-08-09");
        Thread.sleep(1000);

    }



   /* @Then("find Leave")
    public void find_leave() throws InterruptedException {
        sbc.getDriver().findElement(By.xpath("//span[text()='Leave']")).click();
        sbc.getDriver().findElement(By.xpath("//a[text()='Assign Leave']")).click();
        Thread.sleep(1000);
    }*/




    @Then("click save")
    public void click_save() throws  InterruptedException{
        WebDriverWait wait = new WebDriverWait( StaticInstances.sbc.getDriver(), Duration.ofSeconds(10));
        Wait<WebDriver> fluentwait = new FluentWait<>( StaticInstances.sbc.getDriver())
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
        WebElement myinfo =  StaticInstances.sbc.getDriver().findElement(By.xpath("//span[text()='My Info']"));
        wait.until(ExpectedConditions.elementToBeClickable(myinfo));

        myinfo.click();

        WebElement saveButton = StaticInstances.sbc.getDriver().findElement(By.xpath("(//button[@type='submit'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(saveButton));
        Thread.sleep(3000);
        By message = By.xpath("//div[@id='oxd-toaster_1']//p[contains(@class,'toast-message')]");
        saveButton.click();

        fluentwait.until(ExpectedConditions.visibilityOfElementLocated(message));
        String messagetext = StaticInstances.sbc.getDriver().findElement(message).getText();
        System.out.println("Element located");
        Thread.sleep(1000);
        if (messagetext.contains("Successfully Updated")){
            Assert.assertTrue(true);
        } else {
            Assert.fail("Success message not present");
        }

    }


    }












