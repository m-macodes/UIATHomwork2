package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YandexSearchPage {

    private final WebDriver webDriver;
    private final WebElement yandexMarketButton;

    public YandexSearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.yandexMarketButton = webDriver.findElement(By.xpath("//*[contains(@data-id, 'market')]"));
    }

    public void goYandexMarketPage() {
        yandexMarketButton.click();
        webDriver.getWindowHandles().forEach(p -> webDriver.switchTo().window(p));
    }
}
