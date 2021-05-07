package portalPages;

import base.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import portalPages.enums.SignLinks;

@Getter
public class SignIn_SignUp_Popup extends BaseComponent {

    private final String inputElement = ".//input[@id='%s']";
    private final String spanElement = ".//span[@id='%s']";

    @FindBy(xpath = ".//label[@id='login-error']")
    private WebElement errorMessageAuthenticationFailed;

    @FindBy(xpath = ".//div[@id='show-menu-profile']")
    private WebElement menuProfileButton;

    public SignIn_SignUp_Popup(WebDriver driver) {
        super(driver);
    }

    @Override
    protected WebElement getMainElementInComponent() {
        return driver.findElement(By.xpath(String.format(inputElement, SignLinks.SIGN_IN_SIGN_IN_BUTTON)));
    }
}
