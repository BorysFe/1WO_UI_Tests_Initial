package portal.partners.surveys;

import base.enums.Accounts;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.MenuProfileDropDown;
import portalPages.publisher.OnboardingPublisherPage;
import portalPages.publisher.PublisherLoginPage;
import utils.DriverFactory;

public class newFullPageSurvey extends DriverFactory {
    PublisherLoginPage publisherLoginPage;
    MenuProfileDropDown menuProfileDropDown;
    OnboardingPublisherPage onboardingPublisherPage;

    String loginPublisher;
    String passwordPublisher;

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
    }

    @BeforeMethod
    public void openFeedPage() {
        publisherLoginPage = new PublisherLoginPage(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);
        onboardingPublisherPage = new OnboardingPublisherPage(driver);

        publisherLoginPage.getPublisherLoginPage();
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
    public void addNewPoll() {
        String questionText = String.format("Lorem ipsum" + System.currentTimeMillis());
        String answerText = "Answer%s";

        publisherLoginPage.loginPublisher(loginPublisher, passwordPublisher);
        onboardingPublisherPage.openPollsPage();


//        Assertions.assertThat()
//                .as("New Poll doesn't displayed")
//                .isTrue();
    }
}
