package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.DELETE.RemoveMemberFromACardTest;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class AddAMemberToACardTest {

    public String CARD_ID;

    @BeforeTest
    public void createNewCard() {
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
    }

    @Test
    public void addAMemberToACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("value",Globals.MEMBER_ID_ADD)
                .when()
                .post("/1/cards/{id}/idMembers", CARD_ID)
                .then().log().all()
                .extract().jsonPath();

        //Assert.assertEquals(response.get("id"), Globals.MEMBER_ID_ADD);
    }

    @AfterTest
    public void deleteCardWithMember(){
        RemoveMemberFromACardTest removeMemberFromACardTest = new RemoveMemberFromACardTest();
        removeMemberFromACardTest.CARD_ID = CARD_ID;
        removeMemberFromACardTest.removeMemberFromACardTest();
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
