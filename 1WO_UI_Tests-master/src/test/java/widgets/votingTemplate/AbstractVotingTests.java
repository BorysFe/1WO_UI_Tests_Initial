package widgets.votingTemplate;

import apiMain.portal.pollerWidget.APIPollerWidget;
import base.AccountsInfoPage;
import base.enums.Accounts;
import base.enums.DefaultContent;
import icoPages.DashboardPage;
import icoPages.ProfilePage;
import icoPages.SignUpPage;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.polls.polls.PollCategory;
import portalPages.widgets.PollerWidgetPreviewPage;
import portalPages.widgets.WidgetPreviewPage;
import utils.DriverFactory;

import java.util.Date;

import static base.enums.DefaultContent.POLL_ANSWER_ONLY_TEXT;


@Setter
@Getter
public abstract class AbstractVotingTests extends DriverFactory {

    AccountsInfoPage accountsInfoPage;
    PollerWidgetPreviewPage pollerWidgetPreviewPage;

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
    String pollQuestionText;
    String answerText1;
    String answerText2;

    String categoryId;
    String defaultPollType;
    String defaultLocale;

    String newWidgetId;

    protected abstract WidgetPreviewPage getWidgetPreview();

    protected abstract AccountsInfoPage accountsInfoPage();

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
        partnerId = Accounts.PUBLISHER_ID.toString();
        partnerCookie = Accounts.PUBLISHER_COOKIE.toString();
        System.out.println(loginPublisher + " / abstract " + passwordPublisher);
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

        widgetName = DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString();
        pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(),
                date + " - Anonymous -> Synthetic");
        answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");
//
        newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                2);

        accountsInfoPage.openAccountInfoPage();
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void votingPollerWidgetFromAnonymousToSynthetic() {
//        widgetName = DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString();
//        pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(),
//                date + " - Anonymous -> Synthetic");
//        answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
//        answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");
//
//        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
//                partnerCookie,
//                widgetName,
//                pollQuestionText,
//                answerText1,
//                answerText2,
//                2);
        accountsInfoPage().openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage().isUserAnonymous())
                .as("User isn't Anonim")
                .isTrue();

        getWidgetPreview().openPollerWidgetPreview(newWidgetId);
        getWidgetPreview().voteAnswer(answerText1);

        Assertions.assertThat(getWidgetPreview().
                isPollsPercentsDisplayed(answerText1))
                .as("Percents not showed for the voted answer")
                .isTrue();

        accountsInfoPage().openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage().isUserSynthetic())
                .as("User isn't Member")
                .isTrue();
    }

    @Test
    public void votingPollerWidgetFromAnonymousToMember() {
        String randomUserLogin = String.format(Accounts.RANDOM_USER_LOGIN_MAILINATOR.toString(), System.currentTimeMillis());
//        widgetName = DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString();
//        pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(),
//                date + " - Anonymous -> Member");
//        answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
//        answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");
//
//        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
//                partnerCookie,
//                widgetName,
//                pollQuestionText,
//                answerText1,
//                answerText2,
//                2);
//        accountsInfoPage().openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage().isUserAnonymous())
                .as("User isn't Anonim")
                .isTrue();

        getWidgetPreview().openPollerWidgetPreview(newWidgetId);
        getWidgetPreview().voteAnswer(answerText1);

        Assertions.assertThat(getWidgetPreview().
                isPollsPercentsDisplayed(answerText1))
                .as("Percents not showed for the voted answer")
                .isTrue();

        accountsInfoPage().openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage().isUserSynthetic())
                .as("User isn't Member")
                .isTrue();

        icoSignUpPage.openSignUpPage().fillSignUpForm(
                randomUserFirstName,
                randomUserLastName,
                randomUserPassword,
                randomUserLogin);
        icoSignUpPage.openProfilePage();

        accountsInfoPage().openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage().isUserMember())
                .as("User isn't Member")
                .isTrue();
    }

    private String creatingPollsAndAddingToWidgetViaAPI(String partnerId, String partnerCookie, String widgetName,
                                                        String pollQuestionText, String answerText1, String answerText2,
                                                        int pollCount) {

        String owoCode = apiPollerWidget.owoCodeNewBetaWidgetWithMultiplePolls(partnerId, partnerCookie, widgetName,
                pollCount, pollQuestionText, answerText1, answerText2, categoryId, defaultPollType, defaultLocale);
        return owoCode;
    }
}
