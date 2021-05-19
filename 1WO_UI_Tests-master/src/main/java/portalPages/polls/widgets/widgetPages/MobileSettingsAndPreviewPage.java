package portalPages.polls.widgets.widgetPages;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.jsoup.Connection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import portalPages.polls.polls.PollsPage;
import utils.WaitUtils;

public class MobileSettingsAndPreviewPage extends BaseComponent {

    WaitUtils waitUtils;
    PollsPage pollsPage;

    public MobileSettingsAndPreviewPage(WebDriver driver) {
        super(driver);
    waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Finish")));
    }

    public PollsPage finishButtonClick() {
        pollsPage = new PollsPage(driver);
        waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Finish")));
        return pollsPage;
    }
}
