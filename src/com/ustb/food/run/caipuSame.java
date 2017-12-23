package com.ustb.food.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Caipu;
import com.ustb.food.service.CaipuService;

/*
 * 用于处理爬下来高血压菜谱已经在库中的菜谱表中已经存在的情况
 * */
public class caipuSame {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CaipuService cps = (CaipuService) ac.getBean("caipuService");
		File filey = new File("D:\\caipu.xls");
		HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filey));
		HSSFSheet sheet = wookbook.getSheet("Sheet1");
		int rows = sheet.getPhysicalNumberOfRows();
		// 遍历行
		for (int i = 1; i < rows; i++) {
			// 读取左上端单元格
			HSSFRow row = sheet.getRow(i);
			HSSFCell cell = row.getCell(1);
			String cpName = cell.getStringCellValue();
			List<Caipu> list = cps.getList("viewName", cpName);
			if (list.size() > 0) {
				list.get(0).setSource("网:治疗糖尿病");
				cps.update(list.get(0));
				System.out.println(cpName + "替换成功");
			}
		}
	}
}
