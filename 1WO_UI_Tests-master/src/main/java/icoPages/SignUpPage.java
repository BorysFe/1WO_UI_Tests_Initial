package icoPages;

import base.BaseComponent;
import base.enums.GeneralLocators;
import base.enums.PageURLs;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portalPages.polls.widgets.pollerWidgetsPages.PollerWidgetsPage;
import utils.WaitUtils;

@Getter
@Setter
public class SignUpPage extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(SignUpPage.class);

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
                format(GeneralLocators.A_BY_CONTAINS_CLASS.toString(), "signup")));
    }

    public SignUpPage openSignUpPage() {

        driver.get(PageURLs.ICO_SIGNUP_PAGE.toString());
        waitUtils.waitForLoading();

        return this;
    }

    public SignUpPage fillSignUpForm(String firstName,
                                     String lastName,
                                     String password,
                                     String emailAddress) {

        waitUtils.waitForLoading();

        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "First name")), firstName);
        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "Last name")), lastName);
        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "Password")), password);
        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "Confirm password")), password);
        setField(By.xpath(String.format(GeneralLocators.INPUT_BY_PLACEHOLDER.toString(), "Email address")), emailAddress);
        waitUtils.waitForLoading();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.
                format(GeneralLocators.BUTTON_BY_CONTAINS_CLASS.toString(), "btn-sbmt")));
        waitUtils.waitForLoading();

        logger.info("First/Last/Password/Login - " + firstName + "/" + lastName + "/" + password + "/" + emailAddress);
        return this;
    }

    public ProfilePage openProfilePage() {
        waitUtils.waitForLoading();

        waitUtils.clickWhenReadyAfterMiddleWait(By.xpath(String.format(GeneralLocators.A_BY_CONTAINS_TEXT.toString(), "Profile")));

        return profilePage;
    }
}
