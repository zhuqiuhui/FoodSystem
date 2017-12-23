package com.ustb.food.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;

/*
 * 处理薄荷网和国家标准中虽然名字不一样但23种原料是相同的的，则选择去除一个*/
public class Yuanliaounified {

	// @Autowired FoodService foodService;
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		YuanliaoService ys = (YuanliaoService) ac.getBean("yuanliaoService");
		List<Yuanliao> bohes = new ArrayList<Yuanliao>();
		List<Yuanliao> stands = new ArrayList<Yuanliao>();
		bohes = ys.bohe();
		stands = ys.standofcountry();
		int id;
		// equal(ys.get(453),ys.get(22520))
		/**/
		for (int i = 0; i < bohes.size(); i++) {
			for (int j = 0; j < stands.size(); j++) {
				if (equal(bohes.get(i), stands.get(j))) {
					System.out.print(bohes.get(i).getmName());
					System.out.println(stands.get(j).getmName());
					System.out.println("-----------------");
					// bohes.get(i).setSource("国家标准");
					// bohes.get(i).setCode(stands.get(j).getCode());
					// bohes.get(i).setType(stands.get(j).getType());
					// ys.merge(bohes.get(i));
					// ys.delete(stands.get(j).getMaId());
				} else {
				}
			}

		}
		// if(equal(ys.get(453),ys.get(22520))){
		// ys.get(453).setSource("国家标准");
		// ys.get(453).setCode(ys.get(22520).getCode());
		// ys.get(453).setType(ys.get(22520).getType());
		// System.out.print(ys.get(453).getCode()+ys.get(453).getSource()+ys.get(453).getCa());
		// // ys.merge(ys.get(453));
		// // ys.delete(22520);
		// }else{
		// System.out.println("无相同");
		// }
	}

	public static boolean equal(Yuanliao bohe, Yuanliao stand) {
		if (bohe.getCalories() != 0
				&& bohe.getCalories().equals(stand.getCalories())
				&& bohe.getCarbohydrate().equals(stand.getCarbohydrate())
				&& bohe.getFat().equals(stand.getFat())
				&& bohe.getProtein().equals(stand.getProtein())
				&& bohe.getVitamine().equals(stand.getVitamine())
				&& bohe.getVta().equals(stand.getVta())) {
			return true;
		} else {
			return false;
		}
	}
}
