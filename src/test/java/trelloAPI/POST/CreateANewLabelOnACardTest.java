package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.DELETE.RemoveALabelOnACardTest;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class CreateANewLabelOnACardTest {

    public static String CARD_LABEL_ID;

    public String CARD_ID;

    @BeforeTest
    public void createNewCard() {
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
    }

    @Test
    public void createANewLabelOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("color", Globals.LABEL_COLOR)
                .queryParam("name", Globals.LABEL_NAME)
                .when()
                .post("/1/cards/{id}/labels", CARD_ID)
                .then().log().all()
                .extract().jsonPath();

        CARD_LABEL_ID = response.get("id");
        Assert.assertEquals(response.get("color"), Globals.LABEL_COLOR);
        Assert.assertEquals(response.get("name"), Globals.LABEL_NAME);
    }

    @AfterTest
    public void deleteLabelWithCard(){
        RemoveALabelOnACardTest removeALabelOnACardTest = new RemoveALabelOnACardTest();
        removeALabelOnACardTest.ID_LABEL = CARD_LABEL_ID;
        removeALabelOnACardTest.CARD_ID = CARD_ID;
        removeALabelOnACardTest.removeALabelOnACardTest();
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
