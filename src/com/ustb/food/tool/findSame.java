package com.ustb.food.tool;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
/*
 * 通过一个原料的名字来获得所有属于这个原料名字下的所有子原料
 * */
public class findSame {
	public List findSameId(int typeId) throws Exception{
		String name = null;
		Connection connection = new ConnectionFactory().getConnection();
		Statement statement2 = connection.createStatement();
		String sql1="select mName from yuanliao where maId="+typeId+"";
		ResultSet set2 = statement2.executeQuery(sql1);
		while(set2.next()){
			name=set2.getString(1);
		}
		System.out.println(name);
		Statement statement = connection.createStatement();
		String sql="select maId from sametype where typeName='"+name+"'";
		ResultSet set = statement.executeQuery(sql);
		//list用来存放属于同一类的原料
		ArrayList list = new ArrayList();
		while(set.next()){
			int maId = set.getInt(1);
			list.add(maId);
		}
		return list;
	}
	public String name(int typeId) throws Exception{
		String name = null;
		Connection connection = new ConnectionFactory().getConnection();
		Statement statement2 = connection.createStatement();
		String sql1="select mName from yuanliao where maId="+typeId+"";
		ResultSet set2 = statement2.executeQuery(sql1);
		while(set2.next()){
			name=set2.getString(1);
		}
		return name;
	}
	public static void main(String[] args) throws Exception {
		List list = new findSame().findSameId(513);
		System.out.println(list.size());
	}
}
