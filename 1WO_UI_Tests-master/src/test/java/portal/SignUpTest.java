package portal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import portalPages.FeedPage;
import utils.DriverFactory;

public class SignUpTest extends DriverFactory {

    FeedPage feedPage;
    String login;
    String password;

    @BeforeClass
    public void memberCreds() {
        login = "borys1wo@mailinator.com";
        password = "Qwerty123";
    }

    //    @BeforeMethod
//    public void memberCreds() {
//        feedPage = new FeedPage(getDriver("chrome"));
//        login = "borys1wo@mailinator.com";
//        password = "Qwerty123";
//        getDriver("chrome").get("https://frontend-qa.1worldonline.biz/#!/feed");
//    }
    @BeforeMethod
    public void openFeedPage() {
        driver.get("https://frontend-qa.1worldonline.biz/#!/feed");
        feedPage = new FeedPage(driver);
    }

    @Test
    public void signUpMember() {
        feedPage.logInMember(login, password);
    }
}
