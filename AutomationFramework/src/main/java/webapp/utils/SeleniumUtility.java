package webapp.utils;

import common.utils.ExtentReportUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;
import java.util.function.Predicate;


public class SeleniumUtility {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    public static final Logger s_logger = LoggerFactory.getLogger(SeleniumUtility.class);
    public WebDriver getWebDriver() {
        return driver.get();
    }
    @BeforeMethod(alwaysRun = true)
    @Parameters("launchUrl")
    public SeleniumUtility setUpDriver(String launchUrl) {
        s_logger.info("Initializing the ChromeDriver");
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
        launchWebPage(launchUrl);
        return this;
    }
    @AfterMethod
    public SeleniumUtility closeBrowser() {
        s_logger.info("Closing the Browser");
        getWebDriver().quit();
        return this;
    }
    public SeleniumUtility performSendKeys(WebElement element, String value,String elementName) {
        s_logger.info("Entering the value as : "+value +"in "+elementName );
        element.sendKeys(value);
        s_logger.info("Entered the value as : "+value +"in "+elementName );
        takeScreenshot();
        return this;
    }
    public SeleniumUtility performClick(WebElement element,String elementName) {
        s_logger.info("Clicking on the {}",elementName);
        element.click();
        s_logger.info("Clicked on the {}",elementName);
        takeScreenshot();
        return this;
    }
    public SeleniumUtility takeScreenshot() {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) getWebDriver());
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(ExtentReportUtil.screenshotPath.get()+File.separator+"shot_"+new Date().toString().replace(" ","_").replace(":","_")+".jpg");
            FileUtils.copyFile(SrcFile, DestFile);
        }catch(Exception ex){
            s_logger.info("Unable to capture screenshot in page:- "+getWebDriver().getCurrentUrl());
        }
        return this;
    }
    public SeleniumUtility hoverAction(WebElement element) {
        Actions action = new Actions(getWebDriver());
        action.moveToElement(element).perform();
        takeScreenshot();
        return this;
    }
    public SeleniumUtility launchWebPage(String url) {
        s_logger.info("Launching url: "+url);
        getWebDriver().get(url);
        s_logger.info("Successfully launched url");
        getWebDriver().manage().window().maximize();
        return this;
    }
    Predicate<WebDriver> pageLoadWait = webDriver->((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
    public SeleniumUtility waitTillPageLoad() {
        new WebDriverWait(getWebDriver(),Duration.ofSeconds(15)).until(
                webDriver -> pageLoadWait.test(webDriver));
        return this;
    }
    public String getText(WebElement element) {
        String text = element.getText().trim();
        s_logger.info("Extracted Text as : {} ",text);
        return text;
    }
}