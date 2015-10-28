package com.dmall.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.steadystate.css.util.Output;

public class AndroidWebViewTest {
    private AppiumDriver<WebElement> driver;

    @Before
    public void setUp() throws Exception {
        // set up appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void webView() throws InterruptedException {
    	takeScreenshot("homepage");
        WebElement button = driver.findElement(By.id("buttonStartWebview"));
        button.click();
        Thread.sleep(6000);
        
    	takeScreenshot("webview page");
        
    	Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
          System.out.println(contextName);
          if (contextName.contains("WEBVIEW")){
            driver.context(contextName);
          }
        }
//        WebElement inputField = driver.findElement(By.id("name_input"));
//        inputField.sendKeys("Some name");
//        inputField.submit();
    }
    
    public boolean takeScreenshot(final String name) {
    	String screenshotDirectory = System.getProperty("appium.screenshots.dir",
    	 System.getProperty("java.io.tmpdir", ""));
    	       File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	       return screenshot.renameTo(new File(screenshotDirectory,
    	String.format("%s.png", name)));
    	   }

}
