package portalPages.polls.widgets;

import base.BaseComponent;
import base.enums.GeneralLocators;
import base.enums.PageURLs;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

@Setter
@Getter
public class BetaPollerWidgetPreviewPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(BetaPollerWidgetPreviewPage.class);

    WaitUtils waitUtils;

    @FindBy(xpath = ".//div[contains(@class,'footerStatistic')]")
    private WebElement statisticButton;

    @FindBy(xpath = ".//div[contains(@class,'footerPoints')]")
    private WebElement scoreUser;

    @FindBy(xpath = ".//span[text()='Dashboard']")
    private WebElement scorePointsHoverButtonDashboard;

    @FindBy(xpath = ".//div[contains(@class, 'totalViews')]")
    private WebElement viewsCounter;

    @FindBy(xpath = ".//div[contains(@class, 'totalViews')]//span")
    private WebElement viewsValue;

    @FindBy(xpath = ".//div[contains(@class, 'totalVotes')]")
    private WebElement votesCounter;

    @FindBy(xpath = ".//div[contains(@class, 'totalVotes')]//span")
    private WebElement votesValue;

    @FindBy(xpath = ".//div[contains(@class, 'social')]")
    private WebElement sharingArrow;

    @FindBy(xpath = ".//textarea[@id='textareaEmbedCode']")
    private WebElement embedCodeArea;

    private final String pollVotingButton = ".//p[contains(text(),'%s')]/preceding-sibling::label//span/span";
    private final String pollVotingButtonCheck = ".//label[contains(@title,'%s')]//span/input[@checked= 'checked']";
    private final String pollVotingPercent = ".//span[@class='percents-holder']/preceding-sibling::p[contains(text(), '%s')]";
    private final String frameXpath = ".//div[@data-owo-code='%s']/iframe[@id='surveyWidgetOWOFrame']";
    private final String sharingLink = ".//div[contains(@class, 'social')]/button[@aria-label='%s']";

    public BetaPollerWidgetPreviewPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.DIV_BY_ID.toString(), "owo-widget")));
    }

    private BetaPollerWidgetPreviewPage switchToWidgetFrame() {
        String frameID = waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(".//div[@id='widgetOWO']"))
                .getAttribute("data-owo-code");

        driver.switchTo().frame(driver.findElement(By.xpath(String.format(frameXpath, frameID))));

        return this;
    }

    public BetaPollerWidgetPreviewPage openPollerWidgetPreview(String widgetOWOCode) {
        waitUtils.waitForLoading();

        String widgetPreviewPageURL = String.format(PageURLs.BETA_WIDGET_PREVIEW_PAGE_URL.toString(), widgetOWOCode);

        driver.get(widgetPreviewPageURL);

        return this;
    }

    public BetaPollerWidgetPreviewPage voteAnswer(String pollAnswerText) {
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
                .format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), pollQuestionText)));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isPollsPercentsDisplayed(String pollAnswerText) {
        waitUtils.waitForLoading();

        switchToWidgetFrame();
        waitUtils.waitForLoading();

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
                .format(GeneralLocators.DIV_BY_CONTAINS_CLASS.toString(), "count-of-user-score"))).getText();

        driver.switchTo().defaultContent();

        return userScoreValue;
    }

    public String getPollVotePercent(String pollAnswerText) {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        String pollVotePercentValue = waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), pollAnswerText))).getText();

        driver.switchTo().defaultContent();

        return pollVotePercentValue;
    }

    private void pollSearching(String pollQuestionText) {
        waitUtils.waitForLoading();

        waitUtils.waitForLoading();

        switchToWidgetFrame();

        waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), pollQuestionText)));

        driver.switchTo().defaultContent();
    }

    public boolean isStatisticButtonDisplayed() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        boolean status = waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.DIV_BY_CONTAINS_CLASS.toString(), "footerStatistic")));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isStatisticHoverTextDisplayed() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        actions.mouseHover(By.xpath(String
                .format(GeneralLocators.DIV_BY_CONTAINS_CLASS.toString(), "footerStatistic")));

        boolean status = waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Statistic")));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isScorePointsDisplayed() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        boolean status = waitUtils.isElementVisibleAfterShortWait(scoreUser);

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isScorePointsHoverDisplayed(String pollAnswerText) {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        actions.mouseHover(scoreUser);

        boolean status = waitUtils.isElementVisibleAfterShortWait(scorePointsHoverButtonDashboard);

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isViewsCounterDisplayed() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        boolean status = waitUtils.isElementVisibleAfterShortWait(viewsCounter);

        driver.switchTo().defaultContent();

        return status;
    }

    public String getViewsValue() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        String viewsValue = waitUtils.getElementWhenVisibleAfterShortWait(viewsCounter).getText();

        driver.switchTo().defaultContent();

        return viewsValue;
    }

    public boolean isVotesCounterDisplayed() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        boolean status = waitUtils.isElementVisibleAfterShortWait(votesCounter);

        driver.switchTo().defaultContent();

        return status;
    }

    public String getVotesValue() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        String viewsValue = waitUtils.getElementWhenVisibleAfterShortWait(votesValue).getText();

        driver.switchTo().defaultContent();

        return viewsValue;
    }

    //Need refactoring
    public String getVotesValueAfterPageReload(Integer pageReloadValue) {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        for (int y = 0; y < pageReloadValue; y++) {
            waitUtils.waitForElementToBeDisplayedWithPageRefresh(votesCounter);

            waitUtils.waitForLoading();

            switchToWidgetFrame();
        }

        String viewsValue = waitUtils.getElementWhenVisibleAfterShortWait(votesValue).getText();

        driver.switchTo().defaultContent();

        return viewsValue;
    }

    public boolean isSharingArrowDisplayed() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        waitUtils.waitForLoading();

        boolean status = waitUtils.isElementVisibleAfterShortWait(sharingArrow);

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isSharingFacebookLinkDisplayed() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        waitUtils.waitForLoading();
        actions.mouseHover(sharingArrow);

        boolean status = waitUtils
                .isElementVisibleAfterShortWait(By.xpath(String.format(sharingLink, "facebook")));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isSharingTwitterLinkDisplayed() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        waitUtils.waitForLoading();
        actions.mouseHover(sharingArrow);

        boolean status = waitUtils
                .isElementVisibleAfterShortWait(By.xpath(String.format(sharingLink, "twitter")));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isSharingLinkedInLinkDisplayed() {
        waitUtils.waitForLoading();

        switchToWidgetFrame();

        waitUtils.waitForLoading();
        actions.mouseHover(sharingArrow);

        boolean status = waitUtils
                .isElementVisibleAfterShortWait(By.xpath(String.format(sharingLink, "linkedin")));

        driver.switchTo().defaultContent();

        return status;
    }
}
