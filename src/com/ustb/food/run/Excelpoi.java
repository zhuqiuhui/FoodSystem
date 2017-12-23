package com.ustb.food.run;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.ustb.food.entity.Yuanliao;
import com.ustb.food.service.YuanliaoService;
import com.ustb.food.tool.name_del_kuohao;

/*
 * 依据中文关系将转换为数字形式。依据的是caipu.xls和数据库中的原料表转换。
 * caipu表是在数据库中的编号以及菜谱的名称，替换菜谱的编号是是利用caipu.xls进行的提换
 * 而原料替换的时候是采用连接数据库，找到相同的名称的原料，在找他的id的方式进行的提换
 * */
@Component("excelpoi")
public class Excelpoi {

	public static void main(String[] args) throws Exception {
		Map<String, Integer> mapCode = new HashMap<String, Integer>();
		mapCode = Mapcode(mapCode);
		System.out.println(mapCode);
		tihuan(mapCode);
		name_del_kuohao name_del_kuohao = new name_del_kuohao();
		name_del_kuohao.del_kuohao();
	}

	public static void tihuan(Map mapCode) {

		try {
			ApplicationContext ac = new ClassPathXmlApplicationContext(
					"applicationContext.xml");
			YuanliaoService yls = (YuanliaoService) ac
					.getBean("yuanliaoService");
			List<Yuanliao> yll = new ArrayList<Yuanliao>();
			// 创建对Excel工作簿文件的引用，获取Excel表文件
			File filey = new File("D:\\guanxi.xls");
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
			for (int i = 1; i < rows + 1; i++) {
				// 读取左上端单元格
				HSSFRow row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					// 替换菜谱名称为ID
					HSSFCell hc = row.getCell(0);
					Iterator<Map.Entry<String, String>> it = mapCode.entrySet()
							.iterator();
					while (it.hasNext()) {
						Map.Entry<String, String> entry = it.next();
						String str = new String(entry.getKey());
						if (hc.getStringCellValue().equals(str)) {
							hc.setCellValue(entry.getValue());
							System.out.println("修改数据成功");
						}
					}
					// 替换原料名称为ID
					HSSFCell ylc = row.getCell(1);
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
			update(wookbook, "D:\\xin.xls");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map Mapcode(Map mapCode) {
		try {
			// 创建对Excel工作簿文件的引用，获取Excel表文件
			File filey = new File("D:\\caipu.xls");
			// 然后是创建一个Excel处理类的对象，将输入流中的信息读入到此对象
			HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filey));
			// 在Excel文档中，第一张工作表的缺省索引是0
			// 因为一个Excel中可以有多个表，所以获取当前Excel表的第一张表
			// HSSFSheet sheet= wookbook.getSheetAt(0);
			// 按照名称表明获取一张表
			HSSFSheet sheet = wookbook.getSheet("Sheet1");
			// 获取到Excel文件中的所有行数
			int rows = sheet.getPhysicalNumberOfRows();
			// 遍历行
			for (int i = 1; i < rows + 1; i++) {
				// 读取左上端单元格
				HSSFRow row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					// 获取到Excel文件中的所有的列
					int cells = row.getPhysicalNumberOfCells();
					String value = "";
					// 遍历列
					for (int j = 0; j < cells; j++) {
						// 获取到列的值
						HSSFCell cell = row.getCell(j);
						if (cell != null) {
							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_FORMULA:
								break;
							case Cell.CELL_TYPE_NUMERIC:
								value += cell.getNumericCellValue() + ",";
								break;
							case Cell.CELL_TYPE_STRING:
								value += cell.getStringCellValue() + ",";
								break;
							default:
								value += "0";
								break;
							}
						}
					}
					// 将每一行的数据取出来value是一行数据，value[1]代表的名称，value[0]代表的是编号
					// 报错的原因可能是excel表的问题
					String[] val = value.split(",");
					// for (int k = 0; k < val.length; k += 2) {
					if (val.length >= 2) {
						mapCode.put(val[1], val[0]);
					}
					// }

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapCode;
	}

	public static void update(HSSFWorkbook wb, String path) {
		try {
			FileOutputStream fout = new FileOutputStream(path);
			try {
				wb.write(fout);
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
