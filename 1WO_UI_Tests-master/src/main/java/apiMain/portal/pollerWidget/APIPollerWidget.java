package apiMain.portal.pollerWidget;

import base.enums.Accounts;
import base.enums.PageURLs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class APIPollerWidget {

    String partnerId;
    String partnerCookie;
    String pollText;

    @BeforeClass
    public void preparePartnerData() {
        partnerId = Accounts.PUBLISHER_ID.toString();
        partnerCookie = Accounts.PUBLISHER_COOKIE.toString();
        pollText = "Random Poll";
    }

    public int getStatusNewRandomPollerWidgetRequest() {

        int statusCode = new APIPoll().getResponseNewRandomPollRequest(pollText).getStatusCode();

        return statusCode;
    }

    public Response getResponseNewPollerWidgetRequest(String partnerId) {

        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        JSONObject partner = new JSONObject();
        partner.put("externalId", partnerId);

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

    public String getIdForNewPollerWidget(String partnerId,
                                          String partnerCookie,
                                          String widgetTitle) {

        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        JSONObject partner = new JSONObject();
        partner.put("externalId", partnerId);

        JSONObject widget = new JSONObject();
        widget.put("displayLocale", "en");
        widget.put("name", widgetTitle);
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

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) jsonParser.parse(response.getBody().print());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonObject.get("widgetCode").toString();
    }

    public Response adding2PollsInWidget(String partnerId,
                                         String partnerCookie,
                                         Integer pollId1,
                                         Integer pollId2,
                                         String widgetId) {

        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        JSONObject partner = new JSONObject();
        partner.put("externalId", partnerId);

        JSONObject entity1 = new JSONObject();
        entity1.put("entity", pollId1);
        entity1.put("entityType", "poll");
        entity1.put("order", 1);

        JSONObject entity2 = new JSONObject();
        entity2.put("entity", pollId2);
        entity2.put("entityType", "poll");
        entity2.put("order", 2);

        List<JSONObject> entities = Arrays.asList(entity1, entity2);

        JSONObject widget = new JSONObject();
        widget.put("displayLocale", "en");
        widget.put("widgetCode", widgetId);
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

    public String getOWOCodeForNewWidgetWith2Polls(String partnerId, String partnerCookie, String widgettitle, String poll1Text, String poll2Text) {

        int pollId1 = new APIPoll().getIdForNewRandomPoll(partnerId, partnerCookie, poll1Text);
        int pollId2 = new APIPoll().getIdForNewRandomPoll(partnerId, partnerCookie, poll2Text);

        String newWidgetId = getIdForNewPollerWidget(partnerId, partnerCookie, widgettitle).toString();

        adding2PollsInWidget(partnerId, partnerCookie, pollId1, pollId2, newWidgetId);

        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        JSONObject partner = new JSONObject();
        partner.put("externalId", partnerId);

        JSONObject entity1 = new JSONObject();
        entity1.put("entity", pollId1);
        entity1.put("entityType", "poll");
        entity1.put("order", 1);

        JSONObject entity2 = new JSONObject();
        entity2.put("order", 2);
        entity2.put("entity", pollId2);
        entity2.put("entityType", "poll");

        List<JSONObject> entities = Arrays.asList(entity1, entity2);

        JSONObject widget = new JSONObject();
        widget.put("displayLocale", "en");
        widget.put("widgetCode", newWidgetId);
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

        return newWidgetId;
    }
}
