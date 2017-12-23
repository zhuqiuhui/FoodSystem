package com.ustb.food.tool;

import org.springframework.stereotype.Component;
import com.mathworks.toolbox.javabuilder.MWException;
import connect.Connect;
/*
 * 对神经网络进行连接的工具
 * */
@Component("connectShengJing")
public class ConnectShengJing {
	public void conn() {
		String dataLocation="D:\\panding1.xlsx";
		String netLocation="D:\\pso_bp.mat";
		try {
			Connect conn=new Connect();
			conn.connect(netLocation,dataLocation);
		} catch (MWException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ConnectShengJing().conn();
	}
}
