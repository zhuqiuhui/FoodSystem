package com.ustb.food.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;


import com.ustb.food.dao.impl.BaseDaoImpl;
import com.ustb.food.dao.YuanliaoDao;
import com.ustb.food.entity.Yuanliao;

@Component("yuanliaoDao")
public class YuanliaolDaoImpl  extends BaseDaoImpl<Yuanliao, Integer> implements YuanliaoDao {
	String className="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/foods";
	String userName="root";
	String password="940801";
	Connection connection;
	public Connection getConnection() throws Exception{
		Class.forName(className);
		Connection connection = DriverManager.getConnection(url, userName, password);
		return connection;
	}
	
	@Override
	public List<Yuanliao> findSame(String mName) {
		// TODO Auto-generated method stub
//		String hql = "from Yuanliao where mName like '%"+mName+"%'";
		String hql = "from Yuanliao where mName='"+mName+"'";
		return list(hql);
			/*ArrayList<Yuanliao> list = new ArrayList<Yuanliao>();
			try {
				if(connection==null){
					connection = getConnection();
				}
				Statement statement = connection.createStatement();
				String sql="select * from yuanliao where mName='"+mName+"'";
				ResultSet set = statement.executeQuery(sql);
				while(set.next()){
				Yuanliao yuanliao = new  Yuanliao();
				yuanliao.setMaId(set.getInt(1));
				yuanliao.setmName(set.getString(2));
				list.add(yuanliao);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{		
				if(connection!= null)
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			}
			return list;*/
		}

	@Override
	public List<Yuanliao> findByName(String mName) {
		String hql = "from Yuanliao where mName like '%"+mName+"%'";
		return list(hql, 0, 0);
	}

	@Override
	public List<Yuanliao> findbyList(List<String> nameLike) {
		// TODO Auto-generated method stub
		String hql="from Yuanliao where mName like '%"+nameLike.get(0)+"%'";
		String add;
		for(int i = 0;i<nameLike.size();i++){
			if(i>0){
				add=" or mName like '%"+nameLike.get(i)+"%'";
				hql=hql+add;
			}
		}		  
		 return list(hql);
	}

	@Override
	public List<Yuanliao> bohe() {
		// TODO Auto-generated method stub
		String hql="from Yuanliao where source='薄荷网'";
		 return list(hql);
	}

	@Override
	public List<Yuanliao> standofcountry() {
		// TODO Auto-generated method stub
		String hql="from Yuanliao where source='国家标准'";
		 return list(hql);
	}

	@Override
	public List<Yuanliao> findTiaoliao() {
		// TODO Auto-generated method stub
		String hql="from Yuanliao where type='调味品类'";
		return list(hql);
	}
}
