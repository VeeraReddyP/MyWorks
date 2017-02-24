package com.ge.DAP.actionEngines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.ge.DAP.utilities.Getconfig;
import com.ge.DAP.utilities.ResultUtil;



/**
 * @author Veera
 *
 * 
 */
public class ApiHanddler {
	
	static HttpHost proxy = new HttpHost("10.195.49.209", 80, "http");
	
	
	@AfterTest
	public void report(){
		ResultUtil.reporter.endTest(ResultUtil.test);
		ResultUtil.reporter.flush();
	
	}
	
	
   public static HttpResponse get_api(String url, HashMap<String, String> headers) {
	   
	   HttpClient hc =new DefaultHttpClient();
	   hc.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
	   
	   HttpGet get_call = new HttpGet(url);
	  
	  for(Map.Entry<String, String> en: headers.entrySet()){
		  get_call.addHeader(en.getKey().toString(), en.getValue().toString());
	  }
	  
	  HttpResponse get_resp=null;
	try {
		get_resp = hc.execute(get_call);
		
	} catch (ClientProtocolException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	  
	  return get_resp;
	     
   }
   
   public static HttpResponse post_api(String url,HashMap<String, String> values,HashMap<String, String> headers) {
	   
	  HttpClient hc =new DefaultHttpClient();
	   hc.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
	   HttpPost post_call = new HttpPost(url);
	   for(Map.Entry<String, String> en: headers.entrySet()){
		   post_call.setHeader(en.getKey().toString(), en.getValue().toString());
		  }
		  
	   List<NameValuePair> post_params = new ArrayList<NameValuePair>();
	   for(Map.Entry entry : values.entrySet()){		  
		   post_params.add(new BasicNameValuePair((String)entry.getKey(), (String) entry.getValue()));		   
	   }
	   try {
		post_call.setEntity(new UrlEncodedFormEntity(post_params));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   HttpResponse post_resp=null;
	try {
		post_resp = hc.execute(post_call);
		
	} catch (ClientProtocolException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	   return post_resp;
   }
  
   public static String api_responseContent( HttpResponse response) {
	   
	   BufferedReader br=null;
	try {
		br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	} catch (UnsupportedOperationException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	   StringBuffer sb = new StringBuffer();
	   String api_response = "";
	   try {
		while ((api_response = br.readLine()) != null) {
			
		   	sb.append(api_response);
		   }
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	   
	return sb.toString();
   }
   
   public static int response_code(HttpResponse response){	   
	   int status_code = response.getStatusLine().getStatusCode();
	   return status_code;
   }
   
   public  static Map<String, String> headerValues(String headers){
		HashMap<String, String> hm = new HashMap<String, String>();
		String[] a = headers.split(",");
		for(int i=0;i<a.length;i++){	
			String b[] = a[i].split("=");
			hm.put(b[0].toString(), b[1].toString());
			if(b[0].equalsIgnoreCase("Authorization") && b[1].toString()==""){
				hm.put(b[0].toString(), b[1].toString()+fetch_access_token());
			}
		}	
		return hm;		
	} 
   
   public static String fetch_access_token(){
	   String fetch_token_url = Getconfig.get_properties("token_url");
	   String Access_token=null;	
	   JSONObject js=null;
		
		HashMap<String, String> postheaders = new HashMap<String, String>();
		postheaders.put("Authorization", "Basic ZGFwX2NsaWVudF9pZDpkYXBfY2xpZW50X2lk");
		postheaders.put("Content-Type", "application/x-www-form-urlencoded");
		
		HashMap<String, String> post_values = new HashMap<String, String>();
		post_values.put("grant_type", "client_credentials");
		
		HttpResponse postrsp = post_api(fetch_token_url,post_values,postheaders);
		String post_rsp = ApiHanddler.api_responseContent(postrsp);
		
		//System.out.println("post_rsp:::::: "+post_rsp);
		
		
		try {
			js = new JSONObject(post_rsp);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		try {
			 Access_token = js.getString("access_token").toString();
			 validate(Access_token);	
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
	   
	   return Access_token;
   }
   
   public static boolean validate(String value){	   
	   try{
		   
		   //Assert.assertTrue(value!=null, "Oops!!! got null "+ value);
		   if(value!=null){
		   ResultUtil.report("PASS", "Required value is not null::: \n");
		   }else{
			   ResultUtil.report("FAIL", "Required value is  null  ");  
		   }
		return true;
	   }catch (Exception e){
		   e.printStackTrace();
		   ResultUtil.report("FAIL", " Error Occured due to  "+e);
		 return false;
	   }
	 
	   
	   
   }
   public static boolean validate_equal(String Actualvalue, String Expected){
	   	   
	   try{
		   if(Actualvalue!=Expected){
			   ResultUtil.report("PASS", "Expected value ::: "+Expected  + "Actual Value :::::: "+Actualvalue); 
		   }else{
			   ResultUtil.report("FAIL", "Expected value ::: \t \t "+Expected  + "Actual Value :::::: "+Actualvalue);  
		   }
		  // Assert.assertEquals(Actualvalue, Expected, "Oops!!!! Actual and Expected values doesnt match "+ Actualvalue +" "+ Expected);		   
		 return true;
	   }catch (Exception e){
		   e.printStackTrace();
		  return false;
	   }
	  	   
   }
   public static boolean validate_equal(int Actualvalue, int Expected){
   	   
	   try{
		   //Assert.assertEquals(Actualvalue, Expected, "Oops!!!! Actual and Expected values doesnt match "+ Actualvalue +" "+ Expected);
		   if(Actualvalue!=Expected){
		   ResultUtil.report("PASS", "Expected value ::: "+Expected  + "Actual Value :::::: "+Actualvalue);
		   return true;
		   }else{
			   ResultUtil.report("FAIL", "Expected value ::: \t \t "+Expected  + "Actual Value :::::: "+Actualvalue);  
			   return false;
		   }
		 
	   }catch (Exception e){
		   ResultUtil.report("FAIL", "Expected value ::: \t \t "+Expected  + "Actual Value :::::: "+Actualvalue);
		   e.printStackTrace();
		   return false;
	   }
	  	   
   }
   
   public static String data_generator(){
	   
	   //String[] a = {"!","@","#","$","%","^","&","*","(",")","<",">","?","/","\","};
	   
	   String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*():";
	   SecureRandom rnd = new SecureRandom();
	   int len =10;
	   StringBuilder sb = new StringBuilder( len );	   
	   for( int i = 0; i < len; i++ ) {
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   }
		   return sb.toString();
   
   }
   
}

