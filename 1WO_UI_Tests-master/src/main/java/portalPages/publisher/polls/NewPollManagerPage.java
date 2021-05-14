package portalPages.publisher.polls;

import base.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import utils.UtilityHelper;
import utils.WaitUtils;

import java.util.Objects;

@Getter
public class NewPollManagerPage extends BaseComponent {

    WaitUtils waitUtils;

    private final String categorySelectItem = ".//option[contains(text(), '%s')]";
    private final String answerField = ".//input[@id='pollAnswerSide%s']";
    private final String additionalInformationField = ".//input[@name='poll.sides[%s].additionalInfo']";

    @FindBy(xpath = ".//select[@id='pollCategoryId']")
    private WebElement pollCategoryDropdown;

    @FindBy(xpath = ".//input[@value= 'Save']")
    private WebElement savePageButton;

    @FindBy(xpath = ".//input[@id='pollTagline']")
    private WebElement questionField;

    public NewPollManagerPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return questionField;
    }

    @BeforeClass
    private void waitLoading() {
        waitUtils.waitForLoading();
    }

    public NewPollManagerPage selectPollCategory(String selectItem) {
        waitUtils.waitForLoading();
        if (Objects.equals(getDropdownItem(selectItem).getAttribute("selected"), null)) {
            Select select = new Select(pollCategoryDropdown);
            select.selectByVisibleText(selectItem);
        }

        return this;
    }

    public boolean isOptionSelectedInDropdown(String dropdownItem) {

        return Objects.equals(getDropdownItem(dropdownItem).getAttribute("selected"), "true");
    }

    private WebElement getDropdownItem(String dropdownItem) {
        waitUtils.waitForLoading();
        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath(String.format(categorySelectItem, dropdownItem)));
    }

    public NewPollManagerPage fillAnswer(String answerNumber, String answerText) {
        waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(answerField, answerNumber))).sendKeys(answerText);

        return this;
    }

    public NewPollManagerPage fillQuestion(String questionText) {
        waitUtils.getElementWhenVisibleAfterShortWait(questionField).sendKeys(questionText);

        return this;
    }

    public NewPollManagerPage fillAdditionalInformation(String answerNumber, String additionalInformationText) {
        waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(additionalInformationField, answerNumber))).sendKeys(additionalInformationText);

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
}
