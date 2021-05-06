package utils.conditions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import utils.WaitUtils;

import javax.annotation.Nullable;
import java.util.List;

public class WOExpectedConditions {


    public static ExpectedCondition<WebElement> visibilityOf(final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    return element.isDisplayed() ? element : null;
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return null;
                }
            }

            public String toString() {
                return "visibility of element " + element;
            }
        };
    }

    /**
     * Checks if element is not visible. Element considered not visible if during check NoSuchElementException or
     * StaleElementReferenceException happened.
     */
    public static ExpectedCondition<Boolean> invisibilityOf(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    return !element.isDisplayed();
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return true;
                }
            }

            public String toString() {
                return "invisibility of " + element;
            }
        };
    }

    /**
     * Check if elements count equals expected count.
     */
    public static ExpectedCondition<Boolean> numberOfElementsEquals(final By locator, int elementsCount) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    List<WebElement> list = driver.findElements(locator);
                    return !list.isEmpty() && list.size() == elementsCount;
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }

            public String toString() {
                return "Number of elements located by " + locator;
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOf(final By locator) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    List<WebElement> list = driver.findElements(locator);
                    return !list.isEmpty() && list.get(0).isDisplayed();
                } catch (StaleElementReferenceException e) {
                    return true;
                }
            }

            public String toString() {
                return "invisibility of " + locator;
            }
        };
    }

    public static ExpectedCondition<Boolean> visibilityOf(final By locator) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    List<WebElement> list = driver.findElements(locator);
                    return !list.isEmpty() && list.get(0).isDisplayed();
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            }

            public String toString() {
                return "visibility of element " + locator;
            }
        };
    }

    public static ExpectedCondition<Boolean> visibilityOfAfterPageRefresh(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                WaitUtils waitUtils = new WaitUtils(driver);
                try {
                    driver.navigate().refresh();
                    // For elements that annotated with @FindBy it works correctly
                    // For elements that found by driver.findElement() it will throw exception
                    return waitUtils.isElementVisibleAfterShortWait(element);
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            }

            public String toString() {
                return "visibility of element " + element + " after page refresh";
            }
        };
    }

    public static ExpectedCondition<Boolean> visibilityOfAfterPageRefresh(final By byElement) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    driver.navigate().refresh();
                    return driver.findElement(byElement).isDisplayed();
                } catch (NoSuchElementException | StaleElementReferenceException e1) {
                    return false;
                }
            }

            public String toString() {
                return "visibility of element " + byElement + " after page refresh";
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> visibilityOfAllElementsLocated(final By locator) {
        return new ExpectedCondition<List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebDriver driver) {
                try {
                    List<WebElement> elements = driver.findElements(locator);
                    for (WebElement element : elements) {
                        if (!element.isDisplayed()) {
                            return null;
                        }
                    }
                    return elements.size() > 0 ? elements : null;
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "visibility of all elements located by " + locator;
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> visibilityOfAllElements(final List<WebElement> elements) {
        return new ExpectedCondition<List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebDriver driver) {
                try {
                    for (WebElement element : elements) {
                        if (!element.isDisplayed()) {
                            return null;
                        }
                    }
                    return elements.size() > 0 ? elements : null;
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "visibility of all " + elements;
            }
        };
    }

    public static ExpectedCondition<Boolean> visibilityOfElementLocatedAfterPageRefresh(final By locator) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                driver.navigate().refresh();
                return driver.findElement(locator).isDisplayed();
            }
        };
    }

    public static ExpectedCondition<Boolean> elementSelectionStateToBeAfterPageRefresh(final By locator,
                                                                                       final boolean state) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                driver.navigate().refresh();
                return driver.findElement(locator).isSelected() == state;
            }
        };
    }

    public static ExpectedCondition<WebElement> presenceOfElement(final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    // just to check if could interact with element, if yes - it is present
                    element.isDisplayed();
                    return element;
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return null;
                }
            }

            public String toString() {
                return "presence of element " + element;
            }
        };
    }

    public static ExpectedCondition<WebElement> presenceOfElementLocated(final By locator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    // just to check if could interact with element, if yes - it is present
                    WebElement element = driver.findElement(locator);
                    element.isDisplayed();
                    return element;
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return null;
                }
            }

            public String toString() {
                return "presence of element located by " + locator;
            }
        };
    }

