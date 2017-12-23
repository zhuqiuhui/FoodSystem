package com.ustb.food.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
/*
 * 为Apriori的输入文本进行格式转换。将k-means程序运行结果写入excel中在另存为以/t作为分隔的文本文件
 * 让其以，形式输出，这样才可以在Apriori程序中运行。
 * */
public class AprioriFormat {
	public static void main(String[] args) {
		try {
			new AprioriFormat().outPutFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void  outPutFile(){
		String encoding = "UTF-8"; // 字符编码(可解决中文乱码问题 )
		File file = new File("D:\\data.txt");
		BufferedWriter writer = null;
		if (file.isFile() && file.exists()) {
			InputStreamReader read;
			try {
				read = new InputStreamReader(
						new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTXT = null;
				while ((lineTXT = bufferedReader.readLine()) != null){
					 /*String regEx = "['\t']+"; // 一个或多个空格    
				        Pattern p = Pattern.compile(regEx);    
				        Matcher m = p.matcher(lineTXT); */ 
						//String lineNew=lineTXT.replaceAll("\t", ",").trim();
						String lineNew=lineTXT.replaceAll("\t", ",");
				        //String lineNew=m.replaceAll(",");
						System.out.println(lineNew);
						 writer= new BufferedWriter(new FileWriter(new File("D:\\dataNew.txt")));
						 writer.append(lineNew);
						 writer.newLine();
						 writer.flush();
			} 
				writer.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			}
	}
}
