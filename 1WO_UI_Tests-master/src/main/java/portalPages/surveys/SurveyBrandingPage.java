package portalPages.surveys;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class SurveyBrandingPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(SurveyBrandingPage.class);

    WaitUtils waitUtils;
    private SurveyBuilderPage builderPage;

    public SurveyBrandingPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        builderPage = new SurveyBuilderPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {

        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_TEXT.toString(), "Next")));
    }

    public SurveyBuilderPage clickNext() {
        clickNextButton();

        return builderPage;
    }
}
