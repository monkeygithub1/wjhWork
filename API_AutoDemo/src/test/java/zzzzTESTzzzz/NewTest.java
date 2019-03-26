package zzzzTESTzzzz;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.client.ClientProtocolException;

import wjh.pmis.data.Request;
import wjh.pmis.restclient.RestClient;

public class NewTest {
	public static void main(String[] args) throws ClientProtocolException, IOException{
		String dept = "数据运维二部$6095256cea934417924331ed645806d5";
		int first = dept.indexOf("$");
		String dept_ = dept.substring(0, first);
		String dept_id_ = dept.substring(first+1);
		System.out.println(dept_);
		System.out.println(dept_id_);
		System.out.println("6095256cea934417924331ed645806d5");
		        String a = "娌℃湁杩欐潯寰呭锛  ";
		        String b;
		        try {
		            b = new String(a.getBytes("GBK"),"UTF-8");
		            System.out.println(b);
		        } catch (UnsupportedEncodingException e) {
		            e.printStackTrace();
		        }
		    }
	

}