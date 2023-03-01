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
                .queryParam("text", "text")
                .when()
                .put("/1/cards/{id}/actions/{idAction}/comments", Globals.ID_CARD,Globals.ID_CHECKLIST)
                .then().log().all();
    }
}
