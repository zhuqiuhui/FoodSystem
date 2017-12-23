package com.ustb.food.run;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Material;
import com.ustb.food.entity.Views;
import com.ustb.food.service.ViewService;
import com.ustb.food.tool.HttpClientTool;
import com.ustb.food.tool.ToolFood;

public class RunView {

	// @Autowired FoodService foodService;
	public static void main(String[] args) throws Exception {
		
			int page=10;
			String url ="http://www.boohee.com/food/view_group/131?page="+page;
			String content = HttpClientTool
				.get(url);
			
		//System.out.print("第"+j+"页");
		for (int i = 1; i <= 10; i++) {
			
			System.out.println("第"+i+"条");
			List<Material> materials = new ArrayList<Material>();
			content = ToolFood.getFoodGroup(content);
			String foodUrl = ToolFood.getFoodUrl(content);
			System.out.println(foodUrl);
			String foodCotent = HttpClientTool.get(foodUrl);
			List<String> urls = new ArrayList<String>();
			urls = ToolFood.getViewMaterialUrl(foodCotent);
			String materialUrl=null;
			Map materMap = new HashMap();
			materMap = ToolFood.getViewMaterialAmount(foodCotent);
			int save =1;
			for(int j = 0;j<urls.size();j++){
				materialUrl = urls.get(j);
				String mContent = HttpClientTool.get(materialUrl);
				//System.out.print("原料的网址"+materialUrl);
				Double amount =(Double) materMap.get(materialUrl);
				String mName = ToolFood.getFoodName(mContent);				
				Double calories = Double.parseDouble(ToolFood
						.getFoodCalories(mContent));
				Double carbohydrate = Double.parseDouble(ToolFood
						.getFoodOthers(mContent, 1));
				Double fat = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 2));
				Double protein = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 3));
				Double vitamine = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 4));
				Double vta = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 5));
				Double vtc = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 6));
				Double vte = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 7));
				Double carotene = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 8));
				Double thiamine = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 9));
				Double riboflavin = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 10));
				Double yansuan = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 11));
				Double cholesterol = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 12));
				Double mg = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 13));
				Double ca = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 14));
				Double iron = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 15));
				Double zinc = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 16));
				Double copper = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 17));
				Double mn = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 18));
				Double k = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 19));
				Double p = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 20));
				Double na = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 21));
				Double se = Double.parseDouble(ToolFood.getFoodOthers(
						mContent, 22));
				Material  m = new Material();
				m.setmName(mName);
				m.setCalories(calories);
				m.setCarbohydrate(carbohydrate);
				m.setFat(fat);
				m.setProtein(protein);
				m.setVitamine(vitamine);
				m.setVta(vta);
				m.setVtc(vtc);
				m.setVte(vte);
				m.setCarotene(carotene);
				m.setThiamine(thiamine);
				m.setRiboflavin(riboflavin);
				m.setYansuan(yansuan);
				m.setCholesterol(cholesterol);
				m.setMg(mg);
				m.setCa(ca);
				m.setIron(iron);
				m.setZinc(zinc);
				m.setCopper(copper);
				m.setMn(mn);
				m.setK(k);
				m.setP(p);
				m.setNa(na);
				m.setSe(se);
				m.setSource("网上");
				m.setAmount(amount);
				materials.add(m);
				if(m.getAmount()==null){
					save=0;
					break;				
				}
				System.out.println("------------ 原料名称" + mName+m.getAmount()+"克");
				
			}

			String viewName = ToolFood.getFoodName(foodCotent);
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
			
			System.out.println("------------- 食物名称" + viewName);
//			System.out.println("------------- 食物热量" + calories);
//			System.out.println("------------- 食物碳水化合物" + carbohydrate);
//			System.out.println("------------- 食物脂肪" + fat);
//			System.out.println("------------- 食物蛋白质" + protein);
//			System.out.println("------------- 食物维生素" + vitamine);
//			System.out.println("------------- 食物维生素A" + vta);
//			System.out.println("------------- 食物维生素C" + vtc);
//			System.out.println("------------- 食物维生素E" + vte);
//			System.out.println("-------------胡萝卜素" + carotene);
//			System.out.println("-------------硫胺素" + thiamine);
//			System.out.println("-------------核黄素" + riboflavin);
//			System.out.println("-------------烟酸" + yansuan);
//			System.out.println("-------------胆固醇" + cholesterol);
//			System.out.println("-------------镁" + mg);
//			System.out.println("-------------钙" + ca);
//			System.out.println("-------------铁" + iron);
//			System.out.println("-------------锌" + zinc);
//			System.out.println("-------------铜" + copper);
//			System.out.println("-------------锰" + mn);
//			System.out.println("-------------钾" + k);
//			System.out.println("-------------磷" + p);
//			System.out.println("-------------钠" + na);
//			System.out.println("-------------硒" + se);
//			

			Views view = new Views();
			view.setViewName(viewName);
			view.setCalories(calories);
			view.setCarbohydrate(carbohydrate);
			view.setFat(fat);
			view.setProtein(protein);
			view.setVitamine(vitamine);
			view.setVta(vta);
			view.setVtc(vtc);
			view.setVte(vte);
			view.setCarotene(carotene);
			view.setThiamine(thiamine);
			view.setRiboflavin(riboflavin);
			view.setYansuan(yansuan);
			view.setCholesterol(cholesterol);
			view.setMg(mg);
			view.setCa(ca);
			view.setIron(iron);
			view.setZinc(zinc);
			view.setCopper(copper);
			view.setMn(mn);
			view.setK(k);
			view.setP(p);
			view.setNa(na);
			view.setSe(se);
			view.setSource("网上");
			view.setMaterials(materials);
			// System.out.println(food.toString());
			if(save==1){
				ApplicationContext ac = new ClassPathXmlApplicationContext(
						"applicationContext.xml");
				 ViewService viewService=(ViewService)ac.getBean("viewService");
				 viewService.save(view);
				 System.out.println("添加成功");
			}else{
				System.out.println("添加失败");
			}
			System.out.println();
			System.out.println();
		}
		System.out.println("第"+page+"页");
	}

}
