package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;

import com.ustb.food.entity.Perdiet;
import com.ustb.food.service.PerdietService;
import com.ustb.food.util.RecognizeModel;

public class Test {
	
	@Autowired
	static
	PerdietService perdietService;

	public static void main(String[] args) {
//		String parameter = "";
//		String res = RecognizeModel.recoWestHypertension(parameter);
//		System.out.println(res);
//		test02();
		testGetList("xyt");
	}
	
	private static void test01() {
		PythonInterpreter interpreter = new PythonInterpreter(); 
		interpreter.exec("import pandas as pd");
		interpreter.execfile("/Users/zhuqiuhui/zqh/code/foodAnyalis/model/hypertension/test.py");  
		 PyFunction func = (PyFunction)interpreter.get("add",PyFunction.class);
		 PyObject pyobj = func.__call__(new PyInteger(1), new PyInteger(3));  
		 System.out.println("anwser = " + pyobj.toString());
	}
	
	private static void test02() {
		try {
//			PythonInterpreter interpreter = new PythonInterpreter(); 
//			interpreter.exec("from sklearn.externals import joblib");
			String modelPath = "/Users/zhuqiuhui/zqh/code/foodAnyalis/model/hypertension/xgb_model_west_hypertension.m";
			String param = "309.58,66.72,2.12,8.02,2.15,19.97,47.03,3.88,118.71,0.24,0.09,1.35,0.0,90.71,48.0,4.38,1.74,0.43,0.78,354.52,182.42,4.4,3.85";
			String[] args = new String[] { "/usr/local/bin/Python", "/Users/zhuqiuhui/zqh/code/foodAnyalis/model/hypertension/test.py", modelPath, param};
			Process pr = Runtime.getRuntime().exec(args);
//			from sklearn.externals import joblib
			
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				 System.out.println("---"+line);
			}
			in.close();
			pr.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void test03() {
		System.out.println(4 + "," + 7);
	}
	
	private static void test04() {
		try {
			String param = "309.58,66.72,2.12,8.02,2.15,19.97,47.03,3.88,118.71,0.24,0.09,1.35,0.0,90.71,48.0,4.38,1.74,0.43,0.78,354.52,182.42,4.4,3.85";
			String[] args = new String[] { "/usr/local/bin/Python", "/Users/zhuqiuhui/zqh/code/foodAnyalis/model/hypertension/test.py", param};
			Process pr = Runtime.getRuntime().exec(args);
            InputStreamReader ir = new InputStreamReader(pr.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while((line = input.readLine()) != null)
                System.out.println(line);
            input.close();
            ir.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testGetList(String vname) {
		System.out.println(vname);
		List<Perdiet> perdietList = perdietService.getList("vname", vname);
		System.out.println("------" + perdietList);
		System.out.println("------" + perdietList.size());
		System.out.println("------" + perdietList.get(0).getVname());
	}
}
