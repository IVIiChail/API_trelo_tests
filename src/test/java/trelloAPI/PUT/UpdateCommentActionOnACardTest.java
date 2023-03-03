package trelloAPI.PUT;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.*;
import trelloAPI.DELETE.DeleteACommentOnACardTest;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Globals;
import trelloAPI.POST.AddANewCommentToACardTest;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class UpdateCommentActionOnACardTest {
    private static String ACTION_ID;
    public String CARD_ID;

    @BeforeTest
    public void createNewCardWithAComment(){
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
        AddANewCommentToACardTest addANewCommentToACardTest = new AddANewCommentToACardTest();
        addANewCommentToACardTest.CARD_ID = CARD_ID;
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
                .put("/1/cards/{id}/actions/{idAction}/comments", CARD_ID, ACTION_ID)
                .then().log().all()
                .extract().jsonPath();

        Assert.assertEquals(response.get("id"), ACTION_ID);
        Assert.assertEquals(response.get("data.text"), Globals.COMMENT_ACTION_UPDATE_TEXT);
    }

    @AfterTest
    public void deleteCommentWithCard(){
        DeleteACommentOnACardTest deleteACommentOnACardTest = new DeleteACommentOnACardTest();
        deleteACommentOnACardTest.CARD_ID = CARD_ID;
        deleteACommentOnACardTest.COMMENT_ID = ACTION_ID;
        deleteACommentOnACardTest.deleteACommentOnACardTest();
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
