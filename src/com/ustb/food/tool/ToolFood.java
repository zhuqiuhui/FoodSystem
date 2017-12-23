package com.ustb.food.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolFood {

	// public static final String START = " ";
	// public static final String END = "";

	public static String getFoodGroup(final String content) {
		int begin = content
				.indexOf("<span class=\"float-left\" style=\"width:360px;\">");
		int end = content.indexOf("<span class=\"float-right\">");
		String content1 = content.substring(begin);
		int begin1 = content1.indexOf("360px;\">");
		content1 = content1.substring(begin1 + 7);
		return content1;
	}

	public static String getFoodUrl(final String content) {
		int begin = content.indexOf("<a href=\"");
		int end = content.indexOf("\" title");
		String Url = "http://www.boohee.com"
		+ content.substring(begin + 9, end);
		return Url;

	}

	public static String getFoodName(final String content) {
		int begin = content.indexOf("薄荷食物库</a> »");//随着食物种类不同变化
		String foodContent = content.substring(begin+16);
		int beginl = foodContent.indexOf("»");
		int end = foodContent.indexOf("</div>");
		foodContent = foodContent.substring(beginl + 1, end);
		return foodContent;

	}
	
	public static String getMaterialName(final String content) {
		int begin = content.indexOf("薄荷食物库</a> »");//随着食物种类不同变化
		String foodContent = content.substring(begin+16);
		int beginl = foodContent.indexOf("»");
		int end = foodContent.indexOf("</div>");
		foodContent = foodContent.substring(beginl + 1, end);
		return foodContent;

	}
	//得到材料的网址
	public static List<String> getViewMaterialUrl(final String content){
		 List<String> urls = new ArrayList<String>();
		int begin = content.indexOf("<div class=\"part divide10\">");
		String viewMaterial = content.substring(begin);
		 String url=null;
		while((begin = viewMaterial.indexOf("<p><a href=\""))>0){
			int end = viewMaterial.indexOf("\" target");
			if(begin>end){
				break;
			}
			
				url = "http://www.boohee.com"
						+ viewMaterial.substring(begin + 12, end);
				 urls.add(url);
				 int endp = viewMaterial.indexOf("</p>");
				 viewMaterial=viewMaterial.substring(endp+4);
				 int yan = viewMaterial.indexOf("<p>盐");
				 int c = viewMaterial.indexOf("<p><a href=\"");
				 if(yan>0&&yan<c){
						url="http://www.boohee.com/shiwu/jingyan";
						urls.add(url);
						int yans = viewMaterial.indexOf("</p>");
						viewMaterial=viewMaterial.substring(yans+4);
				
			}
				 
			 
		}
		return urls;
	}
	
	//得到材料的用量
	public static Map getViewMaterialAmount(final String content){
		int begin = content.indexOf("<div class=\"part divide10\">");
		String viewMaterial = content.substring(begin);
		 String url=null;
		 HashMap map = new HashMap();
		 
		while((begin = viewMaterial.indexOf("<p><a href=\""))>0){
			int end = viewMaterial.indexOf("\" target");
			if(begin>end){
				break;
			}
			 url = "http://www.boohee.com"
					+ viewMaterial.substring(begin + 12, end);
			 int endp = viewMaterial.indexOf("</a>");
			 int ends = viewMaterial.indexOf("</p>");
			 if(endp>0&&ends>0&&endp>ends){
				 break;}
			 String samount = viewMaterial.substring(endp+5,ends-1);
			 Double amount = Double.parseDouble(samount);
			 map.put(url, amount);
			 viewMaterial=viewMaterial.substring(ends+4);
			int yan = viewMaterial.indexOf("<p>盐");//yan=58
			int c = viewMaterial.indexOf("<p><a href=\"");
			if(yan>0&&yan<c){
				url="http://www.boohee.com/shiwu/jingyan";
				amount=2.0;
				map.put(url, amount);
				int yans = viewMaterial.indexOf("</p>");
				viewMaterial=viewMaterial.substring(yans+4);
			}
			
		}
		return map;
	}
	
 
	
	public static String getFoodCalories(final String content) {
		int begin = content.indexOf("营养信息");
		String foodContent = content.substring(begin);
		int beginl = foodContent.indexOf("<span class=\"stress red1\">");
		int end = foodContent.indexOf("</span></span></dd>");
		foodContent = foodContent.substring(beginl + 26, end);
		return foodContent;

	}
	
	public static String getFoodOthers(final String content,int j) {
		int begin = content.indexOf("营养信息");
		String foodContent = content.substring(begin);
		for(int i = 0 ; i<2+j;i++){
			int beginl = foodContent.indexOf("<span class=\"dd\">");
			foodContent = foodContent.substring(beginl+17);

		}
		int end = foodContent.indexOf("</span></dd>");
		if (end<0){
			foodContent = Double.toString(0.00);
		}else{
			foodContent = foodContent.substring(0,end);
		}
		String s = "一";
		if(foodContent.equals(s))
		{
			foodContent=Double.toString(0.00);
		}
		return foodContent;

	}
}
