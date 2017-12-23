package com.ustb.food.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * 依据其他表的某一列对数据库中某一个表增加一列(注：需要改某些地方)
 * */
//主要包括两部分构成，没注释的部分是依据有了bys,sjwl列的test(name,bys,sjwl)表查询出来更新caipu表
public class Modify_Database {
	public static void main(String[] args) {
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/FoodS";
		String user = "root";
		String password = "940801";
		String new_value = "";
		String value;
		try {
			Class.forName(driverClassName);
			Connection connection = DriverManager.getConnection(url, user,
					password);
			Statement statement = connection.createStatement();
			// 获得原表全部数据
			// String sql = "select * from zhongyi_";
			String sql = "select * from test";
			ResultSet set = statement.executeQuery(sql);
			while (set.next()) {
				String name = set.getString(1);
				int bys = set.getInt(2);
				double sjwl = set.getDouble(3);
				System.out.println("bys" + bys + "sjwl" + sjwl);
				String sql2 = "update caipu set gForTnb_bys=" + bys
						+ ",gForTnb_sjwl=" + sjwl + " where viewName='" + name
						+ "'";
				Statement statement3 = connection.createStatement();
				statement3.execute(sql2);
				System.out.println("更新成");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
