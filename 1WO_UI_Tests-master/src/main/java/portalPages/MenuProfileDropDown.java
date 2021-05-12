package portalPages;

import base.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.admin.AdminDashboardPage;
import utils.WaitUtils;

@Getter
public class MenuProfileDropDown extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(MenuProfileDropDown.class);

    WaitUtils waitUtils;
    private AdminDashboardPage adminDashboardPage;

    public MenuProfileDropDown(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    private final String inputElement = ".//input[@id='%s']";
    private final String spanElement = ".//span[@id='%s']";

    @FindBy(xpath = ".//div[@id='profileDropDownMenu']")
    private WebElement profileDropDownMenu;

    @FindBy(xpath = ".//a[@class='db-link']")
    private WebElement adminDashboardButton;

    @FindBy(xpath = ".//div[@id='show-menu-profile']//div")
    private WebElement menuProfileButton;

    @FindBy(xpath = ".//a[contains(@class, 'sign-out-link')]")
    private WebElement logOutButton;

    @Override
    protected WebElement getMainElementInComponent() {
        return adminDashboardButton;
    }

    public AdminDashboardPage openAdminDashboardPage() {
        adminDashboardPage = new AdminDashboardPage(driver);
        waitUtils.waitForLoading();

        adminDashboardButton.click();
        logger.info("Admin Dashboard opening");
        waitUtils.waitForLoading();

        return adminDashboardPage;
    }

    public void logOut() {
        openMenuProfile();
        waitUtils.clickWhenReadyAfterShortWait(logOutButton);
        logger.info("LogOut from Account");
    }

    public void openMenuProfile() {
        waitUtils.waitForLoading();
        if (!isMenuProfileDropDownOpened()) {
            waitUtils.clickWhenReadyAfterShortWait(menuProfileButton);
        }
    }

    private boolean isMenuProfileDropDownOpened() {
        return waitUtils.isElementVisibleNow(profileDropDownMenu);
    }
}