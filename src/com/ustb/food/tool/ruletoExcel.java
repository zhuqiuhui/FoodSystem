package com.ustb.food.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ustb.food.entity.Perdiet;

/*
 * 将转换好的菜谱（set）集合写入excel中(以名字加上营养成分的总和),以便神经网络进行判定是否为养肠胃
 * */
@Component("ruletoExcel")
public class ruletoExcel {
	private InputStream excelFile;
	@Autowired
	countYingyang countYingyang;
	@Autowired
	ConnectShengJing connectShengJing;

	public void ExcelExport(Set set, int userId) {
		try {
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet("caipu");
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue("viewId");
			row.createCell(1).setCellValue("viewName");
			row.createCell(2).setCellValue("calories");
			row.createCell(3).setCellValue("carbohydrate");
			row.createCell(4).setCellValue("fat");
			row.createCell(5).setCellValue("protein");
			row.createCell(6).setCellValue("vitamine");
			row.createCell(7).setCellValue("vta");
			row.createCell(8).setCellValue("vtc");
			row.createCell(9).setCellValue("vte");
			row.createCell(10).setCellValue("carotene");
			row.createCell(11).setCellValue("thiamine");
			row.createCell(12).setCellValue("riboflavin");
			row.createCell(13).setCellValue("yansuan");
			row.createCell(14).setCellValue("cholesterol");
			row.createCell(15).setCellValue("mg");
			row.createCell(16).setCellValue("ca");
			row.createCell(17).setCellValue("iron");
			row.createCell(18).setCellValue("zinc");
			row.createCell(19).setCellValue("copper");
			row.createCell(20).setCellValue("mn");
			row.createCell(21).setCellValue("k");
			row.createCell(22).setCellValue("p");
			row.createCell(23).setCellValue("na");
			row.createCell(24).setCellValue("se");
			Iterator it = set.iterator();
			for (int i = 1; i < set.size() + 1; i++) {
				String o = "";
				row = sheet.createRow(i);
				row.createCell(0).setCellValue(i);
				// 主要目的是把嵌套的set里面的值改为String类型。放到excel中。
				Set next = (Set) it.next();
				Iterator it1 = next.iterator();
				while (it1.hasNext()) {
					o = o + (String) it1.next();
				}
				row.createCell(1).setCellValue(o);
				Perdiet sumy = countYingyang.sumy(next, userId);
				System.out.println("prediet:" + sumy);
				row.createCell(2).setCellValue(sumy.getCalories());
				row.createCell(3).setCellValue(sumy.getCarbohydrate());
				row.createCell(4).setCellValue(sumy.getFat());
				row.createCell(5).setCellValue(sumy.getProtein());
				row.createCell(6).setCellValue(sumy.getVitamine());
				row.createCell(7).setCellValue(sumy.getVta());
				row.createCell(8).setCellValue(sumy.getVtc());
				row.createCell(9).setCellValue(sumy.getVte());
				row.createCell(10).setCellValue(sumy.getCarotene());
				row.createCell(11).setCellValue(sumy.getThiamine());
				row.createCell(12).setCellValue(sumy.getRiboflavin());
				row.createCell(13).setCellValue(sumy.getYansuan());
				row.createCell(14).setCellValue(sumy.getCholesterol());
				row.createCell(15).setCellValue(sumy.getMg());
				row.createCell(16).setCellValue(sumy.getCa());
				row.createCell(17).setCellValue(sumy.getIron());
				row.createCell(18).setCellValue(sumy.getZinc());
				row.createCell(19).setCellValue(sumy.getCopper());
				row.createCell(20).setCellValue(sumy.getMn());
				row.createCell(21).setCellValue(sumy.getK());
				row.createCell(22).setCellValue(sumy.getP());
				row.createCell(23).setCellValue(sumy.getNa());
				row.createCell(24).setCellValue(sumy.getSe());
			}
			// 将内容写入D:\\panding1.xls中。
			File file = new File("D:\\panding1.xlsx");
			FileOutputStream baos = new FileOutputStream(file);
			workbook.write(baos);
			baos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public countYingyang getCountYingyang() {
		return countYingyang;
	}

	public void setCountYingyang(countYingyang countYingyang) {
		this.countYingyang = countYingyang;
	}

	// 将频繁挖掘结果整理形式，转换为[大米, 红豆, 香米]形式
	public Set changeString(String all) {
		HashSet<Set> list2 = new HashSet<Set>();
		String[] part = all.split("[﻿*********]频繁模式挖掘结果[***********]");
		String rule = part[part.length - 1];
		String[] singleRule = rule.split("<br/>");
		for (int i = 1; i < singleRule.length; i++) {
			HashSet list = new HashSet();
			int index = singleRule[i].indexOf("相对支持度");
			String newRule = singleRule[i].substring(0, index);
			String[] yuan = newRule.split("-->");
			list.add(yuan[1]);
			String yuanliao[] = yuan[0].split(" ");
			for (int j = 0; j < yuanliao.length; j++) {
				list.add(yuanliao[j]);
			}
			list2.add(list);
		}
		Iterator it = list2.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			System.out.println(o);
		}
		return list2;

	}

	// 将挖掘结果读入到程序，是依据程序自己运行的时候将规则写入到了D:\\rule.txt，可以读入此规则
	public String read() {
		String encoding = "UTF-8"; // 字符编码(可解决中文乱码问题 )
		File file = new File("D:\\rule.txt");
		String all = "";
		try {
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader in = new BufferedReader(new FileReader(file));
				String line;
				while ((line = in.readLine()) != null) {
					all = all + line;
				}
				in.close();
			} else {
				System.out.println("找不到指定的文件！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return all;
	}

	public void toExcel(String output, int userId) {
		// TODO Auto-generated method stub
		Set set = changeString(output);
		ExcelExport(set, userId);
		connectShengJing.conn();
	}

	public static void main(String[] args) {
		// new ruletoExcel().changeString(output);
		ruletoExcel ruletoExcel = new ruletoExcel();
		String read = new ruletoExcel().read();
		Set set = new ruletoExcel().changeString(read);
	}
}
