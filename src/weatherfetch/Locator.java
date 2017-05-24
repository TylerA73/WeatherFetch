/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherfetch;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Tyler
 */
public class Locator {
    
    private final String URL = "http://freegeoip.net/json/";
    private String city;
    private String country;
    private String latitude;
    private String longitude;
    
    
    
    Locator() throws IOException{
        
        JsonParser parser = new JsonParser();
        
        
        try{
            
            URL ipapi = new URL(URL);

            URLConnection conn = ipapi.openConnection();
            conn.setRequestProperty("User-Agent", "java-ipapi-client");
            InputStreamReader isr = new InputStreamReader(conn.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            
            String input = reader.readLine();
            JsonObject jo = (JsonObject) parser.parse(input);
            reader.close();
            
            setCity(jo.get("city").toString());
            setCountry(jo.get("country_code").toString());
            setLatitude(jo.get("latitude").toString());
            setLongitude(jo.get("longitude").toString());
            
            
            
            
        }catch(IOException ioe){
            
        }
        
        
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public void setCountry(String country){
        this.country = country;
    }
    
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }
    
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getCountry(){
       return country;
    }
    
    public String getLatitude(){
        return latitude;
    }
    
    public String getLongitude(){
        return longitude;
    }
    
}
