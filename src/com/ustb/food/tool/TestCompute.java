package com.ustb.food.tool;

import com.mathworks.toolbox.javabuilder.MWException;
import compute.ComputeX;

public class TestCompute {
	public static void main(String[] args) throws MWException {
		ComputeX compute = new ComputeX();
		Object[] limit = compute.compute(1, 0.1);
		// double l = (Double) limit[0];
		System.out.println(limit);
	}
}
