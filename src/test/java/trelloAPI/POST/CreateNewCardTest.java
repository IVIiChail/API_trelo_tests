package trelloAPI.POST;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Specifications;
import trelloAPI.Globals;

import static io.restassured.RestAssured.given;

public class CreateNewCardTest {
    public String ID_CARD;
    @Test
    public void createNewCardTest(){
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        JsonPath jsonResponse = given()
                .header("Accept", "application/json")
                .queryParam("idList", Globals.ID_LIST)
                .when()
                .post("/1/cards")
                .then().log().all()
                .extract().jsonPath();

        ID_CARD = jsonResponse.get("id");
        Assert.assertEquals(jsonResponse.get("idList").toString(),Globals.ID_LIST);

    }

    @AfterTest
    public void deleteCard(){
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = ID_CARD;
        deleteCardTest.deleteCardTest();
    }
}
