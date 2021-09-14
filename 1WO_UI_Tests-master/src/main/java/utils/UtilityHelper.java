package utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilityHelper {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UtilityHelper.class);

    public static boolean isUsable(String value) {

        return (value != null && value.length() > 0);

    }

    public static void acceptAlert(WebDriver driver) {
        try {
            waitForAlert(driver, 5);
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            driver.switchTo().activeElement();

            // already waited for 5 seconds, no need to wait in tryApplicationConfirm()
            if (tryApplicationConfirm(driver, By.id("applicationConfirmModalConfirmBtn"))) {
                return;
            }
            if (tryApplicationConfirm(driver, By.id("applicationAlertModalOkBtn"))) {
                return;
            }
            throw e;
        }
    }

    private static boolean tryApplicationConfirm(WebDriver driver, By byId) {
        WOWebDriver webDriver = (WOWebDriver) driver;
        try {
            webDriver.waitUtils.clickWhenReady(byId, 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void acceptAlterIfPresent(WebDriver driver) {
        if (isAlertPresent(driver)) {
            acceptAlert(driver);
        }
    }

    public static void waitForAlert(WebDriver driver, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void dismissAlert(WebDriver driver) {
        waitForAlert(driver, 5);
        driver.switchTo().alert().dismiss();
    }

    public static void refreshThePage(WebDriver driver) {
        WOWebDriver webDriver = (WOWebDriver) driver;
        webDriver.navigate().refresh();
        webDriver.waitUtils.waitMilliseconds(500);
    }

    public static String getEmailDomain(String email) {
        return email.split("@")[1];
    }

    public static boolean isAlertPresent(WebDriver driver, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isDialogPresent(WebDriver driver) {
        Alert alert = null;
        try {
            alert = ExpectedConditions.alertIsPresent().apply(driver);
        } catch (NoSuchWindowException e) {
            logger.info("Alert is not shown");
        }
        return (alert != null);
    }

    public static boolean handleAlert(WebDriver driver) {
        if (isDialogPresent(driver)) {
            acceptAlert(driver);
            return true;
        }
        return false;
    }

    /**
     * Wait up to 5 seconds for alert as it is not displayed immediately.
     */
    public static boolean isAlertPresent(WebDriver driver) {
        return isAlertPresent(driver, 2);
    }

    public static String generateRandomFilename(String input) {
        String filename = input;
        filename = filename.replaceAll("\\s", "_").replaceAll(":", "").replaceAll("\\\\", "-") + ".png";
        filename = "" + System.currentTimeMillis() + "-" + filename;
        return filename;
    }

    public static void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
    }

    public static String removeNonDigits(String queryString) {
        return queryString.replaceAll("\\D+", "");
    }

    public static String removeDigits(String queryString) {
        return queryString.replaceAll("\\P{L}", "");
    }

    public static List<String> getAllRegexMatches(String data, String pattern) {
        List<String> matches = new ArrayList<>();

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(data);

        while (m.find()) {
            matches.add(m.group());
        }
        return matches;
    }

}
