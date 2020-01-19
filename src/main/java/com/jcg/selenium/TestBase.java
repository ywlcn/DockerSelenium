package com.jcg.selenium;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.util.concurrent.TimeUnit;

@RunWith(ParallelParameterized.class)
public class TestBase {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public MutableCapabilities capabilities;

    @Parameterized.Parameters
    public static MutableCapabilities[] getBrowserCapabilities() {
        return new MutableCapabilities[]{
                new ChromeOptions(),
                 new FirefoxOptions()
        };
    }

    public TestBase(MutableCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    @Before
    public void setUp() throws Exception {
        RemoteWebDriver webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        webDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.set(webDriver);
    }

    @After
    public void tearDown() {
        getDriver().quit();
    }

    @AfterClass
    public static void remove() {
        driver.remove();
    }

    protected void copy(File fromFile, File toFile, boolean overwrite) throws IOException {
        if (fromFile == null) {
            throw new IllegalArgumentException("param[fromFile] is null");
        }
        if (toFile == null) {
            throw new IllegalArgumentException("param[fromFile] is null");
        }
        if (!fromFile.exists()) {
            throw new FileNotFoundException("There is not fromFile[" + fromFile.getAbsolutePath() + "].");
        }
        if (toFile.exists() && !overwrite) {
            throw new FileAlreadyExistsException(toFile.getAbsolutePath());
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(fromFile))) {
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(toFile))) {
                byte[] buff = new byte[1024];
                for (int size = in.read(buff); size >= 0; size = in.read(buff)) {
                    out.write(buff, 0, size);
                }
            }
        }
    }



}
