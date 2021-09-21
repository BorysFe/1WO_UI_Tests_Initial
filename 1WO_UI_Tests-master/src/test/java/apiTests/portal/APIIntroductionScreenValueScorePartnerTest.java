package apiTests.portal;

import apiMain.portal.APIValueScorePartner;
import base.enums.Accounts;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class APIIntroductionScreenValueScorePartnerTest {

    private static String partnerId;
    private static String partnerCookie;

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

        System.out.println(scorePartner);

        Assertions.assertThat(scorePartner)
                .as("Score is - " + scorePartner)
                .isEqualTo(90);
    }
}
