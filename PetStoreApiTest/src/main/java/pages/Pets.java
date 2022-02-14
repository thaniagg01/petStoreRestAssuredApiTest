package pages;


import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class Pets {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    public Pets(){
        RestAssured.baseURI = BASE_URL;
    }
    /**
     * Method to get all the pets
     * @return response
     */
    public Response getAllPets(){
        String [] status=new String[] {"available","pending","sold"};
        return RestAssured.given().when().queryParam("status",status).get("/pet/findByStatus/");
    }

    public Response getPetsAvailable(){
        String [] status=new String[] {"available"};
        return RestAssured.given().when().queryParam("status",status).get("/pet/findByStatus/");
    }

    /**
     * Method to add a new pet
     * @param body pet that is going to be add
     * @return
     */
    public Response addNewPet(Map<String,Object>body){
        return RestAssured.given().contentType("application/json")
                .body(body).when().post("/pet/");
    }

    /**
     * Method to get a pet by Id
     * @param id id of the pet
     * @return
     */
    public Response getPetById(String id){
        return RestAssured.given().when().get("/pet/"+id);
    }

    public Response deletePetById(String id){
       return RestAssured.given().when().delete("/pet/"+id);
    }

    public Response updatePet(Map<String,Object>body){
        return RestAssured.given().contentType("application/json")
                .body(body).when().put("/pet/");
    }
}
