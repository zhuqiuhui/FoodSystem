package com.ustb.food.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Food;
import com.ustb.food.service.FoodService;
import com.ustb.food.tool.HttpClientTool;
import com.ustb.food.tool.ToolFood;

public class RunFood {

	// @Autowired FoodService foodService;
	public static void main(String[] args) throws Exception {
		
//			String url = "http://www.boohee.com/food/group/10?page=10";
			String url ="http://www.boohee.com/food/view_group/94";
		String content = HttpClientTool
				.get(url);
		//System.out.print("第"+j+"页");
		for (int i = 1; i <= 10; i++) {

			content = ToolFood.getFoodGroup(content);
			String foodUrl = ToolFood.getFoodUrl(content);
			System.out.println(foodUrl);
			String foodCotent = HttpClientTool.get(foodUrl);
			String foodName = ToolFood.getFoodName(foodCotent);
			Double calories = Double.parseDouble(ToolFood
					.getFoodCalories(foodCotent));
			Double carbohydrate = Double.parseDouble(ToolFood
					.getFoodOthers(foodCotent, 1));
			Double fat = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 2));
			Double protein = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 3));
			Double vitamine = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 4));
			Double vta = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 5));
			Double vtc = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 6));
			Double vte = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 7));
			Double carotene = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 8));
			Double thiamine = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 9));
			Double riboflavin = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 10));
			Double yansuan = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 11));
			Double cholesterol = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 12));
			Double mg = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 13));
			Double ca = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 14));
			Double iron = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 15));
			Double zinc = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 16));
			Double copper = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 17));
			Double mn = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 18));
			Double k = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 19));
			Double p = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 20));
			Double na = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 21));
			Double se = Double.parseDouble(ToolFood.getFoodOthers(
					foodCotent, 22));
			System.out.println("第"+i+"条");
			System.out.println("------------- 食物名称" + foodName);
			System.out.println("------------- 食物热量" + calories);
			System.out.println("------------- 食物碳水化合物" + carbohydrate);
			System.out.println("------------- 食物脂肪" + fat);
			System.out.println("------------- 食物蛋白质" + protein);
			System.out.println("------------- 食物维生素" + vitamine);
			System.out.println("------------- 食物维生素A" + vta);
			System.out.println("------------- 食物维生素C" + vtc);
			System.out.println("------------- 食物维生素E" + vte);
			System.out.println("-------------胡萝卜素" + carotene);
			System.out.println("-------------硫胺素" + thiamine);
			System.out.println("-------------核黄素" + riboflavin);
			System.out.println("-------------烟酸" + yansuan);
			System.out.println("-------------胆固醇" + cholesterol);
			System.out.println("-------------镁" + mg);
			System.out.println("-------------钙" + ca);
			System.out.println("-------------铁" + iron);
			System.out.println("-------------锌" + zinc);
			System.out.println("-------------铜" + copper);
			System.out.println("-------------锰" + mn);
			System.out.println("-------------钾" + k);
			System.out.println("-------------磷" + p);
			System.out.println("-------------钠" + na);
			System.out.println("-------------硒" + se);
			
			Food food = new Food();
			food.setFoodName(foodName);
			food.setCalories(calories);
			food.setCarbohydrate(carbohydrate);
			food.setFat(fat);
			food.setProtein(protein);
			food.setVitamine(vitamine);
			food.setVta(vta);
			food.setVtc(vtc);
			food.setVte(vte);
			food.setCarotene(carotene);
			food.setThiamine(thiamine);
			food.setRiboflavin(riboflavin);
			food.setYansuan(yansuan);
			food.setCholesterol(cholesterol);
			food.setMg(mg);
			food.setCa(ca);
			food.setIron(iron);
			food.setZinc(zinc);
			food.setCopper(copper);
			food.setMn(mn);
			food.setK(k);
			food.setP(p);
			food.setNa(na);
			food.setSe(se);
			food.setSource("网上");
			// System.out.println(food.toString());

			ApplicationContext ac = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			 FoodService foodService=(FoodService)ac.getBean("foodService");
		//	 foodService.save(food);
			 
		}
	  
	}

}
