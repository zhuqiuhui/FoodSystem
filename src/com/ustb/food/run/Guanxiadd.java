package com.ustb.food.run;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.GuanxiId;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;
import com.ustb.food.entity.Caipu;
import com.ustb.food.service.CaipuService;
import com.ustb.food.entity.Guanxi;
import com.ustb.food.service.GuanxiService;

public class Guanxiadd {

	// @Autowired FoodService foodService;
	public static void main(String[] args) throws Exception {
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
		 String source= null;
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		YuanliaoService yls=(YuanliaoService)ac.getBean("yuanliaoService");
		CaipuService cps = (CaipuService)ac.getBean("caipuService");
		GuanxiService gxs = (GuanxiService)ac.getBean("guanxiService");
		List<Guanxi> gxlist = new ArrayList<Guanxi>();
		
		//计算菜谱中成分的含量
		List<Yuanliao> yll = new ArrayList<Yuanliao>();
		Double sum = 0.0;
		Double a = 0.0;
		for(int i=0;i<6;i++){
			Yuanliao yl = yls.get(i);
			yll.add(yl);
		}
       //菜谱中热量含量
		sum = yls.get(1).getCalories()*400+ yls.get(2).getCalories()*5+ yls.get(3).getCalories()*5
				+ yls.get(4).getCalories()*1+ yls.get(5).getCalories()*1+ yls.get(6).getCalories()*10;
		a = sum/(400+5+5+1+1+10);
		DecimalFormat df = new DecimalFormat("#.00");
		calories=Double.valueOf(df.format(a));
		System.out.println(calories);

      //菜谱中碳水化合物含量
		sum = yls.get(1).getCarbohydrate()*400+yls.get(2).getCarbohydrate()*5+ yls.get(3).getCarbohydrate()*5
				+ yls.get(4).getCarbohydrate()*1+ yls.get(5).getCarbohydrate()*1+ yls.get(6).getCarbohydrate()*10;
		a = sum/(400+5+5+1+1+10);
		carbohydrate=Double.valueOf(df.format(a));
		System.out.println(carbohydrate);
		//菜谱中脂肪含量
		sum=yls.get(1).getFat()*400+yls.get(2).getFat()*5+ yls.get(3).getFat()*5
				+ yls.get(4).getFat()*1+ yls.get(5).getFat()*1+ yls.get(6).getFat()*10;
		a = sum/(400+5+5+1+1+10);
		fat=Double.valueOf(df.format(a));
		System.out.println(fat);
		//菜谱中蛋白质含量
		sum=yls.get(1).getProtein()*400+yls.get(2).getProtein()*5+ yls.get(3).getProtein()*5
				+ yls.get(4).getProtein()*1+ yls.get(5).getProtein()*1+ yls.get(6).getProtein()*10;
		a = sum/(400+5+5+1+1+10);
		protein=Double.valueOf(df.format(a));
		System.out.println(protein);
		//菜谱中纤维素的含量
		sum=yls.get(1).getVitamine()*400+yls.get(2).getVitamine()*5+ yls.get(3).getVitamine()*5
				+ yls.get(4).getVitamine()*1+ yls.get(5).getVitamine()*1+ yls.get(6).getVitamine()*10;
		a = sum/(400+5+5+1+1+10);
		vitamine=Double.valueOf(df.format(a));
		System.out.println(vitamine);
		//菜谱中维生素A含量
		sum=yls.get(1).getVta()*400+yls.get(2).getVta()*5+ yls.get(3).getVta()*5
				+ yls.get(4).getVta()*1+ yls.get(5).getVta()*1+ yls.get(6).getVta()*10;
		a = sum/(400+5+5+1+1+10);
		vta=Double.valueOf(df.format(a));
		System.out.println(vta);
		//菜谱中维生素C含量
		sum=yls.get(1).getVtc()*400+yls.get(2).getVtc()*5+ yls.get(3).getVtc()*5
				+ yls.get(4).getVtc()*1+ yls.get(5).getVtc()*1+ yls.get(6).getVtc()*10;
		a = sum/(400+5+5+1+1+10);
		vtc=Double.valueOf(df.format(a));
		System.out.println(vtc);
		//菜谱中维生素E含量
		sum=yls.get(1).getVte()*400+yls.get(2).getVte()*5+ yls.get(3).getVte()*5
				+ yls.get(4).getVte()*1+ yls.get(5).getVte()*1+ yls.get(6).getVte()*10;
		a = sum/(400+5+5+1+1+10);
		vte=Double.valueOf(df.format(a));
		System.out.println(vte);
		//菜谱中胡萝卜素含量
		sum=yls.get(1).getCarotene()*400+yls.get(2).getCarotene()*5+ yls.get(3).getCarotene()*5
				+ yls.get(4).getCarotene()*1+ yls.get(5).getCarotene()*1+ yls.get(6).getCarotene()*10;
		a = sum/(400+5+5+1+1+10);
		carotene=Double.valueOf(df.format(a));
		System.out.println(carotene);
		//菜谱中硫胺素含量
		sum=yls.get(1).getThiamine()*400+yls.get(2).getThiamine()*5+ yls.get(3).getThiamine()*5
				+ yls.get(4).getThiamine()*1+ yls.get(5).getThiamine()*1+ yls.get(6).getThiamine()*10;
		a = sum/(400+5+5+1+1+10);
		thiamine=Double.valueOf(df.format(a));
		System.out.println(thiamine);
		//菜谱中核黄素含量
		sum=yls.get(1).getRiboflavin()*400+yls.get(2).getRiboflavin()*5+ yls.get(3).getRiboflavin()*5
				+ yls.get(4).getRiboflavin()*1+ yls.get(5).getRiboflavin()*1+ yls.get(6).getRiboflavin()*10;
		a = sum/(400+5+5+1+1+10);
		riboflavin=Double.valueOf(df.format(a));
		System.out.println(riboflavin);
		//菜谱中烟酸含量
		sum=yls.get(1).getYansuan()*400+yls.get(2).getYansuan()*5+ yls.get(3).getYansuan()*5
				+ yls.get(4).getYansuan()*1+ yls.get(5).getYansuan()*1+ yls.get(6).getYansuan()*10;
		a = sum/(400+5+5+1+1+10);
		yansuan=Double.valueOf(df.format(a));
		System.out.println(yansuan);
		//菜谱中胆固醇含量
		sum=yls.get(1).getCholesterol()*400+yls.get(2).getCholesterol()*5+ yls.get(3).getCholesterol()*5
				+ yls.get(4).getCholesterol()*1+ yls.get(5).getCholesterol()*1+ yls.get(6).getCholesterol()*10;
		a = sum/(400+5+5+1+1+10);
		cholesterol=Double.valueOf(df.format(a));
		System.out.println(cholesterol);
		//菜谱中镁含量
		sum=yls.get(1).getMg()*400+yls.get(2).getMg()*5+ yls.get(3).getMg()*5
				+ yls.get(4).getMg()*1+ yls.get(5).getMg()*1+ yls.get(6).getMg()*10;
		a = sum/(400+5+5+1+1+10);
		mg=Double.valueOf(df.format(a));
		System.out.println(mg);
		//菜谱中钙含量
		sum=yls.get(1).getCa()*400+yls.get(2).getCa()*5+ yls.get(3).getCa()*5
				+ yls.get(4).getCa()*1+ yls.get(5).getCa()*1+ yls.get(6).getCa()*10;
		a = sum/(400+5+5+1+1+10);
		ca=Double.valueOf(df.format(a));
		System.out.println(ca);
		//菜谱中铁含量
		sum=yls.get(1).getIron()*400+yls.get(2).getIron()*5+ yls.get(3).getIron()*5
				+ yls.get(4).getIron()*1+ yls.get(5).getIron()*1+ yls.get(6).getIron()*10;
		a = sum/(400+5+5+1+1+10);
		iron=Double.valueOf(df.format(a));
		System.out.println(iron);
		//菜谱中锌含量
		sum=yls.get(1).getZinc()*400+yls.get(2).getZinc()*5+ yls.get(3).getZinc()*5
				+ yls.get(4).getZinc()*1+ yls.get(5).getZinc()*1+ yls.get(6).getZinc()*10;
		a = sum/(400+5+5+1+1+10);
		zinc=Double.valueOf(df.format(a));
		System.out.println(zinc);
		//菜谱中铜含量
		sum=yls.get(1).getCopper()*400+yls.get(2).getCopper()*5+ yls.get(3).getCopper()*5
				+ yls.get(4).getCopper()*1+ yls.get(5).getCopper()*1+ yls.get(6).getCopper()*10;
		a = sum/(400+5+5+1+1+10);
		copper=Double.valueOf(df.format(a));
		System.out.println(copper);
		//菜谱中锰含量
		sum=yls.get(1).getMn()*400+yls.get(2).getMn()*5+ yls.get(3).getMn()*5
				+ yls.get(4).getMn()*1+ yls.get(5).getMn()*1+ yls.get(6).getMn()*10;
		a = sum/(400+5+5+1+1+10);
		mn=Double.valueOf(df.format(a));
		System.out.println(mn);
		//菜谱中钾含量
		sum=yls.get(1).getK()*400+yls.get(2).getK()*5+ yls.get(3).getK()*5
				+ yls.get(4).getK()*1+ yls.get(5).getK()*1+ yls.get(6).getK()*10;
		a = sum/(400+5+5+1+1+10);
		k=Double.valueOf(df.format(a));
		System.out.println(k);
		//菜谱中磷含量
		sum=yls.get(1).getP()*400+yls.get(2).getP()*5+ yls.get(3).getP()*5
				+ yls.get(4).getP()*1+ yls.get(5).getP()*1+ yls.get(6).getP()*10;
		a = sum/(400+5+5+1+1+10);
		p=Double.valueOf(df.format(a));
		System.out.println(p);
		//菜谱中钠含量
		sum=yls.get(1).getNa()*400+yls.get(2).getNa()*5+ yls.get(3).getNa()*5
				+ yls.get(4).getNa()*1+ yls.get(5).getNa()*1+ yls.get(6).getNa()*10;
		a = sum/(400+5+5+1+1+10);
		na=Double.valueOf(df.format(a));
		System.out.println(na);
		//菜谱中硒含量
		sum=yls.get(1).getSe()*400+yls.get(2).getSe()*5+ yls.get(3).getSe()*5
				+ yls.get(4).getSe()*1+ yls.get(5).getSe()*1+ yls.get(6).getSe()*10;
		a = sum/(400+5+5+1+1+10);
		se=Double.valueOf(df.format(a));
		System.out.println(se);
		
		Caipu cp = new Caipu("炒空心菜",calories,  carbohydrate,
				 fat,  protein,  vitamine,  vta,  vtc,
				 vte,  carotene,  thiamine,  riboflavin,
				 yansuan,  cholesterol,  mg,  ca,  iron,
				 zinc,  copper,  mn,  k,  p,  na,
				 se,  "网上",  gxlist);
		cp.setViewName("炒空心菜");
//		cps.save(cp);
		
		GuanxiId gi1= new GuanxiId(cp.getViewId(),yls.get(1).getMaId());
		GuanxiId gi2= new GuanxiId(cp.getViewId(),yls.get(2).getMaId());
		GuanxiId gi3= new GuanxiId(cp.getViewId(),yls.get(3).getMaId());
		GuanxiId gi4= new GuanxiId(cp.getViewId(),yls.get(4).getMaId());
		GuanxiId gi5= new GuanxiId(cp.getViewId(),yls.get(5).getMaId());
		GuanxiId gi6= new GuanxiId(cp.getViewId(),yls.get(6).getMaId());
		
		Guanxi gx1 = new Guanxi(gi1,cp,yls.get(1),400);
		Guanxi gx2 = new Guanxi(gi2,cp,yls.get(2),5);
		Guanxi gx3 = new Guanxi(gi3,cp,yls.get(3),5);
		Guanxi gx4 = new Guanxi(gi4,cp,yls.get(4),1);
		Guanxi gx5 = new Guanxi(gi5,cp,yls.get(5),1);
		Guanxi gx6 = new Guanxi(gi6,cp,yls.get(6),10);

//		gxs.save(gx1);
//		gxs.save(gx2);
//		gxs.save(gx3);
//		gxs.save(gx4);
//		gxs.save(gx5);
//		gxs.save(gx6);

//取出菜谱列表
//		List<Caipu> cl = new ArrayList();
//		cl=cps.getList();
//		
//		for(Caipu cp:cl){
//			System.out.println(cp.getViewName());
//			List<Guanxi> gxl = new ArrayList();
//			gxl = cp.getGuanxis();
//			for(Guanxi gx : gxl){
//				System.out.print(gx.getYuanliao().getmName());
//				
//			}			
//		}
	}
}
