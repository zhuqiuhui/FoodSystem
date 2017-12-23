package com.ustb.food.tool;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 * 获得属于调料，糖，油脂，软饮料这些的原料名称
 * */
public class DelTiaoLiao{
	public List deleteTiaoLiao(List allList){
		try {
			com.mysql.jdbc.Connection connection = new ConnectionFactory().getConnection();
			Statement statement = connection.createStatement();
			String sql="select mName from yuanliao where type='调味品类' or type='油脂类' or type='糖、蜜饯类' or type='软饮料'";
			ArrayList tiaoliaolist = new ArrayList();
			//将所有出现的原料全部放入list中
			ResultSet set = statement.executeQuery(sql);
			while(set.next()){
				tiaoliaolist.add(set.getString(1));
			}
			System.out.println("allList="+allList);
			System.out.println("tiaoliao="+tiaoliaolist);
			allList.removeAll(tiaoliaolist);
		}catch(Exception e){
			e.printStackTrace();
		}
		return allList;
	}
	public static void main(String[] args) {
	}
}
