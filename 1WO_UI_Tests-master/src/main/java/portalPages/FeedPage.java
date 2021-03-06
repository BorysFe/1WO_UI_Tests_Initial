package portalPages;

import base.BaseComponent;
import base.enums.PageURLs;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import portalPages.enums.SignLinks;
import utils.WaitUtils;

@Getter
@Setter
public class FeedPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(FeedPage.class);

    WaitUtils waitUtils;
    private SignIn_SignUp_DropDown signDropDown;
    private MenuProfileDropDown menuProfileDropDown;

    public FeedPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    private final String inputElement = ".//input[@id='%s']";
    private final String spanElement = ".//span[@id='%s']";

    @FindBy(xpath = ".//div[@id='profileDropDownMenu']")
    private WebElement profileDropDownMenu;

    @FindBy(xpath = ".//label[@id='login-error']")
    private WebElement errorMessageAuthenticationFailed;

    @FindBy(xpath = ".//img[@data-type='show-menu-profile']")
    private WebElement menuProfileButton;

    @FindBy(xpath = ".//div[@class='header-search-block']")
    private WebElement pollSearch;

    @FindBy(xpath = ".//a[@class='db-link']")
    private WebElement adminDashboardButton;

    @Override
    protected WebElement getMainElementInComponent() {
        return menuProfileButton;
    }

    @BeforeMethod
    public FeedPage getFeedPage() {
        logger.info("Opening Feed page");
        driver.get(PageURLs.PORTAL_FEED_PAGE.toString());
        waitUtils.waitForLoading();
        return this;
    }

    public SignIn_SignUp_DropDown openSignDropDown() {
        waitUtils.waitForLoading();
        signDropDown = new SignIn_SignUp_DropDown(driver);
        waitUtils.waitForLoading();

        if (!waitUtils.isElementVisibleAfterShortWait(profileDropDownMenu)) {
            waitUtils.waitForLoading();
            waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(spanElement, SignLinks.SIGN_IN_SIGN_UP_BUTTON)));
        }
        waitUtils.waitForLoading();
        return signDropDown;
    }

    public void openSignDropdown() {
        if (!waitUtils.isElementVisibleAfterShortWait(profileDropDownMenu)) {
            waitUtils.waitForLoading();
            waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(spanElement, SignLinks.SIGN_IN_SIGN_UP_BUTTON)));
        }
        waitUtils.waitForLoading();
    }

    public MenuProfileDropDown openMenuProfileDropDown() {
        menuProfileDropDown = new MenuProfileDropDown(driver);

        if (!waitUtils.isElementVisibleNow(profileDropDownMenu)) {
            waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(spanElement, SignLinks.SIGN_IN_SIGN_UP_BUTTON)));
        }

        return menuProfileDropDown;
    }

    public boolean isMemberAuthorised() {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleAfterShortWait(menuProfileButton);
    }

    public void logOutIfNeed() {
        if (isMemberAuthorised()) {
            System.out.println("Member is authorised, need to LogOut");
            menuProfileDropDown.logOut();
        }
    }
}
