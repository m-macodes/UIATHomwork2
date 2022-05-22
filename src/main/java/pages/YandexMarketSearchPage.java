package pages;

import custom.driver.Waits;
import custom.utils.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class YandexMarketSearchPage extends YandexMarketBasePage {

    /**
     * Поле для ввода минимальной цены товара
     */
    private WebElement priceMin;
    /**
     * Поле для максимальной цены товара
     */
    private WebElement priceMax;
    /**
     * Кнопка для отображения списка всех производителей
     */
    private WebElement allManufacturersButton;

    /**
     * xpath для серого экрана, который появляется при загрузке товаров
     */
    private final String greyScreen = "//*[@aria-label='Загрузка...']";
    /**
     * xpath конца страницы
     */
    private final String endPage = "//div[@data-zone-name='SearchPager']";

    private List<WebElement> searchResults;
    private static volatile YandexMarketSearchPage instance;

    private YandexMarketSearchPage() {
    }

    /**
     * Singleton создание обьекта
     *
     * @return - возвращает экземпляр класса (если нет экземпляра, то создает новый)
     */
    public static YandexMarketSearchPage getInstance() {
        YandexMarketSearchPage localInstance = instance;
        if (localInstance == null) {
            synchronized (YandexMarketSearchPage.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new YandexMarketSearchPage();
                }
            }
        }
        return localInstance;
    }

    /**
     * Задает параметры фильтрации
     *
     * @param priceMin      - минимальная стоимость товара
     * @param priceMax      - максимальная стоимость товара
     * @param manufacturers - список производителей, товары которых мы хотим получить
     */
    public void setSearchParameters(String priceMin, String priceMax, String... manufacturers) {
        initSearchFilterElement();
        this.priceMin.sendKeys(priceMin);
        this.priceMax.sendKeys(priceMax);
        Waits.waitUntilInvisibilityOfElementLocated(greyScreen);

        allManufacturersButton.click();
        for (String manufacturer : manufacturers) {
            webDriver.findElement(By.xpath("//label[contains(., '" + manufacturer + "')]")).click();
        }
        Waits.waitUntilInvisibilityOfElementLocated(greyScreen);
    }

    /**
     * Получение колличества товаров полученных в результате поиска на странице
     *
     * @return - количество товаров
     */
    public int getCountSearchResult() {
        Actions.hover.accept(By.xpath(endPage));
        getListSearchResult();
        return searchResults.size();
    }

    /**
     * Нажатие на кнопку "Показать ещё"
     */
    public void clickShowMoreButton() {
        String moreButton = "//div[@data-zone-name='SearchPager']//button[contains(., 'Показать ещё')]";
        webDriver.findElement(By.xpath(moreButton)).click();
        Waits.waitUntilInvisibilityOfElementLocated(greyScreen);
    }

    /**
     * Получение наименования товара полученного в результате поиска по номеру товара в списке
     *
     * @param count - номер товара (начинается с нуля)
     * @return - название товара
     */
    public String getSearchResultsText(int count) {
        getListSearchResult();
        return searchResults.get(count).getText();
    }

    /**
     * Проверяет есть ли в списке товаров полученных в результате поиска товар с определенным названием
     *
     * @param text - название продукта который мы хотим найти
     * @return - да, если товар с данным названием есть в результате поиска. нет, если товар с данным названием отсутствует
     */
    public boolean checkSearchResult(String text) {
        Actions.hover.accept(By.xpath(endPage));
        getListSearchResult();
        return searchResults.stream().anyMatch(p -> p.getText().equals(text));
    }

    /**
     * Инициализация обьектов необходимых для поисковой страницы
     */
    private void initSearchFilterElement() {
        String priceMinInput = "//div[@data-auto='filter-range-glprice']//span[@data-auto='filter-range-min']//input";
        String priceMaxInput = "//div[@data-auto='filter-range-glprice']//span[@data-auto='filter-range-max']//input";
        String manufacturersButton = "//fieldset[contains(., 'Производитель')]//span[@role = 'button']";

        priceMin = webDriver.findElement(By.xpath(priceMinInput));
        priceMax = webDriver.findElement(By.xpath(priceMaxInput));
        allManufacturersButton = webDriver.findElement(By.xpath(manufacturersButton));
    }

    /**
     * Получение списка товаров полученных в результате поиска
     */
    private void getListSearchResult() {
        String searchResultH3 = "//div[contains(@data-apiary-widget-id,'SearchSerp')]//article//h3";
        searchResults = webDriver.findElements(By.xpath(searchResultH3));
    }
}
