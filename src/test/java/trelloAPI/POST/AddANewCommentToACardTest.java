package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteACommentOnACardTest;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class AddANewCommentToACardTest {

    public static String COMMENT_ID;
    public String CARD_ID;

    @BeforeTest
    public void createNewCard() {
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
    }

    @Test
    public void addANewCommentToACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("text", Globals.COMMENT_ACTION_TEXT)
                .when()
                .post("/1/cards/{id}/actions/comments", CARD_ID)
                .then().log().all()
                .extract().jsonPath();

        COMMENT_ID = response.get("id");
        Assert.assertEquals(response.get("data.card.id"), CARD_ID);
    }

    @AfterTest
    public void deleteCommentWithCard(){
        DeleteACommentOnACardTest deleteACommentOnACardTest = new DeleteACommentOnACardTest();
        deleteACommentOnACardTest.COMMENT_ID = COMMENT_ID;
        deleteACommentOnACardTest.CARD_ID = CARD_ID;
        deleteACommentOnACardTest.createNewCommentToACard();
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
