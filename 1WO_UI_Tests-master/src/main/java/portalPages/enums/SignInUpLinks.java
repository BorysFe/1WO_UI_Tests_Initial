package portalPages.enums;

public enum SignInUpLinks {
    SIGN_IN_LOGIN_FIELD("signin-email"),
    SIGN_IN_PASSWORD_FIELD("signin_pswd"),
    SIGN_IN_SIGN_IN_BUTTON("sign-in-btn"),
    SIGN_IN_RESTORE_PASSWORD_BUTTON("restore-pass-btn"),
    SIGN_IN_CREATE_ACCOUNT_BUTTON("create-account"),
    SIGN_IN_FORGOT_PASSWORD_FIELD("fp-email"),
    SIGN_IN_SUBMIT_BUTTON("submit"),
    REGISTRATION_EMAIL_FIELD("create-account-email"),
    REGISTRATION_PASSWORD_FIELD("create-password"),
    REGISTRATION_RESTORE_PASSWORD_FIELD("retypePassword"),
    REGISTRATION_SUBMIT_BUTTON("create-account-btn"),
    REGISTRATION_RECEIVE_EMAIL_CHECKBOX("restore-pass-btn"),
    REGISTRATION_TERMS_CHECKBOX("create-account"),
    REGISTRATION_SIGN_IN_BUTTON("sign-in");

    private final String feedPageLogin;

    SignInUpLinks(final String feedPageLogin) {
        this.feedPageLogin = feedPageLogin;
    }

    @Override
    public String toString() {
        return feedPageLogin;
    }
}
