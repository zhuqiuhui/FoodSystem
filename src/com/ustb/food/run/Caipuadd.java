package com.ustb.food.run;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Caipu;
import com.ustb.food.entity.Guanxi;
import com.ustb.food.entity.GuanxiId;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.CaipuService;
import com.ustb.food.service.GuanxiService;

//计算菜谱中营养成分
public class Caipuadd {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CaipuService caipuService = (CaipuService) ac.getBean("caipuService");
		GuanxiService guanxiService = (GuanxiService) ac
				.getBean("guanxiService");

		// Caipu caipu = caipuService.get(2385);
		// List<Guanxi> guanxiList = caipuService.get(2385).getGuanxis();
		// System.out.println(guanxiList.size());
		List<Caipu> caipuList = new ArrayList<Caipu>();
		caipuList = caipuService.getList();
		Yuanliao yuanliao = new Yuanliao();
		Caipu caipu = new Caipu();
		// 注：caipulist中的list的位置不一定是相应位置的菜谱,所要得到 i可以通过导出到excel中然后找对菜谱对应的数字
		List<Guanxi> guanxiList = new ArrayList<Guanxi>();
		for (int i = 3038; i < 3048; i++) {
			caipu = caipuList.get(i);
			System.out.println(caipu.getViewId());
			guanxiList = caipu.getGuanxis();
			if (!guanxiList.isEmpty()) {
				jisuan(guanxiList, caipu, caipuService);
			} else {
				System.out.println("无效菜谱没有原料");
			}

		}
	}

	public static void jisuan(List<Guanxi> guanxiList, Caipu cp,
			CaipuService caipuService) {
		Double calories = 0.0;
		Double carbohydrate = 0.0;
		Double fat = 0.0;
		Double protein = 0.0;
		Double vitamine = 0.0;
		Double vta = 0.0;
		Double vtc = 0.0;
		Double vte = 0.0;
		Double carotene = 0.0;
		Double thiamine = 0.0;
		Double riboflavin = 0.0;
		Double yansuan = 0.0;
		Double cholesterol = 0.0;
		Double mg = 0.0;
		Double ca = 0.0;
		Double iron = 0.0;
		Double zinc = 0.0;
		Double copper = 0.0;
		Double mn = 0.0;
		Double k = 0.0;
		Double p = 0.0;
		Double na = 0.0;
		Double se = 0.0;
		Double sum = 0.0;
		Double asum = 0.0;
		Double a = 0.0;
		// 菜谱中热量含量
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getCalories() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getCalories()
						* guanxiList.get(i).getAmount();
			}
			asum += guanxiList.get(i).getAmount();
		}
		a = sum / asum;
		DecimalFormat df = new DecimalFormat("#.00");
		calories = Double.valueOf(df.format(a));
		// System.out.println("热量"+calories);
		// System.out.println("sum"+sum);
		// System.out.println("asum"+asum);
		// 菜谱中碳水化合物含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getCarbohydrate() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getCarbohydrate()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		carbohydrate = Double.valueOf(df.format(a));
		// System.out.println("碳水化合物"+carbohydrate);
		// 菜谱中脂肪含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getFat() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getFat()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		fat = Double.valueOf(df.format(a));
		// System.out.println("脂肪"+fat);
		// 菜谱中蛋白质含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getProtein() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getProtein()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		protein = Double.valueOf(df.format(a));
		// System.out.println("蛋白质"+protein);
		// 菜谱中纤维素含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getVitamine() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getVitamine()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		vitamine = Double.valueOf(df.format(a));
		// System.out.println("纤维素"+vitamine);
		// 菜谱中维生素A含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getVta() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getVta()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		vta = Double.valueOf(df.format(a));
		// System.out.println("维生素A" + vta);
		// 菜谱中维生素C含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getVtc() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getVtc()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		vtc = Double.valueOf(df.format(a));
		// System.out.println("维生素C" +vtc);
		// 菜谱中维生素E含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getVte() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getVte()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		vte = Double.valueOf(df.format(a));
		// System.out.println("维生素E"+vte);
		// 菜谱中胡萝卜素含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getCarotene() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getCarotene()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		carotene = Double.valueOf(df.format(a));
		// System.out.println(carotene);
		// 菜谱中硫胺素含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getThiamine() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getThiamine()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		thiamine = Double.valueOf(df.format(a));
		// System.out.println(thiamine);
		// 菜谱中核黄素含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getRiboflavin() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getRiboflavin()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		riboflavin = Double.valueOf(df.format(a));
		// System.out.println(riboflavin);
		// 菜谱中烟酸含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getYansuan() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getYansuan()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		yansuan = Double.valueOf(df.format(a));
		// System.out.println(yansuan);
		// 菜谱中胆固醇含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getCholesterol() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getCholesterol()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		cholesterol = Double.valueOf(df.format(a));
		// System.out.println(cholesterol);
		// 菜谱中镁含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getMg() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getMg()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		mg = Double.valueOf(df.format(a));
		// System.out.println(mg);
		// 菜谱中钙含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getCa() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getCa()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		ca = Double.valueOf(df.format(a));
		// System.out.println(ca);
		// 菜谱中铁含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getIron() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getIron()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		iron = Double.valueOf(df.format(a));
		// System.out.println(iron);
		// 菜谱中锌含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getZinc() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getZinc()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		zinc = Double.valueOf(df.format(a));
		// System.out.println(zinc);
		// 菜谱中铜含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getCopper() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getCopper()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		copper = Double.valueOf(df.format(a));
		// System.out.println(copper);
		// 菜谱中锰含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getMn() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getMn()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		mn = Double.valueOf(df.format(a));
		// System.out.println(mn);
		// 菜谱中钾含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getK() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getK()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		k = Double.valueOf(df.format(a));
		// System.out.println(k);
		// 菜谱中磷含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getP() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getP()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		p = Double.valueOf(df.format(a));
		// System.out.println(p);
		// 菜谱中钠含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getNa() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getNa()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		na = Double.valueOf(df.format(a));
		// System.out.println(na);
		// 菜谱中硒含量
		sum = 0.0;
		for (int i = 0; i < guanxiList.size(); i++) {
			Double cal;
			if (guanxiList.get(i).getYuanliao().getSe() == null) {
				cal = 0.0D;
				sum += cal * guanxiList.get(i).getAmount();
			} else {
				sum += guanxiList.get(i).getYuanliao().getSe()
						* guanxiList.get(i).getAmount();
			}
		}
		a = sum / asum;
		se = Double.valueOf(df.format(a));
		// System.out.println(se);
		List<Guanxi> gxlist = new ArrayList<Guanxi>();
		Set<Guanxi> gxl = new HashSet<Guanxi>();
		List<GuanxiId> gxidl = new ArrayList<GuanxiId>();
		// Caipu cp = new Caipu(viewName,calories, carbohydrate,
		// fat, protein, vitamine, vta, vtc,
		// vte, carotene, thiamine, riboflavin,
		// yansuan, cholesterol, mg, ca, iron,
		// zinc, copper, mn, k, p, na,
		// se, "薄荷网", gxlist);
		cp.setCalories(calories);
		cp.setCarbohydrate(carbohydrate);
		cp.setFat(fat);
		cp.setProtein(protein);
		cp.setVitamine(vitamine);
		cp.setVta(vta);
		cp.setVtc(vtc);
		cp.setVte(vte);
		cp.setCarotene(carotene);
		cp.setThiamine(thiamine);
		cp.setRiboflavin(riboflavin);
		cp.setYansuan(yansuan);
		cp.setCholesterol(cholesterol);
		cp.setMg(mg);
		cp.setCa(ca);
		cp.setIron(iron);
		cp.setZinc(zinc);
		cp.setCopper(copper);
		cp.setMn(mn);
		cp.setK(k);
		cp.setP(p);
		cp.setNa(na);
		cp.setSe(se);

		// 保存数据
		caipuService.merge(cp);
	}

}
