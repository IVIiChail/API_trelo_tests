package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class CreateChecklistOnACard {
    public String ID_CHECKLIST;
    public String CARD_ID;

    @BeforeTest
    public void createNewCard() {
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
    }

    @Test
    public void createChecklistOnACards() {
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        JsonPath jsonResponse =
        given().log().all()
                .header("Accept","application.json")
                .queryParam("name","Check")
        .when()
                .post("/1/cards/{id}/checklists",CARD_ID)
        .then().log().all()
                .extract().jsonPath();

        ID_CHECKLIST = jsonResponse.get("id");
        Assert.assertEquals(jsonResponse.get("id").toString(), ID_CHECKLIST);
    }
    @AfterTest
   public void deleteCard(){
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }

}
