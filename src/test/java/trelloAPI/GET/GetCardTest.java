package trelloAPI.GET;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Globals;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class GetCardTest {
    private static String CARD_ID;
    @BeforeMethod
    public void createNewCard (){
        CreateNewCardTest createNewCard = new CreateNewCardTest();
        createNewCard.createNewCardTest();
        CARD_ID = createNewCard.ID_CARD;
    }
    @Test
    public void getCardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath jsonResponse = given()
                .header("Accept", "application/json")
                .when()
                .get("/1/cards/{id}", CARD_ID)
                .then().log().all()
                .extract().jsonPath();

        Assert.assertEquals(jsonResponse.get("idList").toString(),Globals.ID_LIST);
        Assert.assertEquals(jsonResponse.get("id").toString(),CARD_ID);
    }

    @AfterMethod
    public void deleteCard(){
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();

    }
}
