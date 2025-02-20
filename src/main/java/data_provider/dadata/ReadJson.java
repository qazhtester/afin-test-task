package data_provider.dadata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pogo.dadata_service.json.ExpectedResultJsonData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReadJson {
    private static final String EXPECTED_RESULT_JSON_PATH = "expected-result.json";
    private static final String QUERY_JSON_PATH = "query.json";

    /**
     * Загружает список ИНН из файла query.json.
     * Метод читает JSON-файл, содержащий массив объектов с полем "inn",
     * и возвращает список значений этого поля.
     *
     * @return Список ИНН в порядке их следования в файле
     * @return
     */
    public static List<String> loadInnsFromQueryJson() {
        try (InputStream inputStream = DataProviderDaData.class.getClassLoader().getResourceAsStream(QUERY_JSON_PATH)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(inputStream);
            List<String> inns = new ArrayList<>();
            root.forEach(node -> inns.add(node.get("inn").asText()));
            return inns;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load query.json", e);
        }
    }

    /**
     * Загружает ожидаемые данные тестов из файла expected-result.json в виде Map.
     * Метод создает словарь, где:
     * - Ключ: ИНН организации (поле inn)
     * - Значение: Полный объект с ожидаемыми данными ({@link ExpectedResultJsonData})
     *
     * @return
     */
    public static Map<String, ExpectedResultJsonData> loadExpectedDataMap() {
        try (InputStream inputStream = DataProviderDaData.class.getClassLoader().getResourceAsStream(EXPECTED_RESULT_JSON_PATH)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<ExpectedResultJsonData> expectedDataList = mapper.readValue(inputStream, new TypeReference<>() {
            });
            return expectedDataList.stream()
                    .collect(Collectors.toMap(ExpectedResultJsonData::getInn, Function.identity()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load expected-result.json", e);
        }
    }
}
