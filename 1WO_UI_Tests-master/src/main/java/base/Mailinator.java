package base;

import base.enums.PageURLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import utils.WaitUtils;

public class Mailinator extends BaseComponent {
    private static final Logger logger = LoggerFactory.getLogger(Mailinator.class);

    WaitUtils waitUtils;

    private final String mailSubject = ".//td[contains(text(), '%s')]";
    private final String bodyFrameId = "html_msg_body";

    @FindBy(xpath = ".//input[@id= 'search']")
    private WebElement mailSearchInput;

    @FindBy(xpath = ".//input[@id='search-mobile']")
    private WebElement mailSearchInputMobile;

    @FindBy(xpath = ".//table[@class='mainbox']//span")
    private WebElement welcomeName;

    @FindBy(xpath = ".//button[@aria-label='Search for inbox']")
    private WebElement mailSearchButtonMobile;

    @FindBy(xpath = ".//button[text()= 'GO']")
    private WebElement mailSearchButton;

    @FindBy(xpath = ".//h4[contains(text(), 'Public Messages')]")
    private WebElement publicMessagesText;

    @FindBy(xpath = ".//span[contains(text(), 'Username')]//span[2]")
    private WebElement mailResetPassword;

    @FindBy(xpath = ".//a[@class='btniphone']")
    private WebElement mailButton;

    String welcomeSubject = "Welcome to 1World Online";
    String resetPasswordSubject = "Password reset notification";

    public Mailinator(WebDriver driver) {
        super(driver);
        waitUtils = new WaitUtils(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return mailSearchInput;
    }

    @BeforeMethod
    public Mailinator openMailinator() {
        driver.get(PageURLs.MAILINATOR.toString());
        return this;
    }

    public WebElement searchMail() {
        waitUtils.waitForLoading();
        if (waitUtils.isElementVisibleAfterMiddleWait(mailSearchInput)) {
            return mailSearchInput;
        }
        return mailSearchInputMobile;
    }

    public WebElement searchMailButton() {
        waitUtils.waitForLoading();
        if (waitUtils.isElementVisibleAfterMiddleWait(mailSearchButton)) {
            return mailSearchButton;
        }
        return mailSearchButtonMobile;
    }

    public WebElement openMailAccount(String testEMail) {
        driver.get(PageURLs.MAILINATOR.toString());
        searchMail().sendKeys(testEMail);
        searchMailButton().click();
        waitUtils.waitVisibilityOfElementLong(publicMessagesText);
        return waitUtils.getElementWhenVisibleAfterShortWait(By.xpath(mailSubject));
    }

    public boolean isMalListOpened() {
        if (!waitUtils.isElementVisibleAfterMiddleWait(publicMessagesText)) {
            waitUtils.waitVisibilityOfElementShort(publicMessagesText);
        }
        return false;
    }

    public String getMailButtonText(String testEMail, String subject) {
        String mailsSubject = String.format(mailSubject, subject);

        openMailAccount(testEMail);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(mailsSubject));
        driver.switchTo().frame(bodyFrameId);
        return waitUtils.getElementWhenVisibleAfterShortWait(mailButton).getText();
    }

    public String getMailWelcomeText(String testEMail) {
        String mailsSubject = String.format(mailSubject, welcomeSubject);

        openMailAccount(testEMail);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(mailsSubject));
        driver.switchTo().frame(bodyFrameId);
        return waitUtils.getElementWhenVisibleAfterShortWait(welcomeName).getText();
    }

    public String getPasswordFromEmail(String testEmail) {
        String mailsSubject = String.format(mailSubject, resetPasswordSubject);

        openMailAccount(testEmail);
        waitUtils.clickWhenReadyAfterShortWait(By.xpath(mailsSubject));
        driver.switchTo().frame(bodyFrameId);
        return waitUtils.getElementWhenVisibleAfterShortWait(mailResetPassword).getText();
    }
}
