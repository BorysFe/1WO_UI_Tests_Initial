package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtils {
    private static final Logger logger = LoggerFactory.getLogger(TestUtils.class);

    private final static long MILLI_IN_HOUR = 1000L * 60L * 60L;
    private final static long MILLI_IN_MINUTE = 1000L * 60L;

    private String getTag(String content, String tag) {
        Pattern pattern = Pattern.compile(tag + " = \"(.*?)\"");

        Matcher patternMatcher = pattern.matcher(content);
        if (patternMatcher.find()) {
            return patternMatcher.group(1);
        } else {
            String tagID = content.split(tag + ":")[1].split(",\n")[0]
                    .trim().replace("'", "");
            return tagID;
        }
    }

    public static boolean isElementExists(WebDriver driver, By by) {
        return !driver.findElements(by).isEmpty();
    }

    public static void dropDownSelect(WebDriver driver, By by, String elementSelect) {
        Select droplist = new Select(driver.findElement(by));
        droplist.selectByVisibleText(elementSelect);
    }
}
