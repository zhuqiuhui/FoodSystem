package com.ustb.food.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class test_poi {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		File filey = new File("D:\\xin-1.xls");
		HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filey));
		HSSFSheet sheet = wookbook.getSheet("Sheet1");
		HSSFRow row = sheet.getRow(0);
		HSSFCell cell = row.getCell(1);
		String value = cell.getStringCellValue();
		System.out.println(value);
		cell.setCellValue("a");
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("D:\\xin-1.xls");
			wookbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
