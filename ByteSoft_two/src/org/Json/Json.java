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
	     * ����Ǵ��ⲿץȡJson���ݣ������ַ���������
	     */
	    while ((line = mess.readLine()) != null) {
	        sb.append(line);
	    }
	    /**
	     * ���ַ������Ϊ�ַ���
	     */
	    		 
	    read = sb.toString();
	    /**
	     * Json��һ��������������������ֱ��key-valueȡ����,���ﲻ������ֱ�������������װ��
	     */
//	    JSONObject json = JSONObject.fromObject(read);
//	    System.out.println("json"+json);
	    /**
	     * ����ȡ��
	     */
//	    account=json.getString("name");
//	    password=json.getString("pwd");
//		System.out.println("��ȡ��name+pwd"+account+password);
	    return read;
	}
	
	public String JSONgymnasium(Gymnasium gymnasium) {
		return null;
	}
}