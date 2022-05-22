package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class DataProvider {
    public static Stream<Arguments> searchParam() {
        String[] manufacturers = {"HP", "Lenovo"};
        return Stream.of(Arguments.of("Компьютеры", "Ноутбуки", "10000", "900000", manufacturers));
    }
}
