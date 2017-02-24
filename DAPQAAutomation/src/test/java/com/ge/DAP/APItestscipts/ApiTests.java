/*package com.ge.DAP.APItestscipts;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ge.DAP.actionEngines.ApiHanddler;
import com.ge.DAP.utilities.ExcelDP;
import com.ge.DAP.utilities.Getconfig;



/**
 * @author Veera
 *
 * 
 */
/*
public class ApiTests extends ApiHanddler
{
	
	final static Logger log = Logger.getLogger(ApiTests.class);
    
@Test(dataProvider = "getData",description ="Testing the REST API Get call ", enabled = true)
public void test1(String TC_NO,String url_params, String Headers,String staus_code) throws ClientProtocolException, IOException, JSONException{
	 String api_url =Getconfig.get_properties("locomotives_url");	 
	 String actual_url = api_url+url_params;
	 log.info("actual_url ::::"+actual_url);
	 
	 
	 
	 System.out.println("actual_url:::::: "+actual_url);
	 String token = Getconfig.get_properties("access_token");	  
	 HashMap<String, String> hp = (HashMap<String, String>) headerValues(Headers,token);
	
	 HttpResponse getrsp = get_api(actual_url,hp);
	 int rsp_status_code = response_code(getrsp);
	 System.out.println(rsp_status_code);
	 Assert.assertEquals(rsp_status_code, Integer.parseInt(staus_code), "OOps!!!!!! got wrng response code" + TC_NO);
	 String rsp = api_responseContent(getrsp);
	 log.info("API RESPONSE ::::"+rsp);
	 System.out.println(rsp);
	 Assert.assertTrue(rsp!=null,"OOps!!!!! got null response " +TC_NO);
	
}

@Test(description ="Testing the REST API Post call ", enabled = true)
public void test2() throws ClientProtocolException, IOException{
	String url = Getconfig.get_properties("post_url");
	
	HashMap<String, String> headers = new HashMap<String, String>();
	headers.put("Authorization", "Basic YXBwX2NsaWVudF9pZDphcHBfY2xpZW50X2lk");
	headers.put("Content-Type", "application/x-www-form-urlencoded");
	
	HashMap<String, String> values = new HashMap<String, String>();
	values.put("grant_type", "client_credentials");
	
	HttpResponse postrsp = post_api(url,values,headers);
	int rsp_status_code = response_code(postrsp);
	String rsp = api_responseContent(postrsp);
	log.info("API RESPONSE code ::::"+rsp_status_code);
	log.info("API RESPONSE ::::"+rsp);
	 System.out.println(rsp);
		
}


@DataProvider
public Object[][] getData() throws Exception
{
	return ExcelDP.getTableArray("Api_testdata","SearchAsset");
	
}
	
}
*/
