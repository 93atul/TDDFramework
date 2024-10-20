package api.pojos.response;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CityWeatherAPIResponseDTO {
    public Coord coord;
    public ArrayList<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public int dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;
    @Data
    public class Clouds{
        public int all;
    }
    @Data
    public class Coord{
        public double lon;
        public double lat;
    }
    @Data
    public class Main{
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;
    }
    @Data
    public class Sys{
        public int type;
        public int id;
        public String country;
        public int sunrise;
        public int sunset;
    }
    @Data
    public class Weather{
        public int id;
        public String main;
        public String description;
        public String icon;
    }
    @Data
    public class Wind{
        public double speed;
        public int deg;
    }
}