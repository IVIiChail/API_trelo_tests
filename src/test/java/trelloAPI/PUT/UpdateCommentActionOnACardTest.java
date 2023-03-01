package trelloAPI.PUT;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.Specifications;


import static io.restassured.RestAssured.given;

public class UpdateCommentActionOnACardTest {
    public static String ID_CARD;

    @Test
    public void updateCommentActionOnACardTest(){
//        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
//        JsonPath jsonResponse = given()
//                .header("Accept", "application/json")
//                .queryParam("idList", Globals.ID_LIST)
//                .when()
//                .post("/1/cards")
//                .jsonPath();
//
//        ID_CARD = jsonResponse.get("idList");
//        System.out.println(jsonResponse.get("idList").toString());
//        Assert.assertEquals(jsonResponse.get("idList").toString(),Globals.ID_LIST);
    }
}
