package com.balcia.ws;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNot.not;

import com.balcia.ws.color.Color;
import com.balcia.ws.fruit.Fruit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import javax.transaction.Transactional;

@QuarkusTest
public class FruitsEndpointTest {
    Fruit f1;
    Fruit f2;
    Fruit f3;

    @BeforeEach
    @Transactional
    public void beforeEach() {
        Fruit.delete("name", "Cherry");
        Fruit.delete("name", "Apple");
        Fruit.delete("name", "Banana");
        Color.delete("name", "Red");
        Color.delete("name", "Yellow");
        Color.delete("name", "Green");
        Color red = new Color();
        red.name = "Red";
        f1 = new Fruit();
        f1.name = "Cherry";
        f1.color = red;
        f1.persist();
        Color green = new Color();
        green.name = "Green";
        f2 = new Fruit();
        f2.name = "Apple";
        f2.color = green;
        f2.persist();
        Color yellow = new Color();
        yellow.name = "Yellow";
        f3 = new Fruit();
        f3.color = yellow;
        f3.name = "Banana";
        f3.persist();
    }

    @AfterEach
    @Transactional
    public void afterEach() {
        Fruit.delete("name", "Cherry");
        Fruit.delete("name", "Apple");
        Fruit.delete("name", "Banana");
        Color.delete("name", "Red");
        Color.delete("name", "Yellow");
        Color.delete("name", "Green");
    }

    @Test
    public void testDeleteFruit() {
        // Delete the Cherry:
        given().when().delete("/fruits/" + f1.id.toString()).then().statusCode(204);

        // List all, cherry should be missing now:
        given().when().get("/fruits").then().statusCode(200).body(not(containsString("Cherry")),
                containsString("Apple"), containsString("Banana"));
    }

    @Test
    public void testListAllFruits() {
        // List all, should have all 3 fruits with correct names:
        given().when().get("/fruits")
                .then().statusCode(200)
                .body("$.size()", is(3), "name",
                containsInAnyOrder("Cherry", "Apple", "Banana"));

//
//        // Create the Pear:
//        given().when().body("{\"name\" : \"Pear\"}").contentType("application/json").post("/fruits").then()
//                .statusCode(201);
//
//        // List all, cherry should be missing now:
//        given().when().get("/fruits").then().statusCode(200).body(not(containsString("Cherry")),
//                containsString("Apple"), containsString("Banana"), containsString("Pear"));
    }
}
