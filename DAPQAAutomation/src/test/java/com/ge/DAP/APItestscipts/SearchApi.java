package com.ge.DAP.APItestscipts;

import java.util.HashMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ge.DAP.actionEngines.ApiHanddler;
import com.ge.DAP.utilities.ExcelDP;
import com.ge.DAP.utilities.Getconfig;
import com.ge.DAP.utilities.ResultUtil;





public class SearchApi extends ApiHanddler  {
	
	final static Logger log = Logger.getLogger(SearchApi.class);
	static String actual_url = System.getProperty("environment");
	
	
	
	@Test(dataProvider = "getData",description ="Testing the search  API ", enabled = true)
	public void searchby_AssetId(String TC_NO,String TC_Name,String Url_params, String Headers,String staus_code) throws Exception{
	
		ResultUtil.test=ResultUtil.reporter.startTest("Testing the search by asset_ID API");
		String url= actual_url+Url_params;
		log.info("actual_url ::::"+url);
		ResultUtil.report("INFO", "entered API URL ::::\n"+url);		
		HashMap<String, String> hmp = (HashMap<String, String>) headerValues(Headers);
		ResultUtil.report("INFO", "Required Headers ::::: \n"+hmp);
		HttpResponse getrsp = get_api(url,hmp);		
		int rsp_status_code = response_code(getrsp);
		validate_equal(rsp_status_code, Integer.parseInt(staus_code));
		ResultUtil.report("INFO", "Expected status code :::::\n "+rsp_status_code);
		if(rsp_status_code==200){
		String api_rsp = api_responseContent(getrsp);
		validate(api_rsp);
		ResultUtil.report("INFO", "Expected Response Body :::::\n "+api_rsp);
		}
	}
	
	
	@DataProvider
	public Object[][] getData() throws Exception
	{
		return ExcelDP.getTableArray("Api_testdata","SearchAsset_Site");	
		
	}

/*	@AfterMethod
	public void closeActivities(ITestResult testResult) {
		if (testResult.getStatus() == ITestResult.FAILURE) {

			ResultUtil.report("FAIL", "test failed");

		} else if (testResult.getStatus() == ITestResult.SUCCESS) {
			ResultUtil.report("PASS", " test passed");
		} else if (testResult.getStatus() == ITestResult.SKIP) {
			ResultUtil.report("SKIP", "test skipped");
		}
		ResultUtil.reporter.endTest(ResultUtil.test);
		ResultUtil.reporter.flush();

	}
*/
	
	
}
