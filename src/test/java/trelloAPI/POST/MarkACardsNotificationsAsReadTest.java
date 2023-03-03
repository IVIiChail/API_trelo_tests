package trelloAPI.POST;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class MarkACardsNotificationsAsReadTest {

    public String CARD_ID;

    @BeforeTest
    public void createNewCard() {
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
    }

    @Test
    public void markACardsNotificationsAsReadTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .post("/1/cards/{id}/markAssociatedNotificationsRead", CARD_ID)
                .then().log().all()
                .extract().jsonPath();
    }

    @AfterTest
    public void deleteCard(){
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }
}
