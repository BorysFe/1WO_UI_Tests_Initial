package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WOWebDriver implements WebDriver, JavascriptExecutor, HasInputDevices, TakesScreenshot, HasCapabilities {
    private static final Logger logger = LoggerFactory.getLogger(WOWebDriver.class);
    private WebDriver webDriver;
    public WaitUtils waitUtils;

    private String uniqueInstanceMarker = RandomStringUtils.randomAlphabetic(20);
    private String browserName;
    private String testMethod;
    private String browserVersion;

    public WOWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.waitUtils = new WaitUtils(webDriver);

        WebDriver capWebDriver = webDriver;
        if (webDriver instanceof WrapsDriver) {
            capWebDriver = ((WrapsDriver) webDriver).getWrappedDriver();
        }

        Capabilities caps = ((RemoteWebDriver) capWebDriver).getCapabilities();
        this.browserName = caps.getBrowserName();
        this.browserVersion = caps.getVersion();
        logger.info("Browser : {}.{}", browserName, browserVersion);
    }

    @Override
    public Keyboard getKeyboard() {
        return ((HasInputDevices) webDriver).getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return ((HasInputDevices) webDriver).getMouse();
    }

    public WebDriver getWrappedDriver() {
        return webDriver;
    }

    @Override
    public void get(String url) {
        webDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        // A selenium-provided WebDriver returns a list of WebElements with findElements, but the underlying
        // implementation is the selenium one. We want to use the WOWebElement implementation, so we need to wrap
        // the returned elements.
        List<WebElement> woElements = new ArrayList<>();

        List<WebElement> seleniumElements = webDriver.findElements(by);
        for (WebElement seleniumElement : seleniumElements) {
            woElements.add(new WOWebElement(seleniumElement, by));
        }
        return woElements;
    }

    @Override
    public WebElement findElement(By by) {
        return new WOWebElement(by, this.webDriver);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        webDriver.get("about:blank");
        webDriver.close();
    }

    @Override
    public void quit() {
        webDriver.get("about:blank");
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) webDriver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return ((JavascriptExecutor) webDriver).executeAsyncScript(script, args);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot) webDriver).getScreenshotAs(target);
    }

    @Override
    public Capabilities getCapabilities() {
        WebDriver driver = webDriver;
        if (driver instanceof EventFiringWebDriver) {
            driver = ((EventFiringWebDriver) driver).getWrappedDriver();
        }
        return ((HasCapabilities) driver).getCapabilities();
    }

    public SessionId getSessionId() {
        return ((RemoteWebDriver) webDriver).getSessionId();
    }

    public String getTestMethod() {
        return testMethod;
    }

    @Override
    public int hashCode() {
        return uniqueInstanceMarker.hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }
        if (this.getClass() != that.getClass()) {
            return false;
        }
        WOWebDriver aThat = (WOWebDriver) that;
        return this.uniqueInstanceMarker.equals(aThat.uniqueInstanceMarker);
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }
}
