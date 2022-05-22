package ru.bellintegrator;

import custom.properties.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.YandexMarketSearchPage;
import steps.Steps;


public class Tests extends BaseTest {

    @DisplayName("Проверка яндекс маркета")
    @ParameterizedTest(name = "{displayName} : {arguments}")
    @MethodSource("helpers.DataProvider#searchParam")
    public void yandexMarketTest(String category, String subcategory, String minPrice, String maxPrice, String[] manufacturers) {
        Steps.goPage(TestData.propsURL.baseURLYandex());
        Steps.goYandexMarketPage();
        Steps.goByCategory(category, subcategory);
        Steps.setSearchParameters(minPrice, maxPrice, manufacturers);
        Steps.assertElementCount(96);
        YandexMarketSearchPage yandexMarket = YandexMarketSearchPage.getInstance();
        String firstElement = yandexMarket.getSearchResultsText(0);
        Steps.searchMarket(firstElement);
        Steps.assertSearchResult(firstElement);
    }
}
