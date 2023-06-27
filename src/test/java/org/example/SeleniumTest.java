package org.example;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumTest {

    WebDriver driver;

    @BeforeTest
    public void beforetest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);
    }

    @Test(priority = 1)
    public void firsttest() {
        driver.get("https://www.saucedemo.com/");
        String title = driver.getTitle();
        if (title.equals("Swag Labs")) {
            Assert.assertTrue(true);
            System.out.println("Title Matches!");
        } else {
            Assert.fail("title didn't match");
        }
    }
    @Test(priority = 2)
    public void secondtest() {
        driver.get("https://www.saucedemo.com/");
        String url = driver.getCurrentUrl();
        if (url.contains("https://www.saucedemo.com")) {
            Assert.assertTrue(true);
            System.out.println("URL worked");
        } else {
            Assert.fail("URL didn't match");
        }
    }


    @AfterTest
    public void aftertest() {
        driver.quit();
    }




    }
