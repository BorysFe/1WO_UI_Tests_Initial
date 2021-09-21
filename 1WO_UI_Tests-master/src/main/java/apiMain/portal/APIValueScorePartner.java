package apiMain.portal;

import base.enums.PageURLs;
import io.restassured.RestAssured;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class APIValueScorePartner {

    private Response getResponseForValueScorePartnerRequest(String id, String cookie) {
        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        requestBody.put("partners", Collections.singleton(id));

        RequestSpecification request = RestAssured.given()
                .header("cookie", cookie)
                .and()
                .header("Content-Type", "application/json")
                .and()
                .body(requestBody.toString());

        Response response = request.request(Method.POST, "/partners/features-analytics");

        return response;
    }

    public int getStatusScorePartnerRequest(String id, String cookie) {

        return getResponseForValueScorePartnerRequest(id, cookie).getStatusCode();
    }

    private JSONObject getJSONObjectFromResponseForValueScorePartnerRequest(String id, String cookie) {
        Response response = getResponseForValueScorePartnerRequest(id, cookie);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) jsonParser.parse(response.getBody().print());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray featuresArray = (JSONArray) jsonObject.get("featuresPerPartner");

        JSONObject featuresObject = (JSONObject) featuresArray.get(0);

        return featuresObject;
    }

    public int getScoreForPartner(String partnerId, String cookie) {
        JSONObject featuresObject = getJSONObjectFromResponseForValueScorePartnerRequest(partnerId, cookie);

        Long score = (Long) featuresObject.get("score");

        return score.intValue();
    }

    public String getNameOfTheFeatureAnalytics(String partnerId, String cookie) {
        JSONObject featuresObject = getJSONObjectFromResponseForValueScorePartnerRequest(partnerId, cookie);

        JSONArray featuresArray = (JSONArray) featuresObject.get("features");

        List<List<String>> lists = new ArrayList<>();

        for (int y = 0; y < featuresArray.size(); y++) {
            JSONObject featuresSecondObject = (JSONObject) featuresArray.get(y);
            JSONArray triggersArray = (JSONArray) featuresSecondObject.get("triggers");

            List<String> names = new ArrayList<>();

            for (int yy = 0; yy < triggersArray.size(); yy++) {
                JSONObject objectsList = (JSONObject) featuresArray.get(yy);
                String name = objectsList.get("name").toString();
                names.add(name);
            }

            lists.add(names);
        }

        System.out.println(lists.get(0));
        return lists.get(0).get(0);
    }
}
