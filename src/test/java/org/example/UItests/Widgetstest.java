package org.example.UItests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Widgetstest {
    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        String downloadFilepath = "/Users/louis/Desktop/Selenium_Repo/SeleniumTest_Repo";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        String directory = System.getProperty("user.dir") + "/";
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
    public void test_sliders() throws InterruptedException {
        driver.get("https://demoqa.com/slider");
        Actions actions = new Actions(driver);
        WebElement slider = driver.findElement(By.xpath("//input[contains(@class,'range-slider')]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(slider));

        actions.click(slider).build().perform();

        for (int i = 0; i < 5; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT).build().perform();
            Thread.sleep(500);
        }

        Thread.sleep(3000);
    }

    @Test
    public void test_drag_and_drop() throws InterruptedException {
        driver.get("https://demoqa.com/droppable");
        Actions actions = new Actions(driver);

        WebElement drag = driver.findElement(By.xpath("//div[@id='draggable']"));

        WebElement drop = driver.findElement(By.xpath("//div[@id='draggable']/following-sibling::div"));

        //actions.clickAndHold(drag).moveToElement(drop).release(drop).build().perform();

        actions.dragAndDrop(drag, drop).perform();

        Thread.sleep(5000);
    }

    @Test
    public void test_button_color() throws InterruptedException {
        driver.get("https://demoqa.com/dynamic-properties");

        WebElement button = driver.findElement(By.xpath("//button[@id='colorChange']"));
        String colorBefore = button.getCssValue("color");
        System.out.println(colorBefore);


        button.click();

        String colorAfter = button.getCssValue("color");
        System.out.println(colorAfter);

        assert colorBefore != colorAfter;


    }

    @Test
    public void test_download() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");

        WebElement button = driver.findElement(By.xpath("//a[@id='downloadButton']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(button));

        button.click();

        Thread.sleep(5000);
    }
   /* @Test
    public void test_upload() throws InterruptedException {
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.xpath("")).sendKeys("/Users/louis/Desktop/Selenium_Repo/SeleniumTest_Repo/downloads");
        String uploadtext = driver.findElement(By.xpath("//p[@id='uploadedFilePath']")).getText();

        if (uploadtext.contains("sampleFile.jpeg")){
            Assert.assertTrue(true);
        }else {
            Assert.fail("File not uploaded!!");
        }
    }
    */



    @Test
    public void test_autocomplete() throws InterruptedException {
        driver.get("https://jqueryui.com/autocomplete/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        Thread.sleep(2000);

        driver.findElement(By.xpath("//input[@id='tags']")).sendKeys("#");

        String texttobeclicked = "Clojure";
        WebElement autooptions = autooptions = driver.findElement(By.id("ui-id-1"));

        try {
            wait.until(ExpectedConditions.visibilityOf(autooptions));
        } catch (TimeoutException e) {
            System.out.println("No element present with the character");
        }

        List<WebElement> alloptions = autooptions.findElements(By.tagName("li"));


        for (WebElement option : alloptions) {
            if (option.getText().equals(texttobeclicked)) {
                option.click();
                break;
            }
        }



    }


        @AfterTest
        public void aftertest() {

            driver.quit();
        }
    }




