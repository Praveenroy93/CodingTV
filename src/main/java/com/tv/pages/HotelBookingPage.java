package com.tv.pages;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.sun.javafx.PlatformUtil;
import com.tv.Base.BasePage;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
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

public class HotelBookingPage extends BasePage{

	Logger logger= LogManager.getLogger(SignInPage.class);

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink1;
    
    @FindBy(xpath="(//a[@href='/hotels'])[2]")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;
    
    @FindBy(xpath="(//li[@class='list'])[1]/a")
    private WebElement locationText;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;
    
    @FindBy(xpath="//*[text()='Showing hotels around']")
    private WebElement identifier;
    
    
    public HotelBookingPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    
    /**
     * This method is to search the hotel in a specific location.
     * @throws Exception
     */
    
    public void searchHotel() throws Exception{

		WebDriverWait wait= new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		try {
			wait.until(ExpectedConditions.visibilityOf(hotelLink));		
			if(hotelLink.isEnabled()) {
				hotelLink.click();
				logger.info("Clicked on Hotels link button successfully.");
				test.pass("Clicked on Hotels link button successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());

				Assert.assertTrue(true, 
						"Clicked on Hotels link button successfully.");
			}
		}catch(Exception e) {
			System.out.println("Unable to click on Hotels link due to ." + e.getMessage());
			logger.error("Unable to click on Hotels link due to ." + e.getMessage());
			highlightFailure(hotelLink);
			test.fail("Unable to click on Hotels link due to ." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Unable to click on Hotels link Button successfully.");
		}

		try {
			wait.until(ExpectedConditions.visibilityOf(localityTextBox));
			if(localityTextBox.isEnabled()) {
				localityTextBox.sendKeys("Indiranagar, Bangalore");
				Thread.sleep(5000);
				localityTextBox.sendKeys(Keys.ENTER, Keys.ESCAPE);

				logger.info("Filled location text box successfully.");
				test.pass("Filled location text box successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				Assert.assertTrue(true, 
						"Filled location text box successfully.");
			} 
		}catch(Exception e) {
			System.out.println("Unable to fill location text box due to ." + e.getMessage());
			logger.error("Unable to fill location text box due to  ." + e.getMessage());
			highlightFailure(localityTextBox);
			test.fail("Unable to fill location text box due to  ." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Unable to fill location text box due to .");
		}
		
		
		/**
		 * The below code will select the text 1 room, 2 adults from the drop down and perform search operation
		 */
		
		wait.until(ExpectedConditions.visibilityOf(travellerSelection));
		Select select= new Select(travellerSelection);
		select.selectByIndex(1);
		
		
		try {

			wait.until(ExpectedConditions.elementToBeClickable(searchButton));
			if(searchButton.isEnabled()) {
				searchButton.click();
				System.out.println("Clicked on seacrh button to Login.");
				logger.info("Clicked on seacrh button to Login.");
				test.pass("Clicked on seacrh button to Login.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				Assert.assertTrue(true, 
						"Clicked on seacrh button to Login.");
			} 
		}catch(Exception e) {
			System.out.println("Failed to click on seacrh button to login due to." + e.getMessage());
			logger.error("Failed to click on seacrh button to login due to." + e.getMessage());
			highlightFailure(searchButton);
			test.fatal("Failed to click on seacrh button to login due to." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Failed to click on seacrh button to Login.");
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
