package com.ustb.food.action.json;

import java.util.HashMap;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("/json/jsonTestAction")
@Scope("prototype")
// 在这不可以使用注解的主要方式是由于没有实际存在的Bean
public class jsonTestAction extends ActionSupport {

	String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	HashMap map = new HashMap();

	public String jsonConvert() {
		// result = "{'position':'1','age':11,'name':'fc'}";
		map.put("name", "fccc");
		map.put("age", 11);
		map.put("position", "1");
		System.out.println(map);
		try {
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();
			System.out.println("result" + result);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return "json";
	}

	public HashMap getMap() {
		return map;
	}

	public void setMap(HashMap map) {
		this.map = map;
	}
}
