package apiMain.portal.pollerWidget;

import base.enums.Accounts;
import base.enums.PageURLs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class APIPollerWidget {

    String partnerId;
    String partnerCookie;

    @BeforeClass
    public void preparePartnerData() {
        partnerId = Accounts.PUBLISHER_ID.toString();
        partnerCookie = Accounts.PUBLISHER_COOKIE.toString();
    }

    private Response getResponseNewPollRequest(String partnerId,
                                               String partnerCookie,
                                               String questionText,
                                               String answerText1,
                                               String answerText2,
                                               int categoryId,
                                               String pollType,
                                               String locale) {

        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        JSONObject answer1json = new JSONObject();
        answer1json.put("answer", answerText1);
        JSONObject answer2json = new JSONObject();
        answer2json.put("answer", answerText2);
        List<JSONObject> jsonObjects = Arrays.asList(answer1json, answer2json);
        JSONObject sides = new JSONObject();
        sides.put("sides", jsonObjects);

        JSONObject poll = new JSONObject();
        poll.put("categoryId", categoryId);
        poll.put("tagLine", questionText);
        poll.put("sides", jsonObjects);
        poll.put("type", pollType);
        poll.put("privatePoll", false);

        requestBody.put("poll", poll);
        requestBody.put("partnerExternalId", partnerId);
        requestBody.put("locale", locale);

        System.out.println(requestBody);
        RequestSpecification request = RestAssured.given()
                .header("cookie", partnerCookie)
                .and()
                .header("Content-Type", "application/json")
                .and()
                .body(requestBody.toString());

        Response response = request.request(Method.POST, "1ws/json/PollCreateWithMultipleSides");

        return response;
    }

    public int getStatusNewPollerWidgetRequest(String questionText,
                                               String answerText1,
                                               String answerText2,
                                               int categoryId,
                                               String pollType,
                                               String locale) {

        return getResponseNewPollRequest(partnerId,
                partnerCookie,
                questionText,
                answerText1,
                answerText2,
                categoryId,
                pollType,
                locale)
                .getStatusCode();
    }

    public int getStatusNewRandomPollerWidgetRequest() {

        int statusCode = new APIPoll().getResponseNewRandomPollRequest().getStatusCode();

        return statusCode;
    }


    public Response getResponseNewPollerWidgetRequest() {

        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        JSONObject partner = new JSONObject();
        partner.put("externalId", "0818ac2f-d972-4f28-9aa0-ef340ed6527f");

        JSONObject widget = new JSONObject();
        widget.put("displayLocale", "en");
        widget.put("name", "name");
        widget.put("width", 0);
        widget.put("mobileWidth", 0);
        widget.put("height", 375);
        widget.put("type", "smart3");
        widget.put("categories", Collections.singleton(""));
        widget.put("partner", partner);
        widget.put("polls", Collections.singleton(""));
        widget.put("pollsExcluded", Collections.singleton(""));
        widget.put("pollChains", Collections.singleton(""));

        requestBody.put("widget", widget);

        System.out.println(requestBody);
        RequestSpecification request = RestAssured.given()
                .header("cookie", partnerCookie)
                .and()
                .header("Content-Type", "application/json")
                .and()
                .body(requestBody.toString());

        Response response = request.request(Method.POST, "1ws/json/WidgetAdd");

        return response;
    }

    public Response getResponseAddingPollsInWidgetRequest() {

        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        JSONObject partner = new JSONObject();
        partner.put("externalId", "0818ac2f-d972-4f28-9aa0-ef340ed6527f");

        JSONObject entity1 = new JSONObject();
        entity1.put("entity", 31371);
        entity1.put("entityType", "poll");
        entity1.put("order", 1);

        JSONObject entity2 = new JSONObject();
        entity2.put("order", 2);
        entity2.put("entity", 31372);
        entity2.put("entityType", "poll");

        List<JSONObject> entities = Arrays.asList(entity1, entity2);

        JSONObject widget = new JSONObject();
        widget.put("displayLocale", "en");
        widget.put("widgetCode", "43dbf06e-0170-4fc3-a78c-eef961599c72");
        widget.put("entitiesExcluded", Collections.singleton(""));
        widget.put("partner", partner);
        widget.put("entities", entities);

        requestBody.put("widget", widget);

        RequestSpecification request = RestAssured.given()
                .header("cookie", partnerCookie)
                .and()
                .header("Content-Type", "application/json")
                .and()
                .body(requestBody.toString());

        Response response = request.request(Method.POST, "1ws/json/WidgetEdit");

        return response;
    }
}
