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

public class PositivCheckUserSeed {

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

    @DataProvider(name = "resultSeed")
    public Object [] [] positiveData () {
        return new Object [] [] {
                {"seed", "foobar"},
        };
    }


    @Test(dataProvider = "resultSeed")
    public void checkUser(String seed, String date) throws JsonProcessingException {
        UserInfo user = given()
                .spec(RestAssured.requestSpecification)
                .contentType(ContentType.JSON)
                .param(seed, date)
                .when()
                .get("/api/")
                .then()
                .statusCode(200)
                .extract()
                .as(UserInfo.class);

        Assert.assertEquals(user.getInfo().getSeed(), date, "Данный пользователь не"+ date);
    }
}
