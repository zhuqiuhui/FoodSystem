package com.ustb.food.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.TcmPerdiet;
import com.ustb.food.entity.TcmPerrecord;
import com.ustb.food.entity.TcmPerrecordId;
import com.ustb.food.entity.TcmYuanliao;
import com.ustb.food.entity.Users;
import com.ustb.food.service.TcmPerdietService;
import com.ustb.food.service.TcmPerrecordService;
import com.ustb.food.service.TcmYuanliaoService;
import com.ustb.food.service.UsersService;
import com.ustb.food.util.RecognizeModel;

/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年10月1日 下午4:56:45
 */
@Component("/front/TcmPerdietAction")
@Scope("prototype")
public class TcmPerdietAction extends ActionSupport implements SessionAware, RequestAware {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private TcmYuanliaoService tcmYuanliaoService;
	@Resource
	private TcmPerdietService tcmPerdietService;
	@Resource
	UsersService userService;
	@Resource
	TcmPerrecordService tcmPerrecordService;
	
	private Map<String, Object> session;
	private Map<String, Object> request;
	private String url;
	// 原料列表
	private String ylId;
	private String amount;
	private List<TcmYuanliao> yllist = new ArrayList<TcmYuanliao>();
	private List<String> idl = new ArrayList<String>();
	private List<String> al = new ArrayList<String>();
	private String date;
	private List<TcmPerdiet> tcmPerdietList = new ArrayList<TcmPerdiet>();
	private Users user;
	private String name;
	private Date insertDate;
	private int count;
	private int pc;
	private int pn;
	private int s;
	private double p;
	private String vname;
	// 曲线对比
	private String startDate;
	private String endDate;
	private String targetDate;
	private Map<Date, List<TcmPerdiet>> tcmPerdietMap;
	private Map<Date, TcmPerdiet> averageDayTcmPerdietMap; // 存放每天的平均饮食
	private TcmPerdiet averageTcmPerdiet; // 存放时间段的平均饮食或指定日期的平均饮食
	
	private double aucHypertension; // 存放该菜谱高血压 auc 值
	private double aucStomach; // 存放该菜谱养肠胃 auc 值
	private double aucDiabetes; // 存放该菜谱糖尿病 auc 值
	
	public String add() {
		yllist = tcmYuanliaoService.getList();
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		// 获取当前日期已经添加的饮食
		Users user = (Users) session.get("user");
		tcmPerdietList = tcmPerdietService.findbytime(user.getUserId(), date);
		return SUCCESS;
	}
	
	public String addSubmit() {
		fenjie();
		save();
		url = "TcmPerdiet/add";
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
		
		for (int i = 0; i < idl.size(); ++i) {
			// 根据 id 查询具体的中医原材料
			TcmYuanliao yuanliao = tcmYuanliaoService.get(Integer.parseInt(idl.get(i)));
			double temp = yuanliao.getWeight() * Double.parseDouble(al.get(i));
			fei += temp * yuanliao.getFei();
			xin += temp * yuanliao.getXin();
			piwei += temp * yuanliao.getPiwei();
			gandan += temp * yuanliao.getGandan();
			shen += temp * yuanliao.getShen();
			sanjiao += temp * yuanliao.getSanjiao();
			qi += temp * yuanliao.getQi();
			xue += temp * yuanliao.getXue();
			yin += temp * yuanliao.getYin();
			yang += temp * yuanliao.getYang();
			xing += temp * yuanliao.getXing();
			ku += temp * yuanliao.getKu();
			gan += temp * yuanliao.getGan();
			suan += temp * yuanliao.getSuan();
			xian += temp * yuanliao.getXian();
			dan += temp * yuanliao.getDan();
			sheng += temp * yuanliao.getSheng();
			jiang += temp * yuanliao.getJiang();
			chen += temp * yuanliao.getChen();
			fu += temp * yuanliao.getFu();
			hezhong += temp * yuanliao.getHezhong();
		}
		// 创建中医菜谱
		Users users = userService.get(user.getUserId());
		TcmPerdiet tcmPerdiet = new TcmPerdiet(name, fei, xin, piwei, gandan, shen, sanjiao, qi, xue, yin, yang, xing,
				ku, gan, suan, xian, dan, sheng, jiang, chen, fu, hezhong, insertDate, users, new ArrayList<TcmPerrecord>());
		tcmPerdietService.save(tcmPerdiet);
		// 存储外键关系
		List<TcmPerrecordId> tpdl = new ArrayList<TcmPerrecordId>();
		for (int i = 0; i < idl.size(); i++) {
			TcmPerrecordId pd = new TcmPerrecordId(tcmPerdiet.getTpId(), Integer.parseInt(idl.get(i)), users.getUserId());
			tpdl.add(pd);
		}
		for (int i = 0; i < idl.size(); i++) {
			TcmPerrecord tcmPerrecord = new TcmPerrecord(tpdl.get(i), tcmPerdiet, users,
					tcmYuanliaoService.get(Integer.parseInt(idl.get(i))), Integer.parseInt(al.get(i)), insertDate);
			tcmPerrecordService.save(tcmPerrecord);
		}
	}
	
	public String list() {
		// count表示一共有几条记录，pc表示总共有几页，pn表示的是当前是第几页
		count = tcmPerdietService.countAll();
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
		tcmPerdietList = tcmPerdietService.getList(pn, pageSize);
		return SUCCESS;
	}
	
	public String search() {
		if (null != vname) {
			tcmPerdietList = tcmPerdietService.getList("name", vname);
		}
		return "tcmPerdietSearch";
	}
	
