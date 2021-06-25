package portalPages;

import base.BaseComponent;
import base.enums.PageQAURLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

import javax.lang.model.element.AnnotationValueVisitor;

public class AccountInfoPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(FeedPage.class);

    WaitUtils waitUtils;

    public AccountInfoPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    public AccountInfoPage openAccountInfoPage() {
        driver.get(PageQAURLs.QA_ACCOUNT_INFO.toString());

        return this;
    }

    public String getRole() {

        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(".//pre[contains(@style, 'word-wrap:')]")).getText().split("]").toString();
    }
}
