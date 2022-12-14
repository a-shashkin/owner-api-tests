package com.simbirsoft.tests;

import com.simbirsoft.config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresTests extends TestBase {

    Properties properties = new Properties();
    FileInputStream file;
    {
        try
        {
            file = new FileInputStream("./src/test/resources/config/config.properties");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createUserTest() throws Exception {

        properties.load(file);

        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("name", "morpheus");
        jsonBody.put("job", "leader");

        Response response =
                given().
                        log().all().
                        header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                                "(KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36").
                        header("token", properties.getProperty("token")).
                        contentType(ContentType.JSON).
                        body(jsonBody).
                        when().
                        post("/api/users").
                        then().
                        log().all().
                        statusCode(201).
                        extract().response();

        String name = response.path("name");
        String job = response.path("job");

        assertEquals("morpheus", name);
        assertEquals("leader", job);
    }

    @Test
    void updateUserTest() {

        Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("name", "morpheus");
        jsonBody.put("job", "zion resident");

        Response response =
                given().
                        log().all().
                        header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                                "(KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36").
                        header("token", "QpwL5tke4Pnpja7X4").
                        contentType(ContentType.JSON).
                        body(jsonBody).
                        when().
                        post("/api/users").
                        then().
                        log().all().
                        statusCode(201).
                        extract().response();

        String name = response.path("name");
        String job = response.path("job");

        assertEquals("morpheus", name);
        assertEquals("leader", job);
    }
}
