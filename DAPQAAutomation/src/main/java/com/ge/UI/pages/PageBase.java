package com.ge.UI.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ge.DAP.utilities.ResultUtil;
import com.ge.DAP.utilities.XMLReader;

import java.util.HashMap;
import java.util.Map.Entry;

public class PageBase {

	public WebDriver driver;
//	protected Log logger;
	private String fileName;
	private HashMap<String, String> map;

	public PageBase(String fileName, WebDriver driver) {

//		logger = LogFactory.getLog(this.getClass().getName());
		this.fileName = fileName;
		this.driver = driver;

	}

	/**
	 * get current location of the browser
	 * 
	 * @return
	 */
	/*
	  public String getCurrentLocation() {
	  driver.executeScript("return document.readyState;").equals("complete");
	  return driver.getCurrentUrl(); }
	*/ 

	/**
	 * find and return WebElement corresponding to element
	 *
	 * @param fileName
	 * @param element
	 * @return WebElement
	 */
	public WebElement find(String element) {
		WebElement webElement = null;
		map = XMLReader.getDOMElements(fileName, element);

		try {
			for (Entry<String, String> entry : map.entrySet()) {
				String identifier = entry.getKey();
				String locator = entry.getValue();
				if (identifier.equalsIgnoreCase("xpath")) {
					webElement = driver.findElement(By.xpath(locator));
				} else if (identifier.equalsIgnoreCase("id")) {
					webElement = driver.findElement(By.id(locator));
				} else if (identifier.equalsIgnoreCase("cssselector")) {
					webElement = driver.findElement(By.cssSelector(locator));
				} else if (identifier.equalsIgnoreCase("name")) {
					webElement = driver.findElement(By.name(locator));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return webElement;
	}

	
	
	/** input text on a web element
	* @element key from xml
	* @text value to input
	*/
	public void inputText(String elementKey, String value) {
		
		try {
			
			find(elementKey).clear();
			find(elementKey).sendKeys(value);
			ResultUtil.report("PASS", "entered text in "+elementKey);
		} catch (Exception e) {
			ResultUtil.report("FAIL", " failed entering text in "+elementKey);		
			}

	}
	
	public void click(String elementKey) {
		try {
			find(elementKey).click();
			ResultUtil.report("PASS", "clicked on "+elementKey);
		} catch (Exception e) {
			ResultUtil.report("FAIL", "clicked on "+elementKey);		
			}
	}
	
	/*public boolean isElementPresent(String elementKey) {
		boolean flag=false;
		try {
			
			if (find(elementKey).isDisplayed()) {
                 flag= true;		
//                 ResultUtil.report("PASS", elementKey+" is present");
			}

		} catch (Exception e) {
			
			flag= false;
//			ResultUtil.report("FAIL",elementKey+" is absent"+e.getMessage());
		}
		return flag;
	}
*/	
	public boolean isElementPresent(String elementKey) {
		boolean isPresent = false;
		try {
			return find(elementKey).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}

	}
	
	
}
