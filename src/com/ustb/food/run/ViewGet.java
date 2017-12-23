package com.ustb.food.run;

import java.text.DecimalFormat;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Caipu;
import com.ustb.food.entity.Guanxi;
import com.ustb.food.entity.GuanxiId;
import com.ustb.food.entity.Material;
import com.ustb.food.entity.Views;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.CaipuService;
import com.ustb.food.service.GuanxiService;
import com.ustb.food.service.ViewService;
import com.ustb.food.service.YuanliaoService;

public class ViewGet {
	// @Autowired FoodService foodService;
	public static void main(String[] args) throws Exception {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ViewService viewService = (ViewService) ac.getBean("viewService");
		YuanliaoService yuanliaoService = (YuanliaoService) ac
				.getBean("yuanliaoService");
		CaipuService caipuService = (CaipuService) ac
				.getBean("caipuService");
		GuanxiService guanxiService = (GuanxiService) ac
				.getBean("guanxiService");
		Views view = new Views();
		List<Views> views = new ArrayList<Views>();
		views = viewService.getList();
		Yuanliao yl = new Yuanliao();
		int count = 0;
	    String viewName ;
//	    733
		for (int i = 733; i <views.size(); i++) {
		    List <String> idl = new ArrayList<String>();
		    List<String> al = new ArrayList<String>();
			view = views.get(i);
//		    view = viewService.get(31);
			System.out.print("-----------------------------------");
			System.out.print("i为" + i);
			viewName = view.getViewName();
			List<Material> materials = new ArrayList<Material>();
			materials = view.getMaterials();
			String name1="";
			if(!materials.isEmpty()){
				for (Material m : materials) {
					System.out.println("原料" + m.getmName() + m.getAmount() + "克");
					String name =m.getmName();
					if ((!(yuanliaoService.findSame(m.getmName())).isEmpty())) {
						// 找到菜谱中的原料
						yl = yuanliaoService.findSame(m.getmName()).get(0);
						String id =Integer.toString(yl.getMaId());	
						if(!name1.equals(name)){
							idl.add(id);
							String amount = Double.toString(m.getAmount());
							al.add(amount);
						}
						 name1 = yl.getmName();
						System.out.println("对应原料Id为" + id);
					} else if (m.getmName().equals("鸡蛋")) {
						
						String id =Integer.toString(21591);
						idl.add(id);
						String amount = Double.toString(m.getAmount());
						al.add(amount);
					} 
					else {
						System.out.println("原料" + m.getmName() + m.getAmount()
								+ "克");
						System.out
								.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!没找到");
						break;
					}
				}
				save(idl,al,viewName, yuanliaoService,caipuService, guanxiService);
			}
			else{
					System.out.println("无效菜谱");
					continue;
				}
			

		}
	}
	
