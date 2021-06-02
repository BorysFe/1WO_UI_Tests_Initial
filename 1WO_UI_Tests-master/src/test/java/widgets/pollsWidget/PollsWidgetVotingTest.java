package widgets.pollsWidget;

import base.enums.Accounts;
import base.enums.WidgetDefaultContent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.FeedPage;
import portalPages.polls.polls.PollsPage;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import portalPages.polls.widgets.pollerWidgetsPages.PollerWidgetsPage;
import portalPages.publisher.PublisherLoginPage;
import utils.DriverFactory;

public class PollsWidgetVotingTest extends DriverFactory {
    FeedPage feedPage;
    PublisherLoginPage publisherLoginPage;
    PollsPage pollsPage;
    PollerWidgetsPage pollerWidgetsPage;
    PollerWidgetPreviewPage pollerWidgetPreviewPage;

    String loginPublisher;
    String passwordPublisher;

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
        System.out.println(loginPublisher + " / " + passwordPublisher);
    }

    @BeforeMethod
    public void pagesDriver() {
        feedPage = new FeedPage(driver);
        publisherLoginPage = new PublisherLoginPage(driver);
        pollsPage = new PollsPage(driver);
        pollerWidgetsPage = new PollerWidgetsPage(driver);
        pollerWidgetPreviewPage = new PollerWidgetPreviewPage(driver);

        publisherLoginPage.getPublisherLoginPage()
                .loginPublisher(loginPublisher, passwordPublisher)
                .openPollsPage();
    }

    @AfterMethod
    public void logOutIfNeed() {
        feedPage.logOutIfNeed();
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void votingDefaultPollerWidget() {
        String widgetName = String.format(WidgetDefaultContent.DEFAULT_WIDGET_NAME.toString(), "1");

        String pollQuestionText1 = String.format(WidgetDefaultContent.POLL_QUESTION_TEXT.toString(), "1");
        String pollAnswerText1 = String.format(WidgetDefaultContent.POLL_ANSWER_TEXT.toString(), "1");
        String pollQuestionText2 = String.format(WidgetDefaultContent.POLL_QUESTION_TEXT.toString(), "2");
        String pollAnswerText2 = String.format(WidgetDefaultContent.POLL_ANSWER_TEXT.toString(), "2");


        pollsPage.addNewPoll(pollQuestionText1, pollAnswerText1, pollAnswerText2);
        pollsPage.addNewPoll(pollQuestionText2, pollAnswerText1, pollAnswerText2);
        pollsPage.startNewWidgetCreating()
                .newWidgetDefaultLanguage(widgetName)
                .nextButtonClick()
                .addPollToWidget(pollQuestionText1)
                .addPollToWidget(pollQuestionText2)
                .nextButtonClick()
                .nextButtonClick()
                .finishButtonClick();

        pollerWidgetsPage.openPollerWidgetPreviewPage(widgetName);
        pollerWidgetPreviewPage.voteAnswer(String.format(WidgetDefaultContent.POLL_ANSWER_TEXT.toString(), "1"));

        Assertions.assertThat(pollerWidgetPreviewPage.isPollsPercentsDisplayed(String.format(WidgetDefaultContent.POLL_ANSWER_TEXT.toString(), "1")))
                .as("Vote from member wasn't counted")
                .isTrue();
    }
}
