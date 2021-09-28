package icoPages;

import base.BaseComponent;
import base.enums.GeneralLocators;
import base.enums.PageURLs;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

@Getter
@Setter
public class SignUpPage extends BaseComponent {

    WaitUtils waitUtils;
    ProfilePage profilePage;

    public SignUpPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
        profilePage = new ProfilePage(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return waitUtils.waitForElementToBeDisplayedAfterLongWait(By.xpath(String.
                format(GeneralLocators.A_BY_CLASS.toString(), "signup")));
    }

    public SignUpPage openSignUpPage() {

        driver.get(PageURLs.ICO_SIGNUP_PAGE.toString());
        waitUtils.waitForLoading();

        return this;
    }

    public void fillSignUpForm(String firstName,
                               String lastName,
                               String password,
                               String emailAddress) {

        waitUtils.waitForLoading();

        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "First name")), firstName);
        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "Last name")), lastName);
        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "Password")), password);
        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "Confirm password")), password);
        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "Email address")), emailAddress);

        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.
                format(GeneralLocators.BUTTON_BY_CLASS.toString(), "btn-sbmt")));
    }

    public ProfilePage getProfilePage() {
        waitUtils.clickWhenReadyAfterMiddleWait(By.xpath(String.format(GeneralLocators.A_BY_TEXT.toString(), "Profile")));

        return profilePage;
    }
}
