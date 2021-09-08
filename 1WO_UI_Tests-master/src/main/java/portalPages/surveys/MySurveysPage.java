package portalPages.surveys;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.polls.widgets.pollerWidgetsPages.LanguageAndNamePage;
import portalPages.surveys.newFullPageSurvey.NewFullPageSurveyConfigurationPage;
import utils.WaitUtils;

public class MySurveysPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(MySurveysPage.class);

    WaitUtils waitUtils;
    NewFullPageSurveyConfigurationPage configurationPage;


    public MySurveysPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        configurationPage = new NewFullPageSurveyConfigurationPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    public NewFullPageSurveyConfigurationPage startNewFullPageSurveyCreating() {

        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(GeneralLocators.A_BY_TEXT.toString(), "New widget")));

        return configurationPage;
    }

}
