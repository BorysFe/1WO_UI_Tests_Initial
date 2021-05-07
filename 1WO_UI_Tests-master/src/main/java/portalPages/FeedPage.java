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
    private SignIn_SignUp_Popup signPopup;

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

    public SignIn_SignUp_Popup openSignPopup() {
        signPopup = new SignIn_SignUp_Popup(driver);

        if (!waitUtils.isElementVisibleNow(profileDropDownMenu)) {
            waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(spanElement, SignLinks.SIGN_IN_SIGN_UP_BUTTON)));
        }

        return signPopup;
    }

    public boolean isMemberAuthorised() {
        return waitUtils.isElementVisibleNow(menuProfileButton);
    }
}
