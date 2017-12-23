package com.ustb.food.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;

/*
 * 删除相同的原料的代码*/
public class YuanliaoSame {

	// @Autowired FoodService foodService;
	public static void main(String[] args) throws Exception {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		YuanliaoService ys = (YuanliaoService) ac.getBean("yuanliaoService");
		List<Yuanliao> yuanliaos = new ArrayList<Yuanliao>();
		List<Yuanliao> sameyuanliaos = new ArrayList<Yuanliao>();
		yuanliaos = ys.getList();
		int id;
		for (Yuanliao yl : yuanliaos) {
			sameyuanliaos = ys.findSame(yl.getmName());
			for (int i = 1; i < sameyuanliaos.size(); i++) {
				id = sameyuanliaos.get(i).getMaId();
				ys.delete(id);
				System.out.print("删除成功");
			}
		}
		// System.out.println("菜谱名称"+view.getViewName());
		// for(Material m:materials){
		// System.out.println("原料"+m.getmName()+m.getAmount()+"克");
		// }
	}
}
