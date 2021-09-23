package widgets.pollsWidget;

import base.enums.Accounts;
import base.enums.DefaultContent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.AccountsInfoPage;
import portalPages.MenuProfileDropDown;
import portalPages.polls.polls.PollsPage;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import portalPages.polls.widgets.pollerWidgetsPages.PollerWidgetsPage;
import portalPages.publisher.PublisherLoginPage;
import utils.DriverFactory;

public class PollsWidgetVotingTest extends DriverFactory {
    PublisherLoginPage publisherLoginPage;
    PollsPage pollsPage;
    PollerWidgetsPage pollerWidgetsPage;
    PollerWidgetPreviewPage pollerWidgetPreviewPage;
    MenuProfileDropDown menuProfileDropDown;
    AccountsInfoPage accountsInfoPage;

    String loginPublisher;
    String passwordPublisher;

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
        System.out.println(loginPublisher + " / " + passwordPublisher);
    }

    @BeforeMethod
    public void loginPublisher() {
        publisherLoginPage = new PublisherLoginPage(driver);
        pollsPage = new PollsPage(driver);
        pollerWidgetsPage = new PollerWidgetsPage(driver);
        pollerWidgetPreviewPage = new PollerWidgetPreviewPage(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);
        accountsInfoPage = new AccountsInfoPage(driver);

        publisherLoginPage.getPublisherLoginPage()
                .loginPublisher(loginPublisher, passwordPublisher)
                .openPollsPage();
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
    public void votingPollerWidgetByAnonymousToSynthetic() {
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT.toString(), "1");

        String pollQuestionText1 = String.format(DefaultContent.RANDOM_POLL_QUESTION_TEXT.toString(), "1");
        String pollAnswerText1 = String.format(DefaultContent.RANDOM_POLL_ANSWER_TEXT.toString(), "1");
        String pollAnswerText2 = String.format(DefaultContent.RANDOM_POLL_ANSWER_TEXT.toString(), "2");


        pollsPage.addNewPoll(pollQuestionText1, pollAnswerText1, pollAnswerText2);
        pollsPage.startNewWidgetCreating()
                .newWidgetDefaultLanguage(widgetName)
                .nextButtonClick()
                .addPollToWidget(pollQuestionText1)
                .nextButtonClick()
                .nextButtonClick()
                .finishButtonClick();

        String widgetOWOCode = pollerWidgetsPage.getWidgetOWOCode(widgetName);

        menuProfileDropDown.logOut();

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserAnonim())
                .as("User isn't Anonim")
                .isTrue();

        pollerWidgetsPage.openPollerWidgetPreviewPage(widgetOWOCode);
        pollerWidgetPreviewPage.voteAnswer(String.format(DefaultContent.RANDOM_POLL_ANSWER_TEXT.toString(), "1"));

        Assertions.assertThat(pollerWidgetPreviewPage.isPollsPercentsDisplayed(String.format(DefaultContent.RANDOM_POLL_ANSWER_TEXT.toString(), "1")))
                .as("Vote from member wasn't counted")
                .isTrue();

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserSynthetic())
                .as("User isn't Member")
                .isTrue();
    }
}
