package com.ustb.food.run;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Perrecord;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.PerrecordService;
import com.ustb.food.service.YuanliaoService;

public class testSpring {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		YuanliaoService yls = (YuanliaoService) ac.getBean("yuanliaoService");
		List<Yuanliao> same = yls.findSame("羊肉");
		System.out.println(same.size());
		PerrecordService bean = (PerrecordService) ac
				.getBean("perrecordService");
		List<Perrecord> perrecordList = bean.perrecordList(3, "2016-10-22");
		// System.out.println(perrecordList);
	}
}
