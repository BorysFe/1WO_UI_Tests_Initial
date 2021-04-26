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

    @FindBy(xpath = ".//span[@id='login']")
    private WebElement signInRegButton;

    public FeedPage(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    public void openLoginWindow() {
        waitUtils.waitVisibilityOfElementShort(signInRegButton);
        signInRegButton.click();
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
        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_LOGIN_FIELD)));
        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_LOGIN_FIELD))).sendKeys(logIn);
        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_PASSWORD_FIELD)));
        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_PASSWORD_FIELD))).sendKeys(password);
        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_SIGN_IN_BUTTON)));
    }

    public void registrationMember(String newLogIn, String newPassword) {
        openLoginWindow();
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_CREATE_ACCOUNT_BUTTON)));
        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.SIGN_IN_LOGIN_FIELD)));
        waitUtils.waitPresenceOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_EMAIL_FIELD)));
        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_EMAIL_FIELD))).sendKeys(newLogIn);
        waitUtils.waitVisibilityOfElementByShort(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_PASSWORD_FIELD)));
        driver.findElement(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_PASSWORD_FIELD))).sendKeys(newPassword);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(String.format(inputElement, SignInUpLinks.REGISTRATION_SUBMIT_BUTTON)));
    }
}
