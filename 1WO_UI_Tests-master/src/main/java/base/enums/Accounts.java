package base.enums;

public enum Accounts {
    SUPERADMIN_LOGIN("borys@1worldboris.com"),
    SUPERADMIN_PASSWORD("Qwerty123!"),
    ADMIN_LOGIN("borys@1worldboris.com"),
    ADMIN_PASSWORD("Qwerty123!"),
    ADVERTISER_LOGIN("borys@1worldboris.com"),
    ADVERTISER_PASSWORD("Qwerty123!"),
    PUBLISHER_LOGIN("borys@1worldboris.com"),
    PUBLISHER_PASSWORD("Qwerty123!"),
    USER_LOGIN("borys@1worldboris.com"),
    USER_PASSWORD("Qwerty123!"),
    NOT_REGISTERED_USER_LOGIN(""),
    NOT_REGISTERED_USER_PASSWORD(""),
    RANDOM_USER_LOGIN_MAILINATOR("borys" + System.currentTimeMillis() + "@mailinator.com"),
    RANDOM_USER_PASSWORD("Qwerty123!"),
    RANDOM_USER_LOGIN(System.currentTimeMillis() + "@mailinator.com");

    private final String account;

    Accounts(final String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return account;
    }
}
