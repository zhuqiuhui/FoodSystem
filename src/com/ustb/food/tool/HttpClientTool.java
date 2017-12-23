package com.ustb.food.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpClientTool {

	public static String get(String url) throws HttpException, IOException {
		HttpClient httpClient = new HttpClient();

		List<Header> headers = new ArrayList<Header>();
		headers.add(new Header(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.111 Safari/537.36"));
		httpClient.getHostConfiguration().getParams()
				.setParameter("http.default-headers", headers);

		GetMethod method = new GetMethod(url);
		method.getParams().setParameter("http.protocol.cookie-policy",
				CookiePolicy.BROWSER_COMPATIBILITY);
		int statusCode = httpClient.executeMethod(method);
		// byte[] responseBody = method.getResponseBody();
		InputStream inputStream = method.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream));
		StringBuffer stringBuffer = new StringBuffer();
		String str = "";
		while ((str = br.readLine()) != null) {
			stringBuffer.append(str);
			stringBuffer.append("\r\n");
		}

		method.releaseConnection();
		// return new String(responseBody);
		return stringBuffer.toString();
	}

	public static void saveFile(String filePath, String fileName,
			final String content) throws IOException {
		File path = new File(filePath);
		File file = new File(path, fileName);
		if (!path.isDirectory()) {
			path.mkdir();
		}
		if (!file.exists()) {
			file.createNewFile();
		}

		BufferedWriter bufferWritter = new BufferedWriter(new FileWriter(file,
				true));
		bufferWritter.write(content);
		bufferWritter.close();
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		download("http://static.youku.com/index/img/2013/video/blanksprite.png",
				"51bi.gif", "d:\\image\\");
	}

	public static void download(String urlString, String filename,
			String savePath) throws Exception {
		// 构�?URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时�?s
		con.setConnectTimeout(5 * 1000);
		// 输入�?
		InputStream is = con.getInputStream();
		// 1K的数据缓�?
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
		// �?��读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链�?
		os.close();
		is.close();
	}
}
