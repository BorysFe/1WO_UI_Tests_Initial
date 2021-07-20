package welcomePages;

public enum WelcomeLinks {

    PUBLISHER("Publisher"),
    ADVERTISER("Advertiser"),
    MEMBER("Member");


    private final String welcomeLinks;

    WelcomeLinks(final String welcomeLinks) {
        this.welcomeLinks = welcomeLinks;
    }

    @Override
    public String toString() {
        return welcomeLinks;
    }
}
