package base;

import base.enums.GeneralLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.ActionHelperUtils;
import utils.WaitUtils;

import java.util.Date;

public abstract class BaseComponent implements WebElementProvider {
    protected WebDriver driver;
    protected ActionHelperUtils actions;
    WaitUtils waitUtils;
    Date date;

    public BaseComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new ActionHelperUtils(driver);
        waitUtils = new WaitUtils(driver);
        date = new Date();
    }

    protected abstract WebElement getMainElementInComponent();

    public boolean isDisplayed() {
        return waitUtils.isElementVisibleNow(getMainElementInComponent());
    }

    public boolean isDisplayed(int seconds) {
        return waitUtils.isElementVisible(getMainElementInComponent(), seconds);
    }

    protected boolean isCheckBoxSelected(WebElement element) {
        if (element.getAttribute("checked").contains("checked")) {
            return element.getAttribute("checked").contains(" checked");
        } else {
            return element.getAttribute("value").contains("true");
        }
    }

    protected void selectCheckBox(WebElement element) {
        if (!isCheckBoxSelected(element)) {
            element.click();
            WaitUtils.waitMilliseconds(500, "Small wait after click on checkbox");
        }
    }

    protected void unselectCheckBox(WebElement element) {
        if (isCheckBoxSelected(element)) {
            element.click();
            WaitUtils.waitMilliseconds(500, "Small wait after click on checkbox");
        }
    }

    protected void setField(WebElement element, String text) {
        waitUtils.getElementWhenVisibleAfterShortWait(element).clear();
        waitUtils.getElementWhenVisibleAfterShortWait(element).sendKeys(text);
    }

    protected void setField(By element, String text) {
        waitUtils.waitVisibilityOfElementByShort(element);

        waitUtils.getElementWhenVisibleAfterShortWait(element).clear();
        waitUtils.waitVisibilityOfElementByShort(element);
        waitUtils.getElementWhenVisibleAfterShortWait(element).sendKeys(text);
    }

    protected void clickNextButton() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Next")));
        waitUtils.waitForLoading();
    }

    protected void clickFinishButton() {
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath
                (String.format(GeneralLocators.SPAN_BY_CONTAINS_TEXT.toString(), "Finish")));
        waitUtils.waitForLoading();
    }
}
