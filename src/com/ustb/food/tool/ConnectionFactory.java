package com.ustb.food.tool;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
/*
 * 连接工厂
 * */
public class ConnectionFactory {
	String driverClassName="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/FoodS";
	String user="root";
	String password="940801";
	public Connection getConnection() throws Exception{
		Class.forName(driverClassName);
		Connection connection = (Connection) DriverManager.getConnection(url, user, password);
		return connection;
	}
}
