import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import net.sf.json.JSONArray;

public class TestJson {
//	public static void main(String[] args) {
//		ArrayList<PageConstruct> list = new ArrayList<PageConstruct>();
//		PageConstruct model1 = new PageConstruct("", false, 0, 1, "", "", 2, 3,
//				"基础数据", 0, "");
//		PageConstruct model2 = new PageConstruct("", false, 0, 1, "", "", 2,
//				19, "统计报表", 0, "");
//		PageConstruct model11 = new PageConstruct("findOrderdetails.action",
//				false, 0, 3, "", "", 3, 4, "数据显示", 0, "");
//		PageConstruct model12 = new PageConstruct("pageManager.action", false,
//				0, 3, "", "", 3, 6, "数据分页查看", 0, "");
//		PageConstruct model21 = new PageConstruct("", false, 0, 19, "", "", 3,
//				5, "统计报表", 0, "");
//		list.add(model1);
//		list.add(model2);
//		list.add(model11);
//		list.add(model12);
//		list.add(model21);
//		JSONArray json = JSONArray.fromObject(list);
//		String result = json.toString();
//		System.out.println("result" + result);
//	}
	
	public static void main(String[] args) {
	       cut();
	       merge();
	   }
	//拆分文件
	   public static void cut() {
	       File file = new File("G:\\test\\access.log");
	       int num = 10;//分割文件的数量

	       long lon = file.length() / 10L + 1L;//使文件字节数+1，保证取到所有的字节
	       try {
	           RandomAccessFile raf1 = new RandomAccessFile(file, "r");

	           byte[] bytes = new byte[1024];//值设置越小，则各个文件的字节数越接近平均值，但效率会降低，这里折中，取1024
	           int len = -1;
	           for (int i = 0; i < 10; i++) {
	               String name = "G:\\test2\\source" + i + ".txt";
	               File file2 = new File(name);
	               RandomAccessFile raf2 = new RandomAccessFile(file2, "rw");

	               while ((len = raf1.read(bytes)) != -1){//读到文件末尾时，len返回-1，结束循环
	                   raf2.write(bytes, 0, len);
	                   if (raf2.length() > lon)//当生成的新文件字节数大于lon时，结束循环
	                       break;
	               }
	               raf2.close();
	           }
	           raf1.close();
	       } catch (FileNotFoundException e) {
	           e.printStackTrace();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }

	   }
	//合并文件
	   public static void merge() {
	       File file = new File("G:\\test2\\new.avi");
	       try {
	           RandomAccessFile target = new RandomAccessFile(file, "rw");
	           for (int i = 0; i < 10; i++) {
	               File file2 = new File("G:\\test2\\source" + i + ".avi");
	               RandomAccessFile src = new RandomAccessFile(file2, "r");
	               byte[] bytes = new byte[1024];//每次读取字节数
	               int len = -1;
	               while ((len = src.read(bytes)) != -1) {
	                   target.write(bytes, 0, len);//循环赋值
	               }
	               src.close();
	           }
	           target.close();
	       } catch (FileNotFoundException e) {
	           e.printStackTrace();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	   }
}