	public String compare() {
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if(null != startDate && null != endDate) {
			Users user = (Users) session.get("user");
			// 找到<日期，饮食list>
			tcmPerdietMap = tcmPerdietService.findbytime(user.getUserId(), startDate, endDate);
			// 计算<日期，该日期平均值>
			averageDayTcmPerdietMap = getAverageTcmPerdietMap(tcmPerdietMap);
		}
		return "tcmPerdietCompare";
	}
	
	private Map<Date, TcmPerdiet> getAverageTcmPerdietMap(Map<Date, List<TcmPerdiet>> tempTcmPerdietMap) {
		Map<Date, TcmPerdiet> tempAverageDayTcmPerdietMap = new HashMap<Date, TcmPerdiet>();
		for(Map.Entry<Date, List<TcmPerdiet>> entry: tempTcmPerdietMap.entrySet()) {
			List<TcmPerdiet> tempTcmPerdiets = entry.getValue();
			TcmPerdiet averageTcmPerdiet = getAverageTcmPerdiet(tempTcmPerdiets);
			tempAverageDayTcmPerdietMap.put(entry.getKey(), averageTcmPerdiet);
		}
		return tempAverageDayTcmPerdietMap;
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
	
	public String bias() {
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		// 查询时间段的饮食偏向
		if(null != startDate && null != endDate) {
			Users user = (Users) session.get("user");
			// 找到<日期，饮食list>
			tcmPerdietMap = tcmPerdietService.findbytime(user.getUserId(), startDate, endDate);
			if(null == tcmPerdietMap || tcmPerdietMap.size() == 0) {
				System.out.println(startDate + "-" + endDate + "下没有相关饮食");
				return SUCCESS;
			}
			// 计算日期平均值
			List<TcmPerdiet> tempTcmPerdietList = new ArrayList<TcmPerdiet>();
			for(Map.Entry<Date, List<TcmPerdiet>> entry: tcmPerdietMap.entrySet()) {
				tempTcmPerdietList.addAll(entry.getValue());
			}
			averageTcmPerdiet = getAverageTcmPerdiet(tempTcmPerdietList);
			String alaisName = startDate + "-" + endDate;
			averageTcmPerdiet.setName(alaisName);
			String parameter = getTcmPerdietParameter(averageTcmPerdiet);
			// 得到 hypertension、stomach、diabetes预测的 auc 值
			aucHypertension = Double.parseDouble(RecognizeModel.recoTcmHypertension(parameter));
			System.out.println(alaisName + "的高血压概率：" + aucHypertension);
			aucStomach = Double.parseDouble(RecognizeModel.recoTcmStomach(parameter));
			System.out.println(alaisName + "的养肠胃病概率：" + aucStomach);
			aucDiabetes = Double.parseDouble(RecognizeModel.recoTcmDiabetes(parameter));
			System.out.println(alaisName + "的糖尿病概率：" + aucDiabetes);
		}
		// 查询指定日期的饮食偏向
		if(null != targetDate) {
			// 获取当前日期已经添加的饮食
			Users user = (Users) session.get("user");
			tcmPerdietList = tcmPerdietService.findbytime(user.getUserId(), targetDate);
			if(null == tcmPerdietList || tcmPerdietList.size() == 0) {
				System.out.println(targetDate + "下没有相关饮食");
				return SUCCESS;
			}
			averageTcmPerdiet = getAverageTcmPerdiet(tcmPerdietList);
			averageTcmPerdiet.setName(targetDate);
			String parameter = getTcmPerdietParameter(averageTcmPerdiet);
			// 得到 hypertension、stomach、diabetes预测的 auc 值
			aucHypertension = Double.parseDouble(RecognizeModel.recoTcmHypertension(parameter));
			System.out.println(targetDate + "的高血压概率：" + aucHypertension);
			aucStomach = Double.parseDouble(RecognizeModel.recoTcmStomach(parameter));
			System.out.println(targetDate + "的养肠胃病概率：" + aucStomach);
			aucDiabetes = Double.parseDouble(RecognizeModel.recoTcmDiabetes(parameter));
			System.out.println(targetDate + "的糖尿病概率：" + aucDiabetes);
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
	
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public List<TcmYuanliao> getYllist() {
		return yllist;
	}

	public void setYllist(List<TcmYuanliao> yllist) {
		this.yllist = yllist;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<TcmPerdiet> getTcmPerdietList() {
		return tcmPerdietList;
	}

	public void setTcmPerdietList(List<TcmPerdiet> tcmPerdietList) {
		this.tcmPerdietList = tcmPerdietList;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
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

	public double getP() {
		return p;
	}

	public void setP(double p) {
		this.p = p;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
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

	public Map<Date, List<TcmPerdiet>> getTcmPerdietMap() {
		return tcmPerdietMap;
	}

	public void setTcmPerdietMap(Map<Date, List<TcmPerdiet>> tcmPerdietMap) {
		this.tcmPerdietMap = tcmPerdietMap;
	}

	public Map<Date, TcmPerdiet> getAverageDayTcmPerdietMap() {
		return averageDayTcmPerdietMap;
	}

	public void setAverageDayTcmPerdietMap(Map<Date, TcmPerdiet> averageDayTcmPerdietMap) {
		this.averageDayTcmPerdietMap = averageDayTcmPerdietMap;
	}

	public TcmPerdiet getAverageTcmPerdiet() {
		return averageTcmPerdiet;
	}

	public void setAverageTcmPerdiet(TcmPerdiet averageTcmPerdiet) {
		this.averageTcmPerdiet = averageTcmPerdiet;
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
	
}
