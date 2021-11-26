package widgets.pollsWidget;

import apiMain.portal.pollerWidget.APIPollerWidget;
import base.AccountsInfoPage;
import base.enums.Accounts;
import base.enums.DefaultContent;
import icoPages.DashboardPage;
import icoPages.ProfilePage;
import icoPages.SignUpPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.polls.polls.PollCategory;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import utils.DriverFactory;
import utils.UtilityHelper;

import java.util.Date;

import static base.enums.DefaultContent.POLL_ANSWER_ONLY_TEXT;

public class PollerWidgetPreviewTest extends DriverFactory {

    AccountsInfoPage accountsInfoPage;
    PollerWidgetPreviewPage widgetPreview;

    SignUpPage icoSignUpPage;
    ProfilePage icoProfilePage;
    DashboardPage icoDashboardPage;

    Date date;
    APIPollerWidget apiPollerWidget;

    String partnerId;
    String partnerCookie;
    String loginPublisher;
    String passwordPublisher;

    String randomUserFirstName;
    String randomUserLastName;
    String randomUserPassword;

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
    public void creatingNewWidget() {
        accountsInfoPage = new AccountsInfoPage(driver);
        widgetPreview = new PollerWidgetPreviewPage(driver);
        icoSignUpPage = new SignUpPage(driver);
        icoProfilePage = new ProfilePage(driver);
        icoDashboardPage = new DashboardPage(driver);
        widgetPreview = new PollerWidgetPreviewPage(driver);
        date = new Date();
        apiPollerWidget = new APIPollerWidget();

        randomUserFirstName = Accounts.RANDOM_USER_FIRST_NAME.toString();
        randomUserLastName = Accounts.RANDOM_USER_LAST_NAME.toString();
        randomUserPassword = Accounts.RANDOM_USER_PASSWORD.toString();

        categoryId = PollCategory.CATEGORY_VALUE_ART_CULTURE.toString();
        defaultPollType = "dpoll";
        defaultLocale = "en";

        accountsInfoPage.openAccountInfoPage();
        driver.manage().deleteAllCookies();
    }

//    @AfterMethod
//    public void logOutIfNeed() {
//        UtilityHelper.deleteAllCookies(driver);
//    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void percentsNotShowedWithoutVote() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - percents");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);

        Assertions.assertThat(widgetPreview.isPollsPercentsDisplayed(answerText1))
                .as("Percents for answer " + answerText1 + " is displayed")
                .isFalse();

        Assertions.assertThat(widgetPreview.isPollsPercentsDisplayed(answerText2))
                .as("Percents for answer " + answerText2 + " is displayed")
                .isFalse();
    }

    @Test
    public void percentsShowedAfterVote() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(),
                date + " - percents after vote");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);
        widgetPreview.voteAnswer(answerText1);

        Assertions.assertThat(widgetPreview.isPollsPercentsDisplayed(answerText1))
                .as("Percents for answer " + answerText1 + "- isn't displayed")
                .isTrue();
    }

    @Test
    public void statisticButtonDisplayed() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Statistic Button");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);

        Assertions.assertThat(widgetPreview.isStatisticButtonDisplayed())
                .as("Statistic icon isn't displayed")
                .isTrue();
    }

    @Test
    public void statisticHoverTextDisplayed() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Statistic Hover");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);

        Assertions.assertThat(widgetPreview.isStatisticHoverTextDisplayed())
                .as("Statistic hover text isn't displayed")
                .isTrue();
    }

    @Test
    public void scorePointsDisplayed() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Score Points");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);

        Assertions.assertThat(widgetPreview.isScorePointsDisplayed())
                .as("Score Points isn't displayed")
                .isTrue();
    }

    //    @Test
    public void scorePointsCountedAfterVote() {
        String pollQuestionText = DefaultContent.RANDOM_POLL_QUESTION_ONLY_DATE.toString();
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);

        widgetPreview.voteAnswer(answerText1);
        widgetPreview.getVotesValueAfterPageReload(1);

        Assertions.assertThat(widgetPreview.getUsersScore())
                .as("Score Points isn't displayed")
                .isEqualTo("10");
    }

    @Test
    public void sharingArrowDisplayed() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Sharing arrow");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);

        Assertions.assertThat(widgetPreview.isSharingArrowDisplayed())
                .as("Sharing arrow doesn't showed")
                .isTrue();
    }

    @Test
    public void sharingFacebookDisplayed() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Sharing - Facebook");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);

        Assertions.assertThat(widgetPreview.isSharingFacebookLinkDisplayed())
                .as("Social network Facebook doesn't showed")
                .isTrue();
    }

    @Test
    public void sharingTwitterDisplayed() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Sharing - Twitter");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);

        Assertions.assertThat(widgetPreview.isSharingTwitterLinkDisplayed())
                .as("Social network Twitter doesn't showed")
                .isTrue();
    }

    @Test
    public void sharingLinkedInDisplayed() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Sharing - LinkedIn");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT_WITH_DATE.toString(), "Sharing - LinkedIn");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        widgetPreview.openPollerWidgetPreview(newWidgetId);

        Assertions.assertThat(widgetPreview.isSharingLinkedInLinkDisplayed())
                .as("Social network LinkedIn doesn't showed")
                .isTrue();
    }

    @Test
    public void votingBetaPollerWidgetFromAnonymousToMember() {
        String randomUserLogin = String.format(Accounts.RANDOM_USER_LOGIN_MAILINATOR.toString(), System.currentTimeMillis());
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT.toString(), date + " - Anonymous -> Member");
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Anonymous -> Member");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserAnonymous())
                .as("User isn't Anonim")
                .isTrue();

        widgetPreview.openPollerWidgetPreview(newWidgetId);
        widgetPreview.voteAnswer(answerText1);

        Assertions.assertThat(widgetPreview.
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

    @Test
    public void votingPollerWidgetFromAnonymousToSynthetic() {
        String pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Anonymous -> Member");
        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT.toString(), date + " - Anonymous -> Member");
        String answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        String answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                1);

        accountsInfoPage.openAccountInfoPage();


        Assertions.assertThat(accountsInfoPage.isUserAnonymous())
                .as("User isn't Anonim")
                .isTrue();

        widgetPreview.openPollerWidgetPreview(newWidgetId);
        widgetPreview.voteAnswer(answerText1);

        Assertions.assertThat(widgetPreview.
                isPollsPercentsDisplayed(answerText1))
                .as("Percents not showed for the voted answer")
                .isTrue();

        accountsInfoPage.openAccountInfoPage();

        Assertions.assertThat(accountsInfoPage.isUserSynthetic())
                .as("User isn't Member")
                .isTrue();
    }

    private String creatingPollsAndAddingToWidgetViaAPI(String partnerId, String partnerCookie, String widgetName,
                                                        String pollQuestionText, String answerText1, String answerText2,
                                                        int pollCount) {

        String owoCode = apiPollerWidget.owoCodeNewWidgetWithMultiplePolls(partnerId, partnerCookie, widgetName,
                pollCount, pollQuestionText, answerText1, answerText2, categoryId, defaultPollType, defaultLocale);
        return owoCode;
    }
}
