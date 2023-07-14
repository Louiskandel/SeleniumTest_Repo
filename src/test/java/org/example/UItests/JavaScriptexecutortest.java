package org.example.UItests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class JavaScriptexecutortest {
    WebDriver driver;

    @BeforeTest
    public void beforetest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        //ops.addArguments("--start-maximized");
        // ops.addArguments("--incognito");
        ops.addArguments("--disable-geolocation");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }


    @Test
    public void test_scroll() throws InterruptedException {
        driver.get("https://www.tutorialspoint.com/index.htm");
        WebElement element = driver.findElement(By.xpath("//*[text()='ABOUT US']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(3000);
    }

    @Test
    public void test_entertext() throws InterruptedException {


        driver.get("https://www.saucedemo.com/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("document.getElementById('user-name').setAttribute('value','new user')");
        WebElement element = driver.findElement(By.xpath("//input[@id='user-name']"));
        js.executeScript("arguments[0].setAttribute('value','test_user_1')", element);
        Thread.sleep(3000);


    }

    @Test
    public void Five_second_test() throws InterruptedException {
        driver.get("https://demoqa.com/dynamic-properties");

        WebElement button = driver.findElement(By.xpath("//button[@id='enableAfter']"));
        WebElement button2 = driver.findElement(By.xpath("//button[@id='visibleAfter']"));

        button.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(button));
        wait.until(ExpectedConditions.visibilityOf(button2));



    }


        @AfterTest
        public void afterTest() {



            driver.quit();
        }
    }

