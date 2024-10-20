package webapp.Google;

import common.utils.ExtentReportUtil;
import data.DataProviderUtils;
import org.testng.annotations.Test;
import webapp.page.GoogleSearchPage;
import webapp.utils.SeleniumUtility;

public class GoogleSearchTests  extends SeleniumUtility {
    @Test
    public void validateGoogleSearch() {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage();

        String testStep = "Validate Google search page is launched";
        boolean status=googleSearchPage.validatePresenceOfGoogleSearchInput();
        takeScreenshot();
        ExtentReportUtil.updateReportInfo(status,testStep);
    }
    @Test(dataProvider = "DataProvider_web",dataProviderClass = DataProviderUtils.class)
    public void validateGoogleSearchInput(String searchKeyword,String info) {
        GoogleSearchPage googleSearchPage = new GoogleSearchPage();

        String testStep = "Validate Google search page is launched";
        boolean status=googleSearchPage.validatePresenceOfGoogleSearchInput();
        ExtentReportUtil.updateReportInfo(status,testStep);

        googleSearchPage.enterValueInGoogleSearch(searchKeyword);
        ExtentReportUtil.updateReportInfo(true,"Search value enter:- "+searchKeyword);
    }
}
