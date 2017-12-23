package com.ustb.food.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Caipu;
import com.ustb.food.entity.Caipuauc;
import com.ustb.food.entity.Perdiet;
import com.ustb.food.entity.Users;
import com.ustb.food.service.CaipuService;
import com.ustb.food.service.CaipuaucService;
import com.ustb.food.service.PerdietService;
import com.ustb.food.service.UsersService;
import com.ustb.food.util.RecognizeModel;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月27日 下午8:45:25
 */

@Component("/front/CaipuaucAction")
@Scope("prototype")
public class CaipuaucAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> session;
	@Autowired
	CaipuaucService caipuaucService;
	@Autowired
	CaipuService caipuService;
	@Autowired
	PerdietService perdietService;
	@Autowired
	UsersService userService;
	private List<Caipuauc> caipuaucList = new ArrayList<Caipuauc>();
	private List<Caipu> caipuList = new ArrayList<Caipu>();
	private String url;
	private String date;
	private String dBefore;
	private Perdiet averagePerdiet; // 存放时间段的平均饮食或指定日期的平均饮食
	private float aucHypertension; // 存放该菜谱高血压 auc 值
	private float aucStomach; // 存放该菜谱养肠胃 auc 值
	private float aucDiabetes; // 存放该菜谱糖尿病 auc 值
	private String hypertensionStatus;
	private String stomachStatus;
	private String diabetesStatus;
	private String recommendMsg;
	private List<Caipuauc> recommendCaipuaucList = new ArrayList<>();
	private List<Caipu> recommendCaipuList = new ArrayList<Caipu>();
	private static float highLevel = 0.8f;
	private static float lowLevel = 0.5f;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	/**
	 * 保存菜谱中的3个auc值,本函数只用做生成数据，不参与前台页面
	 * @return
	 */
	public String save() {
		// Step 1 获取所有菜谱
		caipuList = caipuService.getAroundList("viewId", 401, 800);
//		caipuList = caipuService.getList("viewName", "蛋炒饭");
		for(Caipu caipu: caipuList) {
			Caipuauc caipuauc = getCaipuaucValue(caipu);
			caipuaucService.save(caipuauc);
		}
		return SUCCESS;
	}
	
	private Caipuauc getCaipuaucValue(Caipu caipu) {
		String parameter = "";
		parameter = caipu.getCalories() + "," + caipu.getCarbohydrate() + "," + caipu.getFat() + ","
				+ caipu.getProtein() + "," + caipu.getVitamine() + "," + caipu.getVta() + "," + caipu.getVtc() + ","
				+ caipu.getVte() + "," + caipu.getCarotene() + "," + caipu.getThiamine() + "," + caipu.getRiboflavin()
				+ "," + caipu.getYansuan() + "," + caipu.getCholesterol() + "," + caipu.getMg() + "," + caipu.getCa()
				+ "," + caipu.getIron() + "," + caipu.getZinc() + "," + caipu.getCopper() + "," + caipu.getMn() + ","
				+ caipu.getK() + "," + caipu.getP() + "," + caipu.getNa() + "," + caipu.getSe();
		Float aucHypertensionTemp = Float.parseFloat(RecognizeModel.recoWestHypertension(parameter));
		Float aucStomachTemp = Float.parseFloat(RecognizeModel.recoWestStomach(parameter));
		Float aucDiabetesTemp = Float.parseFloat(RecognizeModel.recoWestDiabetes(parameter));
		return new Caipuauc(caipu.getViewId(), caipu.getViewName(), aucHypertensionTemp, aucStomachTemp, aucDiabetesTemp);
	}
	
	/**
	 * 根据用户最近3天的饮食来推荐合适的菜品
	 * @return
	 */
	public String recommend() {
		// Step 1 计算用户近3天饮食的三个auc值
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		dBefore = beforeDay(3);
		System.out.println("dBefore:"+dBefore);
		Users user = (Users) session.get("user");
		// 找到<日期，饮食list>
		Map<Date, List<Perdiet>> perdietMap = perdietService.findbytime(user.getUserId(), dBefore, date);
		if(null == perdietMap || perdietMap.size() == 0) {
			System.out.println(dBefore + "-" + date + "下没有相关饮食");
			return SUCCESS;
		}
		// 计算日期平均值
		List<Perdiet> tempPerdietList = new ArrayList<Perdiet>();
		for(Map.Entry<Date, List<Perdiet>> entry: perdietMap.entrySet()) {
			tempPerdietList.addAll(entry.getValue());
		}
		averagePerdiet = getAveragePerdiet(tempPerdietList);
		String alaisName = dBefore + "-" + date;
		averagePerdiet.setVname(alaisName);
		String parameter = getPerdietParameter(averagePerdiet);
		// 得到 hypertension、stomach、diabetes预测的 auc 值
		aucHypertension = Float.parseFloat(RecognizeModel.recoWestHypertension(parameter));
		aucStomach = Float.parseFloat(RecognizeModel.recoWestStomach(parameter));
		aucDiabetes = Float.parseFloat(RecognizeModel.recoWestDiabetes(parameter));
		// Step 2 评估等级。0.8-1.0 正常，0.5-0.8提醒，0.0-0.5严重提醒
		hypertensionStatus = getLevel(aucHypertension);
		stomachStatus = getLevel(aucStomach);
		diabetesStatus = getLevel(aucDiabetes);
		// Step 3 根据不同的级别推荐不同的菜谱（前端附有链接）
		if("A".equals(hypertensionStatus) && "A".equals(stomachStatus) && "A".equals(diabetesStatus) ) {
			recommendMsg = "饮食正常！";
		} else {
			if(aucHypertension < highLevel)
				aucHypertension = highLevel;
			if(aucStomach < highLevel)
				aucStomach = highLevel;
			if(aucDiabetes < highLevel)
				aucDiabetes = highLevel;
			recommendCaipuaucList = caipuaucService.getBestPerdietAucList("aucHypertension", aucHypertension, "aucStomach", aucStomach, "aucDiabetes", aucDiabetes);
			// OPTION TODO 链接，根据caipuauc来查询caipu
		}
		return SUCCESS;
	}
	
	
	/**
	 * A表示正常，B表示提醒，C表示严重
	 * @param auc
	 * @return
	 */
	private String getLevel(float auc) {
		if(auc >= highLevel) {
			return "A";
		} else if(auc >= lowLevel && auc <= highLevel) {
			return "B";
		} else {
			return "C";
		}
	}
	
	private String beforeDay(int n) {
		Date dNow = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dNow);
		calendar.add(Calendar.DAY_OF_MONTH, n * (-1));
		Date before = calendar.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(before);
	}
	
	private Perdiet getAveragePerdiet(List<Perdiet> tempPerdiets) {
		Perdiet averagePerdiet = new Perdiet();
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
		for(Perdiet perdiet:tempPerdiets) {
			calories += perdiet.getCalories();
			carbohydrate += perdiet.getCarbohydrate();
			fat += perdiet.getFat();
			protein += perdiet.getProtein();
			vitamine += perdiet.getVitamine();
			vta += perdiet.getVta();
			vtc += perdiet.getVtc();
			vte += perdiet.getVte();
			carotene += perdiet.getCarotene();
			thiamine += perdiet.getThiamine();
			riboflavin += perdiet.getRiboflavin();
			yansuan += perdiet.getYansuan();
			cholesterol += perdiet.getCholesterol();
			mg += perdiet.getMg();
			ca += perdiet.getCa();
			iron += perdiet.getIron();
			zinc += perdiet.getZinc();
			copper += perdiet.getCopper();
			mn += perdiet.getMn();
			k += perdiet.getK();
			p += perdiet.getP();
			na += perdiet.getNa();
			se += perdiet.getSe();
		}
		averagePerdiet.setCalories(calories/tempPerdiets.size());
		averagePerdiet.setCarbohydrate(carbohydrate/tempPerdiets.size());
		averagePerdiet.setFat(fat/tempPerdiets.size());
		averagePerdiet.setProtein(protein/tempPerdiets.size());
		averagePerdiet.setVitamine(vitamine/tempPerdiets.size());
		averagePerdiet.setVta(vta/tempPerdiets.size());
		averagePerdiet.setVtc(vtc/tempPerdiets.size());
		averagePerdiet.setVte(vte/tempPerdiets.size());
		averagePerdiet.setNa(na/tempPerdiets.size());
		averagePerdiet.setCarotene(carotene/tempPerdiets.size());
		averagePerdiet.setThiamine(thiamine/tempPerdiets.size());
		averagePerdiet.setRiboflavin(riboflavin/tempPerdiets.size());
		averagePerdiet.setYansuan(yansuan/tempPerdiets.size());
		averagePerdiet.setCholesterol(cholesterol/tempPerdiets.size());
		averagePerdiet.setMg(mg/tempPerdiets.size());
		averagePerdiet.setCa(ca/tempPerdiets.size());
		averagePerdiet.setIron(iron/tempPerdiets.size());
		averagePerdiet.setZinc(zinc/tempPerdiets.size());
		averagePerdiet.setCopper(copper/tempPerdiets.size());
		averagePerdiet.setMn(mn/tempPerdiets.size());
		averagePerdiet.setK(k/tempPerdiets.size());
		averagePerdiet.setP(p/tempPerdiets.size());
		averagePerdiet.setSe(se/tempPerdiets.size());
		return averagePerdiet;
	}
	
	private String getPerdietParameter(Perdiet perdiet) {
		String parameter = "";
		parameter = perdiet.getCalories() + "," + perdiet.getCarbohydrate() + "," + perdiet.getFat() + ","
				+ perdiet.getProtein() + "," + perdiet.getVitamine() + "," + perdiet.getVta() + "," + perdiet.getVtc() + ","
				+ perdiet.getVte() + "," + perdiet.getCarotene() + "," + perdiet.getThiamine() + "," + perdiet.getRiboflavin()
				+ "," + perdiet.getYansuan() + "," + perdiet.getCholesterol() + "," + perdiet.getMg() + "," + perdiet.getCa()
				+ "," + perdiet.getIron() + "," + perdiet.getZinc() + "," + perdiet.getCopper() + "," + perdiet.getMn() + ","
				+ perdiet.getK() + "," + perdiet.getP() + "," + perdiet.getNa() + "," + perdiet.getSe();
		return parameter;
	}

	public List<Caipuauc> getCaipuaucList() {
		return caipuaucList;
	}

	public void setCaipuaucList(List<Caipuauc> caipuaucList) {
		this.caipuaucList = caipuaucList;
	}

	public CaipuService getCaipuService() {
		return caipuService;
	}

	public void setCaipuService(CaipuService caipuService) {
		this.caipuService = caipuService;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getdBefore() {
		return dBefore;
	}

	public void setdBefore(String dBefore) {
		this.dBefore = dBefore;
	}

	public Perdiet getAveragePerdiet() {
		return averagePerdiet;
	}

	public void setAveragePerdiet(Perdiet averagePerdiet) {
		this.averagePerdiet = averagePerdiet;
	}

	public float getAucHypertension() {
		return aucHypertension;
	}

	public void setAucHypertension(float aucHypertension) {
		this.aucHypertension = aucHypertension;
	}

	public float getAucStomach() {
		return aucStomach;
	}

	public void setAucStomach(float aucStomach) {
		this.aucStomach = aucStomach;
	}

	public float getAucDiabetes() {
		return aucDiabetes;
	}

	public void setAucDiabetes(float aucDiabetes) {
		this.aucDiabetes = aucDiabetes;
	}

	public String getHypertensionStatus() {
		return hypertensionStatus;
	}

	public void setHypertensionStatus(String hypertensionStatus) {
		this.hypertensionStatus = hypertensionStatus;
	}

	public String getStomachStatus() {
		return stomachStatus;
	}

	public void setStomachStatus(String stomachStatus) {
		this.stomachStatus = stomachStatus;
	}

	public String getDiabetesStatus() {
		return diabetesStatus;
	}

	public void setDiabetesStatus(String diabetesStatus) {
		this.diabetesStatus = diabetesStatus;
	}

	public String getRecommendMsg() {
		return recommendMsg;
	}

	public void setRecommendMsg(String recommendMsg) {
		this.recommendMsg = recommendMsg;
	}

	public List<Caipu> getCaipuList() {
		return caipuList;
	}

	public void setCaipuList(List<Caipu> caipuList) {
		this.caipuList = caipuList;
	}

	public List<Caipuauc> getRecommendCaipuaucList() {
		return recommendCaipuaucList;
	}

	public void setRecommendCaipuaucList(List<Caipuauc> recommendCaipuaucList) {
		this.recommendCaipuaucList = recommendCaipuaucList;
	}

	public List<Caipu> getRecommendCaipuList() {
		return recommendCaipuList;
	}

	public void setRecommendCaipuList(List<Caipu> recommendCaipuList) {
		this.recommendCaipuList = recommendCaipuList;
	}
	
	
}
