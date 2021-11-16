package apiTests.portal;

import apiMain.portal.APIValueScorePartner;
import base.enums.Accounts;
import base.enums.PageURLs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntroductionScreenValueScorePartnerTest {

    private static String partnerCookie;
    private static String partnerId;

    @BeforeClass
    public void preparePartnerData() {
        partnerId = Accounts.PUBLISHER_ID.toString();
        partnerCookie = Accounts.PUBLISHER_COOKIE.toString();
    }

    @Test
    public static void statusScorePartner() {
        int requestScoreStatus = new APIValueScorePartner()
                .getStatusScorePartnerRequest(partnerId, partnerCookie);

        Assertions.assertThat(requestScoreStatus)
                .as("statusCode - " + requestScoreStatus)
                .isEqualTo(200);
    }

    @Test
    public static void valueTotalScorePartner() {
        int scorePartner = new APIValueScorePartner()
                .getScoreForPartner(partnerId, partnerCookie);

        Assertions.assertThat(scorePartner)
                .as("Score is - " + scorePartner)
                .isEqualTo(50);
    }

    @Test
    public void NewBetaPollerWidgetRequest() {

        String partnerId = Accounts.PUBLISHER_ID.toString();
        String partnerCookie = Accounts.PUBLISHER_COOKIE.toString();
        String widgetTitle = "beta Widget from frame";
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
        widget.put("colorTheme", "simpleDark");

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

        Assertions.assertThat(response.getStatusCode())
                .as("statusCode - " + response.getStatusCode())
                .isEqualTo(200);
    }
}
