package com.ustb.food.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.ustb.food.entity.Jiaquan;
import com.ustb.food.entity.Perdietcm;
import com.ustb.food.entity.Users;
import com.ustb.food.service.JiaquanService;
import com.ustb.food.service.PerdietcmService;
import com.ustb.food.service.UsersService;



@Component("/front/PerdietcmAction")
@Scope("prototype")
public class PerdietcmAction extends ActionSupport implements SessionAware{

	@Autowired UsersService usersService;
	@Autowired JiaquanService jiaquanService;
	@Autowired PerdietcmService perdietcmService;
	private Map<String, Object> session;
	private String url;
	private String date;
	private List<Jiaquan> jiaquanList = new ArrayList<Jiaquan>();
	private String  ylId;
	private String amount;
	private Date insertDate;
	private Users user;
	private List<String> idl = new ArrayList<String>();
	private List<String> al = new ArrayList<String>();
	private String pcmName;
	private Map perdietcmMap;
	private Map sumMap = new HashMap();
	public String my(){
		date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		jiaquanList = jiaquanService.getList();
		return SUCCESS;
	}
	
	public String addSubmit(){
		fenjie();
		save();
		url="Perdietcm/my";
		return NONE;
	}
	
	public void fenjie(){
		String [] id = ylId.split("[,，]");
		for(int i=0;i<id.length;i++){
			idl.add(id[i]);
		}
		String [] a = amount.split("[,，]");
		for(int i=0;i<a.length;i++){
			al.add(a[i]);
		}
	}
	
	public void save(){
		 Double fei=0.0;
		 Double xin=0.0;
		 Double piwei=0.0;
		 Double gandan=0.0;
		 Double shen=0.0;
		 Double sanjiao=0.0;
		 Double qi=0.0;
		 Double xue=0.0;
		 Double yin=0.0;
		 Double yang=0.0;
		 Double xxin=0.0;
		 Double ku=0.0;
		 Double gan=0.0;
		 Double tian=0.0;
		 Double xian=0.0;
		 Double dan=0.0;
		 Double sheng=0.0;
		 Double jiang=0.0;
		 Double chenlian=0.0;
		 Double fusan=0.0;
		 Double hezhong=0.0;
		 Double buyang=0.0;
		 Double buyin=0.0;
		 Double xiehuo=0.0;
		 Double sanhan=0.0;	
		 Double sum = 0.0;
		 Double asum = 0.0;
		 Double a = 0.0;
//		 肺
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getFei()*(Double.parseDouble(al.get(i)));
			 asum+=Double.parseDouble(al.get(i));
		 }
		 a=sum/asum;
		 DecimalFormat df = new DecimalFormat("#.00");
		 fei=Double.valueOf(df.format(a));
//		 心
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getXin()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 xin=Double.valueOf(df.format(a));
//		 脾胃
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getPiwei()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 piwei=Double.valueOf(df.format(a));
//		 肝胆
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getGandan()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 gandan=Double.valueOf(df.format(a));
//		 肾
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getShen()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 shen=Double.valueOf(df.format(a));
//		三焦
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getSanjiao()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 sanjiao=Double.valueOf(df.format(a));
//		 气
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getQi()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 qi=Double.valueOf(df.format(a));
//		 血
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getXue()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 xue=Double.valueOf(df.format(a));
//		 阴
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getYin()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 yin=Double.valueOf(df.format(a));
//		 阳
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getYang()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 yang=Double.valueOf(df.format(a));
//		 辛
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getXxin()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 xxin=Double.valueOf(df.format(a));
//		 苦
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getKu()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 ku=Double.valueOf(df.format(a));
//		 甘
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getGan()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 gan=Double.valueOf(df.format(a));
//		 甜
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getTian()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 tian=Double.valueOf(df.format(a));
//		 咸
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getXian()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 xian=Double.valueOf(df.format(a));
//		 淡
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getDan()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 dan=Double.valueOf(df.format(a));
//		 升
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getSheng()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 sheng=Double.valueOf(df.format(a));
//		 降
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getJiang()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 jiang=Double.valueOf(df.format(a));
//		 沉敛
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getChenlian()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 chenlian=Double.valueOf(df.format(a));
//		 浮散
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getFusan()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 fusan=Double.valueOf(df.format(a));
//		 和中
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getHezhong()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 hezhong=Double.valueOf(df.format(a));
//		 补阳
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getBuyang()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 buyang=Double.valueOf(df.format(a));
//		 补阴
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getBuyin()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 buyin=Double.valueOf(df.format(a));
//		 泻火
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getXiehuo()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 xiehuo=Double.valueOf(df.format(a));
//		 散寒
		 sum = 0.0;
		 for(int i = 0 ;i<idl.size();i++){
			 sum+=(jiaquanService.get((int)Double.parseDouble(idl.get(i)))).getSanhan()*(Double.parseDouble(al.get(i)));
		 }
		 a=sum/asum;
		 sanhan=Double.valueOf(df.format(a));
		 
		 Users users = usersService.get(user.getUserId());
		 Perdietcm perdietcm = new Perdietcm(users,pcmName,fei,xin,piwei,gandan,shen,sanjiao,qi,xue,yin,yang,xxin,ku,gan,tian,
				 xian,dan,sheng,jiang,chenlian,fusan,hezhong,buyang,buyin,xiehuo,sanhan,insertDate);
		 perdietcmService.save(perdietcm);
		 
		
	}
	
	public String display(){
		 Double qi = 0.0 ;
		 Double xue =  0.0;
		 Double yin =  0.0;
		 Double yang =  0.0;
		 Double tian = 0.0;

		Users usero = (Users) session.get("user");
		perdietcmMap=perdietcmService.findbytime(usero.getUserId());
		Iterator it = perdietcmMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry) it.next();
			List<Perdietcm> perdietcms = (List<Perdietcm>) entry.getValue();
			Date key =(Date) entry.getKey();
			 qi = 0.0 ;
			  xue =  0.0;
			  yin =  0.0;
			  yang =  0.0;
			  tian = 0.0;
			for(int i = 0;i<perdietcms.size();i++){
				 qi +=perdietcms.get(i).getQi();
				 xue += perdietcms.get(i).getXue();
		         yin += perdietcms.get(i).getYin();
		         yang +=  perdietcms.get(i).getYang();		        
		         tian +=  perdietcms.get(i).getTian();
			}
			Perdietcm sumperdietcm=new Perdietcm();
			sumperdietcm.setQi(qi);
			sumperdietcm.setXue(xue);
			sumperdietcm.setYin(yin);
			sumperdietcm.setYang(yang);
			sumperdietcm.setTian(tian);
			sumMap.put(key, sumperdietcm);
			
		}
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
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
	public List<Jiaquan> getJiaquanList() {
		return jiaquanList;
	}
	public void setJiaquanList(List<Jiaquan> jiaquanList) {
		this.jiaquanList = jiaquanList;
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

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getPcmName() {
		return pcmName;
	}

	public void setPcmName(String pcmName) {
		this.pcmName = pcmName;
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

	public Map getPerdietcmMap() {
		return perdietcmMap;
	}

	public void setPerdietcmMap(Map perdietcmMap) {
		this.perdietcmMap = perdietcmMap;
	}

	public Map getSumMap() {
		return sumMap;
	}

	public void setSumMap(Map sumMap) {
		this.sumMap = sumMap;
	}			
	
	
}
