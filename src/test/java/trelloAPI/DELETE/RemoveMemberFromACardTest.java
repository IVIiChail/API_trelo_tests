package trelloAPI.DELETE;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.POST.AddAMemberToACardTest;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class RemoveMemberFromACardTest {

    public String CARD_ID;

    @BeforeTest
    public void createCardWithMember(){
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
        AddAMemberToACardTest addAMemberToACardTest = new AddAMemberToACardTest();
        addAMemberToACardTest.CARD_ID = CARD_ID;
        addAMemberToACardTest.addAMemberToACardTest();
    }

    @Test
    public void removeMemberFromACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .delete(" /1/cards/{id}/idMembers/{idMember}", CARD_ID, Globals.MEMBER_ID_ADD)
                .then().log().all();
    }
}