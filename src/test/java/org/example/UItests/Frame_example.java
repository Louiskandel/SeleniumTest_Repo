package org.example.UItests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
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

public class Frame_example {
    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
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
    public void test_frame(){

        driver.get("https://demoqa.com/frames");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frame1"));

        String framecontent = driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText();

        System.out.println(framecontent);

        driver.switchTo().defaultContent();

        driver.switchTo().frame("frame2");

        String framecontent2 = driver.findElement(By.xpath("//h1[@id='sampleHeading']")).getText();

        System.out.println(framecontent2);
        driver.switchTo().defaultContent();


    }

    @Test
    public void nested_frame(){
        driver.get("https://demoqa.com/nestedframes");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("frame1"));

        String framecontent = driver.findElement(By.xpath("//body")).getText();

        System.out.println(framecontent);

        WebElement childframe  = driver.findElement(By.xpath("//iframe[@srcdoc]"));

        driver.switchTo().frame(childframe);

        String childframecontent = driver.findElement(By.xpath("//body")).getText();

        System.out.println(childframecontent);

        driver.switchTo().defaultContent();

        driver.switchTo().parentFrame();


    }




    @AfterTest
    public void aftertest() {

        driver.quit();
    }


}
