package com.ustb.food.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Caipu;
import com.ustb.food.entity.Guanxi;
import com.ustb.food.entity.GuanxiId;
import com.ustb.food.entity.Users;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.CaipuService;
import com.ustb.food.service.GuanxiService;
import com.ustb.food.service.YuanliaoService;
import com.ustb.food.util.RecognizeModel;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月27日 下午8:45:12
 */
@Component("/front/CaipuAction")
@Scope("prototype")
public class CaipuAction extends ActionSupport implements SessionAware, RequestAware {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	CaipuService caipuService;
	@Autowired
	YuanliaoService yuanliaoService;
	@Autowired
	GuanxiService guanxiService;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private String url;
	private List<Yuanliao> yllist = new ArrayList<Yuanliao>();
	private String ylId;
	private String amount;
	private List<String> idl = new ArrayList<String>();
	private List<String> al = new ArrayList<String>();
	private List<String> nameLike = new ArrayList<String>();
	private String viewName;
	private List<Caipu> caipuList = new ArrayList<Caipu>();
	private List<Caipu> caipuList1 = new ArrayList<Caipu>();
	private List<Caipu> caipuList2 = new ArrayList<Caipu>();
	private String cpName1;
	private String cpName2;
	private List<Yuanliao> yuanliaoList;
	private List<Guanxi> guanxiList;
	private int pc;
	private int pn;
	private int s;
	private int maId;
	private Yuanliao yuanliao;
	private String sign = null;
	private String nameList;
	private int count;
	private double p;
	private String caipuName;
	private String source;
	private String cpName;
	private String viewName_new;
	private double aucHypertension; // 存放该菜谱高血压 auc 值
	private double aucStomach; // 存放该菜谱养肠胃 auc 值
	private double aucDiabetes; // 存放该菜谱糖尿病 auc 值
	private Caipu caipu;
	private int viewId;

	public String getViewName_new() {
		return viewName_new;
	}

	public void setViewName_new(String viewName_new) {
		this.viewName_new = viewName_new;
	}

	public String getCaipuName() {
		return caipuName;
	}

	public void setCaipuName(String caipuName) {
		this.caipuName = caipuName;
	}

	public String add() {
		if (sign == null) {
			// 加载原料下拉列表
			yllist = yuanliaoService.getList();
		}
		if (sign != null) {
			String[] name = nameList.split("[,，]");
			for (int i = 0; i < name.length; i++) {
				nameLike.add(name[i]);
			}
			yllist = yuanliaoService.findbyList(nameLike);
		}
		return SUCCESS;
	}

	public String compare() {
		if (null != cpName1 && null != cpName2) {
			caipuList1 = caipuService.getList("viewName", cpName1);
			caipuList2 = caipuService.getList("viewName", cpName2);
		}
		return SUCCESS;
	}

	@Override
	public void validate() {
		super.validate();
	}

	public String doCompare() {
		return NONE;
	}

	public String theList() {
		Users users = (Users) session.get("user");
		yuanliao = yuanliaoService.get(maId);
		guanxiList = yuanliao.getGuanxis();
		for (int i = 0; i < guanxiList.size(); i++) {
			Caipu caipu = new Caipu();
			caipu = guanxiList.get(i).getCaipu();
			caipuList.add(caipu);
		}
		System.out.println("users" + users);
		if (users.getUserName().equals("system")) {
			return "other";
		} else {
			return SUCCESS;
		}
	}

	public String list() {
		// count表示一共有几条记录，pc表示总共有几页，pn表示的是当前是第几页
		count = caipuService.countAll();
		int pageSize = 10;
		if (count % pageSize == 0) {
			// pc表示总页数
			pc = count / pageSize;
		} else if (count % pageSize != 0) {
			pc = count / pageSize + 1;
		}
		if (pn < 1) {
			pn = 1;
		}
		if (pn > pc) {
			pn = pc;
		}
		s = pc / 10;
		p = pn / 10;
		caipuList = caipuService.getList(pn, pageSize);
		return SUCCESS;
	}

