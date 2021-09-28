package portalPages.surveys;

import base.BaseComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

public class SurveyStatisticPage extends BaseComponent {

    WaitUtils waitUtils;
    SurveysPage surveysPage;

    @FindBy(xpath = ".//ul[@class= 'chart-legend']/li/span")
    private WebElement totalResponses;

    @FindBy(xpath = ".//ul[@id= 'managerBreadCrumbs']/li[2]")
    private WebElement surveyTitle;

    @FindBy(xpath = ".//ul[@id= 'managerBreadCrumbs']//a")
    private WebElement surveysListButton;

    public SurveyStatisticPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        surveysPage = new SurveysPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {

        return waitUtils.getElementWhenVisibleAfterMiddleWait(totalResponses);
    }

    public boolean isSurveyTitleDisplayed(String title) {
        waitUtils.waitForLoading();

        return waitUtils.getElementWhenVisibleAfterShortWait(surveyTitle).getText().equals(title);
    }

    public String getSurveyTitle() {
        waitUtils.waitForLoading();

        return waitUtils.getElementWhenVisibleAfterShortWait(surveyTitle).getText();
    }

    public String getCompletedResponses() {

        return waitUtils.getElementWhenVisibleAfterMiddleWait(By.xpath(".//div[contains(text(), 'completed / ')]//span[1]"))
                .getText();
    }

    public String getNeededResponses() {

        return waitUtils.getElementWhenVisibleAfterMiddleWait(By.xpath(".//div[contains(text(), 'completed / ')]//span[2]"))
                .getText();
    }

    public String getTotalResponses() {

        return waitUtils.getElementWhenVisibleAfterMiddleWait(totalResponses)
                .getText().split(" ").toString();
    }

    public SurveysPage clickSurveysListButton() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(surveysListButton);

        return surveysPage;
    }
}
