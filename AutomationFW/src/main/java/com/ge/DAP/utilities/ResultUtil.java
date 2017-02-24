package com.ge.DAP.utilities;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.ge.DAP.actionEngines.InitiateDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ResultUtil {
	public static  Logger log;
	public static  ExtentReports reporter;
	public static ExtentTest test;

static{
		log = LogManager.getLogger(ResultUtil.class.getName());
		reporter = new ExtentReports(System.getProperty("user.dir")+"/ExternalReport/"+"DAP_AUTOMATION.html");
		reporter.loadConfig(new File(System.getProperty("user.dir")+"/src/main/resources/extent-config.xml"));
	}

	public static void report(String strStatus, String strDesc) {
		if (strStatus.equalsIgnoreCase("PASS")) {
			test.log(LogStatus.PASS, strDesc);
			log.info("PASS - " + strDesc);
			System.out.println("PASS - " + strDesc);
		} else if (strStatus.equalsIgnoreCase("FAIL")) {
			test.log(LogStatus.FAIL, strDesc);
			
			test.log(LogStatus.INFO, "Snapshot below: " + test.addScreenCapture(takeScreenshot()));
			log.error("FAIL - " + strDesc);
			System.out.println("FAIL - " + strDesc);
		} else if (strStatus.equalsIgnoreCase("INFO")) {
			test.log(LogStatus.INFO, strDesc);
			log.info("INFO - " + strDesc);
			System.out.println("INFO - " + strDesc);
		} else if (strStatus.equalsIgnoreCase("SKIP")) {
			test.log(LogStatus.SKIP, strDesc);
			log.info("SKIP - " + strDesc);
			System.out.println("SKIP - " + strDesc);
		}
		
	}

	/*	// Hard Stop
		if (strStatus.equalsIgnoreCase("FAIL")
				&& HelperUtil.executionConfigMap.get("HARD_STOP").equalsIgnoreCase("Y")) {
			BrowserUtil.closeBrowser();
			System.exit(0);
		}
*/	

	public static String takeScreenshot() {
		String strFileName = UUID.randomUUID().toString() + ".jpg";
		String imagePath=null;
		try {
//			InitiateDriver initDriver= new InitiateDriver();
			EventFiringWebDriver event = new EventFiringWebDriver(InitiateDriver.getDriver());
			File srcFile = event.getScreenshotAs(OutputType.FILE);
			File destFile = new File(System.getProperty("user.dir")+"/ExternalReport/screenshots/"+strFileName);
			FileUtils.copyFile(srcFile, destFile);
			imagePath= System.getProperty("user.dir")+"/ExternalReport/screenshots/"+strFileName;
		} catch (Exception e) {

		}
		return imagePath;
	}
	}
