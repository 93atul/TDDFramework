package mobile.DemoApp;

import common.utils.ExtentReportUtil;
import mobile.page.DemoAppPage;
import mobile.utils.AppiumUtils;
import org.testng.annotations.Test;

public class DemoAppTests extends AppiumUtils {

    @Test
    public void demoAppTest() {
        DemoAppPage demoAppPage = new DemoAppPage();
        boolean status = false;

        String stepName = "Validate App Launch";
        status = demoAppPage.validatePresenceOfContentTab();
        ExtentReportUtil.updateReportInfo(status,stepName);

        stepName = "Validate Content Tab Sub Categories";
        status = demoAppPage.clickOnContentTab().validateContentTabContents();
        ExtentReportUtil.updateReportInfo(status,stepName);

    }
}
