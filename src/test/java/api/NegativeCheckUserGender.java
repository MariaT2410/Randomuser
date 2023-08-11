package api;

import api.model.Gender;
import api.model.Result;
import api.model.UserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class NegativeCheckUserGender {
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

    @DataProvider(name = "resultGender")
    public Object [] [] positiveData () {
        return new Object [] [] {
                {"gender", "34"},
                {"gender", "yh"},
                {"gender", "/jgn"},

        };
    }


    @Test(dataProvider = "resultGender")
    public void checkUser(String gender, String date) {
        //TODO так как нет документации предполагаю должна быть ошибка 400 или 422
        Response response = given()
                .spec(RestAssured.requestSpecification)
                .contentType(ContentType.JSON)
                .param(gender, date)
                .when()
                .get("/api/")
                .then()
                .statusCode(400)
                .extract()
                .response();

        Assert.assertNotNull(response);
    }
}
