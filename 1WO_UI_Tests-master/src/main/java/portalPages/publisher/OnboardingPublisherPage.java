package portalPages.publisher;

import base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.polls.polls.PollsPage;
import portalPages.surveys.SurveysPage;
import utils.WaitUtils;

@Getter
@Setter
public class OnboardingPublisherPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(OnboardingPublisherPage.class);

    WaitUtils waitUtils;
    private PollsPage pollsPage;
    private SurveysPage surveysPage;

    @FindBy(xpath = ".//div[@class='user-profile-name']")
    private WebElement publisherFullName;

    @FindBy(xpath = ".//li[@data-menu='polls']")
    private WebElement pollsMenuButton;

    @FindBy(xpath = ".//li[@data-menu='surveys']")
    private WebElement surveysMenuButton;

    public OnboardingPublisherPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        pollsPage = new PollsPage(driver);
        surveysPage = new SurveysPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {

        return publisherFullName;
    }

    private boolean isPublisherAuthorised() {
        waitUtils.waitForLoading();

        return waitUtils.isElementVisibleNow(publisherFullName);
    }

    public PollsPage openPollsPage() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterMiddleWait(pollsMenuButton);
        waitUtils.waitForLoading();

        return pollsPage;
    }

    public SurveysPage openSurveysPage() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(surveysMenuButton);
        waitUtils.waitForLoading();

        return surveysPage;
    }
}