	public  static void save(List<String> idl ,List<String> al,String viewName,YuanliaoService yuanliaoService,
			CaipuService caipuService,GuanxiService guanxiService){
		Double calories = 0.0 ;
		 Double carbohydrate =  0.0;
		 Double fat =  0.0;
		 Double protein =  0.0;
		 Double vitamine =  0.0;
		 Double vta =  0.0;
		 Double vtc =  0.0;
		 Double vte =  0.0;
		 Double carotene =  0.0;
		 Double thiamine =  0.0;
		 Double riboflavin =  0.0;
		 Double yansuan = 0.0;
		 Double cholesterol =  0.0;
		 Double mg =  0.0;
		 Double ca =  0.0;
		 Double iron =  0.0;
		 Double zinc =  0.0;
		 Double copper =  0.0;
		 Double mn =  0.0;
		 Double k =  0.0;
		 Double p =  0.0;
		 Double na =  0.0;
		 Double se =  0.0;
		 Double sum = 0.0;
		 Double asum = 0.0;
		 Double a = 0.0;
		 System.out.println("");
		 System.out.print(viewName);
		 //菜谱中热量含量
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(yuanliaoService.get((int)Double.parseDouble(idl.get(i)))).getCalories()*(Double.parseDouble(al.get(i)));
			 asum+=Double.parseDouble(al.get(i));
		 }
		 a=sum/asum;
		 DecimalFormat df = new DecimalFormat("#.00");
		 calories=Double.valueOf(df.format(a));
		 System.out.println("热量"+calories);
//		 System.out.println("sum"+sum);
//		 System.out.println("asum"+asum);
		//菜谱中碳水化合物含量
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(yuanliaoService.get((int)Double.parseDouble(idl.get(i)))).getCarbohydrate()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 carbohydrate=Double.valueOf(df.format(a));
		 System.out.println("碳水化合物"+carbohydrate);
		//菜谱中脂肪含量
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(yuanliaoService.get((int)Double.parseDouble(idl.get(i)))).getFat()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 fat=Double.valueOf(df.format(a));
		 System.out.println("脂肪"+fat);
		//菜谱中蛋白质含量
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(yuanliaoService.get((int)Double.parseDouble(idl.get(i)))).getProtein()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 protein=Double.valueOf(df.format(a));
//		 System.out.println("蛋白质"+protein);
		//菜谱中纤维素含量
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(yuanliaoService.get((int)Double.parseDouble(idl.get(i)))).getVitamine()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 vitamine=Double.valueOf(df.format(a));
//		 System.out.println("纤维素"+vitamine);
		//菜谱中维生素A含量
		 sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getVta() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		vta = Double.valueOf(df.format(a));
//		System.out.println("维生素A" + vta);
		// 菜谱中维生素C含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getVtc() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		vtc = Double.valueOf(df.format(a));
//		System.out.println("维生素C" +vtc);
		// 菜谱中维生素E含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getVte() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		vte = Double.valueOf(df.format(a));
//		System.out.println("维生素E"+vte);
		// 菜谱中胡萝卜素含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getCarotene() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		carotene = Double.valueOf(df.format(a));
//		System.out.println(carotene);
		// 菜谱中硫胺素含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getThiamine() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		thiamine = Double.valueOf(df.format(a));
//		System.out.println(thiamine);
		// 菜谱中核黄素含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getRiboflavin() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		riboflavin = Double.valueOf(df.format(a));
//		System.out.println(riboflavin);
		// 菜谱中烟酸含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getYansuan() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		yansuan = Double.valueOf(df.format(a));
//		System.out.println(yansuan);
		// 菜谱中胆固醇含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getCholesterol() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		cholesterol = Double.valueOf(df.format(a));
//		System.out.println(cholesterol);
		// 菜谱中镁含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getMg() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		mg = Double.valueOf(df.format(a));
//		System.out.println(mg);
		// 菜谱中钙含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getCa() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		ca = Double.valueOf(df.format(a));
//		System.out.println(ca);
		// 菜谱中铁含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getIron() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		iron = Double.valueOf(df.format(a));
//		System.out.println(iron);
		// 菜谱中锌含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getZinc() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		zinc = Double.valueOf(df.format(a));
//		System.out.println(zinc);
		// 菜谱中铜含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getCopper() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		copper = Double.valueOf(df.format(a));
//		System.out.println(copper);
		// 菜谱中锰含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getMn() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		mn = Double.valueOf(df.format(a));
//		System.out.println(mn);
		// 菜谱中钾含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getK() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		k = Double.valueOf(df.format(a));
//		System.out.println(k);
		// 菜谱中磷含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getP() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		p = Double.valueOf(df.format(a));
//		System.out.println(p);
		// 菜谱中钠含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getNa() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		na = Double.valueOf(df.format(a));
//		System.out.println(na);
		// 菜谱中硒含量
		sum = 0.0;
		for (int i = 0; i < idl.size(); i++) {
			sum += (yuanliaoService.get((int) Double.parseDouble(idl.get(i))))
					.getSe() * (Double.parseDouble(al.get(i)));
		}
		a = sum / asum;
		se = Double.valueOf(df.format(a));
//		System.out.println(se);
		List<Guanxi> gxlist = new ArrayList<Guanxi>();
		Set<Guanxi> gxl = new HashSet<Guanxi>();
		List<GuanxiId> gxidl = new ArrayList<GuanxiId>();
		Caipu cp = new Caipu(viewName,calories,  carbohydrate,
				 fat,  protein,  vitamine,  vta,  vtc,
				 vte,  carotene,  thiamine,  riboflavin,
				 yansuan,  cholesterol,  mg,  ca,  iron,
				 zinc,  copper,  mn,  k,  p,  na,
				 se,  "薄荷网",  gxlist);
		//保存数据
		caipuService.save(cp);
		for(int i = 0;i<idl.size();i++){
			GuanxiId gxi = new GuanxiId(cp.getViewId(),(int)Double.parseDouble(idl.get(i)));
			gxidl.add(gxi);
		}
		for(int i = 0;i<idl.size();i++){
//			System.out.println(yuanliaoService.get((int) Double.parseDouble(idl.get(i))).getmName()+(int)Double.parseDouble(al.get(i)));
			Guanxi g = new Guanxi(gxidl.get(i),cp,yuanliaoService.get((int) Double.parseDouble(idl.get(i))),(int)Double.parseDouble(al.get(i)));			
			gxl.add(g);	

		}
		
		Iterator iterator =gxl.iterator();
		//保存数据
		while(iterator.hasNext()){
			Guanxi g = (Guanxi)iterator.next();
//			System.out.println(g);
//			System.out.println(g.getId());
//			System.out.println(g.getCaipu().getViewId());
//			System.out.println(g.getYuanliao().getMaId());
//			System.out.println(g.getAmount());
//			System.out.println("---------------------");
			guanxiService.save(g);
		}
		System.out.println("保存成功");
	}
}
