package portalPages.polls.widgets.widgetPages;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class DesktopSettingsAndPreviewPage extends BaseComponent {

    WaitUtils waitUtils;
    MobileSettingsAndPreviewPage mobileSettingsAndPreviewPage;

    private final String pollsButton = ".//a[contains(text(), '%s')]/following-sibling::div/span[@class='%s']";

    public DesktopSettingsAndPreviewPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_CLASS.toString(), "add")));
    }

    public MobileSettingsAndPreviewPage nextButtonClick() {
        mobileSettingsAndPreviewPage = new MobileSettingsAndPreviewPage(driver);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Next")));
        return mobileSettingsAndPreviewPage;
    }
}
