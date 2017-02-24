package com.ge.DAP.UItestscipts;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ge.DAP.actionEngines.InitiateDriver;
import com.ge.DAP.utilities.*;
import com.ge.UI.pages.LoginPage;
public class FirstTest extends InitiateDriver {
	
	@Test(priority = 1)
	public void verifySSOLogin() throws InterruptedException {

		LoginPage lp = new LoginPage(getDriver());
		ResultUtil.test = ResultUtil.reporter.startTest("verifySSOLogin on "+browser);
		lp.login(readExcel.getCellData("DemoData", "User", 2), readExcel.getCellData("DemoData", "password", 2));
		Thread.sleep(10000);
	if (false) {
		ResultUtil.report("PASS", "logout buton presnet");
	} else {
		ResultUtil.report("FAIL", "logout buton not presnet");
	}
	}
	
	

	@Test(priority = 2)
	public void verifySSOLogout() {

		LoginPage lp = new LoginPage(getDriver());
		ResultUtil.test = ResultUtil.reporter.startTest("verifySSOLogouton "+browser);
		if (false) {
			ResultUtil.report("PASS", "logout buton presnet");
		} else {
			ResultUtil.report("FAIL", "logout buton not presnet");
		}
		}


}
