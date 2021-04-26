package portal;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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
        logInUnexpectedMember = "unexpectedMemberEmail@mailinator.com";
        password = "Qwerty123";
        System.out.println(loginNewMember+ " / " +logInUnexpectedMember + " / " +password);
    }

    @BeforeMethod
    public void openFeedPage() {
        feedPage = new FeedPage(driver);
        driver.get("https://frontend-qa.1worldonline.biz/#!/feed");
    }

    @Test
    public void loginUnexpectedMember() {
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
