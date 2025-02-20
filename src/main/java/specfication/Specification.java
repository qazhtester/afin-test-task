package specfication;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {

    private static final String BASE_URL = "http://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/party";
    private static final String TOKEN = "Token 884a52419509c658fa68ae44c515885a41929906";

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Authorization:", TOKEN)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .expectStatusCode(statusCode)
                .build();
    }
}
