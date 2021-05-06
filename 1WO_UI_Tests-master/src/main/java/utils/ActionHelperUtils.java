package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class ActionHelperUtils {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private By reactSelectValueBy = By.xpath(".//div[contains(@class,'Select-option')]");

    protected static final Logger logger = LoggerFactory.getLogger(ActionHelperUtils.class);

    public ActionHelperUtils(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void selectFromDropdown(By selectElement, String itemToSelect) {
        Select select = new Select(waitUtils.waitForElementToBeDisplayedAfterLongWait(selectElement));
        select.selectByVisibleText(itemToSelect);
        logger.debug("The selected value :" + select.getFirstSelectedOption().getText());
    }

    public void selectFromDropdown(By selectElement, int itemToSelect) {
        WebElement element = waitUtils.getElementWhenVisibleAfterShortWait(selectElement);
        if ("div".equalsIgnoreCase(element.getTagName())
                && element.getAttribute("class").contains("ui-select-container")) {
            throw new RuntimeException("Not supported function for select2 component. " +
                    "Please use selectValueFromDropdown instead.");
        } else {
            Select select = new Select(element);
            String toSelect = String.valueOf(itemToSelect);
            select.selectByVisibleText(toSelect);
        }
    }

    public void selectValueFromDropdown(WebElement element, String valueToSelect) {
        if ("div".equalsIgnoreCase(element.getTagName())
                && element.getAttribute("class").contains("ui-select-container")) {
            WebElement caret = element.findElement(
                    By.xpath(String.format("//*[@id='%s']//i[@class='caret pull-right']",
                            element.getAttribute("id"))));
            caret.click();
            By optionCSSSelector = By.xpath(String.format("//*[@id='%s']//span[contains(text(), '%s')]",
                    element.getAttribute("id"), valueToSelect));
            WebElement option = element.findElement(optionCSSSelector);
            option.click();
        } else {
            Select select = new Select(waitUtils.getElementWhenVisibleAfterLongWait(element));
            select.selectByValue(valueToSelect);
        }
    }

    public String getSelectedTextFromDropdown(By selectElement) {
        Select select = new Select(waitUtils.getElementWhenVisibleAfterShortWait(selectElement));
        return select.getFirstSelectedOption().getText();
    }

    public void checkBox(By checkBox, boolean check) {
        boolean isChecked = waitUtils.getElementWhenVisibleAfterShortWait(checkBox).isSelected();

        if ((isChecked && !check) || (!isChecked && check)) {
            clickOnWebElementUsingJavascript(waitUtils.getElementWhenVisibleAfterShortWait(checkBox));
        }
    }

    public void checkBox(WebElement checkBox, boolean check) {
        boolean isChecked = waitUtils.getElementWhenVisibleAfterShortWait(checkBox).isSelected();

        if ((isChecked && !check) || (!isChecked && check)) {
            waitUtils.clickWhenReadyAfterShortWait(checkBox);
        }
    }

    public void clearConsoleErrors() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "console.clear();";
        js.executeScript(script);
        List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).getAll();
        logs.stream().filter(logEntry -> logEntry.getLevel().equals(Level.ALL)).collect(Collectors.toList());
    }

    public void button(WebElement element) {
        waitUtils.clickWhenReadyAfterLongWait(element);
    }

    public void button(By by) {
        waitUtils.clickWhenReadyAfterLongWait(by);
    }

    public String getElementAttributeWithinIFrame(By iFrameBy, By elementBy, String attribute) {
        String attributeValue;
        String originalWindowHandle;
        originalWindowHandle = driver.getWindowHandle();
        driver.switchTo().frame(driver.findElement(iFrameBy));
        WebElement element = waitUtils.getElementWhenVisibleAfterShortWait(elementBy);
        attributeValue = element.getAttribute(attribute);
        driver.switchTo().window(originalWindowHandle);
        return attributeValue;
    }

    public void mouseHover(By mouseHover) {
        Actions action = new Actions(driver);
        WebElement we = waitUtils.getElementWhenVisibleAfterLongWait(mouseHover);
        action.moveToElement(we).build().perform();
    }

    public void mouseHover(WebElement mouseHover) {
        Actions action = new Actions(driver);
        WebElement we = waitUtils.getElementWhenVisibleAfterLongWait(mouseHover);
        action.moveToElement(we).build().perform();
    }

    public static final int DEFAULT_WAIT_MILLIS = 20000;

//    public static WebElement waitForAvailableName(AppiumDriver driver, String elemName) {
//        long start = System.currentTimeMillis();
//        WebElement elem = null;
//
//        while (elem == null && (start + DEFAULT_WAIT_MILLIS < System.currentTimeMillis())) {
//            try {
//                elem = driver.findElementByName(elemName);
//            } catch (NoSuchElementException e) {
//                // ignore
//            }
//        }
//
//        // just try one more time and let exception get thrown
//        elem = driver.findElementByName(elemName);
//
//        return elem;
//    }

    public static void leftDoubleClick(Point p) throws AWTException {
        Robot robot = new Robot();

        robot.delay(1000);
        robot.mouseMove(p.x, p.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(200);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delay(100);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.delay(100);
    }

    public void clickOnElementUsingJavascript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickOnElementUsingJavascript(String id) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("$(\"*[data-ng-id='%s']\").click();", id));
    }

    public void clearByJavascript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value = '';", element);
    }

    public void sendKeysByJavascript(WebElement element, String str) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("arguments[0].value = '%s';", str), element);
    }

