package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import java.util.ArrayList;
import java.util.List;

public class WOWebElement implements WebElement, WrapsElement, Locatable {

    private final By by;
    private final SearchContext context;
    private WebElement webElement;


    public WOWebElement(By by, SearchContext context) {
        this.by = by;
        this.context = context;
        this.webElement = context.findElement(by);
    }

    /**
     * For findElements calls, there are multiple results, so the By is not useful as we don't know which result it
     * refers to. Just use the element directly in this case.
     * It's high chance element has already been created with by, so check it and reassign previous "by".
     */
    public WOWebElement(WebElement element) {
        if (element instanceof WOWebElement) {
            this.by = ((WOWebElement) element).by;
        } else {
            this.by = null;
        }
        this.context = null;
        this.webElement = element;
    }

    /**
     * By is passed for logging.
     */
    public WOWebElement(WebElement element, By by) {
        this.by = by;
        this.context = null;
        this.webElement = element;
    }

    @Override
    public WebElement getWrappedElement() {
        // see if the cached element is stale, and if so get it again
        try {
            webElement.isEnabled();
            return webElement;
        } catch (StaleElementReferenceException e) {
            if (by == null || context == null) {
                throw (e);
            }
            webElement = context.findElement(by);
            return webElement;
        }
    }

    @Override
    public void click() {
//        PollingStateTracker.changeState(Thread.currentThread().getId(), PollingStateTracker.PollingState.ACTED);
        WebElement webElement = getWrappedElement();
        try {
            webElement.click();

        } catch (WebDriverException e) {
            if (e.getMessage().contains("UnknownError: Cannot press more then one button or an already pressed button.")) {
                WebDriver driver = ((WrapsDriver) webElement).getWrappedDriver();
                Actions builder = new Actions(driver);
                builder.release(webElement).perform();
                webElement.click();
            } else {
                throw e;
            }
        }
    }

    @Override
    public void submit() {
//        PollingStateTracker.changeState(Thread.currentThread().getId(), PollingStateTracker.PollingState.ACTED);
        WebElement webElement = getWrappedElement();
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
//        PollingStateTracker.changeState(Thread.currentThread().getId(), PollingStateTracker.PollingState.ACTED);
        WebElement webElement = getWrappedElement();
        webElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
//        PollingStateTracker.changeState(Thread.currentThread().getId(), PollingStateTracker.PollingState.ACTED);
        WebElement webElement = getWrappedElement();
        webElement.clear();
    }

    @Override
    public String getTagName() {
        WebElement webElement = getWrappedElement();
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        WebElement webElement = getWrappedElement();
        return webElement.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        WebElement webElement = getWrappedElement();
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        WebElement webElement = getWrappedElement();
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        WebElement webElement = getWrappedElement();
        return webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        List<WebElement> woElements = new ArrayList<>();

        WebElement webElement = getWrappedElement();
        List<WebElement> seleniumElements = webElement.findElements(by);
        for (WebElement seleniumElement : seleniumElements) {
            woElements.add(new WOWebElement(seleniumElement, by));
        }
        return woElements;
    }

    @Override
    public WebElement findElement(By by) {
        return new WOWebElement(by, this.webElement);
    }

    @Override
    public boolean isDisplayed() {
        WebElement webElement = getWrappedElement();
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        WebElement webElement = getWrappedElement();
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        WebElement webElement = getWrappedElement();
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return null;
    }

    @Override
    public String getCssValue(String propertyName) {
        WebElement webElement = getWrappedElement();
        return webElement.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        return null;
    }

    @Override
    public String toString() {
        return webElement == null ? "null" : webElement.toString();
    }

    @Override
    public Coordinates getCoordinates() {
        WebElement webElement = getWrappedElement();
        return ((Locatable) webElement).getCoordinates();
    }
}
