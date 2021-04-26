package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

//        private static WebDriver driver = null;
//
//    private static SessionId session = null;
    public WebDriver driver;

    @BeforeSuite
    public void getDriver() {
        System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://frontend-qa.1worldonline.biz/#!/feed");
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
//    public WebDriver getDriver() {

//    public static WebDriver getDriver(String name) {
//
//        if (driver != null) {
//            if ("FIREFOX".equalsIgnoreCase(name)) {
//                session = ((FirefoxDriver) driver).getSessionId();
//
//            } else if ("CHROME".equalsIgnoreCase(name)) {
//                session = ((ChromeDriver) driver).getSessionId();
//
//            } else {
//                session = null;
//            }
//        }
//
//        if (driver == null || session == null) {
//            if ("FIREFOX".equalsIgnoreCase(name)) {
//                System.setProperty("webdriver.gecko.driver", "");
//                driver = new FirefoxDriver();
//            } else if ("CHROME".equalsIgnoreCase(name)) {
//        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
//        driver = new ChromeDriver();
//            } else {
//                System.out.println("We can not instantiate browser");
//            }
//        }
//        if (driver != null) {
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        }
//        return driver;
//    }
}
