package portalPages.widgets;

import base.AccountsInfoPage;
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
public class PollerWidgetPreviewPage extends BaseComponent implements WidgetPreviewPage {
    private static final Logger logger = LoggerFactory.getLogger(PollerWidgetPreviewPage.class);

    WaitUtils waitUtils;

    @FindBy(xpath = ".//div[contains(@class,'footerStatistic')]")
    private WebElement statisticButton;

    @FindBy(xpath = ".//div[contains(@class,'count-of-user-score ')]")
    private WebElement scoreUser;

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

    private final String pollVotingButton = ".//label[contains(@title,'%s')]//span[contains(@class,'radio-button')]";
    private final String pollVotingButtonCheck = ".//label[contains(@title,'%s')]//span/input[@checked= 'checked']";
    private final String pollVotingPercent = ".//label[contains(@title,'%s')]//span[contains(@class,'votes-percentage')]";
    private final String sharingLink = ".//div[contains(@class,'social')]/div[contains(@class,'%s')]";

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

        waitUtils.waitForLoading();

        String owoToken = waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(".//div[@data-owo-type='widget']"))
                .getAttribute("data-owo-token");
        String frameID = "1worldOnlineWidgetFrame_" + owoToken;

        driver.switchTo().frame(driver.findElement(By.id(frameID)));

        waitUtils.waitForLoading();

        return this;
    }

    public void openPollerWidgetPreview(String widgetOWOCode) {
        waitUtils.waitForLoading();

        String widgetPreviewPageURL = String.format(PageURLs.WIDGET_PREVIEW_PAGE_URL.toString(), widgetOWOCode);

        driver.get(widgetPreviewPageURL);
    }

    public void voteAnswer(String pollAnswerText) {
        waitUtils.waitForLoading();
        switchToWidgetFrame();
        waitUtils.waitForLoading();

        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String
                .format(pollVotingButton, pollAnswerText)));

        waitUtils.waitForLoading();

        driver.switchTo().defaultContent();
    }

    @Override
    public void getVotesValueAfterPageReload(int i) {

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
        waitUtils.waitForLoading();

        String userScoreValue = waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.DIV_BY_CONTAINS_CLASS.toString(), "count-of-user-score"))).getText();

        driver.switchTo().defaultContent();

        return userScoreValue;
    }

    public String getPollVotePercent(String pollAnswerText) {
        waitUtils.waitForLoading();
        switchToWidgetFrame();
        waitUtils.waitForLoading();

        String pollVotePercentValue = waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), pollAnswerText))).getText();

        driver.switchTo().defaultContent();

        return pollVotePercentValue;
    }

    private void pollSearching(String pollQuestionText) {
        waitUtils.waitForLoading();
        switchToWidgetFrame();
        waitUtils.waitForLoading();

        waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), pollQuestionText)));

        driver.switchTo().defaultContent();
    }

    public boolean isStatisticButtonDisplayed() {
        waitUtils.waitForLoading();
        switchToWidgetFrame();
        waitUtils.waitForLoading();

        boolean status = waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.DIV_BY_CONTAINS_CLASS.toString(), "gift-stat-wrapper")));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isStatisticHoverTextDisplayed() {
        waitUtils.waitForLoading();
        switchToWidgetFrame();
        waitUtils.waitForLoading();

        actions.mouseHover(By.xpath(String
                .format(GeneralLocators.DIV_BY_CONTAINS_CLASS.toString(), "gift-stat-wrapper")));

        boolean status = waitUtils.isElementVisibleAfterShortWait(By.xpath(String
                .format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Poll insights")));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isScorePointsDisplayed() {
        waitUtils.waitForLoading();
        switchToWidgetFrame();
        waitUtils.waitForLoading();

        boolean status = waitUtils.isElementVisibleAfterShortWait(scoreUser);

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
        waitUtils.waitForLoading();

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

        boolean status = waitUtils
                .isElementVisibleAfterShortWait(By.xpath(String.format(sharingLink, "fb-share-btn")));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isSharingTwitterLinkDisplayed() {
        waitUtils.waitForLoading();
        switchToWidgetFrame();
        waitUtils.waitForLoading();

        boolean status = waitUtils
                .isElementVisibleAfterShortWait(By.xpath(String.format(sharingLink, "twitter-share-btn")));

        driver.switchTo().defaultContent();

        return status;
    }

    public boolean isSharingLinkedInLinkDisplayed() {
        waitUtils.waitForLoading();
        switchToWidgetFrame();
        waitUtils.waitForLoading();

        boolean status = waitUtils
                .isElementVisibleAfterShortWait(By.xpath(String.format(sharingLink, "linked-in-share-btn")));

        driver.switchTo().defaultContent();

        return status;
    }
}
