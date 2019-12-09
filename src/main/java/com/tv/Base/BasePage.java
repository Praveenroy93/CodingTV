package com.tv.Base;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Praveen
 *
 */

public class BasePage{

	public final Logger logger = LogManager.getLogger(this.getClass().getSimpleName());;

	private ScreenRecorder screenRecorder;
	public static ExtentHtmlReporter htmlReporter1;
	public static ExtentReports extent;
	public static ExtentTest test;
	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "TVReport.html";
	//	Logger log=Logger.getLogger(BasePage.class);
	String RecordingClassName;
	String RecordingMethodName;
	String fileRecordingLocation;
	File RecordedFile;




	public static WebDriver driver;
	public Actions action;
	public Properties prop;
	public WebDriverWait wait;
	public File f1;
	public FileInputStream file;
	/*public File f1;
	public FileInputStream file;*/
	public static ThreadLocal<ExtentTest> test1 = new ThreadLocal<ExtentTest>();

	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public WebDriver initialize_driver() {

		String browserName = prop.getProperty("browser");


		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--headless");
//			options.addArguments("--disable-gpu");
//			driver = new ChromeDriver(options);
			driver= new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("ie")){
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 15);
		driver.manage().deleteAllCookies();
		driver.manage().window().fullscreen();
		tdriver.set(driver);
		return getDriver();


	}

	/**
	 * This method is use for 
	 * scrolling down the page.
	 *
	 * @Date  09/12/2019
	 * @Revision History
	 * 
	 */
	public void scrollDown() {
		JavascriptExecutor jse;
		jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}


	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

	/**
	 * Read data from config file using Properties
	 * @param file path
	 * 
	 * @throws Exception
	 * @Date  09/12/2019
	 * @Revision History
	 * 
	 */
	public Properties initialize_Properties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("/Users/Praveen/git/CodingTV/src/main/java/com/tv/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}


	@BeforeSuite
	public void settingUp() throws Exception
	{

		htmlReporter1 = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/TVReport.html");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter1);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Host Name", System.getProperty("user.name"));
		extent.setSystemInfo("Admin.Environmentironment", "QA");
		htmlReporter1.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter1.config().setTheme(Theme.STANDARD);
		test = extent.createTest(getClass().getSimpleName());



	}



	@BeforeTest
	public void loadProperties(){

		PropertyConfigurator.configure(System.getProperty("user.dir")+"//Config//log4j2.properties");
		Reporter.log("Reporter log started",true);

		Reporter.log("Properties have been initiated. Moving to next step",true);



	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}


	/**
	 * This method will start the recording of the execution. recorded file will
	 * get generated in the folder name as className_methodName. recording can
	 * be started from any particular time.
	 */

	@SuppressWarnings({ "restriction", "deprecation" })
	public void startRecording() {

		RecordingClassName = sun.reflect.Reflection.getCallerClass(2).getSimpleName();
		RecordingMethodName = new Throwable().fillInStackTrace()
				.getStackTrace()[1].getMethodName();
		fileRecordingLocation = System.getProperty("user.dir")+"//TestcaseVideos//" + RecordingClassName + "_"
				+ RecordingMethodName;
		RecordedFile = new File(fileRecordingLocation);

		GraphicsConfiguration gc = GraphicsEnvironment//
				.getLocalGraphicsEnvironment()//
				.getDefaultScreenDevice()//
				.getDefaultConfiguration();
		try {
			screenRecorder = new ScreenRecorder(gc, null, new Format(
					MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
							ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							CompressorNameKey,
							ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey,
							(int) 24, FrameRateKey, Rational.valueOf(15),
							QualityKey, 1.0f, KeyFrameIntervalKey,
							(int) (15 * 60)), new Format(MediaTypeKey,
									MediaType.VIDEO, EncodingKey, "black",
									FrameRateKey, Rational.valueOf(30)), null,
					RecordedFile);
			screenRecorder.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will stop the recording.
	 */
	public void stopRecording() {
		if (screenRecorder != null) {
			try {
				screenRecorder.stop();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * to delete the recorded file.if the test is passing then this method will delete the recorded file.
	 */
	public void deleteRecordedFile() {
		if (RecordedFile != null) {
			String[] children = RecordedFile.list();
			for (int i = 0; i <children.length; i++) {
				new File(RecordedFile, children[i]).delete();
				System.out.println("Deleted this " + RecordedFile + "//"
						+ children[i]);
			}

			// The directory is now empty so delete it
			RecordedFile.delete();
		}
	}

	public String captureScreenShot(ITestResult result, String status) throws IOException{	
		String destDir = "";
		String passfailMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
		//To capture screenshot.
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		//If status = fail then set folder name "screenshots/Failures"
		if(status.equalsIgnoreCase("fail")){
			destDir = System.getProperty("user.dir")+"/screenshots/Failures";
		}
		//If status = pass then set folder name "screenshots/Success"
		else if (status.equalsIgnoreCase("pass")) {
			destDir = System.getProperty("user.dir")+"/screenshots/Success";
		}

		//To create folder to store screenshots
		new File(destDir).mkdirs();
		//Set file name with combination of test class name + date time.
		String destFile = passfailMethod+" - "+dateFormat.format(new Date()) + ".png";

		try {
			//Store file at destination folder location
			if(destDir!=null)
				FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return destDir + "/" + destFile;
	} 

	@AfterMethod
	public void getResult(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{

			String screenShot = captureScreenShot(result,"fail");
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail("Screen Shot : " + 
					MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(result, "fail")).build());
			test.fail("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));

			//captureScreenShot(result,"fail");
		}


		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			//String screenShot = captureScreen(SeleniumRepo.driver);



			String screenShot = captureScreenShot(result,"pass");
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));

			test.pass("Screen Shot : " + MediaEntityBuilder.createScreenCaptureFromPath(captureScreenShot(result, "pass")).build());

			//captureScreenShot(result,"fail");

			test.pass("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
		}

		else 
		{
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}

	}

	public void finalAssertTrue(boolean condition, String message) {
		stopRecording();
		if (condition)
			deleteRecordedFile();
		assertTrue(condition, message);

	}

	public void finalAssertFalse(boolean condition, String message) {
		stopRecording();
		if (condition)
			deleteRecordedFile();
		assertFalse(condition, message);

	}


	public String CurrentDate() {   
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date=dtf.format(now);
		return date;
	}  


	@AfterSuite
	public void endTest() throws Exception
	{

		extent.flush();
	
	}




	protected void highlightSuccess(WebElement e) throws InterruptedException {
		String color = backgroundColor(e);
		changeColor(e, "green");
		Thread.sleep(2000);
		changeColor(e, color);
	}

	protected void highlightFailure(WebElement e) throws InterruptedException {
		String color = backgroundColor(e);
		changeColor(e, "red");
		Thread.sleep(2000);
		changeColor(e, color);
	}

	private void changeColor(WebElement element, String color) {

		JavascriptExecutor jsDriver = (JavascriptExecutor)this.driver;
		String jsQuery = 
				String.format("arguments[0].style.backgroundColor='%s'",
						color);

		jsDriver.executeScript(jsQuery, element);
	}

	private String backgroundColor(WebElement e) {
		JavascriptExecutor jsDriver = (JavascriptExecutor)this.driver;
		String jsQuery = "return arguments[0].style.backgroundColor";

		return (String)jsDriver.executeScript(jsQuery, e);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}






	
}
