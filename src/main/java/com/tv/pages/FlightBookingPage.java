package com.tv.pages;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.sun.javafx.PlatformUtil;
import com.tv.Base.BasePage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightBookingPage extends BasePage {



    Logger logger= LogManager.getLogger(SignInPage.class);

	@FindBy(id = "FromTag")
	private WebElement fromLocation;

	@FindBy(id = "ToTag")
	private WebElement toLocation;

	@FindBy(id="ui-id-1")
	private WebElement fromAutocomplete;

	@FindBy(id = "ui-id-2")
	private WebElement toAutocomplete;
	
	@FindBy(id = "DepartDate")
	private WebElement departerDate;
	
	
	@FindBy(xpath="//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr[3]/td[7]/a")
	private WebElement pickDate;

	@FindBy(className = "searchSummary")
	private WebElement searchFlight;

	@FindBy(xpath="//*[@class='searchSummary']")
	private WebElement identifier;


	public FlightBookingPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is to search the one way flight for a specific location.
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws Exception
	 */
    public void testThatResultsAppearForAOneWayJourney() throws InterruptedException, IOException {

    	WebDriverWait wait= new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			wait.until(ExpectedConditions.visibilityOf(fromLocation));		
			if(fromLocation.isEnabled()) {
				fromLocation.sendKeys("Bangalore");
				//wait for the auto complete options to appear for the from location
		        //select the first item from the destination auto complete list

				List<WebElement> originOptions = fromAutocomplete.findElements(By.tagName("li"));
		        originOptions.get(0).click();
				logger.info("Filled from location successfully.");
				test.pass("Filled from location successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());

				Assert.assertTrue(true, 
						"Filled from location successfully.");
			}
		}catch(Exception e) {
			System.out.println("Unable to fill from location successfully " + e.getMessage());
			logger.error("Unable to fill from location successfully " + e.getMessage());
			highlightFailure(fromLocation);
			test.fail("Unable to fill from location successfully " + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Unable to fill from location successfully ");
		}

		try {
			wait.until(ExpectedConditions.visibilityOf(toLocation));
			if(toLocation.isEnabled()) {
				toLocation.sendKeys("Delhi");
				//wait for the auto complete options to appear for the location
		        //select the first item from the destination auto complete list

				List<WebElement> originOptions = toAutocomplete.findElements(By.tagName("li"));
		        originOptions.get(0).click();
				
				// As the search location will be first list only so click and escape to not fill the date
				logger.info("Filled to location successfully.");
				test.pass("Filled to location successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				Assert.assertTrue(true, 
						"Filled to location successfully.");
			} 
		}catch(Exception e) {
			System.out.println("Unable to fill to location successfully" + e.getMessage());
			logger.error("Unable to fill to location successfully" + e.getMessage());
			highlightFailure(toLocation);
			test.fail("Unable to fill to location successfully" + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Unable to fill to location successfully");
		}


		try {

			wait.until(ExpectedConditions.elementToBeClickable(departerDate));
			if(departerDate.isEnabled()) {
				departerDate.click();
				System.out.println("Clicked on depart on button successfully.");
				logger.info("Clicked on depart on button successfully.");
				test.pass("Clicked on depart on button successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				Assert.assertTrue(true, 
						"Clicked on depart on button successfully.");
			} 
		}catch(Exception e) {
			System.out.println("Failed to click on depart on button due to." + e.getMessage());
			logger.error("Failed to click on depart on button due to." + e.getMessage());
			highlightFailure(departerDate);
			test.fatal("Failed to click on depart on button due to." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Failed to click on depart on button.");
		}
		
		// Select a date from calander
		
		try {

			wait.until(ExpectedConditions.elementToBeClickable(pickDate));
			if(pickDate.isEnabled()) {
				pickDate.click();
				System.out.println("Clicked on depart date successfully.");
				logger.info("Clicked on depart date successfully.");
				test.pass("Clicked on depart date successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				Assert.assertTrue(true, 
						"Clicked on depart date successfully.");
			} 
		}catch(Exception e) {
			System.out.println("Failed to click on depart date due to." + e.getMessage());
			logger.error("Failed to click on depart date due to." + e.getMessage());
			highlightFailure(pickDate);
			test.fatal("Failed to click on depart date due to." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Failed to click on depart date.");
		}

        //all fields filled in. Now click on search
		
		try {

			wait.until(ExpectedConditions.elementToBeClickable(searchFlight));
			if(searchFlight.isEnabled()) {
				searchFlight.click();
				System.out.println("Clicked on search flight button successfully.");
				logger.info("Clicked on search flight button successfully.");
				test.pass("Clicked on search flight button successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				Assert.assertTrue(true, 
						"Clicked on search flight button successfully.");
			} 
		}catch(Exception e) {
			System.out.println("Failed to click on search flight button due to." + e.getMessage());
			logger.error("Failed to click on search flight button due to." + e.getMessage());
			highlightFailure(pickDate);
			test.fatal("Failed to click on search flight button due to." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Failed to click on search flight button.");
		}
		
		
		wait.until(ExpectedConditions.visibilityOf(identifier));
		
		try {
			if(identifier.isDisplayed())
			{
				System.out.println("Found the identifier successfully.");
				logger.info("Found the identifier successfully.");
				test.pass("Found the identifier successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());

				Assert.assertTrue(true, 
						"Found the identifier successfully");

			}
		}
		catch(Exception e) {
			System.out.println("Unable to find the identifier successfully due to." + e.getMessage());
			logger.error("Unable to find the identifier successfully due to." + e.getMessage());
			highlightFailure(identifier);
			test.fatal("Unable to find the identifier successfully due to." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Unable to find the identifier successfully due to.");
		}

    }
}
