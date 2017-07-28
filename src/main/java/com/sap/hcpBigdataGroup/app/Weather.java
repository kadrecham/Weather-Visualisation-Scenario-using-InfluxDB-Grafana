package com.sap.hcpBigdataGroup.app;

/*
 * Requesting for weather and getting the response then
 * Fetching data from the response using JSON object (You can fetch more features!)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Weather {
	
	private JSONObject jsonObj;
	
	public Weather (String city) throws IOException {
	
		URL obj = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city +",de&APPID=99174197b1cc56b0e044f0180b76ad92");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		StringBuffer response = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) 
			response.append(inputLine);
		in.close();
		this.jsonObj = new JSONObject(response.toString());
	}
	
	public JSONObject getAllJSONObject() {
		return this.jsonObj;
	}
	
	public String getCity() {
		return this.jsonObj.getString("name").replaceAll("\\s+", "-");
	}
	
	public String getCountry() {
		JSONObject subObject = this.jsonObj.getJSONObject("sys");
		return subObject.getString("country");
	}
	
	public String getWeather() {
		JSONArray jsonArray = this.jsonObj.getJSONArray("weather");
		JSONObject subObject = jsonArray.getJSONObject(0);
		return subObject.getString("main");
	}
	
	public int getTemperature() {
		JSONObject subObject = this.jsonObj.getJSONObject("main");
		return Math.round(Math.round(subObject.getDouble("temp") - 273.15));
	}
	
	public int getHumidity() {
		JSONObject subObject = this.jsonObj.getJSONObject("main");
		return Math.round(Math.round(subObject.getDouble("humidity")));
	}
	
	public int getWind() {
		JSONObject subObject = this.jsonObj.getJSONObject("wind");
		return Math.round(Math.round(subObject.getDouble("speed")));
	}
	
}
