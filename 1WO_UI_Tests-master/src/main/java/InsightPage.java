import base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.widgets.BetaPollerWidgetPreviewPage;
import utils.WaitUtils;

@Getter
@Setter
public class InsightPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(BetaPollerWidgetPreviewPage.class);

    WaitUtils waitUtils;

    @FindBy(xpath = ".//div[contains(@class,'footerStatistic')]")
    private WebElement statisticButton;

    private final String answerPersent = ".//span[contains(text(), '%s')]/../following-sibling::span[@class= 'total-percent']";

    public InsightPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);

    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    public boolean isPercentDisplayed(String answer) {
        return waitUtils.isElementVisibleAfterMiddleWait(By.xpath(answerPersent));
    }
}
