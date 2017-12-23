package com.ustb.food.tool;

import java.util.ArrayList;

public class hebingList {
	public static void main(String[] args) {
		ArrayList array = new ArrayList();
		array.add(1);
		array.add(2);
		ArrayList list = new ArrayList();
		list.add(2);
		list.add(3);
		array.removeAll(list);
		array.addAll(list);
		System.out.println(array);
	}
}
