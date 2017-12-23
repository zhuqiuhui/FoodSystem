package com.ustb.food.tool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.ustb.food.dao.impl.PerrecordDaoImpl;
import com.ustb.food.dao.impl.YuanliaolDaoImpl;
import com.ustb.food.entity.Perdiet;
import com.ustb.food.entity.Perrecord;
import com.ustb.food.entity.Yuanliao;
/*
 * 依据一条新的原料组，得到他的营养总和，生成一条precord
 * */
@Component("countYingyang")
public class countYingyang {
	//set是嵌套内部的set，即指的一个自创的菜谱
	private Map<String, Object> session;
	@Autowired
	PerrecordDaoImpl perrecordDao;
	@Autowired
	YuanliaolDaoImpl yuanliaoDao;

	public PerrecordDaoImpl getPerrecordDao() {
		return perrecordDao;
	}
	public void setPerrecordDao(PerrecordDaoImpl perrecordDao) {
		this.perrecordDao = perrecordDao;
	}
	public YuanliaolDaoImpl getYuanliaoDao() {
		return yuanliaoDao;
	}
	public void setYuanliaoDao(YuanliaolDaoImpl yuanliaoDao) {
		this.yuanliaoDao = yuanliaoDao;
	}
	public Perdiet sumy(Set set,int id) throws Exception{
		Perdiet perdiet = new Perdiet();
		//list用来存放所包含的原料
		ArrayList<String> list = new ArrayList<String>();
		Iterator it=set.iterator();
	       while(it.hasNext())
	       {
	            list.add((String) it.next());
	       }
	    Integer[] array=new Integer[list.size()];
	    for(int i=0;i<list.size();i++){
	    	Yuanliao yl=getsingleYY(list.get(i));
	    	array[i]=yl.getMaId();
	    }
	    //依据用户的id和菜谱中的原料新建一条原料名称，数量的perrecord记录
	    List<Perrecord> getrulePre =perrecordDao.getrulePre(id, array);
	    //List<Perrecord> getrulePre =perrecordDao.getrulePre(3, array);
	    //依据新得到的perrecord,计算其营养成分的总和，作为一条新的prediet
	    Perdiet perdiet2 = perrecordDao.calculate(getrulePre);
		return perdiet2;
	}
	public Yuanliao getsingleYY(String mName){
		List<Yuanliao> same = yuanliaoDao.findSame(mName);
		Yuanliao yuanliao = same.get(0);
		return yuanliao;
	}
	public static void main(String[] args) {
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");  
	     context.start();  
	     countYingyang yingyang = new countYingyang();
	     Yuanliao yuanliao = yingyang.getsingleYY("羊肉");
	     System.out.println(yuanliao.getmName());
	}
}
