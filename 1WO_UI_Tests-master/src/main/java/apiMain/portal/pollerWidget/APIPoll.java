package apiMain.portal.pollerWidget;

import base.enums.Accounts;
import base.enums.DefaultContent;
import base.enums.PageURLs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import portalPages.polls.polls.PollCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static base.enums.Accounts.*;
import static base.enums.DefaultContent.RANDOM_POLL_ANSWER_TEXT;
import static base.enums.DefaultContent.RANDOM_POLL_QUESTION_TEXT;

public class APIPoll {

    public Response getResponseNewPollRequest(String partnerId,
                                              String partnerCookie,
                                              String questionText,
                                              String answerText1,
                                              String answerText2,
                                              String categoryId,
                                              String pollType,
                                              String locale) {

        JSONObject requestBody = new JSONObject();

        RestAssured.baseURI = PageURLs.REST_ASSURED_BASE_URI.toString();

        JSONObject answer1json = new JSONObject();
        answer1json.put("answer", answerText1);
        JSONObject answer2json = new JSONObject();
        answer2json.put("answer", answerText2);
        List<JSONObject> listAnswers = Arrays.asList(answer1json, answer2json);
        JSONObject sides = new JSONObject();
        sides.put("sides", listAnswers);

        JSONObject poll = new JSONObject();
        poll.put("categoryId", categoryId);
        poll.put("tagLine", questionText);
        poll.put("sides", listAnswers);
        poll.put("type", pollType);
        poll.put("privatePoll", false);

        requestBody.put("poll", poll);
        requestBody.put("partnerExternalId", partnerId);
        requestBody.put("locale", locale);

        RequestSpecification request = RestAssured.given()
                .header("cookie", partnerCookie)
                .and()
                .header("Content-Type", "application/json")
                .and()
                .body(requestBody.toString());

        Response response = request.request(Method.POST, "1ws/json/PollCreateWithMultipleSides");

        return response;
    }

    public Response getResponseNewRandomPollRequest(String additionalText) {

        String partnerId = Accounts.PUBLISHER_ID.toString();
        String partnerCookie = Accounts.PUBLISHER_COOKIE.toString();

        String questionText = String.format(String.valueOf(RANDOM_POLL_QUESTION_TEXT), additionalText + "");
        String answerText1 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), additionalText + "1");
        String answerText2 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), additionalText + "2");
        String categoryId = String.valueOf(PollCategory.CATEGORY_VALUE_ART_CULTURE);
        String pollType = "dpoll";
        String locale = "en";

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

        RequestSpecification request = RestAssured.given()
                .header("cookie", partnerCookie)
                .and()
                .header("Content-Type", "application/json")
                .and()
                .body(requestBody.toString());

        Response response = request.request(Method.POST, "1ws/json/PollCreateWithMultipleSides");

        return response;
    }

    public Integer getIdForNewRandomPoll(String partnerId,
                                         String partnerCookie,
                                         String additionalText) {

        String questionText = String.format(String.valueOf(RANDOM_POLL_QUESTION_TEXT), additionalText);
        String answerText1 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), additionalText + "1");
        String answerText2 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), additionalText + "2");
        String categoryId = String.valueOf(PollCategory.CATEGORY_VALUE_ART_CULTURE);
        String pollType = "dpoll";
        String locale = "en";

        Response response = getResponseNewPollRequest(partnerId, partnerCookie, questionText, answerText1, answerText2,
                categoryId, pollType, locale);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;

        try {
            jsonObject = (JSONObject) jsonParser.parse(response.getBody().print());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Integer.valueOf(jsonObject.get("id").toString());
    }
}
