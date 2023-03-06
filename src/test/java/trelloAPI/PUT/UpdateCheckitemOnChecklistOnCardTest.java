package trelloAPI.PUT;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteAChecklistOnACardTest;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Globals;
import trelloAPI.POST.CreateCheckitemOnChecklistTest;
import trelloAPI.POST.CreateChecklistOnACard;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class UpdateCheckitemOnChecklistOnCardTest {
    public String CARD_ID;
    public String CHECKLIST_ID;
    public String CHECKITEM_ID;
    public String CHECKITEM_POS;

    @BeforeTest
    public void createChecklistOnCard(){
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
        CreateChecklistOnACard createChecklistOnACard = new CreateChecklistOnACard();
        createChecklistOnACard.CARD_ID = CARD_ID;
        createChecklistOnACard.createChecklistOnACards();
        CHECKLIST_ID = createChecklistOnACard.ID_CHECKLIST;
        CreateCheckitemOnChecklistTest createCheckitemOnChecklistTest = new CreateCheckitemOnChecklistTest();
        createCheckitemOnChecklistTest.CARD_ID = CARD_ID;
        createCheckitemOnChecklistTest.CHECKLIST_ID = CHECKLIST_ID;
        createCheckitemOnChecklistTest.createCheckitemOnChecklistTest();
        CHECKITEM_ID = createCheckitemOnChecklistTest.CHECKITEM_ID;
        CHECKITEM_POS = createCheckitemOnChecklistTest.CHECKITEM_POS;
    }

    @Test
    public void updateCheckitemOnChecklistOnCardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("pos", "top")
                .when()
                .put("/1/cards/{idCard}/checklist/{idChecklist}/checkItem/{idCheckItem}", CARD_ID, CHECKLIST_ID, CHECKITEM_ID)
                .then().log().all()
                .extract().jsonPath();
        
        Assert.assertNotEquals(response.get("pos").toString(), CHECKITEM_POS);
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
