package com.ustb.food.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * 这个方法是将统计好的原料匹配的原料的各种信息写入D:\\neural.csv*/
public class TxtWriter {
	public void Writer(Map<String, Integer> map, HashMap hashmap, String name) {
		File file = new File("D:\\neural.csv"); // 找到File类的实例
		try {
			// 创建文件
			OutputStreamWriter write = new OutputStreamWriter(
					new FileOutputStream(file, true));
			BufferedWriter writer = new BufferedWriter(write);
			// 写入数据
			Set<String> keys = map.keySet(); // 得到全部的key变成Set集合
			Iterator<String> iter = keys.iterator(); // 实例化Iterator对象
			while (iter.hasNext()) {
				String key = iter.next(); // 取出key
				Integer value = map.get(key);
				String type = (String) hashmap.get(key);
				if (value > 3) {
					// name表示的是查找的元原料，（key,value）是存放在一个map中，key表示的是匹配上的原料
					// value是匹配的次数，type是匹配上的原料所属的类型
					writer.write(name + "," + key + "," + value + "," + type);
					writer.newLine();
				}
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}