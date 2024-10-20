package mobile.page;

import common.utils.TestUtility;
import mobile.utils.AppiumUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webapp.utils.SeleniumUtility;

import java.util.List;

import static data.Constants.MobileData.CONTENT_TAB_CONTENT;

public class DemoAppPage extends AppiumUtils {
    public static final Logger s_logger = LoggerFactory.getLogger(DemoAppPage.class);
    public SeleniumUtility selUtil = new SeleniumUtility();
    public DemoAppPage() {
        PageFactory.initElements(getAppiumDriver(),this);
    }

    @FindBy(xpath = "//*[@text='Content']")
    private WebElement contentTab;
    @FindBy(xpath = "//*[contains(@resource-id,'id/text1')]")
    private List<WebElement> subTabContentsElement;

    public boolean validatePresenceOfContentTab() {
        s_logger.info("Validating the presence of Content Tab");
        boolean status = TestUtility.validateElementPresence.test(contentTab);
        s_logger.info("Validated the presence of Content Tab as : {}",status);
        return status;
    }
    public DemoAppPage clickOnContentTab() {
        performClick(contentTab,"Content Tab");
        return this;
    }
    public boolean validateContentTabContents() {
        s_logger.info("Validating the Contents Of contents sub Category");
        boolean status = subTabContentsElement.stream().map(item->selUtil.getText(item)).allMatch(CONTENT_TAB_CONTENT::contains);
        s_logger.info("Validated the contents of Contents Sub category as : {}",status);
        return status;
    }

}
