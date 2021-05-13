package portal.admin.dashboard;

import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import portalPages.FeedPage;
import portalPages.admin.AdminDashboardPage;
import utils.DriverFactory;

public class SummaryTabTest extends DriverFactory {

    FeedPage feedPage;
    AdminDashboardPage adminDashboardPage;

    @BeforeMethod
    public void pages() {
        feedPage = new FeedPage(driver);
        adminDashboardPage = new AdminDashboardPage(driver);
    }

    @Test
    public void openAdminDashboardSummeryTab() {
        adminDashboardPage.openAdminDashboardSummaryPage();

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Admin not authorised")
                .isTrue();
    }
}
