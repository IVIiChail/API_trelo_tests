package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import trelloAPI.DELETE.DeleteALabelTest;
import trelloAPI.Globals;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class CreateALabelTest {

    public String LABEL_ID;

    @Test
    public void createALabelTest(){
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        JsonPath response = given()
                .header("Accept", "application/json")
                .queryParam("name", Globals.LABEL_NAME)
                .queryParam("color", Globals.LABEL_COLOR)
                .queryParam("idBoard", Globals.ID_BOARD)
                .when()
                .post("/1/labels")
                .then().log().all()
                .extract().jsonPath();
        LABEL_ID = response.get("id");
        Assert.assertEquals(response.get("color").toString(), Globals.LABEL_COLOR);
        Assert.assertEquals(response.get("name").toString(), Globals.LABEL_NAME);
    }

    @AfterTest
    public void deleteLabel(){
        DeleteALabelTest deleteALabelTest = new DeleteALabelTest();
        deleteALabelTest.LABEL_ID = LABEL_ID;
        deleteALabelTest.deleteALabelTest();
    }
}
