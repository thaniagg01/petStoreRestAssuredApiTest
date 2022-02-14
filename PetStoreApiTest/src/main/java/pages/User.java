package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class User {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    public User(){
        RestAssured.baseURI = BASE_URL;
    }
    public Response getUserByUsername(String username){
        return RestAssured.given().when().get("/user/"+username);
    }

    public Response createNewUser(Map<String,Object> body){
        return RestAssured.given().contentType("application/json")
                .body(body).when().post("/user/");
    }

    public Response deleteUserByUserName(String userName){
        return RestAssured.given().when().delete("/user/"+userName);
    }
}
