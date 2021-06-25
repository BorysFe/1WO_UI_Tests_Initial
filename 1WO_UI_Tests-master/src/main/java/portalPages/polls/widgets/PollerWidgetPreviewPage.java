package portalPages.polls.widgets;

import base.BaseComponent;
import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import portalPages.polls.widgets.pollerWidgetsPages.PollerWidgetsPage;
import utils.WaitUtils;

public class PollerWidgetPreviewPage extends BaseComponent {

    WaitUtils waitUtils;
    PollerWidgetsPage pollerWidgetsPage;

    private final String pollVotingButton = ".//label[contains(@title,'%s')]//span[contains(@class,'radio-button')]";
    private final String pollVotingPercent = ".//label[contains(@title,'%s')]//span[contains(@class,'votes-percentage')]";

    public PollerWidgetPreviewPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        pollerWidgetsPage = new PollerWidgetsPage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.DIV_BY_ID.toString(), "owo-widget")));
    }

    private PollerWidgetPreviewPage openWidgetFrame() {
        String owoToken = waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(".//div[@data-owo-type='widget']"))
                .getAttribute("data-owo-token");
        String frameID = "1worldOnlineWidgetFrame_" + owoToken;

        driver.switchTo().frame(driver.findElement(By.id(frameID)));

        return this;
    }

    public PollerWidgetPreviewPage voteAnswer(String pollAnswerText) {
        waitUtils.waitForLoading();
        openWidgetFrame();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), pollAnswerText)));

        return this;
    }

    public boolean isPollDisplayed(String pollQuestionText) {
        waitUtils.waitForLoading();

        return waitUtils.isElementVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), pollQuestionText)));
    }

    public boolean isPollsPercentsDisplayed(String pollAnswerText) {
        waitUtils.waitForLoading();

        return waitUtils.isElementVisibleAfterShortWait(By.xpath(String.format(pollVotingPercent, pollAnswerText)));
    }

    public boolean isAnswerVoted(String pollAnswerText) {
        waitUtils.waitForLoading();
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(pollVotingButton, pollAnswerText)))
                .getAttribute("checked").equals("checked");
    }

    public String getUsersScore() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.DIV_BY_CLASS.toString(), "count-of-user-score"))).getText();
    }

    public String getPollVotePercent(String pollAnswerText) {
        waitUtils.waitForLoading();

        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), pollAnswerText))).getText();
    }

    private void pollSearching(String pollQuestionText) {
        waitUtils.waitForLoading();
        waitUtils.isElementVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), pollQuestionText)));
    }
}
