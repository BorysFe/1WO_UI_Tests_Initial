package portalPages.publisher;

import base.BaseComponent;
import base.enums.PageQAURLs;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import portalPages.publisher.polls.PollsPage;
import utils.WaitUtils;

@Getter
public class PublisherLoginPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(PublisherLoginPage.class);

    WaitUtils waitUtils;
    private OnboardingPublisherPage onboardingPage;
    private PollsPage pollsPage;

    @FindBy(xpath = ".//input[@id= 'signin-email']")
    private WebElement publisherEmailField;

    @FindBy(xpath = ".//input[@id= 'signin-pswd']")
    private WebElement publisherPasswordField;

    @FindBy(xpath = ".//input[@id= 'sign-in-btn']")
    private WebElement publisherLoginButton;

    @FindBy(xpath = ".//li[@data-menu='polls']")
    private WebElement pollsMenuButton;

    @FindBy(xpath = ".//label[@id= 'signin-email-error']")
    private WebElement publisherEmailFieldError;

    @FindBy(xpath = ".//label[@id= 'signin-pswd-error']")
    private WebElement publisherPasswordFieldError;

    public PublisherLoginPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return publisherEmailField;
    }

    @BeforeClass
    public PublisherLoginPage getPublisherLoginPage() {
        logger.info("Opening Publisher Login page");
        driver.get(PageQAURLs.QA_PORTAL_LOGIN_PARTNER.toString());
        waitUtils.waitForLoading();

        return this;
    }

    public void loginPublisher(String login, String password) {
        waitUtils.waitForLoading();
        if (login != null) {
            waitUtils.waitForElementToBeDisplayedAfterShortWait(publisherEmailField).sendKeys(login);
        } else {

            if (login != null) {
                waitUtils.waitForElementToBeDisplayedAfterShortWait(publisherPasswordField).sendKeys(password);
            }
        }
        waitUtils.clickWhenReadyAfterMiddleWait(publisherLoginButton);
        waitUtils.waitForLoading();
    }

    public OnboardingPublisherPage openOnboardingPage(String login, String password) {
        onboardingPage = new OnboardingPublisherPage(driver);

        loginPublisher(login, password);
        waitUtils.waitForLoading();

        return onboardingPage;
    }

    public PollsPage openPollsPage(String login, String password) {
        pollsPage = new PollsPage(driver);

        loginPublisher(login, password);

        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(pollsMenuButton);

        return pollsPage;
    }

    public boolean isPublisherLoginErrorDisplayed() {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleNow(publisherEmailFieldError);
    }

    public boolean isPublisherPasswordErrorDisplayed() {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleNow(publisherPasswordFieldError);
    }

    public String getPublisherLoginErrorText() {
        waitUtils.waitForLoading();
        return waitUtils.getElementWhenVisibleAfterShortWait(publisherEmailFieldError).getText();
    }

    public String getPublisherPasswordErrorText() {
        waitUtils.waitForLoading();
        return waitUtils.getElementWhenVisibleAfterShortWait(publisherPasswordFieldError).getText();
    }
}
