package com.ge.DAP.actionEngines;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.ge.DAP.utilities.Getconfig;
import com.ge.DAP.utilities.ResultUtil;
import com.ge.DAP.utilities.XLReader;

import java.util.concurrent.TimeUnit;


public class InitiateDriver {
	
	public XLReader readExcel;

    private static WebDriver driver;
    protected String browser;

    /**
     * initiate driver depend upon BROWSER and URL value in config.properties file
     */
    
    @BeforeClass
    @org.testng.annotations.Parameters(value={"browser"})
    public void LaunchDriver(String browser){
    	this.browser=browser;
    readExcel = new XLReader(
				System.getProperty("user.dir") + "/src/test/resources/testData.xlsx");

        try{
        /*    String platform = getProperties().get("PLATFORM");
            String browser = null;
            String runOn = getProperties().get("RUN_ON");
            String url = getProperties().get("URL");

            if (runOn.equalsIgnoreCase("WEBSITE")){
                browser = getProperties().get("BROWSER");*/
				if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", "/Users/madhurichilaka/Documents/automation_Related/chromedriver");
					driver= new ChromeDriver();

//                driver = new RemoteWebDriver(new URL(url), getBrowserCapabilities(browser, runOn));
					
				}else if (browser.equalsIgnoreCase("firefox")) {
					driver= new FirefoxDriver();
				}
                driver.get(Getconfig.get_properties("URL"));
              driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//            }else{
//
//            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * get driver based upon BROWSER value in config.properties file, at present supporting IE, Chrome, Firefox
     * @return 
     * @return RemoteWebDriver
     */
    public static WebDriver getDriver()
    {
        if (driver == null)
            throw new RuntimeException("We have not instantiated the driver.");
        return driver;
    }

    /**
     * get desired capabilities for browser
     * @param browser
     * @param runOn
     * @return DesiredCapabilities
     */
    private DesiredCapabilities getBrowserCapabilities(String browser, String runOn){
        DesiredCapabilities capabilities = null;

        if (runOn.equalsIgnoreCase("WEBSITE") && browser.equalsIgnoreCase("Firefox")){
            capabilities = DesiredCapabilities.firefox();
            capabilities.setBrowserName("firefox");
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setPlatform(Platform.ANY);
        }
        else if(runOn.equalsIgnoreCase("WEBSITE") && browser.equalsIgnoreCase("chrome")){

        }

        return capabilities;
    }
  @AfterMethod  
    public void closeActivities() {
    	ResultUtil.reporter.endTest(ResultUtil.test);
		ResultUtil.reporter.flush();
	
	}
}
