package base;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import portalPages.FeedPage;

public abstract class GlobalTestBase extends GlobalAbstractTestBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalTestBase.class);

    public WebDriver driver;
    public WebDriver driver2;
    protected FeedPage feedPage;

}
