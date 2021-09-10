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
    private static final Logger logger = LoggerFactory.getLogger(SurveyBrandingPage.class);

    WaitUtils waitUtils;
    private SurveyBrandingPage brandingPage;

    public SurveyMessagesPage(WebDriver driver) {
        super(driver);
        brandingPage = new SurveyBrandingPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Finish")));
    }

    public void clickFinish() {

        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Finish")));
    }
}
