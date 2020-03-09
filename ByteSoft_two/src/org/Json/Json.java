package org.Json;

import java.io.BufferedReader;
import java.io.IOException;

import org.table.Gymnasium;

public class Json {
	private static String read=null;
	private  static BufferedReader mess=null;
	public Json() {}
	public static String SetJson(BufferedReader mess) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    /**
	     * 这个是从外部抓取Json数据，存入字符串缓冲中
	     */
	    while ((line = mess.readLine()) != null) {
	        sb.append(line);
	    }
	    /**
	     * 将字符缓冲变为字符串
	     */
	    		 
	    read = sb.toString();
	    /**
	     * Json第一步解析，解析出来可以直接key-value取出来,这里不解析，直接输出，这个类封装。
	     */
//	    JSONObject json = JSONObject.fromObject(read);
//	    System.out.println("json"+json);
	    /**
	     * 这里取出
	     */
//	    account=json.getString("name");
//	    password=json.getString("pwd");
//		System.out.println("获取的name+pwd"+account+password);
	    return read;
	}
	
	public String JSONgymnasium(Gymnasium gymnasium) {
		return null;
	}
}
