package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class AddANewCommentToACardTest {

    public static String COMMENT_ID;

    @Test
    public void addANewCommentToACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("text", Globals.COMMENT_ACTION_TEXT)
                .when()
                .post("/1/cards/{id}/actions/comments", Globals.ID_CARD)
                .then().log().all()
                .extract().jsonPath();

        COMMENT_ID = response.get("id");
        Assert.assertEquals(response.get("data.card.id"), Globals.ID_CARD);
    }
}
