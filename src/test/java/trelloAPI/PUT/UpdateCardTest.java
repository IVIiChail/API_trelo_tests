package trelloAPI.PUT;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Globals;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class UpdateCardTest {
    public static String UPDATE_CARD_ID;

    @BeforeTest
    public void createNewCard(){
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        UPDATE_CARD_ID = createNewCardTest.ID_CARD;
    }
    @Test
    public void updateCardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("name", Globals.CARD_NAME_UPDATE)
                .when()
                .put("/1/cards/{id}", UPDATE_CARD_ID)
                .then().log().all()
                .extract().jsonPath();

        Assert.assertEquals(response.get("id"), UPDATE_CARD_ID);
        Assert.assertEquals(response.get("name"), Globals.CARD_NAME_UPDATE);

    }
    @AfterTest
    public void deleteCard(){
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = UPDATE_CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
