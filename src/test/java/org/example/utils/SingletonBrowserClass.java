package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class SingletonBrowserClass {
    private static SingletonBrowserClass instanceOfSingletonBrowserClass = null;

    private final WebDriver driver;
    public final static int TIMEOUT = 10;

    private SingletonBrowserClass(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions ops = new ChromeOptions();
        ops.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        ops.addArguments("--remote-allow-origins=*");
        ops.addArguments("--start-maximized");
        ops.addArguments("--incognito");
        ops.addArguments("--disable-geolocation");
        ops.setExperimentalOption("useAutomationExtension", false);
        ops.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        driver = new ChromeDriver(ops);
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

    public static SingletonBrowserClass getInstanceOfSingletonBrowserClass() {
        if (instanceOfSingletonBrowserClass == null){
            instanceOfSingletonBrowserClass = new SingletonBrowserClass();
        }

        return instanceOfSingletonBrowserClass;
    }

    public WebDriver getDriver(){
        return driver;
    }
}