package base;

import base.enums.PageURLs;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.FeedPage;
import utils.WaitUtils;

@Setter
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
        driver.get(PageURLs.ACCOUNT_INFO.toString());

        return this;
    }

    public boolean isUserAnonim() {

        return waitUtils.isElementVisibleAfterMiddleWait(By.xpath(".//h1[text()= \"Whitelabel Error Page\"]"));
//        return !waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(".//h1")).getText().equals("Whitelabelelabel Error Page");
    }

    public boolean isUserSynthetic() {

        return waitUtils.isElementVisibleAfterMiddleWait(By.xpath(".//pre[contains(text(), 'synthetic')]"));
    }

    public boolean isUserMember() {

        return waitUtils.isElementVisibleAfterLongWait(By.xpath(".//pre[contains(text(), 'member')]"));
    }
}
