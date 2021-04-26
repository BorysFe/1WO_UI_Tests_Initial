package portalPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import portalPages.enums.SignInUpLinks;
import utils.WaitUtils;

public class FeedPage extends BasePage {

    WaitUtils waitUtils;

    private final String inputElement = ".//input[@id='%s']";

    private final String spanElement = ".//span[@id='%s']";

    @FindBy(xpath = ".//label[@id='login-error']")
    private WebElement errorMessageAuthenticationFailed;

    @FindBy(xpath = ".//div[@id='show-menu-profile']")
    private WebElement menuProfileButton;

    public FeedPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    public void openLoginWindow() {
        waitUtils.waitForLoading();
        waitUtils.waitVisibilityOfElementShort(driver.findElement(By.xpath(String.format(spanElement, SignInUpLinks.SIGN_IN_SIGN_UP_BUTTON))));
        driver.findElement(By.xpath(String.format(spanElement, SignInUpLinks.SIGN_IN_SIGN_UP_BUTTON))).click();
        waitUtils.waitForLoading();
    }

    //
//    public void logInMember(String logIn, String password) {
//        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_LOGIN_FIELD)));
//        getDriver("chrome").findElement(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_LOGIN_FIELD))).sendKeys(logIn);
//        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_PASSWORD_FIELD)));
//        getDriver("chrome").findElement(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_PASSWORD_FIELD))).sendKeys(password);
//        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_SIGN_IN_BUTTON)));
//    }
//
//    public void registrationMember(String newLogIn, String newPassword) {
//        openLoginWindow();
//        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_CREATE_ACCOUNT_BUTTON)));
//        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_LOGIN_FIELD)));
//        waitUtils.waitPresenceOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_EMAIL_FIELD)));
//        getDriver("chrome").findElement(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_EMAIL_FIELD))).sendKeys(newLogIn);
//        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_PASSWORD_FIELD)));
//        getDriver("chrome").findElement(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_PASSWORD_FIELD))).sendKeys(newPassword);
//        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_SUBMIT_BUTTON)));
//    }
    public void logInMember(String logIn, String password) {
        openLoginWindow();
        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_LOGIN_FIELD)));
        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_LOGIN_FIELD))).sendKeys(logIn);
        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_PASSWORD_FIELD)));
        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_PASSWORD_FIELD))).sendKeys(password);
//        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_SIGN_IN_BUTTON)));
//        waitUtils.waitElementToBeClickableShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_SIGN_IN_BUTTON)));
//        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_SIGN_IN_BUTTON))).click();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_SIGN_IN_BUTTON)));
    }

    public boolean isAuthenticationErrorDisplayed() {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleAfterMiddleWait(errorMessageAuthenticationFailed);
    }

    public void registrationMember(String newLogIn, String newPassword) {
        openLoginWindow();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(spanElement, SignInUpLinks.SIGN_IN_CREATE_ACCOUNT_BUTTON)));
        waitUtils.waitForLoading();
        waitUtils.waitPresenceOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_EMAIL_FIELD)));
        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_EMAIL_FIELD))).sendKeys(newLogIn);
        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_PASSWORD_FIELD)));
        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_PASSWORD_FIELD))).sendKeys(newPassword);
        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_RESTORE_PASSWORD_FIELD)));
        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_RESTORE_PASSWORD_FIELD))).sendKeys(newPassword);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_SUBMIT_BUTTON)));
        waitUtils.waitForLoading();
    }

    public boolean isMemberAuthorised() {
        waitUtils.waitForLoading();
        return waitUtils.isElementVisibleAfterMiddleWait(menuProfileButton);
    }
}
