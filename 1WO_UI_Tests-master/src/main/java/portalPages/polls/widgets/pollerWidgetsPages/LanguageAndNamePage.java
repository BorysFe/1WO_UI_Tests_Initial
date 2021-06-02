package portalPages.polls.widgets.pollerWidgetsPages;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

import java.util.Objects;

public class LanguageAndNamePage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(LanguageAndNamePage.class);

    WaitUtils waitUtils;
    ContentBuilderPage contentBuilderPage;

    public LanguageAndNamePage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.INPUT_BY_ID.toString(), "widget-name")));
    }

    public LanguageAndNamePage selectDropDownItem(String selectTitle, String selectItem) {
        WebElement openedSelect =
                waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath(String.format(GeneralLocators.SELECT_BY_NAME.toString(), selectTitle)));

        if (Objects.equals(getDropdownItem(selectItem).getAttribute("selected"), null)) {
            Select select = new Select(openedSelect);
            select.selectByVisibleText(selectItem);
        }
        logger.info("In the Dropdown " + selectItem + " selected " + selectItem);

        return this;
    }

    public ContentBuilderPage nextButtonClick() {
        contentBuilderPage = new ContentBuilderPage(driver);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Next")));
        logger.info("Click Next button in the page Language and Name page");

        return contentBuilderPage;
    }

    public boolean isOptionSelectedInDropdown(String dropdownItem) {

        return Objects.equals(getDropdownItem(dropdownItem).getAttribute("selected"), "true");
    }

    private WebElement getDropdownItem(String dropdownItem) {
        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath(String.format(GeneralLocators.SELECT_BY_NAME.toString(), dropdownItem)));
    }

    public LanguageAndNamePage newWidgetDefaultLanguage(String widgetName) {
        String nameWidgetField = String.format(GeneralLocators.INPUT_BY_ID.toString(), "widget-name");

        waitUtils.waitForLoading();
        logger.info("Set Widget Name field with: " + widgetName);
        setField(By.xpath(nameWidgetField), widgetName);

        return this;
    }

    void setField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    void setField(By element, String text) {
        waitUtils.waitVisibilityOfElementByShort(element);
        driver.findElement(element).clear();
        waitUtils.waitVisibilityOfElementByShort(element);
        driver.findElement(element).sendKeys(text);
    }
}
