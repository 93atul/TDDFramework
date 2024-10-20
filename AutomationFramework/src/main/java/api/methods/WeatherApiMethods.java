package api.methods;

import api.utils.RestAssuredUtility;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class WeatherApiMethods extends RestAssuredUtility {
    Response response = null;
    public Response performCityWeatherApi(String city) {
        Map<String,String> params = new HashMap<>();
        params.put("q",city);
        params.put("appid","a76f33c8c59e39af1a1a82fb0ad998c7");
        response = performGetCall("/data/2.5/weather",params);
        return response;
    }
    public boolean validateSuccessStatusCode(Response response) {
        return response.getStatusCode() == 200;
    }
    public boolean validateUnauthorisedStatusCode(Response response){
        return response.getStatusCode() == 401;
    }

}