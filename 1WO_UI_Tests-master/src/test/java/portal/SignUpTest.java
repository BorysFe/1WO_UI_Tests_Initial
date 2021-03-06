package portal;

import base.Mailinator;
import base.enums.Accounts;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.testng.annotations.*;
import portalPages.FeedPage;
import portalPages.MenuProfileDropDown;
import portalPages.SignIn_SignUp_DropDown;
import utils.DriverFactory;


public class SignUpTest extends DriverFactory {

    FeedPage feedPage;
    SignIn_SignUp_DropDown signPopup;
    MenuProfileDropDown menuProfileDropDown;
    Mailinator mailinator;

    String loginNewMember;
    String password;

    @BeforeClass
    public void memberCredentials() {
        loginNewMember = Accounts.RANDOM_USER_LOGIN_MAILINATOR.toString();
        password = Accounts.RANDOM_USER_PASSWORD.toString();
    }

    @BeforeMethod
    public void openFeedPage() {
        feedPage = new FeedPage(driver);
        signPopup = new SignIn_SignUp_DropDown(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);
        mailinator = new Mailinator(driver);
        feedPage.getFeedPage();
        feedPage.openSignDropDown();
    }

    @AfterMethod
    public void logOutIfNeed() {
            menuProfileDropDown.tryLogOut();
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void signUpMember() {
        String welcomeMailSubject = "Welcome to 1World Online";
        String nameNewMember = loginNewMember.split("@")[0];
        signPopup.registrationMember(loginNewMember, password);

        Assertions.assertThat(feedPage.isMemberAuthorised())
                .as("Member isn't registered")
                .isTrue();

        Assertions.assertThat(mailinator.getMailButtonText(loginNewMember, welcomeMailSubject))
                .as("Mail with wrong button text")
                .isEqualTo("Confirm Email");

        Assertions.assertThat(mailinator.getMailWelcomeText(loginNewMember))
                .as("Wrong name in the Welcome mail")
                .isEqualTo("Hi " + nameNewMember + " !");
    }
}
