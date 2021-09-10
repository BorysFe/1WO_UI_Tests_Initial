package portalPages.surveys;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.SelectLanguageModalPage;
import utils.WaitUtils;

import java.util.Objects;

public class SurveyConfigurationPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(SurveyConfigurationPage.class);

    WaitUtils waitUtils;
    SurveyBrandingPage brandingPage;

    public SurveyConfigurationPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        brandingPage = new SurveyBrandingPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    public SurveyConfigurationPage enterSurveyName(String name) {

        waitUtils.waitForLoading();
        waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.INPUT_BY_NAME.toString(), "internalTitle")))
                .sendKeys(name);
        waitUtils.waitForLoading();

        return this;
    }

    public SurveyConfigurationPage enterSurveyTitle(String title) {

        waitUtils.waitForLoading();
        waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.INPUT_BY_NAME.toString(), "publicTitle")))
                .sendKeys(title);
        waitUtils.waitForLoading();

        return this;
    }

    public SurveyConfigurationPage enterSurveyParticipantsNeeded(String numberParticipants) {

        waitUtils.waitForLoading();
        waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.INPUT_BY_NAME.toString(), "internalTitle")))
                .sendKeys(numberParticipants);
        waitUtils.waitForLoading();

        return this;
    }

    public SurveyConfigurationPage selectSurveyCategory(String selectItem) {
        WebElement openedSelect =
                waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                        (String.format(GeneralLocators.SELECT_BY_NAME.toString(), "survey.categoryId")));

        if (Objects.equals(getDropdownItem(selectItem).getAttribute("selected"), null)) {
            Select select = new Select(openedSelect);
            select.selectByVisibleText(selectItem);
        }

        return this;
    }

    private WebElement getDropdownItem(String dropdownItem) {
        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.OPTION_BY_TEXT.toString(), dropdownItem)));
    }

    public SurveyBrandingPage clickNext() {

        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Next")));
        waitUtils.waitForLoading();

        return brandingPage;
    }
}
