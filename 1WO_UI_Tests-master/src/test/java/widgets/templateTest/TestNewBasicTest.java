package widgets.templateTest;

import base.AccountsInfoPage;
import base.enums.Accounts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.polls.widgets.WidgetPreviewPage;
import utils.DriverFactory;
import utils.UtilityHelper;

@Setter
@Getter
public abstract class TestNewBasicTest extends DriverFactory {

    AccountsInfoPage accountsInfoPage;

    String partnerId;
    String partnerCookie;
    String loginPublisher;
    String passwordPublisher;
    String poll1Text;
    String poll2Text;
    String answerText1;
    String answerText2;
    String widgetName;

    protected abstract WidgetPreviewPage getWidgetPreview();

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
        partnerId = Accounts.PUBLISHER_ID.toString();
        partnerCookie = Accounts.PUBLISHER_COOKIE.toString();
        System.out.println(loginPublisher + " / " + passwordPublisher);
    }

    @AfterMethod
    public void logOutIfNeed() {
        UtilityHelper.deleteAllCookies(driver);
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void percentsNotShowedWithoutVote() {

        Assertions.assertThat(getWidgetPreview().isPollsPercentsDisplayed(answerText1))
                .as("Percents for answer " + answerText1 + " is displayed")
                .isFalse();

        Assertions.assertThat(getWidgetPreview().isPollsPercentsDisplayed(answerText2))
                .as("Percents for answer " + answerText2 + " is displayed")
                .isFalse();
    }

    @Test
    public void statisticButtonDisplayed() {

        Assertions.assertThat(getWidgetPreview().isStatisticButtonDisplayed())
                .as("Statistic icon isn't displayed")
                .isTrue();
    }

    @Test
    public void statisticHoverTextDisplayed() {

        Assertions.assertThat(getWidgetPreview().isStatisticHoverTextDisplayed())
                .as("Statistic hover text isn't displayed")
                .isTrue();
    }

//    @Test
//    public void scorePointsDisplayed() {
//        Assertions.assertThat(getWidgetPreview().isScorePointsDisplayed())
//                .as("Score Points isn't displayed")
//                .isTrue();
//    }

    //    @Test
    public void scorePointsCountedAfterVote() {

        getWidgetPreview().voteAnswer(answerText1);
        getWidgetPreview().getVotesValueAfterPageReload(1);

        Assertions.assertThat(getWidgetPreview().getUsersScore())
                .as("Score Points isn't displayed")
                .isEqualTo("10");
    }

    @Test
    public void sharingArrowDisplayed() {

        Assertions.assertThat(getWidgetPreview().isSharingArrowDisplayed())
                .as("Sharing arrow doesn't showed")
                .isTrue();
    }

    @Test
    public void sharingFacebookDisplayed() {

        Assertions.assertThat(getWidgetPreview().isSharingFacebookLinkDisplayed())
                .as("Social network Facebook doesn't showed")
                .isTrue();
    }

    @Test
    public void sharingTwitterDisplayed() {

        Assertions.assertThat(getWidgetPreview().isSharingTwitterLinkDisplayed())
                .as("Social network Twitter doesn't showed")
                .isTrue();
    }

    @Test
    public void sharingLinkedInDisplayed() {

        Assertions.assertThat(getWidgetPreview().isSharingLinkedInLinkDisplayed())
                .as("Social network LinkedIn doesn't showed")
                .isTrue();
    }
}
