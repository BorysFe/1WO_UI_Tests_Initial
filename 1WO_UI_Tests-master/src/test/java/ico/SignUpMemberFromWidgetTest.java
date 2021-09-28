package ico;

import base.enums.Accounts;
import base.enums.DefaultContent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import base.AccountsInfoPage;
import portalPages.MenuProfileDropDown;
import portalPages.polls.polls.PollsPage;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import portalPages.polls.widgets.pollerWidgetsPages.ContentBuilderPage;
import portalPages.polls.widgets.pollerWidgetsPages.DesktopSettingsAndPreviewPage;
import portalPages.polls.widgets.pollerWidgetsPages.MobileSettingsAndPreviewPage;
import portalPages.polls.widgets.pollerWidgetsPages.PollerWidgetsPage;
import portalPages.publisher.PublisherLoginPage;
import utils.DriverFactory;

public class SignUpMemberFromWidgetTest extends DriverFactory {
    PublisherLoginPage publisherLoginPage;
    MenuProfileDropDown menuProfileDropDown;

    PollsPage pollsPage;
    PollerWidgetsPage pollerWidgetsPage;
    PollerWidgetPreviewPage pollerWidgetPreviewPage;
    ContentBuilderPage contentBuilderPage;
    DesktopSettingsAndPreviewPage desktopSettingsAndPreviewPage;
    MobileSettingsAndPreviewPage mobileSettingsAndPreviewPage;
    AccountsInfoPage accountInfoPage;

    String loginPublisher;
    String passwordPublisher;
    String pollQuestionText1;
    String pollAnswerText1;
    String pollQuestionText2;
    String pollAnswerText2;

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
        System.out.println(loginPublisher + " / " + passwordPublisher);
    }

    @BeforeMethod
    public void creatingPoll() {
        publisherLoginPage = new PublisherLoginPage(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);

        pollsPage = new PollsPage(driver);
        pollerWidgetsPage = new PollerWidgetsPage(driver);
        pollerWidgetPreviewPage = new PollerWidgetPreviewPage(driver);
        contentBuilderPage = new ContentBuilderPage(driver);
        desktopSettingsAndPreviewPage = new DesktopSettingsAndPreviewPage(driver);
        mobileSettingsAndPreviewPage = new MobileSettingsAndPreviewPage(driver);

        accountInfoPage = new AccountsInfoPage(driver);

        publisherLoginPage.getPublisherLoginPage()
                .loginPublisher(loginPublisher, passwordPublisher)
                .openPollsPage();

        pollQuestionText1 = String.format(DefaultContent.RANDOM_POLL_QUESTION_TEXT.toString(), "1");
        pollAnswerText1 = String.format(DefaultContent.RANDOM_POLL_ANSWER_TEXT.toString(), "1");
        pollQuestionText2 = String.format(DefaultContent.RANDOM_POLL_QUESTION_TEXT.toString(), "2");
        pollAnswerText2 = String.format(DefaultContent.RANDOM_POLL_ANSWER_TEXT.toString(), "2");

        pollsPage.addNewPoll(pollQuestionText1, pollAnswerText1, pollAnswerText2);
        pollsPage.addNewPoll(pollQuestionText2, pollAnswerText1, pollAnswerText2);
    }

    @AfterMethod
    public void logOutIfNeed() {
        menuProfileDropDown.tryLogOut();
    }

    @Test
    public void signUpMemberAfterVote() {
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT.toString(), "1");

        pollsPage.startNewWidgetCreating()
                .newWidgetDefaultLanguage(widgetName)
                .nextButtonClick();
        contentBuilderPage.addPollToWidget(pollQuestionText1)
                .addPollToWidget(pollQuestionText2)
                .nextButtonClick();
        desktopSettingsAndPreviewPage.nextButtonClick();
        mobileSettingsAndPreviewPage.finishButtonClick();

        pollerWidgetsPage.openPollerWidgetPreviewPage(widgetName);
        pollerWidgetPreviewPage.voteAnswer(String.format(DefaultContent.RANDOM_POLL_ANSWER_TEXT.toString(), "1"));

        Assertions.assertThat(pollerWidgetPreviewPage.isAnswerVoted(pollAnswerText1))
                .as("Vote from user wasn't counted")
                .isTrue();

        accountInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountInfoPage.isUserSynthetic())
                .as("User isn't synthetic")
                .isTrue();
    }
}