//    public void sendKeysHtmlTextByJavascript(WebElement element, String str) {
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript(String.format("arguments[0].value = '%s';", StringEscapeUtils.escapeJavaScript(str)), element);
//    }

    public String getValueWithJavascript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return executor.executeScript("return arguments[0].value;", element).toString();
    }

    public void maximizeBrowserWindow() {
        driver.manage().window().maximize();
    }

    public void resizeBrowserWindow(int width, int height) {
        Dimension dimension = new Dimension(width, height);
        driver.manage().window().setSize(dimension);
    }

    public static void hoverOverElement(WebDriver driver, WebElement element) {
        Actions mousehover = new Actions(driver);
        mousehover.moveToElement(element).build().perform();
    }

    public void clickOnWebElementUsingJavascript(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
    }

    public void clickOnWebElementUsingJavascript(By by) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement element = waitUtils.waitForElementToBeDisplayedAfterShortWait(by);
        executor.executeScript("arguments[0].click()", element);
    }

    public void unhideElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'display: block;')", element);
    }

    public void unhideElement(By selector) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'display: block;')", driver.findElement(selector));
    }

    public void reactSelectOption(WebElement selectInput, String value) {
        boolean isSelected = false;
        Actions act = new Actions(driver);
        act.click(selectInput).build().perform();

        waitUtils.waitForElementToBeDisplayedAfterShortWait(reactSelectValueBy);
        List<WebElement> selectValues = driver.findElements(reactSelectValueBy);
        for (WebElement selectValue: selectValues) {
            if (selectValue.getText().contains(value)) {
                act.click(selectValue).build().perform();
                isSelected = true;
                break;
            }
        }

        if (!isSelected) {
            throw new org.openqa.selenium.NoSuchElementException(reactSelectValueBy.toString());
        }
    }

    /**
     * Use this method for any actions where you can catch a StaleElementReferenceException
     * e.g. actionWrapper(() -> driver.waitUtils.clickWhenReadyAfterShortWait(by));
     */
    public void actionWrapper(Runnable toRun) {
        for (int i = 1; i < 10; i++) {
            try {
                toRun.run();
                return;
            } catch (StaleElementReferenceException ignored) {
            }
        }

        throw new RuntimeException("Could not perform the action");
    }

    /**
     * Use this method for any actions that return something (not void),
     * and you can catch a StaleElementReferenceException
     * e.g. actionWrapper(() -> driver.waitUtils.waitForElementToBeDisplayedAfterShortWait(by).getAttribute("attribute"));
     */
    public String actionWrapper(Callable<String> toRun) {
        for (int i = 1; i < 10; i++) {
            try {
                final String action = newSingleThreadExecutor().submit(toRun).get();

                if (action != null) return action;
            } catch (NoSuchElementException | InterruptedException | ExecutionException | StaleElementReferenceException ignored) {
            }
        }

        throw new RuntimeException("Could not perform the action");
    }

    /**
     * Click in different ways (simple click, JS, actions) on element
     * @param element - element to be clicked on
     * @param waitForElement - element awaited not to be present after click
     * @param timeout - time out (in seconds)
     */
    public void clickExpectingElement(WebElement element, WebElement waitForElement, int timeout) {
        String elementLocator = WebDriverEventListener.getLocator(element);

        waitUtils.clickWhenReadyAfterLongWait(element);

        if (!waitUtils.isElementVisible(waitForElement, timeout)) {
            logger.info(String.format("Clicked with JavaScript on %s", elementLocator));
            clickOnWebElementUsingJavascript(element);
        }

        if (!waitUtils.isElementVisible(waitForElement, timeout)) {
            logger.info(String.format("Clicked with Actions on %s", elementLocator));
            Actions actions = new Actions(driver);
            actions.click(element).build().perform();
        }

        if (!waitUtils.isElementVisible(waitForElement, timeout)) {
            logger.info(String.format("Clicked with Actions 2 on %s", elementLocator));
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();
        }

        if (!waitUtils.isElementVisible(waitForElement, timeout)) {
            logger.info(String.format("Clicked with double-click on %s", elementLocator));
            Actions actions = new Actions(driver);
            actions.doubleClick(element).build().perform();
        }
    }
    public void mouseHoverAndClick(WebElement mouseHover, WebElement clickOn) {
        Actions action = new Actions(driver);
        action.moveToElement(waitUtils.waitForElementToBeDisplayedAfterLongWait(mouseHover))
                .moveToElement(waitUtils.waitForElementToBeDisplayedAfterLongWait(clickOn))
                .click()
                .build()
                .perform();
    }
}
