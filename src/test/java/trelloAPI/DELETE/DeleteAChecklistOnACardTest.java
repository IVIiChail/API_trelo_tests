package trelloAPI.DELETE;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.POST.CreateChecklistOnACard;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteAChecklistOnACardTest {
    public String CARD_ID;
    public String CHECKLIST_ID;

    @BeforeTest
    public void createNewCardWithChecklist() {
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
        CreateChecklistOnACard createChecklistOnACard = new CreateChecklistOnACard();
        createChecklistOnACard.CARD_ID = CARD_ID;
        createChecklistOnACard.createChecklistOnACards();
        CHECKLIST_ID = createChecklistOnACard.ID_CHECKLIST;
    }

    @Test
    public void deleteAChecklistOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .delete("/1/cards/{id}/checklists/{idChecklist}", CARD_ID, CHECKLIST_ID)
                .then().log().all()
                .extract().jsonPath();
    }
}
