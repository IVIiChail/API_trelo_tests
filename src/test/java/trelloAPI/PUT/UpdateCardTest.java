package trelloAPI.PUT;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Globals;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;
import static trelloAPI.GET.GetCardTest.CARD_ID;

public class UpdateCardTest {
    public static String NAME_UPDATE;

    @BeforeTest
    public void createNewCard(){
        CreateNewCardTest createNewCard = new CreateNewCardTest();
        createNewCard.createNewCard();
        CARD_ID = createNewCard.ID_CARD;
    }
    @Test
    public void updateCard(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("some text", Globals.CARD_NAME_UPDATE)
                .when()
                .post("/1/cards/{id}", Globals.ID_CARD, Globals.CARD_NAME)
                .then().log().all()
                .extract().jsonPath();

        Assert.assertEquals(response.get("id"), Globals.CARD_NAME);
        Assert.assertEquals(response.get("some text"), Globals.CARD_NAME_UPDATE);

    }
    public void deleteCard(){
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
