package portalPages.publisher.polls;

import base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

@Getter
@Setter
public class NewPollFirstPage extends BaseComponent {

    WaitUtils waitUtils;

    public NewPollFirstPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return null;
    }


}
