package portalPages.surveys;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class SurveyBuilderPage  extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(SurveyBuilderPage.class);

    WaitUtils waitUtils;
    private SurveyMessagesPage messagesPage;

    public SurveyBuilderPage(WebDriver driver) {
        super(driver);
        messagesPage = new SurveyMessagesPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    public SurveyMessagesPage clickNext() {

        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Next")));
        waitUtils.waitForLoading();

        return messagesPage;
    }
}
