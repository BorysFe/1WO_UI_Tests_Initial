package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class DriverFactory {

    //        private static WebDriver driver = null;
//
//    private static SessionId session = null;
    public WebDriver driver;

    @BeforeClass
    public void getDriver() {
        System.setProperty("webdriver.chrome.driver", "D://1WO_UI//chromedriver.exe");
        driver = new ChromeDriver();
        SessionId session = ((ChromeDriver) driver).getSessionId();
//        driver.get("https://frontend-qa.1worldonline.biz/#!/feed");
    }

//    @BeforeMethod
//    public void openFeedPage() {
//        driver.get("https://frontend-qa.1worldonline.biz/#!/feed");
//    }

//    @AfterMethod
//    public void closeDriver() {
//        driver.close();
//        driver.quit();
//    }
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
