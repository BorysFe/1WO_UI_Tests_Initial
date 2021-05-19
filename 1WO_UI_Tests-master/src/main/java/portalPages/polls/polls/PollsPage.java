package portalPages.polls.polls;

import base.BaseComponent;
import base.enums.GeneralLocators;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.polls.RegionsAndLanguages;
import portalPages.polls.widgets.widgetPages.LanguageAndNamePage;
import utils.WaitUtils;

@Getter
public class PollsPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(PollsPage.class);

    WaitUtils waitUtils;
    private NewPollManagerPage newPollManager;
    private SelectPollLanguageModalPage selectPollLanguageModalPage;
    private LanguageAndNamePage languageAndNamePage;

    public PollsPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        newPollManager = new NewPollManagerPage(driver);
        selectPollLanguageModalPage = new SelectPollLanguageModalPage(driver);
        languageAndNamePage = new LanguageAndNamePage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.DIV_BY_ID.toString(), "create-poll")));
    }

    public SelectPollLanguageModalPage startNewPollCreating() {
        waitUtils.waitForLoading();

        selectPollLanguageModalPage = new SelectPollLanguageModalPage(driver);

        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(GeneralLocators.DIV_BY_ID.toString(), "create-poll")));
        return selectPollLanguageModalPage;
    }

    public boolean isPollTitleDisplayed(String pollTitle) {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.SPAN_BY_TEXT.toString(), pollTitle)));
    }

    public PollsPage addNewPoll(String questionText, String answer1, String answer2) {

        startNewPollCreating()
                .selectDropdownAndItem(RegionsAndLanguages.SELECT_POLL_REGION.toString(), RegionsAndLanguages.REGION_ALL.toString())
                .selectDropdownAndItem(RegionsAndLanguages.SELECT_POLL_LANGUAGE.toString(), RegionsAndLanguages.LANGUAGE_ENGLISH.toString())
                .modalSubmit();

        newPollManager.selectPollCategoryOpen(PollCategory.CATEGORY_ART_CULTURE.toString())
                .fillQuestion(questionText)
                .fillAnswer("0", answer1)
                .fillAnswer("1", answer2)
                .saveNewPollPage();
        newPollManager.saveNewPollPageWithAlertAccept();

        return this;
    }

    public LanguageAndNamePage startNewWidgetCreating() {

        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(GeneralLocators.A_BY_TEXT.toString(), "New widget")));

        return languageAndNamePage;
    }

    public PollsPage addNewDefaultWidget(String widgetTitle) {
        waitUtils.waitForLoading();

        return this;
    }
}
