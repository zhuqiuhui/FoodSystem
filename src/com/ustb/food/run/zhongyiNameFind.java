package com.ustb.food.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;

public class zhongyiNameFind {
	public static void main(String args[]) {
		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			YuanliaoService yls = (YuanliaoService) ac
					.getBean("yuanliaoService");
			List<Yuanliao> yll = new ArrayList<Yuanliao>();
			// 创建对Excel工作簿文件的引用，获取Excel表文件
			File filey = new File("D:\\zhongyi.xls");
			// 然后是创建一个Excel处理类的对象，将输入流中的信息读入到此对象
			HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filey));
			// 在Excel文档中，第一张工作表的缺省索引是0
			// 因为一个Excel中可以有多个表，所以获取当前Excel表的第一张表
			// HSSFSheet sheet= wookbook.getSheetAt(0);
			// 按照名称表明获取一张表
			HSSFSheet sheet = wookbook.getSheet("Sheet1");
			// 获取到Excel文件中的所有行数
			int rows = sheet.getPhysicalNumberOfRows();
			System.out.println("row的行" + rows);
			// 遍历行
			for (int i = 0; i < rows; i++) {
				// 读取左上端单元格
				HSSFRow row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					// 替换原料名称为ID
					HSSFCell ylc = row.getCell(0);
					String mName = ylc.getStringCellValue();
					yll = yls.findSame(mName);
					System.out.println(mName);
					if (!yll.isEmpty()) {
						Yuanliao yuanliao = yll.get(0);
						ylc.setCellValue(yuanliao.getMaId());
						// 这一步是如果没有找到替换原料，则到smallname这张表中查找名字，否则输出
						// 找不到替换原料
					} else {
						int j = new Ifempty().find(mName);
						if (j != -1) {
							ylc.setCellValue(j);
						} else {
							System.out.println("找不到替换原料");
						}
					}

				}
			}
			FileOutputStream out = new FileOutputStream("D:\\zhongyi.xls");
			wookbook.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
