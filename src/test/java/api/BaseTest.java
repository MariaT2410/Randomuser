package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.model.UserInfo;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeClass
    public void prepare() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("my.properties"));

        String baseUri = System.getProperty("base.uri");
        if (baseUri == null || baseUri.isEmpty()) {
            throw new RuntimeException("В файле \"my.properties\" отсутствует значение \"base.uri\"");
        }

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://randomuser.me/")
                .addHeader("base.uri", System.getProperty("base.uri"))
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.filters(new ResponseLoggingFilter());
    }

    @Test
    public void checkDocumentation(){
        Response response = given()
                .spec(RestAssured.requestSpecification)
                .when()
                .get("/api/")
                .then()
                .statusCode(200)
                .extract()
                .response();
        response.jsonPath().get();
        Assert.assertNotNull(response);
    }

    @Test
    public void checkUsers() throws IOException {

        UserInfo res = given()
                .spec(RestAssured.requestSpecification)
                .contentType(ContentType.JSON)
                .param("results", 20)
                .when()
                .get("/api/")
                .then()
                .statusCode(200)
                .extract()
                .as(UserInfo.class);

            Assert.assertEquals(res.getResults().size(), 20, "Количество results не 20");
            System.out.println(res.getResults().size());
    }
}
