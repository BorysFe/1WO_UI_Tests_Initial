package apiMain.portal.pollerWidget;

import apiMain.APIValue;
import base.enums.Accounts;
import base.enums.PageURLs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class APIPollerWidget {
    private static final Logger logger = LoggerFactory.getLogger(APIPollerWidget.class);

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

        int statusCode = new APIPoll().newRandomPollRequest(pollText).getStatusCode();

        return statusCode;
    }

    public Response newPollerWidgetRequest(String partnerId, String partnerCookie, String widgetTitle) {

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

        return response;
    }

    public Response newBetaPollerWidgetRequest(String partnerId, String partnerCookie, String widgetTitle) {

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
        widget.put("showHeader", true);
        widget.put("hideClosedPolls", true);
        widget.put("type", "poller");
        widget.put("partner", partner);
        widget.put("showUserScore", true);

        widget.put("colorTheme", "simpleDark");
        widget.put("headerTextAlignment", "center");
        widget.put("pollTaglineAlignment", "center");
        widget.put("headerBarColor", "#141414");
        widget.put("backgroundColor", "#000000");
        widget.put("backgroundTransparency", "50");
        widget.put("pollTaglineTextColor", "#ffffff");
        widget.put("votingButtonsColor", "#4D9BFB");
        widget.put("headerTextColor", "#ffffff");

        widget.put("categories", Collections.singleton(""));
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

    public Response add2PollsInWidget(String partnerId,
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

    public Integer getIntegerValueFromResponse(Response response, String value) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) jsonParser.parse(response.getBody().print());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Integer.valueOf(jsonObject.get(value).toString());
    }

    public String getStringValueFromResponse(Response response, String value) {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) jsonParser.parse(response.getBody().print());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return jsonObject.get(value).toString();
    }

    public Response addPollsInWidget(String partnerId,
                                     String partnerCookie,
                                     String widgetId,
                                     int pollsCount,
                                     String pollTitle,
                                     String pollAnswer1,
                                     String pollAnswer2,
                                     String categoryId,
                                     String pollType,
                                     String locale) {

        APIPoll apiPoll = new APIPoll();
        JSONObject requestBody = new JSONObject();
        List<JSONObject> entityList = new ArrayList<>();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        JSONObject partner = new JSONObject();
        partner.put("externalId", partnerId);

        JSONObject idNewMultiplePolls = apiPoll.createMultiplePolls(partnerId, partnerCookie, pollsCount, pollTitle, pollAnswer1, pollAnswer2, categoryId, pollType, locale);

        for (int yy = 0; yy < pollsCount; yy++) {

            String pollID = idNewMultiplePolls.get(yy).toString();

            JSONObject entity = new JSONObject();
            entity.put("entity", pollID);
            entity.put("entityType", "poll");
            entity.put("order", (yy + 1));

            entityList.add(entity);
        }

        JSONObject widget = new JSONObject();
        widget.put("displayLocale", "en");
        widget.put("widgetCode", widgetId);
        widget.put("entitiesExcluded", Collections.singleton(""));
        widget.put("partner", partner);
        widget.put("entities", entityList);

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

    public String newWidgetWithMultiplePolls(String partnerId,
                                             String partnerCookie,
                                             String widgetTitle,
                                             int pollsCount,
                                             String pollTitle,
                                             String pollAnswer1,
                                             String pollAnswer2,
                                             String categoryId,
                                             String pollType,
                                             String locale) {

        Response newWidgetResponse = newPollerWidgetRequest(partnerId, partnerCookie, widgetTitle);
        String owoWidgetCode = getStringValueFromResponse(newWidgetResponse, APIValue.OWO_WIDGET_CODE.toString());

        addPollsInWidget(partnerId, partnerCookie, owoWidgetCode, pollsCount, pollTitle, pollAnswer1, pollAnswer2, categoryId, pollType, locale);

        return owoWidgetCode;
    }
}
