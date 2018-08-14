
package com.test.demo.client.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.laituan.model.Model;

/**
 * 客户端请求
 *
 */
public class HttpUtil
{
	// 创建HttpClient对象
	public static HttpClient httpClient = new DefaultHttpClient();
	public static final String Log_URL = 
		Model.HTTPURL+Model.SHOPURL+"/User/login.html";
	public static final String Sear_URL = 
			Model.HTTPURL+Model.SHOPURL+"/Search/index.html";
	public static final String Res_URL = 
			Model.HTTPURL+Model.SHOPURL+"/User/register.html";
	public static final String Per_URL = 
			Model.HTTPURL+Model.SHOPURL+"/center/index.html";
	public static final String Logout_URL=
			Model.HTTPURL+Model.SHOPURL+"/User/logout.html";
		public static final String Car_URL = 
				Model.HTTPURL+Model.SHOPURL+"/shopcart/addItem/index.html";
		public static final String Buy_URL = 
				Model.HTTPURL+Model.SHOPURL+"/Shopcart/order.html";
	/**
	 * 
	 * 
	 * @param url 发送请求的URL
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public static String getRequest(String url)
		throws Exception
	{
		// 创建HttpGet对象。
		HttpGet get = new HttpGet(url);
		// 发送GET请求
		HttpResponse httpResponse = httpClient.execute(get);
		// 如果服务器成功地返回响应
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			// 获取服务器响应字符串
			String result = EntityUtils
				.toString(httpResponse.getEntity());
			return result;
		}
		return null;
	}

	/**
	 * 
	 * @param url 发送请求的URL
	 * @param params 请求参数
	 * @return 服务器响应字符串
	 * @throws Exception
	 */
	public static String postRequest(String url
		, Map<String ,String> rawParams)throws Exception
	{
		// 创建HttpPost对象。
		HttpPost post = new HttpPost(url);
		// 如果传递参数个数比较多的话可以对传递的参数进行封装
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : rawParams.keySet())
		{
			//封装请求参数
			params.add(new BasicNameValuePair(key , rawParams.get(key)));
		}
		// 设置请求参数
		post.setEntity(new UrlEncodedFormEntity(
			params, "UTF-8"));
		// 发送POST请求
		HttpResponse httpResponse = httpClient.execute(post);
		
		// 如果服务器成功地返回响应
		if (httpResponse.getStatusLine()
			.getStatusCode() == 200)
		{
			Log.e("logout",url);
			// 获取服务器响应字符串
			String result = EntityUtils
				.toString(httpResponse.getEntity());
			Log.e("result11111111",result);
			return result;
		}
		else Log.e("posterror",url);
		return null;
	}
}
