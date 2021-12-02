package widgets.pollsWidget;

import base.AccountsInfoPage;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.widgets.WidgetPreviewPage;
import utils.DriverFactory;

@Setter
@Getter
public abstract class  AbstractPollerWidgetTests extends DriverFactory {

    AccountsInfoPage accountsInfoPage;

    String answerText1;
    String answerText2;

    protected abstract WidgetPreviewPage getWidgetPreview();

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

    @Test
    public void scorePointsDisplayed() {
        Assertions.assertThat(getWidgetPreview().isScorePointsDisplayed())
                .as("Score Points isn't displayed")
                .isTrue();
    }

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
