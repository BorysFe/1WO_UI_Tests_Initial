package portalPages.polls.polls;

import base.BaseComponent;
import base.enums.GeneralLocators;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import portalPages.polls.widgets.widgetPages.LanguageAndNamePage;
import utils.UtilityHelper;
import utils.WaitUtils;

import java.util.Objects;

@Getter
public class NewPollManagerPage extends BaseComponent {

    WaitUtils waitUtils;
    LanguageAndNamePage newWidgetsPage;

    private final String answerField = ".//input[@id='pollAnswerSide%s']";
    private final String additionalInformationField = ".//input[@name='poll.sides[%s].additionalInfo']";

    @FindBy(xpath = ".//select[@id='pollCategoryId']")
    private WebElement pollCategoryDropdown;

    @FindBy(xpath = ".//a[contains(text(), 'New widget')]")
    private WebElement newWidgetButton;

    @FindBy(xpath = ".//input[@value= 'Save']")
    private WebElement savePageButton;

    public NewPollManagerPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(GeneralLocators.INPUT_BY_ID.toString(), "pollTagline")));
    }

    @BeforeClass
    private void waitLoading() {
        waitUtils.waitForLoading();
    }

    public NewPollManagerPage selectPollCategoryOpen(String selectItem) {
        waitUtils.waitForLoading();
        if (Objects.equals(getDropdownItem(selectItem).getAttribute("selected"), null)) {
            Select select = new Select(pollCategoryDropdown);
            select.selectByVisibleText(selectItem);
        }

        return this;
    }

    public LanguageAndNamePage newWidgetPageOpen(String selectItem) {
        newWidgetsPage = new LanguageAndNamePage(driver);

        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(newWidgetButton);
        return newWidgetsPage;
    }

    public boolean isOptionSelectedInDropdown(String dropdownItem) {

        return Objects.equals(getDropdownItem(dropdownItem).getAttribute("selected"), "true");
    }

    private WebElement getDropdownItem(String dropdownItem) {
        waitUtils.waitForLoading();
        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath(String.format(GeneralLocators.OPTION_BY_TEXT.toString(), dropdownItem)));
    }

    public NewPollManagerPage fillAnswer(String answerNumber, String answerText) {
        setField(By.xpath(String.format(answerField, answerNumber)), answerText);

        return this;
    }

    public NewPollManagerPage fillQuestion(String questionText) {
        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_ID.toString(), "pollTagline")), questionText);

        return this;
    }

    public NewPollManagerPage fillAdditionalInformation(String answerNumber, String additionalInformationText) {
        setField(By.xpath(String.format(additionalInformationField, answerNumber)), additionalInformationText);

        return this;
    }

    public void saveNewPollPageWithAlertAccept() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(savePageButton);
        waitUtils.waitMilliseconds(1000, "Wait for alert displayed");
        driver.switchTo().alert().accept();
    }

    public void saveNewPollPage() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(savePageButton);
    }

    public boolean isNewPollAlertDisplayed() {
        waitUtils.waitForAlertToShowUp();
        return UtilityHelper.isAlertPresent(driver);
    }

    public void newPollAlertAccept() {
        waitUtils.waitMilliseconds(100, "Wait for alert displayed");
        driver.switchTo().alert().accept();
    }

    void setField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    void setField(By element, String text) {
        waitUtils.waitVisibilityOfElementByShort(element);
        driver.findElement(element).clear();
        waitUtils.waitVisibilityOfElementByShort(element);
        driver.findElement(element).sendKeys(text);
    }
}
