package custom.driver;

import custom.properties.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {

    public static WebDriverWait wait = new WebDriverWait(Manager.getWebDriver(), TestData.propsDriver.defaultTimeout());

    /**
     * Ожидание появления элемента в DOM дереве
     * @param xpath - xpath искомого элемента
     */
    public static void waitUntilElementPresents(String xpath) {
        Waits.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Ожидания наличия данного текста в указанном элементе
     * @param element - элемент в котором происходит поиск
     * @param text - искомый текс
     */
    public static void waitUntilElementTextContains(WebElement element, String text) {
        Waits.wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * Ожидания проверки отсутствия элемента в DOM дереве
     * @param xpath - xpath искомого элемента
     */
    public static void waitUntilInvisibilityOfElementLocated(String xpath) {
        Waits.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }
}
