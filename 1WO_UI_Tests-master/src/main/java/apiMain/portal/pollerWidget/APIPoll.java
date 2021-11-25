package apiMain.portal.pollerWidget;

import base.enums.Accounts;
import base.enums.DefaultContent;
import base.enums.PageURLs;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import org.codehaus.groovy.control.messages.ExceptionMessage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import portalPages.MenuProfileDropDown;
import portalPages.polls.polls.PollCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static base.enums.Accounts.*;
import static base.enums.DefaultContent.RANDOM_POLL_ANSWER_TEXT;
import static base.enums.DefaultContent.RANDOM_POLL_QUESTION_TEXT;

public class APIPoll {
    private static final Logger logger = LoggerFactory.getLogger(APIPoll.class);

    String defaultPollType = "dpoll";
    String defaultLocale = "en";

    public Response newPollRequest(String partnerId,
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

    public Response newRandomPollRequest(String additionalText) {

        String partnerId = Accounts.PUBLISHER_ID.toString();
        String partnerCookie = Accounts.PUBLISHER_COOKIE.toString();

        String questionText = String.format(String.valueOf(RANDOM_POLL_QUESTION_TEXT), additionalText);
        String answerText1 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), additionalText + "1");
        String answerText2 = String.format(String.valueOf(RANDOM_POLL_ANSWER_TEXT), additionalText + "2");
        String categoryId = String.valueOf(PollCategory.CATEGORY_VALUE_ART_CULTURE);

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
        poll.put("type", defaultPollType);
        poll.put("privatePoll", false);

        requestBody.put("poll", poll);
        requestBody.put("partnerExternalId", partnerId);
        requestBody.put("locale", defaultLocale);

        RequestSpecification request = RestAssured.given()
                .header("cookie", partnerCookie)
                .and()
                .header("Content-Type", "application/json")
                .and()
                .body(requestBody.toString());

        Response response = request.request(Method.POST, "1ws/json/PollCreateWithMultipleSides");

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

        return Integer.valueOf(jsonObject.get("id").toString());
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

    public JSONObject createMultiplePolls(String partnerId,
                                          String partnerCookie,
                                          Integer pollsCount,
                                          String pollTitle,
                                          String pollAnswerText1,
                                          String pollAnswerText2,
                                          String categoryId,
                                          String pollType,
                                          String locale) {

        JSONObject newPollsIdList = new JSONObject();

        for (int y = 0; y < pollsCount; y++) {

            Response response = newPollRequest(partnerId,
                    partnerCookie,
                    pollTitle + y,
                    pollAnswerText1 + y,
                    pollAnswerText2 + y,
                    categoryId,
                    pollType,
                    locale);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = null;

            try {
                try {
                    jsonObject = (JSONObject) jsonParser.parse(response.getBody().print());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String owoCode = jsonObject.get("id").toString();

                newPollsIdList.put(y, owoCode);

            } catch (Exception e) {
                logger.error("Poll's id doesn't present in the response");
            }

//            System.out.println(newPollsIdList.toJSONString());

        }

        return newPollsIdList;
    }
}
