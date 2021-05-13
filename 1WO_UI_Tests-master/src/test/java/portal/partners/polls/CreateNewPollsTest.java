package portal.partners.polls;

import base.enums.Accounts;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.FeedPage;
import portalPages.MenuProfileDropDown;
import portalPages.publisher.OnboardingPublisherPage;
import portalPages.publisher.PublisherLoginPage;
import portalPages.publisher.polls.NewPollManagerPage;
import portalPages.publisher.polls.PollCategory;
import portalPages.publisher.polls.PollRegionsAndLanguage;
import portalPages.publisher.polls.PollsPage;
import utils.DriverFactory;

public class CreateNewPollsTest extends DriverFactory {

    FeedPage feedPage;
    PublisherLoginPage publisherLoginPage;
    MenuProfileDropDown menuProfileDropDown;
    PollsPage pollsPage;
    OnboardingPublisherPage onboardingPublisherPage;
    NewPollManagerPage newPollManager;

    String loginPublisher;
    String passwordPublisher;

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
    }

    @BeforeMethod
    public void openFeedPage() {
        feedPage = new FeedPage(driver);
        publisherLoginPage = new PublisherLoginPage(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);
        pollsPage = new PollsPage(driver);
        onboardingPublisherPage = new OnboardingPublisherPage(driver);
        newPollManager = new NewPollManagerPage(driver);


        publisherLoginPage.getPublisherLoginPage();
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
    public void addNewPoll() {
        String questionText = String.format("Lorem ipsum" + System.currentTimeMillis());
        String answerText = "Answer%s";

        publisherLoginPage.loginPublisher(loginPublisher, passwordPublisher);
        onboardingPublisherPage.openPollsPage();
        pollsPage.startNewPollCreating()
                .selectDropdownAndLanguage(PollRegionsAndLanguage.SELECT_REGION.toString(), PollRegionsAndLanguage.REGION_ALL.toString())
                .selectDropdownAndLanguage(PollRegionsAndLanguage.SELECT_LANGUAGE.toString(), PollRegionsAndLanguage.LANGUAGE_ENGLISH.toString())
                .modalSubmit();

        newPollManager.selectPollCategory(PollCategory.CATEGORY_ART_CULTURE.toString())
                .fillQuestion(questionText)
                .fillAnswer("0", String.format(answerText, "1"))
                .fillAnswer("1", String.format(answerText, "2"))
                .saveNewPollPage();
        newPollManager.saveNewPollPage();

        Assertions.assertThat(newPollManager.isNewPollAlertDisplayed())
                .as("New Poll Alert not showed")
                .isTrue();

        newPollManager.newPollAlertAccept();
    }
}
