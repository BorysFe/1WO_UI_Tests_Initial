package portal;

import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.FeedPage;
import utils.DriverFactory;

public class SignUpTest extends DriverFactory {

    FeedPage feedPage;
    String loginNewMember;
    String logInUnexpectedMember;
    String password;

    @BeforeClass
    public void memberCredentials() {
        loginNewMember = "borys" + System.currentTimeMillis() + "@mailinator.com";
        logInUnexpectedMember = System.currentTimeMillis() + "@mailinator.com";
        password = "Qwerty123";
        System.out.println(loginNewMember + " / " + logInUnexpectedMember + " / " + password);
    }

    @BeforeMethod
    public void openFeedPage() {
        feedPage = new FeedPage(driver);
    }

    @AfterMethod
    public void logOutIfNeed() {
        if (feedPage.isMemberAuthorised()) {
            feedPage.logOut();
        }
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void loginUnexpectedMember() {
        feedPage.logInMember(logInUnexpectedMember, password);

        Assertions.assertThat(feedPage.isAuthenticationErrorDisplayed())
                .as("Message isn't showed")
                .isTrue();
    }

    @Test
    public void loginMember() {
        feedPage.logInMember(logInUnexpectedMember, password);

        Assertions.assertThat(feedPage.isAuthenticationErrorDisplayed())
                .as("Message isn't showed")
                .isTrue();
    }

    @Test
    public void signUpMember() {
        feedPage.registrationMember(loginNewMember, password);

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Member isn't registered")
                .isTrue();
    }
}
