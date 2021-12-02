package icoPages;

import base.AccountsInfoPage;
import base.BaseComponent;
import base.enums.GeneralLocators;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

@Getter
@Setter
public class ProfilePage extends BaseComponent {

    WaitUtils waitUtils;
    DashboardPage dashboardPage;

    public ProfilePage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    public DashboardPage openDashboardPage() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterMiddleWait(By.xpath(String.format(GeneralLocators.A_BY_CONTAINS_TEXT.toString(), "Dashboard")));

        return dashboardPage;
    }
}
