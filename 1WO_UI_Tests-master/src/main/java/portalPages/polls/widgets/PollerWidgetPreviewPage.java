package portalPages.polls.widgets;

import base.BaseComponent;
import base.enums.GeneralLocators;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

@Getter
public class PollerWidgetPreviewPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(PollerWidgetPreviewPage.class);

    WaitUtils waitUtils;

    private final String pollVotingButton = ".//label[contains(@title,'%s')]//span[contains(@class,'radio-button')]";
    private final String pollVotingButtonCheck = ".//label[contains(@title,'%s')]//span/input[@checked= 'checked']";
    private final String pollVotingPercent = ".//label[contains(@title,'%s')]//span[contains(@class,'votes-percentage')]";

    public PollerWidgetPreviewPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.DIV_BY_ID.toString(), "owo-widget")));
    }

    private PollerWidgetPreviewPage switchToWidgetFrame() {
        String owoToken = waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(".//div[@data-owo-type='widget']"))
                .getAttribute("data-owo-token");
        String frameID = "1worldOnlineWidgetFrame_" + owoToken;

        driver.switchTo().frame(driver.findElement(By.id(frameID)));

        return this;
    }

    public PollerWidgetPreviewPage voteAnswer(String pollAnswerText) {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String
                .format(pollVotingButton, pollAnswerText)));

        driver.switchTo().defaultContent();

        return this;
    }

    public boolean isPollDisplayed(String pollQuestionText) {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        boolean status = waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_TEXT.toString(), pollQuestionText)));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isPollsPercentsDisplayed(String pollAnswerText) {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        boolean status = waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(pollVotingPercent, pollAnswerText)));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isAnswerVoted(String pollAnswerText) {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        waitUtils.waitForLoading();

        boolean status = driver.findElement(By.xpath(String
                .format(pollVotingButtonCheck, pollAnswerText)))
                .getAttribute("checked").equals("true");

        driver.switchTo().defaultContent();

        return status;
    }

    public String getUsersScore() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        String userScoreValue = waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.DIV_BY_CLASS.toString(), "count-of-user-score"))).getText();

        driver.switchTo().defaultContent();

        return userScoreValue;
    }

    public String getPollVotePercent(String pollAnswerText) {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        String pollVotePercentValue = waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_TEXT.toString(), pollAnswerText))).getText();

        driver.switchTo().defaultContent();

        return pollVotePercentValue;
    }

    private void pollSearching(String pollQuestionText) {
        waitUtils.waitForLoading();

        waitUtils.waitForLoading();

        switchToWidgetFrame();

        waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_TEXT.toString(), pollQuestionText)));

        driver.switchTo().defaultContent();
    }
}
