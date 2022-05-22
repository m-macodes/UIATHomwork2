package ru.bellintegrator;

import custom.driver.Manager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void before() {
        Manager.initChrome();
        chromeDriver = Manager.getWebDriver();
    }

    @AfterEach
    public void after() {
        Manager.killCurrentDriver();
    }
}