	public String addSubmit() {
		fenjie();
		save();
		save_tcm();
		url = "Caipu/list";
		return NONE;
	}

	public void fenjie() {
		String[] id = ylId.split(",");
		for (int i = 0; i < id.length; i++) {
			idl.add(id[i]);
		}
		String[] a = amount.split(",");
		for (int i = 0; i < a.length; i++) {
			al.add(a[i]);
		}
	}

	public void save() {
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

		Double ylSum = 0.0;
		Double elem = 0.0;
		Double caloriesSum = 0.0;
		Double carbohydrateSum = 0.0;
		Double fatSum = 0.0;
		Double proteinSum = 0.0;
		Double vitamineSum = 0.0;
		Double vtaSum = 0.0;
		Double vtcSum = 0.0;
		Double vteSum = 0.0;
		Double caroteneSum = 0.0;
		Double thiamineSum = 0.0;
		Double riboflavinSum = 0.0;
		Double yansuanSum = 0.0;
		Double cholesterolSum = 0.0;
		Double mgSum = 0.0;
		Double caSum = 0.0;
		Double ironSum = 0.0;
		Double zincSum = 0.0;
		Double copperSum = 0.0;
		Double mnSum = 0.0;
		Double kSum = 0.0;
		Double pSum = 0.0;
		Double naSum = 0.0;
		Double seSum = 0.0;

		// step 1:把原料的g相加到一起
		for (int i = 0; i < al.size(); ++i) {
			ylSum += Double.parseDouble(al.get(i));
		}
		// step 2:依次从数据库查出每种原料的成份
		for (int i = 0; i < idl.size(); ++i) {
			Yuanliao yuanliao = yuanliaoService.get(Integer.parseInt(idl.get(i)));
			Double ylAmount = Double.parseDouble(al.get(i));
			// 热量含量 calories
			caloriesSum += yuanliao.getCalories() * ylAmount;
			// 碳水化合物含量 carbohydrate
			carbohydrateSum += yuanliao.getCarbohydrate() * ylAmount;
			// 脂肪含量 fat
			fatSum += yuanliao.getFat() * ylAmount;
			// 蛋白质含量 protein
			proteinSum += yuanliao.getProtein() * ylAmount;
			// 维生素含量 vitamine
			vitamineSum += yuanliao.getVitamine() * ylAmount;
			// 维生素A含量 vta
			vtaSum += yuanliao.getVta() * ylAmount;
			// 维生素A含量 vtc
			vtcSum += yuanliao.getVtc() * ylAmount;
			// 维生素A含量 vte
			vteSum += yuanliao.getVte() * ylAmount;
			// 胡罗卜素含量 carotene
			caroteneSum += yuanliao.getCarotene() * ylAmount;
			// 维生素B1 thiamine
			thiamineSum += yuanliao.getThiamine() * ylAmount;
			// 维生素B2 riboflavin
			riboflavinSum += yuanliao.getRiboflavin() * ylAmount;
			// yansuan
			yansuanSum += yuanliao.getYansuan() * ylAmount;
			// 胆固醇 cholesterol
			cholesterolSum += yuanliao.getCholesterol() * ylAmount;
			// 镁 mg
			mgSum += yuanliao.getMg() * ylAmount;
			// 钙 ca
			caSum += yuanliao.getCa() * ylAmount;
			// 铁 iron
			ironSum += yuanliao.getIron() * ylAmount;
			// 锌 zinc
			zincSum += yuanliao.getZinc() * ylAmount;
			// 铜 cholesterol
			copperSum += yuanliao.getCopper() * ylAmount;
			// 锰 mn
			mnSum += yuanliao.getMn() * ylAmount;
			// 钾 k
			kSum += yuanliao.getK() * ylAmount;
			// 磷 p
			pSum += yuanliao.getP() * ylAmount;
			// 钠 na
			naSum += yuanliao.getNa() * ylAmount;
			// 硒 se
			seSum += yuanliao.getSe() * ylAmount;
		}
		DecimalFormat df = new DecimalFormat("#.00");
		elem = caloriesSum / ylSum;
		calories = Double.valueOf(df.format(elem));
		elem = carbohydrateSum / ylSum;
		carbohydrate = Double.valueOf(df.format(elem));
		elem = fatSum / ylSum;
		fat = Double.valueOf(df.format(elem));
		elem = proteinSum / ylSum;
		protein = Double.valueOf(df.format(elem));
		elem = vitamineSum / ylSum;
		vitamine = Double.valueOf(df.format(elem));
		elem = vtaSum / ylSum;
		vta = Double.valueOf(df.format(elem));
		elem = vtcSum / ylSum;
		vtc = Double.valueOf(df.format(elem));
		elem = vteSum / ylSum;
		vte = Double.valueOf(df.format(elem));
		elem = caroteneSum / ylSum;
		carotene = Double.valueOf(df.format(elem));
		elem = thiamineSum / ylSum;
		thiamine = Double.valueOf(df.format(elem));
		elem = riboflavinSum / ylSum;
		riboflavin = Double.valueOf(df.format(elem));
		elem = yansuanSum / ylSum;
		yansuan = Double.valueOf(df.format(elem));
		elem = cholesterolSum / ylSum;
		cholesterol = Double.valueOf(df.format(elem));
		elem = mgSum / ylSum;
		mg = Double.valueOf(df.format(elem));
		elem = caSum / ylSum;
		ca = Double.valueOf(df.format(elem));
		elem = ironSum / ylSum;
		iron = Double.valueOf(df.format(elem));
		elem = zincSum / ylSum;
		zinc = Double.valueOf(df.format(elem));
		elem = copperSum / ylSum;
		copper = Double.valueOf(df.format(elem));
		elem = mnSum / ylSum;
		mn = Double.valueOf(df.format(elem));
		elem = kSum / ylSum;
		k = Double.valueOf(df.format(elem));
		elem = pSum / ylSum;
		p = Double.valueOf(df.format(elem));
		elem = naSum / ylSum;
		na = Double.valueOf(df.format(elem));
		elem = seSum / ylSum;
		se = Double.valueOf(df.format(elem));
		// step 3:插入菜谱（表caipu）并插入原料关系对应表（表）
		List<Guanxi> gxlist = new ArrayList<Guanxi>();
		List<Guanxi> gxl = new ArrayList<Guanxi>();
		List<GuanxiId> gxidl = new ArrayList<GuanxiId>();
		Caipu cp = new Caipu(viewName, calories, carbohydrate, fat, protein, vitamine, vta, vtc, vte, carotene,
				thiamine, riboflavin, yansuan, cholesterol, mg, ca, iron, zinc, copper, mn, k, p, na, se, source,
				gxlist);
		System.out.println("开始保存菜谱......");
		caipuService.save(cp);
		System.out.println("保存菜谱成功......");
		for (int i = 0; i < idl.size(); i++) {
			GuanxiId gxi = new GuanxiId(cp.getViewId(), Integer.parseInt(idl.get(i)));
			gxidl.add(gxi);
		}
		for (int i = 0; i < idl.size(); i++) {
			Guanxi g = new Guanxi(gxidl.get(i), cp, yuanliaoService.get(Integer.parseInt(idl.get(i))),
					Integer.parseInt(al.get(i)));
			gxl.add(g);
		}
		System.out.println("开始保存关系......");
		for (int i = 0; i < idl.size(); i++) {
			guanxiService.save(gxl.get(i));
		}
		System.out.println("保存关系成功......");
	}
	
