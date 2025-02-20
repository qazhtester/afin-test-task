package ru.alfabank.afin.task;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import pogo.dadata_service.json.ExpectedResultJsonData;
import pogo.dadata_service.response_dadata.DaDataSuggestionResponse;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static pogo.dadata_service.request_dadata.DaDataRequestBody.getQueryBody;
import static request_model.post.PostRequests.postResponse;

public class FirstTaskTest {

    @ParameterizedTest
    @MethodSource("data_provider.dadata.DataProviderDaData#getDaDataInn")
    @DisplayName("Проверка на соответствие данных из ответа с данными в файле expetcted-result")
    void daDataFindTest(String inn, ExpectedResultJsonData expectedData) {
        Response response = postResponse(getQueryBody(inn), 200);

        DaDataSuggestionResponse suggestion = response.jsonPath().getObject("suggestions[0]", DaDataSuggestionResponse.class);

        assertAll(
                () -> assertEquals(suggestion.getValue(), expectedData.getName(),
                        "Несоответствие поля name: ожидалось " + expectedData.getName() + ", получено " + suggestion.getValue()),
                () -> assertEquals(suggestion.getData().getInn(), expectedData.getInn(),
                        "Несоответствие поля inn: ожидалось " + expectedData.getInn() + ", получено " + suggestion.getData().getInn()),
                () -> assertEquals(suggestion.getData().getKpp(), expectedData.getKpp(),
                        "Несоответствие поля kpp: ожидалось " + expectedData.getKpp() + ", получено " + suggestion.getData().getKpp()),
                () -> assertEquals(suggestion.getData().getOgrn(), expectedData.getOgrn(),
                        "Несоответствие поля ogrn: ожидалось " + expectedData.getOgrn() + ", получено " + suggestion.getData().getOgrn()),
                () -> assertEquals(suggestion.getData().getType(), expectedData.getType(),
                        "Несоответствие поля type: ожидалось " + expectedData.getType() + ", получено " + suggestion.getData().getType())
        );
    }
}
