package portalPages.polls.polls;

import base.BaseComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class NewPollSecondPage extends BaseComponent {

    WaitUtils waitUtils;

    public NewPollSecondPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }
}
