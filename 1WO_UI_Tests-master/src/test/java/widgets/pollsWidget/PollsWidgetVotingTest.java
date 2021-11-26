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
import portalPages.polls.polls.PollCategory;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import utils.DriverFactory;

import java.util.Date;

import static base.enums.DefaultContent.POLL_ANSWER_ONLY_TEXT;
import static base.enums.DefaultContent.RANDOM_POLL_QUESTION_TEXT_WITH_DATE;

public class PollsWidgetVotingTest extends DriverFactory {

    PollerWidgetPreviewPage pollerWidgetPreviewPage;
    AccountsInfoPage accountsInfoPage;

    SignUpPage icoSignUpPage;
    ProfilePage icoProfilePage;
    DashboardPage icoDashboardPage;

    Date date;
    APIPollerWidget apiPollerWidget;

    String loginPublisher;
    String passwordPublisher;
    String partnerId;
    String partnerCookie;
    String randomUserFirstName;
    String randomUserLastName;
    String randomUserPassword;
    String widgetName;
    String poll1Text;
    String poll2Text;
    String answerText1;
    String answerText2;
    String categoryId;
    String defaultPollType;
    String defaultLocale;

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
        accountsInfoPage = new AccountsInfoPage(driver);
        icoSignUpPage = new SignUpPage(driver);
        icoProfilePage = new ProfilePage(driver);
        icoDashboardPage = new DashboardPage(driver);
        pollerWidgetPreviewPage = new PollerWidgetPreviewPage(driver);
        date = new Date();
        apiPollerWidget = new APIPollerWidget();

        randomUserFirstName = Accounts.RANDOM_USER_FIRST_NAME.toString();
        randomUserLastName = Accounts.RANDOM_USER_LAST_NAME.toString();
        randomUserPassword = Accounts.RANDOM_USER_PASSWORD.toString();

        categoryId = PollCategory.CATEGORY_VALUE_ART_CULTURE.toString();
        defaultPollType = "dpoll";
        defaultLocale = "en";

        driver.manage().deleteAllCookies();
    }

    @AfterMethod
    public void logOutIfNeed() {
//        UtilityHelper.deleteAllCookies(driver);
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void votingPollerWidgetFromAnonymousToSynthetic() {

        widgetName = DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString();
        poll1Text = String.format(String.valueOf(RANDOM_POLL_QUESTION_TEXT_WITH_DATE), "PollText1", date);
        poll2Text = String.format(String.valueOf(RANDOM_POLL_QUESTION_TEXT_WITH_DATE), "PollText2", date);
        answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

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

        Assertions.assertThat(accountsInfoPage.isUserAnonymous())
                .as("User isn't Anonim")
                .isTrue();

        pollerWidgetPreviewPage.openPollerWidgetPreview(newWidgetId);
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

        String randomUserLogin = String.format(Accounts.RANDOM_USER_LOGIN_MAILINATOR.toString(), System.currentTimeMillis());
        String pollQuestionText = DefaultContent.RANDOM_POLL_QUESTION_ONLY_DATE.toString();
        widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = apiPollerWidget.owoCodeNewWidgetWithMultiplePolls(partnerId,
                partnerCookie,
                widgetName,
                3,
                pollQuestionText,
                answerText1,
                answerText2,
                categoryId,
                defaultPollType,
                defaultLocale);

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserAnonymous())
                .as("User isn't Anonim")
                .isTrue();

        pollerWidgetPreviewPage.openPollerWidgetPreview(newWidgetId);
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
                randomUserFirstName,
                randomUserLastName,
                randomUserPassword,
                randomUserLogin);
        icoSignUpPage.openProfilePage();

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

        Response newPoll1 = new APIPoll().newPollRequest(partnerId, partnerCookie, poll1Text, poll1Answer1, poll1Answer2, categoryId, defaultPollType, defaultLocale);
        Integer pollId1 = new APIPoll().getIntegerValueFromResponse(newPoll1, APIValue.ID.toString());

        Response newPoll2 = new APIPoll().newPollRequest(partnerId, partnerCookie, poll2Text, poll2Answer1, poll2Answer2, categoryId, defaultPollType, defaultLocale);
        Integer pollId2 = new APIPoll().getIntegerValueFromResponse(newPoll2, APIValue.ID.toString());

        Response responseAddEmptyWidget = new APIPollerWidget().newPollerWidgetRequest(partnerId, partnerCookie, widgetTitle);
        String owoCodePollerWidget = new APIPollerWidget().getStringValueFromResponse(responseAddEmptyWidget, APIValue.OWO_WIDGET_CODE.toString());

        new APIPollerWidget().add2PollsInWidget(partnerId, partnerCookie, pollId1, pollId2, owoCodePollerWidget);

        return owoCodePollerWidget;
    }
}
