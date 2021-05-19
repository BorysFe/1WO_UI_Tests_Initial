package portal.partners.widgets;

import base.enums.Accounts;
import org.testng.annotations.*;
import portalPages.FeedPage;
import portalPages.MenuProfileDropDown;
import portalPages.polls.polls.PollsPage;
import portalPages.publisher.OnboardingPublisherPage;
import portalPages.publisher.PublisherLoginPage;
import utils.DriverFactory;

public class NewWidgetTest extends DriverFactory {

    FeedPage feedPage;
    MenuProfileDropDown menuProfileDropDown;
    PublisherLoginPage publisherLoginPage;
    OnboardingPublisherPage onboardingPublisherPage;
    PollsPage pollsPage;

    String loginPublisher;
    String passwordPublisher;

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
        System.out.println(loginPublisher + " / " + passwordPublisher);
    }

    @BeforeMethod
    public void pagesDriver() {
        feedPage = new FeedPage(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);
        publisherLoginPage = new PublisherLoginPage(driver);
        onboardingPublisherPage = new OnboardingPublisherPage(driver);
        pollsPage = new PollsPage(driver);

        publisherLoginPage.getPublisherLoginPage();
    }

    @AfterMethod
    public void logOutIfNeed() {
        if (feedPage.isMemberAuthorised()) {
            System.out.println("Member is authorised, need to LogOut");
            menuProfileDropDown.logOut();
        }
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void addNewWidget() {
        String pollQuestionText1 = String.format("Lorem ipsum1- " + System.currentTimeMillis());
        String pollQuestionText2 = String.format("Lorem ipsum2- " + System.currentTimeMillis());
        String pollAnswerText = "Answer%s";
        String widgetName = String.format("Widget" + System.currentTimeMillis());

        publisherLoginPage.loginPublisher(loginPublisher, passwordPublisher);
        onboardingPublisherPage.openPollsPage();
        pollsPage.addNewPoll(pollQuestionText1,
                String.format(pollAnswerText, "1"),
                String.format(pollAnswerText, "2"));
        pollsPage.addNewPoll(pollQuestionText2,
                String.format(pollAnswerText, "1"),
                String.format(pollAnswerText, "2"));
        pollsPage.startNewWidgetCreating()
                .newWidgetDefaultLanguage(widgetName)
                .nextButtonClick()
                .addPollToWidget(pollQuestionText1)
                .addPollToWidget(pollQuestionText2)
                .nextButtonClick()
                .nextButtonClick()
                .finishButtonClick();

    }
}
