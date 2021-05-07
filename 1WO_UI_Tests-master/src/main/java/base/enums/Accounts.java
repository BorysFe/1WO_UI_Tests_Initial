package base.enums;

public enum Accounts {
    SUPERADMIN_LOGIN("borys1620396905044@mailinator.com"),
    SUPERADMIN_PASSWORD("Qwerty123!"),

    ADMIN_LOGIN("borys1620397016978@mailinator.com"),
    ADMIN_PASSWORD("Qwerty123!"),

    ADMIN_GOOGLE_LOGIN("workboris4@gmail.com"),
    ADMIN_GOOGLE_PASSWORD("workboris4"),

    ADVERTISER_LOGIN("borys@1worldboris.com"),
    ADVERTISER_PASSWORD("Qwerty123!"),

    PUBLISHER_LOGIN("borys1620396685711@mailinator.com"),
    PUBLISHER_PASSWORD("Qwerty123!"),

    USER_EXISTED_LOGIN("borys1620398454294@mailinator.com"),
    USER_EXISTED_PASSWORD("Qwerty123!"),

    USER_NOT_EXISTED_LOGIN(""),
    USER_NOT_EXISTED_PASSWORD(""),

    RANDOM_USER_LOGIN_MAILINATOR("borys" + System.currentTimeMillis() + "@mailinator.com"),
    RANDOM_USER_LOGIN(System.currentTimeMillis() + "@mailinator.com"),
    RANDOM_USER_PASSWORD("Qwerty123!");

    private final String account;

    Accounts(final String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return account;
    }
}
