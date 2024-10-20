package api.WeatherApi;

import api.methods.WeatherApiMethods;
import api.pojos.response.CityWeatherAPIResponseDTO;
import common.utils.ExtentReportUtil;
import api.utils.RestAssuredUtility;
import common.utils.TestUtility;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class WeatherApiTests extends RestAssuredUtility {
    WeatherApiMethods weatherApiMethods = new WeatherApiMethods();
    @Test
    public void validateCityWeatherApi() {
        Response response = null;
        boolean status = false;

        String stepName = "Perform city weather api Get call";
        response = weatherApiMethods.performCityWeatherApi("London");
        status = weatherApiMethods.validateSuccessStatusCode(response);
        ExtentReportUtil.updateReportInfo(status, stepName);

        CityWeatherAPIResponseDTO cityWeatherAPIResponseDTO = responseToPojo(response,CityWeatherAPIResponseDTO.class);
        stepName = "Validate City Name in Response";
        status = TestUtility.validateValueEquals.test(cityWeatherAPIResponseDTO.getName(),"London");
        ExtentReportUtil.updateReportInfo(status,stepName);

        stepName = "Validate Presence Of Lat - Long of city";
        status = TestUtility.validateNotBlankDouble.test(cityWeatherAPIResponseDTO.getCoord().getLat()) && TestUtility.validateNotBlankDouble.test(cityWeatherAPIResponseDTO.getCoord().getLon());
        ExtentReportUtil.updateReportInfo(status,stepName);
    }
}
