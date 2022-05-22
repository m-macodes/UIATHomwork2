package custom.driver;

import custom.properties.TestData;
import custom.utils.Actions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Manager {

    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void initChrome() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        try {
            webDriver = new ChromeDriver();
        } catch (SessionNotCreatedException e) {
            Assertions.fail("Данный драйвер не совместим с текущим браузером. Используйте другой драйвер.");
        }
        setDefaultSettings();
        initStaticObjects();
    }

    private static void setDefaultSettings() {
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(TestData.propsDriver.defaultTimeout(), TimeUnit.SECONDS);
        webDriver.manage().deleteAllCookies();
    }

    private static void initStaticObjects() {
        Actions.action = new org.openqa.selenium.interactions.Actions(webDriver);
    }

    public static void killCurrentDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
