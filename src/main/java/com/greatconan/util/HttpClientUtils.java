package com.greatconan.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * HttpClient请求工具类
 * @author zhengfu wang
 *
 */
@Service(value = "httpClientUtils")
public class HttpClientUtils {
	
	// 请求超时时间 30秒
	private static final int TIME_OUT = 30000;
	
	// 请求失败返回信息
	private static final String REQUEST_FAILED_MESSAGE = "error";
	
	/**
	 * Post请求
	 * @param url
	 * @param param 请求参数
	 * @return 服务返回信息
	 */
	public static String doPost(String url, Map<String, Object> param){
		
		StringBuilder sb = null;
		
		try{
			
			HttpClient client = new DefaultHttpClient();
			// 请求超时
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT); 
			// 读取超时
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);
			
			HttpPost post = new HttpPost(url);
			
			// 添加请求参数
			if (param != null && param.size() > 0) {
				List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>(
						param.size());
				Set<String> keys = param.keySet();
				for (Object o : keys) {
					String key = (String) o;
					nameValuePairs.add(new BasicNameValuePair(key, String
							.valueOf(param.get(key))));
				}
				post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
			}
			
			HttpResponse response = client.execute(post);
			
			/** 返回状态 **/
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				
				sb = new StringBuilder();
				
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					BufferedReader br = new BufferedReader(new InputStreamReader(
							instream));
					String tempLine;
					while ((tempLine = br.readLine()) != null) {
						sb.append(tempLine);
					}
				}
			}
			
			// 终止请求
			post.abort();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return sb == null ? REQUEST_FAILED_MESSAGE : sb.toString();
	}
	
	/**
	 * Get请求
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		
		StringBuilder sb = null;
		
		try {
			
			HttpClient client = new DefaultHttpClient();
			// 请求超时
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT); 
			// 读取超时
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);
			
			// HttpGet
			HttpGet get = new HttpGet(url);
			HttpResponse response = client.execute(get);
			StatusLine state = response.getStatusLine();
			if (state.getStatusCode() == HttpStatus.SC_OK) {
				
				sb = new StringBuilder();
				
				HttpEntity eneity = response.getEntity();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						eneity.getContent()));
				String content;
				while ((content = br.readLine()) != null) {
					sb.append(content);
				}
			}
			
			// 终止请求
			get.abort();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb == null ? REQUEST_FAILED_MESSAGE : sb.toString();
	}
	
	/**
	 * 上传文件
	 * @param url
	 * @param param
	 * @param file
	 * @return
	 */
	public static String fileUpload(String url, Map<String, String> param, File file) {
		
		// 请求返回信息
		StringBuilder sb = null;
		
		try{
			
			HttpClient client = new DefaultHttpClient();
			// 请求超时
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT); 
			// 读取超时
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, TIME_OUT);
			
			// 添加请求参数
			MultipartEntity entity = new MultipartEntity();
			if (param != null && !param.isEmpty()) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					entity.addPart(entry.getKey(), new StringBody(entry.getValue()));
				}
			}
			
			// 添加文件
			if (file != null && file.exists()) {
				entity.addPart("file", new FileBody(file));
			}
			
			HttpPost post = new HttpPost(url);
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			int stateCode = response.getStatusLine().getStatusCode();
			if (stateCode == HttpStatus.SC_OK) {
				
				sb = new StringBuilder();
				
				HttpEntity result = response.getEntity();
				if (result != null) {
					InputStream is = result.getContent();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is));
					String tempLine;
					while ((tempLine = br.readLine()) != null) {
						sb.append(tempLine);
					}
				}
			}
			
			// 终止请求
			post.abort();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return sb == null ? REQUEST_FAILED_MESSAGE : sb.toString();
	}
	public static void main(String[] args) {
		System.out.println((HttpClientUtils.doGet("http://www.suning.com")));
	}
}
