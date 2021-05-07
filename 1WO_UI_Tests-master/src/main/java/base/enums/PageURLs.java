package base.enums;

public enum PageURLs {
    PORTAL_FEED_PAGE("https://frontend-qa.1worldonline.biz/#!/feed"),
    SIGN_IN_LOGIN_PFIELD("signin-email");

    private final String pageURL;

    PageURLs(final String pageURL) {
        this.pageURL = pageURL;
    }

    @Override
    public String toString() {
        return pageURL;
    }
}
