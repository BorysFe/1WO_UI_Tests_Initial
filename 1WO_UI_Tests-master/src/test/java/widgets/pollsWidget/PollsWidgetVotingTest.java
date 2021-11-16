package widgets.pollsWidget;

import apiMain.APIValue;
import apiMain.portal.pollerWidget.APIPoll;
import apiMain.portal.pollerWidget.APIPollerWidget;
import base.enums.Accounts;
import base.enums.DefaultContent;
import icoPages.DashboardPage;
import icoPages.ProfilePage;
import icoPages.SignUpPage;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import base.AccountsInfoPage;
import portalPages.MenuProfileDropDown;
import portalPages.polls.polls.PollCategory;
import portalPages.polls.polls.PollsPage;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import portalPages.polls.widgets.pollerWidgetsPages.PollerWidgetsPage;
import portalPages.publisher.PublisherLoginPage;
import utils.DriverFactory;
import utils.UtilityHelper;

import static base.enums.DefaultContent.RANDOM_POLL_ANSWER_TEXT;

public class PollsWidgetVotingTest extends DriverFactory {
    PublisherLoginPage publisherLoginPage;
    PollsPage pollsPage;
    PollerWidgetsPage pollerWidgetsPage;
    PollerWidgetPreviewPage pollerWidgetPreviewPage;
    MenuProfileDropDown menuProfileDropDown;
    AccountsInfoPage accountsInfoPage;

    SignUpPage icoSignUpPage;
    ProfilePage icoProfilePage;
    DashboardPage icoDashboardPage;

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
        icoSignUpPage = new SignUpPage(driver);
        icoProfilePage = new ProfilePage(driver);
        icoDashboardPage = new DashboardPage(driver);
//        helper = new UtilityHelper();

//        publisherLoginPage.getPublisherLoginPage()
//                .loginPublisher(loginPublisher, passwordPublisher)
//                .openPollsPage();
    }

    @AfterMethod
    public void logOutIfNeed() {
        menuProfileDropDown.tryLogOut();
        UtilityHelper.deleteAllCookies(driver);
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void votingPollerWidgetFromAnonymousToSynthetic() {

        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT.toString(), "1");
        String poll1Text = "PollText1";
        String poll2Text = "PollText2";
        String answerText1 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), "AnswerText2");


        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                poll1Text,
                answerText1,
                answerText2,
                poll2Text,
                answerText1,
                answerText2);

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserAnonim())
                .as("User isn't Anonim")
                .isTrue();

        pollerWidgetsPage.openPollerWidgetPreviewPageByOWOCode(newWidgetId);
        pollerWidgetPreviewPage.voteAnswer(answerText1);

        Assertions.assertThat(pollerWidgetPreviewPage.
                isPollsPercentsDisplayed(answerText1))
                .as("Percents not showed for the voted answer")
                .isTrue();

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserSynthetic())
                .as("User isn't Member")
                .isTrue();
    }

    @Test
    public void votingPollerWidgetFromAnonymousToMember() {

        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT.toString(), "1");
        String poll1Text = "PollText1";
        String poll2Text = "PollText2";
        String answerText1 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), "AnswerText2");


        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                poll1Text,
                answerText1,
                answerText2,
                poll2Text,
                answerText1,
                answerText2);

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserAnonim())
                .as("User isn't Anonim")
                .isTrue();

        pollerWidgetsPage.openPollerWidgetPreviewPageByOWOCode(newWidgetId);
        pollerWidgetPreviewPage.voteAnswer(answerText1);

        Assertions.assertThat(pollerWidgetPreviewPage.
                isPollsPercentsDisplayed(answerText1))
                .as("Percents not showed for the voted answer")
                .isTrue();

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserSynthetic())
                .as("User isn't Member")
                .isTrue();

        icoSignUpPage.openSignUpPage().fillSignUpForm(
                Accounts.RANDOM_USER_FIRST_NAME.toString(),
                Accounts.RANDOM_USER_LAST_NAME.toString(),
                Accounts.RANDOM_USER_PASSWORD.toString(),
                Accounts.RANDOM_USER_LOGIN_MAILINATOR.toString());
        icoSignUpPage.openProfilePage();
        icoProfilePage.openDashboardPage();

        Assertions.assertThat(icoDashboardPage.getTotalPointsScore())
                .as("Displayed Total Point Score is incorrect")
                .isEqualTo("110");

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserMember())
                .as("User isn't Member")
                .isTrue();
    }

    private String creatingPollsAndAddingToWidgetViaAPI(String partnerId,
                                                        String partnerCookie,
                                                        String widgetTitle,
                                                        String poll1Text,
                                                        String poll1Answer1,
                                                        String poll1Answer2,
                                                        String poll2Text,
                                                        String poll2Answer1,
                                                        String poll2Answer2) {

        String categoryId = PollCategory.CATEGORY_VALUE_ART_CULTURE.toString();
        String defaultPollType = "dpoll";
        String defaultLocale = "en";

        Response newPoll1 = new APIPoll().NewPollRequest(partnerId, partnerCookie, poll1Text, poll1Answer1, poll1Answer2, categoryId, defaultPollType, defaultLocale);
        Integer pollId1 = new APIPoll().getIntegerValueFromResponse(newPoll1, APIValue.ID.toString());

        Response newPoll2 = new APIPoll().NewPollRequest(partnerId, partnerCookie, poll2Text, poll2Answer1, poll2Answer2, categoryId, defaultPollType, defaultLocale);
        Integer pollId2 = new APIPoll().getIntegerValueFromResponse(newPoll2, APIValue.ID.toString());

        Response responseAddEmptyWidget = new APIPollerWidget().NewPollerWidgetRequest(partnerId, partnerCookie, widgetTitle);
        String owoCodePollerWidget = new APIPollerWidget().getStringValueFromResponse(responseAddEmptyWidget, APIValue.OWO_WIDGET_CODE.toString());

        new APIPollerWidget().adding2PollsInWidget(partnerId, partnerCookie, pollId1, pollId2, owoCodePollerWidget);

        return owoCodePollerWidget;
    }
}
