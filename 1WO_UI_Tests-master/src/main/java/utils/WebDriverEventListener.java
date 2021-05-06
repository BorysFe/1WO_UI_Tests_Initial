package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverEventListener extends AbstractWebDriverEventListener {
    private static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(WebDriverEventListener.class);
    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        logger.info("Navigate to url {}", url);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logger.info("Click on element {}", getLocator(element));
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        super.beforeChangeValueOf(element, driver, keysToSend);
        logger.info("Change value of element {}", getLocator(element));
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        logger.info("Execute script {}", script);
    }

    public static String getLocator(WebElement element) {
        String s = element.toString();
        if (s.contains("->")) {
            return s.substring(s.indexOf("->"), s.length() - 1);
        }
        return s;
    }
}
