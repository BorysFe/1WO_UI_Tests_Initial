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

    String nameWidgetField = String.format(GeneralLocators.INPUT_BY_ID.toString(), "widget-name");

    public LanguageAndNamePage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {

        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath
                (String.format(GeneralLocators.INPUT_BY_ID.toString(), "widget-name")));
    }

    public LanguageAndNamePage selectDropDownItem(String selectTitle, String selectItem) {
        WebElement openedSelect =
                waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                        (String.format(GeneralLocators.SELECT_BY_CONTAINS_NAME.toString(), selectTitle)));

        if (Objects.equals(getDropdownItem(selectItem).getAttribute("selected"), null)) {
            Select select = new Select(openedSelect);
            select.selectByVisibleText(selectItem);
        }
        logger.info("In the Dropdown " + selectTitle + " selected " + selectItem);

        return this;
    }

    public ContentBuilderPage nextButtonClick() {
        contentBuilderPage = new ContentBuilderPage(driver);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Next")));

        return contentBuilderPage;
    }

    public boolean isOptionSelectedInDropdown(String dropdownItem) {

        return Objects.equals(getDropdownItem(dropdownItem).getAttribute("selected"), "true");
    }

    private WebElement getDropdownItem(String dropdownItem) {

        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.SELECT_BY_CONTAINS_NAME.toString(), dropdownItem)));
    }

    public LanguageAndNamePage newWidgetDefaultLanguage(String widgetName) {
        waitUtils.waitForLoading();
        logger.info("Set Widget Name: " + widgetName);
        setField(By.xpath(nameWidgetField), widgetName);

        return this;
    }
}
