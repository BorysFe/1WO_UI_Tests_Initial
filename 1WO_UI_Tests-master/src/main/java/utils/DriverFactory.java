package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

public class DriverFactory {

    public WebDriver driver;

    @BeforeClass
    public void getDriver() {
        System.setProperty("webdriver.chrome.driver", "D://1WO_UI//chromedriver.exe");
        driver = new ChromeDriver();
    }
}