	/**
	 * 保存中医饮食
	 */
	private void save_tcm() {
		
	}

	public String delete() {
		List<Caipu> list = caipuService.getList("viewName", caipuName);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			int viewId = list.get(i).getViewId();
			System.out.println("viewId" + viewId);
			caipuService.delete(viewId);
		}
		request.put("msg", "删除成功");
		return "shanchu";
	}

	public String update() {
		fenjie();
		xiugai();
		url = "Caipu/search";
		return NONE;
	}

	public String search() {
		if (cpName != null) {
			// 查询提交
			caipuList = caipuService.getList("viewName", cpName);
		}
		return SUCCESS;
	}

	public String update_f() {
		return "chaxun";
	}

	public String westRecognize() {
		return SUCCESS;
	}

	public String westRecognizeSubmit() {
		// Step 1：得到要识别的菜谱详细信息
		caipuList = caipuService.getList("viewName", cpName);
		// Step 2：把菜谱信息内的元素拼接成字符串作为识别模型的入参
		String parameter = "";
		Caipu caipu = caipuList.get(0);
		parameter = caipu.getCalories() + "," + caipu.getCarbohydrate() + "," + caipu.getFat() + ","
				+ caipu.getProtein() + "," + caipu.getVitamine() + "," + caipu.getVta() + "," + caipu.getVtc() + ","
				+ caipu.getVte() + "," + caipu.getCarotene() + "," + caipu.getThiamine() + "," + caipu.getRiboflavin()
				+ "," + caipu.getYansuan() + "," + caipu.getCholesterol() + "," + caipu.getMg() + "," + caipu.getCa()
				+ "," + caipu.getIron() + "," + caipu.getZinc() + "," + caipu.getCopper() + "," + caipu.getMn() + ","
				+ caipu.getK() + "," + caipu.getP() + "," + caipu.getNa() + "," + caipu.getSe();
		// Step 3：得到 hypertension、stomach、diabetes预测的 auc 值
		aucHypertension = Double.parseDouble(RecognizeModel.recoWestHypertension(parameter));
		System.out.println(caipu.getViewName() + "的高血压概率：" + aucHypertension);
		aucStomach = Double.parseDouble(RecognizeModel.recoWestStomach(parameter));
		System.out.println(caipu.getViewName() + "的养肠胃病概率：" + aucStomach);
		aucDiabetes = Double.parseDouble(RecognizeModel.recoWestDiabetes(parameter));
		System.out.println(caipu.getViewName() + "的糖尿病概率：" + aucDiabetes);
		return "caipuWestRecognize";
	}
	
	public String detail() {
		caipu = caipuService.get(viewId);
		return SUCCESS;
	}

	public Object getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<Guanxi> getGuanxiList() {
		return guanxiList;
	}

	public void setGuanxiList(List<Guanxi> guanxiList) {
		this.guanxiList = guanxiList;
	}

	public Yuanliao getYuanliao() {
		return yuanliao;
	}

	public void setYuanliao(Yuanliao yuanliao) {
		this.yuanliao = yuanliao;
	}

	public int getMaId() {
		return maId;
	}

	public void setMaId(int maId) {
		this.maId = maId;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getPn() {
		return pn;
	}

	public void setPn(int pn) {
		this.pn = pn;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public List<Caipu> getCaipuList() {
		return caipuList;
	}

	public void setCaipuList(List<Caipu> caipuList) {
		this.caipuList = caipuList;
	}

	public List<Caipu> getCaipuList1() {
		return caipuList1;
	}

	public void setCaipuList1(List<Caipu> caipuList1) {
		this.caipuList1 = caipuList1;
	}

	public List<Caipu> getCaipuList2() {
		return caipuList2;
	}

	public void setCaipuList2(List<Caipu> caipuList2) {
		this.caipuList2 = caipuList2;
	}

	public String getCpName1() {
		return cpName1;
	}

	public void setCpName1(String cpName1) {
		this.cpName1 = cpName1;
	}

	public String getCpName2() {
		return cpName2;
	}

	public void setCpName2(String cpName2) {
		this.cpName2 = cpName2;
	}

	public List<Yuanliao> getYuanliaoList() {
		return yuanliaoList;
	}

	public void setYuanliaoList(List<Yuanliao> yuanliaoList) {
		this.yuanliaoList = yuanliaoList;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getYlId() {
		return ylId;
	}

	public void setYlId(String ylId) {
		this.ylId = ylId;
	}

	public List<Yuanliao> getYllist() {
		return yllist;
	}

	public void setYllist(List<Yuanliao> yllist) {
		this.yllist = yllist;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public double getAucHypertension() {
		return aucHypertension;
	}

	public void setAucHypertension(double aucHypertension) {
		this.aucHypertension = aucHypertension;
	}

	public double getAucStomach() {
		return aucStomach;
	}

	public void setAucStomach(double aucStomach) {
		this.aucStomach = aucStomach;
	}

	public double getAucDiabetes() {
		return aucDiabetes;
	}

	public void setAucDiabetes(double aucDiabetes) {
		this.aucDiabetes = aucDiabetes;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void xiugai() {
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

		Double ylSum = 0.0;
		Double elem = 0.0;
		Double caloriesSum = 0.0;
		Double carbohydrateSum = 0.0;
		Double fatSum = 0.0;
		Double proteinSum = 0.0;
		Double vitamineSum = 0.0;
		Double vtaSum = 0.0;
		Double vtcSum = 0.0;
		Double vteSum = 0.0;
		Double caroteneSum = 0.0;
		Double thiamineSum = 0.0;
		Double riboflavinSum = 0.0;
		Double yansuanSum = 0.0;
		Double cholesterolSum = 0.0;
		Double mgSum = 0.0;
		Double caSum = 0.0;
		Double ironSum = 0.0;
		Double zincSum = 0.0;
		Double copperSum = 0.0;
		Double mnSum = 0.0;
		Double kSum = 0.0;
		Double pSum = 0.0;
		Double naSum = 0.0;
		Double seSum = 0.0;
		// step 1:把原料的g相加到一起
		for (int i = 0; i < al.size(); ++i) {
			ylSum += Double.parseDouble(al.get(i).trim());
		}
		// step 2:依次从数据库查出每种原料的成份
		for (int i = 0; i < idl.size(); ++i) {
			Yuanliao yuanliao = yuanliaoService.get(Integer.parseInt(idl.get(i).trim()));
			Double ylAmount = Double.parseDouble(al.get(i).trim());
			// 热量含量 calories
			caloriesSum += yuanliao.getCalories() * ylAmount;
			// 碳水化合物含量 carbohydrate
			carbohydrateSum += yuanliao.getCarbohydrate() * ylAmount;
			// 脂肪含量 fat
			fatSum += yuanliao.getFat() * ylAmount;
			// 蛋白质含量 protein
			proteinSum += yuanliao.getProtein() * ylAmount;
			// 维生素含量 vitamine
			vitamineSum += yuanliao.getVitamine() * ylAmount;
			// 维生素A含量 vta
			vtaSum += yuanliao.getVta() * ylAmount;
			// 维生素A含量 vtc
			vtcSum += yuanliao.getVtc() * ylAmount;
			// 维生素A含量 vte
			vteSum += yuanliao.getVte() * ylAmount;
			// 胡罗卜素含量 carotene
			caroteneSum += yuanliao.getCarotene() * ylAmount;
			// 维生素B1 thiamine
			thiamineSum += yuanliao.getThiamine() * ylAmount;
			// 维生素B2 riboflavin
			riboflavinSum += yuanliao.getRiboflavin() * ylAmount;
			// yansuan
			yansuanSum += yuanliao.getYansuan() * ylAmount;
			// 胆固醇 cholesterol
			cholesterolSum += yuanliao.getCholesterol() * ylAmount;
			// 镁 mg
			mgSum += yuanliao.getMg() * ylAmount;
			// 钙 ca
			caSum += yuanliao.getCa() * ylAmount;
			// 铁 iron
			ironSum += yuanliao.getIron() * ylAmount;
			// 锌 zinc
			zincSum += yuanliao.getZinc() * ylAmount;
			// 铜 cholesterol
			copperSum += yuanliao.getCopper() * ylAmount;
			// 锰 mn
			mnSum += yuanliao.getMn() * ylAmount;
			// 钾 k
			kSum += yuanliao.getK() * ylAmount;
			// 磷 p
			pSum += yuanliao.getP() * ylAmount;
			// 钠 na
			naSum += yuanliao.getNa() * ylAmount;
			// 硒 se
			seSum += yuanliao.getSe() * ylAmount;
		}
		DecimalFormat df = new DecimalFormat("#.00");
		elem = caloriesSum / ylSum;
		calories = Double.valueOf(df.format(elem));
		elem = carbohydrateSum / ylSum;
		carbohydrate = Double.valueOf(df.format(elem));
		elem = fatSum / ylSum;
		fat = Double.valueOf(df.format(elem));
		elem = proteinSum / ylSum;
		protein = Double.valueOf(df.format(elem));
		elem = vitamineSum / ylSum;
		vitamine = Double.valueOf(df.format(elem));
		elem = vtaSum / ylSum;
		vta = Double.valueOf(df.format(elem));
		elem = vtcSum / ylSum;
		vtc = Double.valueOf(df.format(elem));
		elem = vteSum / ylSum;
		vte = Double.valueOf(df.format(elem));
		elem = caroteneSum / ylSum;
		carotene = Double.valueOf(df.format(elem));
		elem = thiamineSum / ylSum;
		thiamine = Double.valueOf(df.format(elem));
		elem = riboflavinSum / ylSum;
		riboflavin = Double.valueOf(df.format(elem));
		elem = yansuanSum / ylSum;
		yansuan = Double.valueOf(df.format(elem));
		elem = cholesterolSum / ylSum;
		cholesterol = Double.valueOf(df.format(elem));
		elem = mgSum / ylSum;
		mg = Double.valueOf(df.format(elem));
		elem = caSum / ylSum;
		ca = Double.valueOf(df.format(elem));
		elem = ironSum / ylSum;
		iron = Double.valueOf(df.format(elem));
		elem = zincSum / ylSum;
		zinc = Double.valueOf(df.format(elem));
		elem = copperSum / ylSum;
		copper = Double.valueOf(df.format(elem));
		elem = mnSum / ylSum;
		mn = Double.valueOf(df.format(elem));
		elem = kSum / ylSum;
		k = Double.valueOf(df.format(elem));
		elem = pSum / ylSum;
		p = Double.valueOf(df.format(elem));
		elem = naSum / ylSum;
		na = Double.valueOf(df.format(elem));
		elem = seSum / ylSum;
		se = Double.valueOf(df.format(elem));
		List<Caipu> list = caipuService.getList("viewName", viewName);
		Caipu caipu = list.get(0);
		caipu.setCa(ca);
		caipu.setCalories(calories);
		caipu.setCarbohydrate(carbohydrate);
		caipu.setCarotene(carotene);
		caipu.setCholesterol(cholesterol);
		caipu.setCopper(copper);
		caipu.setFat(fat);
		caipu.setIron(iron);
		caipu.setK(k);
		caipu.setMg(mg);
		caipu.setMn(mn);
		caipu.setZinc(zinc);
		caipu.setNa(na);
		caipu.setYansuan(yansuan);
		caipu.setVte(vte);
		caipu.setVtc(vtc);
		caipu.setVta(vta);
		caipu.setVitamine(vitamine);
		caipu.setThiamine(thiamine);
		caipu.setSe(se);
		caipu.setRiboflavin(riboflavin);
		caipu.setProtein(protein);
		caipu.setP(p);
		caipu.setViewName(viewName_new);
		List<Guanxi> gxl = new ArrayList<Guanxi>();
		int viewId = caipu.getViewId();
		for (int i = 0; i < idl.size(); i++) {
			int maId = Integer.parseInt(idl.get(i).trim());
			List<Guanxi> c = guanxiService.getC(viewId, maId);
			c.get(0).setAmount(Integer.parseInt(al.get(i).trim()));
			guanxiService.update(c.get(0));
			gxl.add(c.get(0));
		}
		caipu.setGuanxis(gxl);
		caipuService.update(caipu);
	}

	public Caipu getCaipu() {
		return caipu;
	}

	public void setCaipu(Caipu caipu) {
		this.caipu = caipu;
	}

	public int getViewId() {
		return viewId;
	}

	public void setViewId(int viewId) {
		this.viewId = viewId;
	}
	
}
