/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherfetch;

/**
 *
 * @author Tyler
 */
public class City {
    private String city;
    private double temperature;
    private double humidity;
    private String description;
    
    City(){
        
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public void setTemperature(double temp){
        this.temperature = temp;
    }
    
    public void setHumidity(double h){
        this.humidity = h;
    }
    
    public void setDescription(String desc){
        this.description = desc;
    }
    
    public String getCity(){
        return city;
    }
    
    public double getTemperature(){
        return temperature;
    }
    
    public double getHumidity(){
        return humidity;
    }
    
    public String getDescription(){
        return description;
    }
}
