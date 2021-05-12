package portalPages.publisher.polls;

import base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

import java.util.Objects;

@Getter
@Setter
public class SelectPollLanguageModalPage extends BaseComponent {

    WaitUtils waitUtils;

    private final String selectItems = ".//option[contains(text(), '%s')]";

    private final String modalSelect = ".//select[contains(@class, '%s')]";

    @FindBy(xpath = ".//input[contains(@class, 'js-create-new-entity')]")
    private WebElement createNewPollButton;

    public SelectPollLanguageModalPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(String.format(modalSelect, PollRegionsAndLanguage.SELECT_REGION)));
    }

    public SelectPollLanguageModalPage selectDropdownAndLanguage(String selectTitle, String selectItem) {
        WebElement openedSelect =
                waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath(String.format(modalSelect, selectTitle)));

        if (Objects.equals(getDropdownItem(selectItem).getAttribute("selected"), null)) {
            Select select = new Select(openedSelect);
            select.selectByVisibleText(selectItem);
        }

        return this;
    }

    public void modalSubmit() {
        waitUtils.clickWhenReadyAfterShortWait(createNewPollButton);
    }

    public boolean isOptionSelectedInDropdown(String dropdownItem) {
        return Objects.equals(getDropdownItem(dropdownItem).getAttribute("selected"), "true");
    }

    private WebElement getDropdownItem(String dropdownItem) {
        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath(String.format(selectItems, dropdownItem)));
    }
}
