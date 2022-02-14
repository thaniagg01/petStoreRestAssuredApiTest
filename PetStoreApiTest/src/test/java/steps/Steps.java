package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.Pets;
import pages.Store;
import pages.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Steps {
    private static Response response;
    private static String jsonString;
    private static String index;
    private Pets pets;
    private String petId;
    private Store store;
    private User user;

    @Given("I have pets in the petstore")
    public void petsInThePetstore(){
        pets=new Pets();
        response = pets.getAllPets();
        jsonString = response.asString();
        response.then().statusCode(200);
        List<Integer> petsListId = JsonPath.from(jsonString).get("id");
        int size = petsListId.size();
        Assert.assertTrue(size > 0);

    }

    @When("I add a new Pet to my petStore")
    public void i_add_a_new_pet_to_my_pet_store() {
        //index+=1;
        Map<String,Object> body = new HashMap<String,Object>();
        //body.put("id",index);
        body.put("name", "bucky");
        body.put("status", "available");

        response =pets.addNewPet(body);
        jsonString=response.asString();
        Long I  = JsonPath.from(jsonString).get("id");
        index = I+"";
        System.out.println("Add new pet");
        System.out.println(index);
        response.then().statusCode(200);

    }
    @Then("The pet is added")
    public void the_pet_is_added() {
        System.out.println("added");
        System.out.println(index);
        response= pets.getPetById(index);
        System.out.println(response.asString());
        response.then().statusCode(200);
    }

    @And("I remove the pet from the store")
    public void remove_pet_from_store() {
        System.out.println("Delete");
        response = pets.deletePetById(index);
        System.out.println(response.asString());
        response.then().statusCode(200);

    }

    @Given("I have a pet in the store")
    public void i_want_a_pet_from_the_store() {
        pets=new Pets();
        response = pets.getPetsAvailable();
        jsonString = response.asString();
        response.then().statusCode(200);
        List<Integer> petsListId = JsonPath.from(jsonString).get("id");
        int size = petsListId.size();
        Assert.assertTrue(size > 0);
        petId=petsListId.get(1)+"";

    }
    @When("I place the order for the pet")
    public void i_place_the_order_for_the_pet() {
        Map<String,Object> body = new HashMap<String,Object>();
        body.put("petId", petId);
        body.put("quantity", 1);
        body.put("shipDate", "2022-02-14T17:26:45.189Z");
        body.put("status", "placed");
        body.put("complete", true);

        store= new Store();
        response=store.addNewOrder(body);
        jsonString=response.asString();
        System.out.println(jsonString);
        Long I  = JsonPath.from(jsonString).get("id");
        index = I+"";
        boolean complete =  JsonPath.from(jsonString).get("complete");
        response.then().statusCode(200);
        Assert.assertTrue(complete);

    }
    @Then("I expect the order is added")
    public void i_expect_the_order_is_added() {
        System.out.println("added order");
        int orderId = 5; ////mocking order created according to swagger only valid response are integer between >= 1 and <= 10
        response= store.getOrderById(orderId);
        System.out.println(response.asString());
        boolean complete =  JsonPath.from(jsonString).get("complete");
        response.then().statusCode(200);
        Assert.assertTrue(complete);
    }
    @Then("I delete the order")
    public void i_delete_the_order() {
        System.out.println("Delete");
        System.out.println(index);
        response = store.deleteOrderById(index);
        System.out.println(response.asString());
        response.then().statusCode(200);
    }

    @When("I update the pet")
    public void i_update_the_pet() {
        Map<String,Object> body = new HashMap<String,Object>();
        body.put("id",petId);
        body.put("name", "kratos");
        body.put("status", "available");

        response =pets.updatePet(body);
        jsonString=response.asString();
        Long I  = JsonPath.from(jsonString).get("id");
        index = I+"";
        System.out.println("update pet");
        System.out.println(index);
        response.then().statusCode(200);
    }
}
