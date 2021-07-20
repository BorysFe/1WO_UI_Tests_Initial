package portalPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {

    WebDriver driver;

    private String url;
    protected FeedPage feedPage;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected void setUrl(String url) {
        logger.info("Set url-" + url);

        this.url = url;
    }

    public String getUrl() {
        logger.info("Return url-" + url);

        return url;
    }
}
