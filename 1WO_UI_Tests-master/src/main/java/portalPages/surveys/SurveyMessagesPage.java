package portalPages.surveys;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class SurveyMessagesPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(SurveyMessagesPage.class);

    WaitUtils waitUtils;
    SurveysPage surveysPage;

    String finishButton = String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Finish");

    public SurveyMessagesPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        surveysPage = new SurveysPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {

        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath(finishButton));
    }

    public void clickFinish() {
        clickFinishButton();
    }
}
