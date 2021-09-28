package icoPages;

import base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

@Getter
@Setter
public class DashboardPage extends BaseComponent {

    WaitUtils waitUtils;

    @FindBy(xpath = ".//span[contains(@class, 'asterix')]/preceding-sibling::span")
    private WebElement totalPointScore;

    public DashboardPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }

    public String getTotalPointsScore() {
        waitUtils.waitForLoading();

        return waitUtils.getElementWhenVisibleAfterShortWait(totalPointScore).getText();
    }
}
