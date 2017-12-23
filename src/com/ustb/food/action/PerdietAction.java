package com.ustb.food.action;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Perdiet;
import com.ustb.food.entity.Perrecord;
import com.ustb.food.entity.PerrecordId;
import com.ustb.food.entity.Users;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.PerdietService;
import com.ustb.food.service.PerrecordService;
import com.ustb.food.service.UsersService;
import com.ustb.food.service.YuanliaoService;
import com.ustb.food.util.RecognizeModel;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月27日 下午8:56:19
 */
@Component("/front/PerdietAction")
@Scope("prototype")
public class PerdietAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	YuanliaoService yuanliaoService;
	@Autowired
	PerdietService perdietService;
	@Autowired
	UsersService userService;
	@Autowired
	PerrecordService perrecordService;
	private Map<String, Object> session;
	private String url;
	private List<Yuanliao> yllist = new ArrayList<Yuanliao>();
	private List<Integer> amountList = new ArrayList<Integer>();
	private String sign = null;
	private List<String> nameLike = new ArrayList<String>();
	private String nameList;
	private String ylId;
	private String amount;
	private List<String> idl = new ArrayList<String>();
	private List<String> al = new ArrayList<String>();
	private List<Perdiet> perdietList = new ArrayList<Perdiet>();
	private Users user;
	private String vname;
	private Date insertDate;
	private String date;
	private String startDate;
	private String endDate;
	private String targetDate;
	private Map<Date, List<Perdiet>> perdietMap;
	private Map<Date, Perdiet> averageDayPerdietMap; // 存放每天的平均饮食
	private Perdiet averagePerdiet; // 存放时间段的平均饮食或指定日期的平均饮食
	private String userName;
	private int count;
	private int pc;
	private int pn;
	private int s;
	private double p;
	private double aucHypertension; // 存放该菜谱高血压 auc 值
	private double aucStomach; // 存放该菜谱养肠胃 auc 值
	private double aucDiabetes; // 存放该菜谱糖尿病 auc 值

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	private TreeMap sumMap = new TreeMap<String, Perdiet>();

	private InputStream excelFile;

	public String my() {
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (sign == null) {
			yllist = yuanliaoService.getList();
			// 获取当前日期已经添加的饮食
			Users user = (Users) session.get("user");
			perdietList = perdietService.findbytime(user.getUserId(), date);
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

	public String addSubmit() {
		fenjie();
		save();
		url = "Perdiet/my";
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
		// step 3:插入菜谱（表perdiet）并插入原料关系对应表（表 perrecord）
		Users users = userService.get(user.getUserId());
		Perdiet perdiet = new Perdiet(users, vname, calories, carbohydrate, fat, protein, vitamine, vta, vtc, vte,
				carotene, thiamine, riboflavin, yansuan, cholesterol, mg, ca, iron, zinc, copper, mn, k, p, na, se,
				insertDate, new ArrayList<Perrecord>());
		perdietService.save(perdiet);
		// 存储外键关系
		List<PerrecordId> pdl = new ArrayList<PerrecordId>();
		for (int i = 0; i < idl.size(); i++) {
			PerrecordId pd = new PerrecordId(perdiet.getPgId(), Integer.parseInt(idl.get(i)), users.getUserId());
			pdl.add(pd);
		}
		for (int i = 0; i < idl.size(); i++) {
			Perrecord perrecord = new Perrecord(pdl.get(i), perdiet, users,
					yuanliaoService.get(Integer.parseInt(idl.get(i))), Integer.parseInt(al.get(i)), insertDate);
			perrecordService.save(perrecord);
		}
	}

	
	/**
	 * 饮食目录
	 * @return
	 */
	public String list() {
		// count表示一共有几条记录，pc表示总共有几页，pn表示的是当前是第几页
		count = perdietService.countAll();
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
		perdietList = perdietService.getList(pn, pageSize);
		return SUCCESS;
	}

	/**
	 * 饮食搜索
	 * @return
	 */
	public String search() {
		if (null != vname) {
			System.out.println(vname);
			perdietList = perdietService.getList("vname", vname);
		}
		return SUCCESS;
	}
	
	/**
	 * 饮食曲线
	 * @return
	 */
	public String compare() {
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(null != startDate && null != endDate) {
			Users user = (Users) session.get("user");
			// 找到<日期，饮食list>
			perdietMap = perdietService.findbytime(user.getUserId(), startDate, endDate);
			// 计算<日期，该日期平均值>
			averageDayPerdietMap = getAveragePerdietMap(perdietMap);
		}
		return SUCCESS;
	}
	
	/**
	 * 饮食曲线-01
	 * @param tempPerdietMap
	 * @return
	 */
	private Map<Date, Perdiet> getAveragePerdietMap(Map<Date, List<Perdiet>> tempPerdietMap) {
		Map<Date, Perdiet> tempAverageDayPerdietMap = new HashMap<Date, Perdiet>();
		for(Map.Entry<Date, List<Perdiet>> entry: tempPerdietMap.entrySet()) {
			List<Perdiet> tempPerdiets = entry.getValue();
			Perdiet averagePerdiet = getAveragePerdiet(tempPerdiets);
			tempAverageDayPerdietMap.put(entry.getKey(), averagePerdiet);
		}
		return tempAverageDayPerdietMap;
	}
	
	/**
	 * 饮食曲线-02
	 * @param tempPerdiets
	 * @return
	 */
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
	
	/**
	 * 饮食偏向
	 * @return
	 */
	public String bias() {
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		// 查询时间段的饮食偏向
		if(null != startDate && null != endDate) {
			Users user = (Users) session.get("user");
			// 找到<日期，饮食list>
			perdietMap = perdietService.findbytime(user.getUserId(), startDate, endDate);
			if(null == perdietMap || perdietMap.size() == 0) {
				System.out.println(startDate + "-" + endDate + "下没有相关饮食");
				return SUCCESS;
			}
			// 计算日期平均值
			List<Perdiet> tempPerdietList = new ArrayList<Perdiet>();
			for(Map.Entry<Date, List<Perdiet>> entry: perdietMap.entrySet()) {
				tempPerdietList.addAll(entry.getValue());
			}
			averagePerdiet = getAveragePerdiet(tempPerdietList);
			String alaisName = startDate + "-" + endDate;
			averagePerdiet.setVname(alaisName);
			String parameter = getPerdietParameter(averagePerdiet);
			// 得到 hypertension、stomach、diabetes预测的 auc 值
			aucHypertension = Double.parseDouble(RecognizeModel.recoWestHypertension(parameter));
			System.out.println(startDate + "---" + endDate + "的高血压概率：" + aucHypertension);
			aucStomach = Double.parseDouble(RecognizeModel.recoWestStomach(parameter));
			System.out.println(startDate + "---" + endDate + "的养肠胃病概率：" + aucStomach);
			aucDiabetes = Double.parseDouble(RecognizeModel.recoWestDiabetes(parameter));
			System.out.println(startDate + "---" + endDate + "的糖尿病概率：" + aucDiabetes);
		}
		// 查询指定日期的饮食偏向
		if(null != targetDate) {
			// 获取当前日期已经添加的饮食
			Users user = (Users) session.get("user");
			perdietList = perdietService.findbytime(user.getUserId(), targetDate);
			if(null == perdietList || perdietList.size() == 0) {
				System.out.println(targetDate + "下没有相关饮食");
				return SUCCESS;
			}
			averagePerdiet = getAveragePerdiet(perdietList);
			averagePerdiet.setVname(targetDate);
			String parameter = getPerdietParameter(averagePerdiet);
			// 得到 hypertension、stomach、diabetes预测的 auc 值
			aucHypertension = Double.parseDouble(RecognizeModel.recoWestHypertension(parameter));
			System.out.println(targetDate + "的高血压概率：" + aucHypertension);
			aucStomach = Double.parseDouble(RecognizeModel.recoWestStomach(parameter));
			System.out.println(targetDate + "的养肠胃病概率：" + aucStomach);
			aucDiabetes = Double.parseDouble(RecognizeModel.recoWestDiabetes(parameter));
			System.out.println(targetDate + "的糖尿病概率：" + aucDiabetes);
		}
		return SUCCESS;
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
	
	
	/**
	 * 饮食推荐
	 * @return
	 */
	public String recommend() {
		return SUCCESS;
	}

//	public String delete() {
//		List<Perrecord> perrecordList = new ArrayList<Perrecord>();
//		perrecordList = perrecordService.perrecordList(user.getUserId(), date);
//		for (int i = 0; i < perrecordList.size(); i++) {
//			perrecordService.deleteObject(perrecordList.get(i));
//		}
//		List<Perdiet> list = perdietService.retrieveAndDelete(user.getUserId(), date);
//		for (int i = 0; i < list.size(); i++) {
//			perdietService.deleteObject(list.get(i));
//		}
//		url = "Perdiet/date";
//		return NONE;
//	}

//	public String perdietList() {
//		Users usero = (Users) session.get("user");
//		if (usero.getUserName().equals("system")) {
//			System.out.println(userName);
//			List<Users> list = userService.getList("userName", userName);
//			usero = list.get(0);
//			session.put("usero", usero);
//		}
//		Map perdietMap = perdietService.findbytime(usero.getUserId(), startDate, endDate);
//		TreeMap perdietMap1 = perdietService.findbytime_c(usero.getUserId(), startDate, endDate);
//		sumMap = perdietService.jisuanMap(perdietMap);
//		ArrayList list = new ArrayList();
//		Iterator it = perdietMap1.entrySet().iterator();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			Date key = (Date) entry.getKey();
//			// System.out.println("日期为"+key);
//			List<Perrecord> perrecordList = new ArrayList<Perrecord>();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String str = sdf.format(key);
//			perrecordList = perrecordService.perrecordList(user.getUserId(), str);
//			int summount = 0;
//			for (int i = 0; i < perrecordList.size(); i++) {
//				summount += perrecordList.get(i).getAmount();
//			}
//			// System.out.println("总和为"+summount);
//			list.add(summount);
//		}
//		session.put("summount", list);
//		return SUCCESS;
//	}

	public String date() {
		startDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		return SUCCESS;
	}

//	public String perrecordList() {
//		List<Perrecord> perrecordList = new ArrayList<Perrecord>();
//		perrecordList = perrecordService.perrecordList(user.getUserId(), date);
//		int summount = 0;
//		for (int i = 0; i < perrecordList.size(); i++) {
//			yllist.add(perrecordList.get(i).getYuanliao());
//			amountList.add(perrecordList.get(i).getAmount());
//			summount += perrecordList.get(i).getAmount();
//		}
//
//		return SUCCESS;
//	}

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

	public List<Yuanliao> getYllist() {
		return yllist;
	}

	public void setYllist(List<Yuanliao> yllist) {
		this.yllist = yllist;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<String> getNameLike() {
		return nameLike;
	}

	public void setNameLike(List<String> nameLike) {
		this.nameLike = nameLike;
	}

	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	public String getYlId() {
		return ylId;
	}

	public void setYlId(String ylId) {
		this.ylId = ylId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<String> getIdl() {
		return idl;
	}

	public void setIdl(List<String> idl) {
		this.idl = idl;
	}

	public List<String> getAl() {
		return al;
	}

	public void setAl(List<String> al) {
		this.al = al;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public List<Perdiet> getPerdietList() {
		return perdietList;
	}

	public void setPerdietList(List<Perdiet> perdietList) {
		this.perdietList = perdietList;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public List<Integer> getAmountList() {
		return amountList;
	}

	public void setAmountList(List<Integer> amountList) {
		this.amountList = amountList;
	}
	
	public Map<Date, Perdiet> getAverageDayPerdietMap() {
		return averageDayPerdietMap;
	}

	public void setAverageDayPerdietMap(Map<Date, Perdiet> averageDayPerdietMap) {
		this.averageDayPerdietMap = averageDayPerdietMap;
	}
	
	public Perdiet getAveragePerdiet() {
		return averagePerdiet;
	}

	public void setAveragePerdiet(Perdiet averagePerdiet) {
		this.averagePerdiet = averagePerdiet;
	}

	public Map<Date, List<Perdiet>> getPerdietMap() {
		return perdietMap;
	}

	public void setPerdietMap(Map<Date, List<Perdiet>> perdietMap) {
		this.perdietMap = perdietMap;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
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
	
//
//	// 导出Excel
//	public String ExcelExport() throws Exception {
//		Users usero = (Users) session.get("user");
//		perdietMap = perdietService.findbytime(usero.getUserId(), startDate, endDate);
//		sumMap = perdietService.jisuanMap(perdietMap);
//		List<Perdiet> list = new ArrayList<Perdiet>();
//		Iterator it = sumMap.entrySet().iterator();
//		;
//		while (it.hasNext()) {
//			Entry entry = (Entry) it.next();
//			list.add((Perdiet) entry.getValue());
//		}
//		Workbook workbook = new HSSFWorkbook();
//		Sheet sheet = workbook.createSheet("西医标准");
//		Row row = sheet.createRow(0);
//		row.createCell(0).setCellValue("日期");
//		row.createCell(1).setCellValue("钠");
//		row.createCell(2).setCellValue("蛋白质");
//		row.createCell(3).setCellValue("脂肪");
//		row.createCell(4).setCellValue("卡路里");
//		row.createCell(5).setCellValue("碳水化合物");
//		CellStyle cellStyle = workbook.createCellStyle();
//		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
//		for (int i = 1; i <= list.size(); i++) {
//			Perdiet perdiet = list.get(i - 1);
//			row = sheet.createRow(i);
//			row.createCell(0).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(perdiet.getInsertdate()));
//			row.createCell(1).setCellValue(perdiet.getNa());
//			row.createCell(2).setCellValue(perdiet.getProtein());
//			row.createCell(3).setCellValue(perdiet.getFat());
//			row.createCell(4).setCellValue(perdiet.getCalories());
//			row.createCell(5).setCellValue(perdiet.getCarbohydrate());
//		}
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		workbook.write(baos);
//		// excelFile是和struts中的 <param name="inputName">excelFile</param>相对应
//		excelFile = new ByteArrayInputStream(baos.toByteArray());
//		baos.close();
//		return "excel";
//	}

}
