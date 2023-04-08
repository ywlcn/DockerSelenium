package com.jcg.selenium;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumHubAccess {
    public static void main(String[] args) throws Exception {
        // set the desired capabilities for the browser and operating system you want to
        // use
        // set the desired capabilities for Edge browser
        EdgeOptions options = new EdgeOptions();
        options.setCapability("platformName", "linux");
        options.setCapability("browserName", "MicrosoftEdge");

        // set the URL of the Selenium hub
        URL hubUrl = new URL("http://192.168.56.101:30007/wd/hub");

        // create a new RemoteWebDriver instance to connect to the hub
        WebDriver driver = new RemoteWebDriver(hubUrl, options);

        // navigate to a web page
        driver.get("https://qiita.com/nyakome/items/48e4d327b39aaf4ddeaf");

        // take a screenshot and save it to a file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), new File("screenshot.png").toPath(), StandardCopyOption.REPLACE_EXISTING);

        // do something with the page, e.g. print the title
        System.out.println(driver.getTitle());

        // quit the driver
        driver.quit();
    }
}