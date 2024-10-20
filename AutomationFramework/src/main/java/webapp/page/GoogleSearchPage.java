package webapp.page;

import common.utils.TestUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webapp.utils.SeleniumUtility;

public class GoogleSearchPage extends SeleniumUtility {
    private static final Logger s_logger = LoggerFactory.getLogger(GoogleSearchPage.class);
    public GoogleSearchPage() {
        PageFactory.initElements(getWebDriver(),this);
    }
    @FindBy(xpath = "//*[@aria-label='Search']")
    private WebElement searchInput;


    public GoogleSearchPage enterValueInGoogleSearch(String value) {
        performSendKeys(searchInput,value,"Google search Input");
        return this;
    }
    public boolean validatePresenceOfGoogleSearchInput() {
        s_logger.info("Validating the presence of Google search Input box");
        boolean status = TestUtility.validateElementPresence.test(searchInput);
        s_logger.info("Validated the presence of Google search Input box");
        return status;
    }
}
