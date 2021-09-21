package base.enums;

public enum PageURLs {

    //    APP("https://app.1worldonline.com/"),
    //    PORTAL("https://frontend.1worldonline.com/#!/"),
    //    WELCOME_PAGE("https://welcome.1worldonline.com/"),


    APP("https://app-qa.1worldonline.biz/"),
    PORTAL("https://frontend-qa.1worldonline.biz/#!/"),
    WELCOME_PAGE("https://welcome-qa-ecs.1worldonline.biz/"),

    ACCOUNT_INFO(APP + "account/"),

    WELCOME_REWARDS_PAGE(WELCOME_PAGE + "rewards"),

    PORTAL_FEED_PAGE(PORTAL + "feed"),
    PORTAL_BECOME_PARTNER(PORTAL + "become-partner"),
    PORTAL_LOGIN_PARTNER(PORTAL + "login-page"),

    PORTAL_ADMIN_PARTNERS_LIST(PORTAL + "admin/partner-list/1/votesCount/desc"),
    PORTAL_ADMIN_DASHBOARD_SUMMARY(PORTAL + "admin/dashboard/summary"),

    DAN_CAMPAIGN_ANSWER_URL("https://isa.decipherinc.com/survey/selfserve/584/190602#?"),

    WIDGET_PREVIEW_PAGE_URL(PORTAL + "widget/%s"),

    REST_ASSURED_BASE_URI("https://app.1worldonline.com/"),

    MAILINATOR("https://mailinator.com");

    private String pageURL;


    PageURLs(String pageURL) {
        this.pageURL = pageURL;
    }

    @Override
    public String toString() {
        return String.valueOf(pageURL);
    }
}
