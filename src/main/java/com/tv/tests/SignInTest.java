package com.tv.tests;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tv.Base.BasePage;
import com.tv.listeners.ExtentReportListener;
import com.tv.pages.SignInPage;

// To generate the extent report
@Listeners({ExtentReportListener.class})
public class SignInTest extends BasePage{


	Logger logger= LogManager.getLogger(SignInTest.class);

	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public SignInPage signIn;

	


	@BeforeMethod // this method will be executed before every @test method
	public void setUp() throws Exception {
		basePage = new BasePage();
		prop = basePage.initialize_Properties();
		driver = basePage.initialize_driver();
		driver.get(prop.getProperty("signInUrl"));
		signIn = new SignInPage(driver);

	}
	/**
	 * This Test case will execute the signIn error test functionality
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Verifying the SignIn Error test")
		public void SignInErrorTest() throws Exception {

		logger.info("SignIn Error test Started");

		try {
			test = extent.createTest("SignIn Error Test");


			signIn.signIn();;
			

		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("SignIn Error test Failed due to " + e.getMessage());
			test.log(Status.FAIL, MarkupHelper.createLabel("Sign In Error test is Failed", ExtentColor.RED));

			finalAssertTrue(false,
					"SignIn Error test is failed!!");

		}
		logger.info("SignIn Error Test Completed");
		test.log(Status.INFO, "SignIn Error Test Completed");
	}
	}









