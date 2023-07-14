package org.example.apitests;



import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class FirstAPITest {

    @Test
    public void validate_get_request(){
        Response response = RestAssured.get("https://reqres.in/api/users?page=1");
        System.out.println(response.statusCode());
        System.out.println(response.asString());

        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode, 200);
    }

    @Test
    public void bdd_validate_api(){
        given().get("https://reqres.in/api/users?page=1").then()
                .statusCode(200).body("data[0].id", equalTo(1));
    }

    @Test
    public void post_create_users(){
        JSONObject request = new JSONObject();
        request.put("name","Rupak");
        request.put("job","QA");

        System.out.println(request);

        given().header("Content-Type","application/json")
                .body(request)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("job", equalTo("QA"));
    }

    @Test
    public void test_auth(){
        given().auth().basic("admin","admin").get("https://the-internet.herokuapp.com/basic_auth").then().statusCode(200);

    }

    @Test
    public void test_query_param(){
        given()
                .queryParam("CUSTOMER_ID","68195")
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1")
                .when().get("").then().statusCode(200);

    }
    @Test
    public void Test_android(){
        given().get("https://generator.swagger.io/api/gen/clients").then()
                .statusCode(200).body("[2]",equalTo("android"));
             //   body(containsString("android"));
    }


    @Test
    public void Count_Alpha(){
        String word = "ThIsWoRddd";
        int total_lower= word.replaceAll("[A-Z]", "").length();
        System.out.println("Total Lower: " + total_lower);



    }


}