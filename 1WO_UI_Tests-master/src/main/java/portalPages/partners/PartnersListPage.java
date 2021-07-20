package portalPages.partners;

import base.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

public class PartnersListPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(PartnersListPage.class);

    WaitUtils waitUtils;

    public PartnersListPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @FindBy(xpath = ".//input[@class='js-keywords-search-filter']")
    private WebElement searchPartnerField;

    @Override
    protected WebElement getMainElementInComponent() {
        return searchPartnerField;
    }
}
