package Phase_3;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class Delete_User {

    private static ResponseSpecification responseSpec;
    public static String id;

    @BeforeClass
    private static void createResponseSpecification()
    {
       responseSpec=new ResponseSpecBuilder().
               expectStatusCode(204).build();
       id=Create_User.Post();
    }


    @Test()
    public void delete() {





        baseURI = "https://reqres.in/api";

        given().when().delete("/users/{id}",id)
                .then().
                 spec(responseSpec)
                .log().all();





    }
}
