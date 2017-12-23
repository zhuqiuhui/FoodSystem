package com.ustb.food.tool;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Caipu;
import com.ustb.food.service.CaipuService;

public class Get_bad_tnb_caipu {
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CaipuService bean = (CaipuService) ac.getBean("caipuService");
		File filey = new File("D:\\bad.xls");
		HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filey));
		HSSFSheet sheet = wookbook.getSheet("Sheet1");
		int rows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rows; i++) {
			HSSFRow row = sheet.getRow(i);
			if (row != null) {
				HSSFCell hc = row.getCell(0);
				int value = (int) hc.getNumericCellValue();
				Caipu caipu = bean.get(value);
				System.out.println(caipu);
			}
		}
	}
}
