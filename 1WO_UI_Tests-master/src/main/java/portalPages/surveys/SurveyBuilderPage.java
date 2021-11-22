package portalPages.surveys;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class SurveyBuilderPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(SurveyBuilderPage.class);

    WaitUtils waitUtils;
    private SurveyMessagesPage messagesPage;

    String elementName = String.format(GeneralLocators.SPAN_BY_CONTAINS_CLASS.toString(), "js-element-name");
    String pageNumberInThePage = String.format(GeneralLocators.SPAN_BY_CONTAINS_CLASS.toString(), "element-counter") + "[text()='%s']";

    public SurveyBuilderPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        messagesPage = new SurveyMessagesPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {

        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Next")));
    }

    public SurveyMessagesPage clickNext() {
        clickNextButton();

        return messagesPage;
    }

    public SurveyBuilderPage addNewPage() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Add New Page")));

        return this;
    }

    public boolean isPageWithNumberDisplayed(String pageNumber) {
        waitUtils.waitForLoading();

        return waitUtils.isElementVisibleAfterShortWait(By.xpath(String.format(pageNumberInThePage, pageNumber)));
    }

    public SurveyBuilderPage addDescriptionTextBlockAndEnterText(String descriptionText) {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Add Description Text")));
        setField(By.xpath(String
                .format(GeneralLocators.DIV_BY_CONTAINS_CLASS.toString(), "cke_contents cke_reset")), descriptionText);

        return this;
    }
}
