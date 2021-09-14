package ico;

import base.enums.Accounts;
import base.enums.DefaultContent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.AccountsInfoPage;
import portalPages.FeedPage;
import portalPages.MenuProfileDropDown;
import portalPages.polls.polls.PollsPage;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import portalPages.polls.widgets.pollerWidgetsPages.PollerWidgetsPage;
import portalPages.publisher.PublisherLoginPage;
import utils.DriverFactory;

public class SignUpMemberFromWidgetTest extends DriverFactory {
    FeedPage feedPage;
    PublisherLoginPage publisherLoginPage;
    PollsPage pollsPage;
    PollerWidgetsPage pollerWidgetsPage;
    PollerWidgetPreviewPage pollerWidgetPreviewPage;
    AccountsInfoPage accountInfoPage;
    MenuProfileDropDown menuProfileDropDown;

    String loginAdmin;
    String passwordAdmin;
    String pollQuestionText1;
    String pollAnswerText1;
    String pollQuestionText2;
    String pollAnswerText2;

    @BeforeClass
    public void memberCredentials() {
        loginAdmin = Accounts.ADMIN_LOGIN.toString();
        passwordAdmin = Accounts.ADMIN_PASSWORD.toString();
        System.out.println(loginAdmin + " / " + passwordAdmin);
    }

    @BeforeMethod
    public void pagesDriver() {
        feedPage = new FeedPage(driver);
        publisherLoginPage = new PublisherLoginPage(driver);
        pollsPage = new PollsPage(driver);
        pollerWidgetsPage = new PollerWidgetsPage(driver);
        pollerWidgetPreviewPage = new PollerWidgetPreviewPage(driver);
        accountInfoPage = new AccountsInfoPage(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);

        publisherLoginPage.getPublisherLoginPage()
                .loginPublisher(loginAdmin, passwordAdmin)
                .openPollsPage();

        pollQuestionText1 = String.format(DefaultContent.POLL_QUESTION_TEXT.toString(), "1");
        pollAnswerText1 = String.format(DefaultContent.POLL_ANSWER_TEXT.toString(), "1");
        pollQuestionText2 = String.format(DefaultContent.POLL_QUESTION_TEXT.toString(), "2");
        pollAnswerText2 = String.format(DefaultContent.POLL_ANSWER_TEXT.toString(), "2");

        pollsPage.addNewPoll(pollQuestionText1, pollAnswerText1, pollAnswerText2);
        pollsPage.addNewPoll(pollQuestionText2, pollAnswerText1, pollAnswerText2);
    }

    @AfterMethod
    public void logOutIfNeed() {
        menuProfileDropDown.tryLogOut();
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void signUpMemberAfterVote() {
        String widgetName = String.format(DefaultContent.DEFAULT_WIDGET_NAME.toString(), "1");

        pollsPage.startNewWidgetCreating()
                .newWidgetDefaultLanguage(widgetName)
                .nextButtonClick()
                .addPollToWidget(pollQuestionText1)
                .addPollToWidget(pollQuestionText2)
                .nextButtonClick()
                .selectCheckBox("Show user's score**")
                .nextButtonClick()
                .finishButtonClick();

        pollerWidgetsPage.openPollerWidgetPreviewPage(widgetName);
        pollerWidgetPreviewPage.voteAnswer(String.format(DefaultContent.POLL_ANSWER_TEXT.toString(), "1"));

        Assertions.assertThat(pollerWidgetPreviewPage.getUsersScore())
                .as("Vote from member wasn't counted")
                .isEqualTo("10");

        accountInfoPage.openAccountInfoPage();


    }
}
