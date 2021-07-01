package portal.admin.dashboard;

import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import portalPages.FeedPage;
import portalPages.MenuProfileDropDown;
import portalPages.admin.AdminDashboardPage;
import utils.DriverFactory;

public class SummaryTabTest extends DriverFactory {

    FeedPage feedPage;
    AdminDashboardPage adminDashboardPage;
    MenuProfileDropDown menuProfileDropDown;

    @BeforeMethod
    public void pages() {
        feedPage = new FeedPage(driver);
        adminDashboardPage = new AdminDashboardPage(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);
    }

    @AfterMethod
    public void logOutIfNeed() {
            menuProfileDropDown.tryLogOut();
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void openAdminDashboardSummeryTab() {
        adminDashboardPage.openAdminDashboardSummaryPage();

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Admin not authorised")
                .isTrue();
    }
}
