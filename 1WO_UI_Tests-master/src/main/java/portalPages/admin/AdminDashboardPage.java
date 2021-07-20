package portalPages.admin;

import base.BaseComponent;
import base.enums.GeneralLocators;
import base.enums.PageQAURLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import portalPages.FeedPage;
import portalPages.enums.SignLinks;
import utils.WaitUtils;

public class AdminDashboardPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(AdminDashboardPage.class);

    WaitUtils waitUtils;
    FeedPage feedPage;

    @FindBy(xpath = ".//li[contains(@class, 'input-daterange')]")
    private WebElement dateFilter;

    public AdminDashboardPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        feedPage = new FeedPage(driver);
    }

//    @BeforeClass
//    public void isPageOpened() {
//        if (!waitUtils.isElementVisibleNow(By.xpath(String.format(GeneralLocators.INPUT_BY_ID.toString(), SignLinks.SIGN_IN_SIGN_UP_BUTTON)))) {
//            driver.get(PageQAURLs.QA_PORTAL_FEED_PAGE.toString());
//        }
//    }

    @Override
    protected WebElement getMainElementInComponent() {
        return dateFilter;
    }

    public void openAdminDashboardSummaryPage() {
        if (!waitUtils.isElementVisibleNow(By.xpath(String.format(GeneralLocators.INPUT_BY_ID.toString(), SignLinks.SIGN_IN_SIGN_UP_BUTTON)))) {
            driver.get(PageQAURLs.QA_PORTAL_FEED_PAGE.toString());
            waitUtils.waitForLoading();
        }
        if (!feedPage.isMemberAuthorised()) {
            feedPage.openSignDropDown().logInAdmin();
            waitUtils.waitForLoading();
        }
        driver.get(PageQAURLs.QA_PORTAL_ADMIN_DASHBOARD_SUMMARY.toString());
    }

    public void openPartnerAccount(String partnerName) {

    }
}
