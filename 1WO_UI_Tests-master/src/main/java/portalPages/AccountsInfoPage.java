package portalPages;

import base.BaseComponent;
import base.enums.PageQAURLs;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

@Getter
public class AccountsInfoPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(FeedPage.class);

    WaitUtils waitUtils;

    public AccountsInfoPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    public AccountsInfoPage openAccountInfoPage() {
        driver.get(PageQAURLs.QA_ACCOUNT_INFO.toString());

        return this;
    }

    public boolean isUserAnonim() {

        return waitUtils.isElementVisible((WebElement) By.xpath(".//h1[text()= \"Whitelabel Error Page\"]"));
//        return !waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(".//h1")).getText().equals("Whitelabelelabel Error Page");
    }

    public boolean isUserSynthetic() {

        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(".//pre[contains(@style, 'word-wrap:')]")).getText().equals("synthetic");
    }
}
