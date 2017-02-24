package com.ge.DAP.APItestscipts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.ge.DAP.actionEngines.ApiHanddler;
import com.ge.DAP.utilities.Getconfig;
import com.ge.DAP.utilities.ResultUtil;

public class TraversingAPi extends ApiHanddler  {
	/*
	final static Logger log = Logger.getLogger(TraversingAPi.class);
	private String api_url = Getconfig.get_properties("search_company_url");
	
	@Test(dataProvider = "getData",description ="Testing the search by customer name API ", enabled = true)
	public void searchby_customerName(String TC_NO,String Url_params, String Headers,String staus_code){
		ResultUtil.test=ResultUtil.reporter.startTest("Testing the search by customer name API");
		
		*/
	//public static StringBuffer ab = null;
	
	public static void main(String[] args) throws IOException, JSONException {
		
		/*String x = "sd", y = "", z = "cd";
        String notnull;

        notnull = (x==null& y==null&z==null)? x:y:z;
        System.out.println(notnull + " is the smallest of the three numbers.");
        */
		
		//FileInputStream fs = new FileInputStream(new File("\\Users\\veerareddy\\Desktop\\MultiDimSearch.postman_collection.json"));
	/*
		
		BufferedReader br = new BufferedReader(new FileReader("/Users/veerareddy/Desktop/MultiDimSearch.postman_collection.json"));
		
		String a ="";
		StringBuffer ab = new StringBuffer();
		while((a=br.readLine())!=null){
			ab.append(a);			
		}
		
		
		JSONObject js = new JSONObject(ab.toString());	
		
		HashMap<String, String> headers = new HashMap<String, String>();
		
		if(js.has("item")&&js.getJSONArray("item").length()!=0){
		
			for(int i=0;i<js.getJSONArray("item").length();i++){
				if(js.getJSONArray("item").getJSONObject(i).getJSONObject("request").getString("method").equalsIgnoreCase("GET")){
			
			String url = js.getJSONArray("item").getJSONObject(0).getJSONObject("request").getString("url");
		JSONArray headers_len = js.getJSONArray("item").getJSONObject(i).getJSONObject("request").getJSONArray("header");
			
		for(int j=0;j<headers_len.length();j++){
			headers.put(headers_len.getJSONObject(j).getString("key").toString(), headers_len.getJSONObject(j).getString("value").toString());			
		}
		
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("url", url);
		hm.put("headers", headers);
		System.out.println(hm.size());
		System.out.println(hm.get("url"));
		System.out.println(hm.get("headers"));
		}
		}
		}
		
		*/
		
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$\\{}[]'.%^&*():<>?/";
		//String spl_chrctrs ="!@#$\\{}[]'.%^&*():<>?/"; 
		SecureRandom rnd = new SecureRandom();
		   int len =10;
		   StringBuilder sb = new StringBuilder( len );	   
		   for( int i = 0; i < len; i++ ) {
			      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   }
		   System.out.println(sb);
		   
	}
	
		
	}


