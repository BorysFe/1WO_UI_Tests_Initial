package base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public interface WebElementProvider {
    default WebElement getWebElement(Callable<WebElement> toRunAction) {
        WebElement finalWebElement = internalGetWebElement(toRunAction);
        if (finalWebElement != null) return finalWebElement;

        throw new RuntimeException("Could not convert to WebElement");
    }

    default WebElement getWebElement(Callable<WebElement> toRunAction, int timeOutInSeconds) {
        for (int i = 1; i < timeOutInSeconds; i++) {
            WebElement finalWebElement = internalGetWebElement(toRunAction);
            if (finalWebElement != null) return finalWebElement;
        }

        throw new RuntimeException("Could not convert to WebElement");
    }

    default WebElement internalGetWebElement(Callable<WebElement> toRun) {
        try {
            final WebElement action = newSingleThreadExecutor().submit(toRun).get();
            if (action != null) return action;
        } catch (NoSuchElementException | InterruptedException | ExecutionException | StaleElementReferenceException ignored) {
            WaitUtils.waitForNSeconds(1);
        }
        return null;
    }
}
