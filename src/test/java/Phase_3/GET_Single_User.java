package Phase_3;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class GET_Single_User {


@Test
public void  validatesSingleUser()
{
    baseURI="https://reqres.in/api";
    Response response=
            given().
            get("/users/2").
            then().
            assertThat().
            contentType(ContentType.JSON).
            statusCode(200).
            log().all().
            extract().response();
    JsonPath jsonPathEvaluator=response.jsonPath();
    SoftAssert test=new SoftAssert();

    test.assertEquals(jsonPathEvaluator.get("data.first_name"),"Janet");
    test.assertEquals(jsonPathEvaluator.get("data.last_name"),"Weaver");
    test.assertEquals((int)jsonPathEvaluator.get("data.id"),2);
    test.assertEquals(jsonPathEvaluator.get("data.email"),"janet.weaver@reqres.in");
    test.assertEquals(jsonPathEvaluator.get("data.avatar"),"https://reqres.in/img/faces/2-image.jpg");
    test.assertEquals(jsonPathEvaluator.get("support.url"),"https://reqres.in/#support-heading");
    test.assertAll();

}

  /*  @Test(priority = 1)
    public void Validate_Response()
    {
        given().get("https://reqres.in/api/users/2").then().assertThat().statusCode(200);
    }

    @Test(priority = 2)
    public void Content_Type_JSON()
    {
        given().get("https://reqres.in/api/users/2").then().assertThat().contentType(ContentType.JSON);

    }

    @Test(priority = 3)
    public void Response_Details()
    {
        given().log().all().when().get("https://reqres.in/api/users/2").then().log().body();

    }


    @Test(priority = 4)
    public void Check_Id_in_ResponseBody()
    {
        given().get("https://reqres.in/api/users/2").then().assertThat().body("data.'id'",equalTo(2));

    }


    @Test(priority = 5)
    public void Check_email_in_ResponseBody()
    {
        given().get("https://reqres.in/api/users/2").then().assertThat().body("data.'email'",equalTo("janet.weaver@reqres.in"));

    }

    @Test(priority = 6)
    public void Check_First_Name_in_ResponseBody()
    {
        given().get("https://reqres.in/api/users/2").then().assertThat().body("data.'first_name'",equalTo("Janet"));

    }

    @Test(priority = 7)
    public void Check_Last_Name_in_ResponseBody()
    {
        given().get("https://reqres.in/api/users/2").then().assertThat().body("data.'last_name'",equalTo("Weaver"));

    }
    @Test(priority = 8)
    public void Check_avatar_in_ResponseBody()
    {
        given().get("https://reqres.in/api/users/2").then().assertThat().body("data.'avatar'",equalTo("https://reqres.in/img/faces/2-image.jpg"));

    }

    @Test(priority = 9)
    public void Check_Support_URL_in_ResponseBody()
    {
        given().get("https://reqres.in/api/users/2").then().assertThat().body("support.'url'",equalTo("https://reqres.in/#support-heading"));

    }
*/




}
