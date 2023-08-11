package api;

import api.model.UserInfo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class NegativeTestCheckUsers {

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

    @DataProvider(name = "resultCount")
    public Object [] [] negativeData () {
        return new Object [] [] {
                {"results", "-1"},
                {"results", "0"},
                {"results", "-10"},
        };
    }


    @Test(dataProvider = "resultCount")
    public void checkUsers(String result, String date) throws IOException {

        UserInfo res = given()
                .spec(RestAssured.requestSpecification)
                .contentType(ContentType.JSON)
                .param(result, date)
                .when()
                .get("/api/")
                .then()
                .statusCode(404)
                .extract()
                .as(UserInfo.class);
    }

}
