package portalPages.surveys;

import base.BaseComponent;
import base.enums.GeneralLocators;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

import java.util.Objects;

@Setter
@Getter
public class SurveyConfigurationPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(SurveyConfigurationPage.class);

    WaitUtils waitUtils;
    SurveyBrandingPage brandingPage;

    String surveyTitle = String.format(GeneralLocators.INPUT_BY_CONTAINS_NAME.toString(), "publicTitle");
    String surveyName = String.format(GeneralLocators.INPUT_BY_CONTAINS_NAME.toString(), "internalTitle");
    String surveyParticipantNeeded = String.format(GeneralLocators.INPUT_BY_CONTAINS_NAME.toString(), "survey.peopleNeeded");

    public SurveyConfigurationPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        brandingPage = new SurveyBrandingPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {

        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.SELECT_BY_CONTAINS_NAME.toString(), "survey.categoryId")));
    }

    public SurveyConfigurationPage enterSurveyName(String name) {
        waitUtils.waitForLoading();
        setField(By.xpath(surveyName), name);
        waitUtils.waitForLoading();

        return this;
    }

    public SurveyConfigurationPage enterSurveyTitle(String title) {
        waitUtils.waitForLoading();
        setField(By.xpath(surveyTitle), title);
        waitUtils.waitForLoading();

        return this;
    }

    public SurveyConfigurationPage enterSurveyParticipantsNeeded(String numberParticipants) {
        waitUtils.waitForLoading();
        setField(By.xpath(surveyParticipantNeeded), numberParticipants);
        waitUtils.waitForLoading();

        return this;
    }

    public SurveyConfigurationPage selectSurveyCategory(String selectItem) {
        waitUtils.waitForLoading();
        WebElement openedSelect =
                waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                        (String.format(GeneralLocators.SELECT_BY_CONTAINS_NAME.toString(), "survey.categoryId")));

        if (Objects.equals(getDropdownItem(selectItem).getAttribute("selected"), null)) {
            Select select = new Select(openedSelect);
            select.selectByVisibleText(selectItem);
        }
        waitUtils.waitForLoading();

        return this;
    }

    private WebElement getDropdownItem(String dropdownItem) {
        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.OPTION_BY_CONTAINS_TEXT.toString(), dropdownItem)));
    }

    public SurveyBrandingPage clickNext() {
        clickNextButton();

        return brandingPage;
    }

    public boolean isDisplayedErrorByRequiredCategory() {

        return waitUtils.isElementVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.LABEL_BY_ID.toString(), "survey.categoryId-error")));
    }

    public boolean isDisplayedErrorByCorrectNumbersOfParticipants() {

        return waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.LABEL_BY_ID.toString(), "survey.peopleNeeded-error")));
    }
}
