package com.ustb.food.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.TcmCaipu;
import com.ustb.food.entity.TcmCaipuauc;
import com.ustb.food.entity.TcmPerdiet;
import com.ustb.food.entity.Users;
import com.ustb.food.service.TcmCaipuService;
import com.ustb.food.service.TcmCaipuaucService;
import com.ustb.food.service.TcmPerdietService;
import com.ustb.food.util.RecognizeModel;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月2日 下午5:32:04
 */
@Component("/front/TcmCaipuaucAction")
@Scope("prototype")
public class TcmCaipuaucAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private TcmPerdietService tcmPerdietService;
	@Resource
	private TcmCaipuaucService tcmCaipuaucService;
	@Resource
	private TcmCaipuService tcmCaipuService;
	
	private Map<String, Object> session;
	private float aucHypertension; // 存放该菜谱高血压 auc 值
	private float aucStomach; // 存放该菜谱养肠胃 auc 值
	private float aucDiabetes; // 存放该菜谱糖尿病 auc 值
	
	private String date;
	private String dBefore;
	
	private static float highLevel = 0.8f;
	private static float lowLevel = 0.5f;
	
	private TcmPerdiet averageTcmPerdiet; // 存放时间段的平均饮食或指定日期的平均饮食
	private String hypertensionStatus;
	private String stomachStatus;
	private String diabetesStatus;
	private String recommendMsg;
	private List<TcmCaipuauc> recommendTcmCaipuaucList = new ArrayList<>();
	
	private int viewId;
	private TcmCaipu tcmCaipu;
	
	
	public String recommend() {
		// Step 1 计算用户近3天饮食的三个auc值
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		dBefore = beforeDay(3);
		System.out.println("dBefore:"+dBefore);
		Users user = (Users) session.get("user");
		// 找到<日期，饮食list>
		Map<Date, List<TcmPerdiet>> tcmPerdietMap = tcmPerdietService.findbytime(user.getUserId(), dBefore, date);
		if(null == tcmPerdietMap || tcmPerdietMap.size() == 0) {
			System.out.println(dBefore + "-" + date + "下没有相关饮食");
			return SUCCESS;
		}
		// 计算日期平均值
		List<TcmPerdiet> tempTcmPerdietList = new ArrayList<TcmPerdiet>();
		for(Map.Entry<Date, List<TcmPerdiet>> entry: tcmPerdietMap.entrySet()) {
			tempTcmPerdietList.addAll(entry.getValue());
		}
		averageTcmPerdiet = getAverageTcmPerdiet(tempTcmPerdietList);
		String alaisName = dBefore + "-" + date;
		averageTcmPerdiet.setName(alaisName);
		String parameter = getTcmPerdietParameter(averageTcmPerdiet);
		// 得到 hypertension、stomach、diabetes预测的 auc 值
		aucHypertension = Float.parseFloat(RecognizeModel.recoTcmHypertension(parameter));
		aucStomach = Float.parseFloat(RecognizeModel.recoTcmStomach(parameter));
		aucDiabetes = Float.parseFloat(RecognizeModel.recoTcmDiabetes(parameter));
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
			recommendTcmCaipuaucList = tcmCaipuaucService.getBestTcmPerdietAucList("aucHypertension", aucHypertension, "aucStomach", aucStomach, "aucDiabetes", aucDiabetes);
		}
		return SUCCESS;
	}
	
	private String getTcmPerdietParameter(TcmPerdiet tcmPerdiet) {
		String parameter = "";
		parameter = tcmPerdiet.getFei() + "," + tcmPerdiet.getXin() + "," + tcmPerdiet.getPiwei() + ","
				+ tcmPerdiet.getGandan() + "," + tcmPerdiet.getShen() + "," + tcmPerdiet.getSanjiao() + "," + tcmPerdiet.getQi() + ","
				+ tcmPerdiet.getXue() + "," + tcmPerdiet.getYin() + "," + tcmPerdiet.getYang() + "," + tcmPerdiet.getXing()
				+ "," + tcmPerdiet.getKu() + "," + tcmPerdiet.getGan() + "," + tcmPerdiet.getSuan() + "," + tcmPerdiet.getXian()
				+ "," + tcmPerdiet.getDan() + "," + tcmPerdiet.getSheng() + "," + tcmPerdiet.getJiang() + "," + tcmPerdiet.getChen() + ","
				+ tcmPerdiet.getFu() + "," + tcmPerdiet.getHezhong();
		return parameter;
	}
	
	private TcmPerdiet getAverageTcmPerdiet(List<TcmPerdiet> tempTcmPerdiets) {
		TcmPerdiet averageTcmPerdiet = new TcmPerdiet();
		double fei = 0.0;
		double xin = 0.0;
		double piwei = 0.0;
		double gandan = 0.0;
		double shen = 0.0;
		double sanjiao = 0.0;
		double qi = 0.0;
		double xue = 0.0;
		double yin = 0.0;
		double yang = 0.0;
		double xing = 0.0;
		double ku = 0.0;
		double gan = 0.0;
		double suan = 0.0;
		double xian = 0.0;
		double dan = 0.0;
		double sheng = 0.0;
		double jiang = 0.0;
		double chen = 0.0;
		double fu = 0.0;
		double hezhong = 0.0;
		for(TcmPerdiet tcmPerdiet:tempTcmPerdiets) {
			fei += tcmPerdiet.getFei();
			xin += tcmPerdiet.getXin();
			piwei += tcmPerdiet.getPiwei();
			gandan += tcmPerdiet.getGandan();
			shen += tcmPerdiet.getShen();
			sanjiao += tcmPerdiet.getSanjiao();
			qi += tcmPerdiet.getQi();
			xue += tcmPerdiet.getXue();
			yin += tcmPerdiet.getYin();
			yang += tcmPerdiet.getYang();
			xing += tcmPerdiet.getXing();
			ku += tcmPerdiet.getKu();
			gan += tcmPerdiet.getGan();
			suan += tcmPerdiet.getSuan();
			xian += tcmPerdiet.getXian();
			dan += tcmPerdiet.getDan();
			sheng += tcmPerdiet.getSheng();
			jiang += tcmPerdiet.getJiang();
			chen += tcmPerdiet.getChen();
			fu += tcmPerdiet.getFu();
			hezhong += tcmPerdiet.getHezhong();
		}
		averageTcmPerdiet.setFei(fei/tempTcmPerdiets.size());
		averageTcmPerdiet.setXin(xin/tempTcmPerdiets.size());
		averageTcmPerdiet.setPiwei(piwei/tempTcmPerdiets.size());
		averageTcmPerdiet.setGandan(gandan/tempTcmPerdiets.size());
		averageTcmPerdiet.setShen(shen/tempTcmPerdiets.size());
		averageTcmPerdiet.setSanjiao(sanjiao/tempTcmPerdiets.size());
		averageTcmPerdiet.setQi(qi/tempTcmPerdiets.size());
		averageTcmPerdiet.setXue(xue/tempTcmPerdiets.size());
		averageTcmPerdiet.setYin(yin/tempTcmPerdiets.size());
		averageTcmPerdiet.setYang(yang/tempTcmPerdiets.size());
		averageTcmPerdiet.setXing(xing/tempTcmPerdiets.size());
		averageTcmPerdiet.setKu(ku/tempTcmPerdiets.size());
		averageTcmPerdiet.setGan(gan/tempTcmPerdiets.size());
		averageTcmPerdiet.setSuan(suan/tempTcmPerdiets.size());
		averageTcmPerdiet.setXian(xian/tempTcmPerdiets.size());
		averageTcmPerdiet.setDan(dan/tempTcmPerdiets.size());
		averageTcmPerdiet.setSheng(sheng/tempTcmPerdiets.size());
		averageTcmPerdiet.setJiang(jiang/tempTcmPerdiets.size());
		averageTcmPerdiet.setChen(chen/tempTcmPerdiets.size());
		averageTcmPerdiet.setFu(fu/tempTcmPerdiets.size());
		averageTcmPerdiet.setHezhong(hezhong/tempTcmPerdiets.size());
		
		return averageTcmPerdiet;
	}
	
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
	
	public String detail() {
		tcmCaipu = tcmCaipuService.get(viewId);
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
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

	public static float getHighLevel() {
		return highLevel;
	}

	public static void setHighLevel(float highLevel) {
		TcmCaipuaucAction.highLevel = highLevel;
	}

	public static float getLowLevel() {
		return lowLevel;
	}

	public static void setLowLevel(float lowLevel) {
		TcmCaipuaucAction.lowLevel = lowLevel;
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

	public List<TcmCaipuauc> getRecommendTcmCaipuaucList() {
		return recommendTcmCaipuaucList;
	}

	public void setRecommendTcmCaipuaucList(List<TcmCaipuauc> recommendTcmCaipuaucList) {
		this.recommendTcmCaipuaucList = recommendTcmCaipuaucList;
	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}

	public TcmCaipu getTcmCaipu() {
		return tcmCaipu;
	}

	public void setTcmCaipu(TcmCaipu tcmCaipu) {
		this.tcmCaipu = tcmCaipu;
	}

	public TcmPerdiet getAverageTcmPerdiet() {
		return averageTcmPerdiet;
	}

	public void setAverageTcmPerdiet(TcmPerdiet averageTcmPerdiet) {
		this.averageTcmPerdiet = averageTcmPerdiet;
	}
	
}
