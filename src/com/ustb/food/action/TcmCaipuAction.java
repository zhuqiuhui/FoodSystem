package com.ustb.food.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Caipu;
import com.ustb.food.entity.TcmCaipu;
import com.ustb.food.entity.TcmGuanxi;
import com.ustb.food.entity.TcmGuanxiId;
import com.ustb.food.entity.TcmYuanliao;
import com.ustb.food.service.TcmCaipuService;
import com.ustb.food.service.TcmGuanxiService;
import com.ustb.food.service.TcmYuanliaoService;
import com.ustb.food.util.RecognizeModel;
/**
 * 
 * @author: zhuqiuhui
 * @date: 2017年9月30日 下午12:41:08
 */
@Component("/front/TcmCaipuAction")
@Scope("prototype")
public class TcmCaipuAction extends ActionSupport implements SessionAware, RequestAware {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private TcmYuanliaoService tcmYuanliaoService;
	@Resource 
	private TcmCaipuService tcmCaipuService;
	@Resource
	private TcmGuanxiService tcmGuanxiService;
	
	private Map<String, Object> session;
	private Map<String, Object> request;
	// 原料列表
	private List<TcmYuanliao> yllist = new ArrayList<TcmYuanliao>();
	private String ylId;
	private String amount;
	private List<String> idl = new ArrayList<String>();
	private List<String> al = new ArrayList<String>();
	private String name; // 名字
	private String url;
	private String source;
	// 分页相关
	private int pc;
	private int pn;
	private int s;
	private int p;
	private int count;
	private List<TcmCaipu> tcmCaipuList = new ArrayList<TcmCaipu>();
	private String cpName;
	// 菜谱对比相关
	private List<TcmCaipu> tcmCaipuList1 = new ArrayList<TcmCaipu>();
	private List<TcmCaipu> tcmCaipuList2 = new ArrayList<TcmCaipu>();
	private String cpName1;
	private String cpName2;
	
	private double aucHypertension; // 存放该菜谱高血压 auc 值
	private double aucStomach; // 存放该菜谱养肠胃 auc 值
	private double aucDiabetes; // 存放该菜谱糖尿病 auc 值
	
	public String add() {
		yllist = tcmYuanliaoService.getList();
		return SUCCESS;
	}
	
	public String addSubmit() {
		fenjie();
		save();
		url = "TcmCaipu/add";
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
		List<TcmGuanxiId> gxidl = new ArrayList<TcmGuanxiId>();
		List<TcmGuanxi> tempp = new ArrayList<TcmGuanxi>();
		TcmCaipu tcmCaipu = new TcmCaipu(name, fei, xin, piwei, gandan, shen, sanjiao, qi, xue, yin, yang, xing,
				ku, gan, suan, xian, dan, sheng, jiang, chen, fu, hezhong, source, tempp);
		tcmCaipuService.save(tcmCaipu);
		// 构造关系 ID 类
		for (int i = 0; i < idl.size(); i++) {
			TcmGuanxiId gxi = new TcmGuanxiId(tcmCaipu.getTcmId(), Integer.parseInt(idl.get(i)));
			gxidl.add(gxi);
		}
		// 构造关系类并保存
		for (int i = 0; i < idl.size(); i++) {
			TcmGuanxi g = new TcmGuanxi(gxidl.get(i), tcmCaipu, tcmYuanliaoService.get(Integer.parseInt(idl.get(i))),
					Integer.parseInt(al.get(i)));
			tcmGuanxiService.save(g);
		}
	}
	
	public String list() {
		// count表示一共有几条记录，pc表示总共有几页，pn表示的是当前是第几页
		count = tcmCaipuService.countAll();
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
		tcmCaipuList = tcmCaipuService.getList(pn, pageSize);
		return SUCCESS;
	}
	
	public String search() {
		if (cpName != null) {
			// 查询提交
			tcmCaipuList = tcmCaipuService.getList("name", cpName);
		}
		return "tcmCaipuSearch";
	}
	
	public String compare() {
		if (null != cpName1 && null != cpName2) {
			tcmCaipuList1 = tcmCaipuService.getList("name", cpName1);
			tcmCaipuList2 = tcmCaipuService.getList("name", cpName2);
		}
		return "tcmCaipuCompare";
	}
	
	public String tcmRecognize() {
		return SUCCESS;
	}

	public String tcmRecognizeSubmit() {
		// Step 1：得到要识别的菜谱详细信息
		tcmCaipuList = tcmCaipuService.getList("name", cpName);
		if(null == tcmCaipuList || tcmCaipuList.size() == 0)
			return "tcmCaipuRecognize";
		// Step 2：把菜谱信息内的元素拼接成字符串作为识别模型的入参
		String parameter = "";
		TcmCaipu tcmCaipu = tcmCaipuList.get(0);
		parameter = tcmCaipu.getFei() + "," + tcmCaipu.getXin() + "," + tcmCaipu.getPiwei() + ","
				+ tcmCaipu.getGandan() + "," + tcmCaipu.getShen() + "," + tcmCaipu.getSanjiao() + "," + tcmCaipu.getQi() + ","
				+ tcmCaipu.getXue() + "," + tcmCaipu.getYin() + "," + tcmCaipu.getYang() + "," + tcmCaipu.getXing()
				+ "," + tcmCaipu.getKu() + "," + tcmCaipu.getGan() + "," + tcmCaipu.getSuan() + "," + tcmCaipu.getXian()
				+ "," + tcmCaipu.getDan() + "," + tcmCaipu.getSheng() + "," + tcmCaipu.getJiang() + "," + tcmCaipu.getChen() + ","
				+ tcmCaipu.getFu() + "," + tcmCaipu.getHezhong();
		// Step 3：得到 hypertension、stomach、diabetes预测的 auc 值
		aucHypertension = Double.parseDouble(RecognizeModel.recoTcmHypertension(parameter));
		System.out.println(tcmCaipu.getName() + "的高血压概率：" + aucHypertension);
		aucStomach = Double.parseDouble(RecognizeModel.recoTcmStomach(parameter));
		System.out.println(tcmCaipu.getName() + "的养肠胃病概率：" + aucStomach);
		aucDiabetes = Double.parseDouble(RecognizeModel.recoTcmDiabetes(parameter));
		System.out.println(tcmCaipu.getName() + "的糖尿病概率：" + aucDiabetes);
		return "tcmCaipuRecognize";
	}
	
	public List<TcmYuanliao> getYllist() {
		return yllist;
	}

	public void setYllist(List<TcmYuanliao> yllist) {
		this.yllist = yllist;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<TcmCaipu> getTcmCaipuList() {
		return tcmCaipuList;
	}

	public void setTcmCaipuList(List<TcmCaipu> tcmCaipuList) {
		this.tcmCaipuList = tcmCaipuList;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public List<TcmCaipu> getTcmCaipuList1() {
		return tcmCaipuList1;
	}

	public void setTcmCaipuList1(List<TcmCaipu> tcmCaipuList1) {
		this.tcmCaipuList1 = tcmCaipuList1;
	}

	public List<TcmCaipu> getTcmCaipuList2() {
		return tcmCaipuList2;
	}

	public void setTcmCaipuList2(List<TcmCaipu> tcmCaipuList2) {
		this.tcmCaipuList2 = tcmCaipuList2;
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
