package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GlobalAbstractTestBase {
    private static final Logger logger = LoggerFactory.getLogger(GlobalAbstractTestBase.class);

//    public static void catchFailedScreenshot(WebDriver driver, Throwable throwable) {
//        String message = throwable.getMessage();
//        message = (message == null) ? "null" : message;
//
//        // usually the first line is informative enough
//        if (message.contains("\n")) {
//            message = message.substring(0, message.indexOf("\n"));
//        }
//        TestUtils.takeScreenshot(driver, message);
//    }
}
