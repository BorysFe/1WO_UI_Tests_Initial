package base.enums;

public enum PageURLs {
    QA_WELCOME_PAGE("https://welcome-qa-ecs.1worldonline.biz/"),
    QA_PORTAL_FEED_PAGE("https://frontend-qa.1worldonline.biz/#!/feed"),
    QA_PORTAL_BECOME_PARTNER("https://frontend-qa.1worldonline.biz/#!/become-partner"),

    QA_PORTAL_ADMIN_PARTNERS_LIST("https://frontend-qa.1worldonline.biz/#!/admin/partner-list/1/votesCount/desc"),
    QA_PORTAL_ADMIN_DASHBOARD_SUMMARY("https://frontend-qa.1worldonline.biz/#!/admin/dashboard/summary"),


    DAN_CAMPAIGN_ANSWER_URL("https://isa.decipherinc.com/survey/selfserve/584/190602#?"),


    MAILINATOR("https://mailinator.com");

    private final String pageURL;

    PageURLs(final String pageURL) {
        this.pageURL = pageURL;
    }

    @Override
    public String toString() {
        return pageURL;
    }
}
