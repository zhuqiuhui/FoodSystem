package com.ustb.food.run;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//这个类的主要目的是查找是同一东西，但名字不一样的原料
public class Ifempty {
	int smallname;
	String className = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/foods";
	String userName = "root";
	String password = "940801";
	Connection connection;

	public Connection getConnection() throws Exception {
		Class.forName(className);
		Connection connection = DriverManager.getConnection(url, userName,
				password);
		return connection;
	}

	public int find(String mName) {
		try {
			if (connection == null) {
				connection = getConnection();
			}
			Statement statement = connection.createStatement();
			String sql = "select * from smallname where name='" + mName + "'";
			ResultSet set = statement.executeQuery(sql);
			if (set.next()) {
				// 存在记录 rs就要向上移一条记录 因为rs.next会滚动一条记录了
				set.previous();
				while (set.next()) {
					smallname = set.getInt(1);
				}
			} else {
				return -1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return smallname;
	}

}
