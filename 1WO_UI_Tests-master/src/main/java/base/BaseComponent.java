package base;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.ActionHelperUtils;
import utils.WaitUtils;

public abstract class BaseComponent implements WebElementProvider {
    protected WebDriver driver;
    protected TypeUtils typeUtils;
    protected ActionHelperUtils actions;
    WaitUtils waitUtils;

    public BaseComponent(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = (WebDriver) driver;
//        typeUtils = new TypeUtils(driver);
        actions = new ActionHelperUtils(driver);
    }

    protected abstract WebElement getMainElementInComponent();

    public boolean isDisplayed() {
//        driver.wai
        return waitUtils.isElementVisibleNow(getMainElementInComponent());
    }

    public boolean isDisplayed(int seconds) {
        return waitUtils.isElementVisible(getMainElementInComponent(), seconds);
    }

    protected boolean isCheckBoxSelected(WebElement element) {
        if (element.getAttribute("class").contains("checked")) {
            return element.getAttribute("class").contains(" checked");
        } else {
            return element.getAttribute("aria-checked").contains("true");
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
}
