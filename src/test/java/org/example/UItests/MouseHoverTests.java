package org.example.UItests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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



public class MouseHoverTests {


    WebDriver driver;


    @BeforeTest
    public void beforetest() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        //ops.addArguments("--start-maximized");
        //ops.addArguments("--incognito");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        //implicit wait
        //driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }



    @Test
    public void hover_test() throws InterruptedException {
        driver.get("https://www.spicejet.com/");
        Actions actions = new Actions(driver);
        WebElement spiceJet = driver.findElement(By.xpath("(//div[text()='SpiceClub'])[1]"));

        actions.moveToElement(spiceJet).build().perform();



        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'/home#program')]")));


        WebElement homeProgram = driver.findElement(By.xpath("//a[contains(@href,'/home#program')]"));

        String targetAttr = homeProgram.getAttribute("target");

        if (targetAttr.equals("_blank")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Attribute didn't match");
        }

        homeProgram.click();

       actions.keyDown(Keys.COMMAND).sendKeys(Keys.TAB).build().perform();

        //driver.findElement(By.xpath("//a[contains(@href,'/home#program')]")).click();




        Thread.sleep(5000);

    }

    @Test
    public void open_link_in_new_tab() throws InterruptedException{
        driver.get("https://www.tutorialspoint.com/about/about_careers.htm");
        String term = Keys.chord(Keys.COMMAND, Keys.ENTER);
        driver.findElement(By.xpath("//a[text()='Terms of Use']")).sendKeys(term);
        Thread.sleep(1000);

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));


        String url = driver.getCurrentUrl();

        if (url.equals("https://www.tutorialspoint.com/about/about_terms_of_use.htm")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Landed in wrong tab");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id='search-strings']")));

        driver.findElement(By.cssSelector("input[id='search-strings']")).sendKeys("Find This");

        Thread.sleep(4000);
    }

    @Test
    public void test_new_window(){
        driver.get("https://demoqa.com/browser-windows");
        String currentwindowid = driver.getWindowHandle();
        driver.findElement(By.xpath("//button[@id='windowButton']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String allwindow: driver.getWindowHandles()) {
            if (!currentwindowid.contentEquals(allwindow)){
                driver.switchTo().window(allwindow);
                break;
            }
        }

        String urlofnewwindow = driver.getCurrentUrl();
        if(urlofnewwindow.contains("https://demoqa.com/sample")){
            Assert.assertTrue(true);
        } else {
            Assert.fail("URL of child window is different");
        }

    }

    @Test
    public void hover_at_AddOns() throws InterruptedException {
        driver.get("https://www.spicejet.com/");
        Actions actions = new Actions(driver);
        WebElement spiceJet = driver.findElement(By.xpath("//div[text()='Add-ons']"));

       actions.moveToElement(spiceJet).build().perform();
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'=true')]")));


        driver.findElement(By.xpath("//a[contains(@href,'=true')]")).click();
        Thread.sleep(5000);
    }


    @Test
    public void Radio_Button() throws InterruptedException {
        driver.get("https://www.spicejet.com/");
        Actions actions = new Actions(driver);
        WebElement spiceJet = driver.findElement(By.xpath("//div[text()='Add-ons']"));

        //actions.moveToElement(spiceJet).build().perform();
        Thread.sleep(5000);

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href,'=true')]")));


        driver.findElement(By.xpath("//a[contains(@href,'=true')]")).click();
        Thread.sleep(5000);
    }






        @AfterTest
    public void aftertest() {
        driver.quit();
    }

}