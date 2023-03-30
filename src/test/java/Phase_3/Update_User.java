package Phase_3;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class Update_User {


    public static String id;
    private static ResponseSpecification responseSpec;
    @BeforeClass
    private static void createResponseSpecification()
    {

        responseSpec=new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).build();
         id =Create_User.Post();

    }

    @Test()
    public void update() {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject request = new JSONObject();
        request.put("name", "Fady");
        request.put("job", "Teacher");
        baseURI = "https://reqres.in/api";

        Response  response = given().contentType(ContentType.JSON).
               body(request.toJSONString())
               .when().put("/users/{id}",id)
               .then().
                spec(responseSpec)
               .log().all()
               .extract().response();
       JsonPath jsonPathEvaluator = response.jsonPath();
        SoftAssert test=new SoftAssert();
        test.assertEquals(jsonPathEvaluator.get("name"),"Fady");
        test.assertEquals(jsonPathEvaluator.get("job"),"Teacher");
        test.assertAll();



    }
}
