package com.ustb.food.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;

public class name_del_kuohao {
	public void del_kuohao() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		YuanliaoService yls = (YuanliaoService) ac.getBean("yuanliaoService");
		List<Yuanliao> yll = new ArrayList<Yuanliao>();
		File filey = new File("D:\\xin.xls");
		HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filey));
		HSSFSheet sheet = wookbook.getSheet("Sheet1");
		int rows = sheet.getPhysicalNumberOfRows();
		// 遍历行
		for (int i = 1; i < rows; i++) {
			// 读取左上端单元格
			HSSFRow row = sheet.getRow(i);
			HSSFCell cell = row.getCell(1);
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				break;
			case Cell.CELL_TYPE_STRING:
				String name = cell.getStringCellValue();
				if (name.contains("(")) {
					String a[] = name.split("\\(");
					System.out.println("1新数据:" + a[0]);
					yll = yls.findSame(a[0]);
					if (!yll.isEmpty()) {
						Yuanliao yuanliao = yll.get(0);
						System.out.println(yuanliao.getMaId());
						cell.setCellValue(yuanliao.getMaId());
					}
				}
				if (name.contains("（")) {
					String b[] = name.split("\\（");
					System.out.println("2新数据:" + b[0]);
					yll = yls.findSame(b[0]);
					if (!yll.isEmpty()) {
						Yuanliao yuanliao = yll.get(0);
						System.out.println(yuanliao.getMaId());
						cell.setCellValue(yuanliao.getMaId());
					}
				}
				FileOutputStream out = null;
				out = new FileOutputStream("D:\\xin.xls");
				wookbook.write(out);
				out.close();
			}
		}
	}
}
