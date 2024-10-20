package mobile.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.URL;
import java.util.Date;

public class AppiumUtils {
    public static final Logger s_logger = LoggerFactory.getLogger(AppiumUtils.class);
    public DesiredCapabilities cap;
    public AppiumServiceBuilder builder;
    public AppiumDriverLocalService service;
    public static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();
    @BeforeMethod(alwaysRun = true)
    @Parameters({"platformName","appName","noReset","deviceName"})
    public void startServer(String platformName, String appName, String noReset, String deviceName) throws Exception {
        //Set Capabilities
        cap = new DesiredCapabilities();
        cap.setCapability("platformName",platformName);
        UiAutomator2Options option = new UiAutomator2Options();
        option.setDeviceName(deviceName);
        option.setApp(appName);
        option.setAutomationName("uiautomator2");
        option.setNoReset(Boolean.parseBoolean(noReset));
        //Build the Appium service
        builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4724);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        Thread.sleep(50000);
        appiumDriver.set(new AndroidDriver(new URL("http://127.0.0.1:4724"),option));
        s_logger.info("Driver initiated");
    }
    public AppiumDriver getAppiumDriver() {
        return appiumDriver.get();
    }
    @AfterMethod
    public void stopServer() {
        getAppiumDriver().quit();
        appiumDriver.remove();
        s_logger.info("Closing the AppiumDriver Instance");
        service.stop();
        s_logger.info("Closing the Appium Service");
    }
    public AppiumUtils performClick(WebElement element, String elementName) {
        s_logger.info("Clicking on the {}",elementName);
        element.click();
        s_logger.info("Clicked on the {}",elementName);
        takeScreenshot();
        return this;
    }
    public AppiumUtils takeScreenshot() {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) getAppiumDriver());
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(System.getProperty("user.dir")+File.separator+"screenshots"+File.separator+"shot_"+new Date().toString().replace(" ","_").replace(":","_")+".jpg");
            FileUtils.copyFile(SrcFile, DestFile);
        }catch(Exception ex){
            s_logger.info("Unable to capture screenshot");
        }
        return this;
    }
}
