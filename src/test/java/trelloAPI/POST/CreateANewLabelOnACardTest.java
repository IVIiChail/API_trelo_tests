package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.RemoveALabelOnACardTest;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class CreateANewLabelOnACardTest {

    public static String CARD_LABEL_ID;

    @Test
    public void createANewLabelOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("color", Globals.LABEL_COLOR)
                .queryParam("name", Globals.LABEL_NAME)
                .when()
                .post("/1/cards/{id}/labels", Globals.ID_CARD)
                .then().log().all()
                .extract().jsonPath();

        CARD_LABEL_ID = response.get("id");
        Assert.assertEquals(response.get("color"), Globals.LABEL_COLOR);
        Assert.assertEquals(response.get("name"), Globals.LABEL_NAME);
    }

    @AfterTest
    public void removeALabelFromACard(){
        RemoveALabelOnACardTest removeALabelOnACardTest = new RemoveALabelOnACardTest();
        removeALabelOnACardTest.ID_LABEL = CARD_LABEL_ID;
        removeALabelOnACardTest.removeALabelOnACardTest();
    }
}
