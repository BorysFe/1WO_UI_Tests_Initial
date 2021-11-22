package portal.partners.surveys;

import base.enums.Accounts;
import base.enums.DefaultContent;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;
import portalPages.MenuProfileDropDown;
import portalPages.publisher.PublisherLoginPage;
import portalPages.surveys.*;
import utils.DriverFactory;

public class NewFullPageSurveyTest extends DriverFactory {
    PublisherLoginPage publisherLoginPage;
    MenuProfileDropDown menuProfileDropDown;

    SurveysPage surveysPage;
    SurveyConfigurationPage configurationPage;
    SurveyBrandingPage brandingPage;
    SurveyBuilderPage builderPage;
    SurveyMessagesPage messagesPage;
    SurveyStatisticPage statisticPage;

    String loginPublisher;
    String passwordPublisher;

    @BeforeClass
    public void memberCredentials() {
        loginPublisher = Accounts.PUBLISHER_LOGIN.toString();
        passwordPublisher = Accounts.PUBLISHER_PASSWORD.toString();
        System.out.println(loginPublisher + " / " + passwordPublisher);
    }

    @BeforeMethod
    public void loginPublisher() {
        publisherLoginPage = new PublisherLoginPage(driver);
        menuProfileDropDown = new MenuProfileDropDown(driver);

        surveysPage = new SurveysPage(driver);
        configurationPage = new SurveyConfigurationPage(driver);
        brandingPage = new SurveyBrandingPage(driver);
        builderPage = new SurveyBuilderPage(driver);
        messagesPage = new SurveyMessagesPage(driver);
        statisticPage = new SurveyStatisticPage(driver);

        publisherLoginPage.getPublisherLoginPage()
                .loginPublisher(loginPublisher, passwordPublisher)
                .openSurveysPage();
    }

    @AfterMethod
    public void logOutIfNeed() {
        menuProfileDropDown.tryLogOut();
    }

    @Test
    public void checkDisplayLanguageModal() {
        surveysPage.startNewFullPageSurveyCreating();

        Assertions.assertThat(surveysPage.isLanguageModalDisplayed())
                .as("New Survey's language Modal window isn't displayed")
                .isTrue();
    }

    @Test
    public void checkErrorByRequiredCategory() {
        surveysPage.startNewFullPageSurveyCreating()
                .modalSubmit();
        configurationPage.clickNext();

        Assertions.assertThat(configurationPage.isDisplayedErrorByRequiredCategory())
                .as("Message about required select Survey's category isn't displayed")
                .isTrue();
    }

    @Test
    public void checkErrorByCorrectFillingNumberOfParticipants() {
        surveysPage.startNewFullPageSurveyCreating()
                .modalSubmit();
        configurationPage.selectSurveyCategory(SurveyCategory.CATEGORY_ART_CULTURE.toString())
                .enterSurveyParticipantsNeeded("Test")
                .clickNext();

        Assertions.assertThat(configurationPage.isDisplayedErrorByCorrectNumbersOfParticipants())
                .as("Message about correct filling required participants isn't displayed")
                .isTrue();
    }

    @Test
    public void createEmptySurvey() {
        String surveyName = String.format("Empty Survey - " + System.currentTimeMillis());

        surveysPage.startNewFullPageSurveyCreating()
                .modalSubmit();
        configurationPage.enterSurveyName(surveyName)
                .enterSurveyTitle(surveyName)
                .selectSurveyCategory(SurveyCategory.CATEGORY_ART_CULTURE.toString())
                .clickNext();
        brandingPage.clickNext();
        builderPage.clickNext();
        messagesPage.clickFinish();

        Assertions.assertThat(statisticPage.isSurveyTitleDisplayed(surveyName))
                .as("New Survey doesn't displayed")
                .isTrue();

        Assertions.assertThat(statisticPage.getCompletedResponses())
                .as("New Survey doesn't displayed")
                .isEqualTo("0");

        statisticPage.clickSurveysListButton();

        Assertions.assertThat(surveysPage.isSurveyTitleDisplayed(surveyName))
                .as("New Survey doesn't displayed")
                .isTrue();
    }

//    @Test
    public void createDefaultContentSurvey() {
        String surveyName = String.format("Empty Survey - " + System.currentTimeMillis());

        surveysPage.startNewFullPageSurveyCreating()
                .modalSubmit();
        configurationPage.enterSurveyName(surveyName)
                .enterSurveyTitle(surveyName)
                .selectSurveyCategory(SurveyCategory.CATEGORY_ART_CULTURE.toString())
                .clickNext();
        brandingPage.clickNext();
        builderPage.addDescriptionTextBlockAndEnterText(String.format(DefaultContent.DEFAULT_TEXT.toString()))
                .clickNext();
        messagesPage.clickFinish();

        Assertions.assertThat(statisticPage.isSurveyTitleDisplayed(surveyName))
                .as("New Survey doesn't displayed")
                .isTrue();

        Assertions.assertThat(statisticPage.getCompletedResponses())
                .as("New Survey doesn't displayed")
                .isEqualTo("0");

        statisticPage.clickSurveysListButton();

        Assertions.assertThat(surveysPage.isSurveyTitleDisplayed(surveyName))
                .as("New Survey doesn't displayed")
                .isTrue();
    }
}
