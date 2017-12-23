package com.ustb.food.run;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Tuijie;
import com.ustb.food.service.TuijieService;

public class testTuijie {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		TuijieService tj = (TuijieService) ac.getBean("tuijieService");

		/*
		 * List<Tuijie> list = tj.getList(2, 4); for (int i = 0; i <
		 * list.size(); i++) { System.out.println(list.get(i)); }
		 */
		List<Tuijie> findByName = tj.findByName("白菜");
		System.out.println(findByName.get(0).getNormal());

	}
}
