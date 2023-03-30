package Phase_3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.Constants;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class Create_User {


    @Test
    public static String Post() {

        JSONObject request = new JSONObject();

        request.put("name", "Fady");
        request.put("job", "Engineer");
        baseURI = "https://reqres.in/api";
        Response response =
                given().contentType(ContentType.JSON).
                        body(request.toJSONString())
                        .when().post("/users")
                        .then().assertThat().statusCode(201)
                        .log().all().extract().response();
        JsonPath jsonPathEvaluator = response.jsonPath();
        Constants.userId = jsonPathEvaluator.get("id");

        SoftAssert test=new SoftAssert();

        test.assertEquals(jsonPathEvaluator.get("name"),"Fady");
        test.assertEquals(jsonPathEvaluator.get("job"),"Engineer");
        test.assertAll();


        System.out.println("returned user ID is:" + Constants.userId);
        return Constants.userId;








    }


}
