package portalPages.publisher.polls;

import base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import utils.WaitUtils;

import java.util.Objects;

@Getter
public class NewPollFirstPage extends BaseComponent {

    WaitUtils waitUtils;

    private final String categorySelectItem = ".//option[contains(text(), '%s')]";
    private final String answer = ".//input[@id='pollAnswerSide%s']";

    @FindBy(xpath = ".//select[@id='pollCategoryId']")
    private WebElement pollCategoryDropdown;

    @FindBy(xpath = ".//input[@id='pollSubmitButton']")
    private WebElement saveButton;

    @FindBy(xpath = ".//input[@id='pollSubmitButton']")
    private WebElement questionField;

    public NewPollFirstPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    @BeforeClass
    private void waitLoading() {
        waitUtils.waitForLoading();
    }

    public NewPollFirstPage selectPollCategory(String selectItem) {

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

        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath(String.format(categorySelectItem, dropdownItem)));
    }

    public NewPollFirstPage fillAnswer(String answerNumber, String answerText) {
        waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(answer, answerNumber))).sendKeys(answerText);

        return this;
    }

    public NewPollFirstPage fillQuestion(String questionText) {
        waitUtils.getElementWhenVisibleAfterShortWait(questionField).sendKeys(questionText);

        return this;
    }

    public void saveFirstPage() {
        waitUtils.clickWhenReadyAfterShortWait(saveButton);
        waitUtils.waitForLoading();
    }
}
