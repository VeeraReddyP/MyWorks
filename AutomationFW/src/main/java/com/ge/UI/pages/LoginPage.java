package com.ge.UI.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class LoginPage extends PageBase {

	// location of xml file where elements are captured
	private static final String domFile = "/Users/madhurichilaka/Documents/automation_Related/MySample/src/main/java/com/testing/MySample/LoginPage.xml";

	public LoginPage(WebDriver driver) {
		super(domFile, driver);
	}

	public void login(String username,String password) {
		inputText("SSOUser", username);
		inputText("SSOPassword", password);
		click("SSOLoginBtn");
		click("LoginBtn");

	}
	
	public boolean isLogoutPresent(){
		
		return isElementPresent("LogoutBtn");
		
	}

public boolean isLoginPresent(){
		
		return isElementPresent("LoginBtn");
		
	}

	
}
