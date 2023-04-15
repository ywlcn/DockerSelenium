package com.jcg.selenium;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumHubAccessChrome {
    public static void main(String[] args) throws Exception {
        // set the desired capabilities for the browser and operating system you want to
        // use
        ChromeOptions capabilities = new ChromeOptions();
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("platform", "Linux");

        // set the URL of the Selenium hub
        URL hubUrl = new URL("http://192.168.56.101:30007/wd/hub");

        // create a new RemoteWebDriver instance to connect to the hub
        WebDriver driver = new RemoteWebDriver(hubUrl, capabilities);

        // navigate to a web page
        driver.get("https://qiita.com/nyakome/items/48e4d327b39aaf4ddeaf");

        // do something with the page, e.g. print the title
        System.out.println(driver.getTitle());

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        // get maxwidth
        int maxWidth = Integer.parseInt(String.valueOf(javascriptExecutor.executeScript(
                "return Math.max(document.body.scrollWidth,document.body.offsetWidth, document.documentElement.clientWidth, document.documentElement.scrollWidth, document.documentElement.offsetWidth);")));
        // get max height
        int maxHeight = Integer.parseInt(String.valueOf(javascriptExecutor.executeScript(
                "return Math.max(document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);")));
        // set browser size
        driver.manage().window().setSize(new Dimension(maxWidth, maxHeight));
        // 截图
        // take a screenshot and save it to a file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), new File("screenshot.png").toPath(), StandardCopyOption.REPLACE_EXISTING);

        // quit the driver
        driver.quit();
    }
}