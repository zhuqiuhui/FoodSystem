package com.ustb.food.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class addZhongyiCaipu {
	public static void main(String[] args) throws Exception {
		new addZhongyiCaipu().get();
	}

	public void get() throws Exception {
		com.mysql.jdbc.Connection connection = new ConnectionFactory()
				.getConnection();
		Statement statement = connection.createStatement();
		// HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		// 创建对Excel工作簿文件的引用，获取Excel表文件
		File filey = new File("D:\\zhongyicaipu.xls");
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
			HSSFRow row = sheet.getRow(i);
			if (row != null) {
				// 得到菜谱的编号
				HSSFCell hc = row.getCell(0);
				int value = (int) hc.getNumericCellValue();
				String sql = "select maid,amount from zhongyiguanxi where viewId="
						+ value + "";
				// 将所有出现的原料全部放入list中
				ResultSet set = statement.executeQuery(sql);
				HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
				while (set.next()) {
					map.put(set.getInt(1), set.getInt(2));
				}
				System.out.println("map" + map);
				List<Double> jisuan = jisuan(map);
				System.out.println("寒热值属性" + jisuan);
				HSSFCell cell2 = row.getCell(2);
				HSSFCell cell3 = row.getCell(3);
				HSSFCell cell4 = row.getCell(4);
				HSSFCell cell5 = row.getCell(5);
				HSSFCell cell6 = row.getCell(6);
				cell2.setCellValue(jisuan.get(0));
				cell3.setCellValue(jisuan.get(1));
				cell4.setCellValue(jisuan.get(2));
				cell5.setCellValue(jisuan.get(3));
				cell6.setCellValue(jisuan.get(4));
				System.out.println(i + "行的寒值" + jisuan.get(2));
				FileOutputStream out = null;
				try {
					out = new FileOutputStream("D:\\zhongyicaipu.xls");
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
	}

	public List jisuan(Map<Integer, Integer> map) throws Exception {
		double wen = 0.0;
		double re = 0.0;
		double han = 0.0;
		double liang = 0.0;
		double ping = 0.0;
		com.mysql.jdbc.Connection connection = new ConnectionFactory()
				.getConnection();
		ArrayList<Double> arrayList = new ArrayList<Double>();
		for (int key : map.keySet()) {
			Statement createStatement = connection.createStatement();
			String sql = "select wen,re,han,liang,ping from zhongyiyuanliao where maId="
					+ key + "";
			ResultSet set = createStatement.executeQuery(sql);
			if (set.next() == false) {
				System.out.println("找不到原料" + key);
			} else {
				Double weight = map.get(key) / (double) 10;
				wen += set.getInt(1) * weight;
				re += set.getInt(2) * weight;
				han += set.getInt(3) * weight;
				liang += set.getInt(4) * weight;
				ping += set.getInt(5) * weight;

				/*
				 * System.out.println("wen" + wen); System.out.println("re" +
				 * re); System.out.println("hang" + han);
				 * System.out.println("liang" + liang);
				 * System.out.println("ping" + ping);
				 */

			}
		}
		arrayList.add(wen);
		arrayList.add(re);
		arrayList.add(han);
		arrayList.add(liang);
		arrayList.add(ping);
		return arrayList;

	}
}
