package portalPages.surveys.newFullPageSurvey;

import base.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.polls.polls.PollsPage;
import utils.DriverFactory;

public class NewFullPageSurveyConfigurationPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(NewFullPageSurveyConfigurationPage.class);

    public NewFullPageSurveyConfigurationPage(WebDriver driver) {
        super(driver);
    }


    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }
}
