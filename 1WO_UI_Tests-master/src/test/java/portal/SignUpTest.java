package portal;

import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.FeedPage;
import portalPages.SignIn_SignUp_Popup;
import utils.DriverFactory;

public class SignUpTest extends DriverFactory {

    FeedPage feedPage;
    SignIn_SignUp_Popup signPopup;

    String loginNewMember;
    String logInWrongMember;
    String password;

    @BeforeClass
    public void memberCredentials() {
        loginNewMember = "borys" + System.currentTimeMillis() + "@mailinator.com";
        logInWrongMember = System.currentTimeMillis() + "@mailinator.com";
        password = "Qwerty123";
        System.out.println(loginNewMember + " / " + logInWrongMember + " / " + password);
    }

    @BeforeMethod
    public void openFeedPage() {
        feedPage = new FeedPage(driver);
        signPopup = new SignIn_SignUp_Popup(driver);
        feedPage.getFeedPage();
        feedPage.openSignPopup();
    }

    @AfterMethod
    public void logOutIfNeed() {
        if (feedPage.isMemberAuthorised()) {
            signPopup.logOut();
        }
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void loginNotRegisteredMember() {
        signPopup.logInMember(logInWrongMember, password);

        Assertions.assertThat(signPopup.isAuthenticationErrorDisplayed())
                .as("Message isn't showed")
                .isTrue();
    }

    @Test
    public void loginMember() {
        signPopup.logInMember(logInWrongMember, password);

        Assertions.assertThat(signPopup.isAuthenticationErrorDisplayed())
                .as("Message isn't showed")
                .isTrue();
    }

    @Test
    public void signUpMember() {
        signPopup.registrationMember(loginNewMember, password);

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Member isn't registered")
                .isTrue();
    }
}
