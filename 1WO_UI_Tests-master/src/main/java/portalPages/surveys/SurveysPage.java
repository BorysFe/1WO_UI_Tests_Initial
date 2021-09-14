package portalPages.surveys;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.SelectLanguageModalPage;
import utils.WaitUtils;

public class SurveysPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(SurveysPage.class);

    WaitUtils waitUtils;
    private final SelectLanguageModalPage languageModal;

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    public SurveysPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        languageModal = new SelectLanguageModalPage(driver);
    }

    public SelectLanguageModalPage startNewFullPageSurveyCreating() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath
                (String.format(GeneralLocators.INPUT_BY_VALUE.toString(), "New Full Page survey")));
        waitUtils.waitForLoading();

        return languageModal;
    }

    public boolean isSurveyTitleDisplayed(String title) {
        waitUtils.waitForLoading();

        return waitUtils.isElementVisibleAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_TEXT.toString(), title)));
    }

    public boolean isLanguageModalDisplayed() {
        waitUtils.waitForLoading();

        return waitUtils.isElementVisibleAfterShortWait(By.xpath
                (String.format(GeneralLocators.DIV_BY_CLASS.toString(), "g-modal-padding-box")));
    }
}
