package custom.utils;

import custom.driver.Manager;
import org.openqa.selenium.By;

import java.util.function.Consumer;

public class Actions {

    public static org.openqa.selenium.interactions.Actions action;

    public static Consumer<By> hover = (By by) -> {
        action.moveToElement(Manager.getWebDriver().findElement(by))
                .perform();
    };
}
