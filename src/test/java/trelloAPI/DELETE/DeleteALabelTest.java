package trelloAPI.DELETE;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import trelloAPI.POST.CreateALabelTest;
import trelloAPI.POST.CreateNewCardTest;
import trelloAPI.Specifications;

import static io.restassured.RestAssured.given;

public class DeleteALabelTest {
    public String LABEL_ID;

    @BeforeTest
    public void createLabel() {
        CreateALabelTest createALabelTest = new CreateALabelTest();
        createALabelTest.createALabelTest();
        LABEL_ID = createALabelTest.LABEL_ID;
    }

    @Test
    public void deleteALabelTest(){
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        given()
                .header("Accept", "application/json")
                .when()
                .delete("/1/labels/{id}", LABEL_ID)
                .then().log().all()
                .extract().jsonPath();
    }
}
