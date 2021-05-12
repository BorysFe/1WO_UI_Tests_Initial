package portalPages.partners;

import base.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.admin.AdminDashboardPage;
import utils.WaitUtils;

public class PartnersSummaryTab extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(AdminDashboardPage.class);

    WaitUtils waitUtils;

    @FindBy(xpath = ".//li[contains(@class, 'input-daterange')]")
    private WebElement dateFilter;

    public PartnersSummaryTab(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return dateFilter;
    }
}
