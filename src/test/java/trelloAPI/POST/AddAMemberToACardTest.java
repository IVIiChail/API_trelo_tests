package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.RemoveMemberFromACardTest;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class AddAMemberToACardTest {

    @AfterTest
    public void removeMemberFromCard(){
        RemoveMemberFromACardTest removeMemberFromACardTest = new RemoveMemberFromACardTest();
        removeMemberFromACardTest.removeMemberFromACardTest();
    }

    @Test
    public void addAMemberToACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("value",Globals.MEMBER_ID_ADD)
                .when()
                .post("/1/cards/{id}/idMembers", Globals.ID_CARD)
                .then().log().all()
                .extract().jsonPath();

        //Assert.assertEquals(response.get("id"), Globals.MEMBER_ID_ADD);
    }
}
