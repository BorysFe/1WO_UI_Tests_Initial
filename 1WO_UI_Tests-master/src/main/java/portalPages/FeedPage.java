package portalPages;

import base.BaseComponent;
import base.enums.PageURLs;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import portalPages.enums.SignLinks;
import utils.WaitUtils;

@Getter
public class FeedPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(FeedPage.class);

    WaitUtils waitUtils;

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

    @FindBy(xpath = ".//ul[@class='user-profile-wrapper']//div//div")
    private WebElement menuProfileButton;

    @FindBy(xpath = ".//div[@class='header-search-block']")
    private WebElement pollSearch;

    @FindBy(xpath = ".//a[contains(@class, 'sign-out-link')]")
    private WebElement logOutButton;

    @Override
    protected WebElement getMainElementInComponent() {
        return menuProfileButton;
    }

    @BeforeTest
    public FeedPage getFeedPage() {
        logger.info("Opening Feed page");
        driver.get(PageURLs.PORTAL_FEED_PAGE.toString());
        waitUtils.waitForElementToBeDisplayed(pollSearch, 120);
        waitUtils.waitForLoading();
        return this;
    }

    public void openLoginPopUp() {
        getFeedPage();
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(spanElement, SignLinks.SIGN_IN_SIGN_UP_BUTTON)));
        logger.info("Opening SignIn popup");
        waitUtils.waitForLoading();
    }

    public void logInMember(String logIn, String password) {
        waitUtils.waitForLoading();
        openLoginPopUp();
        setField(By.xpath(String.format(inputElement, SignLinks.SIGN_IN_LOGIN_FIELD)), logIn);
        setField(By.xpath(String.format(inputElement, SignLinks.SIGN_IN_PASSWORD_FIELD)), password);
        logger.info("Set LogIn fields with credentials: " + logIn + ", " + password);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(inputElement, SignLinks.SIGN_IN_SIGN_IN_BUTTON)));
        logger.info("LogIn process");
    }

    public boolean isAuthenticationErrorDisplayed() {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleAfterMiddleWait(errorMessageAuthenticationFailed);
    }

    public void registrationMember(String newLogIn, String newPassword) {
        waitUtils.waitForLoading();
        openLoginPopUp();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(spanElement, SignLinks.SIGN_IN_CREATE_ACCOUNT_BUTTON)));
        waitUtils.waitForLoading();

        logger.info("Set SignUp fields with credentials: " + newLogIn + ", " + newPassword);
        setField(By.xpath(String.format(inputElement, SignLinks.SIGN_UP_EMAIL_FIELD)), newLogIn);
        setField(By.xpath(String.format(inputElement, SignLinks.SIGN_UP_PASSWORD_FIELD)), newPassword);
        setField(By.xpath(String.format(inputElement, SignLinks.SIGN_UP_RESTORE_PASSWORD_FIELD)), newPassword);

        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(inputElement, SignLinks.SIGN_UP_SUBMIT_BUTTON)));
        logger.info("SignUp process");
        waitUtils.waitForLoading();
    }

    public boolean isMemberAuthorised() {
        return waitUtils.isElementVisibleNow(menuProfileButton);
    }

    void setField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    void setField(By element, String text) {
        waitUtils.waitVisibilityOfElementByShort(element);
        driver.findElement(element).clear();
        waitUtils.waitVisibilityOfElementByShort(element);
        driver.findElement(element).sendKeys(text);
    }

    public void openMenuProfile() {
        waitUtils.waitForLoading();
        if (!isMenuProfileDropDownOpened()) {
            waitUtils.clickWhenReadyAfterShortWait(menuProfileButton);
        }
    }

    public void logOut() {
        openMenuProfile();
        waitUtils.clickWhenReadyAfterShortWait(logOutButton);
        logger.info("LogOut from Account");
    }

    private boolean isMenuProfileDropDownOpened() {
        return waitUtils.isElementVisibleNow(profileDropDownMenu);
    }
}
