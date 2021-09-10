package portalPages;

import base.BaseComponent;
import base.enums.GeneralLocators;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import portalPages.polls.RegionsAndLanguages;
import utils.WaitUtils;

import java.util.Objects;

@Getter
@Setter
public class SelectLanguageModalPage extends BaseComponent {

    WaitUtils waitUtils;

    public SelectLanguageModalPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath
                (String.format(GeneralLocators.INPUT_BY_CLASS.toString(), "js-create-new-entity")));
    }

    public SelectLanguageModalPage selectDropdownAndItem(String selectTitle, String selectItem) {
        WebElement openedSelect =
                waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                        (String.format(GeneralLocators.SELECT_BY_CLASS.toString(), selectTitle)));

        if (Objects.equals(getDropdownItem(selectItem).getAttribute("selected"), null)) {
            Select select = new Select(openedSelect);
            select.selectByVisibleText(selectItem);
        }

        return this;
    }

    public void modalSubmit() {
        waitUtils.clickWhenReadyAfterShortWait(By.xpath
                (String.format(GeneralLocators.INPUT_BY_CLASS.toString(), "js-create-new-entity")));
    }

    public boolean isOptionSelectedInDropdown(String dropdownItem) {
        return Objects.equals(getDropdownItem(dropdownItem).getAttribute("selected"), "true");
    }

    private WebElement getDropdownItem(String dropdownItem) {
        return waitUtils.waitForElementToBeDisplayedAfterShortWait(By.xpath
                (String.format(GeneralLocators.OPTION_BY_TEXT.toString(), dropdownItem)));
    }
}
