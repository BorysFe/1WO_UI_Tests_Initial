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

public class IntroductionScreenTest {

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
}
