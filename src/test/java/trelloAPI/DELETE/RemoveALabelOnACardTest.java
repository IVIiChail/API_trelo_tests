package trelloAPI.DELETE;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.POST.CreateANewLabelOnACardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class RemoveALabelOnACardTest {

    public String ID_LABEL;

    @BeforeTest
    public void setupLabelToNewCard(){
//        AddAMemberToACardTest addAMemberToACardTest = new AddAMemberToACardTest();
//        addAMemberToACardTest.addAMemberToACardTest();
        CreateANewLabelOnACardTest createANewLabelOnACardTest = new CreateANewLabelOnACardTest();
        createANewLabelOnACardTest.createANewLabelOnACardTest();
        ID_LABEL = createANewLabelOnACardTest.CARD_LABEL_ID;
    }

    @Test
    public void removeALabelOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .delete("/1/cards/{id}/idLabels/{idLabel}", Globals.ID_CARD, ID_LABEL)
                .then().log().all();
    }
}