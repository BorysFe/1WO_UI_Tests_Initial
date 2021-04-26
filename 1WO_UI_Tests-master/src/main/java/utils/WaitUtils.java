package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitUtils extends DriverFactory {

    private final int shortTimeout = 5;
    private final int longTimeout = 15;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
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

    public void waitVisibilityOfElement(WebElement element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitVisibilityOfElementShort(WebElement element) {
        waitVisibilityOfElement(element, shortTimeout);
        return element;
    }

    public void waitVisibilityOfElementLong(WebElement element) {
        waitVisibilityOfElement(element, longTimeout);
    }

    public void waitVisibilityOfListElements(List<WebElement> elements, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitVisibilityOfElementsShort(List<WebElement> elements) {
        waitVisibilityOfListElements(elements, shortTimeout);
    }

    public void waitVisibilityOfElementsLong(List<WebElement> elements) {
        waitVisibilityOfListElements(elements, longTimeout);
    }

    public void waitVisibilityOfElementBy(By element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitVisibilityOfElementByShort(By element) {
        waitVisibilityOfElementBy(element, shortTimeout);
    }

    public void waitVisibilityOfElementByLong(By element) {
        waitVisibilityOfElementBy(element, longTimeout);
    }

    public void waitInvisibilityOfElementBy(By element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public void waitInvisibilityOfElementByShort(By element) {
        waitInvisibilityOfElementBy(element, shortTimeout);
    }

    public void waitInvisibilityOfElementByLong(By element) {
        waitInvisibilityOfElementBy(element, longTimeout);
    }

    public void waitInvisibilityOfElement(WebElement element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitInvisibilityOfElementShort(WebElement element) {
        waitInvisibilityOfElement(element, shortTimeout);
    }

    public void waitInvisibilityOfElementLong(WebElement element) {
        waitInvisibilityOfElement(element, longTimeout);
    }

    public void waitInvisibilityOfListElements(List<WebElement> elements, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

    public void waitInvisibilityOfElementsShort(List<WebElement> elements) {
        waitInvisibilityOfListElements(elements, shortTimeout);
    }

    public void waitInvisibilityOfElementsLong(List<WebElement> elements) {
        waitInvisibilityOfListElements(elements, longTimeout);
    }

    public void waitPresenceOfElementByLocated(By element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public void waitPresenceOfElementByShort(By element) {
        waitPresenceOfElementByLocated(element, shortTimeout);
    }

    public void waitPresenceOfElementByLong(By element) {
        waitPresenceOfElementByLocated(element, longTimeout);
    }

    public void waitElementToBeClickable(WebElement element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementToBeClickableShort(WebElement element) {
        waitElementToBeClickable(element, shortTimeout);
    }

    public void waitElementToBeClickableLong(WebElement element) {
        waitElementToBeClickable(element, longTimeout);
    }

    public void waitElementToBeClickable(By element, int timeOut) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOut);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementToBeClickableShort(By element) {
        waitElementToBeClickable(element, shortTimeout);
    }

    public void waitElementToBeClickableLong(By element) {
        waitElementToBeClickable(element, longTimeout);
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
