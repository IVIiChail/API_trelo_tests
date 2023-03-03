package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteALabelTest;
import trelloAPI.DELETE.DeleteCardTest;
import trelloAPI.DELETE.RemoveALabelOnACardTest;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class AddALabelToACardTest {

    public String CARD_ID;
    public String LABEL_ID;

    @BeforeTest
    public void createNewCard() {
        CreateNewCardTest createNewCardTest = new CreateNewCardTest();
        createNewCardTest.createNewCardTest();
        CARD_ID = createNewCardTest.ID_CARD;
    }

    @BeforeTest
    public void createNewLabel(){
        CreateALabelTest createALabelTest = new CreateALabelTest();
        createALabelTest.createALabelTest();
        LABEL_ID = createALabelTest.LABEL_ID;
    }

    @Test
    public void addALabelToACardTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("value", LABEL_ID)
                .when()
                .post("/1/cards/{id}/idLabels", CARD_ID)
                .then().log().all()
                .extract().jsonPath();

//        System.out.println("Misha");
//        Assert.assertEquals(response.get(""), LABEL_ID);
    }

    @AfterTest
    public void deleteLabelWithCard(){
        DeleteCardTest deleteCardTest = new DeleteCardTest();
        deleteCardTest.CARD_ID = CARD_ID;
        deleteCardTest.deleteCardTest();
    }

    @AfterTest
    public void deleteLabel(){
        DeleteALabelTest deleteALabelTest = new DeleteALabelTest();
        deleteALabelTest.LABEL_ID = LABEL_ID;
        deleteALabelTest.deleteALabelTest();
    }
}
