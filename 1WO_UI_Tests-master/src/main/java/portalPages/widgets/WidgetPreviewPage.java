package portalPages.widgets;

public interface WidgetPreviewPage {

    boolean isPollsPercentsDisplayed(String answerText1);

    boolean isStatisticButtonDisplayed();

    boolean isStatisticHoverTextDisplayed();

    void voteAnswer(String answerText1);

    void getVotesValueAfterPageReload(int i);

    String getUsersScore();

    boolean isSharingArrowDisplayed();

    boolean isSharingFacebookLinkDisplayed();

    boolean isSharingTwitterLinkDisplayed();

    boolean isSharingLinkedInLinkDisplayed();

    boolean isScorePointsDisplayed();

    void openPollerWidgetPreview(String newWidgetId);
}
