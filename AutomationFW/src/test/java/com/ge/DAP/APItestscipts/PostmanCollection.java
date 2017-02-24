package com.ge.DAP.APItestscipts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class PostmanCollection {

	
	public static Map<String, Object> api_url_headers(String collection_path) throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(collection_path));
		String collection_json ="";
		StringBuffer sb = new StringBuffer();
		while((collection_json=br.readLine())!=null){
			sb.append(collection_json);			
		}
		JSONObject js = new JSONObject(sb.toString());	
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, String> headers = new HashMap<String, String>();
		
		if(js.has("item")&&js.getJSONArray("item").length()!=0){
		
			for(int i=0;i<js.getJSONArray("item").length();i++){
				String url = js.getJSONArray("item").getJSONObject(i).getJSONObject("request").getString("url");
				JSONArray headers_len = js.getJSONArray("item").getJSONObject(i).getJSONObject("request").getJSONArray("header");
				
				for(int j=0;j<headers_len.length();j++){				
					headers.put(headers_len.getJSONObject(j).getString("key").toString(), headers_len.getJSONObject(j).getString("value").toString());
					}
				hm.put("url", url);
				hm.put("headers", headers);
				if(js.getJSONArray("item").getJSONObject(i).getJSONObject("request").getString("method").equalsIgnoreCase("POST")){
					String post_body = js.getJSONArray("item").getJSONObject(i).getJSONObject("request").getJSONObject("body").getString("raw");
					hm.put("body", post_body);
					}
				}
			}
		return hm;				
	}
	
		
	public static void main(String[] args) throws Exception {
		
		String file_path = "/Users/veerareddy/Desktop/MultiDimSearch.postman_collection.json";
		HashMap<String, Object> ha = (HashMap<String, Object>) api_url_headers(file_path);
		System.out.println(ha.size());
		System.out.println(ha.get("url"));
		System.out.println(ha.get("headers"));
		
		
	}

}
