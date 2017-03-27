/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherfetch;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Fetches Weather data from Open Weather Map for a City.
 *
 * @author Tyler
 */
public class WeatherFetcher {
    final String OWM_API_URL;
    final String OWM_CITY;
    final String OWM_API_KEY;
    
    /**
     * Constructor for the WeatherFetcher. Requires a city name and country code.
     * Example: City city = new City("London", "uk");
     * 
     * @param city City to find the weather data for.
     */
    WeatherFetcher(String city, String countryCode){
        OWM_API_URL = "http://api.openweathermap.org/data/2.5/weather?units=metric";
        OWM_CITY = "&q=" + city + "," + countryCode;
        OWM_API_KEY = "&APPID=097a7b18975da0266749baee01c311ba";
        
    }
    
    /**
     * Builds a string based on the JSON returned by the API.
     * 
     * @param rd Reader
     * @return String
     * @throws IOException 
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    
    /**
     * Gets the data from the Open Weather Map API to store in a City class.
     * 
     * @return city City containing the relevant weather information.
     * @throws MalformedURLException
     * @throws IOException 
     */
    public City getData() throws MalformedURLException, IOException{
        City city = new City();
        JsonParser parser = new JsonParser();
        try{
            URL url = new URL(OWM_API_URL + OWM_CITY + OWM_API_KEY);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String input;
            while((input = br.readLine()) != null){
                JsonObject jo = (JsonObject)parser.parse(input);
                city.setCity(jo.get("name").toString());
                
                JsonObject main = jo.getAsJsonObject("main");
                city.setTemperature(main.get("temp").getAsDouble());
                city.setHumidity(main.get("humidity").getAsDouble());
                
                String weather = jo.getAsJsonArray("weather").toString();
                
                String[] weatherArray = weather.split(",");
                String desc = new String();
                for(int i = 0; i < weatherArray.length; i++){
                    if(weatherArray[i].contains("description")){
                        desc = weatherArray[i];
                        break;
                    }
                }
                
                city.setDescription(desc.split(":")[1]);
                
                
                
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        return city;
    }
    
    /**
     * Runs to test that the fetcher successfully collects data from the API
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException{
        WeatherFetcher wf = new WeatherFetcher("edmonton", "ca");
        City city = wf.getData();
        
        System.out.println(city.getCity());
        System.out.println(city.getTemperature());
        System.out.println(city.getDescription());
        System.out.println(city.getHumidity());
        
    }
    
    
    
}
