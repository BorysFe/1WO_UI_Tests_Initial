package apiTests.portal;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.Collections;

public class PurtnerDashBoardTest {

    @Test
    public static void valueScorePartner() {
        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = "https://app.1worldonline.com/";

        requestBody.put("partners", Collections.singleton("\"f9f5cc9e-cf6c-4c6e-90a1-a54f0863dcbc\""));

        RequestSpecification request = RestAssured.given()
                .header("cookie", "RememberMe=Ym9yeXNib3J5c0BtYWlsaW5hdG9yLmNvbQ~~#l7DMAoTz70MSh4CXoIp3hA~~")
                .and()
                .header("Content-Type", "application/json")
                .and()
                .body(requestBody.toString());

        Response response = request.request(Method.POST, "/partners/features-analytics");

        System.out.println("request - " + request);
        System.out.println("requestBody - " + requestBody);

        System.out.println("statusCode - " + response.getStatusCode());
        System.out.println("responseBody - " + response.getBody().htmlPath().prettyPrint());

        Assertions.assertThat(response.getStatusCode())
                .as("statusCode - " + response.getStatusCode())
                .isEqualTo(200);
    }
}
