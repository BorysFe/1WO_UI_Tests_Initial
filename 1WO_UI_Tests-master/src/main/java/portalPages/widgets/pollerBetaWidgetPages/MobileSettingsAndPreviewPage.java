package portalPages.widgets.pollerBetaWidgetPages;

import base.AccountsInfoPage;
import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.polls.polls.PollsPage;
import utils.WaitUtils;

public class MobileSettingsAndPreviewPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(MobileSettingsAndPreviewPage.class);

    WaitUtils waitUtils;
    PollsPage pollsPage;

    public MobileSettingsAndPreviewPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {

        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Finish")));
    }

    public PollsPage finishButtonClick() {
        pollsPage = new PollsPage(driver);

        try {
            waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Finish")));
            logger.info("Finishing Poller Widget creating");

            return pollsPage;

        } catch (Exception e) {
            logger.error("Member not authorised");
        }

        return null;
    }
}
