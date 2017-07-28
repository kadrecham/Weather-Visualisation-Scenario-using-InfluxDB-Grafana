package com.sap.hcpBigdataGroup.app;

/**
 * Weather Visualisation Scenario using InfluxDB and Grafana!
 *
 */

public class App {
	
    public static void main( String[] args ) throws Exception{
 
        while (true){
            
            //Requesting for weather and getting the response
			Weather frankfurt_weather = new Weather("Frankfurt");
			Weather wiesloch_weather = new Weather("Wiesloch");
			
            //Writing data to infuxDB
			ToInfluxDB toInfluxDB = new ToInfluxDB("localhost", "8086", "root", "root", "local_temp");
			toInfluxDB.dataWrite("weather", frankfurt_weather.getCity(), frankfurt_weather.getCountry(), frankfurt_weather.getWeather(), frankfurt_weather.getTemperature(), frankfurt_weather.getHumidity(), frankfurt_weather.getWind());
			toInfluxDB.dataWrite("weather", wiesloch_weather.getCity(), wiesloch_weather.getCountry(), wiesloch_weather.getWeather(), wiesloch_weather.getTemperature(), wiesloch_weather.getHumidity(), wiesloch_weather.getWind());
			
			//Every 10 min
			Thread.sleep(600000);
		}
    }
}
