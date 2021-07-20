package apiTests.portal;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Collections;

public class PurtnerDashBoardTest {
    @Test
    public static void main2() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("partners", Collections.singleton("f9f5cc9e-cf6c-4c6e-90a1-a54f0863dcbc"));

        RequestSpecification request = RestAssured.given()
                .header("cookie", "RememberMe=Ym9yeXNib3J5c0BtYWlsaW5hdG9yLmNvbQ~~#l7DMAoTz70MSh4CXoIp3hA~~")
                .and()
                .header("Content-Type", "application/json")
                .and()
                .body(requestBody.toString());

        Response response = request.post("https://app.1worldonline.com/partners/features-analytics");

        System.out.println("request - " + request);
        System.out.println("requestBody - " + requestBody);

        System.out.println("statusCode - " + response.getStatusCode());
        System.out.println("responseBody - " + response.getBody().prettyPrint());
    }
}
