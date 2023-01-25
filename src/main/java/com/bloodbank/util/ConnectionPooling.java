package com.bloodbank.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Service;

@Service
public class ConnectionPooling {

	private String url = null;
	String username = null;
	String password = null;
	String drivername = null;

	private static BasicDataSource datasource = null;

	public String propertyRead() throws IOException {

		FileInputStream f = new FileInputStream(
				"D:\\Spring Tool Workspace\\BloodBank\\src\\main\\resources\\application.properties");

		Properties p = new Properties();
		p.load(f);

		url = p.getProperty("spring.datasource.url");
		username = p.getProperty("spring.datasource.username");
		password = p.getProperty("spring.datasource.password");
		drivername = p.getProperty("spring.datasource.driverClassName");
		return url + username + password + drivername;

	}

	public BasicDataSource getConnectionDetails() {
		if (datasource != null) {
			return datasource;
		}
		datasource = new BasicDataSource();

		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(drivername);

		datasource.setMinIdle(5);
		datasource.setMaxIdle(10);
		datasource.setMaxTotal(25);

		return datasource;

	}

	public Connection connect() throws SQLException, IOException {

		if (datasource == null) {
			propertyRead();
		}

		Connection connection = null;
		try {
			datasource = getConnectionDetails();
			connection = datasource.getConnection();   
			

			System.out.println("Database Connected Succesfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return connection;
	}
}
