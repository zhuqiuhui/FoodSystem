package com.ustb.food.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;

/*
 * 主要的目的是将属于薄荷网的原料中名字不规范的问题处理，比如将/n," "都以""替换*/
public class YuanliaoName {

	// @Autowired FoodService foodService;
	public static void main(String[] args) throws Exception {

		// 去空格
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		YuanliaoService ys = (YuanliaoService) ac.getBean("yuanliaoService");
		List<Yuanliao> yuanliaos = new ArrayList<Yuanliao>();
		List<Yuanliao> sameyuanliaos = new ArrayList<Yuanliao>();
		yuanliaos = ys.bohe();
		Yuanliao yl = new Yuanliao();
		for (int i = 0; i < yuanliaos.size(); i++) {
			yl = yuanliaos.get(i);
			String a = yl.getmName().replace("\n", "").replace(" ", "")
					.replace("\t", "").replace("\r", "");
			yl.setmName(a);
			ys.merge(yl);
			System.out.println(yl.getmName() + "成功");
		}

	}
}
