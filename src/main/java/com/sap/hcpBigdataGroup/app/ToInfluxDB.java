package com.sap.hcpBigdataGroup.app;

/*
 * Writing data to infuxDB
 */

import com.db.influxdb.Configuration;
import com.db.influxdb.DataWriter;

public class ToInfluxDB {
	
	private DataWriter writer;
	
	public ToInfluxDB (String host, String port, String user, String pass, String db_name) throws Exception {
		Configuration configuration = new Configuration(host, port, user, pass, db_name);
		this.writer = new DataWriter(configuration);
	}
	
	public void dataWrite (String db_table, String city, String country, String type, int temperature, int humidity, int wind) throws Exception {
		writer.setTableName(db_table);
		writer.addTag("city", city);
		writer.addTag("country", country);
		writer.addField("type", type);
		writer.addField("temp", temperature);
		writer.addField("humidity", humidity);
		writer.addField("wind", wind);
		writer.writeData();
	}

}
