package portalPages.polls.widgets.pollerWidgetsPages;

import base.BaseComponent;
import base.enums.GeneralLocators;
import base.enums.PageURLs;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.MenuProfileDropDown;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import utils.WaitUtils;

@Getter
public class PollerWidgetsPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(PollerWidgetsPage.class);

    String dataWidgetCode = ".//a[contains(text(), '%s')]/../../following-sibling::td/div[contains(@class, 'delete')]";

    WaitUtils waitUtils;
    PollerWidgetPreviewPage pollerWidgetPreviewPage;
    MenuProfileDropDown menuProfileDropDown;

    public PollerWidgetsPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        pollerWidgetPreviewPage = new PollerWidgetPreviewPage(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {

        return waitUtils.getElementWhenVisibleAfterShortWait(By
                .xpath(String.format(GeneralLocators.A_BY_TEXT.toString(), "Poller widgets")));
    }

    public PollerWidgetsPage openPollerWidgetsPage() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By
                .xpath(String.format(GeneralLocators.A_BY_TEXT.toString(), "Poller widgets")));

        return this;
    }

    public String getWidgetOWOCode(String widgetName) {

        try {
            String owoCode = waitUtils.getElementWhenVisibleAfterShortWait(By
                    .xpath(String.format(dataWidgetCode, widgetName)))
                    .getAttribute("data-widget-code");

            logger.info("Widget's OWO Code - " + owoCode);

            return owoCode;
        } catch (Exception e) {
            return "OWO Code not found";
        }
    }

    public boolean isWidgetTitleDisplayed(String widgetName) {
        logger.info("Widget title " + widgetName + " searching");

        return waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.A_BY_TEXT.toString(), widgetName)));
    }

    public PollerWidgetPreviewPage openPollerWidgetPreviewPage(String widgetName) {
        waitUtils.waitForLoading();
        String widgetOWOCode = getWidgetOWOCode(widgetName);
        String widgetPreviewPageURL = String.format(PageURLs.WIDGET_PREVIEW_PAGE_URL.toString(), widgetOWOCode);

        menuProfileDropDown.logOut();

        driver.get(widgetPreviewPageURL);

        return pollerWidgetPreviewPage;
    }

    public PollerWidgetPreviewPage openPollerWidgetPreviewPageByOWOCode(String widgetOWOCode) {
        waitUtils.waitForLoading();

        String widgetPreviewPageURL = String.format(PageURLs.WIDGET_PREVIEW_PAGE_URL.toString(), widgetOWOCode);

        driver.get(widgetPreviewPageURL);

        return pollerWidgetPreviewPage;
    }
}
