package Phase_3;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class Get_List_of_Users {

    private static ResponseSpecification responseSpec;
    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createResponseSpecification() {

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

    }

    @Test
    public void validateListofUsers() {

        Response response = given().
                when().
                get("https://reqres.in/api/users?page=2").
                then().
                spec(responseSpec).
                and().
                log().all().
                extract().response();


        JsonPath jsonpathEvaluator = response.jsonPath();
        SoftAssert test = new SoftAssert();

        test.assertEquals((int) jsonpathEvaluator.get("page"), 2);
        test.assertEquals((int) jsonpathEvaluator.get("per_page"), 6);
        test.assertEquals((int) jsonpathEvaluator.get("total"), 12);
        test.assertEquals((int) jsonpathEvaluator.get("total_pages"), 2);
        test.assertEquals((int) jsonpathEvaluator.get("data[5].id"), 12);
        test.assertEquals(jsonpathEvaluator.get("data[5].email"), "rachel.howell@reqres.in");
        test.assertEquals(jsonpathEvaluator.get("data[5].first_name"), "Rachel");
        test.assertEquals(jsonpathEvaluator.get("data[5].last_name"), "Howell");
        test.assertEquals(jsonpathEvaluator.get("data[5].avatar"), "https://reqres.in/img/faces/12-image.jpg");
        test.assertAll();


    }


    /*
    @Test(priority = 1)
    public void Validate_Response()
    {
        given().get("https://reqres.in/api/users?page=2").then().assertThat().statusCode(200);
    }

    @Test(priority = 2)
    public void Content_Type_JSON()
    {
        given().get("https://reqres.in/api/users?page=2").then().assertThat().contentType(ContentType.JSON);

    }

    @Test(priority = 3)
    public void Response_Details()
    {
        given().log().all().when().get("https://reqres.in/api/users?page=2").then().log().body();

    }

        @Test(priority = 4)
        public void Check_PageNo_ResponseBody()

        {
        given().get("https://reqres.in/api/users?page=2").then().assertThat().body("page",equalTo(2));

        }
        @Test(priority = 5)
        public void Check_No_of_users_PerPage_ResponseBody()

        {
        given().get("https://reqres.in/api/users?page=2").then().assertThat().body("per_page",equalTo(6));

        }
        @Test(priority = 6)
        public void Check_Total_NoOfUsers_ResponseBody()

        {
        given().get("https://reqres.in/api/users?page=2").then().assertThat().body("total",equalTo(12));

        }
        @Test(priority = 7)
        public void Check_Total_NoOfPages_ResponseBody()
        {
        given().get("https://reqres.in/api/users?page=2").then().assertThat().body("total_pages",equalTo(2));

        }



        @Test(priority = 8)
        public void Check_Id_in_Random_User_ResponseBody()
        {
            given().get("https://reqres.in/api/users?page=2").then().assertThat().body("data[5].'id'",equalTo(12));

        }


        @Test(priority = 9)
        public void Check_email_in_ResponseBody()
        {
            given().get("https://reqres.in/api/users?page=2").then().assertThat().body("data[5].'email'",equalTo("rachel.howell@reqres.in"));

        }

        @Test(priority = 10)
        public void Check_First_Name_in_ResponseBody()
        {
            given().get("https://reqres.in/api/users?page=2").then().assertThat().body("data[5].'first_name'",equalTo("Rachel"));

        }

        @Test(priority = 11)
        public void Check_Last_Name_in_ResponseBody()
        {
            given().get("https://reqres.in/api/users?page=2").then().assertThat().body("data[5].'last_name'",equalTo("Howell"));

        }

        @Test(priority = 12)
        public void Check_avatar_in_ResponseBody()
        {
            given().get("https://reqres.in/api/users?page=2").then().assertThat().body("data[5].'avatar'",equalTo("https://reqres.in/img/faces/12-image.jpg"));

        }

        @Test(priority = 13)
        public void Check_Support_URL_in_ResponseBody()
        {
            given().get("https://reqres.in/api/users?page=2").then().assertThat().body("support.'url'",equalTo("https://reqres.in/#support-heading"));

        }*/


}
