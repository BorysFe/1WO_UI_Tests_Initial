package portalPages.widgets.pollerWidgetsPages;

import base.AccountsInfoPage;
import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class DesktopSettingsAndPreviewPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(DesktopSettingsAndPreviewPage.class);

    WaitUtils waitUtils;
    MobileSettingsAndPreviewPage mobileSettingsAndPreviewPage;

    private final String pollsButton = ".//a[contains(text(), '%s')]/following-sibling::div/span[@class='%s']";

    public DesktopSettingsAndPreviewPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_CONTAINS_CLASS.toString(), "add")));
    }

    public MobileSettingsAndPreviewPage nextButtonClick() {
        mobileSettingsAndPreviewPage = new MobileSettingsAndPreviewPage(driver);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Next")));
        logger.info("Click Next button in the page Desktop Settings And Preview Page");
        logger.info(getWidgetOWOCode());

        return mobileSettingsAndPreviewPage;
    }

    public DesktopSettingsAndPreviewPage selectCheckBox(String checkBoxName) {
        waitUtils.waitForLoading();
        unselectCheckBox(waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.INPUT_BY_ID.toString(), checkBoxName))));

        return this;
    }

    public String getWidgetOWOCode() {

        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.DIV_BY_CONTAINS_CLASS + "/div", "widget-element")))
                .getAttribute("data-owo-code");
    }
}
