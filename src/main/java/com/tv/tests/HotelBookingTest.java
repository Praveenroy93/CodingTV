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
import com.tv.pages.HotelBookingPage;

@Listeners({ExtentReportListener.class})
public class HotelBookingTest extends BasePage{


	Logger logger= LogManager.getLogger(HotelBookingTest.class);

	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public HotelBookingPage hotelBookingPage;

	


	@BeforeMethod // this method will be executed before every @test method
	public void setUp() throws Exception {
		basePage = new BasePage();
		prop = basePage.initialize_Properties();
		driver = basePage.initialize_driver();
		driver.get(prop.getProperty("hotelBookingUrl"));
		hotelBookingPage = new HotelBookingPage(driver);

	}
	/**
	 * This Test case will execute the hotel search functionality
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Verifying the hotel search  test")
		public void SignInErrorTest() throws Exception {

		logger.info("Hotel Search test Started");

		try {
			test = extent.createTest("Hotel Search Test");


			hotelBookingPage.searchHotel();
			logger.info("Hotel Search Test passed");
			test.log(Status.PASS, "Hotel Search Test passed");
			

		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Hotel Search test Failed due to " + e.getMessage());
			test.log(Status.FAIL, MarkupHelper.createLabel("Hotel Search test Failed", ExtentColor.RED));

			finalAssertTrue(false,
					"Hotel Search test Failed!!");

		}
		logger.info("Hotel Search Test Completed");
		test.log(Status.INFO, "Hotel Search Test Completed");
	}
	
	}



