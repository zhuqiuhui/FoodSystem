package com.ustb.food.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Material;
import com.ustb.food.service.MaterialService;


public class MaterialName {

	// @Autowired FoodService foodService;
	public static void main(String[] args) throws Exception {
		
		
		
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MaterialService ms=(MaterialService)ac.getBean("materialService");
		List<Material> materials = new ArrayList<Material>();
		materials=ms.getList();
		Material mt = new Material();
		for(int i=0;i<materials.size();i++){
			mt=materials.get(i);
			String a = mt.getmName().replace("\n", "").replace(" ", "").replace("\t", "").replace("\r", "");
			mt.setmName(a);
			ms.merge(mt);
			System.out.println(mt.getmName()+"成功");
		}		
		
	}
}
