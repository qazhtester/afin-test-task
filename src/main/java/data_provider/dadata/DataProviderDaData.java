package data_provider.dadata;


import org.junit.jupiter.params.provider.Arguments;
import pogo.dadata_service.json.ExpectedResultJsonData;

import java.util.Map;
import java.util.stream.Stream;

import static data_provider.dadata.ReadJson.loadExpectedDataMap;
import static data_provider.dadata.ReadJson.loadInnsFromQueryJson;

public class DataProviderDaData {
    /**
     * Для каждого ИНН из списка создает объект Arguments с двумя элементами:
     * Сам ИНН (String)
     * Соответствующий объект ExpectedData из мапы
     *
     * @return
     */
    public static Stream<Arguments> getDaDataInn() {
        Map<String, ExpectedResultJsonData> expectedDataMap = loadExpectedDataMap();
        return loadInnsFromQueryJson().stream()
                .map(inn -> Arguments.of(inn, expectedDataMap.get(inn)));
    }
}
