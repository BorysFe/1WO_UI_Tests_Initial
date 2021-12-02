package widgets.pollsWidget;

import apiMain.portal.pollerWidget.APIPollerWidget;
import base.AccountsInfoPage;
import base.enums.Accounts;
import base.enums.DefaultContent;
import org.testng.annotations.*;
import portalPages.polls.polls.PollCategory;
import portalPages.widgets.BetaPollerWidgetPreviewPage;
import portalPages.widgets.WidgetPreviewPage;
import utils.UtilityHelper;

import java.util.Date;

import static base.enums.DefaultContent.POLL_ANSWER_ONLY_TEXT;

public class BetaPollerWidgetPreviewTest extends AbstractPollerWidgetTests {

    AccountsInfoPage accountsInfoPage;
    BetaPollerWidgetPreviewPage widgetPreview;

    Date date;
    APIPollerWidget apiPollerWidget;

    String partnerId;
    String partnerCookie;

    String pollQuestionText;
    String answerText1;
    String answerText2;
    String widgetName;

    String categoryId;
    String defaultPollType;
    String defaultLocale;

    @Override
    protected WidgetPreviewPage getWidgetPreview() {
        return widgetPreview;
    }

    @BeforeClass
    public void memberCredentials() {
        partnerId = Accounts.PUBLISHER_ID.toString();
        partnerCookie = Accounts.PUBLISHER_COOKIE.toString();
    }

    @BeforeMethod
    public void creatingNewWidget() {
        accountsInfoPage = new AccountsInfoPage(driver);
        widgetPreview = new BetaPollerWidgetPreviewPage(driver);
        date = new Date();
        apiPollerWidget = new APIPollerWidget();

        widgetName = String.format(DefaultContent.RANDOM_WIDGET_NAME_TEXT.toString(), date + " - Anonymous -> Member");
        pollQuestionText = String.format(DefaultContent.POLL_QUESTION_ONLY_TEXT.toString(), date + " - Anonymous -> Member");
        answerText1 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText1");
        answerText2 = String.format(String.valueOf(POLL_ANSWER_ONLY_TEXT), "AnswerText2");

        categoryId = PollCategory.CATEGORY_VALUE_ART_CULTURE.toString();
        defaultPollType = "dpoll";
        defaultLocale = "en";

        accountsInfoPage.openAccountInfoPage();
        UtilityHelper.deleteAllCookies(driver);

        String newWidgetId = creatingPollsAndAddingToWidgetViaAPI(partnerId,
                partnerCookie,
                widgetName,
                pollQuestionText,
                answerText1,
                answerText2,
                2);

        widgetPreview.openPollerWidgetPreview(newWidgetId);
    }

    private String creatingPollsAndAddingToWidgetViaAPI(String partnerId, String partnerCookie, String widgetName,
                                                        String pollQuestionText, String answerText1, String answerText2,
                                                        int pollCount) {

        String owoCode = apiPollerWidget.owoCodeNewBetaWidgetWithMultiplePolls(partnerId, partnerCookie, widgetName,
                pollCount, pollQuestionText, answerText1, answerText2, categoryId, defaultPollType, defaultLocale);
        return owoCode;
    }
}
