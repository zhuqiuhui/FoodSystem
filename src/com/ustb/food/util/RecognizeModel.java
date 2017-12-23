package com.ustb.food.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RecognizeModel {
	// python.exe 路径
	private static final String pythonPath = "/usr/local/bin/Python";
	// model 和 py 文件的公共路径
	private static final String commonPath = "/Users/zhuqiuhui/zqh/code/workspace/Food/WebRoot/python/";
	// 西医三种疾病的识别 model 文件位置
	private static String westPy; // py文件
	private static String westHypertensionModel; // 高血压
	private static String westStomachModel; // 养肠胃
	private static String westDiabetesModel; // 糖尿病
	// 中医三种疾病的识别 model 文件位置
	private static String tcmPy; // py文件
	private static String tcmHypertensionModel; // 高血压
	private static String tcmStomachModel; // 养肠胃
	private static String tcmDiabetesModel; // 糖尿病

	static {
		westPy = commonPath.concat("py/west.py");
		westHypertensionModel = commonPath.concat("model/xgb_model_west_hypertension.m");
		westStomachModel = commonPath.concat("model/xgb_model_west_stomach.m");
		westDiabetesModel = commonPath.concat("model/xgb_model_west_diabetes.m");
		tcmPy = commonPath.concat("py/tcm.py");
		tcmHypertensionModel = commonPath.concat("model/lr_model_tcm_hypertension.m");
		tcmStomachModel = commonPath.concat("model/lr_model_tcm_stomach.m");
		tcmDiabetesModel = commonPath.concat("model/lr_model_tcm_diabetes.m");
	}

	
	/**
	 * 识别西医高血压菜谱，返回值是字符串auc
	 * 
	 * @param modelPath, parameter
	 * @return auc值字符串
	 */
	public static String recoWestHypertension(String parameter) {
		return commonLoadModel(westPy, westHypertensionModel, parameter);
	}
	
	/**
	 * 识别西医养肠胃菜谱，返回值是字符串auc
	 * 
	 * @param modelPath, parameter
	 * @return auc值字符串
	 */
	public static String recoWestStomach(String parameter) {
		return commonLoadModel(westPy, westStomachModel, parameter);
	}
	
	/**
	 * 识别西医糖尿病菜谱，返回值是字符串auc
	 * 
	 * @param modelPath, parameter
	 * @return auc值字符串
	 */
	public static String recoWestDiabetes(String parameter) {
		return commonLoadModel(westPy, westDiabetesModel, parameter);
	}
	
	/**
	 * 识别中医高血压菜谱，返回值是字符串auc
	 * 
	 * @param modelPath, parameter
	 * @return auc值字符串
	 */
	public static String recoTcmHypertension(String parameter) {
		return commonLoadModel(tcmPy, tcmHypertensionModel, parameter);
	}
	
	/**
	 * 识别中医养肠胃菜谱，返回值是字符串auc
	 * 
	 * @param modelPath, parameter
	 * @return auc值字符串
	 */
	public static String recoTcmStomach(String parameter) {
		return commonLoadModel(tcmPy, tcmStomachModel, parameter);
	}
	
	/**
	 * 识别中医糖尿病菜谱，返回值是字符串auc
	 * 
	 * @param modelPath, parameter
	 * @return auc值字符串
	 */
	public static String recoTcmDiabetes(String parameter) {
		return commonLoadModel(tcmPy, tcmDiabetesModel, parameter);
	}
	
	private static String commonLoadModel(String pyFile, String modelPath, String parameter) {
		String predictResult = "";
		String[] args = new String[] {pythonPath, pyFile, modelPath, parameter};
		try {
			Process pr = Runtime.getRuntime().exec(args);
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				predictResult = predictResult.concat(line);
			}
			in.close();
			pr.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return predictResult;
	}

}
