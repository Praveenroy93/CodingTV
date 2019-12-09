package com.tv.tests;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sun.javafx.PlatformUtil;
import com.tv.Base.BasePage;
import com.tv.pages.FlightBookingPage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;

public class FlightBookingTest extends BasePage{

	Logger logger= LogManager.getLogger(HotelBookingTest.class);

	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public FlightBookingPage flightBookingPage;




	@BeforeMethod // this method will be executed before every @test method
	public void setUp() throws Exception {
		basePage = new BasePage();
		prop = basePage.initialize_Properties();
		driver = basePage.initialize_driver();
		driver.get(prop.getProperty("flightBookingUrl"));
		flightBookingPage = new FlightBookingPage(driver);

	}
	/**
	 * This Test case will execute the flight search functionality
	 * @throws Exception
	 */
	@Test(priority = 1, description = "Verifying the flight search  test")
	public void flightSearchTest() throws Exception {

		logger.info("Flight Search test Started");

		try {
			test = extent.createTest("Flight Search Test");


			flightBookingPage.testThatResultsAppearForAOneWayJourney();
			logger.info("Flight Search Test passed");
			test.log(Status.PASS, "Flight Search Test passed");


		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Flight Search test Failed due to " + e.getMessage());
			test.log(Status.FAIL, MarkupHelper.createLabel("Flight Search test Failed", ExtentColor.RED));

			finalAssertTrue(false,
					"Flight Search test Failed!!");

		}
		logger.info("Flight Search Test Completed");
		test.log(Status.INFO, "Flight Search Test Completed");
	}

}


  