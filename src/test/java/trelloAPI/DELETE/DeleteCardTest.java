package trelloAPI.DELETE;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteCardTest {
    public String CARD_ID;

    @BeforeTest
    public void createNewCard(){
        CreateNewCardTest createNewCard = new CreateNewCardTest();
        createNewCard.createNewCard();
        CARD_ID = createNewCard.ID_CARD;
    }
    @Test
    public void deleteCardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath jsonResponse = given()
                .header("Accept", "application/json")
                .when()
                .delete("/1/cards/{id}", CARD_ID)
                .then().log().all()
                .extract().jsonPath();
    }
}
