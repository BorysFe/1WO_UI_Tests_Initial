package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.conditions.WOExpectedConditions;

import java.util.List;

public class WaitUtils extends DriverFactory {

    public static final int SHORT_TIMEOUT = 5;
    private WebDriverWait shortWait;

    public static final int MIDDLE_TIMEOUT = 10;
    private WebDriverWait midWait;

    public static final int LONG_TIMEOUT = 15;
    private WebDriverWait longWait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
//        jsUtility = new JSUtility(driver);

        shortWait = new WebDriverWait(driver, SHORT_TIMEOUT);
        shortWait.ignoring(StaleElementReferenceException.class);

        midWait = new WebDriverWait(driver, MIDDLE_TIMEOUT);
        midWait.ignoring(StaleElementReferenceException.class);

        longWait = new WebDriverWait(driver, LONG_TIMEOUT);
        longWait.ignoring(StaleElementReferenceException.class);
    }

    private By spinnerSelector = By.xpath(".//div[@id='loaderDiv']");

    public void waitForLoading() {
        waitMilliseconds(500, "Wait for loading spinner to be displayed");
        boolean isSpinnerVisible;
        isSpinnerVisible = isElementVisibleNow(spinnerSelector);
        if (isSpinnerVisible) {
            longWait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerSelector));
        }
    }

    public static void waitForNSeconds(int n, String reason) {
        waitMilliseconds(n * 1000, reason);
    }

    public static void waitForNSeconds(int n) {
        waitForNSeconds(n, "wait");
    }

    public static void waitMilliseconds(int milliseconds, String reason) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new Error("got interrupted:" + e.getMessage(), e);
        }
    }

    public void waitUntilJavascriptIsTrue(String js) {
        midWait.until(WOExpectedConditions.isJavascriptTrue(js));
    }


    public void waitMilliseconds(int milliseconds) {
        waitMilliseconds(milliseconds, "wait");
    }


    public void waitForAlertToShowUp() {
        midWait.until(ExpectedConditions.alertIsPresent());
    }

    //    public void waitVisibilityOfElement(WebElement element, int timeOut) {
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver("chrome"), timeOut);
//        webDriverWait.until(ExpectedConditions.visibilityOf(element));
//    }
//
//    public WebElement waitVisibilityOfElementShort(WebElement element) {
//        waitVisibilityOfElement(element, shortTimeout);
//        return element;
//    }
//
//    public void waitVisibilityOfElementLong(WebElement element) {
//        waitVisibilityOfElement(element, longTimeout);
//    }
//
//    public void waitVisibilityOfListElements(List<WebElement> elements, int timeOut) {
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver("chrome"), timeOut);
//        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
//    }
//
//    public void waitVisibilityOfElementsShort(List<WebElement> elements) {
//        waitVisibilityOfListElements(elements, shortTimeout);
//    }
//
//    public void waitVisibilityOfElementsLong(List<WebElement> elements) {
//        waitVisibilityOfListElements(elements, longTimeout);
//    }
//
//    public void waitVisibilityOfElementBy(By element, int timeOut) {
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver("chrome"), timeOut);
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(element));
//    }
//
//    public void waitVisibilityOfElementByShort(By element) {
//        waitVisibilityOfElementBy(element, shortTimeout);
//    }
//
//    public void waitVisibilityOfElementByLong(By element) {
//        waitVisibilityOfElementBy(element, longTimeout);
//    }
//
//    public void waitInvisibilityOfElementBy(By element, int timeOut) {
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver("chrome"), timeOut);
//        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(element));
//    }
//
//    public void waitInvisibilityOfElementByShort(By element) {
//        waitInvisibilityOfElementBy(element, shortTimeout);
//    }
//
//    public void waitInvisibilityOfElementByLong(By element) {
//        waitInvisibilityOfElementBy(element, longTimeout);
//    }
//
//    public void waitInvisibilityOfElement(WebElement element, int timeOut) {
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver("chrome"), timeOut);
//        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
//    }
//
//    public void waitInvisibilityOfElementShort(WebElement element) {
//        waitInvisibilityOfElement(element, shortTimeout);
//    }
//
//    public void waitInvisibilityOfElementLong(WebElement element) {
//        waitInvisibilityOfElement(element, longTimeout);
//    }
//
//    public void waitInvisibilityOfListElements(List<WebElement> elements, int timeOut) {
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver("chrome"), timeOut);
//        webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
//    }
//
//    public void waitInvisibilityOfElementsShort(List<WebElement> elements) {
//        waitInvisibilityOfListElements(elements, shortTimeout);
//    }
//
//    public void waitInvisibilityOfElementsLong(List<WebElement> elements) {
//        waitInvisibilityOfListElements(elements, longTimeout);
//    }
//
//    public void waitPresenceOfElementByLocated(By element, int timeOut) {
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver("chrome"), timeOut);
//        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(element));
//    }
//
//    public void waitPresenceOfElementByShort(By element) {
//        waitPresenceOfElementByLocated(element, shortTimeout);
//    }
//
//    public void waitPresenceOfElementByLong(By element) {
//        waitPresenceOfElementByLocated(element, longTimeout);
//    }
//
//    public void waitElementToBeClickable(WebElement element, int timeOut) {
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver("chrome"), timeOut);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//    public void waitElementToBeClickableShort(WebElement element) {
//        waitElementToBeClickable(element, shortTimeout);
//    }
//
//    public void waitElementToBeClickableLong(WebElement element) {
//        waitElementToBeClickable(element, longTimeout);
//    }
//
//    public void waitElementToBeClickable(By element, int timeOut) {
//        WebDriverWait webDriverWait = new WebDriverWait(getDriver("chrome"), timeOut);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//    public void waitElementToBeClickableShort(By element) {
//        waitElementToBeClickable(element, shortTimeout);
//    }
//
//    public void waitElementToBeClickableLong(By element) {
//        waitElementToBeClickable(element, longTimeout);
//    }
//
//    public boolean isElementVisibleShortTimeout(WebElement element) {
//        try {
//            waitVisibilityOfElementShort(element);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public void clickWhenReadyAfterShortWait(By element) {
//        waitVisibilityOfElementByShort(element);
//        waitElementToBeClickableShort(element);
//        getDriver("chrome").findElement(element).click();
//    }

    public boolean isElementVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            return wait.until(WOExpectedConditions.visibilityOf(element)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        try {
            return wait.until(WOExpectedConditions.visibilityOf(locator));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementVisibleNow(By locator) {
        return isElementVisible(locator, 0);
    }

    public boolean isElementVisibleAfterShortWait(By locator) {
        return isElementVisible(locator, SHORT_TIMEOUT);
    }

    public boolean isElementVisibleAfterMiddleWait(By locator) {
        return isElementVisible(locator, MIDDLE_TIMEOUT);
    }

    public boolean isElementVisibleAfterLongWait(By locator) {
        return isElementVisible(locator, LONG_TIMEOUT);
    }

    public boolean isElementVisible(WebElement element) {
        return isElementVisible(element, SHORT_TIMEOUT);
    }

    public boolean isElementVisibleNow(WebElement element) {
        return isElementVisible(element, 0);
    }

    public boolean isElementVisibleAfterShortWait(WebElement element) {
        return isElementVisible(element, SHORT_TIMEOUT);
    }

    public boolean isElementVisibleAfterMiddleWait(WebElement element) {
        return isElementVisible(element, MIDDLE_TIMEOUT);
    }

    public boolean isElementVisibleAfterLongWait(WebElement element) {
        return isElementVisible(element, LONG_TIMEOUT);
    }

    public void waitVisibilityOfElement(WebElement element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitVisibilityOfElementShort(WebElement element) {
        waitVisibilityOfElement(element, SHORT_TIMEOUT);
        return element;
    }

    public void waitVisibilityOfElementLong(WebElement element) {
        waitVisibilityOfElement(element, LONG_TIMEOUT);
    }

    public void waitVisibilityOfListElements(List<WebElement> elements, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitVisibilityOfElementsShort(List<WebElement> elements) {
        waitVisibilityOfListElements(elements, SHORT_TIMEOUT);
    }

    public void waitVisibilityOfElementsLong(List<WebElement> elements) {
        waitVisibilityOfListElements(elements, LONG_TIMEOUT);
    }

    public void waitVisibilityOfElementBy(By element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitVisibilityOfElementByShort(By element) {
        waitVisibilityOfElementBy(element, SHORT_TIMEOUT);
    }

    public void waitVisibilityOfElementByLong(By element) {
        waitVisibilityOfElementBy(element, LONG_TIMEOUT);
    }

    public void waitInvisibilityOfElementBy(By element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void waitInvisibilityOfElementByShort(By element) {
        waitInvisibilityOfElementBy(element, SHORT_TIMEOUT);
    }

    public void waitInvisibilityOfElementByLong(By element) {
        waitInvisibilityOfElementBy(element, LONG_TIMEOUT);
    }

    public void waitInvisibilityOfElement(WebElement element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitInvisibilityOfElementShort(WebElement element) {
        waitInvisibilityOfElement(element, SHORT_TIMEOUT);
    }

    public void waitInvisibilityOfElementLong(WebElement element) {
        waitInvisibilityOfElement(element, LONG_TIMEOUT);
    }

    public void waitInvisibilityOfListElements(List<WebElement> elements, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public void waitInvisibilityOfElementsShort(List<WebElement> elements) {
        waitInvisibilityOfListElements(elements, SHORT_TIMEOUT);
    }

    public void waitInvisibilityOfElementsLong(List<WebElement> elements) {
        waitInvisibilityOfListElements(elements, LONG_TIMEOUT);
    }

    public void waitPresenceOfElementByLocated(By element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void waitPresenceOfElementByShort(By element) {
        waitPresenceOfElementByLocated(element, SHORT_TIMEOUT);
    }

    public void waitPresenceOfElementByLong(By element) {
        waitPresenceOfElementByLocated(element, LONG_TIMEOUT);
    }

    public void waitElementToBeClickable(WebElement element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementToBeClickableShort(WebElement element) {
        waitElementToBeClickable(element, SHORT_TIMEOUT);
    }

    public void waitElementToBeClickableLong(WebElement element) {
        waitElementToBeClickable(element, LONG_TIMEOUT);
    }

    public void waitElementToBeClickable(By element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementToBeClickableShort(By element) {
        waitElementToBeClickable(element, SHORT_TIMEOUT);
    }

    public void waitElementToBeClickableLong(By element) {
        waitElementToBeClickable(element, LONG_TIMEOUT);
    }

    public boolean isElementVisibleShortTimeout(WebElement element) {
        try {
            waitVisibilityOfElementShort(element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickWhenReadyAfterShortWait(By element) {
        waitVisibilityOfElementByShort(element);
        waitElementToBeClickableShort(element);
        driver.findElement(element).click();
    }
}
