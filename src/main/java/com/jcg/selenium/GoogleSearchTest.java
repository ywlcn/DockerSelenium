package com.jcg.selenium;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeOptions;

public class GoogleSearchTest extends TestBase {

    public GoogleSearchTest(MutableCapabilities capabilities) {
        super(capabilities);
    }

    // public GoogleSearchTest(){
    // super(new ChromeOptions());
    // }

    @Test
    public void openGoogle() throws IOException {
        WebDriver webDriver = getDriver();
        webDriver.navigate().to("http://www.google.com");
        Assert.assertEquals("Google", webDriver.getTitle());


    }

    // @Test
    // public void enterGoogleSearchAndViewResults() {
    //     WebDriver webDriver = getDriver();
    //     // By searchLocator = By.cssSelector("input[value='Google Search']");
    //     webDriver.navigate().to("http://www.google.com");
    //     By searchLocator = By.cssSelector("input[value='Google Search']");
    //     WebElement searchText = webDriver.findElement(By.cssSelector("input[title=Search]"));
    //     searchText.sendKeys("hi");
    //     WebElement searchButton = webDriver.findElement(searchLocator);
    //     searchButton.click();
    //     Assert.assertEquals("hi - Google 検索", webDriver.getTitle());
    // }

    // @Test
    // public void enterGoogleSearchAndImageSearch() {
    //     WebDriver webDriver = getDriver();
    //     By searchLocator = By.cssSelector("input[value='Google Search']");
    //     webDriver.navigate().to("http://www.google.com");
    //     WebElement searchText = webDriver.findElement(By.cssSelector("input[title=Search]"));
    //     searchText.sendKeys("hi");
    //     WebElement searchButton = webDriver.findElement(searchLocator);
    //     searchButton.click();
    //     WebElement imageSearch = webDriver.findElement(By.xpath("//a[contains(text(), 'Images')]"));
    //     imageSearch.click();
    // }


}
