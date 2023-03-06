package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteAChecklistOnACardTest;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class CreateCheckitemOnChecklistTest {
    public String CHECKLIST_ID;
    public String CHECKITEM_ID;
    public String CHECKITEM_POS;
    public String CARD_ID;

    @BeforeTest
    public void createChecklistOnCard(){
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
        CreateChecklistOnACard createChecklistOnACard = new CreateChecklistOnACard();
        createChecklistOnACard.CARD_ID = CARD_ID;
        createChecklistOnACard.createChecklistOnACards();
        CHECKLIST_ID = createChecklistOnACard.ID_CHECKLIST;
    }


    @Test
    public void createCheckitemOnChecklistTest(){
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        JsonPath jsonResponse = given()
                .header("Accept", "application/json")
                .queryParam("name", Globals.CHECKITEM_NAME)
                .when()
                .post("/1/checklists/{id}/checkItems", CHECKLIST_ID)
                .then().log().all()
                .extract().jsonPath();
        CHECKITEM_ID = jsonResponse.get("id");
        CHECKITEM_POS = jsonResponse.get("pos").toString();
        Assert.assertEquals(jsonResponse.get("idChecklist").toString(), CHECKLIST_ID);
        Assert.assertEquals(jsonResponse.get("name"), Globals.CHECKITEM_NAME);
    }

    @AfterTest
    public void deleteCardWithChecklist() {
        DeleteAChecklistOnACardTest deleteAChecklistOnACardTest = new DeleteAChecklistOnACardTest();
        deleteAChecklistOnACardTest.CARD_ID = CARD_ID;
        deleteAChecklistOnACardTest.CHECKLIST_ID = CHECKLIST_ID;
        deleteAChecklistOnACardTest.deleteAChecklistOnACardTest();
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
