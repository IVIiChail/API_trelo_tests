package trelloAPI.DELETE;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.POST.AddANewCommentToACardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteACommentOnACardTest {
    private static String COMMENT_ID;
    private static AddANewCommentToACardTest addANewCommentToACardTest;

    @BeforeTest
    public void createNewCommentToACard(){
        addANewCommentToACardTest = new AddANewCommentToACardTest();
        addANewCommentToACardTest.addANewCommentToACardTest();
        COMMENT_ID = addANewCommentToACardTest.COMMENT_ID;
    }

    @Test
    public void deleteACommentOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .delete("/1/cards/{id}/actions/{idAction}/comments", Globals.ID_CARD, COMMENT_ID)
                .then().log().all();
    }
}
