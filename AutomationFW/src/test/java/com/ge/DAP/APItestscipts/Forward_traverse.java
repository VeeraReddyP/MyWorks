package com.ge.DAP.APItestscipts;

import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ge.DAP.actionEngines.ApiHanddler;
import com.ge.DAP.utilities.ExcelDP;
import com.ge.DAP.utilities.Getconfig;
import com.ge.DAP.utilities.ResultUtil;

public class Forward_traverse  extends ApiHanddler {
	final static Logger log = Logger.getLogger(SearchApi.class);
	//private String api_url = Getconfig.get_properties("Fwd_Traverse_url");
	
	@Test(dataProvider = "getData",description ="Testing the search hospitals by customer id  ", enabled = true)
	
	public void getHsptl_byclientID(String TC_NO,String Url_params, String Headers,String staus_code,String err_msg,String Final_URL) throws Exception{
		ResultUtil.test=ResultUtil.reporter.startTest("Testing the search hospitals by cust id ");
		ResultUtil.report("INFO", "entered API URL ::::\n"+Final_URL);
		
		HashMap<String, String> hmp = (HashMap<String, String>) headerValues(Headers);
		ResultUtil.report("INFO", "Required Headers ::::: \n "+ hmp);
		
		HttpResponse getrsp = get_api(Final_URL,hmp);		
		int rsp_status_code = response_code(getrsp);
		validate_equal(rsp_status_code, Integer.parseInt(staus_code));
	}
	
	@Test(dataProvider = "getdeptData",description ="Testing the search departments by Hospital id  ", enabled = false)
	public void getDepts_byhsptlID(String TC_NO,String Url_params, String Headers,String staus_code,String err_msg,String Final_URL) throws Exception{
		ResultUtil.test=ResultUtil.reporter.startTest("Testing the search departments by Hospital id ");
		
		ResultUtil.report("INFO", "entered API URL ::::\n"+Final_URL);	
			
		HashMap<String, String> hmp = (HashMap<String, String>) headerValues(Headers);
		ResultUtil.report("INFO", "Required Headers ::::: \n "+ hmp);
		
		HttpResponse getrsp = get_api(Final_URL,hmp);		
		int rsp_status_code = response_code(getrsp);
		validate_equal(rsp_status_code, Integer.parseInt(staus_code));
		
	}
	
	
	@Test(dataProvider = "getmachieData",description ="Testing the search machine by asset id  ", enabled = false)
	public void getmachine_byID(String TC_NO,String Url_params, String Headers,String staus_code,String err_msg,String Final_URL) throws Exception{
		ResultUtil.test=ResultUtil.reporter.startTest("Testing the search machine by asset id ");
		
		ResultUtil.report("INFO", "entered API URL ::::\n"+Final_URL);	
			
		HashMap<String, String> hmp = (HashMap<String, String>) headerValues(Headers);
		ResultUtil.report("INFO", "Required Headers ::::: \n "+ hmp);
		
		HttpResponse getrsp = get_api(Final_URL,hmp);		
		int rsp_status_code = response_code(getrsp);
		validate_equal(rsp_status_code, Integer.parseInt(staus_code));
		
	}
	
	@DataProvider
	public Object[][] getmachieData() throws Exception
	{
		return ExcelDP.getTableArray("Api_testdata","Machinedata");	
		
	}
	@DataProvider
	public Object[][] getdeptData() throws Exception
	{
		return ExcelDP.getTableArray("Api_testdata","DepartmentData");	
		
	}
	
	@DataProvider
	public Object[][] getData() throws Exception
	{
		return ExcelDP.getTableArray("Api_testdata","FwdTraverse");	
		
	}
	
}
