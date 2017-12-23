package com.ustb.food.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Perdiet;
import com.ustb.food.entity.Perrecord;
import com.ustb.food.entity.Users;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.PerdietService;
import com.ustb.food.service.PerrecordService;
import com.ustb.food.service.UsersService;
import com.ustb.food.service.YuanliaoService;
import com.ustb.food.tool.Apriori;
import com.ustb.food.tool.ruletoExcel;

@Component("/front/PerrecordAction")
@Scope("prototype")
public class PerrecordAction extends ActionSupport implements SessionAware{

	@Autowired
	YuanliaoService yuanliaoService;
	@Autowired
	PerrecordService perrecordService;
	@Autowired
	UsersService userService;
	@Autowired
	PerdietService perdietService;
	@Autowired
	@Qualifier("ruletoExcel")
	ruletoExcel rule;
	private Map<String, Object> session;
	private String date;
	private String sign = null;
	private List<Yuanliao> yllist = new ArrayList<Yuanliao>();
	private String nameList;
	private List<String> nameLike = new ArrayList<String>();
	private String ylId;
	private List<String> idl = new ArrayList<String>();
	//private String sum;
	private List<String> al = new ArrayList<String>();
	//private Map<Integer, String> perdietMap = new HashMap<Integer, String>();
	private List<Perdiet> perdiets = new ArrayList<Perdiet>();
	private Users user;
	private Date insertDate;
	private String amount;
	private Yuanliao yuanliao;
	private String url;
	private String vname;
	private String output;
	
	public String my() {
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		if (sign == null) {
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
	
	public void fenjie() {
		String[] id = ylId.split("[,，]");
		for (int i = 0; i < id.length; i++) {
			idl.add(id[i]);
		}
		String[] a = amount.split("[,，]");
		for (int i = 0; i < a.length; i++) {
			
			al.add(a[i]);
		}
	}
	
	public void save() {
		Users users = userService.get(user.getUserId());
		for(int i = 0;i<al.size();i++){
			Perrecord perrecord = new Perrecord(users, yuanliaoService.get(Integer.valueOf(idl.get(i))), Integer.valueOf(al.get(i)), insertDate);
			perrecordService.save(perrecord);
		}		
	}
	
	public String addSubmit() {
		fenjie();
		save();
		perdietSave();
		url = "Perdiet/my";
		return NONE;
	}
	
	public String rule(){
		outPutFile();
		new Apriori().run();
		StringBuffer str=Apriori.strbuf;
		output=str.toString();
		Users usero = (Users) session.get("user");
		rule.toExcel(output,usero.getUserId());
		return SUCCESS;
	}
	
	
	public ruletoExcel getRule() {
		return rule;
	}

	public void setRule(ruletoExcel rule) {
		this.rule = rule;
	}

	public void  outPutFile(){
		Users u = (Users) session.get("user");
		Map<Date, List<String>> map = new HashMap<Date, List<String>>();
		map = perrecordService.getAll(u.getUserId());
		File file = new File("D:\\Workspaces\\Food\\WebRoot\\data\\data.txt");
		try {
			FileOutputStream fop = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fop, "UTF-8"); 
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			int count = 0;
			for (List<String> list : map.values()) {
				for (int i = 0; i < list.size(); i++) {
					String fen = ",";
					String out = list.get(i) + fen;
					if (i == (list.size() - 1)) {
						out = list.get(i);
					}
					osw.write(out);
				}
				count++;
				if (map.size() != count) {
					osw.write("\r\n");
				}
			}
			osw.flush();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	//用List的实现方式
	public void perdietSave() {
		Users users = userService.get(user.getUserId());
		List<Perrecord> PerrecordList = new ArrayList<Perrecord>();
		PerrecordList = perrecordService.perrecordList(user.getUserId(), new SimpleDateFormat("yyyy-MM-dd").format(insertDate));
		Perdiet perdiet = new Perdiet();
		try {
			perdiet = perrecordService.calculate(PerrecordList);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		perdiet.setUsers(users);
		perdiet.setVname(vname);
		perdiet.setInsertdate(insertDate);
		perdiets = perdietService.retrieveAndDelete(user.getUserId(), (new SimpleDateFormat("yyyy-MM-dd")).format(insertDate));
		for (int i = 0; i < perdiets.size(); i++) {
			perdietService.delete(perdiets.get(i).getPid());
		}
		perdietService.save(perdiet);
	}
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public List<Yuanliao> getYllist() {
		return yllist;
	}

	public void setYllist(List<Yuanliao> yllist) {
		this.yllist = yllist;
	}

	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	public List<String> getNameLike() {
		return nameLike;
	}

	public void setNameLike(List<String> nameLike) {
		this.nameLike = nameLike;
	}

	public String getYlId() {
		return ylId;
	}

	public void setYlId(String ylId) {
		this.ylId = ylId;
	}

	public List<String> getIdl() {
		return idl;
	}

	public void setIdl(List<String> idl) {
		this.idl = idl;
	}

	/*public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}*/

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

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Yuanliao getYuanliao() {
		return yuanliao;
	}

	public void setYuanliao(Yuanliao yuanliao) {
		this.yuanliao = yuanliao;
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

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	

}
