package com.jcg.selenium;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import static com.codeborne.selenide.Selenide.screenshot;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class ChromeHeadLessTest {

    private static final Logger loger = LoggerFactory.getLogger(ChromeHeadLessTest.class);

    public static void main(String[] args) throws Exception {

    	loger.error("test");


        // ChromeDriver を headless モードで利用
        Configuration.timeout = 10000;
        Configuration.savePageSource = true;
        Configuration.browser = "edge";
        Configuration.headless = true;
        Configuration.driverManagerEnabled = true;
        Configuration.reportsFolder = "build/reports";
        //Configuration.browserCapabilities.setCapability("platformName", "LINUX");
        //Configuration.browserSize = "1024x768";

        //System.setProperty("webdriver.chrome.driver","./driver/msedgedriver.exe");
        //System.setProperty("webdriver.chrome.edge","./driver/msedgedriver");

        Selenide.open("https://qiita.com/kazuki-ma/items/93a317f19d6706d13858");

        WebDriver driver = WebDriverRunner.getWebDriver();

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        // get maxwidth
        int maxWidth = Integer.parseInt(String.valueOf(javascriptExecutor.executeScript(
                "return Math.max(document.body.scrollWidth,document.body.offsetWidth, document.documentElement.clientWidth, document.documentElement.scrollWidth, document.documentElement.offsetWidth);")));
        // get max height
        int maxHeight = Integer.parseInt(String.valueOf(javascriptExecutor.executeScript(
                "return Math.max(document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);")));
        // set browser size
        driver.manage().window().setSize(new Dimension(maxWidth, maxHeight));


        loger.warn(maxWidth + " " + maxHeight );
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            //screenshot("google.com");
        	File savePath = new File("screenshot.png");
            Files.copy(screenshot.toPath(), savePath.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
            try {
                Files.setPosixFilePermissions(savePath.toPath(), perms);
                System.out.println("File permissions changed successfully.");
            } catch (Exception e) {
                System.out.println("Failed to change file permissions: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // quit the driver
        driver.quit();
    }
}