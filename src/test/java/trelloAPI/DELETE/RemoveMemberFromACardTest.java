package trelloAPI.DELETE;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.POST.AddAMemberToACardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class RemoveMemberFromACardTest {

    @BeforeTest
    public void addMemberToCard(){
        AddAMemberToACardTest addAMemberToACardTest = new AddAMemberToACardTest();
        addAMemberToACardTest.addAMemberToACardTest();
    }

    @Test
    public void removeMemberFromACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .delete(" /1/cards/{id}/idMembers/{idMember}", Globals.ID_CARD, Globals.MEMBER_ID_ADD)
                .then().log().all();
    }
}