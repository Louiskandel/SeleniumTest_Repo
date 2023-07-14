package org.example.UItests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {

    WebDriver driver;

    @BeforeTest
    public void beforetest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
       // driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    @Test(enabled = false)
    public void firsttest() {
        driver.get("https://www.saucedemo.com/");

        String title = driver.getTitle();

        if (title.equals("Swag Labs")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Title didn't match");
        }
    }

    @Test(priority = 1)
    public void positive_login_scenario() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[id='user-name']")).sendKeys("locked_out_user");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[id='login-button']")).click();
        //Thread.sleep(2000);
        String errortext = driver.findElement(By.xpath("//div[contains(@class,'error-message-container')]/h3[contains(text(),'Sorry, this user has been locked out.')]")).getText();
        if (errortext.contains("this user has been locked out.")){
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Text doesn't contains locked out user");
        }
        //Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void cart_scenario() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[id='login-button']")).click();
        // Thread.sleep(1000);

        for (int i=1; i <= 2; i++){
            driver.findElement(By.xpath("(//button[text()='Add to cart'])["+i+"]")).click();
            // Thread.sleep(1000);
        }

        String cartItems = driver.findElement(By.xpath("//div[@id='shopping_cart_container']//span")).getText();

        if (Integer.parseInt(cartItems) == 2){
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Cart don't have exactly 2 items");
        }
        //Thread.sleep(1000);

    }

    @Test(priority = 3)
    public void verify_total_items() throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        List<WebElement> element = driver.findElements(By.xpath("//div[@class='cart_list']/div[@class='cart_item']"));
        if(element.size() == 2){
            Assert.assertTrue(true);
        }else {
            Assert.fail("Cart don't have exactly 2 items");
        }
        // Thread.sleep(1000);
    }


    @Test(priority = 4)
    public void verify_total_amount() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
        //Thread.sleep(500);

        driver.findElement(By.xpath("//input[@id='continue']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'error-message-container')]/h3")));

       /* FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(1));
        wait.ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'error-message-container')]/h3")));*/

        String errortext = driver.findElement(By.xpath("//div[contains(@class,'error-message-container')]/h3")).getText();

        if (errortext.contains("Error: First Name is required")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Error didn't appeared");
        }


        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Rupak");
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Bha");
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("784562");
        driver.findElement(By.xpath("//input[@id='continue']")).click();
        // Thread.sleep(500);

        List<Double> inventory_price = new ArrayList<>();

        List<WebElement> element = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        for (WebElement webElement : element) {
            String price = webElement.getText().replaceAll("[a-zA-Z$]", "").trim();
            System.out.println(price);
            inventory_price.add(Double.parseDouble(price));
        }

        String final_price = driver.findElement(By.xpath("//div[contains(@class,'summary_total_label')]")).getText().replaceAll("[a-zA-Z$:]","").trim();
        inventory_price.add(Double.parseDouble(final_price));

        if((inventory_price.get(0)+inventory_price.get(1)) <= inventory_price.get(2)){
            Assert.assertTrue(true);
        }else {
            Assert.fail("Sum total is lesser than sum of individual prices");
        }
    }


    @Test(priority = 0)
    public void performance_glitch_login_scenario() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[id='user-name']")).sendKeys("performance_glitch_user");
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[id='login-button']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='app_logo' and text()='Swag Labs']")));
        //Thread.sleep(2000);
        String errortext = driver.findElement(By.xpath("//div[@class='app_logo' and text()='Swag Labs']")).getText();
        if (errortext.contains("Swag Labs")){
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Logo didn't appeared after waiting for 10 seconds");
        }
        //Thread.sleep(3000);
    }


    @AfterTest
    public void aftertest() {
        driver.quit();
    }

}