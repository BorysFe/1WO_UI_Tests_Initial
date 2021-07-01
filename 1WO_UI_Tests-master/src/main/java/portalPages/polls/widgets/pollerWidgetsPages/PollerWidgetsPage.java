package portalPages.polls.widgets.pollerWidgetsPages;

import base.BaseComponent;
import base.enums.GeneralLocators;
import base.enums.PageQAURLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import utils.WaitUtils;

public class PollerWidgetsPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(PollerWidgetsPage.class);

    WaitUtils waitUtils;
    PollerWidgetPreviewPage pollerWidgetPreviewPage;

    public PollerWidgetsPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By
                .xpath(String.format(GeneralLocators.A_BY_TEXT.toString(), "New widget")));
    }

    public PollerWidgetsPage openPollerWidgetsPage() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By
                .xpath(String.format(GeneralLocators.A_BY_TEXT.toString(), "Poller widgets")));
        return this;
    }

    public String getWidgetOWOCode(String widgetName) {
        String owoCode = waitUtils.getElementWhenVisibleAfterShortWait(By
                .xpath(String.format(GeneralLocators.A_BY_TEXT.toString(), widgetName)))
                .getAttribute("href")
                .split("content/")[1];

        logger.info("Widget's OWO Code - " + owoCode);
        return owoCode;
    }

    public boolean isWidgetTitleDisplayed(String widgetName) {
        logger.info("Widget title " + widgetName + " searching");
        return waitUtils.isElementVisibleAfterShortWait(By
                .xpath(String.format(GeneralLocators.A_BY_TEXT.toString(), widgetName)));
    }

    public PollerWidgetPreviewPage openPollerWidgetPreviewPage(String widgetName) {
        pollerWidgetPreviewPage = new PollerWidgetPreviewPage(driver);

        String widgetOWOCode = getWidgetOWOCode(widgetName);
        String widgetPreviewPageURL = String.format(PageQAURLs.QA_WIDGET_PREVIEW_PAGE_URL.toString(), widgetOWOCode);

        driver.get(widgetPreviewPageURL);

        return pollerWidgetPreviewPage;
    }
}
