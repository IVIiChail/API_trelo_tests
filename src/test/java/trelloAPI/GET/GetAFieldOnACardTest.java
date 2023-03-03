package trelloAPI.GET;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Globals;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class GetAFieldOnACardTest {
    public String CARD_ID;

    @BeforeTest
    public void createNewCard(){
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
    }
    
    @Test
    public void getFieldOnCardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath jsonResponse = given()
                .header("Accept", "application/json")
                .when()
                .get("/1/cards/{id}/{field}", CARD_ID, Globals.CARD_FIELD_NAME)
                .then().log().all()
                .extract().jsonPath();
        Assert.assertEquals(jsonResponse.get("_value").toString(), "");
    }

    @AfterTest
    public void deleteCardField(){
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
