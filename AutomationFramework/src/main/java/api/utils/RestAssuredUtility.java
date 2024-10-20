package api.utils;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredUtility {
    ThreadLocal<RequestSpecification> requestSpecification=new ThreadLocal<>();
    public RestAssuredUtility() {
        requestSpecification.set(new RequestSpecBuilder().setBaseUri("https://api.openweathermap.org").setContentType(ContentType.JSON).build());
    }
    public RequestSpecification getRequestSpecification() {
        return requestSpecification.get();
    }
    public Response performGetCall(String url, Map<String,String> pathParams) {
        Response response = null;
        try {
            RequestSpecification rest = RestAssured.given().spec(getRequestSpecification()).basePath(url).queryParams(pathParams);
            response = rest.get();
        }catch(Exception ex){
            System.out.println("Unable to Call api "+url+" . Exception : "+ex.getMessage());
            throw ex;
        }
        return response;
    }
    public <T>T responseToPojo(Response response, Class<T> pojoClass){
        Gson gson = new Gson();
        return gson.fromJson(response.getBody().asString(),pojoClass);
    }
}