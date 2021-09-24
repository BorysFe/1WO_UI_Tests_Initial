package widgets.pollsWidget;

import apiMain.portal.pollerWidget.APIPoll;
import apiMain.portal.pollerWidget.APIPollerWidget;
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

import static base.enums.DefaultContent.RANDOM_POLL_ANSWER_TEXT;

public class PollsWidgetVotingTest extends DriverFactory {
    PublisherLoginPage publisherLoginPage;
    PollsPage pollsPage;
    PollerWidgetsPage pollerWidgetsPage;
    PollerWidgetPreviewPage pollerWidgetPreviewPage;
    MenuProfileDropDown menuProfileDropDown;
    AccountsInfoPage accountsInfoPage;

    String loginPublisher;
    String passwordPublisher;
    String partnerId;
    String partnerCookie;

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
        partnerId = Accounts.PUBLISHER_ID.toString();
        partnerCookie = Accounts.PUBLISHER_COOKIE.toString();
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

//        publisherLoginPage.getPublisherLoginPage()
//                .loginPublisher(loginPublisher, passwordPublisher)
//                .openPollsPage();
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

        Integer pollId1 = new APIPoll().getIdForNewRandomPoll(partnerId, partnerCookie, "Text1");
        Integer pollId2 = new APIPoll().getIdForNewRandomPoll(partnerId, partnerCookie, "Text2");
        String answerText1 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), "Text11");

        String newWidgetId = new APIPollerWidget().getIdForNewPollerWidget(partnerId, partnerCookie, widgetName);

        System.out.println("Widget - " + newWidgetId + "; Poll1 - " + pollId1 + "; Poll2 - " + pollId2);

        new APIPollerWidget().adding2PollsInWidget(partnerId, partnerCookie, pollId1, pollId2, newWidgetId);

        menuProfileDropDown.logOut();

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserAnonim())
                .as("User isn't Anonim")
                .isTrue();

        pollerWidgetsPage.openPollerWidgetPreviewPageByOWOCode(newWidgetId);
        pollerWidgetPreviewPage.voteAnswer(answerText1);

        Assertions.assertThat(pollerWidgetPreviewPage.isPollsPercentsDisplayed(String.format(DefaultContent.RANDOM_POLL_ANSWER_TEXT.toString(), "Text11")))
                .as("Vote from member wasn't counted")
                .isTrue();

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserSynthetic())
                .as("User isn't Member")
                .isTrue();
    }
}
