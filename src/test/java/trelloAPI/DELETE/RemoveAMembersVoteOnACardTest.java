package trelloAPI.DELETE;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.POST.AddMemberVoteToCardTest;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class RemoveAMembersVoteOnACardTest {
    public String CARD_ID;

    @BeforeTest
    public void createNewCardWithMembersVote() {
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
        AddMemberVoteToCardTest addMemberVoteToCardTest = new AddMemberVoteToCardTest();
        addMemberVoteToCardTest.CARD_ID = CARD_ID;
        addMemberVoteToCardTest.addMemberVoteToCardTest();
    }

    @Test
    public void removeAMembersVoteOnACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .delete("/1/cards/{id}/membersVoted/{idMember}", CARD_ID, Globals.MEMBER_ID_ADD)
                .then().log().all();
    }

    @AfterTest
    public void deleteCard(){
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
