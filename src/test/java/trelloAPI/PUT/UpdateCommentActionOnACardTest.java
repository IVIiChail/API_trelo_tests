package trelloAPI.PUT;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteACommentOnACardTest;
import trelloAPI.Globals;
import trelloAPI.POST.AddANewCommentToACardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class UpdateCommentActionOnACardTest {
    private static AddANewCommentToACardTest addANewCommentToACardTest;
    private static String ACTION_ID;

    @AfterMethod
    public void deleteComment(){
        DeleteACommentOnACardTest deleteACommentOnACardTest = new DeleteACommentOnACardTest();
        deleteACommentOnACardTest.createNewCommentToACard();
    }

    @BeforeTest
    public void createNewCommentToACard(){
        addANewCommentToACardTest = new AddANewCommentToACardTest();
        addANewCommentToACardTest.addANewCommentToACardTest();
        ACTION_ID = addANewCommentToACardTest.COMMENT_ID;
    }

    @Test
    public void updateCommentActionOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("text", Globals.COMMENT_ACTION_UPDATE_TEXT)
                .when()
                .put("/1/cards/{id}/actions/{idAction}/comments", Globals.ID_CARD, ACTION_ID)
                .then().log().all()
                .extract().jsonPath();

        Assert.assertEquals(response.get("id"), ACTION_ID);
        Assert.assertEquals(response.get("data.text"), Globals.COMMENT_ACTION_UPDATE_TEXT);
    }
}
