package portalPages;

import base.BaseComponent;
import base.enums.Accounts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.awt.*;

public class ResetPassword extends BaseComponent {

    WaitUtils waitUtils;

    @FindBy(xpath = ".//div[@id='restore-pass-btn']")
    private WebElement forgotPasswordButton;

    @FindBy(xpath = ".//input[@id='fp-email']")
    private WebElement resetPasswordEmailField;

    @FindBy(xpath = ".//input[@value='submit']")
    private WebElement resetPasswordSubmit;

    @FindBy(xpath = ".//p[@id='pswdHBsent']")
    private WebElement resetPasswordSuccessMessage;

    @FindBy(xpath = ".//input[@id='rp-pswd']")
    private WebElement newPassword;

    @FindBy(xpath = ".//input[@id='rp-re_pswd']")
    private WebElement retypeNewPassword;

    public ResetPassword(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return resetPasswordEmailField;
    }

    void setField(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    void setField(By element, String text) {
        waitUtils.waitVisibilityOfElementByShort(element);
        driver.findElement(element).clear();
        waitUtils.waitVisibilityOfElementByShort(element);
        driver.findElement(element).sendKeys(text);
    }

    public void resetPassword(String accountEmail) {
        waitUtils.clickWhenReadyAfterShortWait(forgotPasswordButton);
        waitUtils.waitForLoading();
        setField(resetPasswordEmailField, accountEmail);
        waitUtils.clickWhenReadyAfterShortWait(resetPasswordSubmit);
        waitUtils.waitForLoading();
    }

    public boolean isResetPasswordMailSent() {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleNow(resetPasswordSuccessMessage);
    }

    public void enterNewPassword(String newMemberPassword) {
        waitUtils.waitForLoading();
        waitUtils.waitVisibilityOfElementLong(newPassword);
        setField(newPassword, newMemberPassword);
        waitUtils.waitVisibilityOfElementLong(retypeNewPassword);
        setField(retypeNewPassword, newMemberPassword);
        waitUtils.clickWhenReadyAfterShortWait(resetPasswordSubmit);
        waitUtils.waitForLoading();;
    }
}
