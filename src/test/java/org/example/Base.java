package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Base {

    @Before
    public void setup(){
        RestAssured.baseURI = "https://api.todoist.com";
        RestAssured.basePath = "/rest/v1/";

        RestAssured.requestSpecification =
                RestAssured
                        .given()
                        .header("Authorization", "Bearer 6e0af658835382fa334b51863752c64b07dcc204")
                        .contentType(ContentType.JSON);

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
