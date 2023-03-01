package trelloAPI.PUT;

import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class UpdateCommentActionOnACardTest {

    @Test
    public void updateCommentActionOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .queryParam("text", Globals.COMMENT_ACTION_TEXT)
                .when()
                .put("/1/cards/{id}/actions/{idAction}/comments", Globals.ID_CARD, Globals.ID_ACTION)
                .then().log().all();
    }
}
