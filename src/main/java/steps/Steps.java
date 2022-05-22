package steps;

import custom.driver.Manager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import pages.*;

public class Steps {

    @Step("Переходим на страницу {url}")
    public static void goPage(String url) {
        Manager.getWebDriver().get(url);
    }

    @Step("Переходим на сайт яндекс маркета")
    public static void goYandexMarketPage() {
        YandexSearchPage yandexSearchPage = new YandexSearchPage(Manager.getWebDriver());
        yandexSearchPage.goYandexMarketPage();
    }

    @Step("Выбираем категорию и подкатегорию")
    public static void goByCategory(String category, String subcategory) {
        YandexMarketHomePage yandexMarketHomePage = new YandexMarketHomePage();
        yandexMarketHomePage.goByCategory(category, subcategory);
    }

    @Step("Задаем параметры поиска")
    public static void setSearchParameters(String priceMin, String priceMax, String... manufacturers) {
        YandexMarketSearchPage yandexMarketSearchPage = YandexMarketSearchPage.getInstance();
        yandexMarketSearchPage.setSearchParameters(priceMin, priceMax, manufacturers);
    }

    @Step("Проверяем, что на странице, после нажатия на кнопку показать еще, {number} элемента/ов")
    public static void assertElementCount(int number) {
        YandexMarketSearchPage yandexMarketSearchPage = YandexMarketSearchPage.getInstance();
        yandexMarketSearchPage.clickShowMoreButton();
        Assertions.assertEquals(number, yandexMarketSearchPage.getCountSearchResult(), "Колличество результатов поиска не равно 96");
    }

    @Step("Ищем в поиске наименования первого элемента результата поиска по фильтрам")
    public static void searchMarket(String text) {
        YandexMarketSearchPage yandexMarketSearchPage = YandexMarketSearchPage.getInstance();
        yandexMarketSearchPage.searchMarket(text);
    }

    @Step("Проверяем наличие искомого элемента в результатах поиска")
    public static void assertSearchResult(String text) {
        YandexMarketSearchPage yandexMarketSearchPage = YandexMarketSearchPage.getInstance();
        Assertions.assertTrue(yandexMarketSearchPage.checkSearchResult(text), "В результатах поиска отсутствует искомый элемент");
    }
}
