package pages;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class Store {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    public Store(){
        RestAssured.baseURI = BASE_URL;
    }

    public Response addNewOrder(Map<String,Object> body){
        return RestAssured.given().contentType("application/json")
                .body(body).when().post("/store/order");
    }

    public Response getOrderById(int id){
        //mocking order created according to swagger only valid response are integer between >= 1 and <= 10
        return RestAssured.given().when().get("/store/order/"+id);
    }

    public Response deleteOrderById(String id){
        return RestAssured.given().when().delete("/store/order/"+id);
    }
}
