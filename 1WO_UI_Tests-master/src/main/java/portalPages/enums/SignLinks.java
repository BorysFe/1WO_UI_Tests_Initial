package portalPages.enums;

public enum SignLinks {
    SIGN_IN_SIGN_UP_BUTTON("login"),
    SIGN_IN_LOGIN_FIELD("signin-email"),
    SIGN_IN_PASSWORD_FIELD("signin-pswd"),
    SIGN_IN_SIGN_IN_BUTTON("sign-in-btn"),
    SIGN_IN_RESTORE_PASSWORD_BUTTON("restore-pass-btn"),
    SIGN_IN_CREATE_ACCOUNT_BUTTON("create-account"),
    SIGN_IN_FORGOT_PASSWORD_FIELD("fp-email"),
    SIGN_IN_SUBMIT_BUTTON("submit"),

    SIGN_UP_EMAIL_FIELD("create-account-email"),
    SIGN_UP_PASSWORD_FIELD("create-password"),
    SIGN_UP_RESTORE_PASSWORD_FIELD("retypePassword"),
    SIGN_UP_SUBMIT_BUTTON("create-account-btn"),
    SIGN_UP_RECEIVE_EMAIL_CHECKBOX("restore-pass-btn"),
    SIGN_UP_TERMS_CHECKBOX("create-account"),
    SIGN_UP_SIGN_IN_BUTTON("sign-in");

    private final String feedPageSignBox;

    SignLinks(final String feedPageSignBox) {
        this.feedPageSignBox = feedPageSignBox;
    }

    @Override
    public String toString() {
        return feedPageSignBox;
    }
}
