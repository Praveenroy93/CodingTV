package com.tv.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tv.Base.BasePage;


public class SignInPage extends BasePage{

	Logger logger= LogManager.getLogger(SignInPage.class);

	//define page objects (PAGE OR) : using PageFactory Pattern


	@FindBy(xpath="//*[text()='Your trips']")
	WebElement yourTrip;

	@FindBy(xpath="//*[@title='Sign In']")
	WebElement signIn;

	@FindBy(xpath="//*[@id='signInButton']")
	WebElement signInButton;

	@FindBy(xpath= "//*[contains(text(),'There were errors in your submission')]")
	WebElement errorMessage;



	//Constructor of page class and initialize elements with driver
	public SignInPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * This method is to perform the signinError validation
	 * @throws Exception
	 */
	public void signIn() throws Exception{

		WebDriverWait wait= new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		try {
			//To click on the Your Trip button
			
			wait.until(ExpectedConditions.visibilityOf(yourTrip));		
			if(yourTrip.isEnabled()) {
				yourTrip.click();
				logger.info("Clicked on Your Trip button successfully.");
				test.pass("Clicked on Your Trip button successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());

				Assert.assertTrue(true, 
						"Clicked on Your Trip button successfully.");
			}
		}catch(Exception e) {
			System.out.println("Unable to click on your trip due to ." + e.getMessage());
			logger.error("Unable to click on your trip due to ." + e.getMessage());
			highlightFailure(yourTrip);
			test.fail("Unable to click on your trip due to ." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Unable to click on SignIn Button successfully.");
		}

		try {
			//To click on the Sign In button

			wait.until(ExpectedConditions.visibilityOf(signIn));
			if(signIn.isEnabled()) {
				signIn.click();
				logger.info("Clicked on Sign In button successfully.");
				test.pass("Clicked on Sign In button successfully.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				Assert.assertTrue(true, 
						"Clicked on Sign In button successfully.");
			} 
		}catch(Exception e) {
			System.out.println("Unable to click on Sign In due to ." + e.getMessage());
			logger.error("Unable to click on Sign In due to ." + e.getMessage());
			highlightFailure(signIn);
			test.fail("Unable to click on Sign In due to ." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Unable to click on Sign In successfully.");
		}
		
		/**
		 * The below code will switch to the specific frame and click on signIn button.
		 */
		driver.switchTo().frame("modal_window");
		try {

			wait.until(ExpectedConditions.elementToBeClickable(signInButton));
			if(signInButton.isEnabled()) {
				signInButton.click();
				System.out.println("Clicked on signIn button to Login.");
				logger.info("Clicked on signIn button to Login.");
				test.pass("Clicked on signIn button to Login.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
				Assert.assertTrue(true, 
						"Clicked on signIn button to Login.");
			} 
		}catch(Exception e) {
			System.out.println("Failed to click on SignIn button to login due to." + e.getMessage());
			logger.error("Failed to click on SignIn button to login due to." + e.getMessage());
			highlightFailure(signInButton);
			test.fatal("Failed to click on SignIn button to login due to." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Failed to click on SignIn button to Login.");
		}


		// To check if the error message appear or not
		
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
		highlightSuccess(errorMessage);
		try {
			if(errorMessage.isDisplayed())
			{
				System.out.println("SignIn Error test is successfull.");
				logger.info("SignIn Error test is successfull.");
				test.pass("SignIn Error test is successfull.",MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());

				Assert.assertTrue(true, 
						"SignIn Error test is successfull..");

			}
		}
		catch(Exception e) {
			System.out.println("Unable to validate SingIn Error test due to." + e.getMessage());
			logger.error("Unable to validate SingIn Error test due to." + e.getMessage());
			highlightFailure(errorMessage);
			test.fatal("Unable to validate SingIn Error test due to." + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
			Assert.assertFalse(false, 
					"Unable to validate SingIn Error test due to.");
		}


	}


}