//    public static ExpectedCondition<Boolean> visibilityOfElementLocatedAfterDeletingCookiesAndPageRefresh(final By locator, WebDriver driver) {
//        return new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver driver) {
//                OneWOWebDriver webDriver = (OneWOWebDriver) driver;
//                webDriver.manage().deleteAllCookies();
//                webDriver.navigate().refresh();
//                for (int i = 0; i <= 10; i++) {
//                    try {
//                        if (!webDriver.findElement(locator).isDisplayed()) {
//                            webDriver.waitUtils.waitMilliseconds(200);
//                        } else {
//                            return true;
//                        }
//                    } catch (NoSuchElementException | StaleElementReferenceException e) {
//                        webDriver.waitUtils.waitMilliseconds(500);
//                    }
//                }
//                return false;
//            }
//
//            public String toString() {
//                return "visibility of element located by " + locator + " after deleting cookies and page refresh";
//            }
//        };
//    }

    public static ExpectedCondition<Boolean> invisibilityOfAfterPageRefresh(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                WaitUtils waitUtils = new WaitUtils(driver);
                try {
                    waitUtils.waitVisibilityOfElementShort(element);
                    driver.navigate().refresh();
                    return !waitUtils.isElementVisibleAfterShortWait(element);
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    return false;
                }
            }

            public String toString() {
                return "invisibility of " + element + " after page refresh";
            }
        };
    }

    public static ExpectedCondition<WebElement> elementToBeClickable(final WebElement element) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    if (element.isDisplayed() && element.isEnabled()) {
                        return element;
                    }
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    // means element is not visible as a result it is not enabled
                }
                return null;
            }

            public String toString() {
                return "element to be enabled " + element;
            }
        };
    }

    public static ExpectedCondition<WebElement> elementToBeClickable(final By locator) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    WebElement element = driver.findElement(locator);
                    if (element.isDisplayed() && element.isEnabled()) {
                        return element;
                    }
                } catch (NoSuchElementException | StaleElementReferenceException e) {
                    // means element is not visible as a result it is not enabled
                }
                return null;
            }

            public String toString() {
                return "element " + locator + " to be clickable";
            }
        };
    }

    public static ExpectedCondition<Boolean> isJavascriptTrue(String js) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript(js);
            }

            @Override
            public String toString() {
                return "javascript " + js + " to be true";
            }
        };
    }

    public static ExpectedCondition<Boolean> isElementTextMatches(WebElement element, String matcherString) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return element.isDisplayed() && element.getText().matches(matcherString);
            }

            @Override
            public String toString() {
                return "element's " + element + " text to match text " + matcherString;
            }
        };
    }

    public static ExpectedCondition<Boolean> isElementTextNotMatches(WebElement element, String matcherString) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return element.isDisplayed() && !element.getText().matches(matcherString);
            }

            @Override
            public String toString() {
                return "element's " + element + " text to not match text " + matcherString;
            }
        };
    }

    public static ExpectedCondition<Boolean> isElementTextNotEquals(WebElement element, String text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return element.isDisplayed() && !element.getText()
                        .equals(text);
            }

            @Override
            public String toString() {
                return String.format("element's %s text not equals text %s", element, text);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeToBeNotEmpty(WebElement element, String attribute) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                boolean hasText = false;
                try {
                    currentValue = element.getAttribute(attribute);
                    if (currentValue == null || currentValue.isEmpty()) {
                        currentValue = element.getCssValue(attribute);
                    }
                    hasText = !currentValue.isEmpty();
                } catch (Exception e) {
                    // ignore
                }
                return hasText;
            }

            @Override
            public String toString() {
                return String.format("attribute %s of element %s is not empty or null", attribute, element);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeToBeNotEmpty(By locator, String attribute) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                boolean hasText = false;
                try {
                    WebElement element = driver.findElement(locator);
                    currentValue = element.getAttribute(attribute);
                    if (currentValue == null || currentValue.isEmpty()) {
                        currentValue = element.getCssValue(attribute);
                    }
                    hasText = !currentValue.isEmpty();
                } catch (Exception e) {
                    // ignore
                }
                return hasText;
            }

            @Override
            public String toString() {
                return String.format("attribute %s of element %s is not empty or null", attribute, locator);
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> presenceOfElements(final List<WebElement> elements,
                                                                         final int countOfElements) {
        return new ExpectedCondition<List<WebElement>>() {
            @Nullable
            @Override
            public List<WebElement> apply(@Nullable WebDriver driver) {
                try {
                    for (WebElement element : elements) {
                        if (!isElementExists(element)) {
                            return null;
                        }
                    }
                    return elements.size() >= countOfElements ? elements : null;
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "Presence of all WebElements";
            }
        };
    }

    public static boolean isElementExists(WebDriver driver, By by) {
        return !driver.findElements(by).isEmpty();
    }

    public static boolean isElementExists(WebElement element) {
        try {
            if (element == null)
                return false;
            if (element.getTagName().contains("Exception"))
                return false;
        } catch (java.util.NoSuchElementException ex) {
            return false;
        }
        return true;
    }

    public static ExpectedCondition<Boolean> isSuccessfullySwitchedToIFrame(String iFrameID,
                                                                            WebElement elementInFrame) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                driver.switchTo().frame(iFrameID);
                if (elementInFrame.isDisplayed()) {
                    return true;
                } else {
                    driver.switchTo().defaultContent();
                    return false;
                }
            }

            public String toString() {
                return String.format("Wasn't successfully switched to following iFrame %s", elementInFrame);
            }
        };
    }

    public static ExpectedCondition<Boolean> isSuccessfullySwitchedToIFrame(String iFrameID, By elementInFrame) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                driver.switchTo().frame(iFrameID);
                if (driver.findElements(elementInFrame).size() > 0) {
                    return true;
                } else {
                    driver.switchTo().defaultContent();
                    return false;
                }
            }

            public String toString() {
                return String.format("Wasn't successfully switched to following iFrame %s", elementInFrame);
            }
        };
    }
}
