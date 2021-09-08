package welcomePages;

import base.BaseComponent;
import base.enums.PageURLs;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.publisher.PublisherLoginPage;
import utils.WaitUtils;

@Getter
public class WelcomePage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(WelcomePage.class);

    WaitUtils waitUtils;
    PublisherLoginPage publisherLoginPage;

    private final String SignUpSignInLink = ".//div[@id='navbarContent']//a[contains(text(),'%s')]";
    private final String SignUpSignInPopup = ".//div[@id='navbarContent']//img[contains(@src, '%s')]//..";

    @FindBy(xpath = ".//button[contains(text(),'Sign up')]")
    private WebElement signUpButton;

    @FindBy(xpath = ".//a[contains(text(),'Sign In')]")
    private WebElement signInButton;

    @FindBy(xpath = ".//img[contains(@src,'mobile')]")
    private WebElement sandwichMenu;

    public WelcomePage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(SignUpSignInLink, WelcomeLinks.PUBLISHER)));
    }

    public WelcomePage openWelcomePage() {
        waitUtils.waitForLoading();
        driver.get(PageURLs.WELCOME_PAGE.toString());
        return this;
    }

    private void openSignInPopup() {
        waitUtils.waitForLoading();
        if (waitUtils.isElementVisibleNow(sandwichMenu)) {
            waitUtils.clickWhenReadyAfterShortWait(sandwichMenu);
        } else {
            waitUtils.clickWhenReadyAfterShortWait(signInButton);
        }
    }

    public PublisherLoginPage openPublisherLoginPage() {
        publisherLoginPage = new PublisherLoginPage(driver);

        openSignInPopup();
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(signInButton);

        //From QAWelcomePage wrong redirect to PROD PublisherLoginPage.
        if (driver.getCurrentUrl() != PageURLs.PORTAL_LOGIN_PARTNER.toString()) {
            publisherLoginPage.getPublisherLoginPage();
        }

        return publisherLoginPage;
    }
}
