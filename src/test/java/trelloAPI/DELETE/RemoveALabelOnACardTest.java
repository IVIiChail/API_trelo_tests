package trelloAPI.DELETE;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.POST.CreateANewLabelOnACardTest;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class RemoveALabelOnACardTest {

    public String ID_LABEL;
    public String CARD_ID;

    @BeforeTest
    public void createNewCardWithALabel() {
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
        CreateANewLabelOnACardTest createANewLabelOnACardTest = new CreateANewLabelOnACardTest();
        createANewLabelOnACardTest.CARD_ID = CARD_ID;
        createANewLabelOnACardTest.createANewLabelOnACardTest();
        ID_LABEL = createANewLabelOnACardTest.CARD_LABEL_ID;
    }

    @Test
    public void removeALabelOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .delete("/1/cards/{id}/idLabels/{idLabel}", CARD_ID, ID_LABEL)
                .then().log().all();
    }
}