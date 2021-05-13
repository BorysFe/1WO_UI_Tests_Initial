package portal;

import base.Mailinator;
import base.enums.Accounts;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.FeedPage;
import portalPages.MenuProfileDropDown;
import portalPages.ResetPassword;
import portalPages.SignIn_SignUp_DropDown;
import utils.DriverFactory;

public class SignInTest extends DriverFactory {

    FeedPage feedPage;
    SignIn_SignUp_DropDown signPopup;
    MenuProfileDropDown menuProfileDropDown;
    Mailinator mailinator;
    ResetPassword resetPassword;

    String loginNewMember;
    String loginWrongMember;
    String loginPublisher;
    String password;

    @BeforeClass
    public void memberCredentials() {
        loginNewMember = Accounts.RANDOM_USER_LOGIN_MAILINATOR.toString();
        loginWrongMember = Accounts.RANDOM_USER_LOGIN.toString();
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        password = Accounts.RANDOM_USER_PASSWORD.toString();
        System.out.println(loginNewMember + " / " + loginWrongMember + " / " + password);
    }

    @BeforeMethod
    public void openFeedPage() {
        feedPage = new FeedPage(driver);
        signPopup = new SignIn_SignUp_DropDown(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);
        mailinator = new Mailinator(driver);
        resetPassword = new ResetPassword(driver);

        feedPage.getFeedPage();
        feedPage.openSignDropDown();
    }

    @AfterMethod
    public void logOutIfNeed() {
        if (feedPage.isMemberAuthorised()) {
            menuProfileDropDown.logOut();
        }
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void loginNotRegisteredMemberWithError() {
        signPopup.logInMember(loginWrongMember, password);

        Assertions.assertThat(signPopup.isAuthenticationErrorDisplayed())
                .as("Message isn't showed")
                .isTrue();
    }

    @Test
    public void loginMemberWithError() {
        signPopup.logInMember(loginWrongMember, password);

        Assertions.assertThat(signPopup.isAuthenticationErrorDisplayed())
                .as("Message isn't showed")
                .isTrue();
    }

    @Test
    public void loginPublisher() {
        signPopup.logInMember(loginPublisher, password);

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Publisher not authorised")
                .isTrue();
    }

    @Test
    public void recoveryPasswordMailSentSuccess() {
        String accountMail = Accounts.USER_EXISTED_LOGIN.toString();

        resetPassword.resetPassword(accountMail);

        Assertions.assertThat(resetPassword.isResetPasswordMailSent())
                .as("Mail for recovery password wasn't sent")
                .isTrue();
    }

    @Test
    public void recoveryPasswordSuccess() {
        String accountMail = Accounts.USER_EXISTED_LOGIN.toString();

        resetPassword.resetPassword(accountMail);

        Assertions.assertThat(resetPassword.isResetPasswordMailSent())
                .as("Mail for recovery password wasn't sent")
                .isTrue();

        String newPassword = mailinator.getPasswordFromEmail(accountMail);

        feedPage.getFeedPage();
        feedPage.openSignDropDown();
        signPopup.logInMemberLongWait(accountMail, newPassword);

        resetPassword.enterNewPassword(Accounts.USER_EXISTED_PASSWORD.toString());

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Member not authorised")
                .isTrue();
    }
}
