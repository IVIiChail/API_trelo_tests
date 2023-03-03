package trelloAPI.DELETE;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.POST.AddANewCommentToACardTest;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteACommentOnACardTest {
    public String COMMENT_ID;
    public String CARD_ID;

    @BeforeTest
    public void createNewCardWithAComment(){
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
        AddANewCommentToACardTest addANewCommentToACardTest = new AddANewCommentToACardTest();
        addANewCommentToACardTest.CARD_ID = CARD_ID;
        addANewCommentToACardTest.addANewCommentToACardTest();
        COMMENT_ID = addANewCommentToACardTest.COMMENT_ID;
    }

    @Test
    public void deleteACommentOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .delete("/1/cards/{id}/actions/{idAction}/comments", CARD_ID, COMMENT_ID)
                .then().log().all();
    }
}
