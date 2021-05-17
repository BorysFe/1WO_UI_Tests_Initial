package portal;

import base.Mailinator;
import base.enums.Accounts;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.*;
import portalPages.FeedPage;
import portalPages.MenuProfileDropDown;
import portalPages.ResetPassword;
import portalPages.SignIn_SignUp_DropDown;
import portalPages.publisher.PublisherLoginPage;
import utils.DriverFactory;
import welcomePages.WelcomePage;

public class SignInTest extends DriverFactory {

    FeedPage feedPage;
    SignIn_SignUp_DropDown signPopup;
    MenuProfileDropDown menuProfileDropDown;
    Mailinator mailinator;
    ResetPassword resetPassword;
    WelcomePage welcomePage;
    PublisherLoginPage publisherLoginPage;

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
    public void pagesDriver() {
        feedPage = new FeedPage(driver);
        signPopup = new SignIn_SignUp_DropDown(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);
        mailinator = new Mailinator(driver);
        resetPassword = new ResetPassword(driver);
        welcomePage = new WelcomePage(driver);
        publisherLoginPage = new PublisherLoginPage(driver);
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
        feedPage.getFeedPage();
        feedPage.openSignDropDown();
        signPopup.logInMember(loginWrongMember, password);

        Assertions.assertThat(signPopup.isAuthenticationErrorDisplayed())
                .as("Message isn't showed")
                .isTrue();
    }

    @Test
    public void loginMemberWithError() {
        feedPage.getFeedPage();
        feedPage.openSignDropDown();
        signPopup.logInMember(loginWrongMember, password);

        Assertions.assertThat(signPopup.isAuthenticationErrorDisplayed())
                .as("Message isn't showed")
                .isTrue();
    }

    @Test
    public void loginPublisher() {
        feedPage.getFeedPage();
        feedPage.openSignDropDown();
        signPopup.logInMember(loginPublisher, password);

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Publisher not authorised")
                .isTrue();
    }

    @Test
    public void emptyLoginErrorVerification() {
        publisherLoginPage.getPublisherLoginPage().loginPublisher(null, null);

        Assertions.assertThat(publisherLoginPage.isPublisherLoginErrorDisplayed())
                .as("Error message below Login field not showed")
                .isTrue();

        Assertions.assertThat(publisherLoginPage.getPublisherLoginErrorText())
                .as("Error message below Login field not showed")
                .isEqualTo("This field is required.");

        Assertions.assertThat(publisherLoginPage.isPublisherPasswordErrorDisplayed())
                .as("Error message below Password field not showed")
                .isTrue();

        Assertions.assertThat(publisherLoginPage.getPublisherLoginErrorText())
                .as("Error message below Login field not showed")
                .isEqualTo("This field is required.");
    }

    @Test
    public void loginTextErrorVerification() {
        String incorrectPublisherLogin = Accounts.PUBLISHER_LOGIN.toString();

        publisherLoginPage.getPublisherLoginPage().loginPublisher(incorrectPublisherLogin.split("@")[0], null);

        Assertions.assertThat(publisherLoginPage.isPublisherLoginErrorDisplayed())
                .as("Error message below Login field not showed")
                .isTrue();

        Assertions.assertThat(publisherLoginPage.getPublisherLoginErrorText())
                .as("Error message below Login field is incorrect")
                .isEqualTo("Please enter a valid email address.");
    }

    @Test
    public void recoveryPasswordMailSentSuccess() {
        feedPage.getFeedPage();
        feedPage.openSignDropDown();
        resetPassword.resetPassword(loginPublisher);

        Assertions.assertThat(resetPassword.isResetPasswordMailSent())
                .as("Mail for recovery password wasn't sent")
                .isTrue();
    }

    @Test
    public void recoveryPasswordSuccess() {
        feedPage.getFeedPage();
        feedPage.openSignDropDown();
        resetPassword.resetPassword(loginPublisher);

        Assertions.assertThat(resetPassword.isResetPasswordMailSent())
                .as("Mail for recovery password wasn't sent")
                .isTrue();

        String newPassword = mailinator.getPasswordFromEmail(loginPublisher);

        feedPage.getFeedPage();
        feedPage.openSignDropDown();
        signPopup.logInMemberLongWait(loginPublisher, newPassword);

        resetPassword.enterNewPassword(Accounts.USER_EXISTED_PASSWORD.toString());

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Member not authorised")
                .isTrue();
    }

    @Test
    public void publisherSignInFromWelcomePage() {
        welcomePage.openWelcomePage().openPublisherLoginPage();

        publisherLoginPage.loginPublisher(loginPublisher, password);

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Member not authorised")
                .isTrue();
    }
}
