package portalPages.publisher;

import base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import portalPages.publisher.polls.PollsPage;
import utils.WaitUtils;

@Getter
@Setter
public class OnboardingPublisherPage extends BaseComponent {

    WaitUtils waitUtils;
    private PollsPage pollsPage;

    @FindBy(xpath = ".//div[@class='user-profile-name']")
    private WebElement publisherFullName;

    @FindBy(xpath = ".//li[@data-menu='polls']")
    private WebElement pollsMenuButton;

    public OnboardingPublisherPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return publisherFullName;
    }

    private boolean isPublisherlogged() {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleNow(publisherFullName);
    }

    public PollsPage openPollsPage() {
        pollsPage = new PollsPage(driver);

        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(pollsMenuButton);
        return pollsPage;
    }
}
