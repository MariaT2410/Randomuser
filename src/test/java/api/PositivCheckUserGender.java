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
import api.BaseTest;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PositivCheckUserGender {


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
                {"gender", Gender.MALE.getCode()},
                {"gender", Gender.FEMALE.getCode()},
        };
    }


    @Test(dataProvider = "resultGender")
    public void checkUser(String gender, String date) throws JsonProcessingException {
        Response response = given()
                .spec(RestAssured.requestSpecification)
                .contentType(ContentType.JSON)
                .param(gender, date)
                .when()
                .get("/api/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        Assert.assertNotNull(response);

        String js = response.asString();
        ObjectMapper mapper = new ObjectMapper();
        UserInfo u = mapper.readValue(js, UserInfo.class);

        List<Result> a = u.getResults();
        for(Result result:a){
            Assert.assertEquals(result.getGender().getCode(), date, "Данный чеовек не"+ date);
        }

    }
}
