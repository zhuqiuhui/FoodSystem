package com.ustb.food.run;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Caipu;
import com.ustb.food.entity.Guanxi;
import com.ustb.food.entity.GuanxiId;
import com.ustb.food.entity.Material;
import com.ustb.food.entity.Views;
import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.CaipuService;
import com.ustb.food.service.GuanxiService;
import com.ustb.food.service.ViewService;
import com.ustb.food.service.YuanliaoService;

public class ViewGetTest {
	// @Autowired FoodService foodService;
	public static void main(String[] args) throws Exception {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		ViewService viewService = (ViewService) ac.getBean("viewService");
		YuanliaoService yuanliaoService = (YuanliaoService) ac
				.getBean("yuanliaoService");
		CaipuService caipuService = (CaipuService) ac
				.getBean("caipuService");
		GuanxiService guanxiService = (GuanxiService) ac
				.getBean("guanxiService");
		Views view = new Views();
		List<Views> views = new ArrayList<Views>();
		views = viewService.getList();
		Yuanliao yl = new Yuanliao();
		int count = 0;
	    String viewName ;

		    List <String> idl = new ArrayList<String>();
		    List<String> al = new ArrayList<String>();
			view = views.get(462);
			viewName = view.getViewName();
			List<Material> materials = new ArrayList<Material>();
			materials = view.getMaterials();
			String name1="";
			if(!materials.isEmpty()){
//				for (Material m : materials) {
//					System.out.println("原料" + m.getmName() + m.getAmount() + "克");
//					String name =m.getmName();
//					if ((!(yuanliaoService.findSame(m.getmName())).isEmpty())) {
//						// 找到菜谱中的原料
//						yl = yuanliaoService.findSame(m.getmName()).get(0);
//						String id =Integer.toString(yl.getMaId());	
//						if(!name1.equals(name)){
//							idl.add(id);
//							String amount = Double.toString(m.getAmount());
//							al.add(amount);
//						}
//						 name1 = yl.getmName();
//						System.out.println("对应原料Id为" + id);
//					} else if (m.getmName().equals("鸡蛋")) {
//						
//						String id =Integer.toString(21591);
//						idl.add(id);
//						String amount = Double.toString(m.getAmount());
//						al.add(amount);
//					} 
//					else {
//						System.out.println("原料" + m.getmName() + m.getAmount()
//								+ "克");
//						System.out
//								.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!没找到");
//						break;
//					}
//				}
				save(idl,al,viewName, yuanliaoService,caipuService, guanxiService);
			}
			else{
					System.out.println("无效菜谱");
				}
			

//		}
	}
	
	public  static void save(List<String> idl ,List<String> al,String viewName,YuanliaoService yuanliaoService,
			CaipuService caipuService,GuanxiService guanxiService){
		 System.out.println("");
		 System.out.print(viewName);
		List<Guanxi> gxlist = new ArrayList<Guanxi>();
		Set<Guanxi> gxl = new HashSet<Guanxi>();
		List<GuanxiId> gxidl = new ArrayList<GuanxiId>();
		Caipu cp = new Caipu(viewName,0.0,  0.0,
				0.0,  0.0,  0.0,  0.0,  0.0,
				0.0,  0.0,  0.0,  0.0,
				0.0,  0.0,  0.0,  0.0,  0.0,
				0.0,  0.0,  0.0,  0.0,  0.0,  0.0,
				0.0,  "薄荷网",  gxlist);
		//保存数据
		caipuService.save(cp);
//		for(int i = 0;i<idl.size();i++){
//			GuanxiId gxi = new GuanxiId(cp.getViewId(),(int)Double.parseDouble(idl.get(i)));
//			gxidl.add(gxi);
//		}
//		for(int i = 0;i<idl.size();i++){
//			Guanxi g = new Guanxi(gxidl.get(i),cp,yuanliaoService.get((int) Double.parseDouble(idl.get(i))),(int)Double.parseDouble(al.get(i)));			
//			guanxiService.save(g);
//		}
		Yuanliao yl = yuanliaoService.get(1);
		GuanxiId gxi = new GuanxiId(12,1);
		Caipu caipu =caipuService.get(12);
		Guanxi g = new Guanxi(gxi,caipu,yl,20);
		guanxiService.save(g);
		
		System.out.println("保存成功");
	}
}
