package pages;

import custom.driver.Manager;
import custom.driver.Waits;
import custom.utils.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class YandexMarketBasePage {

    protected WebDriver webDriver = Manager.getWebDriver();


    /**
     * Переход по котегории и подкатегории
     *
     * @param categoryName    - название категории, на которое мы наводим, для получения списка подкатегорий
     * @param subcategoryName - подкатегория, по которой мы хотим перейти
     */
    public void goByCategory(String categoryName, String subcategoryName) {
        String catalogPopup = "//div[@id='catalogPopup']//ul";
        String xpathCatalog = "//div[contains(@data-apiary-widget-name,'@MarketNode/CmsLayout')]";
        String xpathCategory = "//div[@id='catalogPopup']//li[contains(.,'" + categoryName + "')]";
        String xpathSubcategory = xpathCatalog + "//li[contains(., " + subcategoryName + ")]";
        String xpathCatalogButton = "//button[@id='catalogPopupButton']";

        Waits.waitUntilElementPresents(xpathCatalogButton);
        webDriver.findElement(By.xpath(xpathCatalogButton)).click();
        Waits.waitUntilElementPresents(catalogPopup);
        Actions.hover.accept(By.xpath(xpathCategory));
        WebElement menu = webDriver.findElement(By.xpath(xpathCatalog));
        Waits.waitUntilElementTextContains(menu, categoryName);
        menu.findElement(By.xpath(xpathSubcategory)).click();
    }

    /**
     * Поиск на яндекс маркете
     *
     * @param text - текст, по которому мы хотим произвести поиск
     */
    public void searchMarket(String text) {
        String searchInputXpath = "//input[@placeholder='Искать товары']";
        String searchButtonXpath = "//button[@data-r='search-button']";
        String searchResultXpath = "//div[contains(@data-apiary-widget-id,'SearchSerp')]";

        webDriver.findElement(By.xpath(searchInputXpath)).sendKeys(text);
        webDriver.findElement(By.xpath(searchButtonXpath)).click();
        Waits.waitUntilElementPresents(searchResultXpath);
    }
}
