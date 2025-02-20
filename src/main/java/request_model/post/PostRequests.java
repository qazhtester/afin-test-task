package request_model.post;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specfication.Specification.requestSpec;
import static specfication.Specification.responseSpec;

public class PostRequests {
    public static Response postResponse(Object body, int statusCode) {
        return given(requestSpec())
                .body(body)
                .when()
                .post()
                .then()
                .spec(responseSpec(statusCode))
                .extract().response();
    }
}
