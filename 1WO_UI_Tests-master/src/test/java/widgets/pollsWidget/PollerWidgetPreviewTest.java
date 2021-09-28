package widgets.pollsWidget;

import apiMain.APIValue;
import apiMain.portal.pollerWidget.APIPoll;
import apiMain.portal.pollerWidget.APIPollerWidget;
import base.AccountsInfoPage;
import base.enums.Accounts;
import base.enums.DefaultContent;
import icoPages.DashboardPage;
import icoPages.ProfilePage;
import icoPages.SignUpPage;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.MenuProfileDropDown;
import portalPages.polls.polls.PollCategory;
import portalPages.polls.polls.PollsPage;
import portalPages.polls.widgets.PollerWidgetPreviewPage;
import portalPages.polls.widgets.pollerWidgetsPages.PollerWidgetsPage;
import portalPages.publisher.PublisherLoginPage;
import utils.DriverFactory;
import utils.UtilityHelper;

import static base.enums.DefaultContent.RANDOM_POLL_ANSWER_TEXT;

public class PollerWidgetPreviewTest extends DriverFactory {

    MenuProfileDropDown menuProfileDropDown;
    AccountsInfoPage accountsInfoPage;
    PollerWidgetPreviewPage widgetPreview;

    String partnerId;
    String partnerCookie;
    String loginPublisher;
    String passwordPublisher;
    String poll1Text;
    String poll2Text;
    String answerText1;
    String answerText2;
    String widgetName;


    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
        partnerId = Accounts.PUBLISHER_ID.toString();
        partnerCookie = Accounts.PUBLISHER_COOKIE.toString();
        System.out.println(loginPublisher + " / " + passwordPublisher);
    }

    @BeforeMethod
    public void preconditions() {
        accountsInfoPage = new AccountsInfoPage(driver);
        widgetPreview = new PollerWidgetPreviewPage(driver);

        widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT.toString(), "1");
        poll1Text = "PollText1";
        poll2Text = "PollText2";
        answerText1 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), "AnswerText1");
        answerText2 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), "AnswerText2");


        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                poll1Text,
                answerText1,
                answerText2,
                poll2Text,
                answerText1,
                answerText2);

        widgetPreview.openPollerWidgetPreview(newWidgetId);
    }

    @AfterMethod
    public void logOutIfNeed() {
//        menuProfileDropDown.tryLogOut();
        UtilityHelper.deleteAllCookies(driver);
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void percentsNotShowedWithoutVote() {
//        String widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT.toString(), "1");
//        String poll1Text = "PollText1";
//        String poll2Text = "PollText2";
//        String answerText1 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), "AnswerText1");
//        String answerText2 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), "AnswerText2");
//
//
//        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
//                partnerCookie,
//                widgetName,
//                poll1Text,
//                answerText1,
//                answerText2,
//                poll2Text,
//                answerText1,
//                answerText2);
        Assertions.assertThat(widgetPreview.isPollsPercentsDisplayed(answerText1))
                .as("Percents for answer " + answerText1 + "is displayed")
                .isFalse();

        Assertions.assertThat(widgetPreview.isPollsPercentsDisplayed(answerText2))
                .as("Percents for answer " + answerText2 + "is displayed")
                .isFalse();
    }

//    @Test
//    public void statisticButtonDisplayed() {
//
//    }
//
//    @Test
//    public void scorePointsDisplayed() {
//
//    }
//
//    @Test
//    public void facebookSharingButtonDisplayed() {
//
//    }
//
//    @Test
//    public void twitterSharingButtonDisplayed() {
//
//    }
//
//    @Test
//    public void LinkedInSharingButtonDisplayed() {
//
//    }
//
//    @Test
//    public void scorePointsAddedAfterVote() {
//
//        widgetPreview.voteAnswer(answerText1);
//
//    }

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
