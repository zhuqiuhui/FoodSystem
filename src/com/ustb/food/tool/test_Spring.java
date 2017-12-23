package com.ustb.food.tool;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ustb.food.action.TuijieAction;
import com.ustb.food.entity.Caipu;
import com.ustb.food.entity.Tuijie;
import com.ustb.food.fptree.FPTree;
import com.ustb.food.fptree.TreeNode;
import com.ustb.food.service.CaipuService;
import com.ustb.food.service.TuijieService;

public class test_Spring {
	static CaipuService caipuService;

	public static void main(String[] args) {
		/*
		 * ApplicationContext ac = new ClassPathXmlApplicationContext(
		 * "applicationContext.xml"); caipuService = (CaipuService)
		 * ac.getBean("caipuService"); caipuService.delete(3118);
		 */
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		TuijieAction fptre = (TuijieAction) ac.getBean("/front/TuijieAction");
		TuijieService bean = (TuijieService) ac.getBean("tuijieService");
		// List<Tuijie> findByName = bean.findByName("小白菜");
		// System.out.println(findByName.get(0).getTnb());
		FPTree fptree = new FPTree();
		fptree.setMinSup(10);
		List<List<String>> transRecords = fptree
				.readTransData("D:\\PT-Tree.txt"); // 第一组测试
		ArrayList<TreeNode> F1 = fptree.buildF1Items(transRecords);
		ArrayList tnb_list = new ArrayList();
		for (TreeNode item : F1) {
			tnb_list.add(item.getName());
		}
		List<Tuijie> list = fptre.findSameType(tnb_list);
		System.out.println(list.size());
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i).getName());
		// }

	}

	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		CaipuService c = (CaipuService) ac.getBean("caipuService");
		List<Caipu> list = c.getList();
		System.out.println(list.size());
	}
}
