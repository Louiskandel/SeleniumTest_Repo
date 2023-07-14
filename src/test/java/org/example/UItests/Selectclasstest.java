package org.example.UItests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Selectclasstest {

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
    public void test_select_class()  {
        driver.get("https://demoqa.com/select-menu");

        WebElement selectcolor = driver.findElement(By.xpath("//select[@id='oldSelectMenu']"));

        Select se = new Select(selectcolor);

        se.selectByVisibleText("Purple");
    }

    @Test
    public void test_alerts() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");

        driver.findElement(By.xpath("//button[@id='alertButton']")).click();

Thread.sleep(3000);
        System.out.println(driver.switchTo().alert().getText());


driver.switchTo().alert().dismiss();
    }

    @AfterTest
    public void aftertest() {

        driver.quit();
    }

}