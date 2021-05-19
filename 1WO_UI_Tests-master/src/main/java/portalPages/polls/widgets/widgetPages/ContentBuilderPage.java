package portalPages.polls.widgets.widgetPages;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

import java.util.Objects;

public class ContentBuilderPage extends BaseComponent {

    WaitUtils waitUtils;
    private DesktopSettingsAndPreviewPage desktopSettingsAndPreviewPage;

    private final String pollsButton = ".//a[contains(text(), '%s')]/following-sibling::div/span[@class='%s']";

    public ContentBuilderPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_CLASS.toString(), "add")));
    }

    public ContentBuilderPage selectDropDownItem(String selectTitle, String selectItem) {
        WebElement openedSelect = waitUtils.waitForElementToBeDisplayedAfterShortWait(
                By.xpath(String.format(GeneralLocators.SELECT_BY_NAME.toString(), selectTitle)));

        if (Objects.equals(getDropdownItem(selectItem).getAttribute("selected"), null)) {
            Select select = new Select(openedSelect);
            select.selectByVisibleText(selectItem);
        }

        return this;
    }

    public boolean isOptionSelectedInDropdown(String dropdownItem) {
        return Objects.equals(getDropdownItem(dropdownItem).getAttribute("selected"), "true");
    }

    private WebElement getDropdownItem(String dropdownItem) {
        return waitUtils.waitForElementToBeDisplayedAfterShortWait(
                By.xpath(String.format(GeneralLocators.SELECT_BY_NAME.toString(), dropdownItem)));
    }

    public DesktopSettingsAndPreviewPage nextButtonClick() {
        desktopSettingsAndPreviewPage = new DesktopSettingsAndPreviewPage(driver);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Next")));
        waitUtils.waitForLoading();
        return desktopSettingsAndPreviewPage;
    }

    public ContentBuilderPage addPollToWidget(String pollTitle) {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(pollsButton, pollTitle, "add")));
        waitUtils.waitForLoading();
        return this;
    }
}
